package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
import static control.eventos.Evento.FinRecorridoSalaA;
import eventos.FinRecorridoSalaA;
import eventos.FinRecorridoSalaC;
import eventos.FinRecorridoSalaD;
import java.util.Random;
import model.Configuracion;
import objects.Distribuciones;
import objects.Sala;
import objects.Visitantes;
import static objects.Distribuciones.COS;
import static objects.Distribuciones.SENO;

public class EventoFinRecorridoSalaC extends Evento {

    public EventoFinRecorridoSalaC(String nombre) {
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

        FinRecorridoSalaC newFinRecorridoSalaC = new FinRecorridoSalaC();
        double rndFinRecorridoC = 0.0;
        double tRecorridoC = 0.0;
        double finRecorridoC = 0.0;

        double tRecorridoA = 0.0;

        double rndFinRecorridoD = 0.0;
        double tRecorridoD = 0.0;
        double finRecorridoD = 0.0;

        double horaActual = actual.getReloj();
        Visitantes visitanteTerminoDeRecorrer = null;
        for (Visitantes visitante : actual.getVisitantes()) {
            if (horaActual == visitante.getFinRecorridoC().getFinRecorrido()) {
                visitanteTerminoDeRecorrer = visitante;
                break;
            }
        }

        if (visitanteTerminoDeRecorrer.getRecorrido().get(1).getNombre().equals("A")) {
            //Si la capacidad de A es menor que 40
            if (actual.getSalas().get(1).getCapacidad() < 40) {
                if (actual.getSalas().get(0).getSenocosenoSalaC().equals("")) {
                    FinRecorridoSalaA nuevoFinRecorridoA = new FinRecorridoSalaA();

                    actual.getSalas().get(0).setRnd1SalaC(new Random().nextDouble());
                    actual.getSalas().get(0).setRnd2SalaC(new Random().nextDouble());
                    actual.getSalas().get(0).setSenocosenoSalaC(COS);

                    nuevoFinRecorridoA.setRnd1(actual.getSalas().get(0).getRnd1SalaC());
                    nuevoFinRecorridoA.setRnd2(actual.getSalas().get(0).getRnd2SalaC());
                    nuevoFinRecorridoA.setSenocoseno(COS);

                    tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                            config.getDesviacionFinRecorridoSalaA(),
                            actual.getSalas().get(0).getRnd1SalaC(),
                            actual.getSalas().get(0).getRnd2SalaC(),
                            actual.getSalas().get(0).getSenocosenoSalaC());

                    nuevoFinRecorridoA.settRecorrido(tRecorridoA);
                    nuevoFinRecorridoA.setFinRecorrido(tRecorridoA + horaActual);
                    visitanteTerminoDeRecorrer.setFinRecorridoA(nuevoFinRecorridoA);
                    visitanteTerminoDeRecorrer.setFinRecorridoC(new FinRecorridoSalaC());
                } else {
                    if ("Coseno".equals(actual.getSalas().get(0).getSenocosenoSalaC())) {

                        FinRecorridoSalaA nuevoFinRecorridoA = new FinRecorridoSalaA();
                        actual.getSalas().get(0).setSenocosenoSalaC(SENO);
                        nuevoFinRecorridoA.setRnd1(actual.getSalas().get(0).getRnd1SalaC());
                        nuevoFinRecorridoA.setRnd2(actual.getSalas().get(0).getRnd2SalaC());
                        nuevoFinRecorridoA.setSenocoseno(SENO);

                        tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                                config.getDesviacionFinRecorridoSalaA(),
                                actual.getSalas().get(0).getRnd1SalaC(),
                                actual.getSalas().get(0).getRnd2SalaC(),
                                actual.getSalas().get(0).getSenocosenoSalaC());

                        nuevoFinRecorridoA.settRecorrido(tRecorridoA);
                        nuevoFinRecorridoA.setFinRecorrido(tRecorridoA + horaActual);
                        visitanteTerminoDeRecorrer.setFinRecorridoA(nuevoFinRecorridoA);
                        visitanteTerminoDeRecorrer.setFinRecorridoC(new FinRecorridoSalaC());

                    } else {
                        FinRecorridoSalaA nuevoFinRecorridoA = new FinRecorridoSalaA();

                        actual.getSalas().get(0).setRnd1SalaC(new Random().nextDouble());
                        actual.getSalas().get(0).setRnd2SalaC(new Random().nextDouble());
                        actual.getSalas().get(0).setSenocosenoSalaC(COS);

                        nuevoFinRecorridoA.setRnd1(actual.getSalas().get(0).getRnd1SalaC());
                        nuevoFinRecorridoA.setRnd2(actual.getSalas().get(0).getRnd2SalaC());
                        nuevoFinRecorridoA.setSenocoseno(COS);

                        tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                                config.getDesviacionFinRecorridoSalaA(),
                                actual.getSalas().get(0).getRnd1SalaC(),
                                actual.getSalas().get(0).getRnd2SalaC(),
                                actual.getSalas().get(0).getSenocosenoSalaC());

                        nuevoFinRecorridoA.settRecorrido(tRecorridoA);
                        nuevoFinRecorridoA.setFinRecorrido(tRecorridoA + horaActual);
                        visitanteTerminoDeRecorrer.setFinRecorridoA(nuevoFinRecorridoA);
                        visitanteTerminoDeRecorrer.setFinRecorridoC(new FinRecorridoSalaC());
                    }
                }

                visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_A);
                visitanteTerminoDeRecorrer.setSala("A");
                actual.getSalas().get(1).setEstado(Sala.Estado.CON_VISITANTES);
                actual.getSalas().get(1).aumentarCapacidad();
                if (actual.getSalas().get(1).getCapacidad() == 40) {
                    actual.getSalas().get(1).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }

            } else {
                visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_A);
                actual.getSalas().get(1).agregarVisitanteALaCola();
            }
        }
        if (visitanteTerminoDeRecorrer.getRecorrido().get(1).getNombre().equals("D")) {
            // Me fijo en la capacidad de D, que esta en la posicion 3 de la lista de las salas
            if (actual.getSalas().get(3).getCapacidad() < 100) {
                rndFinRecorridoD = randomObject.nextDouble();
                tRecorridoD = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaC(),
                        config.getHastaFinRecorridoSalaD(),
                        rndFinRecorridoD);
                finRecorridoD = tRecorridoD + horaActual;
                FinRecorridoSalaD newFinRecorridoD = new FinRecorridoSalaD(rndFinRecorridoD, tRecorridoD, finRecorridoD);

                visitanteTerminoDeRecorrer.setFinRecorridoC(new FinRecorridoSalaC());
                visitanteTerminoDeRecorrer.setFinRecorridoD(newFinRecorridoD);
                visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_D);
                visitanteTerminoDeRecorrer.setSala("D");
                //aumento a la sala D (3)
                actual.getSalas().get(3).aumentarCapacidad();
                actual.getSalas().get(3).setEstado(Sala.Estado.CON_VISITANTES);

                if (actual.getSalas().get(3).getCapacidad() == 100) {
                    actual.getSalas().get(3).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }
            } else {
                visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_D);
                actual.getSalas().get(3).agregarVisitanteALaCola();
            }
        }

        actual.getSalas().get(0).disminuirCapacidad();

        // Calculo el proximo recorrido en la sala C
        if (actual.getSalas().get(0).getCola() > 0 && actual.getSalas().get(0).getCapacidad() < 100) {
            actual.getSalas().get(0).setEstado(Sala.Estado.CON_VISITANTES);
            Visitantes visitanteARecorrerSalaC = new Visitantes();
            for (Visitantes visitante : actual.getVisitantes()) {
                if (visitante.getEstado().equals(Visitantes.Estado.ESPERANDO_RECORRIDO_C)) {
                    visitanteARecorrerSalaC = visitante;
                    break;
                }
            }

            visitanteARecorrerSalaC.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_C);

            rndFinRecorridoC = randomObject.nextDouble();
            tRecorridoC = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaC(),
                    config.getHastaFinRecorridoSalaC(),
                    rndFinRecorridoC);
            finRecorridoC = tRecorridoC + horaActual;

            newFinRecorridoSalaC.setRnd(rndFinRecorridoC);
            newFinRecorridoSalaC.settRecorrido(tRecorridoC);
            newFinRecorridoSalaC.setFinRecorrido(finRecorridoC);

            visitanteARecorrerSalaC.setFinRecorridoC(newFinRecorridoSalaC);

            actual.getSalas().get(0).aumentarCapacidad();
            actual.getSalas().get(0).disminuirCola();

            if (actual.getSalas().get(0).getCapacidad() == 100) {
                actual.getSalas().get(0).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
            }

        } else if (actual.getSalas().get(0).getCola() == 0 && actual.getSalas().get(0).getCapacidad() == 0) {
            actual.getSalas().get(0).setEstado(Sala.Estado.VACIA);
        }

    }

}
