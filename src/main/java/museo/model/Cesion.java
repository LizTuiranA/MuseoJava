package museo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import museo.enums.EstadoCesion;

/**
 * Registro de cesion de una obra a otro museo.
 */
public class Cesion {
    private final Museo museoDestino;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final BigDecimal importePagado;
    private EstadoCesion estado;

    public Cesion(Museo museoDestino, LocalDate fechaInicio, LocalDate fechaFin,
                  BigDecimal importePagado, EstadoCesion estado) {
        this.museoDestino = museoDestino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.importePagado = importePagado;
        this.estado = estado;
    }

    public Museo getMuseoDestino() {
        return museoDestino;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public BigDecimal getImportePagado() {
        return importePagado;
    }

    public EstadoCesion getEstado() {
        return estado;
    }

    public void activar() {
        this.estado = EstadoCesion.ACTIVA;
    }

    public void finalizar() {
        this.estado = EstadoCesion.FINALIZADA;
    }

    @Override
    public String toString() {
        return "destino=" + museoDestino + ", periodo=" + fechaInicio + " a " + fechaFin
                + ", importe=" + importePagado + ", estado=" + estado;
    }
}
