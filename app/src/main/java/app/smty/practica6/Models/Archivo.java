package app.smty.practica6.Models;

public class Archivo {
    String nombre;
    String fecha;
    String archivo;

    public Archivo() {
    }

    public Archivo(String nombre, String fecha, String archivo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.archivo = archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "Archivo{" +
                "nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", archivo='" + archivo + '\'' +
                '}';
    }
}
