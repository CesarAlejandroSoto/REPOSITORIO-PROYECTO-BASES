
package uis.edu.co.banco_de_sangre.controlador;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uis.edu.co.banco_de_sangre.modelo.AnalisisDeSangre;





public class CAnalisisDeSangre {
    
    public void cargarTablaAnalisis(JTable tabla) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Análisis");
        model.addColumn("Unidad");
        model.addColumn("Fecha Análisis");
        model.addColumn("Resultado Análisis");

        try {
            Statement st = Conexion.getConect().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM analisis_de_sangre");

            while (rs.next()) {
                String[] fila = {
                    rs.getString("id_analisis_de_sangre"),
                    rs.getString("unidad"),
                    rs.getString("fecha_analisis"),
                    rs.getString("resultado_analisis")
                };
                model.addRow(fila);
            }
            tabla.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void consultarAnalisisPorId(JTable tabla, String idAnalisis) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Análisis");
        model.addColumn("Unidad");
        model.addColumn("Fecha Análisis");
        model.addColumn("Resultado Análisis");

        try {
            Statement st = Conexion.getConect().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM analisis_de_sangre WHERE id_analisis_de_sangre = '" + idAnalisis + "'");

            while (rs.next()) {
                String[] fila = {
                    rs.getString("id_analisis_de_sangre"),
                    rs.getString("unidad"),
                    rs.getString("fecha_analisis"),
                    rs.getString("resultado_analisis")
                };
                model.addRow(fila);
            }
            tabla.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    public void consultarAnalisisPorFecha(JTable tabla, String fechaInicio, String fechaFin) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Análisis");
        model.addColumn("Unidad");
        model.addColumn("Fecha Análisis");
        model.addColumn("Resultado Análisis");

        try {
            Statement st = Conexion.getConect().createStatement();
            String query = "SELECT * FROM analisis_de_sangre WHERE fecha_analisis BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String[] fila = {
                    rs.getString("id_analisis_de_sangre"),
                    rs.getString("unidad"),
                    rs.getString("fecha_analisis"),
                    rs.getString("resultado_analisis")
                };
                model.addRow(fila);
            }
            tabla.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
     public void registrarAnalisis(String idAnalisis, String unidad, String fechaAnalisis, String resultadoAnalisis) {
        try {
            
            String query = "INSERT INTO analisis_de_sangre (id_analisis_de_sangre, unidad, fecha_analisis, resultado_analisis) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = Conexion.getConect().prepareStatement(query);
            statement.setString(1, idAnalisis);
            statement.setString(2, unidad);
            statement.setString(3, fechaAnalisis);
            statement.setString(4, resultadoAnalisis);

            
            statement.executeUpdate();
            
           
            statement.close();

            
            JOptionPane.showMessageDialog(null, "Análisis registrado correctamente.");
        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "Error al registrar el análisis: " + "Por favor digite una unidad existente o dirijase a unidades y creela antes de ealizar este proceso" );
        }
    }
    
    
     public void modificarAnalisis(String idAnalisis, String unidad, String fechaAnalisis, String resultadoAnalisis) {
    try {
        Connection conn = Conexion.getConect();
        String query = "UPDATE analisis_de_sangre SET unidad = ?, fecha_analisis = ?, resultado_analisis = ? WHERE id_analisis_de_sangre = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, unidad);
        ps.setString(2, fechaAnalisis);
        ps.setString(3, resultadoAnalisis);
        ps.setString(4, idAnalisis);
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Análisis modificado correctamente.");
    } catch (SQLException ex) {
        Logger.getLogger(CAnalisisDeSangre.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al modificar el análisis: " + ex.getMessage());
    }
}

    public boolean eliminarAnalisis(String idAnalisis) {
    try {
        Connection conn = Conexion.getConect();
        String query = "DELETE FROM analisis_de_sangre WHERE id_analisis_de_sangre = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, idAnalisis);
        int rowsDeleted = ps.executeUpdate();
        ps.close();

        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(null, "Análisis eliminado correctamente.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el análisis. Verifique que el ID proporcionado sea correcto.");
            return false;
        }
    } catch (SQLException ex) {
        Logger.getLogger(CAnalisisDeSangre.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al eliminar el análisis: " + ex.getMessage());
        return false;
    }
    }
    
 
    public int contarTotalAnalisis() {
        int total = 0;
        try {
            Connection conn = Conexion.getConect();
            String query = "SELECT COUNT(*) AS total FROM analisis_de_sangre";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CAnalisisDeSangre.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al contar los análisis: " + ex.getMessage());
        }
        return total;
    }
  public int obtenerProximoIdAnalisis() {
        int proximoId = 1; 
        try {
            Connection conn = Conexion.getConect();
            Statement st = conn.createStatement();
            String query = "SELECT MAX(id_analisis_de_sangre) FROM analisis_de_sangre";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                proximoId = rs.getInt(1) + 1; 
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnalisisDeSangre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proximoId;
    }  
    
    
}

    

    