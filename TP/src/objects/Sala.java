package objects;

public class Sala {
    
    private enum Estado {
        VACIA,
        CON_VISITANTES,
        CAPACIDAD_MAXIMA
    }
    
    private Estado estado;
    private int capacidad;
    private int cola;

    public Sala(Estado estado, int capacidad, int cola) {
        this.estado = estado;
        this.capacidad = capacidad;
        this.cola = cola;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCola() {
        return cola;
    }

    public void setCola(int cola) {
        this.cola = cola;
    }
    
}
