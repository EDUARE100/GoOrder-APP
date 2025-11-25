
package interfaz;

import conexion.Conexion;
import static interfaz.Interfaz.jDesktopPane_menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class InterMesa extends javax.swing.JInternalFrame {

    public InterMesa() {
        initComponents();
        this.setTitle("Mesas Disponibles");
        setLocation(220, 10);
    }

    

  

       
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new interfaz.Fondo_ventana("/imagenes/fondo2.jpg");
        jPanel1 = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        Boton7 = new javax.swing.JButton();
        Boton_salir = new javax.swing.JButton();
        Boton2 = new javax.swing.JButton();
        Boton9 = new javax.swing.JButton();
        Boton8 = new javax.swing.JButton();
        Boton6 = new javax.swing.JButton();
        Boton5 = new javax.swing.JButton();
        Boton4 = new javax.swing.JButton();
        Titulo0 = new javax.swing.JLabel();
        Titulo1 = new javax.swing.JLabel();
        Titulo4 = new javax.swing.JLabel();
        Titulo5 = new javax.swing.JLabel();
        Titulo6 = new javax.swing.JLabel();
        Titulo7 = new javax.swing.JLabel();
        Titulo8 = new javax.swing.JLabel();
        Titulo9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Boton10 = new javax.swing.JButton();
        Boton11 = new javax.swing.JButton();


        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 48)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("MESAS");
        jPanel1.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 250, 80));

        Boton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton7.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton7ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 170, 81));

        Boton_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png"))); // NOI18N
        Boton_salir.setText("Boton_salir");
        Boton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_salirActionPerformed(evt);
            }
        });
        jPanel2.add(Boton_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 50, 56));
        Boton_salir.addActionListener(_ -> dispose());
        jPanel2.add(Boton_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 50, 56));

        Boton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton2.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton2ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 170, 81));

        Boton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton9.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton9ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, 170, 81));

        Boton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton8.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton8ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 170, 81));

        Boton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton6.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton6ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 170, 81));

        Boton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton5.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton5ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 170, 81));

        Boton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton4.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton4ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 170, 81));

        Titulo0.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Titulo0.setText("Mesa 9");
        jPanel2.add(Titulo0, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 70, -1));

        Titulo1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Titulo1.setText("Mesa 1");
        jPanel2.add(Titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 82, -1));

        Titulo4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Titulo4.setText("Mesa 8");
        jPanel2.add(Titulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 70, -1));

        Titulo5.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Titulo5.setText("Mesa 7");
        jPanel2.add(Titulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 70, -1));

        Titulo6.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Titulo6.setText("Mesa 4");
        jPanel2.add(Titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 70, -1));

        Titulo7.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Titulo7.setText("Mesa 5");
        jPanel2.add(Titulo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 70, -1));

        Titulo8.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Titulo8.setText("Mesa 2");
        jPanel2.add(Titulo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 70, -1));

        Titulo9.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Titulo9.setText("Mesa 3");
        jPanel2.add(Titulo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 70, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel20.setText("Mesa 6");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 70, -1));

        Boton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton10.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton10ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 170, 81));

        Boton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesa (1).png"))); // NOI18N
        Boton11.setMargin(new java.awt.Insets(2, 14, 15, 14));
        Boton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton11ActionPerformed(evt);
            }
        });
        jPanel2.add(Boton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 170, 81));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton4ActionPerformed
     verificarMesa(4);
    }//GEN-LAST:event_Boton4ActionPerformed

    private void Boton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton5ActionPerformed
     verificarMesa(5);  
    }//GEN-LAST:event_Boton5ActionPerformed

    private void Boton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton6ActionPerformed
       verificarMesa(6);  
    }//GEN-LAST:event_Boton6ActionPerformed

    private void Boton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton8ActionPerformed
    verificarMesa(8);          
    }//GEN-LAST:event_Boton8ActionPerformed

    private void Boton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton9ActionPerformed
    verificarMesa(9);  
    }//GEN-LAST:event_Boton9ActionPerformed

    private void Boton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_salirActionPerformed
    }//GEN-LAST:event_Boton_salirActionPerformed

    private void Boton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton7ActionPerformed
    verificarMesa(1);   
    }//GEN-LAST:event_Boton7ActionPerformed

    private void Boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton2ActionPerformed
    verificarMesa(2);  
    }//GEN-LAST:event_Boton2ActionPerformed

    private void Boton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton10ActionPerformed
    verificarMesa(3);  
    }//GEN-LAST:event_Boton10ActionPerformed

    private void Boton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton11ActionPerformed
    verificarMesa(7);  
    }//GEN-LAST:event_Boton11ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton10;
    private javax.swing.JButton Boton11;
    private javax.swing.JButton Boton2;
    private javax.swing.JButton Boton4;
    private javax.swing.JButton Boton5;
    private javax.swing.JButton Boton6;
    private javax.swing.JButton Boton7;
    private javax.swing.JButton Boton8;
    private javax.swing.JButton Boton9;
    private javax.swing.JButton Boton_salir;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Titulo0;
    private javax.swing.JLabel Titulo1;
    private javax.swing.JLabel Titulo4;
    private javax.swing.JLabel Titulo5;
    private javax.swing.JLabel Titulo6;
    private javax.swing.JLabel Titulo7;
    private javax.swing.JLabel Titulo8;
    private javax.swing.JLabel Titulo9;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
                                 
 private void verificarMesa(int numeroMesa) {
    // Verificar si mesa esta ocupada
    if (esMesaOcupada(numeroMesa)) {
        //si esta se abre el menu
        abrirMenu(numeroMesa); 
    } else {
         //si no pues no
        JOptionPane.showMessageDialog(null, "Mesa " + numeroMesa + " No tiene registrado un cliente. No puedes ingresar.");
    }
}

//metodo para comprobar si la mesa está ocupada
private boolean esMesaOcupada(int numeroMesa) {
     Connection cn = Conexion.conectar();
    String sql = "SELECT estado FROM tb_mesas WHERE numero_mesa = ?";

    try (PreparedStatement stmt = cn.prepareStatement(sql)) {  
        stmt.setInt(1, numeroMesa);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            String estado = rs.getString("estado");
            return "ocupada".equals(estado);  // Retorna true si la mesa está ocupada
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
   
    return false; //si esta libre
}

private void abrirMenu(int numeroMesa) {
    MenuInterface action = new VerMenu(jDesktopPane_menu);
    action.openMenu(numeroMesa);
}
}