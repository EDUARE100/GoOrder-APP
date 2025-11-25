
package interfaz;

import conexion.Conexion;
import controlador.Ctrl_Categoria;
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
import modelo.Categoria;

public class InterGestionarCategoria extends javax.swing.JInternalFrame {
    
    private int idCategoria;
    
    public InterGestionarCategoria() {
        initComponents();
        this.setSize(new Dimension(600,400));
        this.setTitle("Gestionar Categorias");
        
        this.cargartablas();
        
    }
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_categorias = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        descripcion = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("Administrar Categorias");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_categorias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_categorias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 350, 250));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        actualizar.setBackground(new java.awt.Color(153, 0, 51));
        actualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        jPanel2.add(actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 10, 110, 30));

        eliminar.setBackground(new java.awt.Color(153, 0, 51));
        eliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel2.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 130, 90));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        descripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        descripcion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcion.setText("Descripción: ");
        jPanel3.add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyPressed(evt);
            }
        });
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 190, 80));

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo3.jpg"))); // NOI18N
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
       this.actualizar();
    }//GEN-LAST:event_actualizarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
       
        if(!txt_descripcion.getText().isEmpty()){
         Categoria categoria = new Categoria();
         Ctrl_Categoria controlcategoria = new Ctrl_Categoria();
         
         categoria.setDescripcion(txt_descripcion.getText().trim());
         
         if(!controlcategoria.eliminar(idCategoria)){
            JOptionPane.showMessageDialog(null, "Categoria eliminada"); 
            txt_descripcion.setText("");
            this.cargartablas();
         }else{
            JOptionPane.showMessageDialog(null, "Error al eliminar categoria");
         }
       }else{
         JOptionPane.showMessageDialog(null, "Seleccione una categoria");
       }
    }//GEN-LAST:event_eliminarActionPerformed

    private void txt_descripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
       this.actualizar();
       }
    }//GEN-LAST:event_txt_descripcionKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel descripcion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable table_categorias;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables

    
    //Metodo para registrar las categorias registradas
    
    private void cargartablas(){
        Connection con = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "select idCategoria, descripcion, estado from tb_categoria";
        Statement st;
        
        try{
            
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            InterGestionarCategoria.table_categorias = new JTable(model);
            InterGestionarCategoria.jScrollPane1.setViewportView(InterGestionarCategoria.table_categorias);
            
            model.addColumn("idCategoria");
            model.addColumn("descripcion");
            model.addColumn("estado");
            
            while(rs.next()){
                
                Object fila[] = new Object[3];
                
                for(int i =0; i<3 ; i++){
                    fila[i] = rs.getObject(i+1);
                }
                model.addRow(fila);
            }
            con.close();
            
        }catch(SQLException e){
            System.out.println("Error al llenar la tabla categoria: "+e);
        }
        
        table_categorias.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                int fila_point = table_categorias.rowAtPoint(e.getPoint());
                int columna_point = 0;
                
                if(fila_point > -1){
                    idCategoria = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosCat(idCategoria);
                }
            }
            
        });
        
    }
    private void EnviarDatosCat(int idCategoria){
        try{
            
            Connection con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement("select * from tb_categoria where idCategoria = '" + idCategoria + "'");
            
            ResultSet rs = pst.executeQuery();
                    
            if(rs.next()){
                txt_descripcion.setText(rs.getString("descripcion"));
                
            }
            con.close();
            
        }catch(SQLException e){
            System.out.println("Error al seleccionar categoria: "+e);
        }
    }
    
    private void actualizar(){
         if(!txt_descripcion.getText().isEmpty()){
         Categoria categoria = new Categoria();
         Ctrl_Categoria controlcategoria = new Ctrl_Categoria();
         
         categoria.setDescripcion(txt_descripcion.getText().trim());
          
         if(controlcategoria.actualizar(categoria, idCategoria)){
            JOptionPane.showMessageDialog(null, "Categoria actualizada"); 
            txt_descripcion.setText("");
            this.cargartablas();
         }else{
            JOptionPane.showMessageDialog(null, "Error al actualizar categoria");
         }
       }else{
         JOptionPane.showMessageDialog(null, "Seleccione una categoria");
       }
    }

}
