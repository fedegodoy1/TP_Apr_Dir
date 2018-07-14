package model;

import control.eventos.Evento;
import eventos.LlegadaVisitantes;
import java.util.List;
import objects.Sala;

public interface VectorEstadoUI {

    double getReloj();

    Evento getEvento();

    LlegadaVisitantes getLlegadaVisitantes();

    List<Sala> getSalas();

    int getAcumuladorVisitantes();

    int getMaxVisitantesEnEntrada();

}
