package museo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import museo.model.Cesion;
import museo.model.Museo;
import museo.model.ObraArte;

/**
 * Contrato para operaciones de cesion.
 */
public interface ServicioCesion {
    Cesion registrarCesion(ObraArte obra, Museo museo, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal importe);

    Cesion solicitarCesion(ObraArte obra, Museo museo, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal importe);

    List<Cesion> obtenerCesiones(ObraArte obra);
}
