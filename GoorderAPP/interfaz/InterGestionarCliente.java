
package interfaz;

import conexion.Conexion;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class InterGestionarCliente extends javax.swing.JInternalFrame {

    private int idCliente;

    public InterGestionarCliente() {
        initComponents();
        this.setSize(new Dimension(920, 500));
        this.setTitle("Gestionar clientes");
        //Cargar tabla
        this.CargarTablaClientes();

    }

    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_productos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton_actualizar = new javax.swing.JButton();
        jButton_eliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Campo_telefono_nombre = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        Campo_telefono_apellido = new javax.swing.JLabel();
        Campo_telefono = new javax.swing.JLabel();
        No_mesa = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        txt_mesa = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        Wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        Titulo.setForeground(new java.awt.Color(0, 0, 0));
        Titulo.setText("Administrar Clientes");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_productos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 710, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 730, 270));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_actualizar.setBackground(new java.awt.Color(51, 204, 0));
        jButton_actualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_actualizar.setText("Actualizar");
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, -1));

        jButton_eliminar.setBackground(new java.awt.Color(255, 51, 51));
        jButton_eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 130, 270));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Campo_telefono_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_telefono_nombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_telefono_nombre.setText("Nombre:");
        jPanel3.add(Campo_telefono_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, -1));

        Campo_telefono_apellido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_telefono_apellido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_telefono_apellido.setText("Apellido:");
        jPanel3.add(Campo_telefono_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, -1));

        Campo_telefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_telefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_telefono.setText("Teléfono");
        jPanel3.add(Campo_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 90, -1));

        No_mesa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        No_mesa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        No_mesa.setText("No° de mesa:");
        jPanel3.add(No_mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 100, -1));

        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 170, -1));

        txt_mesa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 170, -1));

        txt_apellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 870, 100));

         // Cargas la imagen de fondo y la escalas al tamaño de la ventana
        javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpg"));
        java.awt.Image originalImage = originalIcon.getImage();
        
        // 2. Define el tamaño al que quieres escalar (el tamaño de tu ventana)
        int targetWidth = 950;
        int targetHeight = 500;

        // 3. Crea la imagen escalada
        java.awt.Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH);
        
        // 4. Pon la imagen NUEVA (ya escalada) en el JLabel
        Wallpaper.setIcon(new javax.swing.ImageIcon(scaledImage));
        getContentPane().add(Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 500));

        pack();
    }

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed
    //cliente esté seleccionado
    if (idCliente == 0) {
        JOptionPane.showMessageDialog(null, "¡Seleccione un cliente para actualizar!");
        return;  // Si no se selecciona un cliente, no continua
    }

    // Validación de Campo_telefonos vacíos
    if (txt_nombre.getText().isEmpty() || txt_apellido.getText().isEmpty() || txt_telefono.getText().isEmpty() || txt_mesa.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "¡Complete todos los Campo_telefonos!");
        return;
    }

    // datos del clente a actualizar
    String nombre = txt_nombre.getText().trim();
    String apellido = txt_apellido.getText().trim();
    String telefono = txt_telefono.getText().trim();
    String numeroMesa = txt_mesa.getText().trim();

    
    Connection con = Conexion.conectar();
    String sql = "UPDATE tb_cliente SET nombre = ?, apellido = ?, telefono = ?, numero_mesa = ? WHERE idCliente = ?";
    
    try {
        // Preparar el statement para la actualización
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setString(2, apellido);
        pst.setString(3, telefono);
        pst.setString(4, numeroMesa);
        pst.setInt(5, idCliente);  //id seleccionado en tabla

        // Ejecutar la actualización
        int filasAfectadas = pst.executeUpdate();
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "¡Cliente actualizado correctamente!");
            // Refrescar la tabla para mostrar los nuevos datos
            CargarTablaClientes();
            Limpiar(); // Limpiar los Campo_telefonos de entrada
        } else {
            JOptionPane.showMessageDialog(null, "¡Error al actualizar el cliente!");
        }

        con.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "¡Error al actualizar los datos! \n" + e.getMessage());
    }   

    }//GEN-LAST:event_jButton_actualizarActionPerformed

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
    if (idCliente == 0) { 
        JOptionPane.showMessageDialog(null, "¡Seleccione un cliente para eliminar!");
    } else {
        // metodo para desocupar mesa y elimnar cliente
        boolean eliminado = eliminarCliente(idCliente); 

        if (eliminado) {
            JOptionPane.showMessageDialog(null, "¡Cliente eliminado y mesa liberada!");
            CargarTablaClientes();  // Actualiza la tabla
            Limpiar();  // Limpiar los Campo_telefonos
        } else {
            JOptionPane.showMessageDialog(null, "¡Error al eliminar el cliente o liberar la mesa!");
        }
    }
    }//GEN-LAST:event_jButton_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Campo_telefono_nombre;
    private javax.swing.JLabel Campo_telefono_apellido;
    private javax.swing.JLabel Campo_telefono;
    private javax.swing.JLabel No_mesa;
    private javax.swing.JLabel Wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_productos;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_mesa;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables

   
    private void Limpiar() {
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_telefono.setText("");
        txt_mesa.setText("");
    }




   private void CargarTablaClientes() {
    Connection con = Conexion.conectar();
    DefaultTableModel model = new DefaultTableModel();
    String sql = "SELECT idCliente, nombre, apellido, telefono, numero_mesa FROM tb_cliente";
    Statement st;
    try {
        st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        InterGestionarCliente.jTable_productos = new JTable(model);
        InterGestionarCliente.jScrollPane1.setViewportView(InterGestionarCliente.jTable_productos);

        //aañadir columnas
        model.addColumn("ID Cliente");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Teléfono");
        model.addColumn("No° de Mesa");

        // Llenar la tabla con los datos
        while (rs.next()) {
            Object fila[] = new Object[5]; //num de columnas
            fila[0] = rs.getInt("idCliente");
            fila[1] = rs.getString("nombre");
            fila[2] = rs.getString("apellido");
            fila[3] = rs.getString("telefono");
            fila[4] = rs.getString("numero_mesa");

            model.addRow(fila);
        }
        con.close();
    } catch (SQLException e) {
        System.out.println("Error al llenar la tabla clientes: " + e);
    }

    // evento para obtener el dato del cliente al hacer clic en una fila de la tabla
    jTable_productos.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila_point = jTable_productos.rowAtPoint(e.getPoint());
            int columna_point = 0;  // La columna 0 tiene el idCliente

            if (fila_point > -1) {
                idCliente = (int) model.getValueAt(fila_point, columna_point);  // Aquí usamos idCliente
                EnviarDatosProductoSeleccionado(idCliente);  // Pasar idCliente, no idProducto
            }
        }
    });
}



  //Metodo que envia datos seleccionados
    private void EnviarDatosProductoSeleccionado(int idCliente) {
        try {
            Connection con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "select * from tb_cliente where idCLiente = '" + idCliente + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txt_nombre.setText(rs.getString("nombre"));
                txt_apellido.setText(rs.getString("apellido"));
                txt_telefono.setText(rs.getString("telefono"));
                txt_mesa.setText(rs.getString("numero_mesa"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar producto: " + e);
        }
    }
    
    private boolean eliminarCliente(int idCliente) {
    Connection con = null;
    PreparedStatement pstLiberarMesa = null;
    PreparedStatement pstEliminarCliente = null;

    try {
        con = Conexion.conectar();
        con.setAutoCommit(false);  

        //liberar mesa
        String sqlLiberarMesa = "UPDATE tb_mesas SET estado = 'libre' WHERE numero_mesa IN (SELECT numero_mesa FROM tb_cliente WHERE idCliente = ?)";
        pstLiberarMesa = con.prepareStatement(sqlLiberarMesa);
        pstLiberarMesa.setInt(1, idCliente);
        int filasAfectadasMesa = pstLiberarMesa.executeUpdate();

        // verificar
        if (filasAfectadasMesa == 0) {
            System.out.println("No se pudo liberar la mesa para el cliente con idCliente: " + idCliente);
            con.rollback();  // Si no se pudo, deshacer la transacción
            return false;
        }

        //Eliminar al cliente
        String sqlEliminarCliente = "DELETE FROM tb_cliente WHERE idCliente = ?";
        pstEliminarCliente = con.prepareStatement(sqlEliminarCliente);
        pstEliminarCliente.setInt(1, idCliente);
        int filasAfectadasCliente = pstEliminarCliente.executeUpdate();

        //verificar
        if (filasAfectadasCliente == 0) {
            System.out.println("No se pudo eliminar al cliente con idCliente: " + idCliente);
            con.rollback();  // Si no se pudo , deshacer la transacción
            return false;
        }

        // si se pudo ambas se confirma
        con.commit();
        con.close();
        return true;
    } catch (SQLException e) {
       
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException ex) {
            System.out.println("Error al hacer rollback: " + ex);
        }
        System.out.println("Error al liberar la mesa y eliminar el cliente: " + e);
        return false;
    } finally {
        
        try {
            if (pstLiberarMesa != null) pstLiberarMesa.close();
            if (pstEliminarCliente != null) pstEliminarCliente.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e);
        }
    }
}




}

