package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinRecorridoSalaC {
    private double rnd;
    private double tRecorrido;

    public FinRecorridoSalaC() {
        rnd = tRecorrido = Double.MAX_VALUE;
    }
    
    public FinRecorridoSalaC(double rnd, double tRecorrido) {
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
    public FinRecorridoSalaC clone() {
        try {
            FinRecorridoSalaC clon = (FinRecorridoSalaC) super.clone();
            clon.rnd = Double.MAX_VALUE;
            clon.tRecorrido = Double.MAX_VALUE;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinRecorridoSalaA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FinRecorridoSalaC();
    }
}
