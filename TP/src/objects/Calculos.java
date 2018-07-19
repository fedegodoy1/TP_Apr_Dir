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
        
        if(rnd < (float) 0.60) {
            return recorridoCorto;
        }
        if(rnd < (float) 0.80) {
            return recorridoCompleto;
        }
        if(rnd < (float) 1.0) {
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
