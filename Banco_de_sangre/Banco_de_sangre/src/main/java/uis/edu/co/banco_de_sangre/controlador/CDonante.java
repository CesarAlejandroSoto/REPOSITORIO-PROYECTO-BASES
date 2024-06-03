
package uis.edu.co.banco_de_sangre.controlador;

import com.sun.jdi.connect.spi.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uis.edu.co.banco_de_sangre.modelo.Donante;

public class CDonante {
    public void registrarDonantes(Donante don) {
        try {
            Statement st = Conexion.getConect().createStatement();
            st.execute("INSERT INTO donantes (nombre, apellido, documento, grupo_sanguineo_id, fecha_ultima_donacion, fecha_de_nacimiento) VALUES ('" 
               + don.getNombre() + "','" + don.getApellido() + "', '" + don.getDocumento() + "','"
               + don.getGrupoSanguineoId() + "', '" + don.getFechaUltimaDonacion() + "','" + don.getFechaDeNacimiento() + "')");
            
            JOptionPane.showMessageDialog(null, "REGISTRADO");
       
        } catch (SQLException ex) {
            Logger.getLogger(CDonante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR: " + ex.getMessage());
        }
    }

    
    public ResultSet consultarDonantes(){
        try {
            Statement st = Conexion.getConect().createStatement();
            return st.executeQuery("SELECT * FROM donantes");
        } catch (SQLException ex) {
            Logger.getLogger(CDonante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DONANTES EN LA TABLA"+ex.getMessage());
        }
        return null;
        
    }
    public void cargarDonantesEnTabla(JTable table, Donante donante) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); 
    
    
    if (donante != null) {
        
        model.addRow(new Object[]{
            donante.getIdDonante(),
            donante.getNombre(),
            donante.getApellido(),
            donante.getDocumento(),
            donante.getGrupoSanguineoId(),
            donante.getFechaUltimaDonacion(),
            donante.getFechaDeNacimiento()
        });
    } else {
        JOptionPane.showMessageDialog(null, "No se encontró ningún donante con el ID ingresado.");
        }
    }
    public Donante consultarDonantePorId(int idDonante) {
    try {
        
        String sql = "SELECT * FROM donantes WHERE id_donante = ?";
        
        
        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
        statement.setInt(1, idDonante); 
        
        
        ResultSet resultSet = statement.executeQuery();
        
        
        if (resultSet.next()) {
            
            int id = resultSet.getInt("id_donante");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String documento = resultSet.getString("documento");
            int grupoSanguineoId = resultSet.getInt("grupo_sanguineo_id");
            String fechaUltimaDonacion = resultSet.getString("fecha_ultima_donacion");
            String fechaDeNacimiento = resultSet.getString("fecha_de_nacimiento");
            
            
            return new Donante(id, nombre, apellido, documento, grupoSanguineoId, fechaUltimaDonacion, fechaDeNacimiento);
        } else {
           
            return null;
        }
    } catch (SQLException ex) {
        Logger.getLogger(CDonante.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al consultar el donante por ID: " + ex.getMessage());
        return null;
    }
}
    public void cargarDonanteEnTabla(JTable table, Donante donante) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); 
    
 
    if (donante != null) {
       
        model.addRow(new Object[]{
            donante.getIdDonante(),
            donante.getNombre(),
            donante.getApellido(),
            donante.getDocumento(),
            donante.getGrupoSanguineoId(),
            donante.getFechaUltimaDonacion(),
            donante.getFechaDeNacimiento()
        });
    } else {
        JOptionPane.showMessageDialog(null, "No se encontró ningún donante con el ID ingresado.");
    }
    
}

public void eliminarDonante(int idDonante) {
    try {
       
        String sql = "DELETE FROM donantes WHERE id_donante = ?";
        
        
        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
        statement.setInt(1, idDonante); 
        
        
        int filasEliminadas = statement.executeUpdate();
        
       
        if (filasEliminadas > 0) {
            JOptionPane.showMessageDialog(null, "Donante eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún donante con el ID especificado.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(CDonante.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al eliminar el donante: " + "Dirijase a la tabla analisis si quiere eliminar un donante para respetar las foraneas");
    }
}

public ResultSet consultarDonantesPorGrupoSanguineo(String grupoSanguineo) {
    try {
        String sql = "SELECT d.id_donante, d.nombre, d.apellido, d.documento, g.tipo_grupo_sanguineo AS grupo_sanguineo, d.fecha_ultima_donacion, d.fecha_de_nacimiento "
                   + "FROM donantes d INNER JOIN grupo_sanguineo g ON d.grupo_sanguineo_id = g.id_grupo_sanguineo "
                   + "WHERE g.tipo_grupo_sanguineo = ?";
        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
        statement.setString(1, grupoSanguineo);
        return statement.executeQuery();
    } catch (SQLException ex) {
        Logger.getLogger(CDonante.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "ERROR AL CONSULTAR DONANTES POR GRUPO SANGUÍNEO: " + ex.getMessage());
        return null;
    }
}
public void generarAlarmaDonacionesAntiguas() {
    try {
       
        LocalDate fechaMinimaAntigua = LocalDate.now().minusYears(1);

      
        java.sql.Date fechaMinimaAntiguaSQL = java.sql.Date.valueOf(fechaMinimaAntigua);

        
        String sql = "SELECT * FROM donantes WHERE fecha_ultima_donacion < ?";
        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
       
        statement.setDate(1, fechaMinimaAntiguaSQL);
        ResultSet rs = statement.executeQuery();

        
        StringBuilder mensaje = new StringBuilder("Atencion!! \n Donaciones antiguas: \n Asegurate de que estas muestras sean distribuidas\n");
        while (rs.next()) {
            int idDonante = rs.getInt("id_donante");
            Date fechaDonacion = rs.getDate("fecha_ultima_donacion");
            mensaje.append("ID: ").append(idDonante).append(", Fecha: ").append(fechaDonacion).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());

    } catch (SQLException ex) {
        Logger.getLogger(CDonante.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al generar alarma de donaciones antiguas: " + ex.getMessage());
    }
}

}

    

