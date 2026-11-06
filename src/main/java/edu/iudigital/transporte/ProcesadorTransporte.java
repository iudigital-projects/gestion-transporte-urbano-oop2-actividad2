package edu.iudigital.transporte;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.Duration;
import java.util.Comparator;
import java.util.stream.IntStream;

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

    // d)Patrones de viaje por usuario
    // Generamos un listado de estaciones visitadas por cada usuario, conservando el orden de aparición.
    public static Map<String, List<String>> patronesDeViajePorUsuario(List<registro> registros) {
        return registros.stream()
                .collect(Collectors.groupingBy(
                        registro::idUsuario,
                        Collectors.mapping(registro::estacion, Collectors.toList())
                ));
    }

    // e)Cálculo de tiempo promedio entre estaciones (en minutos)
    // Usamos IntStream para calcular las diferencias de tiempo sin variables mutables (sin usar bucles for)
    public static double calcularTiempoPromedioEntreEstaciones(List<registro> registros) {
        return registros.stream()
                .collect(Collectors.groupingBy(registro::idUsuario)) // Agrupamos por usuario
                .values().stream()
                .flatMapToDouble(listaUsuario -> {
                    // Ordenamos los registros del usuario por tiempo
                    List<registro> ordenados = listaUsuario.stream()
                            .sorted(Comparator.comparing(registro::timestamp))
                            .toList();

                    // Comparamos el registro actual con el siguiente de forma funcional pura
                    return IntStream.range(0, ordenados.size() - 1)
                            .mapToDouble(i -> Duration.between(ordenados.get(i).timestamp(), ordenados.get(i + 1).timestamp()).toMinutes());
                })
                .average()
                .orElse(0.0);
    }
}
