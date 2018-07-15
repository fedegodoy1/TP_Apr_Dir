package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinRecorridoSalaA implements Cloneable {
    private double rnd;
    private double tRecorrido;

    public FinRecorridoSalaA() {
        rnd = tRecorrido = Double.MAX_VALUE;
    }

    public FinRecorridoSalaA(double rnd, double tRecorrido) {
        this.rnd = rnd;
        this.tRecorrido = tRecorrido;
    }

    public double getRnd() {
        return rnd;
    }

    public void setRnd(double rnd) {
        this.rnd = rnd;
    }

    public double gettRecorrido() {
        return tRecorrido;
    }

    public void settRecorrido(double tRecorrido) {
        this.tRecorrido = tRecorrido;
    }

    
    @Override
    public FinRecorridoSalaA clone() {
        try {
            FinRecorridoSalaA clon = (FinRecorridoSalaA) super.clone();
            clon.rnd = Double.MAX_VALUE;
            clon.tRecorrido = Double.MAX_VALUE;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinRecorridoSalaA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FinRecorridoSalaA();
    }
}
