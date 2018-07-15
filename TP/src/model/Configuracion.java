package model;


public class Configuracion {
    private static Configuracion instancia;
    
    private double mediaLlegadaVisitantes;
    private double mediaFinRecorridoSalaA;    
    private double desviacionFinRecorridoSalaA;    
    private double mediaFinRecorridoSalaB;    
    private double desviacionFinRecorridoSalaB;    
    private double desdeFinRecorridoSalaC;    
    private double hastaFinRecorridoSalaC;    
    private double desdeFinRecorridoSalaD;    
    private double hastaFinRecorridoSalaD;    
    private double mediaLote;
    
    private int minutosASimular;
    private int minutoDesde;
    private int iteracionesAMostrar;
    
    private Configuracion() {
        this.mediaLlegadaVisitantes = 5;
        this.mediaFinRecorridoSalaA = 30;
        this.desviacionFinRecorridoSalaA = 5;
        this.mediaFinRecorridoSalaB = 25;
        this.desviacionFinRecorridoSalaB = 4;
        this.desdeFinRecorridoSalaC = 3;
        this.hastaFinRecorridoSalaC = 15;
        this.desdeFinRecorridoSalaD = 2;
        this.hastaFinRecorridoSalaD = 16;
        this.mediaLote = 3;
        ///////////////////
        this.minutosASimular = 60000;
        this.minutoDesde = 0;
        this.iteracionesAMostrar = 1000000;
    }

    public static Configuracion getInstancia() {
        return instancia;
    }

    public static void setInstancia(Configuracion instancia) {
        Configuracion.instancia = instancia;
    }

    public double getMediaLlegadaVisitantes() {
        return mediaLlegadaVisitantes;
    }

    public void setMediaLlegadaVisitantes(double mediaLlegadaVisitantes) {
        this.mediaLlegadaVisitantes = mediaLlegadaVisitantes;
    }

    public double getMediaFinRecorridoSalaA() {
        return mediaFinRecorridoSalaA;
    }

    public void setMediaFinRecorridoSalaA(double mediaFinRecorridoSalaA) {
        this.mediaFinRecorridoSalaA = mediaFinRecorridoSalaA;
    }

    public double getDesviacionFinRecorridoSalaA() {
        return desviacionFinRecorridoSalaA;
    }

    public void setDesviacionFinRecorridoSalaA(double desviacionFinRecorridoSalaA) {
        this.desviacionFinRecorridoSalaA = desviacionFinRecorridoSalaA;
    }

    public double getMediaFinRecorridoSalaB() {
        return mediaFinRecorridoSalaB;
    }

    public void setMediaFinRecorridoSalaB(double mediaFinRecorridoSalaB) {
        this.mediaFinRecorridoSalaB = mediaFinRecorridoSalaB;
    }

    public double getDesviacionFinRecorridoSalaB() {
        return desviacionFinRecorridoSalaB;
    }

    public void setDesviacionFinRecorridoSalaB(double desviacionFinRecorridoSalaB) {
        this.desviacionFinRecorridoSalaB = desviacionFinRecorridoSalaB;
    }

    public double getDesdeFinRecorridoSalaC() {
        return desdeFinRecorridoSalaC;
    }

    public void setDesdeFinRecorridoSalaC(double desdeFinRecorridoSalaC) {
        this.desdeFinRecorridoSalaC = desdeFinRecorridoSalaC;
    }

    public double getHastaFinRecorridoSalaC() {
        return hastaFinRecorridoSalaC;
    }

    public void setHastaFinRecorridoSalaC(double hastaFinRecorridoSalaC) {
        this.hastaFinRecorridoSalaC = hastaFinRecorridoSalaC;
    }

    public double getDesdeFinRecorridoSalaD() {
        return desdeFinRecorridoSalaD;
    }

    public void setDesdeFinRecorridoSalaD(double desdeFinRecorridoSalaD) {
        this.desdeFinRecorridoSalaD = desdeFinRecorridoSalaD;
    }

    public double getHastaFinRecorridoSalaD() {
        return hastaFinRecorridoSalaD;
    }

    public void setHastaFinRecorridoSalaD(double hastaFinRecorridoSalaD) {
        this.hastaFinRecorridoSalaD = hastaFinRecorridoSalaD;
    }

    public int getMinutosASimular() {
        return minutosASimular;
    }

    public void setMinutosASimular(int minutosASimular) {
        this.minutosASimular = minutosASimular;
    }

    public int getMinutoDesde() {
        return minutoDesde;
    }

    public void setMinutoDesde(int minutoDesde) {
        this.minutoDesde = minutoDesde;
    }

    public int getIteracionesAMostrar() {
        return iteracionesAMostrar;
    }

    public void setIteracionesAMostrar(int iteracionesAMostrar) {
        this.iteracionesAMostrar = iteracionesAMostrar;
    }

    public double getMediaLote() {
        return mediaLote;
    }

    public void setMediaLote(double mediaLote) {
        this.mediaLote = mediaLote;
    }
    
    public static Configuracion getConfiguracion() {
        if(instancia==null) {
            instancia = new Configuracion();
        }
        return instancia;
    }
    
    public static Configuracion getConfiguracionPorDefecto() {
        instancia = new Configuracion();
        return instancia;
    }
}
