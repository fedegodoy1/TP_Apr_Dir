package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
import java.util.ArrayList;
import java.util.List;
import eventos.LlegadaVisitantes;
import java.util.Random;
import model.Configuracion;
import objects.Distribuciones;
import objects.Sala;
import objects.Visitantes;

public class EventoInicial extends Evento {

    public EventoInicial(String nombre) {
        super(nombre);
    }

    @Override
    public void actualizarEstadoVector() {
        VectorEstado actual = ControladorSimulacion.getVectorActual();
        VectorEstado anterior = ControladorSimulacion.getVectorAnterior();

        actual.setAcumuladorVisitantes(0);
        actual.setMaxVisitantesEnEntrada(0);
        actual.setSalas(getSalaList());
        actual.setVisitantes(new ArrayList<Visitantes>());
        
        //Seteo de la proxima llegada
        LlegadaVisitantes proxLlegada = new LlegadaVisitantes();
        proxLlegada.setRnd(new Random().nextDouble());
        double tiempoEntreLlegada = Distribuciones.calcular_exponencial(
                Configuracion.getConfiguracion().getMediaLlegadaVisitantes(), 
                proxLlegada.getRnd());
        proxLlegada.setTiempo_entre_llegadas(tiempoEntreLlegada);
        proxLlegada.setProx_llegada(tiempoEntreLlegada + actual.getReloj());
        actual.setLlegadaVisitantes(proxLlegada);
    }

    private List<Sala> getSalaList() {
        List<Sala> salas = new ArrayList();
        salas.add(new Sala(Sala.Estado.VACIA, "C", 0, 0));
        salas.add(new Sala(Sala.Estado.VACIA, "A", 0, 0));
        salas.add(new Sala(Sala.Estado.VACIA, "B", 0, 0));
        salas.add(new Sala(Sala.Estado.VACIA, "D", 0, 0));
        return salas;
    }
}
