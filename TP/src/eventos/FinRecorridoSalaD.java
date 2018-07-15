package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinRecorridoSalaD {
    private double rnd;
    private double tRecorrido;
    private double finRecorrido;

    public FinRecorridoSalaD() {
        rnd = tRecorrido = finRecorrido = Double.MAX_VALUE;
    }
    
    public FinRecorridoSalaD(double rnd, double tRecorrido, double finRecorrido) {
        this.rnd = rnd;
        this.tRecorrido = tRecorrido;
        this.finRecorrido = finRecorrido;
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

    public double getFinRecorrido() {
        return finRecorrido;
    }

    public void setFinRecorrido(double finRecorrido) {
        this.finRecorrido = finRecorrido;
    }
    
    @Override
    public FinRecorridoSalaD clone() {
        try {
            FinRecorridoSalaD clon = (FinRecorridoSalaD) super.clone();
            clon.rnd = Double.MAX_VALUE;
            clon.tRecorrido = Double.MAX_VALUE;
            clon.finRecorrido = Double.MAX_VALUE;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinRecorridoSalaA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FinRecorridoSalaD();
    }
}
