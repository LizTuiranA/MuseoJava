package museo.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import museo.enums.EstadoCesion;
import museo.enums.EstadoObra;
import museo.model.Cesion;
import museo.model.Museo;
import museo.model.ObraArte;
import museo.service.ServicioCesion;

/**
 * Implementacion de las reglas de cesion.
 */
public class ServicioCesionImpl implements ServicioCesion {
    @Override
    public Cesion registrarCesion(ObraArte obra, Museo museo, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal importe) {
        if (obra == null || museo == null) {
            return null;
        }
        if (obra.getEstado() == EstadoObra.EN_RESTAURACION) {
            return null;
        }

        boolean tieneActiva = obra.obtenerCesionActiva() != null;
        EstadoCesion estadoInicial = tieneActiva ? EstadoCesion.PROGRAMADA : EstadoCesion.ACTIVA;
        Cesion cesion = new Cesion(museo, fechaInicio, fechaFin, importe, estadoInicial);
        obra.agregarCesion(cesion);

        if (estadoInicial == EstadoCesion.ACTIVA) {
            obra.cambiarEstado(EstadoObra.CEDIDA);
        }
        return cesion;
    }

    @Override
    public Cesion solicitarCesion(ObraArte obra, Museo museo, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal importe) {
        return registrarCesion(obra, museo, fechaInicio, fechaFin, importe);
    }

    @Override
    public List<Cesion> obtenerCesiones(ObraArte obra) {
        return new ArrayList<>(obra.getCesiones());
    }
}
