package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelos.Archivo;
import modelos.CifrasSignificativas;
import modelos.TiposDeErrores;
import vistas.DialogoGeneral;
import vistas.VentanaPrincipal;
import vistas.cifrassignificativas.PanelesCS;
import vistas.tiposerrores.PanelTiposDeErrores;

/**
 *
 * @author jarv
 */
public class OyenteMenus extends KeyAdapter implements ActionListener {

    VentanaPrincipal ventana;
    private CifrasSignificativas cifrasSignificativas;
    private PanelesCS panelesCS;
    private PanelTiposDeErrores panelTiposDeErrores;
    private TiposDeErrores tiposDeErrores;
    private boolean banderaAbrir;//SIRVE PARA CONTROLAR LA LA COMBINACION DE TECLAS PARA ABRIR

    public OyenteMenus(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public void keyReleased(KeyEvent e) {
        if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_A && banderaAbrir) {
            opcionAbrir();
            banderaAbrir = false;
        }
        if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_X) {
            System.exit(0);
        }
        if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_C) {
            abrirPanelCifrasSignificativas();
            banderaAbrir = true;
        }
        if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_T) {
            banderaAbrir = false;
            abrirPanelTiposDeErrores();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent componente = (JComponent) e.getSource();
        switch (componente.getName()) {
            case "abrir":
                opcionAbrir();
                break;
            case "salir":
                System.exit(0);
                break;
            case "cifrasSignificativas":
                abrirPanelCifrasSignificativas();
                break;
            case "tiposDeErrores":
                abrirPanelTiposDeErrores();
                banderaAbrir = false;
                break;
            case "acercaDe":
                abrirVentanaDialogo("AcercaDe", 400, 300);
                break;
            case "ayudaTiposDeErrores":
                abrirVentanaDialogo("AyudaTiposDeErrores", 500, 400);
                break;
            case "ayudaCifrasSignificativas":
                abrirVentanaDialogo("AyudaCifrasSignificativas", 500, 400);
        }
    }

    public void abrirPanelCifrasSignificativas() {
        ventana.getContentPane().removeAll();
        cifrasSignificativas = new CifrasSignificativas();
        panelesCS = new PanelesCS(cifrasSignificativas);
        ventana.getOpcionAbrir().setEnabled(true);//ACTIVO LA OPCION DE ABRIR
        ventana.add(panelesCS);
        ventana.validate();
    }

    public void abrirPanelTiposDeErrores() {
        ventana.getContentPane().removeAll();
        tiposDeErrores = new TiposDeErrores();
        panelTiposDeErrores = new PanelTiposDeErrores(tiposDeErrores);
        ventana.getOpcionAbrir().setEnabled(false);//DESACTIVO LA OPCION DE ABRIR
        ventana.add(panelTiposDeErrores);
        ventana.validate();
    }

    public void opcionAbrir() {
        JFileChooser seleccion = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Texto", "txt");
        seleccion.setFileFilter(filtro);
        int opcion = seleccion.showOpenDialog(ventana);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String ruta = seleccion.getSelectedFile().getPath();
            System.out.println(ruta);
            ArrayList<String> lineas = Archivo.leerArchivo(ruta);
            JTabbedPane paneles = panelesCS.getPestanasPaneles();
            String panelActivado = paneles.getTitleAt(paneles.getSelectedIndex());
            cifrasSignificativas.cargaValoresAlCampo(panelActivado, lineas);
        }
    }

    public void abrirVentanaDialogo(String tipoDialogo, int ancho, int alto) {
        DialogoGeneral dialogo = new DialogoGeneral(ventana, true);
        dialogo.setSize(ancho, alto);
        int cx = ventana.getWidth() / 2;
        int cy = ventana.getHeight() / 2;
        int x1 = cx - dialogo.getWidth() / 2;
        int y1 = cy - dialogo.getHeight() / 2;
        dialogo.setLocation(ventana.getX() + x1, ventana.getY() + y1);
        dialogo.setPage("/plantillashtml/" + tipoDialogo + ".html");
        dialogo.setVisible(true);
    }

}
