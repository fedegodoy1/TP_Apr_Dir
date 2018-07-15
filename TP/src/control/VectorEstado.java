package control;

import control.eventos.Evento;
import eventos.AsignacionLote;
import eventos.LlegadaVisitantes;
import java.util.List;
import model.VectorEstadoUI;
import objects.Sala;
import objects.Visitantes;

public class VectorEstado implements VectorEstadoUI {

    private double reloj;

    private Evento evento;

    private LlegadaVisitantes llegadaVisitantes;

    private List<Sala> salas;
    
    private List<Visitantes> visitantes;
    
    private AsignacionLote lote;

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

    @Override
    public List<Visitantes> getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(List<Visitantes> visitantes) {
        this.visitantes = visitantes;
    }

    @Override
    public AsignacionLote getAsignacionLote() {
        return lote;
    }

    public void setLote(AsignacionLote lote) {
        this.lote = lote;
    }
}
