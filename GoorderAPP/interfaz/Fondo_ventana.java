package interfaz; // En el mismo paquete

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel; // <-- ¡Este es el cambio!

// Esta clase hereda de JPanel, no de JDesktopPane
public class Fondo_ventana extends JPanel {
    
    private Image imagenFondo;

    public Fondo_ventana(String rutaImagen) {
        URL imgUrl = getClass().getResource(rutaImagen);

        if (imgUrl != null) {
            this.imagenFondo = new ImageIcon(imgUrl).getImage();
        } else {
            System.err.println("No se pudo encontrar la imagen de fondo: " + rutaImagen);
            this.imagenFondo = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (imagenFondo != null) {
            // Dibuja la imagen de fondo, escalándola al tamaño del panel
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}