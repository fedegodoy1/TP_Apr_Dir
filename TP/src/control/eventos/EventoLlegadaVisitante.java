package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
import eventos.AsignacionLote;
import eventos.AsignacionRecorrido;
import eventos.FinRecorridoSalaC;
import eventos.FinRecorridoSalaD;
import eventos.LlegadaVisitantes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Configuracion;
import objects.Calculos;
import objects.Distribuciones;
import objects.Sala;
import objects.Visitantes;

public class EventoLlegadaVisitante extends Evento {

    public EventoLlegadaVisitante(String nombre) {
        super(nombre);
    }

    @Override
    public void actualizarEstadoVector() {
        Configuracion config = Configuracion.getConfiguracion();
        VectorEstado actual = ControladorSimulacion.getVectorActual();
        VectorEstado anterior = ControladorSimulacion.getVectorAnterior();

        actual.setVisitantes(clonarVisitantes(anterior.getVisitantes()));
        actual.setSalas(clonarSalas(anterior.getSalas()));

        Random randomObject = new Random();
        LlegadaVisitantes newLlegada = new LlegadaVisitantes();
        AsignacionLote newAsignacionLote = new AsignacionLote();
        AsignacionRecorrido newAsignacionRecorrido = new AsignacionRecorrido();
        List<Visitantes> newLoteVisitantes = new ArrayList();

        double rndLote = randomObject.nextDouble();
        int lote = 0;
        lote = Distribuciones.calcular_poisson(config.getMediaLote(), rndLote);
        newAsignacionLote.setRnd(rndLote);
        newAsignacionLote.setLoteVisitantes(lote);

        actual.setLote(newAsignacionLote);

        for (int i = 0; i < lote; i++) {
            newLoteVisitantes.add(new Visitantes());
        }

        double rndAsignacionRecorrido = 0.0;

        double rndFinRecorrido = 0.0;
        double tRecorrido = 0.0;
        double finRecorrido = 0.0;

        double rndProxLlegada = randomObject.nextDouble();
        double tEntreLlegada = Distribuciones.calcular_exponencial(
                Configuracion.getConfiguracion().getMediaLlegadaVisitantes(),
                rndProxLlegada);
        double proxLlegada = tEntreLlegada + actual.getReloj();
        newLlegada.setRnd(rndProxLlegada);
        newLlegada.setTiempo_entre_llegadas(tEntreLlegada);
        newLlegada.setProx_llegada(proxLlegada);
        
        actual.setLlegadaVisitantes(newLlegada);
        // es siempre 0 por ser la primera sala que se recorre
        for (Visitantes newVisitante : newLoteVisitantes) {

            rndAsignacionRecorrido = randomObject.nextDouble();
            List<Sala> recorridoSalas = Calculos.calcularAsignacionDeRecorrido(rndAsignacionRecorrido, actual.getSalas());
            String recorridoSalasString = "";
            for(Sala sala : recorridoSalas) {
                if(!recorridoSalasString.equals("")) {
                    recorridoSalasString = recorridoSalasString + ", " + sala.getNombre();
                } else {
                    recorridoSalasString = "" + sala.getNombre();
                }
            }
            newAsignacionRecorrido.setRnd(rndAsignacionRecorrido);
            newAsignacionRecorrido.setSalas(recorridoSalasString);

            newVisitante.setAsignacion(newAsignacionRecorrido);
            newVisitante.setRecorrido(recorridoSalas);

            if (actual.getSalas().get(0).getCapacidad() < 100) {

                FinRecorridoSalaC newFinRecorridoSalaC = new FinRecorridoSalaC();
                rndFinRecorrido = randomObject.nextDouble();
                tRecorrido = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaC(), config.getHastaFinRecorridoSalaC(), rndFinRecorrido);
                finRecorrido = tRecorrido + actual.getReloj();

                newFinRecorridoSalaC.setRnd(rndFinRecorrido);
                newFinRecorridoSalaC.settRecorrido(tRecorrido);
                newFinRecorridoSalaC.setFinRecorrido(finRecorrido);

                newVisitante.setFinRecorridoC(newFinRecorridoSalaC);
                newVisitante.setSala("C");
                newVisitante.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_C);

                actual.getVisitantes().add(newVisitante);
                actual.getSalas().get(0).setCapacidad(actual.getSalas().get(0).getCapacidad() + 1);
                actual.getSalas().get(0).setEstado(Sala.Estado.CON_VISITANTES);
                
                if(actual.getSalas().get(0).getCapacidad() == 100) {
                    actual.getSalas().get(0).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }
            } else {
                
                newVisitante.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_C);
                actual.getSalas().get(0).agregarVisitanteALaCola();
                
            }
        }
        actual.setAcumuladorVisitantes(anterior.getAcumuladorVisitantes() + lote);
        superaMaximoEnCola(actual);
    }
    
    private void superaMaximoEnCola(VectorEstado actual) {
        if(actual.getMaxVisitantesEnEntrada() > actual.getSalas().get(0).getCola()) {
            actual.setMaxVisitantesEnEntrada(actual.getSalas().get(0).getCola());
        }
    }
}
