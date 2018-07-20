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

public class EventoFinRecorridoSalaA extends Evento {

    public EventoFinRecorridoSalaA(String nombre) {
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

        double tRecorridoB = 0.0;

        double tRecorridoA = 0.0;

        double rndFinRecorridoD = 0.0;
        double tRecorridoD = 0.0;
        double finRecorridoD = 0.0;

        double horaActual = actual.getReloj();
        Visitantes visitanteTerminoDeRecorrerSalaA = null;
        for (Visitantes visitante : actual.getVisitantes()) {
            if (horaActual == visitante.getFinRecorridoA().getFinRecorrido()) {
                visitanteTerminoDeRecorrerSalaA = visitante;
                break;
            }
        }

        if (visitanteTerminoDeRecorrerSalaA.getRecorrido().get(2).getNombre().equals("B")) {
            if (actual.getSalas().get(2).getCapacidad() < 40) {
                if (actual.getSalas().get(2).getSenocosenoSalaB().equals("")) {
                    FinRecorridoSalaB nuevoFinRecorridoB = new FinRecorridoSalaB();

                    actual.getSalas().get(2).setRnd1SalaB(randomObject.nextDouble());
                    actual.getSalas().get(2).setRnd2SalaB(randomObject.nextDouble());
                    actual.getSalas().get(2).setSenocosenoSalaB(COS);

                    nuevoFinRecorridoB.setSenocoseno(COS);
                    nuevoFinRecorridoB.setRnd1(actual.getSalas().get(2).getRnd1SalaB());
                    nuevoFinRecorridoB.setRnd2(actual.getSalas().get(2).getRnd2SalaB());

                    tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaB(),
                            config.getDesviacionFinRecorridoSalaB(),
                            nuevoFinRecorridoB.getRnd1(),
                            nuevoFinRecorridoB.getRnd2(),
                            nuevoFinRecorridoB.getSenocoseno());

                    visitanteTerminoDeRecorrerSalaA.setFinRecorridoB(nuevoFinRecorridoB);
                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().settRecorrido(tRecorridoB);
                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().setFinRecorrido(tRecorridoB + horaActual);

                    visitanteTerminoDeRecorrerSalaA.setFinRecorridoA(new FinRecorridoSalaA(
                            visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getRnd1(),
                            visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getRnd2(),
                            visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getSenocoseno()));
                } else {
                    if (actual.getSalas().get(2).getSenocosenoSalaB().equals("Coseno")) {

                        FinRecorridoSalaB nuevoFinRecorridoB = new FinRecorridoSalaB();
                        actual.getSalas().get(2).setSenocosenoSalaB(SENO);
                        nuevoFinRecorridoB.setRnd1(actual.getSalas().get(2).getRnd1SalaB());
                        nuevoFinRecorridoB.setRnd2(actual.getSalas().get(2).getRnd2SalaB());
                        nuevoFinRecorridoB.setSenocoseno(SENO);

                        tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaB(),
                                config.getDesviacionFinRecorridoSalaB(),
                                nuevoFinRecorridoB.getRnd1(),
                                nuevoFinRecorridoB.getRnd2(),
                                nuevoFinRecorridoB.getSenocoseno());

                        visitanteTerminoDeRecorrerSalaA.setFinRecorridoB(nuevoFinRecorridoB);
                        visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().settRecorrido(tRecorridoB);
                        visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().setFinRecorrido(tRecorridoB + horaActual);

                        visitanteTerminoDeRecorrerSalaA.setFinRecorridoA(new FinRecorridoSalaA(
                                visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getRnd1(),
                                visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getRnd2(),
                                visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getSenocoseno()));

                    } else {

                        FinRecorridoSalaB nuevoFinRecorridoB = new FinRecorridoSalaB();

                        actual.getSalas().get(2).setRnd1SalaB(randomObject.nextDouble());
                        actual.getSalas().get(2).setRnd2SalaB(randomObject.nextDouble());
                        actual.getSalas().get(2).setSenocosenoSalaB(COS);

                        nuevoFinRecorridoB.setSenocoseno(COS);
                        nuevoFinRecorridoB.setRnd1(actual.getSalas().get(2).getRnd1SalaB());
                        nuevoFinRecorridoB.setRnd2(actual.getSalas().get(2).getRnd2SalaB());

                        tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaB(),
                                config.getDesviacionFinRecorridoSalaB(),
                                nuevoFinRecorridoB.getRnd1(),
                                nuevoFinRecorridoB.getRnd2(),
                                nuevoFinRecorridoB.getSenocoseno());

                        visitanteTerminoDeRecorrerSalaA.setFinRecorridoB(nuevoFinRecorridoB);
                        visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().settRecorrido(tRecorridoB);
                        visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().setFinRecorrido(tRecorridoB + horaActual);

                        visitanteTerminoDeRecorrerSalaA.setFinRecorridoA(new FinRecorridoSalaA(
                                visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getRnd1(),
                                visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getRnd2(),
                                visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getSenocoseno()));

                    }
                }
                visitanteTerminoDeRecorrerSalaA.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_B);
                visitanteTerminoDeRecorrerSalaA.setSala("B");
                actual.getSalas().get(2).setEstado(Sala.Estado.CON_VISITANTES);
                actual.getSalas().get(2).aumentarCapacidad();

                if (actual.getSalas().get(2).getCapacidad() == 40) {
                    actual.getSalas().get(2).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }

            } else {
                visitanteTerminoDeRecorrerSalaA.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_B);
                actual.getSalas().get(2).agregarVisitanteALaCola();
            }
        } else if (visitanteTerminoDeRecorrerSalaA.getRecorrido().get(2).getNombre().equals("D")) {

            if (actual.getSalas().get(3).getCapacidad() < 100) {
                rndFinRecorridoD = randomObject.nextDouble();
                tRecorridoD = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaC(),
                        config.getHastaFinRecorridoSalaD(),
                        rndFinRecorridoD);
                finRecorridoD = tRecorridoD + horaActual;
                FinRecorridoSalaD newFinRecorridoD = new FinRecorridoSalaD(rndFinRecorridoD, tRecorridoD, finRecorridoD);

                visitanteTerminoDeRecorrerSalaA.setFinRecorridoD(newFinRecorridoD);
                visitanteTerminoDeRecorrerSalaA.setFinRecorridoA(new FinRecorridoSalaA(
                        visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getRnd1(),
                        visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getRnd2(),
                        visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().getSenocoseno()));

                visitanteTerminoDeRecorrerSalaA.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_D);
                visitanteTerminoDeRecorrerSalaA.setSala("D");
                actual.getSalas().get(3).setEstado(Sala.Estado.CON_VISITANTES);
                actual.getSalas().get(3).aumentarCapacidad();

                if (actual.getSalas().get(3).getCapacidad() == 100) {
                    actual.getSalas().get(3).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }
            } else {
                visitanteTerminoDeRecorrerSalaA.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_D);
                actual.getSalas().get(3).agregarVisitanteALaCola();
            }
        }

        actual.getSalas().get(1).disminuirCapacidad();

        if (actual.getSalas().get(1).getCola() > 0 && actual.getSalas().get(1).getCapacidad() < 40) {
            actual.getSalas().get(1).setEstado(Sala.Estado.CON_VISITANTES);
            Visitantes visitanteQueRecorreSalaA = new Visitantes();
            for (Visitantes visitante : actual.getVisitantes()) {
                if (visitante.getEstado().equals(Visitantes.Estado.ESPERANDO_RECORRIDO_A)) {
                    visitanteQueRecorreSalaA = visitante;
                    break;
                }
            }

            visitanteQueRecorreSalaA.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_A);
            if (actual.getSalas().get(1).getSenocosenoSalaA().equals("")) {
                actual.getSalas().get(1).setRnd1SalaA(randomObject.nextDouble());
                actual.getSalas().get(1).setRnd1SalaB(randomObject.nextDouble());

                visitanteQueRecorreSalaA.getFinRecorridoA().setRnd1(actual.getSalas().get(1).getRnd1SalaA());
                visitanteQueRecorreSalaA.getFinRecorridoA().setRnd2(actual.getSalas().get(1).getRnd2SalaA());

                visitanteQueRecorreSalaA.getFinRecorridoA().setSenocoseno(COS);
                tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                        config.getDesviacionFinRecorridoSalaA(),
                        actual.getSalas().get(1).getRnd1SalaA(),
                        actual.getSalas().get(1).getRnd2SalaA(),
                        visitanteQueRecorreSalaA.getFinRecorridoA().getSenocoseno());

                visitanteQueRecorreSalaA.getFinRecorridoA().settRecorrido(tRecorridoA);
                visitanteQueRecorreSalaA.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);
            } else {
                if (COS.equals(actual.getSalas().get(1).getSenocosenoSalaA())) {

                    visitanteQueRecorreSalaA.getFinRecorridoA().setSenocoseno(SENO);
                    actual.getSalas().get(1).setSenocosenoSalaA(SENO);

                    tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                            config.getDesviacionFinRecorridoSalaA(),
                            actual.getSalas().get(1).getRnd1SalaA(),
                            actual.getSalas().get(1).getRnd2SalaA(),
                            visitanteQueRecorreSalaA.getFinRecorridoA().getSenocoseno());

                    visitanteQueRecorreSalaA.getFinRecorridoA().settRecorrido(tRecorridoA);
                    visitanteQueRecorreSalaA.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);
                } else {

                    actual.getSalas().get(1).setRnd1SalaA(randomObject.nextDouble());
                    actual.getSalas().get(1).setRnd1SalaB(randomObject.nextDouble());

                    visitanteQueRecorreSalaA.getFinRecorridoA().setRnd1(actual.getSalas().get(1).getRnd1SalaA());
                    visitanteQueRecorreSalaA.getFinRecorridoA().setRnd2(actual.getSalas().get(1).getRnd2SalaA());

                    visitanteQueRecorreSalaA.getFinRecorridoA().setSenocoseno(COS);
                    tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                            config.getDesviacionFinRecorridoSalaA(),
                            actual.getSalas().get(1).getRnd1SalaA(),
                            actual.getSalas().get(1).getRnd2SalaA(),
                            visitanteQueRecorreSalaA.getFinRecorridoA().getSenocoseno());

                    visitanteQueRecorreSalaA.getFinRecorridoA().settRecorrido(tRecorridoA);
                    visitanteQueRecorreSalaA.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);
                }
            }

            visitanteQueRecorreSalaA.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_A);
            visitanteQueRecorreSalaA.setSala("A");

            // Disminuyo a la sala C(0) y aumento en la sala A(1)
            actual.getSalas().get(1).aumentarCapacidad();

            if (actual.getSalas().get(1).getCapacidad() == 40) {
                actual.getSalas().get(1).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
            }
        } else if (actual.getSalas().get(1).getCola() == 0 && actual.getSalas().get(1).getCapacidad() == 0) {
            actual.getSalas().get(1).setEstado(Sala.Estado.VACIA);
        }
    }
}
