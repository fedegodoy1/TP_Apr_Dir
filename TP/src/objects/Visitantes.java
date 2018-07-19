package objects;

import eventos.AsignacionLote;
import eventos.AsignacionRecorrido;
import eventos.FinRecorridoSalaA;
import eventos.FinRecorridoSalaB;
import eventos.FinRecorridoSalaC;
import eventos.FinRecorridoSalaD;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Visitantes implements Cloneable {
    
    private String sala;
    
    public enum Estado {
        ESPERANDO_RECORRIDO_A,
        ESPERANDO_RECORRIDO_B,
        ESPERANDO_RECORRIDO_C,
        ESPERANDO_RECORRIDO_D,
        HACIENDO_RECORRIDO_A,
        HACIENDO_RECORRIDO_B,
        HACIENDO_RECORRIDO_C,
        HACIENDO_RECORRIDO_D
    }
    private Estado estado;
    private List<Sala> recorrido;
    private AsignacionRecorrido asignacion;
    private FinRecorridoSalaA finRecorridoA;
    private FinRecorridoSalaB finRecorridoB;
    private FinRecorridoSalaC finRecorridoC;
    private FinRecorridoSalaD finRecorridoD;

    public Visitantes() {
        asignacion = new AsignacionRecorrido();
        finRecorridoA = new FinRecorridoSalaA();
        finRecorridoB  = new FinRecorridoSalaB();
        finRecorridoC = new FinRecorridoSalaC();
        finRecorridoD = new FinRecorridoSalaD();
    }

    public Visitantes(String sala, Estado estado, List<Sala> recorrido, AsignacionRecorrido asignacion, 
            FinRecorridoSalaA finRecorridoA, FinRecorridoSalaB finRecorridoB, FinRecorridoSalaC finRecorridoC, FinRecorridoSalaD finRecorridoD) {
        this.sala = sala;
        this.estado = estado;
        this.recorrido = recorrido;
        this.asignacion = asignacion;
        this.finRecorridoA = finRecorridoA;
        this.finRecorridoB = finRecorridoB;
        this.finRecorridoC = finRecorridoC;
        this.finRecorridoD = finRecorridoD;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Sala> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(List<Sala> recorrido) {
        this.recorrido = recorrido;
    }

    public AsignacionRecorrido getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(AsignacionRecorrido asignacion) {
        this.asignacion = asignacion;
    }

    public FinRecorridoSalaA getFinRecorridoA() {
        return finRecorridoA;
    }

    public void setFinRecorridoA(FinRecorridoSalaA finRecorridoA) {
        this.finRecorridoA = finRecorridoA;
    }

    public FinRecorridoSalaB getFinRecorridoB() {
        return finRecorridoB;
    }

    public void setFinRecorridoB(FinRecorridoSalaB finRecorridoB) {
        this.finRecorridoB = finRecorridoB;
    }

    public FinRecorridoSalaC getFinRecorridoC() {
        return finRecorridoC;
    }

    public void setFinRecorridoC(FinRecorridoSalaC finRecorridoC) {
        this.finRecorridoC = finRecorridoC;
    }

    public FinRecorridoSalaD getFinRecorridoD() {
        return finRecorridoD;
    }

    public void setFinRecorridoD(FinRecorridoSalaD finRecorridoD) {
        this.finRecorridoD = finRecorridoD;
    }

    @Override
    public Visitantes clone() {
        try {
            Visitantes clon = (Visitantes) super.clone();
            clon.estado = this.estado;
            clon.recorrido = this.recorrido;
            clon.sala = this.sala;
            clon.asignacion = this.asignacion;
            clon.finRecorridoA = this.finRecorridoA;
            clon.finRecorridoB = this.finRecorridoB;
            clon.finRecorridoC = this.finRecorridoC;
            clon.finRecorridoD = this.finRecorridoD;
            return clon;
        } catch(CloneNotSupportedException ex) {
            Logger.getLogger(Visitantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Visitantes();
    }
}
