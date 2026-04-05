package museo.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import museo.model.Autor;
import museo.model.CatalogoObras;
import museo.model.Cesion;
import museo.model.Cuadro;
import museo.model.Escultura;
import museo.model.MonitorVestibulo;
import museo.model.Museo;
import museo.model.ObjetoMuseo;
import museo.model.ObraArte;
import museo.model.Periodo;
import museo.model.Restauracion;
import museo.model.Sala;
import museo.model.Usuario;
import museo.security.SesionUsuario;
import museo.service.ServicioAutenticacion;
import museo.service.ServicioCesion;
import museo.service.ServicioConsulta;
import museo.service.ServicioRestauracion;
import museo.serviceimpl.ServicioAutenticacionImpl;
import museo.serviceimpl.ServicioCesionImpl;
import museo.serviceimpl.ServicioConsultaImpl;
import museo.serviceimpl.ServicioRestauracionImpl;

/**
 * Clase de consola con el menu interactivo manual.
 */
public class AplicacionMuseo {
    private final Scanner scanner = new Scanner(System.in);
    private final DatosIniciales datos = new DatosIniciales();
    private final SesionUsuario sesion = new SesionUsuario();

    private final ServicioAutenticacion servicioAutenticacion = new ServicioAutenticacionImpl();
    private final ServicioRestauracion servicioRestauracion = new ServicioRestauracionImpl();
    private final ServicioCesion servicioCesion = new ServicioCesionImpl();
    private final ServicioConsulta servicioConsulta = new ServicioConsultaImpl();
    private final MonitorVestibulo monitorVestibulo = new MonitorVestibulo(servicioConsulta);

    public void iniciar() {
        datos.cargar();
        menuPrincipal();
    }

    private void menuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== Sistema Museo Java ===");
            System.out.println("1. Ingresar como usuario interno");
            System.out.println("2. Modo visitante");
            System.out.println("3. Salir");
            String opcion = leerTexto("Seleccione una opcion: ");

            switch (opcion) {
                case "1":
                    autenticar();
                    if (sesion.estaAutenticado()) {
                        menuInterno();
                    }
                    break;
                case "2":
                    modoVisitante();
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private void autenticar() {
        String username = leerTexto("Usuario: ");
        String password = leerTexto("Contrasena: ");
        Usuario usuario = servicioAutenticacion.autenticar(username, password, datos.getUsuarios());

        if (usuario == null) {
            System.out.println("Credenciales invalidas.");
            return;
        }

        sesion.iniciarSesion(usuario);
        System.out.println("Bienvenido " + usuario.getUsername() + " - Rol: " + usuario.getRol().getNombre());
    }

    private void modoVisitante() {
        int sala = leerEntero("Numero de sala: ");
        List<ObraArte> obras = monitorVestibulo.consultarObrasPorSala(datos.getCatalogo(), sala);
        if (obras.isEmpty()) {
            System.out.println("No hay obras en esa sala.");
            return;
        }
        for (ObraArte obra : obras) {
            System.out.println(obra.obtenerInformacion());
        }
    }

    private void menuInterno() {
        boolean volver = false;
        while (!volver && sesion.estaAutenticado()) {
            System.out.println("\n--- Menu interno ---");
            System.out.println("1. Registrar obra");
            System.out.println("2. Listar obras");
            System.out.println("3. Buscar obra por ID");
            System.out.println("4. Consultar obras por sala");
            System.out.println("5. Iniciar restauracion");
            System.out.println("6. Finalizar restauracion");
            System.out.println("7. Ver historial de restauraciones");
            System.out.println("8. Ver obras pendientes por restauracion");
            System.out.println("9. Registrar museo colaborador");
            System.out.println("10. Registrar cesion");
            System.out.println("11. Ver cesiones de una obra");
            System.out.println("12. Consultar valor total del museo");
            System.out.println("13. Cambiar de usuario");
            System.out.println("14. Salir al menu principal");
            String opcion = leerTexto("Seleccione una opcion: ");

            switch (opcion) {
                case "1": registrarObra(); break;
                case "2": listarObras(); break;
                case "3": buscarObraPorId(); break;
                case "4": consultarPorSala(); break;
                case "5": iniciarRestauracion(); break;
                case "6": finalizarRestauracion(); break;
                case "7": verHistorial(); break;
                case "8": verPendientes(); break;
                case "9": registrarMuseo(); break;
                case "10": registrarCesion(); break;
                case "11": verCesiones(); break;
                case "12": valorTotal(); break;
                case "13": sesion.cerrarSesion(); System.out.println("Sesion cerrada."); break;
                case "14": volver = true; break;
                default: System.out.println("Opcion invalida.");
            }
        }
    }

    private void registrarObra() {
        CatalogoObras catalogo = datos.getCatalogo();
        String tipo = leerTexto("Tipo (1=Cuadro, 2=Escultura, 3=Objeto): ");
        String id = leerTexto("ID de obra: ");
        if (catalogo.buscarObraPorId(id) != null) {
            System.out.println("Ya existe una obra con ese ID.");
            return;
        }

        String titulo = leerTexto("Titulo: ");
        Autor autor = seleccionarAutor();
        Periodo periodo = seleccionarPeriodo();
        Sala sala = seleccionarSala();
        BigDecimal valor = leerDecimal("Valor economico: ");
        LocalDate fechaCreacion = leerFecha("Fecha creacion (yyyy-MM-dd): ");
        LocalDate fechaIngreso = leerFecha("Fecha ingreso (yyyy-MM-dd): ");

        ObraArte obra;
        if ("1".equals(tipo)) {
            String estilo = leerTexto("Estilo: ");
            String tecnica = leerTexto("Tecnica: ");
            obra = new Cuadro(id, titulo, autor, periodo, valor, fechaCreacion, fechaIngreso, sala, estilo, tecnica);
        } else if ("2".equals(tipo)) {
            String estilo = leerTexto("Estilo: ");
            String material = leerTexto("Material: ");
            obra = new Escultura(id, titulo, autor, periodo, valor, fechaCreacion, fechaIngreso, sala, estilo, material);
        } else if ("3".equals(tipo)) {
            String tipoObjeto = leerTexto("Tipo de objeto: ");
            obra = new ObjetoMuseo(id, titulo, autor, periodo, valor, fechaCreacion, fechaIngreso, sala, tipoObjeto);
        } else {
            System.out.println("Tipo invalido.");
            return;
        }

        catalogo.agregarObra(obra);
        System.out.println("Obra registrada correctamente.");
    }

    private void listarObras() {
        List<ObraArte> obras = datos.getCatalogo().listarObras();
        if (obras.isEmpty()) {
            System.out.println("No hay obras registradas.");
            return;
        }
        for (ObraArte obra : obras) {
            System.out.println(obra.obtenerInformacion());
        }
    }

    private void buscarObraPorId() {
        ObraArte obra = solicitarObra();
        if (obra != null) {
            System.out.println(obra.obtenerInformacion());
        }
    }

    private void consultarPorSala() {
        int numeroSala = leerEntero("Numero de sala: ");
        List<ObraArte> obras = servicioConsulta.listarObrasPorSala(datos.getCatalogo(), numeroSala);
        if (obras.isEmpty()) {
            System.out.println("No hay obras para la sala.");
            return;
        }
        for (ObraArte obra : obras) {
            System.out.println(obra.obtenerInformacion());
        }
    }

    private void iniciarRestauracion() {
        ObraArte obra = solicitarObra();
        if (obra == null) {
            return;
        }
        String tipo = leerTexto("Tipo de restauracion: ");
        String observaciones = leerTexto("Observaciones: ");
        boolean ok = servicioRestauracion.iniciarRestauracion(obra, tipo, observaciones);
        System.out.println(ok ? "Restauracion iniciada." : "No se puede iniciar restauracion.");
    }

    private void finalizarRestauracion() {
        ObraArte obra = solicitarObra();
        if (obra == null) {
            return;
        }
        boolean ok = servicioRestauracion.finalizarRestauracion(obra);
        System.out.println(ok ? "Restauracion finalizada." : "No se puede finalizar restauracion.");
    }

    private void verHistorial() {
        ObraArte obra = solicitarObra();
        if (obra == null) {
            return;
        }
        List<Restauracion> historial = servicioRestauracion.obtenerHistorial(obra);
        if (historial.isEmpty()) {
            System.out.println("No hay historial.");
            return;
        }
        for (Restauracion restauracion : historial) {
            System.out.println("- " + restauracion);
        }
    }

    private void verPendientes() {
        List<ObraArte> pendientes = servicioRestauracion.obrasPendientesPorRestauracion(datos.getCatalogo());
        if (pendientes.isEmpty()) {
            System.out.println("No hay obras pendientes por restauracion.");
            return;
        }
        for (ObraArte obra : pendientes) {
            System.out.println("- " + obra.obtenerInformacion());
        }
    }

    private void registrarMuseo() {
        String nombre = leerTexto("Nombre museo: ");
        String ciudad = leerTexto("Ciudad: ");
        String pais = leerTexto("Pais: ");
        datos.getMuseos().add(new Museo(nombre, ciudad, pais));
        System.out.println("Museo registrado.");
    }

    private void registrarCesion() {
        ObraArte obra = solicitarObra();
        if (obra == null) {
            return;
        }
        Museo museo = seleccionarMuseo();
        LocalDate inicio = leerFecha("Fecha inicio cesion (yyyy-MM-dd): ");
        LocalDate fin = leerFecha("Fecha fin cesion (yyyy-MM-dd): ");
        BigDecimal importe = leerDecimal("Importe pagado: ");

        Cesion cesion = servicioCesion.registrarCesion(obra, museo, inicio, fin, importe);
        if (cesion == null) {
            System.out.println("No se puede ceder obra en restauracion.");
            return;
        }
        System.out.println("Cesion registrada con estado: " + cesion.getEstado());
    }

    private void verCesiones() {
        ObraArte obra = solicitarObra();
        if (obra == null) {
            return;
        }
        List<Cesion> cesiones = servicioCesion.obtenerCesiones(obra);
        if (cesiones.isEmpty()) {
            System.out.println("No hay cesiones registradas.");
            return;
        }
        for (Cesion cesion : cesiones) {
            System.out.println("- " + cesion);
        }
    }

    private void valorTotal() {
        BigDecimal total = servicioConsulta.calcularValorTotal(datos.getCatalogo());
        System.out.println("Valor total del museo: " + total);
    }

    private ObraArte solicitarObra() {
        String id = leerTexto("ID de obra: ");
        ObraArte obra = datos.getCatalogo().buscarObraPorId(id);
        if (obra == null) {
            System.out.println("No existe obra con ese ID.");
        }
        return obra;
    }

    private Autor seleccionarAutor() {
        List<Autor> autores = datos.getAutores();
        System.out.println("Autores:");
        for (int i = 0; i < autores.size(); i++) {
            System.out.println((i + 1) + ". " + autores.get(i));
        }
        int indice = leerEntero("Seleccione autor: ") - 1;
        return (indice >= 0 && indice < autores.size()) ? autores.get(indice) : autores.get(0);
    }

    private Periodo seleccionarPeriodo() {
        List<Periodo> periodos = datos.getPeriodos();
        System.out.println("Periodos:");
        for (int i = 0; i < periodos.size(); i++) {
            System.out.println((i + 1) + ". " + periodos.get(i));
        }
        int indice = leerEntero("Seleccione periodo: ") - 1;
        return (indice >= 0 && indice < periodos.size()) ? periodos.get(indice) : periodos.get(0);
    }

    private Sala seleccionarSala() {
        List<Sala> salas = datos.getSalas();
        System.out.println("Salas:");
        for (int i = 0; i < salas.size(); i++) {
            System.out.println((i + 1) + ". " + salas.get(i));
        }
        int indice = leerEntero("Seleccione sala: ") - 1;
        return (indice >= 0 && indice < salas.size()) ? salas.get(indice) : salas.get(0);
    }

    private Museo seleccionarMuseo() {
        List<Museo> museos = datos.getMuseos();
        System.out.println("Museos colaboradores:");
        for (int i = 0; i < museos.size(); i++) {
            System.out.println((i + 1) + ". " + museos.get(i));
        }
        int indice = leerEntero("Seleccione museo: ") - 1;
        return (indice >= 0 && indice < museos.size()) ? museos.get(indice) : museos.get(0);
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException ex) {
                System.out.println("Entrada invalida. Ingrese un numero entero.");
            }
        }
    }

    private BigDecimal leerDecimal(String mensaje) {
        while (true) {
            try {
                return new BigDecimal(leerTexto(mensaje));
            } catch (NumberFormatException ex) {
                System.out.println("Entrada invalida. Ingrese un valor numerico.");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                return LocalDate.parse(leerTexto(mensaje));
            } catch (DateTimeParseException ex) {
                System.out.println("Formato invalido. Use yyyy-MM-dd.");
            }
        }
    }
}
