package controlador;

import conexion.Conexion;
import modelo.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ctrl_Mesas {
    
    // Metodo para obtener todas las mesas
    public List<Mesa> obtenerMesas() {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT numero_mesa, estado FROM tb_mesas";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                mesas.add(new Mesa(rs.getInt("numero_mesa"), rs.getString("estado")));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener mesas: " + e);
        }
        return mesas;
    }

    // Metodo para obtener el estado de una mesa
    public String obtenerEstadoMesa(int numero_Mesa) {
        String estado = "libre";
        String sql = "SELECT estado FROM tb_mesas WHERE numero_mesa = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, numero_Mesa);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    estado = rs.getString("estado");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener estado de la mesa: " + e);
        }
        return estado;
    }

    // Metodo para verificar si hay un cliente con el número de mesa
    private boolean hayClienteEnMesa(int numero_Mesa) {
        String sql = "SELECT COUNT(*) FROM tb_cliente WHERE numero_mesa = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, numero_Mesa);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Si hay clientes en la mesa
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar si hay cliente en la mesa: " + e);
        }
        return false;
    }

    // Metodo para actualizar el estado de la mesa basado en si hay cliente o no
    public boolean actualizarEstadoMesaSegunCliente(int numero_Mesa) {
        boolean respuesta = false;
        String nuevoEstado = hayClienteEnMesa(numero_Mesa) ? "ocupada" : "libre";
        String sql = "UPDATE tb_mesas SET estado = ? WHERE numero_mesa = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, nuevoEstado);
            pst.setInt(2, numero_Mesa);
            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado de la mesa: " + e);
        }
        return respuesta;
    }
}