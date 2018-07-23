package control.eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import objects.Sala;
import objects.Visitantes;

public abstract class Evento {
    public static final Evento Inicial = new EventoInicial("Inicial");
    public static final Evento LlegadaVisitante = new EventoLlegadaVisitante("Llegada Visitante");
    public static final Evento FinRecorridoSalaA = new EventoFinRecorridoSalaA("Fin Recorrido Sala A");
    public static final Evento FinRecorridoSalaB = new EventoFinRecorridoSalaB("Fin Recorrido Sala B");
    public static final Evento FinRecorridoSalaC = new EventoFinRecorridoSalaC("Fin Recorrido Sala C");
    public static final Evento FinRecorridoSalaD = new EventoFinRecorridoSalaD("Fin Recorrido Sala D");
            
    private double horaEvento;
    private String nombre;
    
    public Evento(String nombre)
    {
        this.nombre = nombre;
    }

    public double getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(double horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public abstract void actualizarEstadoVector();
    
    public static List<Visitantes> clonarVisitantes(List<Visitantes> listaAClonar) {
        return listaAClonar.stream().map(visitante -> new Visitantes(visitante)).collect(Collectors.toList());
    }
    
    public static List<Sala> clonarSalas(List<Sala> listaAClonar) {
        return listaAClonar.stream().map(sala -> new Sala(sala)).collect(Collectors.toList());
    }
}
