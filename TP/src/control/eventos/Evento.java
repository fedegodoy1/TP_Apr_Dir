package control.eventos;

public abstract class Evento {
    public static final Evento Inicial = new EventoInicial("Inicial");
    public static final Evento LlegadaVisitante = new EventoLlegadaVisitante("Llegada Visitante");
    public static final Evento FinRecorridoSalaA = new FinRecorridoSalaA("Fin Recorrido Sala A");
    public static final Evento FinRecorridoSalaB = new FinRecorridoSalaB("Fin Recorrido Sala B");
    public static final Evento FinRecorridoSalaC = new FinRecorridoSalaC("Fin Recorrido Sala C");
    public static final Evento FinRecorridoSalaD = new FinRecorridoSalaD("Fin Recorrido Sala D");
            
    private double horaEvento;
    private String nombre;
    
    public Evento(String nombre)
    {
        this.nombre = nombre;
    }
    
    public abstract void actualizarEstadoVector();
}
