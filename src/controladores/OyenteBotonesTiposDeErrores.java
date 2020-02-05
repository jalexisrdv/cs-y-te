/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.TiposDeErrores;
import vistas.tiposerrores.PanelTiposDeErrores;

/**
 *
 * @author jarv
 */
public class OyenteBotonesTiposDeErrores implements ActionListener {
    
    TiposDeErrores tiposDeErrores; 
    PanelTiposDeErrores panel;
    
    public OyenteBotonesTiposDeErrores(TiposDeErrores tiposDeErrores, PanelTiposDeErrores panel) {
        this.tiposDeErrores = tiposDeErrores;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tiposDeErrores.gestionaResultados();
    }
    
}
