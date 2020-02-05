package principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import vistas.*;

/**
 *
 * @author jarv
 */
public class MainCSYTiposDeErrores {

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamañoPantalla = pantalla.getScreenSize();
        int y = (tamañoPantalla.height / 2) - 250;
        int x = (tamañoPantalla.width / 2) - 300;
        ventana.setBounds(x, y, 600, 500);
    }

}
