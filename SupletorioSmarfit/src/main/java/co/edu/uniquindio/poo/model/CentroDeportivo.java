package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class CentroDeportivo {

    public static final int MAX_MAQUINAS = 50;
    public static final int MAX_RESERVAS = 200;
    public static final String[] DIAS = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Entrenador> entrenadores;
    private Maquina[] maquinas;
    private int cantidadMaquinas;
    private Reserva[] reservas;
    private int cantidadReservas;
    private int[][] ocupacion; // filas = zonas, columnas = días de la semana

    public CentroDeportivo(String nombreComercial, String nit, String direccion, String telefono) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        usuarios = new ArrayList<>();
        entrenadores = new ArrayList<>();
        maquinas = new Maquina[MAX_MAQUINAS];
        cantidadMaquinas = 0;
        reservas = new Reserva[MAX_RESERVAS];
        cantidadReservas = 0;
        ocupacion = new int[ZonaGimnasio.values().length][DIAS.length];
    }

    public String getNombreComercial() {
        return nombreComercial;
    }
    public String getNit() {
        return nit;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefono() {
        return telefono;
    }

    // CRUD USUARIOS

    public Usuario buscarUsuario(String documento) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDocumento().equals(documento)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean registrarUsuario (Usuario Usuario) {
        Usuario usuarioEncontrado = buscarUsuario (Usuario.getDocumento());
        if (usuarioEncontrado == null) {
            usuarios.add(Usuario);
            return true;
        } else return false;
    }

    public boolean actualizarUsuario(String documento, String nombre, int edad, String telefono, double peso, Membresia membresia) {
        Usuario usuarioEncontrado = buscarUsuario(documento);
        if (usuarioEncontrado != null) {
            usuarioEncontrado.setNombreCompleto(nombre);
            usuarioEncontrado.setEdad(edad);
            usuarioEncontrado.setTelefono(telefono);
            usuarioEncontrado.setPeso(peso);
            usuarioEncontrado.setMembresia(membresia);
            return true;
        } else return false;
    }
    
    public boolean eliminarUsuario(String documento) {
        Usuario usuarioEncontrado = buscarUsuario(documento);
        if (usuarioEncontrado != null) {
            usuarios.remove (usuarioEncontrado);
            return true;
        } else return false;
        }
        
    private boolean usuarioTieneReservas(String documento) {
        for (int i = 0; i < cantidadReservas; i++) {
            if (reservas[i].getUsuario().getDocumento().equals(documento)) {
                return true;
            }
        }
        return false;
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }
    
    // CRUD ENTRENADORES
    
    public Entrenador buscarEntrenador(String documento) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getDocumento().equals(documento)) {
                return entrenador;
            }
        }
        return null;
    }

    public boolean registrarEntrenador (Entrenador entrenador) {
        Entrenador EntrenadorEncontrado = buscarEntrenador (entrenador.getDocumento());
        if (EntrenadorEncontrado == null) {
            entrenadores.add (entrenador);
            return true;
        } else return false;

    }

    public boolean actualizarEntrenador(String documento, String nombre, Especialidad especialidad, int anios, String telefono) {
        Entrenador entrenadorEncontrado = buscarEntrenador(documento);
        if (entrenadorEncontrado != null) {
            entrenadorEncontrado.setNombreCompleto(nombre);
            entrenadorEncontrado.setEspecialidad(especialidad);
            entrenadorEncontrado.setAniosExperiencia(anios);
            entrenadorEncontrado.setTelefono(telefono);
            return true;
        } else return false;
    }

    public boolean eliminarEntrenador(String documento) {
        Entrenador entrenadorEncontrado = buscarEntrenador(documento);
        if (entrenadorEncontrado != null) {
            entrenadores.remove(entrenadorEncontrado);
            return true;
        } else return false;
    }

    private boolean entrenadorTieneReservas(String documento) {
        for (int i = 0; i < cantidadReservas; i++) {
            if (reservas[i].getEntrenador().getDocumento().equals(documento)) {
                return true;
            }
        }
        return false;
    }

    public void listarEntrenadores() {
        if (entrenadores.isEmpty()) {
            System.out.println("No hay entrenadores registrados.");
            return;
        }
        for (Entrenador e : entrenadores) {
            System.out.println(e);
        }
    }

    // CRUD MÁQUINAS (arreglo)

    private int buscarIndiceMaquina(String codigo) {
        for (int i = 0; i < cantidadMaquinas; i++) {
            if (maquinas[i].getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public Maquina buscarMaquina(String codigo) {
        int i = buscarIndiceMaquina(codigo);
        if (i == -1) {
            return null;
        }
        return maquinas[i];
    }

    public boolean registrarMaquina(Maquina nueva) {
        if (buscarMaquina(nueva.getCodigo()) != null) {
            System.out.println("Ya existe una máquina con ese código.");
            return false;
        }
        if (cantidadMaquinas == maquinas.length) {
            System.out.println("No hay espacio para más máquinas.");
            return false;
        }
        maquinas[cantidadMaquinas] = nueva;
        cantidadMaquinas++;
        return true;
    }

    public boolean actualizarMaquina(String codigo, String nombre, String tipo, ZonaGimnasio zona, EstadoMaquina estado, double valor) {
        Maquina maquina = buscarMaquina(codigo);
        if (maquina == null) {
            return false;
        }
        maquina.setNombre(nombre);
        maquina.setTipo(tipo);
        maquina.setZona(zona);
        maquina.setEstado(estado);
        maquina.setValorMantenimiento(valor);
        return true;
    }

    public boolean eliminarMaquina(String codigo) {
        int pos = buscarIndiceMaquina(codigo);
        if (pos == -1) {
            System.out.println("No existe una máquina con ese código.");
            return false;
        }
        if (maquinaTieneReservas(codigo)) {
            System.out.println("No se puede eliminar: la máquina tiene reservas asociadas.");
            return false;
        }
        for (int i = pos; i < cantidadMaquinas - 1; i++) {
            maquinas[i] = maquinas[i + 1];
        }
        maquinas[cantidadMaquinas - 1] = null;
        cantidadMaquinas--;
        return true;
    }

    private boolean maquinaTieneReservas(String codigo) {
        for (int i = 0; i < cantidadReservas; i++) {
            Maquina m = reservas[i].getMaquina();
            if (m != null && m.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public void listarMaquinas() {
        if (cantidadMaquinas == 0) {
            System.out.println("No hay máquinas registradas.");
            return;
        }
        for (int i = 0; i < cantidadMaquinas; i++) {
            System.out.println(maquinas[i]);
        }
    }
    
    // CRUD RESERVAS (arreglo)
    
    private int buscarIndiceReserva(int codigo) {
        for (int i = 0; i < cantidadReservas; i++) {
            if (reservas[i].getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    public Reserva buscarReserva(int codigo) {
        int i = buscarIndiceReserva(codigo);
        if (i == -1) {
            return null;
        }
        return reservas[i];
    }

    public boolean registrarReserva(int codigo, String fecha, String hora, int duracion, String tipo,
                                    EstadoReserva estado, double valor, String docUsuario,
                                    String docEntrenador, String codigoMaquina, ZonaGimnasio zona) {
        if (buscarReserva(codigo) != null) {
            System.out.println("Ya existe una reserva con ese código.");
            return false;
        }
        if (cantidadReservas == reservas.length) {
            System.out.println("No hay espacio para más reservas.");
            return false;
        }
        Usuario usuario = buscarUsuario(docUsuario);
        if (usuario == null) {
            System.out.println("El usuario no existe. Regístrelo primero.");
            return false;
        }
        Entrenador entrenador = buscarEntrenador(docEntrenador);
        if (entrenador == null) {
            System.out.println("El entrenador no existe. Regístrelo primero.");
            return false;
        }
        Maquina maquina = null;
        if (!codigoMaquina.isEmpty()) {
            maquina = buscarMaquina(codigoMaquina);
            if (maquina == null) {
                System.out.println("La máquina no existe. Regístrela primero.");
                return false;
            }
            zona = maquina.getZona();
        }
        if (estado == EstadoReserva.CONFIRMADA && maquina != null) {
            if (maquina.getEstado() != EstadoMaquina.DISPONIBLE) {
                System.out.println("La máquina no está disponible (estado: " + maquina.getEstado() + ").");
                return false;
            }
            maquina.setEstado(EstadoMaquina.EN_USO);
        }
        reservas[cantidadReservas] = new Reserva(codigo, fecha, hora, duracion, tipo, estado, valor,
                usuario, entrenador, maquina, zona);
        cantidadReservas++;
        return true;
    }

    public boolean actualizarReserva(int codigo, String fecha, String hora, int duracion, String tipo, double valor) {
        Reserva r = buscarReserva(codigo);
        if (r == null) {
            return false;
        }
        r.setFecha(fecha);
        r.setHora(hora);
        r.setDuracionMinutos(duracion);
        r.setTipoEntrenamiento(tipo);
        r.setValorPagado(valor);
        return true;
    }

    public boolean cambiarEstadoReserva(int codigo, EstadoReserva nuevo) {
        Reserva r = buscarReserva(codigo);
        if (r == null) {
            System.out.println("No existe una reserva con ese código.");
            return false;
        }
        EstadoReserva actual = r.getEstado();
        if (actual == EstadoReserva.FINALIZADA || actual == EstadoReserva.CANCELADA) {
            System.out.println("Una reserva finalizada o cancelada ya no puede cambiar de estado.");
            return false;
        }
        Maquina m = r.getMaquina();
        if (nuevo == EstadoReserva.CONFIRMADA && actual != EstadoReserva.CONFIRMADA && m != null) {
            if (m.getEstado() != EstadoMaquina.DISPONIBLE) {
                System.out.println("La máquina no está disponible (estado: " + m.getEstado() + ").");
                return false;
            }
            m.setEstado(EstadoMaquina.EN_USO);
        }
        if ((nuevo == EstadoReserva.FINALIZADA || nuevo == EstadoReserva.CANCELADA)
                && actual == EstadoReserva.CONFIRMADA && m != null) {
            m.setEstado(EstadoMaquina.DISPONIBLE);
        }
        r.setEstado(nuevo);
        return true;
    }

    public boolean eliminarReserva(int codigo) {
        int pos = buscarIndiceReserva(codigo);
        if (pos == -1) {
            System.out.println("No existe una reserva con ese código.");
            return false;
        }
        Reserva r = reservas[pos];
        if (r.getEstado() == EstadoReserva.CONFIRMADA && r.getMaquina() != null) {
            r.getMaquina().setEstado(EstadoMaquina.DISPONIBLE);
        }
        for (int i = pos; i < cantidadReservas - 1; i++) {
            reservas[i] = reservas[i + 1];
        }
        reservas[cantidadReservas - 1] = null;
        cantidadReservas--;
        return true;
    }

    public void listarReservas() {
        if (cantidadReservas == 0) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        for (int i = 0; i < cantidadReservas; i++) {
            System.out.println(reservas[i]);
        }
    }

    // FUNCIONALIDAD 1: usuarios VIP

    public void consultarUsuariosVIP() {
        int contador = 0;
        for (Usuario u : usuarios) {
            if (u.getMembresia() == Membresia.VIP) {
                System.out.println(u);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay usuarios con membresía VIP.");
        } else {
            System.out.println("Total usuarios VIP: " + contador);
        }
    }

    // FUNCIONALIDAD 2: control de máquinas

    public void controlMaquinas() {
        if (cantidadMaquinas == 0) {
            System.out.println("No hay máquinas registradas.");
            return;
        }
        int disponibles = 0;
        int enUso = 0;
        int mantenimiento = 0;
        Maquina mayor = maquinas[0];
        Maquina menor = maquinas[0];

        for (int i = 0; i < cantidadMaquinas; i++) {
            Maquina m = maquinas[i];
            if (m.getEstado() == EstadoMaquina.DISPONIBLE) {
                disponibles++;
            } else if (m.getEstado() == EstadoMaquina.EN_USO) {
                enUso++;
            } else {
                mantenimiento++;
            }
            if (m.getValorMantenimiento() > mayor.getValorMantenimiento()) {
                mayor = m;
            }
            if (m.getValorMantenimiento() < menor.getValorMantenimiento()) {
                menor = m;
            }
        }
        System.out.println("Máquinas disponibles: " + disponibles);
        System.out.println("Máquinas en uso: " + enUso);
        System.out.println("Máquinas en mantenimiento: " + mantenimiento);
        System.out.println("Mayor valor de mantenimiento: " + mayor);
        System.out.println("Menor valor de mantenimiento: " + menor);
    }

    // FUNCIONALIDAD 3: matriz de ocupación

    public boolean registrarOcupacion(ZonaGimnasio zona, int dia, int cantidad) {
        if (dia < 0 || dia >= DIAS.length || cantidad < 0) {
            return false;
        }
        ocupacion[zona.ordinal()][dia] = cantidad;
        return true;
    }

    public void mostrarMatriz() {
        System.out.printf("%-22s", "Zona");
        for (int d = 0; d < DIAS.length; d++) {
            System.out.printf("%-11s", DIAS[d]);
        }
        System.out.println();
        for (int z = 0; z < ocupacion.length; z++) {
            System.out.printf("%-22s", ZonaGimnasio.values()[z]);
            for (int d = 0; d < ocupacion[z].length; d++) {
                System.out.printf("%-11d", ocupacion[z][d]);
            }
            System.out.println();
        }
    }

    public void analizarOcupacion() {
        int[] totalPorDia = new int[DIAS.length];
        int[] totalPorZona = new int[ocupacion.length];
        int totalSemana = 0;

        for (int z = 0; z < ocupacion.length; z++) {
            for (int d = 0; d < ocupacion[z].length; d++) {
                totalPorDia[d] += ocupacion[z][d];
                totalPorZona[z] += ocupacion[z][d];
                totalSemana += ocupacion[z][d];
            }
        }

        int diaMayor = 0;
        int diaMenor = 0;
        for (int d = 1; d < totalPorDia.length; d++) {
            if (totalPorDia[d] > totalPorDia[diaMayor]) {
                diaMayor = d;
            }
            if (totalPorDia[d] < totalPorDia[diaMenor]) {
                diaMenor = d;
            }
        }

        int zonaMayor = 0;
        for (int z = 1; z < totalPorZona.length; z++) {
            if (totalPorZona[z] > totalPorZona[zonaMayor]) {
                zonaMayor = z;
            }
        }

        System.out.println("Día con mayor cantidad de usuarios: " + DIAS[diaMayor] + " (" + totalPorDia[diaMayor] + ")");
        System.out.println("Día con menor cantidad de usuarios: " + DIAS[diaMenor] + " (" + totalPorDia[diaMenor] + ")");
        System.out.println("Total de usuarios atendidos en la semana: " + totalSemana);
        System.out.println("Zona con mayor utilización: " + ZonaGimnasio.values()[zonaMayor] + " (" + totalPorZona[zonaMayor] + ")");
    }

    // FUNCIONALIDAD 4: número especial de reserva

    public int sumaDigitos(int numero) {
        int suma = 0;
        numero = Math.abs(numero);
        while (numero > 0) {
            suma += numero % 10;
            numero = numero / 10;
        }
        return suma;
    }

    public boolean esReservaEspecial(int codigo) {
        return sumaDigitos(codigo) % 2 == 0;
    }

    public void mostrarReservasEspeciales() {
        int contador = 0;
        for (int i = 0; i < cantidadReservas; i++) {
            int codigo = reservas[i].getCodigo();
            if (esReservaEspecial(codigo)) {
                System.out.println("Reserva especial -> código " + codigo + " (suma de dígitos = " + sumaDigitos(codigo) + ")");
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay reservas especiales.");
        } else {
            System.out.println("Total de reservas especiales: " + contador);
        }
    }

    // FUNCIONALIDAD 5: reporte de ingresos por fecha

    public double ingresosPorFecha(String fecha) {
        double total = 0;
        int contador = 0;
        for (int i = 0; i < cantidadReservas; i++) {
            Reserva r = reservas[i];
            boolean mismaFecha = r.getFecha().equals(fecha);
            boolean pagada = r.getEstado() == EstadoReserva.CONFIRMADA || r.getEstado() == EstadoReserva.FINALIZADA;
            if (mismaFecha && pagada) {
                total += r.getValorPagado();
                contador++;
            }
        }
        System.out.println("Reservas confirmadas o finalizadas el " + fecha + ": " + contador);
        System.out.println("Ingreso total del día: $" + total);
        return total;
    }

    // FUNCIONALIDAD 6: rendimiento de entrenadores
    // (sesión realizada = reserva FINALIZADA)

    public void rendimientoEntrenadores() {
        if (entrenadores.isEmpty()) {
            System.out.println("No hay entrenadores registrados.");
            return;
        }
        Entrenador mayor = null;
        Entrenador menor = null;
        int sesionesMayor = 0;
        int sesionesMenor = 0;

        for (int i = 0; i < entrenadores.size(); i++) {
            Entrenador e = entrenadores.get(i);
            int sesiones = 0;
            ArrayList<String> usuariosAtendidos = new ArrayList<>();

            for (int j = 0; j < cantidadReservas; j++) {
                Reserva r = reservas[j];
                if (r.getEntrenador().getDocumento().equals(e.getDocumento())
                        && r.getEstado() == EstadoReserva.FINALIZADA) {
                    sesiones++;
                    String docUsuario = r.getUsuario().getDocumento();
                    if (!usuariosAtendidos.contains(docUsuario)) {
                        usuariosAtendidos.add(docUsuario);
                    }
                }
            }

            System.out.println(e.getNombreCompleto() + " -> sesiones realizadas: " + sesiones
                    + " | usuarios atendidos: " + usuariosAtendidos.size());

            if (i == 0 || sesiones > sesionesMayor) {
                mayor = e;
                sesionesMayor = sesiones;
            }
            if (i == 0 || sesiones < sesionesMenor) {
                menor = e;
                sesionesMenor = sesiones;
            }
        }
        System.out.println("Entrenador con más sesiones: " + mayor.getNombreCompleto() + " (" + sesionesMayor + ")");
        System.out.println("Entrenador con menos sesiones: " + menor.getNombreCompleto() + " (" + sesionesMenor + ")");
    }
}

