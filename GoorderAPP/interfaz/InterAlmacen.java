
package interfaz;

import conexion.Conexion;
import controlador.Ctrl_Producto;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import modelo.Producto;


public class InterAlmacen extends javax.swing.JInternalFrame {
    
    int obtenercategoriaproducto=0;
    
    public InterAlmacen() {
        initComponents();
        
        this.setSize(new Dimension(400,315));
        this.setTitle("Nuevo Producto");
        
        this.cargarcategorias();
    }
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        Campo1 = new javax.swing.JLabel();
        Campo2 = new javax.swing.JLabel();
        Campo3 = new javax.swing.JLabel();
        Campo4 = new javax.swing.JLabel();
        Campo5 = new javax.swing.JLabel();
        Campo6 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_cantidad = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        iva_box = new javax.swing.JComboBox<>();
        categoria_box = new javax.swing.JComboBox<>();
        guardar_boton = new javax.swing.JButton();
        wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("Nuevo Producto");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        Campo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo1.setForeground(new java.awt.Color(255, 255, 255));
        Campo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo1.setText("Nombre:");
        getContentPane().add(Campo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 90, -1));

        Campo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo2.setForeground(new java.awt.Color(255, 255, 255));
        Campo2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo2.setText("Cantidad:");
        getContentPane().add(Campo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 90, -1));

        Campo3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo3.setForeground(new java.awt.Color(255, 255, 255));
        Campo3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo3.setText("Precio:");
        getContentPane().add(Campo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 90, -1));

        Campo4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo4.setForeground(new java.awt.Color(255, 255, 255));
        Campo4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo4.setText("Descripcion:");
        getContentPane().add(Campo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 90, -1));

        Campo5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo5.setForeground(new java.awt.Color(255, 255, 255));
        Campo5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo5.setText("IVA:");
        getContentPane().add(Campo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 90, -1));

        Campo6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo6.setForeground(new java.awt.Color(255, 255, 255));
        Campo6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo6.setText("Categoria:");
        getContentPane().add(Campo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 90, -1));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombreKeyPressed(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 170, -1));

        txt_cantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyPressed(evt);
            }
        });
        getContentPane().add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 170, -1));

        txt_precio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precioKeyPressed(evt);
            }
        });
        getContentPane().add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 170, -1));

        txt_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyPressed(evt);
            }
        });
        getContentPane().add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 170, -1));

        iva_box.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        iva_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar IVA:", "No grava IVA", "16%" }));
        iva_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iva_boxActionPerformed(evt);
            }
        });
        getContentPane().add(iva_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 170, -1));

        categoria_box.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        categoria_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Categoria:", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(categoria_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 170, -1));

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
        getContentPane().add(guardar_boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 90, 30));

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo3.jpg"))); // NOI18N
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_botonActionPerformed
      Producto producto = new Producto();
      Ctrl_Producto controlproducto = new Ctrl_Producto();
      String iva = "";
      String categoria = "";
      iva = iva_box.getSelectedItem().toString().trim();
      categoria = categoria_box.getSelectedItem().toString().trim();
      
      //validar campos
      if(txt_nombre.getText().equals("") || txt_cantidad.getText().equals("") || txt_precio.getText().equals("")){
          JOptionPane.showMessageDialog(null, "Complete todos los campos");
      }else{
          //Consultar para ver si el producto existe
          if(!controlproducto.existe(txt_nombre.getText().trim())){
              if(iva.equalsIgnoreCase("Seleccione IVA:")){
                  JOptionPane.showMessageDialog(null, "Seleccione IVA");
              }else{
                  if(categoria.equalsIgnoreCase("Seleccione categoria:")){
                      JOptionPane.showMessageDialog(null, "Seleccione categoria");
                  }else{
                      try{
                          producto.setNombre(txt_nombre.getText().trim());
                          producto.setCantidad(Integer.parseInt(txt_cantidad.getText().trim()));
                          String preciotxt = "";
                          double precio = 0.0;
                          preciotxt = txt_precio.getText().trim();
                          boolean aux = false; 
                          
                          //Si el usuario ingresa ,(coma) lo transformara a .(punto)
                          
                          for(int i=0; i<preciotxt.length(); i++){
                              if(preciotxt.charAt(i) == ','){
                                  String precionuevo = preciotxt.replace(",", ".");
                                  precio = Double.parseDouble(precionuevo);
                                  aux = true;
                              }
                          }
                          //Evaluar la condicion
                          
                          if(aux == true){
                              producto.setPrecio(precio);
                          }else{
                              precio = Double.parseDouble(preciotxt);
                              producto.setPrecio(precio);
                          }
                          
                          producto.setDescripcion(txt_descripcion.getText().trim());
                          //Porcentaje iva
                          
                          if(iva.equalsIgnoreCase("No grava iva")){
                              producto.setPorcentajeIva(0);
                          }else if(iva.equalsIgnoreCase("16%")){
                              producto.setPorcentajeIva(16);
                          }
                          
                          //id categoria exportacion de metodo
                          this.IdCategoria();
                          producto.setIdCategoria(obtenercategoriaproducto);//Primero se debe ejecutar el metodo IdCategoria sino daria error por el orden
                          producto.setEstado(1); //El 1 significa un estado activo
                          
                          if(controlproducto.guardar(producto)){
                              
                              JOptionPane.showMessageDialog(null, "Registro Guardado");
                              
                              this.cargarcategorias();
                              this.iva_box.setSelectedItem("Seleccionar IVA:");
                              this.limpiar();
                              
                          }else{
                              JOptionPane.showMessageDialog(null, "Error al guardar");
                          }
                          
                          
                      }catch(HeadlessException | NumberFormatException e) /*Para la seccion del error*/{
                          System.out.println("Erorr en: "+e);
                      }
                  }
              }
          }else{
              JOptionPane.showMessageDialog(null, "El producto ya existe en la base de datos");
          }
          
      }
    }//GEN-LAST:event_guardar_botonActionPerformed

    private void iva_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iva_boxActionPerformed
    }//GEN-LAST:event_iva_boxActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed

    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           txt_cantidad.requestFocus();
       }
    }//GEN-LAST:event_txt_nombreKeyPressed

    private void txt_cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyPressed

       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           txt_precio.requestFocus();
       }
    }//GEN-LAST:event_txt_cantidadKeyPressed

    private void txt_precioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           txt_descripcion.requestFocus();
       }
    }//GEN-LAST:event_txt_precioKeyPressed

    private void txt_descripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           iva_box.requestFocus();
       }
    }//GEN-LAST:event_txt_descripcionKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> categoria_box;
    private javax.swing.JButton guardar_boton;
    private javax.swing.JComboBox<String> iva_box;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Campo1;
    private javax.swing.JLabel Campo2;
    private javax.swing.JLabel Campo3;
    private javax.swing.JLabel Campo4;
    private javax.swing.JLabel Campo5;
    private javax.swing.JLabel Campo6;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables
//Metodo para limpiar campos
    
    private void limpiar(){
        txt_nombre.setText("");
        txt_cantidad.setText("");
        txt_precio.setText("");
        txt_descripcion.setText("");
    }
//Metodos auxiliares para cargar las categorias registradas en la base de datos
    
    private void cargarcategorias(){
    Connection cn = Conexion.conectar();
    String sql = "select *  from tb_categoria";
    Statement st;
    try{
        
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        categoria_box.removeAllItems(); //Remueve los elementos del box (Item 1,item 2, item 3)
        categoria_box.addItem("Seleccione categoria:");
        while(rs.next()){
            categoria_box.addItem(rs.getString("descripcion"));//Para agregar todas las descripciones de las categorias
        }
        cn.close();
        
    }catch(SQLException e){
        System.out.println("");
    }
  
}

    
    private int IdCategoria(){
        String sql = "select * from tb_categoria where descripcion = '" + this.categoria_box.getSelectedItem() + "'";
        Statement st;
        try{
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               obtenercategoriaproducto = rs.getInt("idCategoria"); //El idCategoria tiene que estar nombrado como en la base de datos
                
            }
        }catch(SQLException e){
            System.out.println("Error al obtener id categoria");
        }
        //Debe retornar debido a que esta funcion es de tipo entero
        return obtenercategoriaproducto; //Debe retornar la variable que se asigno al principio
    }
}
