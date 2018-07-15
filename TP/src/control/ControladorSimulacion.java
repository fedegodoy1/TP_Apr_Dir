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
import objects.Visitantes;

public class ControladorSimulacion {

    VentanaPrincipal vistaAplicacion;

    private static VectorEstado actual;
    private static VectorEstado anterior;

    private List<VectorEstadoUI> modelo;

    ControladorSimulacion() {
        vistaAplicacion = new VentanaPrincipal(this);
    }

    public void mostrarVentanaPrincipal() {
        vistaAplicacion.setVisible(true);
    }

    public void simular() {

        int iteracionActual = 0;
        int iteracionesMostrando = 0;
        inicializar();

        int minutosASimular = Configuracion.getConfiguracion().getMinutosASimular();

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
        vistaAplicacion.setearModelo(modelo);
        modelo = new ArrayList<>();
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
                if(visitante.getFin_recorrido() > 0 &&
                        visitante.getFin_recorrido() < Double.MAX_VALUE) {
                    String sala = visitante.getSala().getNombre();
                    switch (sala) {
                        case "C":
                            mapaDeTiempos.put(visitante.getFin_recorrido(), Evento.FinRecorridoSalaC);
                            break;
                        case "A":
                            mapaDeTiempos.put(visitante.getFin_recorrido(), Evento.FinRecorridoSalaA);
                            break;
                        case "B":
                            mapaDeTiempos.put(visitante.getFin_recorrido(), Evento.FinRecorridoSalaB);
                            break;
                        case "D":
                            mapaDeTiempos.put(visitante.getFin_recorrido(), Evento.FinRecorridoSalaD);
                            break;
                    }
                    
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
