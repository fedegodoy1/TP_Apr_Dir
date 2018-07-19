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

        double rndFinRecorridoB1 = 0.0;
        double rndFinRecorridoB2 = 0.0;
        double tRecorridoB = 0.0;

        double rndFinRecorridoA1 = 0.0;
        double rndFinRecorridoA2 = 0.0;
        double tRecorridoA = 0.0;

        double rndFinRecorridoD = 0.0;
        double tRecorridoD = 0.0;
        double finRecorridoD = 0.0;

        double horaActual = actual.getReloj();
        Visitantes visitanteTerminoDeRecorrerSalaA = null;
        for (Visitantes visitante : actual.getVisitantes()) {
            if (horaActual == visitante.getFinRecorridoC().getFinRecorrido()) {
                visitanteTerminoDeRecorrerSalaA = visitante;
                break;
            }
        }
        // Primero calculamos al visitante que viene a recorrer la sala A (1)
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
            if (COS.equals(visitanteQueRecorreSalaA.getFinRecorridoA().getSenocoseno())) {

                visitanteQueRecorreSalaA.getFinRecorridoA().setSenocoseno(SENO);
                tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                        config.getDesviacionFinRecorridoSalaA(),
                        visitanteQueRecorreSalaA.getFinRecorridoA().getRnd1(),
                        visitanteQueRecorreSalaA.getFinRecorridoA().getRnd2(),
                        visitanteQueRecorreSalaA.getFinRecorridoA().getSenocoseno());

                visitanteQueRecorreSalaA.getFinRecorridoA().settRecorrido(tRecorridoA);
                visitanteQueRecorreSalaA.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);
            } else {

                rndFinRecorridoA1 = randomObject.nextDouble();
                rndFinRecorridoA2 = randomObject.nextDouble();
                visitanteQueRecorreSalaA.getFinRecorridoA().setSenocoseno(COS);
                tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                        config.getDesviacionFinRecorridoSalaA(),
                        rndFinRecorridoA1,
                        rndFinRecorridoA2,
                        visitanteQueRecorreSalaA.getFinRecorridoA().getSenocoseno());

                visitanteQueRecorreSalaA.getFinRecorridoA().setRnd1(rndFinRecorridoA1);
                visitanteQueRecorreSalaA.getFinRecorridoA().setRnd2(rndFinRecorridoA2);
                visitanteQueRecorreSalaA.getFinRecorridoA().settRecorrido(tRecorridoA);
                visitanteQueRecorreSalaA.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);
            }

            // Disminuyo a la sala C(0) y aumento en la sala A(1)
            actual.getSalas().get(1).setCapacidad(actual.getSalas().get(0).getCapacidad() - 1);
            actual.getSalas().get(1).setCapacidad(anterior.getSalas().get(1).getCapacidad() + 1);

            if (actual.getSalas().get(1).getCapacidad() == 40) {
                actual.getSalas().get(1).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
            }
        } else if (actual.getSalas().get(1).getCola() == 0 && actual.getSalas().get(1).getCapacidad() == 0) {
            actual.getSalas().get(1).setEstado(Sala.Estado.VACIA);
        }

        if (visitanteTerminoDeRecorrerSalaA.getRecorrido().get(2).getNombre().equals("B")) {
            if (actual.getSalas().get(2).getCapacidad() < 40) {

                if (COS.equals(visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().getSenocoseno())) {

                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().setSenocoseno(SENO);
                    tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaB(),
                            config.getDesviacionFinRecorridoSalaB(),
                            visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().getRnd1(),
                            visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().getRnd2(),
                            visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().getSenocoseno());
                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().settRecorrido(tRecorridoB);
                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().setFinRecorrido(tRecorridoB + horaActual);
                    
                    visitanteTerminoDeRecorrerSalaA.setFinRecorridoA(new FinRecorridoSalaA());

                } else {

                    rndFinRecorridoB1 = randomObject.nextDouble();
                    rndFinRecorridoB2 = randomObject.nextDouble();

                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().setSenocoseno(COS);
                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().setRnd1(rndFinRecorridoB1);
                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoA().setRnd2(rndFinRecorridoB2);

                    tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaB(),
                            config.getDesviacionFinRecorridoSalaB(),
                            rndFinRecorridoB1,
                            rndFinRecorridoB2,
                            visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().getSenocoseno());

                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().settRecorrido(tRecorridoB);
                    visitanteTerminoDeRecorrerSalaA.getFinRecorridoB().setFinRecorrido(tRecorridoB + horaActual);
                    
                    visitanteTerminoDeRecorrerSalaA.setFinRecorridoA(new FinRecorridoSalaA());

                }

                visitanteTerminoDeRecorrerSalaA.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_B);

                actual.getSalas().get(2).setCapacidad(actual.getSalas().get(2).getCapacidad() + 1);

                if (actual.getSalas().get(2).getCapacidad() == 40) {
                    actual.getSalas().get(2).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }

            } else {
                visitanteTerminoDeRecorrerSalaA.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_B);
                actual.getSalas().get(2).agregarVisitanteALaCola();
            }
        } else if (visitanteTerminoDeRecorrerSalaA.getRecorrido().get(3).getNombre().equals("D")) {

            if (actual.getSalas().get(3).getCapacidad() < 100) {
                rndFinRecorridoD = randomObject.nextDouble();
                tRecorridoD = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaC(),
                        config.getHastaFinRecorridoSalaD(),
                        rndFinRecorridoD);
                finRecorridoD = tRecorridoD + horaActual;
                FinRecorridoSalaD newFinRecorridoD = new FinRecorridoSalaD(rndFinRecorridoD, tRecorridoD, finRecorridoD);

                visitanteTerminoDeRecorrerSalaA.setFinRecorridoD(newFinRecorridoD);
                visitanteTerminoDeRecorrerSalaA.setFinRecorridoA(new FinRecorridoSalaA());
                actual.getSalas().get(3).setCapacidad(anterior.getSalas().get(3).getCapacidad() + 1);

                if (actual.getSalas().get(3).getCapacidad() == 100) {
                    actual.getSalas().get(3).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }
            } else {
                visitanteTerminoDeRecorrerSalaA.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_D);
                actual.getSalas().get(3).agregarVisitanteALaCola();
            }
        }
        actual.getSalas().get(1).setCapacidad(actual.getSalas().get(1).getCapacidad() - 1);
    }
}
