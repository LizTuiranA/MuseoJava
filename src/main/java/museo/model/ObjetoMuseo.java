package museo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Especializacion de obra para otros objetos.
 */
public class ObjetoMuseo extends ObraArte {
    private final String tipoObjeto;

    public ObjetoMuseo(String id, String titulo, Autor autor, Periodo periodo, BigDecimal valor,
                       LocalDate fechaCreacion, LocalDate fechaIngresoMuseo, Sala sala,
                       String tipoObjeto) {
        super(id, titulo, autor, periodo, valor, fechaCreacion, fechaIngresoMuseo, sala);
        this.tipoObjeto = tipoObjeto;
    }

    @Override
    public String obtenerInformacion() {
        return "[Objeto] id=" + getId() + ", titulo=" + getTitulo() + ", autor=" + getAutor().getNombre()
                + ", sala=" + getSala().getNumero() + ", tipo=" + tipoObjeto
                + ", estado=" + getEstado() + ", valor=" + getValor();
    }
}
