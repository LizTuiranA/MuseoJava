package museo.serviceimpl;

import java.math.BigDecimal;
import java.util.List;

import museo.model.CatalogoObras;
import museo.model.ObraArte;
import museo.service.ServicioConsulta;

/**
 * Implementacion de consultas del catalogo.
 */
public class ServicioConsultaImpl implements ServicioConsulta {
    @Override
    public List<ObraArte> listarObrasPorSala(CatalogoObras catalogo, int numeroSala) {
        return catalogo.listarObrasPorSala(numeroSala);
    }

    @Override
    public BigDecimal calcularValorTotal(CatalogoObras catalogo) {
        BigDecimal total = BigDecimal.ZERO;
        for (ObraArte obra : catalogo.listarObras()) {
            total = total.add(obra.getValor());
        }
        return total;
    }
}
