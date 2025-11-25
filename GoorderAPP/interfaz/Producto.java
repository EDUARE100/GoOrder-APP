
package interfaz;

// Asumo que tienes una interfaz ProductoInterface
public class Producto implements ProductoInterface {
    
    private int idProducto;
    private String nombre;
    private double precio;

    // Este es el único constructor correcto
    public Producto(int idProducto, String nombre, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public String descripcion() {
        return nombre + " - $" + precio;
    }

    // Método corregido (ahora es público y devuelve el valor)
    public int getIdProducto() {
        return idProducto;
    }
}