package museo.service;

import java.util.List;

import museo.model.CatalogoObras;
import museo.model.ObraArte;
import museo.model.Restauracion;

/**
 * Contrato para operaciones de restauracion.
 */
public interface ServicioRestauracion {
    boolean iniciarRestauracion(ObraArte obra, String tipo, String observaciones);

    boolean finalizarRestauracion(ObraArte obra);

    List<Restauracion> obtenerHistorial(ObraArte obra);

    List<ObraArte> obrasPendientesPorRestauracion(CatalogoObras catalogo);
}
