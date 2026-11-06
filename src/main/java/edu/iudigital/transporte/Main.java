package edu.iudigital.transporte;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Iniciando simulación TecnoMovil Data ---");

        // Simulación de datos inmutables con List.of
        List<registro> registros = List.of(
            new registro("U001", "Ruta A", "Estacion Norte", accion.ENTRADA, LocalDateTime.of(2023, 10, 24, 7, 15)),
            new registro("U001", "Ruta A", "Estacion Centro", accion.SALIDA, LocalDateTime.of(2023, 10, 24, 7, 45)),
            new registro("U002", "Ruta B", "Estacion Sur", accion.ENTRADA, LocalDateTime.of(2023, 10, 24, 8, 10)),
            new registro("U002", "Ruta B", "Estacion Centro", accion.SALIDA, LocalDateTime.of(2023, 10, 24, 8, 30)),
            new registro("U003", "Ruta A", "Estacion Norte", accion.ENTRADA, LocalDateTime.of(2023, 10, 24, 7, 20)),
            new registro("U003", "Ruta A", "Estacion Sur", accion.SALIDA, LocalDateTime.of(2023, 10, 24, 8, 5)),
            new registro("U001", "Ruta C", "Estacion Centro", accion.ENTRADA, LocalDateTime.of(2023, 10, 24, 17, 30)),
            new registro("U001", "Ruta C", "Estacion Este", accion.SALIDA, LocalDateTime.of(2023, 10, 24, 18, 10))
        );

        System.out.println("\n--- a) Afluencia por Estación ---");
        ProcesadorTransporte.calcularAfluenciaPorEstacion(registros)
            .forEach((estacion, cantidad) -> System.out.println(estacion + ": " + cantidad + " ingresos"));

        System.out.println("\n--- b) Horas Pico ---");
        ProcesadorTransporte.identificarHorasPico(registros)
            .forEach((hora, cantidad) -> System.out.println("Hora " + hora + ":00 - " + cantidad + " movimientos totales"));

        System.out.println("\n--- c) Rutas Más Utilizadas ---");
        ProcesadorTransporte.rutasMasUtilizadas(registros)
            .forEach((ruta, cantidad) -> System.out.println(ruta + ": " + cantidad + " ingresos"));

        System.out.println("\n--- d) Patrones de Viaje (Estaciones visitadas por usuario) ---");
        ProcesadorTransporte.patronesDeViajePorUsuario(registros)
            .forEach((usuario, viajes) -> System.out.println("Usuario " + usuario + ": " + viajes));

        System.out.println("\n--- e) Tiempo Promedio Entre Estaciones ---");
        double tiempoPromedio = ProcesadorTransporte.calcularTiempoPromedioEntreEstaciones(registros);
        System.out.println(String.format("%.2f minutos", tiempoPromedio));

        System.out.println("\n--- f) Detección de Sobrecarga en Rutas ---");
        long umbral = 1; // Usamos un umbral bajo de 1 para que el test muestre resultados
        List<String> rutasSobrecargadas = ProcesadorTransporte.detectarSobrecargaEnRutas(registros, umbral);
        System.out.println("Rutas que superan " + umbral + " ingresos: " + rutasSobrecargadas);
    }
}
