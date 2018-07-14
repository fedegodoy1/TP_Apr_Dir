package control;

import control.eventos.Evento;
import eventos.LlegadaVisitantes;
import java.util.List;
import model.VectorEstadoUI;
import objects.Sala;

public class VectorEstado implements VectorEstadoUI {

    private double reloj;

    private Evento evento;

    private LlegadaVisitantes llegadaVisitantes;

    private List<Sala> salas;

    private int acumuladorVisitantes;

    private int maxVisitantesEnEntrada;

    @Override
    public double getReloj() {
        return reloj;
    }

    @Override
    public Evento getEvento() {
        return evento;
    }

    @Override
    public LlegadaVisitantes getLlegadaVisitantes() {
        return llegadaVisitantes;
    }

    @Override
    public List<Sala> getSalas() {
        return salas;
    }

    @Override
    public int getAcumuladorVisitantes() {
        return acumuladorVisitantes;
    }

    public void setReloj(double reloj) {
        this.reloj = reloj;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setLlegadaVisitantes(LlegadaVisitantes llegadaVisitantes) {
        this.llegadaVisitantes = llegadaVisitantes;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public void setAcumuladorVisitantes(int acumuladorVisitantes) {
        this.acumuladorVisitantes = acumuladorVisitantes;
    }

    public void setMaxVisitantesEnEntrada(int maxVisitantesEnEntrada) {
        this.maxVisitantesEnEntrada = maxVisitantesEnEntrada;
    }

    @Override
    public int getMaxVisitantesEnEntrada() {
        return maxVisitantesEnEntrada;
    }
}
