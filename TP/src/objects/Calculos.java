package objects;

import java.util.ArrayList;
import java.util.List;


public class Calculos {
    
    public static List<Sala> calcularAsignacionDeRecorrido(double rnd, List<Sala> salas) {
        List<Sala> recorridoCompleto = clonarSalas(salas);
        
        List<Sala> recorridoMedio = clonarSalas(salas);
        recorridoMedio.remove(2);
        
        List<Sala> recorridoCorto = clonarSalas(salas);
        recorridoCorto.remove(2);
        recorridoCorto.remove(1);
        
        if(rnd >= 0.0 && rnd <= 0.59) {
            return recorridoCorto;
        }
        if(rnd >= 0.60 && rnd <= 0.79) {
            return recorridoCompleto;
        }
        if(rnd >= 0.80 && rnd <= 0.99) {
            return recorridoMedio;
        } 
        return null;
    }
    
    private static List<Sala> clonarSalas(List<Sala> listaAClonar) {
        List<Sala> listaClonada = new ArrayList<>(listaAClonar.size());
        for(Sala sala : listaAClonar) {
            listaClonada.add(sala.clone());
        }
        return listaClonada;
    }
}
