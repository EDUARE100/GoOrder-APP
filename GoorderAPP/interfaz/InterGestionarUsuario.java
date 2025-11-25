
package interfaz;

import conexion.Conexion;
import controlador.Ctrl_Usuario;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Usuario;

public class InterGestionarUsuario extends javax.swing.JInternalFrame {
    
    private int idUsuario;
    
    public InterGestionarUsuario() {
        initComponents();
        this.setSize(new Dimension(728,430));
        this.setTitle("Gestionar Usuarios");
        
        this.cargartablas();
        
    }


    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_categorias = new javax.swing.JTable();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Campo_password = new javax.swing.JLabel();
        txt_password = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        Campo_telefono = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        Campo_usuario = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        wallpaper = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setBackground(new java.awt.Color(63, 60, 230));
        Titulo.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        Titulo.setForeground(new java.awt.Color(0, 0, 0));
        Titulo.setText("Administrar Usuarios");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 290, 40));

        jPanel1.setBackground(new java.awt.Color(63, 60, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_categorias.setBackground(new java.awt.Color(228, 228, 228));
        table_categorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(table_categorias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 420, 270));

        actualizar.setBackground(new java.awt.Color(63, 60, 230));
        actualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actualizar.setForeground(new java.awt.Color(0, 0, 0));
        actualizar.setText("Actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 110, 30));

        eliminar.setBackground(new java.awt.Color(63, 60, 230));
        eliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar.setForeground(new java.awt.Color(0, 0, 0));
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 110, 30));

        jPanel3.setBackground(new java.awt.Color(63, 60, 230));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Campo_password.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        Campo_password.setForeground(new java.awt.Color(255, 255, 255));
        Campo_password.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Campo_password.setText("Password");
        jPanel3.add(Campo_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
        });
        jPanel3.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 190, 60));

        jPanel4.setBackground(new java.awt.Color(63, 60, 230));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Campo_telefono.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        Campo_telefono.setForeground(new java.awt.Color(255, 255, 255));
        Campo_telefono.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Campo_telefono.setText("Telefono");
        jPanel4.add(Campo_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyPressed(evt);
            }
        });
        jPanel4.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 190, 60));

        jPanel5.setBackground(new java.awt.Color(63, 60, 230));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Campo_usuario.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        Campo_usuario.setForeground(new java.awt.Color(255, 255, 255));
        Campo_usuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Campo_usuario.setText("Usuario");
        jPanel5.add(Campo_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_usuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_usuarioKeyPressed(evt);
            }
        });
        jPanel5.add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 190, 60));

        // Cargas la imagen de fondo y la escalas al tamaño de la ventana
        javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpg"));
        java.awt.Image originalImage = originalIcon.getImage();
        
        // 2. Define el tamaño al que quieres escalar (el tamaño de tu ventana)
        int targetWidth = 728;
        int targetHeight = 430;

        // 3. Crea la imagen escalada
        java.awt.Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH);
        
        // 4. Pon la imagen NUEVA (ya escalada) en el JLabel
        wallpaper.setIcon(new javax.swing.ImageIcon(scaledImage));
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 728, 430));


    }// </editor-fold>//GEN-END:initComponents

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
       this.actualizar();
       }
    }//GEN-LAST:event_txt_passwordKeyPressed

    private void txt_telefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER){
       this.actualizar();
       }
    }//GEN-LAST:event_txt_telefonoKeyPressed

    private void txt_usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usuarioKeyPressed
    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
       this.actualizar();
       }
    }//GEN-LAST:event_txt_usuarioKeyPressed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed

        if(!txt_password.getText().isEmpty()){
            Usuario usuario = new Usuario();
            Ctrl_Usuario controlusuario = new Ctrl_Usuario();

            usuario.setUsuario(txt_password.getText().trim());

            if(!controlusuario.eliminar(idUsuario)){
                JOptionPane.showMessageDialog(null, "usuario eliminada");
                txt_password.setText("");
                this.cargartablas();
            }else{
                JOptionPane.showMessageDialog(null, "Usuario eliminada");
                this.cargartablas();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un usuario");
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        this.actualizar();
    }//GEN-LAST:event_actualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Campo_password;
    private javax.swing.JLabel Campo_telefono;
    private javax.swing.JLabel Campo_usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable table_categorias;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_usuario;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables

    
    //Metodo para registrar las categorias registradas
    
    private void cargartablas(){
            Connection con = Conexion.conectar();
            DefaultTableModel model = new DefaultTableModel();
        String sql = "select idUsuario, nombre, apellido, usuario, password, telefono from tb_usuario";
        Statement st;
        
        try{
            
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            InterGestionarUsuario.table_categorias = new JTable(model);
            InterGestionarUsuario.jScrollPane1.setViewportView(InterGestionarUsuario.table_categorias);
            
            model.addColumn("idUsuario");
            model.addColumn("Nombre");
            model.addColumn("apellido");
            model.addColumn("usuario");
            model.addColumn("password");
            model.addColumn("telefono");
            
            
            while(rs.next()){
                
                Object fila[] = new Object[6];
                
                for(int i =0; i<6 ; i++){
                    fila[i] = rs.getObject(i+1);
                }
                model.addRow(fila);
            }
            con.close();
            
        }catch(SQLException e){
            System.out.println("Error al llenar la tabla usuario: "+e);
        }
        
        table_categorias.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                int fila_point = table_categorias.rowAtPoint(e.getPoint());
                int columna_point = 0;
                
                if(fila_point > -1){
                    idUsuario = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosCat(idUsuario);
                }
            }
            
        });
        
    }
    private void EnviarDatosCat(int idUsuario){
        try{
            
            Connection con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement("select * from tb_usuario where idUsuario = '" + idUsuario + "'");
            
            ResultSet rs = pst.executeQuery();
                    
            if(rs.next()){
                txt_password.setText(rs.getString("password"));
                txt_usuario.setText(rs.getString("usuario"));
                txt_telefono.setText(rs.getString("telefono"));
                
                
            }
            con.close();
            
        }catch(SQLException e){
            System.out.println("Error al seleccionar usuario: "+e);
        }
    }
    
    private void actualizar(){
         if(!txt_password.getText().isEmpty()){
         Usuario usuario = new Usuario();
         Ctrl_Usuario controlusuario = new Ctrl_Usuario();
         
         usuario.setPassword(txt_password.getText().trim());
         usuario.setUsuario(txt_usuario.getText().trim());
         usuario.setTelefono(txt_telefono.getText().trim());
          
         if(controlusuario.actualizar(usuario, idUsuario)){
            JOptionPane.showMessageDialog(null, "Usuario actualizada"); 
            txt_password.setText("");
            txt_usuario.setText("");
            txt_telefono.setText("");
            this.cargartablas();
         }else{
            JOptionPane.showMessageDialog(null, "Error al actualizar Usuario");
         }
       }else{
         JOptionPane.showMessageDialog(null, "Seleccione un usuario");
       }
    }

}
