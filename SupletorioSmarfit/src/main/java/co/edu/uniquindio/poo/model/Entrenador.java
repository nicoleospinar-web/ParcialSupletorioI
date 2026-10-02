package co.edu.uniquindio.poo.model;

public class Entrenador {
    private String documento;
    private String nombreCompleto;
    private Especialidad especialidad;
    private int aniosExperiencia;
    private String telefono;

    public Entrenador(String documento, String nombreCompleto, Especialidad especialidad, int aniosExperiencia, String telefono) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;
        this.telefono = telefono;
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

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "documento='" + documento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", especialidad=" + especialidad +
                ", aniosExperiencia=" + aniosExperiencia +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
