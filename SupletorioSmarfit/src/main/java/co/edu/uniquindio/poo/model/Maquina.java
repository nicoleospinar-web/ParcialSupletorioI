package co.edu.uniquindio.poo.model;

public class Maquina {
    private String codigo;
    private String nombre;
    private String tipo;
    private double valorMantenimiento;
    private ZonaGimnasio zona;
    private EstadoMaquina estado;

    public Maquina(String codigo, String nombre, String tipo, double valorMantenimiento, ZonaGimnasio zona, EstadoMaquina estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.valorMantenimiento = valorMantenimiento;
        this.zona = zona;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorMantenimiento() {
        return valorMantenimiento;
    }

    public void setValorMantenimiento(double valorMantenimiento) {
        this.valorMantenimiento = valorMantenimiento;
    }

    public ZonaGimnasio getZona() {
        return zona;
    }

    public void setZona(ZonaGimnasio zona) {
        this.zona = zona;
    }

    public EstadoMaquina getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaquina estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", valorMantenimiento=" + valorMantenimiento +
                ", zona=" + zona +
                ", estado=" + estado +
                '}';
    }
}
