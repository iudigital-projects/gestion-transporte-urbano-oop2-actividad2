package edu.iudigital.transporte;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcesadorTransporte {

    //a) Cálculo de afluencia por estación
    //Usa funciones puras y no modifica la lista 'registros' original
    public static Map<String, Long> calcularAfluenciaPorEstacion(List<registro> registros) {
        return registros.stream()
                .filter(registro -> registro.accion() == accion.ENTRADA)
                .collect(Collectors.groupingBy(registro::estacion, Collectors.counting()));
    }
}
