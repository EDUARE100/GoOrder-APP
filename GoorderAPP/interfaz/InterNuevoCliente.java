
package interfaz;

import conexion.Conexion;
import controlador.Ctrl_Cliente;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Cliente;


public class InterNuevoCliente extends javax.swing.JInternalFrame {
    
    int obtenernuevocliente=0;
    
    public InterNuevoCliente() {
        initComponents();
        
        this.setSize(new Dimension(400,300));
        this.setTitle("Nuevo cliente");
        
        this.cargarclientes();
    }

    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        Campo_nombre = new javax.swing.JLabel();
        Campo_apellido = new javax.swing.JLabel();
        Campo_telefono = new javax.swing.JLabel();
        No_mesa = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        mesa_box = new javax.swing.JComboBox<>();
        guardar_boton = new javax.swing.JButton();
        wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("Nuevo Cliente");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        Campo_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_nombre.setForeground(new java.awt.Color(255, 255, 255));
        Campo_nombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_nombre.setText("Nombre:");
        getContentPane().add(Campo_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 90, -1));

        Campo_apellido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_apellido.setForeground(new java.awt.Color(255, 255, 255));
        Campo_apellido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_apellido.setText("Apellido:");
        getContentPane().add(Campo_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 90, -1));

        Campo_telefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_telefono.setForeground(new java.awt.Color(255, 255, 255));
        Campo_telefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_telefono.setText("Telefono:");
        getContentPane().add(Campo_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 90, -1));

        No_mesa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        No_mesa.setForeground(new java.awt.Color(255, 255, 255));
        No_mesa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        No_mesa.setText("Numero de Mesa");
        getContentPane().add(No_mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 140, -1));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nombre.setName(""); // NOI18N
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 170, -1));

        txt_apellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 170, -1));

        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 170, -1));

        mesa_box.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesa_box.setMaximumRowCount(20);
        mesa_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione el numero de mesa", "1", "2", "3", "4", "5", "6", "7", "8", "9", " " }));
        mesa_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesa_boxActionPerformed(evt);
            }
        });
        getContentPane().add(mesa_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 170, -1));

        guardar_boton.setBackground(new java.awt.Color(153, 0, 51));
        guardar_boton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        guardar_boton.setForeground(new java.awt.Color(255, 255, 255));
        guardar_boton.setText("Guardar");
        guardar_boton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        guardar_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_botonActionPerformed(evt);
            }
        });
        getContentPane().add(guardar_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 90, 30));

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo3.jpg"))); // NOI18N
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_botonActionPerformed
     // Validar campos antes de continuar
    if (txt_nombre.getText().equals("") || txt_apellido.getText().equals("") || 
        txt_telefono.getText().equals("") || mesa_box.getSelectedItem().toString().equals("Seleccione el numero de mesa")) {
        JOptionPane.showMessageDialog(null, "Complete todos los campos");
        return; // Salir del método si hay campos vacíos
    }

    Cliente cliente = new Cliente();
    Ctrl_Cliente controlcliente = new Ctrl_Cliente();
    String mesa1 = mesa_box.getSelectedItem().toString();
    
    cliente.setNombre(txt_nombre.getText());
    cliente.setApellido(txt_apellido.getText());
    cliente.setTelefono(txt_telefono.getText());
    cliente.setMesa(Integer.parseInt(mesa1));

    // Guardar el cliente
    if (controlcliente.guardar(cliente)) {
        JOptionPane.showMessageDialog(null, "Cliente guardado con éxito");

        // Cambiar el estado de la mesa a ocupada
        try {
            int mesaSeleccionada = Integer.parseInt(mesa1);
            Connection cn = Conexion.conectar();
            String sqlUpdateMesa = "UPDATE tb_mesas SET estado = 'ocupada' WHERE numero_mesa = ?";
            PreparedStatement pstMesa = cn.prepareStatement(sqlUpdateMesa);
            pstMesa.setInt(1, mesaSeleccionada);
            pstMesa.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la mesa");
        }

        this.dispose(); 
    } else {
        JOptionPane.showMessageDialog(null, "Error al guardar cliente");
    }

    }//GEN-LAST:event_guardar_botonActionPerformed

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
    }//GEN-LAST:event_txt_apellidoActionPerformed

    private void mesa_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesa_boxActionPerformed
    }//GEN-LAST:event_mesa_boxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton guardar_boton;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Campo_nombre;
    private javax.swing.JLabel Campo_apellido;
    private javax.swing.JLabel Campo_telefono;
    private javax.swing.JLabel No_mesa;
    private javax.swing.JComboBox<String> mesa_box;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables
//Metodo pa cargar las categorias registradas en la base de datos
    
   private void cargarclientes() {
    String sql = "SELECT numero_mesa FROM tb_mesas WHERE estado = 'libre'"; // Filtrar mesas con estado 'libre'
    try (Connection cn = Conexion.conectar(); 
         Statement st = cn.createStatement(); 
         ResultSet rs = st.executeQuery(sql)) {
        
        mesa_box.removeAllItems(); 
        mesa_box.addItem("Seleccione número de mesa:"); //textp por defecto

        // agregar los números de mesa disponibles a la box
        while (rs.next()) {
            mesa_box.addItem(String.valueOf(rs.getInt("numero_mesa"))); // Convertir int a String antes de agregarlo
        }
    } catch (SQLException e) {
        System.out.println("Error al cargar las mesas disponibles: " + e.getMessage());
    }
}}
   
