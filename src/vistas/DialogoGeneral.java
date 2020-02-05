/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.IOException;

/**
 *
 * @author jarv
 */
public class DialogoGeneral extends javax.swing.JDialog {

    /**
     * Creates new form DialogoAcercaDe
     */
    public DialogoGeneral(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void setPage(String ruta) {
        try {
            campoPlatilla.setPage(getClass().getResource(ruta));
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEditorPane = new javax.swing.JScrollPane();
        campoPlatilla = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        campoPlatilla.setEditable(false);
        panelEditorPane.setViewportView(campoPlatilla);

        getContentPane().add(panelEditorPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane campoPlatilla;
    private javax.swing.JScrollPane panelEditorPane;
    // End of variables declaration//GEN-END:variables
}