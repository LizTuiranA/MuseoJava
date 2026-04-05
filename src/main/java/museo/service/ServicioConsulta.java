package museo.service;

import java.math.BigDecimal;
import java.util.List;

import museo.model.CatalogoObras;
import museo.model.ObraArte;

/**
 * Contrato para consultas del museo.
 */
public interface ServicioConsulta {
    List<ObraArte> listarObrasPorSala(CatalogoObras catalogo, int numeroSala);

    BigDecimal calcularValorTotal(CatalogoObras catalogo);
}
