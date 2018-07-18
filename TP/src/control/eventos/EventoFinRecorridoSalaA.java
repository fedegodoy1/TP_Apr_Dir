package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
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

        FinRecorridoSalaB finRecorridoSalaB = new FinRecorridoSalaB();
        double rndFinRecorridoB1 = 0.0;
        double rndFinRecorridoB2 = 0.0;
        double tRecorridoB = 0.0;
        double finRecorridoB = 0.0;

        double rndFinRecorridoA1 = 0.0;
        double rndFinRecorridoA2 = 0.0;
        double tRecorridoA = 0.0;

        FinRecorridoSalaD finRecorridoSalaD = new FinRecorridoSalaD();
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

        if (actual.getSalas().get(1).getCola() > 0 && actual.getSalas().get(1).getCapacidad() < 40) {
            actual.getSalas().get(1).setEstado(Sala.Estado.CON_VISITANTES);
            Visitantes visitanteARecorrerSalaA = new Visitantes();
            for (Visitantes visitante : actual.getVisitantes()) {
                if (visitante.getEstado().equals(Visitantes.Estado.ESPERANDO_RECORRIDO_A)) {
                    visitanteARecorrerSalaA = visitante;
                    break;
                }
            }

            visitanteARecorrerSalaA.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_A);
            if (COS.equals(visitanteARecorrerSalaA.getFinRecorridoA().getSenocoseno())) {

                visitanteARecorrerSalaA.getFinRecorridoA().setSenocoseno(SENO);
                tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                        config.getDesviacionFinRecorridoSalaA(),
                        visitanteARecorrerSalaA.getFinRecorridoA().getRnd1(),
                        visitanteARecorrerSalaA.getFinRecorridoA().getRnd2(),
                        visitanteARecorrerSalaA.getFinRecorridoA().getSenocoseno());

                visitanteARecorrerSalaA.getFinRecorridoA().settRecorrido(tRecorridoA);
                visitanteARecorrerSalaA.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);
            } else {

                rndFinRecorridoA1 = randomObject.nextDouble();
                rndFinRecorridoA2 = randomObject.nextDouble();
                visitanteARecorrerSalaA.getFinRecorridoA().setSenocoseno(COS);
                tRecorridoA = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaA(),
                        config.getDesviacionFinRecorridoSalaA(),
                        rndFinRecorridoA1,
                        rndFinRecorridoA2,
                        visitanteARecorrerSalaA.getFinRecorridoA().getSenocoseno());

                visitanteARecorrerSalaA.getFinRecorridoA().setRnd1(rndFinRecorridoA1);
                visitanteARecorrerSalaA.getFinRecorridoA().setRnd2(rndFinRecorridoA2);
                visitanteARecorrerSalaA.getFinRecorridoA().settRecorrido(tRecorridoA);
                visitanteARecorrerSalaA.getFinRecorridoA().setFinRecorrido(tRecorridoA + horaActual);

                if (actual.getSalas().get(1).getCola() == 100) {
                    actual.getSalas().get(1).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }
            }
        } else if (actual.getSalas().get(1).getCola() == 0) {
            actual.getSalas().get(1).setEstado(Sala.Estado.VACIA);
        }

        if (visitanteTerminoDeRecorrer.getRecorrido().get(2).getNombre().equals("B")) {
            if (anterior.getSalas().get(2).getCapacidad() < 40) {

                if (COS.equals(visitanteTerminoDeRecorrer.getFinRecorridoB().getSenocoseno())) {
                    visitanteTerminoDeRecorrer.getFinRecorridoB().setSenocoseno(SENO);
                    tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaB(), 
                            config.getDesviacionFinRecorridoSalaB(), 
                            visitanteTerminoDeRecorrer.getFinRecorridoB().getRnd1(), 
                            visitanteTerminoDeRecorrer.getFinRecorridoB().getRnd2(), 
                            visitanteTerminoDeRecorrer.getFinRecorridoB().getSenocoseno());
                    visitanteTerminoDeRecorrer.getFinRecorridoB().settRecorrido(tRecorridoB);
                    visitanteTerminoDeRecorrer.getFinRecorridoB().setFinRecorrido(tRecorridoB + horaActual);
                    
                } else {
                    
                    rndFinRecorridoB1 = randomObject.nextDouble();
                    rndFinRecorridoB2 = randomObject.nextDouble();
                    
                    visitanteTerminoDeRecorrer.getFinRecorridoA().setSenocoseno(COS);
                    visitanteTerminoDeRecorrer.getFinRecorridoA().setRnd1(rndFinRecorridoB1);
                    visitanteTerminoDeRecorrer.getFinRecorridoA().setRnd2(rndFinRecorridoB2);
                    
                    tRecorridoB = Distribuciones.calcular_normal(config.getMediaFinRecorridoSalaB(), 
                            config.getDesviacionFinRecorridoSalaB(), 
                            rndFinRecorridoB1, 
                            rndFinRecorridoB2, 
                            visitanteTerminoDeRecorrer.getFinRecorridoB().getSenocoseno());
                    
                    visitanteTerminoDeRecorrer.getFinRecorridoB().settRecorrido(tRecorridoB);
                    visitanteTerminoDeRecorrer.getFinRecorridoB().setFinRecorrido(tRecorridoB + horaActual);
                    
                }
                
                visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_B);
                actual.getSalas().get(2).setCapacidad(actual.getSalas().get(2).getCapacidad() + 1);
                
                if(actual.getSalas().get(2).getCapacidad()==40) {
                    actual.getSalas().get(2).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                }
                
            } else if (visitanteTerminoDeRecorrer.getRecorrido().get(2).getNombre().equals("D")) {

                if (anterior.getSalas().get(2).getCapacidad() < 100) {
                    rndFinRecorridoD = randomObject.nextDouble();
                    tRecorridoD = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaC(),
                            config.getHastaFinRecorridoSalaD(),
                            rndFinRecorridoD);
                    finRecorridoD = tRecorridoD + horaActual;
                    FinRecorridoSalaD newFinRecorridoD = new FinRecorridoSalaD(rndFinRecorridoD, tRecorridoD, finRecorridoD);

                    visitanteTerminoDeRecorrer.setFinRecorridoD(newFinRecorridoD);
                    actual.getSalas().get(2).setCapacidad(anterior.getSalas().get(2).getCapacidad() + 1);

                    if (actual.getSalas().get(2).getCapacidad() == 100) {
                        actual.getSalas().get(2).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
                    }
                } else {
                    visitanteTerminoDeRecorrer.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_D);
                    actual.getSalas().get(2).agregarVisitanteALaCola();
                }
            }

        }
    }
}
