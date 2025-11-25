
package interfaz;

import conexion.Conexion;
import controlador.Ctrl_Producto;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Producto;

/**
 *
 * @author ediso
 */
public class InterActualizarStock extends javax.swing.JInternalFrame {

    //variables
    int idProducto = 0;
    int cantidad = 0;

    public InterActualizarStock() {
        initComponents();
        setTitle("Actualizar Stock");
        setSize(new Dimension(400, 300));

        this.CargarComboProductos();
    }
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        Campo1 = new javax.swing.JLabel();
        Campo2 = new javax.swing.JLabel();
        Campo3 = new javax.swing.JLabel();
        txt_cantidad_actual = new javax.swing.JTextField();
        txt_cantidad_nueva = new javax.swing.JTextField();
        jComboBox_producto = new javax.swing.JComboBox<>();
        Boton_actualizar = new javax.swing.JButton();
        wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("Actualizar Stock de Productos");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        Campo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo1.setForeground(new java.awt.Color(255, 255, 255));
        Campo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo1.setText("Producto:");
        getContentPane().add(Campo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 110, -1));

        Campo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo2.setForeground(new java.awt.Color(255, 255, 255));
        Campo2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo2.setText("Stock Actual:");
        getContentPane().add(Campo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 110, -1));

        Campo3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Campo3.setForeground(new java.awt.Color(255, 255, 255));
        Campo3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Campo3.setText("Stock Nuevo:");
        getContentPane().add(Campo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 110, -1));

        txt_cantidad_actual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cantidad_actual.setEnabled(false);
        getContentPane().add(txt_cantidad_actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 170, -1));

        txt_cantidad_nueva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cantidad_nueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cantidad_nuevaActionPerformed(evt);
            }
        });
        txt_cantidad_nueva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantidad_nuevaKeyPressed(evt);
            }
        });
        getContentPane().add(txt_cantidad_nueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 170, -1));

        jComboBox_producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione producto:", "Item 2", "Item 3", "Item 4" }));
        jComboBox_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_productoActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 170, -1));

        Boton_actualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Boton_actualizar.setText("Actualizar");
        Boton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 170, 30));

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo3.jpg"))); // NOI18N
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_productoActionPerformed
        this.MostrarStock();
    }//GEN-LAST:event_jComboBox_productoActionPerformed

    private void Boton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_actualizarActionPerformed
        this.actualizar();
    }//GEN-LAST:event_Boton_actualizarActionPerformed

    private void txt_cantidad_nuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cantidad_nuevaActionPerformed
    }//GEN-LAST:event_txt_cantidad_nuevaActionPerformed

    private void txt_cantidad_nuevaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidad_nuevaKeyPressed
   if(evt.getKeyCode() == KeyEvent.VK_ENTER){
          this.actualizar();
       }
    }//GEN-LAST:event_txt_cantidad_nuevaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_actualizar;
    private javax.swing.JComboBox<String> jComboBox_producto;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Campo1;
    private javax.swing.JLabel Campo2;
    private javax.swing.JLabel Campo3;
    private javax.swing.JLabel wallpaper;
    private javax.swing.JTextField txt_cantidad_actual;
    private javax.swing.JTextField txt_cantidad_nueva;
    // End of variables declaration//GEN-END:variables
        
    
    private void actualizar(){
        //validamos seleccion del producto
        if (!jComboBox_producto.getSelectedItem().equals("Seleccione producto:")) {
            //Validamos campos vacios
            if (!txt_cantidad_nueva.getText().isEmpty()) {
                //validamos que el usuario no ingrese otros caracteres no numericos
                boolean validacion = validar(txt_cantidad_nueva.getText().trim());
                if (validacion == true) {
                    //validar que la cantidad sea mayor cero (0)
                    if (Integer.parseInt(txt_cantidad_nueva.getText()) > 0) {

                        Producto producto = new Producto();
                        Ctrl_Producto controlProducto = new Ctrl_Producto();
                        int stockActual = Integer.parseInt(txt_cantidad_actual.getText().trim());
                        int stockNuevo = Integer.parseInt(txt_cantidad_nueva.getText().trim());

                        stockNuevo = stockActual + stockNuevo;
                        producto.setCantidad(stockNuevo);
                        if (controlProducto.actualizarStock(producto, idProducto)) {
                            JOptionPane.showMessageDialog(null, "Stock Actualizado");
                            jComboBox_producto.setSelectedItem("Seleccione producto:");
                            txt_cantidad_actual.setText("");
                            txt_cantidad_nueva.setText("");
                            this.CargarComboProductos();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Actualizar Stock");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad no puede ser cero ni negativa");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "En la cantidad no se admiten caracteres no numericos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una nueva cantidad para sumar el stock del producto");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        }
    }
    
    
    //Metodo para caragar los productos en el jComboBox
    private void CargarComboProductos() {

        Connection cn = Conexion.conectar();
        String sql = "select * from tb_producto";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            jComboBox_producto.removeAllItems();
            jComboBox_producto.addItem("Seleccione producto:");
            while (rs.next()) {
                jComboBox_producto.addItem(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los productos en: " + e);
        }

    }

    //metodo para mostrar stock del producto seleccionado
    private void MostrarStock() {
        try {

            Connection cn = Conexion.conectar();
            String sql = "select * from tb_producto where nombre = '" + this.jComboBox_producto.getSelectedItem() + "'";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                idProducto = rs.getInt("idProducto");
                cantidad = rs.getInt("cantidad");
                txt_cantidad_actual.setText(String.valueOf(cantidad));
            } else {
                txt_cantidad_actual.setText("");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener stock del producto en: " + e);
        }
    }

    //metodo de validacion de caracteres no numericos
    private boolean validar(String valor) {
        try {
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
