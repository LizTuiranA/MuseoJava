package museo.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import museo.model.Autor;
import museo.model.CatalogoObras;
import museo.model.Cuadro;
import museo.model.Escultura;
import museo.model.Museo;
import museo.model.ObjetoMuseo;
import museo.model.Periodo;
import museo.model.Rol;
import museo.model.Sala;
import museo.model.Usuario;

/**
 * Carga de datos en memoria para pruebas manuales.
 */
public class DatosIniciales {
    private final List<Autor> autores = new ArrayList<>();
    private final List<Periodo> periodos = new ArrayList<>();
    private final List<Sala> salas = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Museo> museos = new ArrayList<>();
    private final CatalogoObras catalogo = new CatalogoObras();

    public void cargar() {
        autores.add(new Autor("Pablo Picasso", "Espanola"));
        autores.add(new Autor("Fernando Botero", "Colombiana"));
        autores.add(new Autor("Anonimo Muisca", "Colombiana"));

        periodos.add(new Periodo("Cubismo", LocalDate.of(1907, 1, 1), LocalDate.of(1925, 12, 31)));
        periodos.add(new Periodo("Arte Moderno", LocalDate.of(1900, 1, 1), LocalDate.of(2000, 12, 31)));
        periodos.add(new Periodo("Precolombino", LocalDate.of(1000, 1, 1), LocalDate.of(1600, 12, 31)));

        salas.add(new Sala("Pintura", 1));
        salas.add(new Sala("Escultura", 2));
        salas.add(new Sala("Coleccion Historica", 3));

        usuarios.add(new Usuario("encargado_catalogo", "1234", new Rol("encargado_catalogo")));
        usuarios.add(new Usuario("restaurador_jefe", "1234", new Rol("restaurador_jefe")));
        usuarios.add(new Usuario("director_museo", "1234", new Rol("director_museo")));

        museos.add(new Museo("Museo de Antioquia", "Medellin", "Colombia"));
        museos.add(new Museo("Museo del Prado", "Madrid", "Espana"));

        catalogo.agregarObra(new Cuadro("C001", "Guernica", autores.get(0), periodos.get(0),
                new BigDecimal("125000000"), LocalDate.of(1937, 6, 1), LocalDate.of(2010, 2, 15),
                salas.get(0), "Cubismo", "Oleo"));
        catalogo.agregarObra(new Escultura("E001", "Caballo", autores.get(1), periodos.get(1),
                new BigDecimal("5000000"), LocalDate.of(1990, 3, 10), LocalDate.of(2014, 7, 20),
                salas.get(1), "Figurativo", "Bronce"));
        catalogo.agregarObra(new ObjetoMuseo("O001", "Tunjo ceremonial", autores.get(2), periodos.get(2),
                new BigDecimal("750000"), LocalDate.of(1500, 1, 1), LocalDate.of(2018, 10, 12),
                salas.get(2), "Objeto ritual"));
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Museo> getMuseos() {
        return museos;
    }

    public CatalogoObras getCatalogo() {
        return catalogo;
    }
}
