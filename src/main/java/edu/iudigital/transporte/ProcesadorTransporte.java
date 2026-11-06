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

    // b) Identificación de horas pico
    // Agrupa los registros por la hora del día para ver en qué momento hay más flujo
    public static Map<Integer, Long> identificarHorasPico(List<registro> registros) {
        return registros.stream()
                .collect(Collectors.groupingBy(
                        registro -> registro.timestamp().getHour(),
                        Collectors.counting()
                ));
    }

    // c)Rutas más utilizadas
    // Cuenta cuántos ingresos hay por cada ruta
    public static Map<String, Long> rutasMasUtilizadas(List<registro> registros) {
        return registros.stream()
                .filter(registro -> registro.accion() == accion.ENTRADA)
                .collect(Collectors.groupingBy(registro::ruta, Collectors.counting()));
    }
}
