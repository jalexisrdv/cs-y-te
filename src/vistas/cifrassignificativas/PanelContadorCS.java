/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.cifrassignificativas;

import controladores.OyenteBotonesCS;
import javax.swing.JTextArea;
import modelos.CifrasSignificativas;

/**
 *
 * @author jarv
 */
public class PanelContadorCS extends javax.swing.JPanel {

    /**
     * Creates new form PanelCifrasSignificativas
     */
    public PanelContadorCS(CifrasSignificativas cifrasSignificativas) {
        initComponents();
        cifrasSignificativas.setCampoNumerosContador(campoNumerosContador);
        cifrasSignificativas.setCampoResultadosContador(campoResultadosContador);
        addEventos(new OyenteBotonesCS(cifrasSignificativas, this));
    }

    public final void addEventos(OyenteBotonesCS oyente) {
        botonContar.addActionListener(oyente);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNorte = new javax.swing.JPanel();
        labelCS = new javax.swing.JLabel();
        panelCentro = new javax.swing.JPanel();
        panelNumerosContador = new javax.swing.JScrollPane();
        campoNumerosContador = new javax.swing.JTextArea();
        panelResultadosContador = new javax.swing.JScrollPane();
        campoResultadosContador = new javax.swing.JTextArea();
        botonContar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 20));
        setLayout(new java.awt.BorderLayout());

        labelCS.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelCS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCS.setText("Contador de Cifras Significativas");
        labelCS.setToolTipText("");
        panelNorte.add(labelCS);

        add(panelNorte, java.awt.BorderLayout.NORTH);

        panelCentro.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 10, 1));
        panelCentro.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        panelNumerosContador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Introduce Valores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        campoNumerosContador.setColumns(20);
        campoNumerosContador.setRows(5);
        panelNumerosContador.setViewportView(campoNumerosContador);

        panelCentro.add(panelNumerosContador);

        panelResultadosContador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        campoResultadosContador.setEditable(false);
        campoResultadosContador.setColumns(20);
        campoResultadosContador.setRows(5);
        panelResultadosContador.setViewportView(campoResultadosContador);

        panelCentro.add(panelResultadosContador);

        add(panelCentro, java.awt.BorderLayout.CENTER);

        botonContar.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        botonContar.setText("Contar cifras significativas");
        botonContar.setName("contar"); // NOI18N
        botonContar.setPreferredSize(new java.awt.Dimension(224, 40));
        add(botonContar, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonContar;
    private javax.swing.JTextArea campoNumerosContador;
    private javax.swing.JTextArea campoResultadosContador;
    private javax.swing.JLabel labelCS;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelNorte;
    private javax.swing.JScrollPane panelNumerosContador;
    private javax.swing.JScrollPane panelResultadosContador;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JLabel getLabelCS() {
        return labelCS;
    }
    
    public JTextArea getCampoNumeros() {
        return campoNumerosContador;
    }
    
    public JTextArea getCampoResultados() {
        return campoResultadosContador;
    }

}
