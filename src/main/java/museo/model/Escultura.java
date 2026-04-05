package museo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Especializacion de obra para esculturas.
 */
public class Escultura extends ObraArte {
    private final String estilo;
    private final String material;

    public Escultura(String id, String titulo, Autor autor, Periodo periodo, java.math.BigDecimal valor,
                     LocalDate fechaCreacion, LocalDate fechaIngresoMuseo, Sala sala,
                     String estilo, String material) {
        super(id, titulo, autor, periodo, valor, fechaCreacion, fechaIngresoMuseo, sala);
        this.estilo = estilo;
        this.material = material;
    }

    @Override
    public String obtenerInformacion() {
        return "[Escultura] id=" + getId() + ", titulo=" + getTitulo() + ", autor=" + getAutor().getNombre()
                + ", sala=" + getSala().getNumero() + ", estilo=" + estilo + ", material=" + material
                + ", estado=" + getEstado() + ", valor=" + getValor();
    }
}
