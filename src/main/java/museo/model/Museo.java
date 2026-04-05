package museo.model;

/**
 * Museo colaborador para cesiones.
 */
public class Museo {
    private final String nombre;
    private final String ciudad;
    private final String pais;

    public Museo(String nombre, String ciudad, String pais) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return nombre + " - " + ciudad + ", " + pais;
    }
}
