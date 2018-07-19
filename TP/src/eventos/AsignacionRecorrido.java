package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AsignacionRecorrido {
    private double rnd;
    private String salas;

    public AsignacionRecorrido(double rnd, String salas) {
        this.rnd = rnd;
        this.salas = salas;
    }

    public AsignacionRecorrido() {
        rnd = Double.MAX_VALUE;
        salas = "";
    }

    public double getRnd() {
        return rnd;
    }

    public void setRnd(double rnd) {
        this.rnd = rnd;
    }

    public String getSalas() {
        return salas;
    }

    public void setSalas(String salas) {
        this.salas = salas;
    }
    
    @Override
    public AsignacionRecorrido clone() {
        try {
            AsignacionRecorrido clon = (AsignacionRecorrido) super.clone();
            clon.rnd = Double.MAX_VALUE;
            clon.salas = "";
            
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinRecorridoSalaA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AsignacionRecorrido();
    }
}
