package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
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

        FinRecorridoSalaC newFinRecorridoSalaC = new FinRecorridoSalaC();
        double rndFinRecorridoC = 0.0;
        double tRecorridoC = 0.0;
        double finRecorridoC = 0.0;

        double rndFinRecorridoA1 = 0.0;
        double rndFinRecorridoA2 = 0.0;
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

            actual.getSalas().get(0).setCapacidad(anterior.getSalas().get(0).getCapacidad() + 1);
            actual.getSalas().get(0).disminuirCola();
            
            if (actual.getSalas().get(0).getCapacidad() == 100) {
                actual.getSalas().get(0).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
            }

        } else if (actual.getSalas().get(0).getCola() == 0 && actual.getSalas().get(0).getCapacidad() == 0) {
            actual.getSalas().get(0).setEstado(Sala.Estado.VACIA);
        }

        // Obtengo el recorrido para asignarlo a la proxima sala
        if (visitanteTerminoDeRecorrer.getRecorrido().get(1).getNombre().equals("A")) {
            //Si la capacidad de A es menor que 40
            if (actual.getSalas().get(1).getCapacidad() < 40) {

                if (COS.equals(visitanteTerminoDeRecorrer.getFinRecorridoA().getSenocoseno())) {

                    visitanteTerminoDeRecorrer.getFinRecorridoA().setSenocoseno(SENO);
                    tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                            config.getDesviacionFinRecorridoSalaA(),
                            visitanteTerminoDeRecorrer.getFinRecorridoA().getRnd1(),
                            visitanteTerminoDeRecorrer.getFinRecorridoA().getRnd2(),
                            visitanteTerminoDeRecorrer.getFinRecorridoA().getSenocoseno());

                    visitanteTerminoDeRecorrer.getFinRecorridoA().settRecorrido(tRecorridoA);
                    visitanteTerminoDeRecorrer.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);
                    visitanteTerminoDeRecorrer.setFinRecorridoC(new FinRecorridoSalaC());
                } else {

                    rndFinRecorridoA1 = randomObject.nextDouble();
                    rndFinRecorridoA2 = randomObject.nextDouble();
                    visitanteTerminoDeRecorrer.getFinRecorridoA().setSenocoseno(COS);
                    tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                            config.getDesviacionFinRecorridoSalaA(),
                            rndFinRecorridoA1,
                            rndFinRecorridoA2,
                            visitanteTerminoDeRecorrer.getFinRecorridoA().getSenocoseno());

                    visitanteTerminoDeRecorrer.getFinRecorridoA().setRnd1(rndFinRecorridoA1);
                    visitanteTerminoDeRecorrer.getFinRecorridoA().setRnd2(rndFinRecorridoA2);
                    visitanteTerminoDeRecorrer.getFinRecorridoA().settRecorrido(tRecorridoA);
                    visitanteTerminoDeRecorrer.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);
                    visitanteTerminoDeRecorrer.setFinRecorridoC(new FinRecorridoSalaC());
                }

                visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_A);

                actual.getSalas().get(1).setCapacidad(anterior.getSalas().get(1).getCapacidad() + 1);
                if (actual.getSalas().get(1).getCapacidad() == 40) {
                    actual.getSalas().get(1).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }

            } else {
                visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_A);
                actual.getSalas().get(1).agregarVisitanteALaCola();
            }
        } else if (visitanteTerminoDeRecorrer.getRecorrido().get(1).getNombre().equals("D")) {
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
                //aumento a la sala D (3)
                actual.getSalas().get(3).setCapacidad(anterior.getSalas().get(3).getCapacidad() + 1);

                if (actual.getSalas().get(3).getCapacidad() == 100) {
                    actual.getSalas().get(3).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }
            } else {
                visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_D);
                actual.getSalas().get(3).agregarVisitanteALaCola();
            }
        }
        //Ya sea que sumo uno mas al recorrido que sea siempre se le resta el que se va
        actual.getSalas().get(0).setCapacidad(anterior.getSalas().get(0).getCapacidad() - 1);

    }

}
