package museo.model;

/**
 * Sala del museo donde se exhiben obras.
 */
public class Sala {
    private final String nombre;
    private final int numero;

    public Sala(String nombre, int numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Sala " + numero + " - " + nombre;
    }
}
