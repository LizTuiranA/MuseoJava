package museo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Especializacion de obra para cuadros.
 */
public class Cuadro extends ObraArte {
    private final String estilo;
    private final String tecnica;

    public Cuadro(String id, String titulo, Autor autor, Periodo periodo, BigDecimal valor,
                  LocalDate fechaCreacion, LocalDate fechaIngresoMuseo, Sala sala,
                  String estilo, String tecnica) {
        super(id, titulo, autor, periodo, valor, fechaCreacion, fechaIngresoMuseo, sala);
        this.estilo = estilo;
        this.tecnica = tecnica;
    }

    @Override
    public String obtenerInformacion() {
        return "[Cuadro] id=" + getId() + ", titulo=" + getTitulo() + ", autor=" + getAutor().getNombre()
                + ", sala=" + getSala().getNumero() + ", estilo=" + estilo + ", tecnica=" + tecnica
                + ", estado=" + getEstado() + ", valor=" + getValor();
    }
}
