
package uis.edu.co.banco_de_sangre.modelo;

public class Donacion {

    @Override
    public String toString() {
        return "Donacion{" + "id_donacion=" + id_donacion + ", donante=" + donante + ", fecha_de_donacion=" + fecha_de_donacion + ", cantidad_sangre=" + cantidad_sangre + ", tipo_donacion=" + tipo_donacion + '}';
    }
    private int id_donacion;
    private int donante;
    private String fecha_de_donacion;
    private double cantidad_sangre;
    private String tipo_donacion;

    // Constructor
    public Donacion(int id_donacion, int donante, String fecha_de_donacion, double cantidad_sangre, String tipo_donacion) {
        this.id_donacion = id_donacion;
        this.donante = donante;
        this.fecha_de_donacion = fecha_de_donacion;
        this.cantidad_sangre = cantidad_sangre;
        this.tipo_donacion = tipo_donacion;
    }

    // Getters
    public int getId_donacion() {
        return id_donacion;
    }

    public int getDonante() {
        return donante;
    }

    public String getFecha_de_donacion() {
        return fecha_de_donacion;
    }

    public double getCantidad_sangre() {
        return cantidad_sangre;
    }

    public String getTipo_donacion() {
        return tipo_donacion;
    }
}
