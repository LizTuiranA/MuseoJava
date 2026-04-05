package museo.serviceimpl;

import java.util.List;

import museo.model.Usuario;
import museo.service.ServicioAutenticacion;

/**
 * Implementacion de autenticacion en memoria.
 */
public class ServicioAutenticacionImpl implements ServicioAutenticacion {
    @Override
    public Usuario autenticar(String username, String password, List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }
}
