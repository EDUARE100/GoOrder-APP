
package interfaz;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import modelo.Usuario;
import controlador.Ctrl_Usuario;


public class InterNuevoUsuario extends javax.swing.JInternalFrame {
    
    int obtenernuevousuario=0;
    
    public InterNuevoUsuario() {
        initComponents();
        
        this.setSize(new Dimension(400,350));
        this.setTitle("Nuevo usuario");
        
        
    }

    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        Campo_nombre = new javax.swing.JLabel();
        Campo_apellido = new javax.swing.JLabel();
        Campo_usuario = new javax.swing.JLabel();
        Campo_password = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_password = new javax.swing.JTextField();
        txt_Usuario = new javax.swing.JTextField();
        guardar_boton = new javax.swing.JButton();
        telefono_new = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("Nuevo Usuario");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        Campo_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_nombre.setForeground(new java.awt.Color(255, 255, 255));
        Campo_nombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_nombre.setText("Nombre:");
        getContentPane().add(Campo_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 90, -1));

        Campo_apellido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_apellido.setForeground(new java.awt.Color(255, 255, 255));
        Campo_apellido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_apellido.setText("Apellido:");
        getContentPane().add(Campo_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, -1));

        Campo_usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_usuario.setForeground(new java.awt.Color(255, 255, 255));
        Campo_usuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_usuario.setText("Usuario:");
        getContentPane().add(Campo_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 90, -1));

        Campo_password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo_password.setForeground(new java.awt.Color(255, 255, 255));
        Campo_password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo_password.setText("Password:");
        getContentPane().add(Campo_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 90, -1));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nombre.setName(""); // NOI18N
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 170, -1));

        txt_apellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 170, -1));

        txt_password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 170, -1));

        txt_Usuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 170, -1));

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
        getContentPane().add(guardar_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 90, 30));

        telefono_new.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        telefono_new.setForeground(new java.awt.Color(255, 255, 255));
        telefono_new.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        telefono_new.setText("Telefono");
        getContentPane().add(telefono_new, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 90, -1));

        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefonoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 170, -1));

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo3.jpg"))); // NOI18N
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_botonActionPerformed
     // Validar campos antes de continuar
    if (txt_nombre.getText().equals("") || txt_apellido.getText().equals("") || 
        txt_Usuario.getText().equals("") || txt_password.getText().equals("") || 
        txt_telefono.getText().equals("") ) {
        JOptionPane.showMessageDialog(null, "Complete todos los campos");
        return; // Salir del método si hay campos vacíos
    }

    Usuario usuario = new Usuario();
    Ctrl_Usuario controlusuario = new Ctrl_Usuario();

    
    usuario.setNombre(txt_nombre.getText());
    usuario.setApellido(txt_apellido.getText());
    usuario.setUsuario(txt_Usuario.getText());
    usuario.setPassword(txt_password.getText());
    usuario.setTelefono(txt_telefono.getText());
   

    // Guardar el usuario
    if (controlusuario.guardar(usuario)) {
        JOptionPane.showMessageDialog(null, "Usuario guardado con éxito");
        this.dispose();  // Cerrar la ventana
    } else {
        JOptionPane.showMessageDialog(null, "Error al guardar Usuario");
    }

    }//GEN-LAST:event_guardar_botonActionPerformed

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
    }//GEN-LAST:event_txt_apellidoActionPerformed

    private void txt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefonoActionPerformed
    }//GEN-LAST:event_txt_telefonoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton guardar_boton;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Campo_nombre;
    private javax.swing.JLabel Campo_apellido;
    private javax.swing.JLabel Campo_usuario;
    private javax.swing.JLabel Campo_password;
    private javax.swing.JLabel telefono_new;
    private javax.swing.JTextField txt_Usuario;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables
 }
   
   
