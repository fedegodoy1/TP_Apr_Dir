package objects;

import java.util.List;

public class Visitantes {
    
    private Sala sala;
    
    private enum Estado {
        ESPERANDO_RECORRIDO_A,
        ESPERANDO_RECORRIDO_B,
        ESPERANDO_RECORRIDO_C,
        ESPERANDO_RECORRIDO_D,
        HACIENDO_RECORRIDO_A,
        HACIENDO_RECORRIDO_B,
        HACIENDO_RECORRIDO_C,
        HACIENDO_RECORRIDO_D
    }
    private Estado estado;
    private List<Sala> recorrido;
    private double fin_recorrido;

    public Visitantes() {
    }

    public Visitantes(Sala sala, Estado estado, List<Sala> recorrido, double fin_recorrido) {
        this.sala = sala;
        this.estado = estado;
        this.recorrido = recorrido;
        this.fin_recorrido = fin_recorrido;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Sala> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(List<Sala> recorrido) {
        this.recorrido = recorrido;
    }

    public double getFin_recorrido() {
        return fin_recorrido;
    }

    public void setFin_recorrido(double fin_recorrido) {
        this.fin_recorrido = fin_recorrido;
    }
    
}
