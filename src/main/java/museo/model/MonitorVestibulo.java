package museo.model;

import java.util.List;

import museo.service.ServicioConsulta;

/**
 * Punto de consulta para visitantes en el vestibulo.
 */
public class MonitorVestibulo {
    private final ServicioConsulta servicioConsulta;

    public MonitorVestibulo(ServicioConsulta servicioConsulta) {
        this.servicioConsulta = servicioConsulta;
    }

    public List<ObraArte> consultarObrasPorSala(CatalogoObras catalogo, int numeroSala) {
        return servicioConsulta.listarObrasPorSala(catalogo, numeroSala);
    }
}
