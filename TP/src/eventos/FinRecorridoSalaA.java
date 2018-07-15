package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinRecorridoSalaA implements Cloneable {
    private double rnd1;
    private double rnd2;
    private double tRecorrido;
    private double finRecorrido;

    public FinRecorridoSalaA() {
        rnd1 = rnd2 = tRecorrido = finRecorrido = Double.MAX_VALUE;
    }

    public FinRecorridoSalaA(double rnd1, double rnd2, double tRecorrido, double finRecorrido) {
        this.rnd1 = rnd1;
        this.rnd2 = rnd2;
        this.tRecorrido = tRecorrido;
        this.finRecorrido = finRecorrido;
    }

    public double getRnd1() {
        return rnd1;
    }

    public void setRnd1(double rnd1) {
        this.rnd1 = rnd1;
    }

    public double getRnd2() {
        return rnd2;
    }

    public void setRnd2(double rnd2) {
        this.rnd2 = rnd2;
    }

    public double getFinRecorrido() {
        return finRecorrido;
    }

    public void setFinRecorrido(double finRecorrido) {
        this.finRecorrido = finRecorrido;
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
            clon.finRecorrido = Double.MAX_VALUE;
            clon.rnd1 = Double.MAX_VALUE;
            clon.rnd2 = Double.MAX_VALUE;
            clon.tRecorrido = Double.MAX_VALUE;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinRecorridoSalaA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FinRecorridoSalaA();
    }
}
