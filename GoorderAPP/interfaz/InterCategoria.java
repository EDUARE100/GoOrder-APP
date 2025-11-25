
package interfaz;

import controlador.Ctrl_Categoria;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import modelo.Categoria;


public class InterCategoria extends javax.swing.JInternalFrame {

    
    public InterCategoria() {
        initComponents();
        
        this.setSize(new Dimension(400,200));
        this.setTitle("Nueva Categoria");
        
    }

    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        Campo1 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        Boton_guardar = new javax.swing.JButton();
        wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("Nueva Categoria");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        Campo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo1.setForeground(new java.awt.Color(255, 255, 255));
        Campo1.setText("Descripción categorías");
        getContentPane().add(Campo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txt_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyPressed(evt);
            }
        });
        getContentPane().add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 150, -1));

        Boton_guardar.setBackground(new java.awt.Color(153, 0, 51));
        Boton_guardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Boton_guardar.setText("Guardar");
        Boton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_guardarActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 90, 30));

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo3.jpg"))); // NOI18N
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_guardarActionPerformed
        this.entrar();
    }//GEN-LAST:event_Boton_guardarActionPerformed

    private void txt_descripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
       this.entrar();}
    }//GEN-LAST:event_txt_descripcionKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_guardar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Campo1;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables

    
    private void entrar(){
                Categoria categoria = new Categoria();
        Ctrl_Categoria controlCategoria = new Ctrl_Categoria();
        
        if(txt_descripcion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Complete todos los campos");
        }else{
            
            if(!controlCategoria.existe(txt_descripcion.getText().trim())){
                categoria.setDescripcion(txt_descripcion.getText().trim());
            categoria.setEstado(1);
            if(controlCategoria.guardar(categoria)){
             JOptionPane.showMessageDialog(null,"Registros guardados");
            }else{
             JOptionPane.showMessageDialog(null,"Error al guardar");   
            } 
            }else{
                JOptionPane.showMessageDialog(null,"La categoria ya existe en la base de datos"); 
            } 
        }
        //Limpiar campo
        txt_descripcion.setText("");
    }
}
