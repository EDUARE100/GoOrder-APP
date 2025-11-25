
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Usuario;


public class Ctrl_Usuario {
    
    public boolean loginUser(Usuario objeto){
    
       boolean respuesta = false;
       
       Connection cn = Conexion.conectar();
        String sql = "select usuario, password from tb_usuario where usuario = '" + objeto.getUsuario() + "' and password = '" + objeto.getPassword() + "'";
        Statement st;
        
        try{
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                respuesta = true;
            }
            
        }catch (SQLException e){
            System.out.println("Error al iniciar sesión");
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
        }
        return respuesta;
}
  public boolean guardar(Usuario usuario) {
    boolean respuesta = false;
    Connection cn = Conexion.conectar();
    String sql = "INSERT INTO tb_usuario (nombre, apellido, usuario, password, telefono) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement pst = cn.prepareStatement(sql)) {
        pst.setString(1, usuario.getNombre());
        pst.setString(2, usuario.getApellido());
        pst.setString(3, usuario.getUsuario());
        pst.setString(4, usuario.getPassword());
        pst.setString(5, usuario.getTelefono());
       

        if (pst.executeUpdate() > 0) {
            respuesta = true;
        }
    } catch (SQLException e) {
        System.err.println("Error al guardar cliente: " + e);
    }
    return respuesta;
}   
    public boolean eliminar(int id) {
        boolean respuesta = false;
        String sql = "delete from tb_usuario where idUsuario = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement consulta = cn.prepareStatement(sql)) {
            consulta.setInt(1, id);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Usuario eliminado: " + e);
        }
        return respuesta;
    }
  public boolean actualizar(Usuario objeto, int idUsuario) {
    boolean respuesta = false;
    Connection cn = Conexion.conectar();
    try {
        // Consulta preparada sin concatenación de variables
        String query = "UPDATE tb_usuario SET usuario = ?, password = ?, telefono = ? WHERE idUsuario = ?";
        PreparedStatement consulta = cn.prepareStatement(query);

        // Asignar valores a los parámetros
        consulta.setString(1, objeto.getUsuario());
        consulta.setString(2, objeto.getPassword());
        consulta.setString(3, objeto.getTelefono());
        consulta.setInt(4, idUsuario); // El índice 4 corresponde a "idUsuario"

        // Ejecutar la consulta
        if (consulta.executeUpdate() > 0) {
            respuesta = true; // Actualización exitosa
        }
    } catch (SQLException e) {
        System.out.println("Error al actualizar usuario: " + e.getMessage());
    } finally {
        try {
            if (cn != null) {
                cn.close(); // Asegurar cierre de conexión
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    return respuesta;
}
}
