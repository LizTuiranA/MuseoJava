package museo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Catalogo central de obras del museo.
 */
public class CatalogoObras {
    private final List<ObraArte> obras;

    public CatalogoObras() {
        this.obras = new ArrayList<>();
    }

    public void agregarObra(ObraArte obra) {
        this.obras.add(obra);
    }

    public List<ObraArte> listarObras() {
        return Collections.unmodifiableList(obras);
    }

    public ObraArte buscarObraPorId(String idObra) {
        for (ObraArte obra : obras) {
            if (obra.getId().equalsIgnoreCase(idObra)) {
                return obra;
            }
        }
        return null;
    }

    public List<ObraArte> listarObrasPorSala(int numeroSala) {
        List<ObraArte> resultado = new ArrayList<>();
        for (ObraArte obra : obras) {
            if (obra.getSala().getNumero() == numeroSala) {
                resultado.add(obra);
            }
        }
        return resultado;
    }
}
