/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import modelos.CifrasSignificativas;
import vistas.*;

/**
 *
 * @author jarv
 */
public class OyenteBotonesCS implements ActionListener {

    private CifrasSignificativas cifrasSignificativas;
    private JPanel panel;

    public OyenteBotonesCS(CifrasSignificativas cifrasSignificativas, JPanel panel) {
        this.cifrasSignificativas = cifrasSignificativas;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent componente = (JComponent) e.getSource();
        String tipoOperacion = componente.getName();
        switch (tipoOperacion) {
            case "contar":
                cifrasSignificativas.cuentaCS();
                break;
            default:
                cifrasSignificativas.operacionesCS(tipoOperacion);
        }
    }

}
