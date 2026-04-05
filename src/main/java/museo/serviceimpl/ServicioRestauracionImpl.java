package museo.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import museo.enums.EstadoObra;
import museo.model.CatalogoObras;
import museo.model.ObraArte;
import museo.model.Restauracion;
import museo.service.ServicioRestauracion;

/**
 * Implementacion de las reglas de restauracion.
 */
public class ServicioRestauracionImpl implements ServicioRestauracion {
    @Override
    public boolean iniciarRestauracion(ObraArte obra, String tipo, String observaciones) {
        if (obra == null) {
            return false;
        }
        if (obra.getEstado() == EstadoObra.EN_RESTAURACION) {
            return false;
        }
        if (obra.getEstado() == EstadoObra.CEDIDA) {
            return false;
        }

        Restauracion restauracion = new Restauracion(tipo, LocalDate.now(), observaciones);
        obra.agregarRestauracion(restauracion);
        obra.cambiarEstado(EstadoObra.EN_RESTAURACION);
        return true;
    }

    @Override
    public boolean finalizarRestauracion(ObraArte obra) {
        if (obra == null || obra.getEstado() != EstadoObra.EN_RESTAURACION) {
            return false;
        }

        Restauracion activa = obra.obtenerRestauracionActiva();
        if (activa == null) {
            return false;
        }

        activa.finalizar(LocalDate.now());
        if (obra.obtenerCesionActiva() != null) {
            obra.cambiarEstado(EstadoObra.CEDIDA);
        } else {
            obra.cambiarEstado(EstadoObra.EXPUESTA);
        }
        return true;
    }

    @Override
    public List<Restauracion> obtenerHistorial(ObraArte obra) {
        List<Restauracion> historial = new ArrayList<>(obra.getRestauraciones());
        historial.sort(Comparator.comparing(Restauracion::getFechaInicio));
        return historial;
    }

    @Override
    public List<ObraArte> obrasPendientesPorRestauracion(CatalogoObras catalogo) {
        List<ObraArte> pendientes = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for (ObraArte obra : catalogo.listarObras()) {
            if (obra.getEstado() == EstadoObra.EN_RESTAURACION) {
                continue;
            }

            LocalDate referencia = obra.getFechaIngresoMuseo();
            List<Restauracion> historial = obra.getRestauraciones();
            if (!historial.isEmpty()) {
                Restauracion ultima = historial.get(historial.size() - 1);
                referencia = ultima.getFechaFin() != null ? ultima.getFechaFin() : ultima.getFechaInicio();
            }

            if (!referencia.plusYears(5).isAfter(hoy)) {
                pendientes.add(obra);
            }
        }
        return pendientes;
    }
}
