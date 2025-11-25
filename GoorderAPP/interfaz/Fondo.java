package interfaz;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class Fondo extends JDesktopPane{
    
    private Image imagenFondo;

    public Fondo(String rutaImagen) {
        // Carga la imagen desde la ruta de recursos
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
        
        // Dibuja la imagen de fondo, escalándola al tamaño del panel
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
