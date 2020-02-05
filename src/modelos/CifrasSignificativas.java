package modelos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author jarv
 */
public class CifrasSignificativas {

    private JTextArea campoNumerosOperaciones;
    private JTextArea campoNumerosContador;
    private JTextArea campoResultadosContador;
    private JTextArea campoResultadosOperaciones;

    //Carga los valores leidos desde un archivo almacenados dentro de una lista de string.
    //Los valores del archivo son leidos en la clase OyenteMenu mediante el metodo opcionAbrir()
    public void cargaValoresAlCampo(String panelActivo, ArrayList<String> numeros) {
        switch (panelActivo) {
            case "Contador":
                for (String numero : numeros) {
                    campoNumerosContador.append(numero + "\n");
                }
                break;
            case "Operaciones":
                for (String numero : numeros) {
                    campoNumerosOperaciones.append(numero + "\n");
                }
        }
    }

    /*Debido a que el usuario puede insertar valores utilizando la coma y espacios en blanco entre los numeros, 
     debo eliminar ese caracrter para no tener error al realizar casting.*/
    public String eliminaComa(String soloNumeros) {
        String aux = soloNumeros.replace(" ", "");
        return aux.replace(",", "");
    }

    /*Este metodo se encarga de mostrar el numero de cifras significativas para cada valor introducido
     en nuestro campo de resultados*/
    public void cuentaCS() {
        try {
            if (!campoNumerosContador.getText().isEmpty()) {
                String[] numeros = campoNumerosContador.getText().split("\n");
                String soloNumeros = guardaSoloNumeros(numeros);
                String[] auxNumeros = soloNumeros.split("\n");
                numeros = eliminaComa(soloNumeros).split("\n");
                String textoCampoResultados = "";
                campoNumerosContador.setText(soloNumeros);
                for (int i = 0; i < numeros.length; i++) {
                    String numero = numeros[i];
                    double aux = Double.parseDouble(numero);
                    textoCampoResultados += auxNumeros[i] + " = " + contarCifrasSignificativas(numero) + " CS" + "\n";
                }
                campoResultadosContador.setText(textoCampoResultados);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(campoNumerosOperaciones.getParent(), "Elimina las letras para poder contar las cifras significativas",
                    "Letras introducidas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*Gestiona el tipo de operacion a realizar*/
    public void operacionesCS(String tipoOperacion) {
        try {
            String[] numeros = campoNumerosOperaciones.getText().split("\n");
            String soloNumeros = guardaSoloNumeros(numeros);
            numeros = eliminaComa(soloNumeros).split("\n");
            int contNumeros = numeros.length;
            double resultado = 0;
            campoNumerosOperaciones.setText(soloNumeros);
            if (contNumeros >= 2) {
                switch (tipoOperacion) {
                    case "sumar":
                        for (int i = 0; i < numeros.length; i++) {
                            resultado += Double.parseDouble(numeros[i]);
                        }
                        formatoResultadoSR(tipoOperacion, numeros, resultado);
                        break;
                    case "restar":
                        if (contNumeros <= 2) {
                            resultado = Double.parseDouble(numeros[0]) - Double.parseDouble(numeros[1]);
                            formatoResultadoSR(tipoOperacion, numeros, resultado);
                        } else {
                            JOptionPane.showMessageDialog(campoNumerosOperaciones.getParent(), "No puedes restar mas de 2 cifras",
                                    "Restas excedidas", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case "multiplicar":
                        resultado = 1;
                        for (int i = 0; i < numeros.length; i++) {
                            resultado *= Double.parseDouble(numeros[i]);
                        }
                        formatoResultadoMD(tipoOperacion, numeros, resultado);
                        break;
                    case "dividir":
                        if (contNumeros <= 2) {
                            resultado = Double.parseDouble(numeros[0]) / Double.parseDouble(numeros[1]);
                            formatoResultadoMD(tipoOperacion, numeros, resultado);
                        } else {
                            JOptionPane.showMessageDialog(campoNumerosOperaciones.getParent(), "No puedes dividir mas de 2 cifras",
                                    "Divisiones excedidas", JOptionPane.INFORMATION_MESSAGE);
                        }
                }
            } else {
                JOptionPane.showMessageDialog(campoNumerosOperaciones.getParent(), "Debes tener al menos 2 cifras para realizar operaciones",
                        "Numero de cifras inferior", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(campoNumerosOperaciones.getParent(), "Elimina las letras para poder realizar operaciones",
                    "Letras introducidas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*Se encarga de filtrar el texto introducido del usuario para guardar solo numeros*/
    public String guardaSoloNumeros(String[] numeros) {
        String numero;
        String guardaSoloNumeros = "";
        for (int i = 0; i < numeros.length; i++) {
            numero = numeros[i];
            if (!numero.matches("[a-zA-Z]*")) {/*Mediante esta expresion regular solo detectamos numeros*/

                guardaSoloNumeros += numero + "\n";
            }
        }
        return guardaSoloNumeros;
    }

    //Ajusta al formato de respuesta, respecto a los criterios (reglas) para suma y resta
    public void formatoResultadoSR(String tipoOperacion, String[] numeros, double resultado) {
        int numeroMenorDecimales = numeroMenorDecimales(numeros);
        String resultadoFormateado = String.format("%." + numeroMenorDecimales + "f", resultado);
        campoResultadosOperaciones.setText("RESULTADO AL " + tipoOperacion.toUpperCase() + " = " + resultadoFormateado
                + " tiene " + contarCifrasSignificativas(resultadoFormateado) + " CS");
    }

    //Ajusta al formato de respuesta, respecto a los criterios (reglas) para multiplicacion y division
    public void formatoResultadoMD(String tipoOperacion, String[] numeros, double resultado) {
        int posicionNumeroMenorCS = posicionNumeroMenorCS(numeros);
        int numeroMenorCS = contarCifrasSignificativas(numeros[posicionNumeroMenorCS]);
        String numeroDecimales = "";
        for (int i = 1; i < numeroMenorCS; i++) {
            numeroDecimales += "0";
        }
        String punto = (numeroDecimales.length() >= 1) ? "." : "";
        DecimalFormat notacionCientifica = new DecimalFormat("0" + punto + numeroDecimales + "E0");
        String resultadoFormateado = notacionCientifica.format(resultado);
        campoResultadosOperaciones.setText("RESULTADO AL " + tipoOperacion.toUpperCase()
                + " = " + resultadoFormateado + " tiene " + contarCifrasSignificativas(resultadoFormateado) + " CS");
    }

    //Devuelve la posicion del vector con el numero de menor cifras significativas
    public int posicionNumeroMenorCS(String[] numeros) {
        int posicionNumeroMenorCS = 0;
        int numeroMenorCS = contarCifrasSignificativas(numeros[0]);
        for (int i = 1; i < numeros.length; i++) {
            int aux = contarCifrasSignificativas(numeros[i]);
            if (numeroMenorCS > aux) {
                posicionNumeroMenorCS = i;
            }
        }
        return posicionNumeroMenorCS;
    }

    /*Cuenta las cifras significativas*/
    public int contarCifrasSignificativas(String numeros) {
        int contadorCS = 0;
        boolean banderaNumeroSignificativo = false;
        boolean banderaExponencial = false;//si es true ya no se cuentan CS
        for (int j = 0; j < numeros.length(); j++) {
            char letra = numeros.charAt(j);
            if ((letra != '0' && letra != '.' && letra != ',') && !banderaNumeroSignificativo) {
                banderaNumeroSignificativo = true;
            }
            if (letra == 'E' || letra == 'e') {
                banderaExponencial = true;
            }
            if (letra != '.' && letra != ',' && letra != '-' && letra != '+' && banderaNumeroSignificativo && !banderaExponencial) {
                contadorCS++;
            }
        }
        return contadorCS;
    }

    /*Cuenta el numero de decimales que tiene cada cifra y devuelve el valor mas pequeño respecto al numero de decimales
     Ejemplo: 12.3456 + 45.6
     Si analizamos las dos cifras anteriores mediante este metodo, devolvera como valor el numero UNO,
     debido a que es el menor numero de decimales.*/
    public int numeroMenorDecimales(String[] numeros) {
        int numMenorDecimales = 0;
        Pattern patron = Pattern.compile("\\.(\\d*)");
        Matcher emparejador;
        try {
            emparejador = patron.matcher(numeros[0]);
            emparejador.find();
            numMenorDecimales = emparejador.group(1).length();
            for (int i = 1; i < numeros.length; i++) {
                emparejador = patron.matcher(numeros[i]);
                emparejador.find();
                int numDecimales = emparejador.group(1).length();
                if (numMenorDecimales > numDecimales) {
                    numMenorDecimales = numDecimales;
                }
            }
        } catch (IllegalStateException e) {
            /*Si ocurre una excepcion quiere decir que el patron no ha encontrado decimales,
             debido a que al tratar de extraer los numeros mediante group(1) no existe una variable
             donde este almacenado un valor (no existen decimales)*/
            numMenorDecimales = 0;
        }
        return numMenorDecimales;
    }

    public void setCampoNumerosOperaciones(JTextArea campoNumerosOperaciones) {
        this.campoNumerosOperaciones = campoNumerosOperaciones;
    }

    public void setCampoNumerosContador(JTextArea campoNumerosContador) {
        this.campoNumerosContador = campoNumerosContador;
    }

    public void setCampoResultadosContador(JTextArea campoResultadosContador) {
        this.campoResultadosContador = campoResultadosContador;
    }

    public void setCampoResultadosOperaciones(JTextArea campoResultadosOperaciones) {
        this.campoResultadosOperaciones = campoResultadosOperaciones;
    }

}
