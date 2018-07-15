package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AsignacionLote implements Cloneable {
    private double rnd;
    private int loteVisitantes;

    public AsignacionLote(double rnd, int loteVisitantes) {
        this.rnd = rnd;
        this.loteVisitantes = loteVisitantes;
    }
    
    public AsignacionLote() {
        rnd = Double.MAX_VALUE;
        loteVisitantes = Integer.MAX_VALUE;
    }

    public double getRnd() {
        return rnd;
    }

    public void setRnd(double rnd) {
        this.rnd = rnd;
    }

    public int getLoteVisitantes() {
        return loteVisitantes;
    }

    public void setLoteVisitantes(int loteVisitantes) {
        this.loteVisitantes = loteVisitantes;
    }

    @Override
    public AsignacionLote clone() {
        try {
            AsignacionLote clon = (AsignacionLote) super.clone();
            clon.rnd = Double.MAX_VALUE;
            clon.loteVisitantes = Integer.MAX_VALUE;
            return clon;
        } catch(CloneNotSupportedException ex) {
            Logger.getLogger(AsignacionLote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AsignacionLote();
    }
}
