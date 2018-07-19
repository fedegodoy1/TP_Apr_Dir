package objects;


public class Estadisticas {
    private Integer maxCantVisitantesEnCola;
    private Integer acumVisitantesEnSistema;

    public Estadisticas(Integer maxCantVisitantesEnCola, Integer acumVisitantesEnSistema) {
        this.maxCantVisitantesEnCola = maxCantVisitantesEnCola;
        this.acumVisitantesEnSistema = acumVisitantesEnSistema;
    }

    public Estadisticas() {
        maxCantVisitantesEnCola = 0;
        acumVisitantesEnSistema = 0;
    }

    public Integer getMaxCantVisitantesEnCola() {
        return maxCantVisitantesEnCola;
    }

    public void setMaxCantVisitantesEnCola(Integer maxCantVisitantesEnCola) {
        this.maxCantVisitantesEnCola = maxCantVisitantesEnCola;
    }

    public Integer getAcumVisitantesEnSistema() {
        return acumVisitantesEnSistema;
    }

    public void setAcumVisitantesEnSistema(Integer acumVisitantesEnSistema) {
        this.acumVisitantesEnSistema = acumVisitantesEnSistema;
    }
    
}
