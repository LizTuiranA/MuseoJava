package museo.model;

import java.time.LocalDate;

/**
 * Registro de restauracion de una obra.
 */
public class Restauracion {
    private final String tipo;
    private final LocalDate fechaInicio;
    private LocalDate fechaFin;
    private final String observaciones;

    public Restauracion(String tipo, LocalDate fechaInicio, String observaciones) {
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.observaciones = observaciones;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public boolean estaActiva() {
        return fechaFin == null;
    }

    public void finalizar(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "tipo=" + tipo + ", inicio=" + fechaInicio + ", fin="
                + (fechaFin == null ? "ACTIVA" : fechaFin) + ", observaciones=" + observaciones;
    }
}
