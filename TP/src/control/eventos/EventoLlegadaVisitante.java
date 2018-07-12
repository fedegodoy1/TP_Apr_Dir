package control.eventos;

public class EventoLlegadaVisitante extends Evento {
    
    public EventoLlegadaVisitante(String nombre) {
        super(nombre);
    }
    
    @Override
    public void actualizarEstadoVector() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
