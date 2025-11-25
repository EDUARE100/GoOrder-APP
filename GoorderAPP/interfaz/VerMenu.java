
package interfaz;

import javax.swing.JDesktopPane;

public class VerMenu implements MenuInterface {
    int numeroMesa;
    private JDesktopPane desktopPane;

    public VerMenu(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    @Override
    public void openMenu() {
        //instanciar menu
        MenuComponent menu = new MenuComponent();
        desktopPane.add(menu);
        menu.setVisible(true);
    }

    @Override
    public void openMenu(int numeroMesa) {
         // instanciar menu
        MenuComponent menu = new MenuComponent(numeroMesa); 
        desktopPane.add(menu); 
        menu.setVisible(true);
    }
   
}

