package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinRecorridoSalaD {
    private double rnd;
    private double tRecorrido;

    public FinRecorridoSalaD() {
        rnd = tRecorrido = Double.MAX_VALUE;
    }
    
    public FinRecorridoSalaD(double rnd, double tRecorrido) {
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
    public FinRecorridoSalaD clone() {
        try {
            FinRecorridoSalaD clon = (FinRecorridoSalaD) super.clone();
            clon.rnd = Double.MAX_VALUE;
            clon.tRecorrido = Double.MAX_VALUE;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinRecorridoSalaA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FinRecorridoSalaD();
    }
}
