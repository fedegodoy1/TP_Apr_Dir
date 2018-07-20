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
            tRecorridoD = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaD(),
                    config.getHastaFinRecorridoSalaD(),
                    rndFinRecorridoD);
            FinRecorridoSalaD newFinRecorridoD = new FinRecorridoSalaD(rndFinRecorridoD, tRecorridoD, tRecorridoD + horaActual);

            visitanteTerminoDeRecorrerSalaB.setFinRecorridoD(newFinRecorridoD);
            visitanteTerminoDeRecorrerSalaB.setFinRecorridoB(new FinRecorridoSalaB(
                    visitanteTerminoDeRecorrerSalaB.getFinRecorridoA().getRnd1(),
                    visitanteTerminoDeRecorrerSalaB.getFinRecorridoA().getRnd2(),
                    visitanteTerminoDeRecorrerSalaB.getFinRecorridoA().getSenocoseno()));

            actual.getSalas().get(3).aumentarCapacidad();
            actual.getSalas().get(3).setEstado(Sala.Estado.CON_VISITANTES);
        } else {
            visitanteTerminoDeRecorrerSalaB.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_D);
            actual.getSalas().get(3).agregarVisitanteALaCola();
        }
        actual.getSalas().get(2).disminuirCapacidad();

        if (actual.getSalas().get(2).getCola() > 0 && actual.getSalas().get(2).getCapacidad() < 40) {

            actual.getSalas().get(2).setEstado(Sala.Estado.CON_VISITANTES);
            Visitantes visitanteQueRecorreSalaB = new Visitantes();

            for (Visitantes visitante : actual.getVisitantes()) {
                if (visitante.getEstado().equals(Visitantes.Estado.ESPERANDO_RECORRIDO_B)) {
                    visitanteQueRecorreSalaB = visitante;
                    break;
                }
            }

            visitanteQueRecorreSalaB.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_B);
            if (actual.getSalas().get(2).getSenocosenoSalaB().equals("")) {
                actual.getSalas().get(2).setRnd1SalaB(randomObject.nextDouble());
                actual.getSalas().get(2).setRnd2SalaB(randomObject.nextDouble());
                actual.getSalas().get(2).setSenocosenoSalaB(COS);

                visitanteQueRecorreSalaB.getFinRecorridoB().setSenocoseno(COS);
                finRecorridoSalaB.setRnd1(actual.getSalas().get(2).getRnd1SalaB());
                finRecorridoSalaB.setRnd2(actual.getSalas().get(2).getRnd2SalaB());

                tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                        config.getDesviacionFinRecorridoSalaA(),
                        actual.getSalas().get(2).getRnd1SalaB(),
                        actual.getSalas().get(2).getRnd2SalaB(),
                        visitanteQueRecorreSalaB.getFinRecorridoB().getSenocoseno());

                finRecorridoSalaB.settRecorrido(tRecorridoB);
                finRecorridoSalaB.setFinRecorrido(tRecorridoB + horaActual);

                visitanteQueRecorreSalaB.setFinRecorridoA(new FinRecorridoSalaA(
                        visitanteQueRecorreSalaB.getFinRecorridoA().getRnd1(),
                        visitanteQueRecorreSalaB.getFinRecorridoA().getRnd2(),
                        visitanteQueRecorreSalaB.getFinRecorridoA().getSenocoseno()));
                visitanteQueRecorreSalaB.setFinRecorridoB(finRecorridoSalaB);
            } else {
                if (COS.equals(actual.getSalas().get(2).getSenocosenoSalaB())) {

                    actual.getSalas().get(2).setSenocosenoSalaB(SENO);
                    visitanteQueRecorreSalaB.getFinRecorridoB().setSenocoseno(SENO);

                    finRecorridoSalaB.setRnd1(actual.getSalas().get(2).getRnd1SalaB());
                    finRecorridoSalaB.setRnd2(actual.getSalas().get(2).getRnd2SalaB());

                    tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                            config.getDesviacionFinRecorridoSalaA(),
                            visitanteQueRecorreSalaB.getFinRecorridoB().getRnd1(),
                            visitanteQueRecorreSalaB.getFinRecorridoB().getRnd2(),
                            visitanteQueRecorreSalaB.getFinRecorridoB().getSenocoseno());

                    finRecorridoSalaB.settRecorrido(tRecorridoB);
                    finRecorridoSalaB.setFinRecorrido(tRecorridoB + horaActual);

                    visitanteQueRecorreSalaB.setFinRecorridoA(new FinRecorridoSalaA(
                            visitanteQueRecorreSalaB.getFinRecorridoA().getRnd1(),
                            visitanteQueRecorreSalaB.getFinRecorridoA().getRnd2(),
                            visitanteQueRecorreSalaB.getFinRecorridoA().getSenocoseno()));
                    visitanteQueRecorreSalaB.setFinRecorridoB(finRecorridoSalaB);

                } else {

                    actual.getSalas().get(2).setRnd1SalaB(randomObject.nextDouble());
                    actual.getSalas().get(2).setRnd2SalaB(randomObject.nextDouble());
                    actual.getSalas().get(2).setSenocosenoSalaB(COS);

                    visitanteQueRecorreSalaB.getFinRecorridoB().setSenocoseno(COS);
                    finRecorridoSalaB.setRnd1(actual.getSalas().get(2).getRnd1SalaB());
                    finRecorridoSalaB.setRnd2(actual.getSalas().get(2).getRnd2SalaB());

                    tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                            config.getDesviacionFinRecorridoSalaA(),
                            actual.getSalas().get(2).getRnd1SalaB(),
                            actual.getSalas().get(2).getRnd2SalaB(),
                            visitanteQueRecorreSalaB.getFinRecorridoB().getSenocoseno());

                    finRecorridoSalaB.settRecorrido(tRecorridoB);
                    finRecorridoSalaB.setFinRecorrido(tRecorridoB + horaActual);

                    visitanteQueRecorreSalaB.setFinRecorridoA(new FinRecorridoSalaA(
                            visitanteQueRecorreSalaB.getFinRecorridoA().getRnd1(),
                            visitanteQueRecorreSalaB.getFinRecorridoA().getRnd2(),
                            visitanteQueRecorreSalaB.getFinRecorridoA().getSenocoseno()));
                    visitanteQueRecorreSalaB.setFinRecorridoB(finRecorridoSalaB);

                }
            }

            actual.getSalas().get(2).disminuirCola();
            actual.getSalas().get(2).aumentarCapacidad();

            if (actual.getSalas().get(2).getCapacidad() == 40) {
                actual.getSalas().get(2).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
            }

        } else if (actual.getSalas().get(2).getCola() == 0 && actual.getSalas().get(2).getCapacidad() == 0) {
            actual.getSalas().get(2).setEstado(Sala.Estado.VACIA);
        }
    }

}
