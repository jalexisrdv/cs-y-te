/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import vistas.VentanaPrincipal;

/**
 *
 * @author jarv
 */
public class OyenteVentana extends WindowAdapter {
    
    private VentanaPrincipal ventana;
    
    public OyenteVentana(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }
    
    @Override
    public void windowClosing(WindowEvent window) {
        int opcion = JOptionPane.showConfirmDialog(ventana, "¿Desea salir del programa?",
                "Saliendo del programa", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
}
