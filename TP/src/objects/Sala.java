package objects;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sala implements Cloneable {

    public enum Estado {
        VACIA,
        CON_VISITANTES,
        CAPACIDAD_MAXIMA
    }

    private Estado estado;
    private String nombre;
    private int capacidad;
    private int cola;
    private String senocosenoSalaA;
    private double rnd1SalaA;
    private double rnd2SalaA;
    private String senocosenoSalaB;
    private double rnd1SalaB;
    private double rnd2SalaB;
    private String senocosenoSalaC;
    private double rnd1SalaC;
    private double rnd2SalaC;

    public Sala() {
    }
    
    public Sala(Sala sala) {
        this.estado = sala.estado;
        this.nombre = sala.nombre;
        this.capacidad = sala.capacidad;
        this.cola = sala.cola;
        senocosenoSalaA = sala.senocosenoSalaA; 
        senocosenoSalaB = sala.senocosenoSalaB; 
        senocosenoSalaC = sala.senocosenoSalaC;
        rnd1SalaA = sala.rnd1SalaA;
        rnd2SalaA = sala.rnd2SalaA;
        rnd1SalaB = sala.rnd1SalaB;
        rnd2SalaB = sala.rnd2SalaB; 
        rnd1SalaC = sala.rnd1SalaC;
        rnd2SalaC = sala.rnd2SalaC;
    }

    public Sala(Estado estado, String nombre, int capacidad, int cola) {
        this.estado = estado;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.cola = cola;
        senocosenoSalaA = senocosenoSalaB = senocosenoSalaC = "";
//        rnd1SalaA = new Random().nextDouble();
//        rnd2SalaA = new Random().nextDouble();
//        rnd1SalaB = new Random().nextDouble();
//        rnd2SalaB = new Random().nextDouble(); 
//        rnd1SalaC = new Random().nextDouble();
//        rnd2SalaC = new Random().nextDouble();
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
        cola++;
    }

    public void disminuirCola() {
        cola--;
    }
    
    public void aumentarCapacidad() {
        capacidad ++;
    }
    
    public void disminuirCapacidad() {
        capacidad --;
    }

    public String getSenocosenoSalaA() {
        return senocosenoSalaA;
    }

    public void setSenocosenoSalaA(String senocosenoSalaA) {
        this.senocosenoSalaA = senocosenoSalaA;
    }

    public double getRnd1SalaA() {
        return rnd1SalaA;
    }

    public void setRnd1SalaA(double rnd1SalaA) {
        this.rnd1SalaA = rnd1SalaA;
    }

    public double getRnd2SalaA() {
        return rnd2SalaA;
    }

    public void setRnd2SalaA(double rnd2SalaA) {
        this.rnd2SalaA = rnd2SalaA;
    }

    public String getSenocosenoSalaB() {
        return senocosenoSalaB;
    }

    public void setSenocosenoSalaB(String senocosenoSalaB) {
        this.senocosenoSalaB = senocosenoSalaB;
    }

    public double getRnd1SalaB() {
        return rnd1SalaB;
    }

    public void setRnd1SalaB(double rnd1SalaB) {
        this.rnd1SalaB = rnd1SalaB;
    }

    public double getRnd2SalaB() {
        return rnd2SalaB;
    }

    public void setRnd2SalaB(double rnd2SalaB) {
        this.rnd2SalaB = rnd2SalaB;
    }

    public String getSenocosenoSalaC() {
        return senocosenoSalaC;
    }

    public void setSenocosenoSalaC(String senocosenoSalaC) {
        this.senocosenoSalaC = senocosenoSalaC;
    }

    public double getRnd1SalaC() {
        return rnd1SalaC;
    }

    public void setRnd1SalaC(double rnd1SalaC) {
        this.rnd1SalaC = rnd1SalaC;
    }

    public double getRnd2SalaC() {
        return rnd2SalaC;
    }

    public void setRnd2SalaC(double rnd2SalaC) {
        this.rnd2SalaC = rnd2SalaC;
    }
    
    @Override
    public Sala clone() {
        try {
            Sala clon = (Sala) super.clone();
            clon.capacidad = this.capacidad;
            clon.cola = this.cola;
            clon.estado = this.estado;
            clon.nombre = this.nombre;
            clon.rnd1SalaA = this.rnd1SalaA;
            clon.rnd1SalaA = this.rnd1SalaA;
            clon.rnd1SalaB = this.rnd1SalaB;
            clon.rnd1SalaB = this.rnd1SalaB;
            clon.rnd1SalaC = this.rnd1SalaC;
            clon.rnd1SalaC = this.rnd1SalaC;
            clon.senocosenoSalaA = this.senocosenoSalaA;
            clon.senocosenoSalaB = this.senocosenoSalaB;
            clon.senocosenoSalaB = this.senocosenoSalaB;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Sala();
    }
}
