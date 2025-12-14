
package interfaz;

import java.awt.Dimension;
import javax.swing.JDesktopPane;


public class Interfaz extends javax.swing.JFrame {
    
    public static JDesktopPane jDesktopPane_menu;


    public Interfaz() {
        initComponents();
        this.setSize(new Dimension(1200,700));
        this.setLocationRelativeTo(null);
        this.setTitle("Interfaz");
        
        this.setLayout(null);
        String rutaFondo = "/imagenes/fondo2.jpg";
        jDesktopPane_menu = new Fondo(rutaFondo);
        
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        Interfaz.jDesktopPane_menu.setBounds(0, 0, ancho, alto);
        this.add(jDesktopPane_menu);
        
    }

  
    private void initComponents() {

        Barra_interfaz = new javax.swing.JMenuBar();

        Usuario = new javax.swing.JMenu();
        nuevo_usuario = new javax.swing.JMenuItem();
        gestionar_usuario = new javax.swing.JMenuItem();

        Menu = new javax.swing.JMenu();
        Vermenu = new javax.swing.JMenuItem();

        Producto = new javax.swing.JMenu();
        nuevo_producto = new javax.swing.JMenuItem();
        gestionar_producto = new javax.swing.JMenuItem();
        actualizar_stock = new javax.swing.JMenuItem();

        Cliente = new javax.swing.JMenu();
        nuevo_cliente = new javax.swing.JMenuItem();
        gestionar_cliente = new javax.swing.JMenuItem();
        boton_mesas = new javax.swing.JMenuItem();

        Categoria = new javax.swing.JMenu();
        nueva_categoria = new javax.swing.JMenuItem();
        gestionar_categoria = new javax.swing.JMenuItem();

        Reportes = new javax.swing.JMenu();
        reportes_clientes = new javax.swing.JMenuItem();

        Cerrar_sesion = new javax.swing.JMenu();
        cerrar_sesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user2.png"))); 
        Usuario.setText("Usuario");
        Usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        Usuario.setPreferredSize(new java.awt.Dimension(150, 50));

        nuevo_usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        nuevo_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); 
        nuevo_usuario.setText("Nuevo Usuario");
        nuevo_usuario.setPreferredSize(new java.awt.Dimension(190, 30));
        nuevo_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_usuario(evt);
            }
        });
        Usuario.add(nuevo_usuario);

        gestionar_usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        gestionar_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/configuraciones.png"))); 
        gestionar_usuario.setText("Gestionar Usuarios");
        gestionar_usuario.setPreferredSize(new java.awt.Dimension(190, 30));
        gestionar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionar_usuario(evt);
            }
        });
        Usuario.add(gestionar_usuario);

        Barra_interfaz.add(Usuario);

        Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cuchilleria.png"))); 
        Menu.setText("Menú");
        Menu.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        Menu.setPreferredSize(new java.awt.Dimension(150, 50));

        Vermenu.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        Vermenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carrito.png"))); 
        Vermenu.setText("Ver Menú");
        Vermenu.setPreferredSize(new java.awt.Dimension(150, 30));
        Vermenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vermenu(evt);
            }
        });
        Menu.add(Vermenu);

        Barra_interfaz.add(Menu);

        Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/producto.png"))); 
        Producto.setText("Producto");
        Producto.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        Producto.setPreferredSize(new java.awt.Dimension(150, 50));

        nuevo_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        nuevo_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); 
        nuevo_producto.setText("Nuevo Producto");
        nuevo_producto.setPreferredSize(new java.awt.Dimension(200, 30));
        nuevo_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_producto(evt);
            }
        });
        Producto.add(nuevo_producto);

        gestionar_producto.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        gestionar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/configuraciones.png"))); 
        gestionar_producto.setText("Gestionar Productos");
        gestionar_producto.setPreferredSize(new java.awt.Dimension(200, 30));
        gestionar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionar_producto(evt);
            }
        });
        Producto.add(gestionar_producto);

        actualizar_stock.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        actualizar_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo-producto.png")));
        actualizar_stock.setText("Actualizar Stock");
        actualizar_stock.setPreferredSize(new java.awt.Dimension(200, 30));
        actualizar_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar_stock(evt);
            }
        });
        Producto.add(actualizar_stock);

        Barra_interfaz.add(Producto);

        Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png")));
        Cliente.setText("Cliente");
        Cliente.setFont(new java.awt.Font("Tahoma", 1, 14));
        Cliente.setPreferredSize(new java.awt.Dimension(150, 50));

        nuevo_cliente.setFont(new java.awt.Font("Tahoma", 1, 14));
        nuevo_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); 
        nuevo_cliente.setText("Nuevo Cliente");
        nuevo_cliente.setPreferredSize(new java.awt.Dimension(190, 30));
        nuevo_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo_cliente(evt);
            }
        });
        Cliente.add(nuevo_cliente);

        gestionar_cliente.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        gestionar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png")));
        gestionar_cliente.setText("Gestionar Clientes");
        gestionar_cliente.setPreferredSize(new java.awt.Dimension(190, 30));
        gestionar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionar_cliente(evt);
            }
        });
        Cliente.add(gestionar_cliente);

        Barra_interfaz.add(Cliente);

        boton_mesas.setFont(new java.awt.Font("Tahoma", 1, 14));
        boton_mesas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mesaicon.png")));
        boton_mesas.setText("Gestionar Mesas");
        boton_mesas.setPreferredSize(new java.awt.Dimension(190, 30));
        boton_mesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionar_mesa(evt);
            }
        });
        Cliente.add(boton_mesas);

        Barra_interfaz.add(Cliente);

        Categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/categorias.png"))); 
        Categoria.setText("Categoria");
        Categoria.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        Categoria.setPreferredSize(new java.awt.Dimension(150, 50));

        nueva_categoria.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        nueva_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png")));
        nueva_categoria.setText("Nueva Categoria");
        nueva_categoria.setPreferredSize(new java.awt.Dimension(210, 30));
        nueva_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nueva_categoria(evt);
            }
        });
        Categoria.add(nueva_categoria);

        gestionar_categoria.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        gestionar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/configuraciones.png"))); 
        gestionar_categoria.setText("Gestionar Categorias");
        gestionar_categoria.setPreferredSize(new java.awt.Dimension(210, 30));
        gestionar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionar_categoria(evt);
            }
        });
        Categoria.add(gestionar_categoria);

        Barra_interfaz.add(Categoria);

        Reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/historial1.png")));
        Reportes.setText("Reportes");
        Reportes.setFont(new java.awt.Font("Tahoma", 1, 14));
        Reportes.setPreferredSize(new java.awt.Dimension(150, 50));

        reportes_clientes.setFont(new java.awt.Font("Tahoma", 1, 14));
        reportes_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportes.png")));
        reportes_clientes.setText("Reportes Clientes");
        reportes_clientes.setPreferredSize(new java.awt.Dimension(200, 30));
        reportes_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportes_clientes(evt);
            }
        });
        Reportes.add(reportes_clientes);

        Barra_interfaz.add(Reportes);

        Cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png"))); 
        Cerrar_sesion.setText("Cerrar Sesión");
        Cerrar_sesion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cerrar_sesion.setPreferredSize(new java.awt.Dimension(150, 50));

        cerrar_sesion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png"))); 
        cerrar_sesion.setText("Cerrar Sesión");
        cerrar_sesion.setPreferredSize(new java.awt.Dimension(150, 30));
        cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cerrar_sesion(evt);
            }
        });
        Cerrar_sesion.add(cerrar_sesion);

        Barra_interfaz.add(Cerrar_sesion);

        setJMenuBar(Barra_interfaz);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        pack();
    }


    //USUARIO
    private void nuevo_usuario(java.awt.event.ActionEvent evt) {
       InterNuevoUsuario usuario = new InterNuevoUsuario();
         jDesktopPane_menu.add(usuario);
         usuario.setVisible(true);
    }

    private void gestionar_usuario(java.awt.event.ActionEvent evt) {
        InterGestionarUsuario usuario = new InterGestionarUsuario();
        jDesktopPane_menu.add(usuario);
        usuario.setVisible(true); 
    }


    //MENU
    private void Vermenu(java.awt.event.ActionEvent evt) {
        // Crear la acción para abrir el menú
        MenuInterface action = new VerMenu(jDesktopPane_menu);
        action.openMenu(); // Llamar a openMenu para abrir el menú
    }


    //PRODUCTO
    private void nuevo_producto(java.awt.event.ActionEvent evt) {
       InterAlmacen interalmacen = new InterAlmacen();
       jDesktopPane_menu.add(interalmacen);
       interalmacen.setVisible(true);    
    }

    private void gestionar_producto(java.awt.event.ActionEvent evt) {
        InterGestionarAlmacen intergestionaralmacen = new InterGestionarAlmacen();
        jDesktopPane_menu.add(intergestionaralmacen);
        intergestionaralmacen.setVisible(true);
    }

    private void actualizar_stock(java.awt.event.ActionEvent evt) {
     InterActualizarStock interActualizarStock = new InterActualizarStock();
       jDesktopPane_menu.add(interActualizarStock);
       interActualizarStock.setVisible(true); 
    }


    //CLIENTES
    private void nuevo_cliente(java.awt.event.ActionEvent evt) {
        InterNuevoCliente cliente = new InterNuevoCliente();
        jDesktopPane_menu.add(cliente);
        cliente.setVisible(true); 
    }

    private void gestionar_cliente(java.awt.event.ActionEvent evt) {
        InterGestionarCliente cliente = new InterGestionarCliente();
        jDesktopPane_menu.add(cliente);
        cliente.setVisible(true);    
    }

    private void gestionar_mesa(java.awt.event.ActionEvent evt) {
        InterMesa mesa = new InterMesa();
        jDesktopPane_menu.add(mesa);
        mesa.setVisible(true);    
    }


    //CATEGORIAS
    private void nueva_categoria(java.awt.event.ActionEvent evt) {
        InterCategoria categoria = new InterCategoria();
        jDesktopPane_menu.add(categoria);
        categoria.setVisible(true);
    }


    private void gestionar_categoria(java.awt.event.ActionEvent evt) {
       InterGestionarCategoria intergestionarcategoria = new InterGestionarCategoria();
       jDesktopPane_menu.add(intergestionarcategoria);
       intergestionarcategoria.setVisible(true);
    }


    //REPORTES
    private void reportes_clientes(java.awt.event.ActionEvent evt) {
        InterPedidosWeb pedidosWeb = new InterPedidosWeb();
        jDesktopPane_menu.add(pedidosWeb);
        pedidosWeb.setVisible(true);
    }

    
    //CERRAR SESION
    private void Cerrar_sesion(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    
    //Codigo de arranque estándar de la biblioteca Java Swing
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    //Variables
    private javax.swing.JMenuBar Barra_interfaz; //Barra de interfaz donde estarán todos los botones
    //USUARIO
    private javax.swing.JMenu Usuario;
    private javax.swing.JMenuItem nuevo_usuario;
    private javax.swing.JMenuItem gestionar_usuario;
    //MENU
    private javax.swing.JMenu Menu;
    private javax.swing.JMenuItem Vermenu;
    //PRODUCTO
    private javax.swing.JMenu Producto;
    private javax.swing.JMenuItem nuevo_producto;
    private javax.swing.JMenuItem gestionar_producto;
    private javax.swing.JMenuItem actualizar_stock;
    //CLIENTES
    private javax.swing.JMenu Cliente;
    private javax.swing.JMenuItem nuevo_cliente;
    private javax.swing.JMenuItem gestionar_cliente;
    private javax.swing.JMenuItem boton_mesas;
    //CATEGORIA
    private javax.swing.JMenu Categoria;
    private javax.swing.JMenuItem nueva_categoria;
    private javax.swing.JMenuItem gestionar_categoria;
    //REPORTES
    private javax.swing.JMenu Reportes;
    private javax.swing.JMenuItem reportes_clientes;
    //CERRAR SESION
    private javax.swing.JMenu Cerrar_sesion;
    private javax.swing.JMenuItem cerrar_sesion;
}
