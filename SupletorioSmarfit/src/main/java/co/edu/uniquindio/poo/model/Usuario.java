package co.edu.uniquindio.poo.model;

public class Usuario {
    private String documento;
    private String nombreCompleto;
    private int edad;
    private String telefono;
    private double peso;
    private Membresia membresia;

    public Usuario(String documento, String nombreCompleto, int edad, String telefono, double peso, Membresia membresia) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.telefono = telefono;
        this.peso = peso;
        this.membresia = membresia;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "documento='" + documento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", peso=" + peso +
                ", membresia=" + membresia +
                '}';
    }
}
