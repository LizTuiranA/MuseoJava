package museo.service;

import java.util.List;

import museo.model.Usuario;

/**
 * Contrato de autenticacion en memoria.
 */
public interface ServicioAutenticacion {
    Usuario autenticar(String username, String password, List<Usuario> usuarios);
}
