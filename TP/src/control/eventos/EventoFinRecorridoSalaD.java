package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
import eventos.FinRecorridoSalaD;
import java.util.List;
import java.util.Random;
import model.Configuracion;
import objects.Distribuciones;
import objects.Sala;
import objects.Visitantes;

public class EventoFinRecorridoSalaD extends Evento {

    public EventoFinRecorridoSalaD(String nombre) {
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

        double rndFinRecorridoD = 0.0;
        double tRecorridoD = 0.0;
        double finRecorridoD = 0.0;

        double horaActual = actual.getReloj();
        Visitantes visitanteTerminoDeRecorrerSalaD = null;
        for (Visitantes visitante : actual.getVisitantes()) {
            if (horaActual == visitante.getFinRecorridoD().getFinRecorrido()) {
                visitanteTerminoDeRecorrerSalaD = visitante;
                break;
            }
        }

        List<Visitantes> visitantesActuales = clonarVisitantes(anterior.getVisitantes());
        visitantesActuales.remove(visitanteTerminoDeRecorrerSalaD);
        visitanteTerminoDeRecorrerSalaD = null;
        actual.setVisitantes(visitantesActuales);
        actual.getSalas().get(3).setCapacidad(actual.getSalas().get(3).getCapacidad() - 1);

        if (actual.getSalas().get(3).getCola() > 0 && actual.getSalas().get(3).getCapacidad() < 100) {
            actual.getSalas().get(3).setEstado(Sala.Estado.CON_VISITANTES);
            Visitantes visitanteQueRecorreSalaD = new Visitantes();

            for (Visitantes visitante : actual.getVisitantes()) {
                if (visitante.getEstado().equals(Visitantes.Estado.ESPERANDO_RECORRIDO_D)) {
                    visitanteQueRecorreSalaD = visitante;
                    break;
                }
            }

            visitanteQueRecorreSalaD.setEstado(Visitantes.Estado.HACIENDO_RECORRIDO_D);

            rndFinRecorridoD = randomObject.nextDouble();
            tRecorridoD = Distribuciones.calcular_uniforme(config.getDesdeFinRecorridoSalaC(),
                    config.getHastaFinRecorridoSalaD(),
                    rndFinRecorridoD);
            finRecorridoD = tRecorridoD + horaActual;
            FinRecorridoSalaD newFinRecorridoD = new FinRecorridoSalaD(rndFinRecorridoD, tRecorridoD, finRecorridoD);
            visitanteTerminoDeRecorrerSalaD.setFinRecorridoD(newFinRecorridoD);
            actual.getSalas().get(3).setCapacidad(actual.getSalas().get(3).getCapacidad() + 1);
            if (actual.getSalas().get(3).getCapacidad() == 100) {
                actual.getSalas().get(3).setEstado(Sala.Estado.CAPACIDAD_MAXIMA);
            }
        } else {
            visitanteTerminoDeRecorrerSalaD.setEstado(Visitantes.Estado.ESPERANDO_RECORRIDO_D);
            actual.getSalas().get(3).agregarVisitanteALaCola();
        }
    }

}
