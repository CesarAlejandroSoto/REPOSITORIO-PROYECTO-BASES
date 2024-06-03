
package uis.edu.co.banco_de_sangre.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import uis.edu.co.banco_de_sangre.modelo.UnidadesDeSangre;

public class CUnidadesDeSangre {
    public UnidadesDeSangre consultarUnidadDeSangrePorId(int idUnidad) {
    try {
        
        String sql = "SELECT * FROM unidades_de_sangre WHERE id_unidades_sangre = ?";
        
        
        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
        statement.setInt(1, idUnidad); 
        
        
        ResultSet resultSet = statement.executeQuery();
        
        
        if (resultSet.next()) {
            
            int id = resultSet.getInt("id_unidades_sangre");
            int donacion = resultSet.getInt("donacion");
            int tipoComponenteId = resultSet.getInt("tipo_componente_sanguineo_id");
            int estadoId = resultSet.getInt("estado_unidades_id");
            String fechaProcesamiento = resultSet.getString("fecha_procesamiento");
            
            
            return new UnidadesDeSangre(id, donacion, tipoComponenteId, estadoId, fechaProcesamiento);
        } else {
            
            return null;
        }
    } catch (SQLException ex) {
        Logger.getLogger(CUnidadesDeSangre.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al consultar la unidad de sangre por ID: " + ex.getMessage());
        return null;
    }
}
public void cargarUnidadEnTabla(JTable tabla, UnidadesDeSangre unidad) {
    DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
    modelo.setRowCount(0); 

    modelo.setColumnIdentifiers(new Object[]{"ID de la Unidad", "Donación", "Tipo Componente", "Estado", "Fecha Procesamiento"});

    if (unidad != null) {
       
        int id = unidad.getIdUnidadesSangre();
        int donacion = unidad.getDonacion();
        String tipoComponente = obtenerTipoComponenteSanguineo(unidad.getTipoComponenteSanguineoId());
        String estado = obtenerEstadoUnidades(unidad.getEstadoUnidadesId());
        String fechaProcesamiento = unidad.getFechaProcesamiento();

       
        modelo.addRow(new Object[]{id, donacion, tipoComponente, estado, fechaProcesamiento});
    }
}

public String obtenerTipoComponenteSanguineo(int tipoComponenteId) {
    try {
        
        String sql = "SELECT nombre_tipo_componente FROM tipo_componente_sanguineo WHERE id_tipo_componente = ?";
        
        
        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
        statement.setInt(1, tipoComponenteId); 
        
        
        ResultSet resultSet = statement.executeQuery();
        
       
        if (resultSet.next()) {
           
            return resultSet.getString("nombre_tipo_componente");
        } else {
            
            return "Tipo de componente no encontrado";
        }
    } catch (SQLException ex) {
        Logger.getLogger(CUnidadesDeSangre.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al obtener el tipo de componente sanguíneo: " + ex.getMessage());
        return null;
    }
}

public String obtenerEstadoUnidades(int estadoId) {
    try {
        
        String sql = "SELECT estado FROM estado_unidades WHERE id_estado_unidades = ?";
        
       
        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
        statement.setInt(1, estadoId); 
        
       
        ResultSet resultSet = statement.executeQuery();
        
        
        if (resultSet.next()) {
            
            return resultSet.getString("estado");
        } else {
            
            return "Estado de unidades no encontrado";
        }
    } catch (SQLException ex) {
        Logger.getLogger(CUnidadesDeSangre.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al obtener el estado de las unidades: " + ex.getMessage());
        return null;
    }
}

 
    public boolean registrarUnidadDeSangre(UnidadesDeSangre unidad) {
        try {
            Connection connection = Conexion.getConect();
            String sql = "INSERT INTO unidades_de_sangre (donacion, tipo_componente_sanguineo_id, estado_unidades_id, fecha_procesamiento) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, unidad.getDonacion());
            statement.setInt(2, unidad.getTipoComponenteSanguineoId());
            statement.setInt(3, unidad.getEstadoUnidadesId());
            statement.setString(4, unidad.getFechaProcesamiento());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar la unidad de sangre: " + ex.getMessage());
            return false;
        }
    }

 
public void cargarTodasLasUnidadesEnTabla(JTable tblUnidades) {
    try {
        Connection connection = Conexion.getConect();
        String sql = "SELECT us.id_unidades_sangre, d.nombre, d.apellido, tcs.nombre_tipo_componente, eu.estado, us.fecha_procesamiento " +
                     "FROM unidades_de_sangre us " +
                     "JOIN donantes d ON us.donacion = d.id_donante " +
                     "JOIN tipo_componente_sanguineo tcs ON us.tipo_componente_sanguineo_id = tcs.id_tipo_componente " +
                     "JOIN estado_unidades eu ON us.estado_unidades_id = eu.id_estado_unidades " +
                     "ORDER BY us.id_unidades_sangre"; 
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        DefaultTableModel modelo = (DefaultTableModel) tblUnidades.getModel();
        modelo.setRowCount(0); 

        while (resultSet.next()) {
            int id = resultSet.getInt("id_unidades_sangre");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String tipoComponente = resultSet.getString("nombre_tipo_componente");
            String estado = resultSet.getString("estado");
            String fechaProcesamiento = resultSet.getString("fecha_procesamiento");

            modelo.addRow(new Object[]{id, nombre, apellido, tipoComponente, estado, fechaProcesamiento});
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar las unidades de sangre: " + ex.getMessage());
    }
}

    
    

public boolean eliminarUnidadDeSangre(int idUnidad) {
    try {
        
        String sql = "DELETE FROM unidades_de_sangre WHERE id_unidades_sangre = ?";
        

        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
        statement.setInt(1, idUnidad); // Establecer el ID de la unidad como parámetro
       
        int rowsDeleted = statement.executeUpdate();
        
       
        if (rowsDeleted > 0) {
            // Si se eliminó correctamente, cargar nuevamente todas las unidades en la tabla
            
            return true;
        } else {
            return false;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar la unidad de sangre: " + "Dirijase a analisis de sangre para respetar las foraneas");
        return false;
    }
}

public void cargarTodasLasUnidadesDEnTabla(JTable tblUnidades) {
try {
        Connection connection = Conexion.getConect();
        String sql = "SELECT us.id_unidades_sangre, don.nombre, don.apellido, tcs.nombre_tipo_componente, eu.estado, us.fecha_procesamiento " +
                     "FROM unidades_de_sangre us " +
                     "JOIN donantes don ON us.donacion = don.id_donante " +
                     "JOIN tipo_componente_sanguineo tcs ON us.tipo_componente_sanguineo_id = tcs.id_tipo_componente " +
                     "JOIN estado_unidades eu ON us.estado_unidades_id = eu.id_estado_unidades " +
                     "WHERE eu.estado = 'Disponible' " + 
                     "ORDER BY us.id_unidades_sangre"; 
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        DefaultTableModel modelo = (DefaultTableModel) tblUnidades.getModel();
        modelo.setRowCount(0); 

        while (resultSet.next()) {
            int id = resultSet.getInt("id_unidades_sangre");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String tipoComponente = resultSet.getString("nombre_tipo_componente");
            String estado = resultSet.getString("estado");
            String fechaProcesamiento = resultSet.getString("fecha_procesamiento");

            modelo.addRow(new Object[]{id, nombre, apellido, tipoComponente, estado, fechaProcesamiento});
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar las unidades de sangre disponibles: " + ex.getMessage());
    }
}
public void cargarDonantesEnTabla(JTable tabla) {
    DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
    modelo.setRowCount(0); 
    
    modelo.setColumnIdentifiers(new Object[]{"ID Donante", "Nombre", "Apellido", "Documento", "Grupo Sanguíneo", "Última Donación", "Fecha de Nacimiento"});

    try {
       
        String sql = "SELECT * FROM donantes";
        
       
        PreparedStatement statement = Conexion.getConect().prepareStatement(sql);
        
        
        ResultSet resultSet = statement.executeQuery();
        
       
        while (resultSet.next()) {
            int id = resultSet.getInt("id_donante");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String documento = resultSet.getString("documento");
            int grupoSanguineoId = resultSet.getInt("grupo_sanguineo_id");
            String fechaUltimaDonacion = resultSet.getString("fecha_ultima_donacion");
            String fechaNacimiento = resultSet.getString("fecha_de_nacimiento");
           
            modelo.addRow(new Object[]{id, nombre, apellido, documento, grupoSanguineoId, fechaUltimaDonacion, fechaNacimiento});
        }
    } catch (SQLException ex) {
        Logger.getLogger(CUnidadesDeSangre.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al cargar los donantes en la tabla: " + ex.getMessage());
    }
}
public void cargarUnidadesEnProcesoDeAnalisisYDistribucion(JTable tblUnidades) {
    try {
        Connection connection = Conexion.getConect();
        
        
        String sql = "SELECT us.id_unidades_sangre, don.nombre, don.apellido, tcs.nombre_tipo_componente, eu.estado, us.fecha_procesamiento " +
                     "FROM unidades_de_sangre us " +
                     "JOIN donantes don ON us.donacion = don.id_donante " +
                     "JOIN tipo_componente_sanguineo tcs ON us.tipo_componente_sanguineo_id = tcs.id_tipo_componente " +
                     "JOIN estado_unidades eu ON us.estado_unidades_id = eu.id_estado_unidades " +
                     "WHERE eu.estado IN ('En proceso de análisis', 'En proceso de distribución') " + 
                     "ORDER BY us.id_unidades_sangre"; 
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        
        DefaultTableModel modelo = (DefaultTableModel) tblUnidades.getModel();
        modelo.setRowCount(0);

        
        while (resultSet.next()) {
            int id = resultSet.getInt("id_unidades_sangre");
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String tipoComponente = resultSet.getString("nombre_tipo_componente");
            String estado = resultSet.getString("estado");
            String fechaProcesamiento = resultSet.getString("fecha_procesamiento");

            modelo.addRow(new Object[]{id, nombre, apellido, tipoComponente, estado, fechaProcesamiento});
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al cargar las unidades en proceso de análisis y distribución: " + ex.getMessage());
    }
}

}
