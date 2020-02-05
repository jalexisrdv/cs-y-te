/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javax.swing.ImageIcon;

/**
 *
 * @author jarv
 */
public class ConceptosRandomInicio {
    
    public ImageIcon getImagenRandom() {
        ImageIcon imagen = null;
        int num = (int) (Math.random() * 4) + 1;
        switch(num) {
            case 1:
                imagen = getImagen("exactitud");
                imagen.setDescription("Exactitud");
                break;
            case 2:
                imagen = getImagen("precision");
                imagen.setDescription("Precisión");
                break;
            case 3:
                imagen = getImagen("inexactitud");
                imagen.setDescription("Inexactitud (sesgo)");
                break;
            case 4:
                imagen = getImagen("imprecision");
                imagen.setDescription("Imprecisión (incertidumbre)");
        }
        return imagen;
    }
    
    public ImageIcon getImagen(String nombreImagen) {
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/" + nombreImagen + ".png"));
        return imagen;
    }
    
    public String getDefinicion(String tipoDefinicion) {
        String definicion = "";
        switch(tipoDefinicion) {
            case "Exactitud":
                definicion = "La exactitud se refiere a qué tan cercano está el valor calculado o medido del valor verdadero.";
                break;
            case "Precisión":
                definicion = "La precisión se refiere a qué tan cercanos se encuentran, unos de otros, diversos valores calculados o medidos.";
                break;
            case "Inexactitud (sesgo)":
                definicion = "Se define como una desviación sistemática del valor verdadero.";
                break;
            case "Imprecisión (incertidumbre)":
                definicion = "Se refiere a la magnitud en la dispersión de los disparos.";
                
        }
        return definicion;
    }
    
}
