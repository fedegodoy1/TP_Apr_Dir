package objects;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Sala implements Cloneable{
    
    public enum Estado {
        VACIA,
        CON_VISITANTES,
        CAPACIDAD_MAXIMA
    }
    
    private Estado estado;
    private String nombre;
    private int capacidad;
    private int cola;
    
    public Sala() {
    }
    
    public Sala(Estado estado, String nombre, int capacidad, int cola) {
        this.estado = estado;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.cola = cola;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    public void agregarVisitanteALaCola() {
        cola ++;
    }
    
    public void disminuirCola() {
        cola --;
    }
    
    @Override
    public Sala clone() {
        try {
            Sala clon = (Sala) super.clone();
            clon.capacidad = this.capacidad;
            clon.cola = this.cola;
            clon.estado = this.estado;
            clon.nombre = this.nombre;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Sala();
    }
}
