package control;

import control.eventos.Evento;
import front.VentanaPrincipal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import model.Configuracion;
import model.VectorEstadoUI;
import objects.Estadisticas;
import objects.Visitantes;

public class ControladorSimulacion {

    VentanaPrincipal vistaAplicacion;
    Estadisticas estadisticas;

    private static VectorEstado actual;
    private static VectorEstado anterior;

    private List<VectorEstadoUI> modelo;

    ControladorSimulacion() {
        vistaAplicacion = new VentanaPrincipal(this);
        estadisticas = new Estadisticas();
    }
    
    public Estadisticas mostrarEstadisticas() {
        return estadisticas;
    }
    
    public void mostrarVentanaPrincipal() {
        vistaAplicacion.setVisible(true);
    }

    public void simular() {

        int iteracionActual = 0;
        int iteracionesMostrando = 0;
        inicializar();

        double minutosASimular = new Double(Configuracion.getConfiguracion().getMinutosASimular());

        while (iteracionActual < 1000000 && anterior.getReloj() < minutosASimular) {
            //Mover vector "actual" a "anterior"
            rotacionVector();
            Evento nuevoEvento = determinarProximoEvento();
            //Actualizar reloj a la hora de este evento.
            actual.setReloj(nuevoEvento.getHoraEvento());
            actual.setEvento(nuevoEvento);
            actual.getEvento().actualizarEstadoVector();
            
            if (seMuestra(iteracionesMostrando))
            {
                //Guardar en la lista a devolver
                guardarVectorParaVista();
                iteracionesMostrando++;
            }
            iteracionActual++;
        }
        
        //Actualizar Vista
        obtenerEstadisticas();
        vistaAplicacion.setearModelo(modelo);
        modelo = new ArrayList<>();
    }
    
    private void obtenerEstadisticas() {
        estadisticas.setMaxCantVisitantesEnCola(actual.getMaxVisitantesEnEntrada());
        estadisticas.setAcumVisitantesEnSistema(actual.getAcumuladorVisitantes());
    }

    public void inicializar() {
        actual = new VectorEstado();
        actual.setReloj(0);
        actual.setEvento(Evento.Inicial);
        actual.getEvento().actualizarEstadoVector();

        if (seMuestra(0)) {
            //Guardar en la lista a devolver
            guardarVectorParaVista();
        }
        anterior = actual;
    }

    private Evento determinarProximoEvento() {

        HashMap<Double, Evento> mapaDeTiempos = new HashMap<>();

        if (anterior.getLlegadaVisitantes() != null
                && anterior.getLlegadaVisitantes().getProx_llegada() > 0
                && anterior.getLlegadaVisitantes().getProx_llegada() < Double.MAX_VALUE) {
            mapaDeTiempos.put(anterior.getLlegadaVisitantes().getProx_llegada(), Evento.LlegadaVisitante);
        }

        if (anterior.getVisitantes() != null && anterior.getVisitantes().size() > 0) {
            
            for(Visitantes visitante : anterior.getVisitantes()) {
                if(visitante.getFinRecorridoC().getFinRecorrido() > 0 &&
                        visitante.getFinRecorridoC().getFinRecorrido() < Double.MAX_VALUE) {
                    mapaDeTiempos.put(visitante.getFinRecorridoC().getFinRecorrido(), Evento.FinRecorridoSalaC);
                }
                if(visitante.getFinRecorridoA().getFinRecorrido() > 0 &&
                        visitante.getFinRecorridoA().getFinRecorrido() < Double.MAX_VALUE) {
                    mapaDeTiempos.put(visitante.getFinRecorridoA().getFinRecorrido(), Evento.FinRecorridoSalaA);
                }
                if(visitante.getFinRecorridoB().getFinRecorrido() > 0 &&
                        visitante.getFinRecorridoB().getFinRecorrido() < Double.MAX_VALUE) {
                    mapaDeTiempos.put(visitante.getFinRecorridoB().getFinRecorrido(), Evento.FinRecorridoSalaB);
                }
                if(visitante.getFinRecorridoD().getFinRecorrido() > 0 &&
                        visitante.getFinRecorridoD().getFinRecorrido() < Double.MAX_VALUE) {
                    mapaDeTiempos.put(visitante.getFinRecorridoD().getFinRecorrido(), Evento.FinRecorridoSalaD);
                }
            }
        }
        
        Set<Double> tiempos = mapaDeTiempos.keySet();
        Double tiempoSiguiente = Collections.min(tiempos);
        
        Evento returnValue = mapaDeTiempos.get(tiempoSiguiente);
        returnValue.setHoraEvento(tiempoSiguiente);
        
        return returnValue;
    }

    public static VectorEstado getVectorActual() {
        return actual;
    }

    public static VectorEstado getVectorAnterior() {
        return anterior;
    }

    private boolean seMuestra(int iteracionActual) {
        //actual.getReloj() > Config &&
        // iteraciones < Config
        boolean seMuestra = false;
        if (actual != null) {
            seMuestra = actual.getReloj() >= Configuracion.getConfiguracion().getMinutoDesde();

        }
        if (seMuestra) {
            seMuestra = seMuestra && iteracionActual <= Configuracion.getConfiguracion().getIteracionesAMostrar();
        }
        return seMuestra;
    }

    private void rotacionVector() {
        anterior = actual;
        actual = new VectorEstado();
    }

    private void guardarVectorParaVista() {
        if (modelo == null) {
            modelo = new ArrayList<>();
        }

        modelo.add(actual);

    }
}
