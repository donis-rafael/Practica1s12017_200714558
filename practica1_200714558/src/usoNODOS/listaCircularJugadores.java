/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoJugadores;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class listaCircularJugadores {

    public nodoJugadores inicio;
    public nodoJugadores fin;
    StringBuffer buffer;

    public listaCircularJugadores() {
        inicio = fin = null;
    }

    public void ingresar(nodoJugadores nuevoNodo) {
        if (inicio == null) {
            inicio = fin = nuevoNodo;

        } else if (verSiRepite(nuevoNodo.getId()) == 1) {
            JOptionPane.showMessageDialog(null, "Usuario Ya Existe");

        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
            fin.siguiente = inicio;
        }
    }

    public void imprimir() {
        if (inicio != null) {
            nodoJugadores aux = inicio;
            while (aux != fin) {
                System.out.println(aux.getId());
                aux = aux.siguiente;
            }
            System.out.println(aux.getId());
        }
    }

    public int verSiRepite(String id) {
        nodoJugadores aux = inicio;
        while (aux != fin) {
            if (aux.getId().equals(id)) {
                return 1;
            }
            aux = aux.siguiente;
        }
        if (aux.getId().equals(id)) {
            return 1;
        }

        return 0;
    }

    public int obtenerListaJugador(String id) {
        nodoJugadores aux = inicio;
        while (aux != fin) {
            if (aux.getId().equals(id)) {
                return aux.listaFichas.tamano();
            }
            aux = aux.siguiente;
        }
        if (aux.getId().equals(id)) {
            return aux.listaFichas.tamano();
        }

        return 0;
    }

    public nodoJugadores obtenerJugador(String id) {
        if (inicio != null) {
            nodoJugadores aux = inicio;
            while (aux != fin) {
                if (aux.getId().equals(id)) {
                    return aux;
                }
                aux = aux.siguiente;
            }
            if (aux.getId().equals(id)) {
                return aux;
            }
        }
        return null;
    }
    
    public int tamano() {
        if (inicio != null) {
            int tam = 0;
            nodoJugadores temp = inicio;
            while (temp != fin) {
                //System.out.println(temp.getPalabra());
                tam++;
                temp = temp.siguiente;
            }
            if((temp == fin) && (temp != inicio)){
                tam++;
            }
            if((fin!=null) && (fin == inicio)){
                tam++;
            }
            return tam;
        }
        return 0;
    }
    
    public void graficar() {
        File miDir = new File(".");
        try {
            String dotPath = miDir.getCanonicalPath() + File.separator + "grafo_Jugadores.txt";
            String jpgPath = miDir.getCanonicalPath() + File.separator + "grafo_Jugadores.jpg";

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
        nodoJugadores aux = inicio;
        int tama = tamano();
        for (int a = 0; a < tama ; a++) {
            buffer.append("\"Node").append(a).append("\"[label=\"").append(aux.getId()).append("\", style=filled, fillcolor=\"#7FB07F\", shape=doubleoctagon];\n");
            aux = aux.siguiente;
        }
        buffer.append("\n");

        for (int a = 0; a < (tama - 1); a++) {
            buffer.append("\"Node").append(a).append("\" -> \"Node").append(a + 1).append("\";\n");
            buffer.append("{rank=same; \"Node").append(a).append("\" \"Node").append(a + 1).append("\"}\n");
        }
        
        if(tama > 1){
            buffer.append("\"Node").append(tama-1).append("\" -> \"Node0\";\n");
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
