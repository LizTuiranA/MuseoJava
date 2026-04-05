package museo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import museo.enums.EstadoCesion;
import museo.enums.EstadoObra;

/**
 * Clase abstracta base para todas las obras.
 */
public abstract class ObraArte {
    private final String id;
    private final String titulo;
    private final Autor autor;
    private final Periodo periodo;
    private final BigDecimal valor;
    private final LocalDate fechaCreacion;
    private final LocalDate fechaIngresoMuseo;
    private EstadoObra estado;
    private final Sala sala;
    private final List<Restauracion> restauraciones;
    private final List<Cesion> cesiones;

    protected ObraArte(String id, String titulo, Autor autor, Periodo periodo, BigDecimal valor,
                       LocalDate fechaCreacion, LocalDate fechaIngresoMuseo, Sala sala) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.periodo = periodo;
        this.valor = valor;
        this.fechaCreacion = fechaCreacion;
        this.fechaIngresoMuseo = fechaIngresoMuseo;
        this.estado = EstadoObra.EXPUESTA;
        this.sala = sala;
        this.restauraciones = new ArrayList<>();
        this.cesiones = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaIngresoMuseo() {
        return fechaIngresoMuseo;
    }

    public EstadoObra getEstado() {
        return estado;
    }

    public Sala getSala() {
        return sala;
    }

    public List<Restauracion> getRestauraciones() {
        return Collections.unmodifiableList(restauraciones);
    }

    public List<Cesion> getCesiones() {
        return Collections.unmodifiableList(cesiones);
    }

    public void cambiarEstado(EstadoObra nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void agregarRestauracion(Restauracion restauracion) {
        this.restauraciones.add(restauracion);
    }

    public void agregarCesion(Cesion cesion) {
        this.cesiones.add(cesion);
    }

    public Restauracion obtenerRestauracionActiva() {
        for (Restauracion restauracion : restauraciones) {
            if (restauracion.estaActiva()) {
                return restauracion;
            }
        }
        return null;
    }

    public Cesion obtenerCesionActiva() {
        for (Cesion cesion : cesiones) {
            if (cesion.getEstado() == EstadoCesion.ACTIVA) {
                return cesion;
            }
        }
        return null;
    }

    public abstract String obtenerInformacion();
}
