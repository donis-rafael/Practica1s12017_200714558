/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoListaFichas;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica1_200714558.*;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class listaSimpleFichasJugador {

    nodoListaFichas inicio, ultimo, nuevoNodo;
    StringBuffer buffer;

    public listaSimpleFichasJugador() {
        inicio = ultimo = null;
    }

    public void ingresar() {
        for (int tam = tamano(); tam < 7; tam++) {
            String a[];
            a = Practica1_200714558.ini.tab.fichasCola.eliminar().split(",");
            char letra = a[0].charAt(0);
            int ptos = Integer.parseInt(a[1]);
            nuevoNodo = new nodoListaFichas(letra, ptos);
            if (inicio == null) {
                inicio = ultimo = nuevoNodo;

            } else {
                ultimo.siguiente = nuevoNodo;
                ultimo = ultimo.siguiente;
                ultimo.siguiente = null;
            }
        }
    }

    public void imprimir() {
        if (inicio != null) {
            nodoListaFichas aux = inicio;
            while (aux != null) {
                System.out.println(aux.getLetra());
                aux = aux.siguiente;
            }
        }
    }

    public int eliminar(char letra) {
        if (inicio != null) {
            nodoListaFichas aux = inicio, aux2 = inicio;
            if (inicio.getLetra() == letra) {
                nodoListaFichas temp = inicio;
                inicio = inicio.siguiente;
                temp.siguiente = null;
                temp = null;
                return 1;

            } else {
                while (aux != null) {
                    if (aux.getLetra() == letra) {
                        nodoListaFichas temp = aux;
                        aux2.siguiente = aux.siguiente;
                        temp.siguiente = null;
                        temp = null;
                        return 1;
                    }
                    aux2 = aux;
                    aux = aux.siguiente;
                }
            }
            return 0;
        }
        return 0;
    }
    
    public int tamano() {
        if (inicio != null) {
            int tam = 0;
            nodoListaFichas temp = inicio;
            while (temp != null) {
                //System.out.println(temp.getPalabra());
                tam++;
                temp = temp.siguiente;
            }
            return tam;
        }
        return 0;
    }

    public void graficar(String idJugador) {
        File miDir = new File(".");
        try {
            String dotPath = miDir.getCanonicalPath() + File.separator + "grafo_FichasJugador.txt";
            String jpgPath = miDir.getCanonicalPath() + File.separator + "grafo_FichasJugador.jpg";

            buffer = new StringBuffer();
            buffer.append("\nDigraph G {\n");

            GeneradorDot(idJugador);

            buffer.append("}");
            this.creararchivo(dotPath, buffer.toString());

            doDot(dotPath, jpgPath);

        } catch (Exception e) {
        }
    }

    void GeneradorDot(String idJugador) {
        nodoListaFichas aux = inicio;
        buffer.append("NodoA [label=\"").append(idJugador).append("\", style=filled, fillcolor=\"#84B787\", shape=tripleoctagon];\n");
        
        for (int a = 0; a < tamano(); a++) {
            buffer.append("Nodo").append(a).append("[label=\"").append(aux.getLetra()).append("\", style=filled, fillcolor=\"#7FB07F\", shape=doubleoctagon];\n");
            aux = aux.siguiente;
        }
        buffer.append("\n");

            buffer.append("NodoA -> Nodo0;\n");
            buffer.append("{rank=same; NodoA Nodo0}\n");
            
        for (int a = 0; a < (tamano()-1); a++) {
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
