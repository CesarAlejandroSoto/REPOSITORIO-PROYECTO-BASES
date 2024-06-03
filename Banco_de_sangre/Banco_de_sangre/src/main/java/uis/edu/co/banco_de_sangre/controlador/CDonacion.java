
package uis.edu.co.banco_de_sangre.controlador;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uis.edu.co.banco_de_sangre.modelo.Donacion;

public class CDonacion {

    public void cargarTablaDonaciones(JTable tabla) {
     DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Donación");
    model.addColumn("ID Donante");
    model.addColumn("Nombre");
    model.addColumn("Apellido");
    model.addColumn("Grupo Sanguíneo");
    model.addColumn("Fecha de Donación");
    model.addColumn("Cantidad de Sangre");
    model.addColumn("Tipo de Donacion");

    try {
        Statement st = Conexion.getConect().createStatement();
        ResultSet rs = st.executeQuery(
            "SELECT donaciones.id_donacion, donantes.id_donante, donantes.nombre, donantes.apellido, " +
            "grupo_sanguineo.tipo_grupo_sanguineo, donaciones.fecha_de_donacion, donaciones.cantidad_sangre, donaciones.tipo_donacion " +
            "FROM donantes " +
            "INNER JOIN grupo_sanguineo ON donantes.grupo_sanguineo_id = grupo_sanguineo.id_grupo_sanguineo " +
            "INNER JOIN donaciones ON donantes.id_donante = donaciones.donante " +
            "ORDER BY donaciones.id_donacion ASC"
        );

        while (rs.next()) {
            String[] fila = {
                rs.getString("id_donacion"),
                rs.getString("id_donante"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("tipo_grupo_sanguineo"),
                rs.getString("fecha_de_donacion"),
                rs.getString("cantidad_sangre"),
                rs.getString("tipo_donacion")
            };
            model.addRow(fila);
        }
        tabla.setModel(model);

    } catch (SQLException ex) {
        Logger.getLogger(CDonacion.class.getName()).log(Level.SEVERE, null, ex);
    }

    }

    
    public int obtenerProximoIdDonacion() {
        int proximoId = 1;
        try {
            Connection conn = Conexion.getConect();
            Statement st = conn.createStatement();
            String query = "SELECT MAX(id_donacion) FROM donaciones";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                proximoId = rs.getInt(1) + 1; 
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(CDonacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proximoId;
    }


    public void registrardonaciones(Donacion d) {

    try {
        Connection conn = Conexion.getConect();
        String query = "INSERT INTO donaciones (donante, fecha_de_donacion, cantidad_sangre, tipo_donacion) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, d.getDonante());
        ps.setString(2, d.getFecha_de_donacion());
        ps.setDouble(3, d.getCantidad_sangre());
        ps.setString(4, d.getTipo_donacion());
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Donación registrada correctamente.");
    } catch (SQLException ex) {
        Logger.getLogger(CDonacion.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al registrar la donación: " + ex.getMessage());
    }
    }

 
    public void modificarDonacion(Donacion d) {
        try {
            Connection conn = Conexion.getConect();
            String query = "UPDATE donaciones SET donante = ?, fecha_de_donacion = ?, cantidad_sangre = ?, tipo_donacion = ? WHERE id_donacion = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, d.getDonante());
            ps.setString(2, d.getFecha_de_donacion());
            ps.setDouble(3, d.getCantidad_sangre());
            ps.setString(4, d.getTipo_donacion());
            ps.setInt(5, d.getId_donacion());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Donación modificada correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(CDonacion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al modificar la donación: " + ex.getMessage());
        }
    }

 
    public boolean eliminarDonacion(int idDonacion) {
     try {
        Connection conn = Conexion.getConect();
        String query = "DELETE FROM donaciones WHERE id_donacion = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, idDonacion);
        int rowsDeleted = ps.executeUpdate();
        ps.close();

        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(null, "Donación eliminada correctamente.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la donación. Verifique que el ID proporcionado sea correcto.");
            return false;
        }
    } catch (SQLException ex) {
        Logger.getLogger(CDonacion.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al eliminar la donación: " + "Dirijase a unidades de sangre para respetar las foraneas");
        return false;
    }
    }

    public void consultarTablaDonacionesPorDonante(JTable tabla, int idDonante) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID Donación");
    model.addColumn("ID Donante");
    model.addColumn("Nombre");
    model.addColumn("Apellido");
    model.addColumn("Grupo Sanguíneo");
    model.addColumn("Fecha de Donación");

    try {
        Connection conn = Conexion.getConect();
        String query = "SELECT donaciones.id_donacion, donantes.id_donante, donantes.nombre, donantes.apellido, grupo_sanguineo.tipo_grupo_sanguineo, donaciones.fecha_de_donacion FROM donantes INNER JOIN grupo_sanguineo ON donantes.grupo_sanguineo_id = grupo_sanguineo.id_grupo_sanguineo INNER JOIN donaciones ON donantes.id_donante = donaciones.donante WHERE donantes.id_donante = ? ORDER BY donaciones.id_donacion ASC";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, idDonante);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String[] fila = {
                rs.getString("id_donacion"), 
                rs.getString("id_donante"), 
                rs.getString("nombre"), 
                rs.getString("apellido"), 
                rs.getString("tipo_grupo_sanguineo"), 
                rs.getString("fecha_de_donacion")
            };
            model.addRow(fila);
        }
        tabla.setModel(model);
        
        ps.close();
        rs.close();
    } catch (SQLException ex) {
        Logger.getLogger(CDonacion.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void registrarDonanteYDonacion(String nombre, String apellido, int grupo_sanguineo_id, String fecha_ultima_donacion, String fecha_de_nacimiento, String fecha_de_donacion, double cantidad_sangre, String tipo_donacion) {
    try {
        Connection conn = Conexion.getConect();
        String query = "{CALL registrarDonanteYDonacion(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(query);
        cs.setString(1, nombre);
        cs.setString(2, apellido);
        cs.setInt(3, grupo_sanguineo_id);
        cs.setString(4, fecha_ultima_donacion);
        cs.setString(5, fecha_de_nacimiento);
        cs.setString(6, fecha_de_donacion);
        cs.setDouble(7, cantidad_sangre);
        cs.setString(8, tipo_donacion);
        cs.execute();
        cs.close();
        JOptionPane.showMessageDialog(null, "Donante y donación registrados correctamente.");
    } catch (SQLException ex) {
        Logger.getLogger(CDonacion.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al registrar el donante y la donación: " );
    }
}
    public void consultarDonacionesPorFechas(JTable tabla, String fechaInicio, String fechaFin) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Donación");
        model.addColumn("ID Donante");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Grupo Sanguíneo");
        model.addColumn("Fecha de Donación");
        model.addColumn("Cantidad de Sangre");

        try {
            Statement st = Conexion.getConect().createStatement();
            String query = "SELECT donaciones.id_donacion, donantes.id_donante, donantes.nombre, donantes.apellido, grupo_sanguineo.tipo_grupo_sanguineo, donaciones.fecha_de_donacion, donaciones.cantidad_sangre " +
                           "FROM donantes " +
                           "INNER JOIN grupo_sanguineo ON donantes.grupo_sanguineo_id = grupo_sanguineo.id_grupo_sanguineo " +
                           "INNER JOIN donaciones ON donantes.id_donante = donaciones.donante " +
                           "WHERE donaciones.fecha_de_donacion BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' " +
                           "ORDER BY donaciones.id_donacion ASC";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String[] fila = {
                    rs.getString("id_donacion"), 
                    rs.getString("id_donante"), 
                    rs.getString("nombre"), 
                    rs.getString("apellido"), 
                    rs.getString("tipo_grupo_sanguineo"), 
                    rs.getString("fecha_de_donacion"),
                    rs.getString("cantidad_sangre")
                };
                model.addRow(fila);
            }
            tabla.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(CDonacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}




