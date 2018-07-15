package model;

import control.eventos.Evento;
import eventos.AsignacionLote;
import eventos.LlegadaVisitantes;
import java.util.List;
import objects.Sala;
import objects.Visitantes;

public interface VectorEstadoUI {

    double getReloj();

    Evento getEvento();

    LlegadaVisitantes getLlegadaVisitantes();

    AsignacionLote getAsignacionLote();
    
    List<Sala> getSalas();
    
    List<Visitantes> getVisitantes();

    int getAcumuladorVisitantes();

    int getMaxVisitantesEnEntrada();

}
