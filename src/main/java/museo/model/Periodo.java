package museo.model;

import java.time.LocalDate;

/**
 * Periodo artistico o historico de una obra.
 */
public class Periodo {
    private final String nombre;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;

    public Periodo(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    @Override
    public String toString() {
        return nombre + " (" + fechaInicio + " a " + fechaFin + ")";
    }
}
