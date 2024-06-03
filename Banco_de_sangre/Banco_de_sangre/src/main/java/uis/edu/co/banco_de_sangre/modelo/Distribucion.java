
package uis.edu.co.banco_de_sangre.modelo;


public class Distribucion {
   
    private int idDistribucion;
    private int unidad;
    private int destinoId;
    private String fechaDistribucion;

    public Distribucion(int idDistribucion, int unidad, int destinoId, String fechaDistribucion) {
        this.idDistribucion = idDistribucion;
        this.unidad = unidad;
        this.destinoId = destinoId;
        this.fechaDistribucion = fechaDistribucion;
    }

    public int getIdDistribucion() {
        return idDistribucion;
    }

    public void setIdDistribucion(int idDistribucion) {
        this.idDistribucion = idDistribucion;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoId) {
        this.destinoId = destinoId;
    }

    public String getFechaDistribucion() {
        return fechaDistribucion;
    }

    public void setFechaDistribucion(String fechaDistribucion) {
        this.fechaDistribucion = fechaDistribucion;
    }

    @Override
    public String toString() {
        return "Distribucion{" + "idDistribucion=" + idDistribucion + ", unidad=" + unidad + ", destinoId=" + destinoId + ", fechaDistribucion=" + fechaDistribucion + '}';
    }
    
    
}
