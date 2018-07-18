package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;
import static objects.Distribuciones.COS;
import static objects.Distribuciones.SENO;

public class FinRecorridoSalaB {
    private double rnd1;
    private double rnd2;
    private double tRecorrido;
    private double finRecorrido;
    private String senocoseno;

    public FinRecorridoSalaB() {
        rnd1 = rnd2 = tRecorrido = finRecorrido = Double.MAX_VALUE;
        senocoseno = COS;
    }
    
    public FinRecorridoSalaB(double rnd1, double rnd2, double tRecorrido, double finRecorrido) {
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

    public String getSenocoseno() {
        return senocoseno;
    }

    public void setSenocoseno(String senocoseno) {
        this.senocoseno = senocoseno;
    }
    
    @Override
    public FinRecorridoSalaB clone() {
        try {
            FinRecorridoSalaB clon = (FinRecorridoSalaB) super.clone();
            clon.finRecorrido = Double.MAX_VALUE;
            clon.rnd1 = this.rnd1;
            clon.rnd2 = this.rnd2;
            clon.tRecorrido = Double.MAX_VALUE;
            clon.senocoseno = this.senocoseno;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinRecorridoSalaA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FinRecorridoSalaB();
    }
}
