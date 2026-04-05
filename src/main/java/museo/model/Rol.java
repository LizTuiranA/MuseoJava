package museo.model;

/**
 * Rol basico de usuario interno.
 */
public class Rol {
    private final String nombre;

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
