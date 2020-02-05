/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author jarv
 */
public class TiposDeErrores {

    private JTextArea campoResultados;
    private JComboBox comboValores;
    private JComboBox comboValores1;
    private JTextField campoValor;
    private JTextField campoValor1;
    private String tipoValorCalculado;
    private double errorTrue;
    private double valorTrue;
    private double valorAproximado;

    /*Debido a que el usuario puede insertar valores utilizando la coma y espacios en blanco entre los numeros, 
     debo eliminar ese caracrter para no tener error al realizar casting.*/
    public String eliminaComa(String soloNumeros) {
        String aux = soloNumeros.replace(" ", "");
        return aux.replace(",", "");
    }

    public void gestionaResultados() {
        String tipoPrimerValor = comboValores.getSelectedItem().toString();
        String tipoSegundoValor = comboValores1.getSelectedItem().toString();
        try {
            double numero = Double.parseDouble(eliminaComa(campoValor.getText()));
            double numero1 = Double.parseDouble(eliminaComa(campoValor1.getText()));
            double resultado = 0;
            campoResultados.setText("");
            /*Compara como se introdujo el valor mediante el comoBox, como puede surgir el caso en donde
             se introducen al revés los valores se han creado dos condicionales para obtener el resultado
             respecto a los valores seleccionados del comboBox*/

            //Calcula el valor verdadero
            if (tipoPrimerValor.equals("Valor Aproximado") && tipoSegundoValor.equals("Error Verdadero")) {
                resultado = calculaValorTrue(numero, numero1);
                tipoValorCalculado = "Valor Verdadero";
            }
            if (tipoPrimerValor.equals("Error Verdadero") && tipoSegundoValor.equals("Valor Aproximado")) {
                resultado = calculaValorTrue(numero1, numero);
                tipoValorCalculado = "Valor Verdadero";
            }

            //Calcula el error verdadero
            if (tipoPrimerValor.equals("Valor Verdadero") && tipoSegundoValor.equals("Valor Aproximado")) {
                resultado = calculaErrorTrue(numero, numero1);
                tipoValorCalculado = "Error Verdadero";
            }
            if (tipoPrimerValor.equals("Valor Aproximado") && tipoSegundoValor.equals("Valor Verdadero")) {
                resultado = calculaErrorTrue(numero1, numero);
                tipoValorCalculado = "Error Verdadero";
            }

            //Calcula el valor aproximado
            if (tipoPrimerValor.equals("Valor Verdadero") && tipoSegundoValor.equals("Error Verdadero")) {
                resultado = calculaValorAproximado(numero, numero1);
                tipoValorCalculado = "Valor Aproximado";
            }
            if (tipoPrimerValor.equals("Error Verdadero") && tipoSegundoValor.equals("Valor Verdadero")) {
                resultado = calculaValorAproximado(numero1, numero);
                tipoValorCalculado = "Valor Aproximado";
            }
            if (!tipoValorCalculado.isEmpty()) {
                campoResultados.append(tipoValorCalculado + " = " + Math.abs(resultado) + "\n");
                iniciaValores(tipoPrimerValor, numero);
                iniciaValores(tipoSegundoValor, numero1);
                iniciaValores(tipoValorCalculado, resultado);
                aplicaFormulasRestantes();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(campoResultados.getParent(), "No puedes introducir letras o dejar vacio el campo",
                    "Solo numeros", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*Detecta el resultado obtenido e inicia el valor faltante.
     Esto permite tener todos los valores de las variables y asi calcular aplicar las demas formulas*/
    public void aplicaFormulasRestantes() {
        double errorRelativoTrue = calculaErrorRelativoTrue(errorTrue, valorTrue);
        double errorRelativoPorcentualTrue = calculaErrorRelativoPorcentualTrue(errorTrue, valorTrue);
        campoResultados.append("Error Relativo Verdadero = " + Math.abs(errorRelativoTrue) + "\n");
        campoResultados.append("Error Relativo Porcentual Verdadero = " + Math.abs(errorRelativoPorcentualTrue) + "%\n");
    }

    //Inicia los valores de las variables que ha introducido el usuario y el resultado obtenido
    public void iniciaValores(String camposValor, double numero) {
        switch (camposValor) {
            case "Valor Verdadero":
                valorTrue = numero;
                break;
            case "Error Verdadero":
                errorTrue = numero;
                break;
            case "Valor Aproximado":
                valorAproximado = numero;
        }
    }

    public double calculaValorTrue(double valorAproximado, double errorTrue) {
        double valorTrue = valorAproximado + errorTrue;
        return valorTrue;
    }

    public double calculaValorAproximado(double valorTrue, double errorTrue) {
        double valorAproximado = valorTrue - errorTrue;
        return valorAproximado;
    }

    public double calculaErrorTrue(double valorTrue, double valorAproximado) {
        double errorTrue = valorTrue - valorAproximado;
        return errorTrue;
    }

    public double calculaErrorRelativoTrue(double errorTrue, double valorTrue) {
        double errorRelativoTrue = errorTrue / valorTrue;
        return errorRelativoTrue;
    }

    public double calculaErrorRelativoPorcentualTrue(double errorTrue, double valorTrue) {
        double errorRelativoPorcentualTrue = (calculaErrorRelativoTrue(errorTrue, valorTrue)) * 100;
        return errorRelativoPorcentualTrue;
    }

    public void setCampoResultados(JTextArea campoResultados) {
        this.campoResultados = campoResultados;
    }

    public void setComboValores(JComboBox comboValores) {
        this.comboValores = comboValores;
    }

    public void setComboValores1(JComboBox comboValores1) {
        this.comboValores1 = comboValores1;
    }

    public void setCampoValor(JTextField campoValor) {
        this.campoValor = campoValor;
    }

    public void setCampoValor1(JTextField campoValor1) {
        this.campoValor1 = campoValor1;
    }

}
