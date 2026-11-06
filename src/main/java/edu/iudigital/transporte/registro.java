package edu.iudigital.transporte;

import java.time.LocalDateTime;

public record registro(
    String idUsuario,
    String ruta,
    String estacion,
    accion accion,
    LocalDateTime timestamp
) {}
