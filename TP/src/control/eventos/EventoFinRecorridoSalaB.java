package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
import eventos.FinRecorridoSalaA;
import eventos.FinRecorridoSalaB;
import eventos.FinRecorridoSalaD;
import java.util.Random;
import model.Configuracion;
import objects.Distribuciones;
import static objects.Distribuciones.COS;
import static objects.Distribuciones.SENO;
import objects.Sala;
import objects.Visitantes;

public class EventoFinRecorridoSalaB extends Evento {
    
    public EventoFinRecorridoSalaB(String nombre) {
        super(nombre);
    }

    @Override
    public void actualizarEstadoVector() {
        Configuracion config = Configuracion.getConfiguracion();
        VectorEstado actual = ControladorSimulacion.getVectorActual();
        VectorEstado anterior = ControladorSimulacion.getVectorAnterior();
        Random randomObject = new Random();

        actual.setMaxVisitantesEnEntrada(anterior.getMaxVisitantesEnEntrada());
        actual.setAcumuladorVisitantes(anterior.getAcumuladorVisitantes());
        actual.setSalas(clonarSalas(anterior.getSalas()));
        actual.setVisitantes(clonarVisitantes(anterior.getVisitantes()));
        actual.setLlegadaVisitantes(anterior.getLlegadaVisitantes());
        actual.setLote(anterior.getAsignacionLote());

        FinRecorridoSalaB finRecorridoSalaB = new FinRecorridoSalaB();
        double rndFinRecorridoB1 = 0.0;
        double rndFinRecorridoB2 = 0.0;
        double tRecorridoB = 0.0;

        double rndFinRecorridoD = 0.0;
        double tRecorridoD = 0.0;

        double horaActual = actual.getReloj();
        Visitantes visitanteTerminoDeRecorrerSalaB = null;
        for (Visitantes visitante : actual.getVisitantes()) {
            if (horaActual == visitante.getFinRecorridoB().getFinRecorrido()) {
                visitanteTerminoDeRecorrerSalaB = visitante;
                break;
            }
        }
        
        // Ahora calcula para el visitante que termino de recorrer antes de el, la prox sala si o si sera D
        if (actual.getSalas().get(3).getCapacidad() < 100) {

            rndFinRecorridoD = randomObject.nextDouble();
            tRecorridoD = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaC(),
                    config.getHastaFinRecorridoSalaD(),
                    rndFinRecorridoD);
            FinRecorridoSalaD newFinRecorridoD = new FinRecorridoSalaD(rndFinRecorridoD, tRecorridoD, tRecorridoD + horaActual);

            visitanteTerminoDeRecorrerSalaB.setFinRecorridoD(newFinRecorridoD);
            visitanteTerminoDeRecorrerSalaB.setFinRecorridoB(new FinRecorridoSalaB(
                        visitanteTerminoDeRecorrerSalaB.getFinRecorridoA().getRnd1(),
                        visitanteTerminoDeRecorrerSalaB.getFinRecorridoA().getRnd2(),
                        visitanteTerminoDeRecorrerSalaB.getFinRecorridoA().getSenocoseno()));
            
            actual.getSalas().get(3).setCapacidad(anterior.getSalas().get(3).getCapacidad() + 1);
            actual.getSalas().get(3).setEstado(Sala.Estado.CON_VISITANTES);
        } else {
            visitanteTerminoDeRecorrerSalaB.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_D);
            actual.getSalas().get(3).agregarVisitanteALaCola();
        }
        actual.getSalas().get(2).setCapacidad(actual.getSalas().get(2).getCapacidad() - 1);

        if (actual.getSalas().get(2).getCola() > 0) {

            actual.getSalas().get(2).setEstado(Sala.Estado.CON_VISITANTES);
            Visitantes visitanteQueRecorreSalaB = new Visitantes();

            for (Visitantes visitante : actual.getVisitantes()) {
                if (visitante.getEstado().equals(Visitantes.Estado.ESPERANDO_RECORRIDO_B)) {
                    visitanteQueRecorreSalaB = visitante;
                    break;
                }
            }

            visitanteQueRecorreSalaB.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_B);

            if (COS.equals(visitanteQueRecorreSalaB.getFinRecorridoB().getSenocoseno())) {
                visitanteQueRecorreSalaB.getFinRecorridoB().setSenocoseno(SENO);
                tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                        config.getDesviacionFinRecorridoSalaA(),
                        visitanteQueRecorreSalaB.getFinRecorridoB().getRnd1(),
                        visitanteQueRecorreSalaB.getFinRecorridoB().getRnd2(),
                        visitanteQueRecorreSalaB.getFinRecorridoB().getSenocoseno());
                finRecorridoSalaB.setRnd1(visitanteQueRecorreSalaB.getFinRecorridoB().getRnd1());
                finRecorridoSalaB.setRnd2(visitanteQueRecorreSalaB.getFinRecorridoB().getRnd2());
                finRecorridoSalaB.settRecorrido(tRecorridoB);
                finRecorridoSalaB.setFinRecorrido(tRecorridoB + horaActual);
                
                visitanteQueRecorreSalaB.setFinRecorridoA(new FinRecorridoSalaA(
                        visitanteQueRecorreSalaB.getFinRecorridoA().getRnd1(),
                        visitanteQueRecorreSalaB.getFinRecorridoA().getRnd2(),
                        visitanteQueRecorreSalaB.getFinRecorridoA().getSenocoseno()));
                visitanteQueRecorreSalaB.setFinRecorridoB(finRecorridoSalaB);

            } else {

                rndFinRecorridoB1 = randomObject.nextDouble();
                rndFinRecorridoB2 = randomObject.nextDouble();
                visitanteQueRecorreSalaB.getFinRecorridoB().setSenocoseno(COS);
                tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                        config.getDesviacionFinRecorridoSalaA(),
                        rndFinRecorridoB1,
                        rndFinRecorridoB2,
                        visitanteQueRecorreSalaB.getFinRecorridoB().getSenocoseno());
                finRecorridoSalaB.setRnd1(rndFinRecorridoB1);
                finRecorridoSalaB.setRnd2(rndFinRecorridoB2);
                finRecorridoSalaB.settRecorrido(tRecorridoB);
                finRecorridoSalaB.setFinRecorrido(tRecorridoB + horaActual);

                visitanteQueRecorreSalaB.setFinRecorridoA(new FinRecorridoSalaA(
                        visitanteQueRecorreSalaB.getFinRecorridoA().getRnd1(),
                        visitanteQueRecorreSalaB.getFinRecorridoA().getRnd2(),
                        visitanteQueRecorreSalaB.getFinRecorridoA().getSenocoseno()));
                visitanteQueRecorreSalaB.setFinRecorridoB(finRecorridoSalaB);

            }

            actual.getSalas().get(2).disminuirCola();
            actual.getSalas().get(2).setCapacidad(actual.getSalas().get(2).getCapacidad() + 1);

            if (actual.getSalas().get(2).getCapacidad() == 40) {
                actual.getSalas().get(2).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
            }

        } else if (actual.getSalas().get(2).getCola() == 0 && actual.getSalas().get(2).getCapacidad() == 0) {
            actual.getSalas().get(2).setEstado(Sala.Estado.VACIA);
        }
    }

}
