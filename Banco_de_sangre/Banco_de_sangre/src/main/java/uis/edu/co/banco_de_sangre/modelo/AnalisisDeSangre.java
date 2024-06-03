
package uis.edu.co.banco_de_sangre.modelo;


public class AnalisisDeSangre {
    private int idAnalisis;
    private int unidad;
    private String fechaAnalisis; 
    private String resultadoAnalisis;

    @Override
    public String toString() {
        return "AnalisisDeSangre{" + "idAnalisis=" + idAnalisis + ", unidad=" + unidad + ", fechaAnalisis=" + fechaAnalisis + ", resultadoAnalisis=" + resultadoAnalisis + '}';
    }

    public AnalisisDeSangre(int idAnalisis, int unidad, String fechaAnalisis, String resultadoAnalisis) {
        this.idAnalisis = idAnalisis;
        this.unidad = unidad;
        this.fechaAnalisis = fechaAnalisis;
        this.resultadoAnalisis = resultadoAnalisis;
    }


    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public String getFechaAnalisis() {
        return fechaAnalisis;
    }

    public void setFechaAnalisis(String fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    public String getResultadoAnalisis() {
        return resultadoAnalisis;
    }

    public void setResultadoAnalisis(String resultadoAnalisis) {
        this.resultadoAnalisis = resultadoAnalisis;
    }
}
