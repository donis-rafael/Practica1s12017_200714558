/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoPalabras;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class listaSimplePalabras {

    nodoPalabras inicio;
    StringBuffer buffer;

    public listaSimplePalabras() {
        inicio = null;
    }

    public void insertar(nodoPalabras nuevoNodo) {
        if (inicio == null) {
            inicio = nuevoNodo;

        } else {
            nodoPalabras aux = inicio;
            nodoPalabras aux2 = inicio;
            while (aux != null) {
                aux2 = aux;
                aux = aux.siguiente;
            }

            aux = nuevoNodo;
            aux2.siguiente = aux;
            aux.siguiente = null;
        }
    }

    public int tamano() {
        if (inicio != null) {
            int tam = 0;
            nodoPalabras temp = inicio;
            while (temp != null) {
                //System.out.println(temp.getPalabra());
                tam++;
                temp = temp.siguiente;
            }
            return tam;
        }
        return 0;
    }

    public void graficar() {
        File miDir = new File(".");
        try {
            String dotPath = miDir.getCanonicalPath() + File.separator + "grafo_Diccionario.txt";
            String jpgPath = miDir.getCanonicalPath() + File.separator + "grafo_Diccionario.jpg";

            buffer = new StringBuffer();
            buffer.append("\nDigraph G {\n");

            GeneradorDot();

            buffer.append("}");
            this.creararchivo(dotPath, buffer.toString());

            doDot(dotPath, jpgPath);

        } catch (Exception e) {
        }
    }

    void GeneradorDot() {
        nodoPalabras aux = inicio;
        for (int a = 0; a < tamano(); a++) {
            buffer.append("Nodo").append(a).append("[label=\"").append(aux.getPalabra()).append("\", style=filled, fillcolor=\"#7FB07F\", shape=doubleoctagon];\n");
            aux = aux.siguiente;
        }
        buffer.append("\n");

        for (int a = 0; a < (tamano() - 1); a++) {
            buffer.append("Nodo").append(a).append(" -> Nodo").append(a + 1).append(";\n");
            buffer.append("{rank=same; Nodo").append(a).append(" Nodo").append(a + 1).append("}\n");
        }
    }

    public synchronized void creararchivo(String dotPath, String toString) {
        FileWriter archivo = null;

        try {
            archivo = new FileWriter(dotPath);
        } catch (IOException ex) {
            Logger.getLogger(listaSimplePalabras.class.getName()).log(Level.SEVERE, null, ex);
        }
        File a = new File(dotPath);
        if (!a.exists()) {
            return;
        }
        try (PrintWriter printwriter = new PrintWriter(archivo)) {
            printwriter.print(toString);
            printwriter.close();
        }

    }

    void doDot(String DotPath, String jpgPath) {

        try {
            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String PathOfThefileInput = DotPath;
            String PathOfThefileOutput = jpgPath;

            String format = "-Tjpg";
            String Ofunction = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = format;
            cmd[2] = PathOfThefileInput;
            cmd[3] = Ofunction;
            cmd[4] = PathOfThefileOutput;

            Runtime runtim = Runtime.getRuntime();
            runtim.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }
}
