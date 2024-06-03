
package uis.edu.co.banco_de_sangre.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uis.edu.co.banco_de_sangre.modelo.Distribucion;


public class CDistribucion {
        public void consultarDistribucionPorId(JTable tblDistribuciones, int idDistribucion) {
    try {
        Connection connection = Conexion.getConect();
        String sql = "SELECT d.id_distribucion, u.id_unidades_sangre, de.nombre_destino, d.fecha_distribucion " +
                     "FROM distribucion d " +
                     "JOIN unidades_de_sangre u ON d.unidad = u.id_unidades_sangre " +
                     "JOIN destino de ON d.destino_id = de.id_destino " +
                     "WHERE d.id_distribucion = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idDistribucion);
        ResultSet resultSet = statement.executeQuery();

        DefaultTableModel modelo = (DefaultTableModel) tblDistribuciones.getModel();
        modelo.setRowCount(0); 
        
        modelo.setColumnIdentifiers(new Object[]{"ID Distribución", "ID Unidad", "Destino", "Fecha de Distribución"});

        while (resultSet.next()) {
            int id = resultSet.getInt("id_distribucion");
            int unidad = resultSet.getInt("id_unidades_sangre");
            String destino = resultSet.getString("nombre_destino");
            String fechaDistribucion = resultSet.getString("fecha_distribucion");

            modelo.addRow(new Object[]{id, unidad, destino, fechaDistribucion});
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al consultar la distribución: " + ex.getMessage());
    }
}

public boolean registrarDistribucion(Distribucion distribucion) {
        try {
            Connection connection = Conexion.getConect();
            String sql = "INSERT INTO distribucion (unidad, destino_id, fecha_distribucion) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, distribucion.getUnidad());
            statement.setInt(2, distribucion.getDestinoId());
            statement.setString(3, distribucion.getFechaDistribucion());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar la distribución: " + ex.getMessage());
            return false;
        }
    }

public boolean eliminarDistribucion(int idDistribucion) {
    try {
        Connection connection = Conexion.getConect();
        String sql = "DELETE FROM distribucion WHERE id_distribucion = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idDistribucion);

        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar la distribución: " + ex.getMessage());
        return false;
    }
}

   public ArrayList<String[]> consultarDestinos() {
    ArrayList<String[]> destinos = new ArrayList<>();
    try {
        Connection connection = Conexion.getConect();
        String sql = "SELECT * FROM destino ORDER BY id_destino";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int idDestino = resultSet.getInt("id_destino");
            String nombreDestino = resultSet.getString("nombre_destino");
            destinos.add(new String[]{String.valueOf(idDestino), nombreDestino});
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al consultar los destinos: " + ex.getMessage());
    }
    return destinos;
}
public void CargarDistribucion(JTable tabla){
      DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Distribución");
        model.addColumn("Unidad");
        model.addColumn("Destino");
        model.addColumn("Fecha Distribución");

        String query = "SELECT d.id_distribucion, d.unidad, dest.nombre_destino, d.fecha_distribucion " +
                       "FROM distribucion d " +
                       "INNER JOIN destino dest ON d.destino_id = dest.id_destino " +
                       "ORDER BY d.id_distribucion ASC";

        try (Connection conn = Conexion.getConect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String[] fila = {
                    rs.getString("id_distribucion"),
                    rs.getString("unidad"),
                    rs.getString("nombre_destino"),
                    rs.getString("fecha_distribucion")
                };
                model.addRow(fila);
            }
            tabla.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
