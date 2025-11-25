package interfaz;
import conexion.Conexion;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import controlador.Ctrl_Cliente;
import controlador.Ctrl_Mesas;


public class MenuComponent extends JInternalFrame {
    private JComboBox<String> comboCategorias;
    private JPanel panelProductos;
    private JButton botonEliminar; 
    private JPanel panelTotal; 
    private JLabel totalLabel; 
    private double totalTicket = 0.0;
    
    private List<Producto> productosTicket = new ArrayList<>();
    private JTable tableTicket;
    private DefaultTableModel tableModel;
   
    // Constructor 1 (Menú general)
    public MenuComponent() {
        // Configuración del InternalFrame
        setTitle("Menú");
        setSize(1000, 600); 
        setLocation(100, 10); 
        setClosable(true); 
        setResizable(true); 
        setMaximizable(true); 
        setIconifiable(true);

        Fondo_ventana panelFondo = new Fondo_ventana("/imagenes/fondo2.jpg"); // Asegúrate que la ruta sea correcta
        
        // 2. Pon el layout null AL PANEL DE FONDO
        panelFondo.setLayout(null);
        
        // 3. Establece el panel como el contenedor principal
        this.setContentPane(panelFondo);

        comboCategorias = new JComboBox<>();
        comboCategorias.setBounds(10, 10, 250, 30); 
        comboCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoriaSeleccionada = (String) comboCategorias.getSelectedItem();
                if (!categoriaSeleccionada.equals("Seleccione categoria:")) {
                    cargarProductosPorCategoria(categoriaSeleccionada);
                }
            }
        });

        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 2, 10, 10)); 
        panelProductos.setBackground(Color.WHITE); 
        JScrollPane scrollProductos = new JScrollPane(panelProductos);
        scrollProductos.setBounds(10, 60, 500, 400); 

        String[] columnNames = {"Producto", "Precio"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableTicket = new JTable(tableModel);
        tableTicket.setDefaultEditor(Object.class, null);
        JScrollPane scrollTable = new JScrollPane(tableTicket);
        scrollTable.setBounds(520, 60, 450, 400); 

        panelTotal = new JPanel();
        panelTotal.setBounds(520, 470, 450, 40);
        panelTotal.setBackground(Color.WHITE); 

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setForeground(Color.BLACK); 
        panelTotal.add(totalLabel);

        botonEliminar = new JButton("Eliminar Producto Seleccionado");
        botonEliminar.setBounds(10, 470, 500, 30); 
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProductoSeleccionado();
            }
        });

        setLayout(null);
        add(comboCategorias);
        add(scrollProductos);  
        add(scrollTable);      
        add(panelTotal);       
        add(botonEliminar);  
        cargarCategorias();
    }

    // Constructor 2 (Sobrecarga para una mesa específica)
    public MenuComponent(int numero_mesa) {
       
        setTitle("Menú");
        setSize(1000, 600); 
        setLocation(100, 10);  
        setClosable(true); 
        setResizable(true); 
        setMaximizable(true); 
        setIconifiable(true); 
        
        Fondo_ventana panelFondo = new Fondo_ventana("/imagenes/fondo2.jpg"); // Asegúrate que la ruta sea correcta
        
        // 2. Pon el layout null AL PANEL DE FONDO
        panelFondo.setLayout(null);
        
        // 3. Establece el panel como el contenedor principal
        this.setContentPane(panelFondo);

        comboCategorias = new JComboBox<>();
        comboCategorias.setBounds(10, 10, 250, 30); 
        comboCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoriaSeleccionada = (String) comboCategorias.getSelectedItem();
                if (!categoriaSeleccionada.equals("Seleccione categoria:")) {
                    cargarProductosPorCategoria(categoriaSeleccionada);
                }
            }
        });

        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 2, 10, 10)); 
        panelProductos.setBackground(Color.WHITE); 
        JScrollPane scrollProductos = new JScrollPane(panelProductos);
        scrollProductos.setBounds(10, 60, 500, 400); 

        String[] columnNames = {"Producto", "Precio"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableTicket = new JTable(tableModel);
        tableTicket.setDefaultEditor(Object.class, null);

        JScrollPane scrollTable = new JScrollPane(tableTicket);
        scrollTable.setBounds(520, 60, 450, 400); 

        panelTotal = new JPanel();
        panelTotal.setBounds(520, 470, 450, 40);
        panelTotal.setBackground(Color.WHITE); 

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setForeground(Color.BLACK);
        panelTotal.add(totalLabel); 

        botonEliminar = new JButton("Eliminar Producto Seleccionado");
        botonEliminar.setBounds(10, 470, 500, 30); 
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProductoSeleccionado();
            }
        });

        JButton botonFinalizarCompra = new JButton("Finalizar Compra");
        botonFinalizarCompra.setBounds(10, 510, 500, 30); 
        botonFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCompra(numero_mesa);
            }
        });

        setLayout(null);
        add(comboCategorias);
        add(scrollProductos);  
        add(scrollTable);      
        add(panelTotal);       
        add(botonEliminar);    
        add(botonFinalizarCompra); 

        cargarCategorias();
    }
 
    // Metodo para cargar las categorías (Corregido, sin fuga de conexión)
    private void cargarCategorias() {
        String sql = "SELECT * FROM tb_categoria";
        try (Connection cn = Conexion.conectar(); 
             Statement st = cn.createStatement()) {
        
            ResultSet rs = st.executeQuery(sql);
            comboCategorias.removeAllItems();
            comboCategorias.addItem("Seleccione categoria:");
            while (rs.next()) {
                comboCategorias.addItem(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para cargar productos (Corregido, sin fuga de conexión y SQL limpio)
    private void cargarProductosPorCategoria(String categoria) {
        
        panelProductos.removeAll();

        // SQL Corregido: Se quitó p.cantidad
        String sql = "SELECT p.nombre, p.idProducto, p.precio FROM tb_producto p "
                   + "JOIN tb_categoria c ON p.idCategoria = c.idCategoria "
                   + "WHERE c.descripcion = ? AND p.estado = 1"; 

        // CORREGIDO: Connection DENTRO del try-with-resources
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            
            pst.setString(1, categoria); 
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                int idProducto = rs.getInt("idProducto");
                double precio = rs.getDouble("precio");

                String precioFormateado = String.format("$%.2f", precio);
                JButton botonProducto = new JButton(nombreProducto + " - " + precioFormateado);
                
                botonProducto.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Se pasa el idProducto
                        agregarProductoATicket(idProducto, nombreProducto, precio);
                    }
                });

                panelProductos.add(botonProducto);
            }

            panelProductos.revalidate();
            panelProductos.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para agg productos al ticket (Corregido, acepta idProducto)
    private void agregarProductoATicket(int idProducto, String nombreProducto, double precio) {
        totalTicket += precio;

        // Se usa el constructor de Producto que guarda el ID
        Producto producto = new Producto(idProducto, nombreProducto, precio); 
        productosTicket.add(producto);
        
        Object[] row = {nombreProducto, String.format("$%.2f", precio)};
        tableModel.addRow(row);
        totalLabel.setText("Total: $" + String.format("%.2f", totalTicket));
    }

    // Metodo para eliminar producto (Corregido, elimina de la lista y del modelo)
    private void eliminarProductoSeleccionado() {
        int selectedRow = tableTicket.getSelectedRow();

        if (selectedRow != -1) {
            // 1. Obtiene el producto de la lista de datos
            Producto productoAEliminar = productosTicket.get(selectedRow);
        
            // 2. Obtiene el precio real del objeto
            double precio = productoAEliminar.getPrecio();

            // 3. Elimina de AMBAS listas
            productosTicket.remove(selectedRow); // De la lista de datos
            tableModel.removeRow(selectedRow);    // De la tabla visual

            // 4. Resta el precio al total
            totalTicket -= precio;

            totalLabel.setText("Total: $" + String.format("%.2f", totalTicket));
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Metodo para finalizar la compra (Corregido, sin llamada redundante a BBDD)
    private void finalizarCompra(int numeroMesa) {
   
        String nombre = "", apellido = "",  telefono = "";
        
        // CORREGIDO: Se eliminó la llamada a obtenerDatosCliente(numeroMesa)
        // Se hace la consulta directamente y una sola vez
        String query = "SELECT nombre, apellido,  telefono FROM tb_cliente WHERE numero_mesa = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, numeroMesa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                    apellido = rs.getString("apellido");
                    telefono = rs.getString("telefono");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener los datos del cliente.");
            return;
        }

        // Generar ticket
        StringBuilder ticket = new StringBuilder("----- TICKET DE COMPRA -----\n");
        ticket.append("Cliente: ").append(nombre).append(" ").append(apellido).append("\n");
        ticket.append("Teléfono: ").append(telefono).append("\n");

        ticket.append("Productos:\n");
        for (Producto producto : productosTicket) {
            ticket.append("- ").append(producto.getNombre()).append(": $")
                  .append(String.format("%.2f", producto.getPrecio())).append("\n");
        }

        ticket.append("\nTotal: $").append(String.format("%.2f", totalTicket)).append("\n");
        ticket.append("----------------------------");

        JOptionPane.showMessageDialog(this, ticket.toString(), "Ticket de Compra", JOptionPane.INFORMATION_MESSAGE);

        // Eliminar cliente
        Ctrl_Cliente ctrlCliente = new Ctrl_Cliente();
        if (ctrlCliente.eliminarPorMesa(numeroMesa)) {
            JOptionPane.showMessageDialog(this, "Datos del cliente eliminados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar los datos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Actualizar estado de la mesa
        Ctrl_Mesas ctrlmesa = new Ctrl_Mesas();
        ctrlmesa.actualizarEstadoMesaSegunCliente(numeroMesa);
        
        InterGestionarAlmacen gestionAlmacen = new InterGestionarAlmacen();

        // Recorrer la lista para actualizar el stock (CORREGIDO: se obtiene idProducto)
        for (Producto producto : productosTicket) {
            String nombreProducto = producto.getNombre();
            
            // Se obtiene el ID del objeto, sin consultar la BBDD
            int idProducto = producto.getIdProducto();
            
            int cantidadRestada = 1;  
            boolean actualizado = gestionAlmacen.actualizarCantidadProducto(idProducto, cantidadRestada);
            
            if (!actualizado) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el producto: " + nombreProducto, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Limpiar todo al final (CORREGIDO: el orden es correcto)
        productosTicket.clear();
        totalTicket = 0.0;
        tableModel.setRowCount(0);
        totalLabel.setText("Total: $0.00");
        
        dispose();
    }

    // ELIMINADO: El método obtenerDatosCliente(int numeroMesa) ya no es necesario
}