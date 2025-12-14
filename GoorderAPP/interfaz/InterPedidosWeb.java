package interfaz;

import conexion.Conexion;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class InterPedidosWeb extends JInternalFrame {

    private JTable tablaPedidos;
    private JTable tablaDetalles;
    private JScrollPane scrollPedidos;
    private JScrollPane scrollDetalles;
    private JButton btnActualizar;
    private JButton btnAtender;
    private int idPedidoSeleccionado = -1;

    public InterPedidosWeb() {
        this.setSize(1000, 600);
        this.setTitle("Centro de Notificaciones - Pedidos Web");
        this.setResizable(false);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setLayout(null);

        // --- TITULOS ---
        JLabel lblPedidos = new JLabel("Pedidos Pendientes:");
        lblPedidos.setBounds(20, 10, 200, 20);
        this.add(lblPedidos);

        JLabel lblDetalles = new JLabel("Detalle del Pedido Seleccionado:");
        lblDetalles.setBounds(20, 280, 250, 20);
        this.add(lblDetalles);

        // --- TABLA PEDIDOS (ARRIBA) ---
        tablaPedidos = new JTable();
        scrollPedidos = new JScrollPane(tablaPedidos);
        scrollPedidos.setBounds(20, 40, 940, 230);
        this.add(scrollPedidos);

        // Evento al hacer clic en un pedido para ver detalles
        tablaPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaPedidos.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    idPedidoSeleccionado = Integer.parseInt(tablaPedidos.getValueAt(fila, 0).toString());
                    CargarTablaDetalles(idPedidoSeleccionado);
                }
            }
        });

        // --- TABLA DETALLES (ABAJO) ---
        tablaDetalles = new JTable();
        scrollDetalles = new JScrollPane(tablaDetalles);
        scrollDetalles.setBounds(20, 310, 940, 180);
        this.add(scrollDetalles);

        // --- BOTONES ---
        btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.setBounds(650, 510, 150, 40);
        btnActualizar.setBackground(Color.cyan);
        btnActualizar.addActionListener(e -> CargarTablaPedidos());
        this.add(btnActualizar);

        btnAtender = new JButton("Despachar Pedido");
        btnAtender.setBounds(810, 510, 150, 40);
        btnAtender.setBackground(Color.green);
        btnAtender.addActionListener(e -> AtenderPedido());
        this.add(btnAtender);

        // Cargar datos al abrir
        CargarTablaPedidos();
    }

    // --- METODO PARA CARGAR PEDIDOS PENDIENTES ---
    private void CargarTablaPedidos() {
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        
        // Columnas
        model.addColumn("ID Pedido");
        model.addColumn("Cliente");
        model.addColumn("Fecha");
        model.addColumn("Dirección");
        model.addColumn("Total");
        model.addColumn("Estado");

        // SQL: Unimos tb_pedido_web con tb_usuario_web para ver el nombre del cliente
        String sql = "SELECT p.idPedidoWeb, u.nombre, p.fecha_pedido, p.direccion_entrega, p.total_pagar, p.estado " +
                     "FROM tb_pedido_web p " +
                     "JOIN tb_usuario_web u ON p.idClienteWeb = u.idClienteweb " +
                     "WHERE p.estado = 'PENDIENTE' ORDER BY p.fecha_pedido DESC";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("idPedidoWeb");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("fecha_pedido");
                fila[3] = rs.getString("direccion_entrega");
                fila[4] = "$" + rs.getDouble("total_pagar");
                fila[5] = rs.getString("estado");
                model.addRow(fila);
            }
            tablaPedidos.setModel(model);
            con.close();
            
            // Limpiar tabla de detalles si se actualiza
            DefaultTableModel modeloVacio = new DefaultTableModel();
            tablaDetalles.setModel(modeloVacio);
            idPedidoSeleccionado = -1;

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de pedidos: " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar pedidos.");
        }
    }

    // --- METODO PARA CARGAR DETALLES DEL PEDIDO ---
    private void CargarTablaDetalles(int idPedido) {
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        model.addColumn("Precio Unit.");
        model.addColumn("Subtotal");

        String sql = "SELECT pr.nombre, d.cantidad, d.precio_unitario, d.subtotal " +
                     "FROM tb_detalle_pedido_web d " +
                     "JOIN tb_producto pr ON d.idProducto = pr.idProducto " +
                     "WHERE d.idPedidoWeb = '" + idPedido + "'";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getInt("cantidad");
                fila[2] = "$" + rs.getDouble("precio_unitario");
                fila[3] = "$" + rs.getDouble("subtotal");
                model.addRow(fila);
            }
            tablaDetalles.setModel(model);
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar detalles: " + e);
        }
    }

    // --- METODO PARA CAMBIAR ESTADO A 'EN_CAMINO' O 'FINALIZADO' ---
    private void AtenderPedido() {
        if (idPedidoSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un pedido primero.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Marcar pedido como DESPACHADO?");
        if (confirmacion == 0) {
            try {
                Connection con = Conexion.conectar();
                PreparedStatement pst = con.prepareStatement(
                        "UPDATE tb_pedido_web SET estado = 'EN CAMINO' WHERE idPedidoWeb = ?");
                
                pst.setInt(1, idPedidoSeleccionado);
                int resultado = pst.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Pedido actualizado correctamente.");
                    CargarTablaPedidos(); // Recargar la lista
                }
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al actualizar pedido: " + e);
            }
        }
    }
}