package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinRecorridoSalaB {
    private double rnd;
    private double tRecorrido;

    public FinRecorridoSalaB() {
        rnd = tRecorrido = Double.MAX_VALUE;
    }
    
    public FinRecorridoSalaB(double rnd, double tRecorrido) {
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
    public FinRecorridoSalaB clone() {
        try {
            FinRecorridoSalaB clon = (FinRecorridoSalaB) super.clone();
            clon.rnd = Double.MAX_VALUE;
            clon.tRecorrido = Double.MAX_VALUE;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinRecorridoSalaA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FinRecorridoSalaB();
    }
}
