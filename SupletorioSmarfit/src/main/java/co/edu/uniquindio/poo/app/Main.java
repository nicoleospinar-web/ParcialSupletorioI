package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    static CentroDeportivo centro = new CentroDeportivo("SmartFit", "900.123.456-7", "Calle 10 # 5-20", "6061234567");

    // MÉTODOS AUXILIARES DE LECTURA

    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    static double leerDecimal(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    // Muestra las opciones numeradas y devuelve la posición elegida (0, 1, 2...)
    static int elegirOpcion(String titulo, Object[] opciones) {
        System.out.println(titulo);
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        int opcion;
        do {
            opcion = leerEntero("Opción: ");
        } while (opcion < 1 || opcion > opciones.length);
        return opcion - 1;
    }

    // MAIN

    public static void main(String[] args) {
        cargarDatosDePrueba();
        int opcion;
        do {
            System.out.println("\n===== " + centro.getNombreComercial() + " | NIT " + centro.getNit() + " =====");
            System.out.println("1. Gestión de usuarios");
            System.out.println("2. Gestión de entrenadores");
            System.out.println("3. Gestión de máquinas");
            System.out.println("4. Gestión de reservas");
            System.out.println("5. Matriz de ocupación semanal");
            System.out.println("6. Reportes y consultas");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: menuUsuarios(); break;
                case 2: menuEntrenadores(); break;
                case 3: menuMaquinas(); break;
                case 4: menuReservas(); break;
                case 5: menuMatriz(); break;
                case 6: menuReportes(); break;
                case 0: System.out.println("Hasta pronto."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // USUARIOS

    static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- USUARIOS ---");
            System.out.println("1. Registrar  2. Consultar  3. Listar  4. Actualizar  5. Eliminar  0. Volver");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: registrarUsuario(); break;
                case 2: consultarUsuario(); break;
                case 3: centro.listarUsuarios(); break;
                case 4: actualizarUsuario(); break;
                case 5: eliminarUsuario(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    static void registrarUsuario() {
        String documento = leerTexto("Documento: ");
        if (documento.isEmpty()) {
            System.out.println("El documento no puede estar vacío.");
            return;
        }
        if (centro.buscarUsuario(documento) != null) {
            System.out.println("Ya existe un usuario con ese documento.");
            return;
        }
        String nombre = leerTexto("Nombre completo: ");
        int edad = leerEntero("Edad: ");
        String telefono = leerTexto("Teléfono: ");
        double peso = leerDecimal("Peso (kg): ");
        Membresia membresia = Membresia.values()[elegirOpcion("Tipo de membresía:", Membresia.values())];
        if (centro.registrarUsuario(new Usuario(documento, nombre, edad, telefono, peso, membresia))) {
            System.out.println("Usuario registrado correctamente.");
        }
    }

    static void consultarUsuario() {
        Usuario u = centro.buscarUsuario(leerTexto("Documento: "));
        if (u == null) {
            System.out.println("No existe un usuario con ese documento.");
        } else {
            System.out.println(u);
        }
    }

    static void actualizarUsuario() {
        String documento = leerTexto("Documento del usuario a actualizar: ");
        if (centro.buscarUsuario(documento) == null) {
            System.out.println("No existe un usuario con ese documento.");
            return;
        }
        String nombre = leerTexto("Nuevo nombre completo: ");
        int edad = leerEntero("Nueva edad: ");
        String telefono = leerTexto("Nuevo teléfono: ");
        double peso = leerDecimal("Nuevo peso (kg): ");
        Membresia membresia = Membresia.values()[elegirOpcion("Nuevo tipo de membresía:", Membresia.values())];
        centro.actualizarUsuario(documento, nombre, edad, telefono, peso, membresia);
        System.out.println("Usuario actualizado.");
    }

    static void eliminarUsuario() {
        if (centro.eliminarUsuario(leerTexto("Documento del usuario a eliminar: "))) {
            System.out.println("Usuario eliminado.");
        }
    }

    // ENTRENADORES

    static void menuEntrenadores() {
        int opcion;
        do {
            System.out.println("\n--- ENTRENADORES ---");
            System.out.println("1. Registrar  2. Consultar  3. Listar  4. Actualizar  5. Eliminar  0. Volver");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: registrarEntrenador(); break;
                case 2: consultarEntrenador(); break;
                case 3: centro.listarEntrenadores(); break;
                case 4: actualizarEntrenador(); break;
                case 5: eliminarEntrenador(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    static void registrarEntrenador() {
        String documento = leerTexto("Documento: ");
        if (documento.isEmpty()) {
            System.out.println("El documento no puede estar vacío.");
            return;
        }
        if (centro.buscarEntrenador(documento) != null) {
            System.out.println("Ya existe un entrenador con ese documento.");
            return;
        }
        String nombre = leerTexto("Nombre completo: ");
        Especialidad especialidad = Especialidad.values()[elegirOpcion("Especialidad:", Especialidad.values())];
        int anios = leerEntero("Años de experiencia: ");
        String telefono = leerTexto("Teléfono: ");
        if (centro.registrarEntrenador(new Entrenador(documento, nombre, especialidad, anios, telefono))) {
            System.out.println("Entrenador registrado correctamente.");
        }
    }

    static void consultarEntrenador() {
        Entrenador e = centro.buscarEntrenador(leerTexto("Documento: "));
        if (e == null) {
            System.out.println("No existe un entrenador con ese documento.");
        } else {
            System.out.println(e);
        }
    }

    static void actualizarEntrenador() {
        String documento = leerTexto("Documento del entrenador a actualizar: ");
        if (centro.buscarEntrenador(documento) == null) {
            System.out.println("No existe un entrenador con ese documento.");
            return;
        }
        String nombre = leerTexto("Nuevo nombre completo: ");
        Especialidad especialidad = Especialidad.values()[elegirOpcion("Nueva especialidad:", Especialidad.values())];
        int anios = leerEntero("Nuevos años de experiencia: ");
        String telefono = leerTexto("Nuevo teléfono: ");
        centro.actualizarEntrenador(documento, nombre, especialidad, anios, telefono);
        System.out.println("Entrenador actualizado.");
    }

    static void eliminarEntrenador() {
        if (centro.eliminarEntrenador(leerTexto("Documento del entrenador a eliminar: "))) {
            System.out.println("Entrenador eliminado.");
        }
    }

    // MÁQUINAS

    static void menuMaquinas() {
        int opcion;
        do {
            System.out.println("\n--- MÁQUINAS ---");
            System.out.println("1. Registrar  2. Consultar  3. Listar  4. Actualizar  5. Eliminar  0. Volver");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: registrarMaquina(); break;
                case 2: consultarMaquina(); break;
                case 3: centro.listarMaquinas(); break;
                case 4: actualizarMaquina(); break;
                case 5: eliminarMaquina(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    static void registrarMaquina() {
        String codigo = leerTexto("Código de la máquina: ");
        if (codigo.isEmpty()) {
            System.out.println("El código no puede estar vacío.");
            return;
        }
        if (centro.buscarMaquina(codigo) != null) {
            System.out.println("Ya existe una máquina con ese código.");
            return;
        }
        String nombre = leerTexto("Nombre: ");
        String tipo = leerTexto("Tipo de máquina: ");
        ZonaGimnasio zona = ZonaGimnasio.values()[elegirOpcion("Zona:", ZonaGimnasio.values())];
        EstadoMaquina estado = EstadoMaquina.values()[elegirOpcion("Estado:", EstadoMaquina.values())];
        double valor = leerDecimal("Valor de mantenimiento: ");
        if (centro.registrarMaquina(new Maquina(codigo, nombre, tipo, valor, zona, estado))) {
            System.out.println("Máquina registrada correctamente.");
        }
    }

    static void consultarMaquina() {
        Maquina m = centro.buscarMaquina(leerTexto("Código: "));
        if (m == null) {
            System.out.println("No existe una máquina con ese código.");
        } else {
            System.out.println(m);
        }
    }

    static void actualizarMaquina() {
        String codigo = leerTexto("Código de la máquina a actualizar: ");
        if (centro.buscarMaquina(codigo) == null) {
            System.out.println("No existe una máquina con ese código.");
            return;
        }
        String nombre = leerTexto("Nuevo nombre: ");
        String tipo = leerTexto("Nuevo tipo: ");
        ZonaGimnasio zona = ZonaGimnasio.values()[elegirOpcion("Nueva zona:", ZonaGimnasio.values())];
        EstadoMaquina estado = EstadoMaquina.values()[elegirOpcion("Nuevo estado:", EstadoMaquina.values())];
        double valor = leerDecimal("Nuevo valor de mantenimiento: ");
        centro.actualizarMaquina(codigo, nombre, tipo, zona, estado, valor);
        System.out.println("Máquina actualizada.");
    }

    static void eliminarMaquina() {
        if (centro.eliminarMaquina(leerTexto("Código de la máquina a eliminar: "))) {
            System.out.println("Máquina eliminada.");
        }
    }

    // RESERVAS

    static void menuReservas() {
        int opcion;
        do {
            System.out.println("\n--- RESERVAS ---");
            System.out.println("1. Registrar  2. Consultar  3. Listar  4. Actualizar datos  5. Cambiar estado  6. Eliminar  0. Volver");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: registrarReserva(); break;
                case 2: consultarReserva(); break;
                case 3: centro.listarReservas(); break;
                case 4: actualizarReserva(); break;
                case 5: cambiarEstadoReserva(); break;
                case 6: eliminarReserva(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    static void registrarReserva() {
        int codigo = leerEntero("Código de la reserva: ");
        if (centro.buscarReserva(codigo) != null) {
            System.out.println("Ya existe una reserva con ese código.");
            return;
        }
        String docUsuario = leerTexto("Documento del usuario: ");
        if (centro.buscarUsuario(docUsuario) == null) {
            System.out.println("El usuario no existe. Regístrelo primero.");
            return;
        }
        String docEntrenador = leerTexto("Documento del entrenador: ");
        if (centro.buscarEntrenador(docEntrenador) == null) {
            System.out.println("El entrenador no existe. Regístrelo primero.");
            return;
        }
        String codigoMaquina = leerTexto("Código de la máquina (Enter si es solo una zona): ");
        ZonaGimnasio zona = null;
        if (codigoMaquina.isEmpty()) {
            zona = ZonaGimnasio.values()[elegirOpcion("Zona de entrenamiento:", ZonaGimnasio.values())];
        } else if (centro.buscarMaquina(codigoMaquina) == null) {
            System.out.println("La máquina no existe. Regístrela primero.");
            return;
        }
        String fecha = leerTexto("Fecha (dd/mm/aaaa): ");
        String hora = leerTexto("Hora (hh:mm): ");
        int duracion = leerEntero("Duración en minutos: ");
        String tipo = leerTexto("Tipo de entrenamiento: ");
        EstadoReserva estado = EstadoReserva.values()[elegirOpcion("Estado:", EstadoReserva.values())];
        double valor = leerDecimal("Valor pagado: ");
        if (centro.registrarReserva(codigo, fecha, hora, duracion, tipo, estado, valor,
                docUsuario, docEntrenador, codigoMaquina, zona)) {
            System.out.println("Reserva registrada correctamente.");
        }
    }

    static void consultarReserva() {
        Reserva r = centro.buscarReserva(leerEntero("Código de la reserva: "));
        if (r == null) {
            System.out.println("No existe una reserva con ese código.");
        } else {
            System.out.println(r);
        }
    }

    static void actualizarReserva() {
        int codigo = leerEntero("Código de la reserva a actualizar: ");
        if (centro.buscarReserva(codigo) == null) {
            System.out.println("No existe una reserva con ese código.");
            return;
        }
        String fecha = leerTexto("Nueva fecha (dd/mm/aaaa): ");
        String hora = leerTexto("Nueva hora (hh:mm): ");
        int duracion = leerEntero("Nueva duración en minutos: ");
        String tipo = leerTexto("Nuevo tipo de entrenamiento: ");
        double valor = leerDecimal("Nuevo valor pagado: ");
        centro.actualizarReserva(codigo, fecha, hora, duracion, tipo, valor);
        System.out.println("Reserva actualizada.");
    }

    static void cambiarEstadoReserva() {
        int codigo = leerEntero("Código de la reserva: ");
        if (centro.buscarReserva(codigo) == null) {
            System.out.println("No existe una reserva con ese código.");
            return;
        }
        EstadoReserva nuevo = EstadoReserva.values()[elegirOpcion("Nuevo estado:", EstadoReserva.values())];
        if (centro.cambiarEstadoReserva(codigo, nuevo)) {
            System.out.println("Estado actualizado.");
        }
    }

    static void eliminarReserva() {
        if (centro.eliminarReserva(leerEntero("Código de la reserva a eliminar: "))) {
            System.out.println("Reserva eliminada.");
        }
    }

    // MATRIZ DE OCUPACIÓN

    static void menuMatriz() {
        int opcion;
        do {
            System.out.println("\n--- MATRIZ DE OCUPACIÓN ---");
            System.out.println("1. Ver matriz  2. Registrar ocupación  3. Analizar ocupación  0. Volver");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: centro.mostrarMatriz(); break;
                case 2: registrarOcupacion(); break;
                case 3: centro.analizarOcupacion(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    static void registrarOcupacion() {
        ZonaGimnasio zona = ZonaGimnasio.values()[elegirOpcion("Zona:", ZonaGimnasio.values())];
        int dia = elegirOpcion("Día de la semana:", CentroDeportivo.DIAS);
        int cantidad = leerEntero("Cantidad de usuarios: ");
        if (centro.registrarOcupacion(zona, dia, cantidad)) {
            System.out.println("Ocupación registrada.");
        } else {
            System.out.println("Datos no válidos (la cantidad no puede ser negativa).");
        }
    }

    // REPORTES Y CONSULTAS

    static void menuReportes() {
        int opcion;
        do {
            System.out.println("\n--- REPORTES Y CONSULTAS ---");
            System.out.println("1. Usuarios VIP");
            System.out.println("2. Control de máquinas");
            System.out.println("3. Reservas especiales");
            System.out.println("4. Ingresos por fecha");
            System.out.println("5. Rendimiento de entrenadores");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: centro.consultarUsuariosVIP(); break;
                case 2: centro.controlMaquinas(); break;
                case 3: centro.mostrarReservasEspeciales(); break;
                case 4: centro.ingresosPorFecha(leerTexto("Fecha (dd/mm/aaaa): ")); break;
                case 5: centro.rendimientoEntrenadores(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // DATOS DE PRUEBA (para la demostración)

    static void cargarDatosDePrueba() {
        centro.registrarUsuario(new Usuario("1001", "Ana Torres", 25, "3001112233", 60.5, Membresia.VIP));
        centro.registrarUsuario(new Usuario("1002", "Luis Gómez", 30, "3004445566", 78.0, Membresia.BASICA));
        centro.registrarUsuario(new Usuario("1003", "Marta Ríos", 28, "3007778899", 65.2, Membresia.VIP));
        centro.registrarUsuario(new Usuario("1004", "Pedro Salas", 22, "3112223344", 70.0, Membresia.PREMIUM));

        centro.registrarEntrenador(new Entrenador("2001", "Carlos Ruiz", Especialidad.MUSCULACION, 5, "3201112233"));
        centro.registrarEntrenador(new Entrenador("2002", "Laura Peña", Especialidad.CARDIO, 3, "3204445566"));

        centro.registrarMaquina(new Maquina("M001", "Caminadora", "Cardio", 150000, ZonaGimnasio.ZONA_CARDIOVASCULAR, EstadoMaquina.DISPONIBLE));
        centro.registrarMaquina(new Maquina("M002", "Press de banca", "Pesas", 300000, ZonaGimnasio.ZONA_PESAS, EstadoMaquina.EN_USO));
        centro.registrarMaquina(new Maquina("M003", "Bicicleta estática", "Cardio", 90000, ZonaGimnasio.ZONA_CARDIOVASCULAR, EstadoMaquina.MANTENIMIENTO));
        centro.registrarMaquina(new Maquina("M004", "Cuerda de batalla", "Funcional", 40000, ZonaGimnasio.ZONA_FUNCIONAL, EstadoMaquina.DISPONIBLE));

        centro.registrarReserva(1234, "01/10/2026", "08:00", 60, "Cardio", EstadoReserva.FINALIZADA, 20000, "1001", "2002", "M001", null);
        centro.registrarReserva(5671, "01/10/2026", "09:00", 90, "Funcional", EstadoReserva.CONFIRMADA, 35000, "1002", "2001", "M004", null);
        centro.registrarReserva(2468, "01/10/2026", "10:00", 45, "Funcional", EstadoReserva.CANCELADA, 15000, "1003", "2002", "", ZonaGimnasio.ZONA_FUNCIONAL);
        centro.registrarReserva(3030, "02/10/2026", "07:00", 60, "Musculación", EstadoReserva.FINALIZADA, 30000, "1004", "2001", "", ZonaGimnasio.ZONA_PESAS);
        centro.registrarReserva(1235, "02/10/2026", "08:30", 60, "Cardio", EstadoReserva.FINALIZADA, 25000, "1001", "2002", "M001", null);

        int[][] datos = {
                {23, 12, 34, 18, 25, 30, 10},
                {12, 34, 5, 20, 28, 15, 8},
                {9, 14, 11, 16, 22, 19, 6}
        };
        for (int z = 0; z < datos.length; z++) {
            for (int d = 0; d < datos[z].length; d++) {
                centro.registrarOcupacion(ZonaGimnasio.values()[z], d, datos[z][d]);
            }
        }
    }
}

