package co.edu.uniquindio.poo.model;

public class Reserva {
    private int codigo;
    private String fecha;
    private String hora;
    private int duracionMinutos;
    private String tipoEntrenamiento;
    private EstadoReserva estado;
    private double valorPagado;
    private Usuario usuario;
    private Entrenador entrenador;
    private Maquina maquina;
    private ZonaGimnasio zona;

    public Reserva(int codigo, String fecha, String hora, int duracionMinutos, String tipoEntrenamiento, EstadoReserva estado, double valorPagado, Usuario usuario, Entrenador entrenador, Maquina maquina, ZonaGimnasio zona) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.duracionMinutos = duracionMinutos;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.estado = estado;
        this.valorPagado = valorPagado;
        this.usuario = usuario;
        this.entrenador = entrenador;
        this.maquina = maquina;
        this.zona = zona;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(String tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public ZonaGimnasio getZona() {
        return zona;
    }

    public void setZona(ZonaGimnasio zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigo=" + codigo +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", duracionMinutos=" + duracionMinutos +
                ", tipoEntrenamiento='" + tipoEntrenamiento + '\'' +
                ", estado=" + estado +
                ", valorPagado=" + valorPagado +
                ", usuario=" + usuario +
                ", entrenador=" + entrenador +
                ", maquina=" + maquina +
                ", zona=" + zona +
                '}';
    }
}
