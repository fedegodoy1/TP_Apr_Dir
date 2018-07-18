package objects;

import java.util.ArrayList;
import java.util.List;


public class Calculos {
    
    public static List<String> calcularAsignacionDeRecorrido(double rnd) {
        List<String> recorridoCompleto = new ArrayList();
        recorridoCompleto.add("C");
        recorridoCompleto.add("A");
        recorridoCompleto.add("B");
        recorridoCompleto.add("D");
        
        List<String> recorridoMedio = new ArrayList();
        recorridoMedio.add("C");
        recorridoMedio.add("A");
        recorridoMedio.add("D");
        
        List<String> recorridoCorto = new ArrayList();
        recorridoCorto.add("C");
        recorridoCorto.add("D");
        
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
    
    public static List<Sala> setearRecorrido() {
        
    }
}
