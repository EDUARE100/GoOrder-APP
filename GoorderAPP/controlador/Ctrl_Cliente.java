package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Cliente;

public class Ctrl_Cliente {

    // Metodo para registrar cliente
  public boolean guardar(Cliente cliente) {
    boolean respuesta = false;
    Connection cn = Conexion.conectar();
    String sql = "INSERT INTO tb_cliente (nombre, apellido, telefono,  numero_mesa) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pst = cn.prepareStatement(sql)) {
        pst.setString(1, cliente.getNombre());
        pst.setString(2, cliente.getApellido());
        pst.setString(3, cliente.getTelefono());
        pst.setInt(4, cliente.getMesa()); 

        if (pst.executeUpdate() > 0) {
            respuesta = true;
        }
    } catch (SQLException e) {
        System.err.println("Error al guardar cliente: " + e);
    }
    return respuesta;
}


   public boolean actualizar(Cliente cliente, int id) {
    boolean respuesta = false;
    Connection cn = Conexion.conectar();
    String sql = "UPDATE tb_cliente SET nombre=?, apellido=?, telefono=?, numero_mesa=? WHERE idCliente=?";

    try (PreparedStatement pst = cn.prepareStatement(sql)) {
        pst.setString(1, cliente.getNombre());
        pst.setString(2, cliente.getApellido());
        pst.setString(3, cliente.getTelefono());
        pst.setInt(4, cliente.getMesa()); // Nueva columna
        pst.setInt(5, id);

        if (pst.executeUpdate() > 0) {
            respuesta = true;
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar cliente: " + e);
    }
    return respuesta;
}

    // Metodo para eliminar cliente
    public boolean eliminar(int id) {
        boolean respuesta = false;
        String sql = "delete from tb_cliente where id = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement consulta = cn.prepareStatement(sql)) {
            consulta.setInt(1, id);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
        return respuesta;
    }
      public boolean eliminarPorMesa(int numeroMesa) {
    boolean respuesta = false;
    String sql = "DELETE FROM tb_cliente WHERE numero_mesa = ?";

    try (Connection cn = Conexion.conectar();
         PreparedStatement consulta = cn.prepareStatement(sql)) {
        consulta.setInt(1, numeroMesa);

        if (consulta.executeUpdate() > 0) {
            respuesta = true;
        }
    } catch (SQLException e) {
        System.out.println("Error al eliminar cliente por mesa: " + e);
    }
    return respuesta;
}
}