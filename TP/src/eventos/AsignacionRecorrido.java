package eventos;

import java.util.List;
import objects.Sala;


public class AsignacionRecorrido {
    private float rnd;
    private List<Sala> recorrido;

    public AsignacionRecorrido(float rnd, List<Sala> recorrido) {
        this.rnd = rnd;
        this.recorrido = recorrido;
    }

    public float getRnd() {
        return rnd;
    }

    public void setRnd(float rnd) {
        this.rnd = rnd;
    }

    public List<Sala> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(List<Sala> recorrido) {
        this.recorrido = recorrido;
    }

}
