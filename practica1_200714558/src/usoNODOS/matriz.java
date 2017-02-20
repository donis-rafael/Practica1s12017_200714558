/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoMatriz;
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
public class matriz {

    public nodoMatriz inicio, ultimo, nuevoNodo;
    StringBuffer buffer;
    int contador;

    public matriz() {
        inicio = ultimo = null;
    }

    public void ingresar(int x, int y, int puntosDef, int puntosLetra, char letra) {
        nuevoNodo = new nodoMatriz(x, y, puntosDef, puntosLetra, letra);

        if (inicio == null) {
            inicio = ultimo = nuevoNodo;

        } else {
            nodoMatriz aux = inicio;
            if (x > 0) {
                if (y > 0) {
                    while (aux.abajo != null) {
                        aux = aux.abajo;
                    }
                    while (aux.derecha != null) {
                        aux = aux.derecha;
                    }
                } else {
                    while (aux.derecha != null) {
                        aux = aux.derecha;
                    }
                }
                aux.derecha = nuevoNodo;
                aux.derecha.izquierda = aux;
                ultimo = aux.derecha;

            } else {
                if (y > 0) {
                    while (aux.abajo != null) {
                        aux = aux.abajo;
                    }
                }
                aux.abajo = ultimo = nuevoNodo;
            }

            if ((y > 0) && (obtieneNodo(x, (y - 1), inicio) != null)) {
                nodoMatriz aux2 = obtieneNodo(x, (y - 1), inicio);
                aux2.abajo = nuevoNodo;
                aux2.abajo.arriba = aux2;
            }

        }
    }

    public nodoMatriz obtieneNodo(int x, int y, nodoMatriz empieza) {
        nodoMatriz aux = empieza;
        if (aux.getY() == y) {

            while (aux != null) {
                if (aux.getX() == x) {
                    return aux;
                }
                aux = aux.derecha;
            }

            return null;

        } else {
            return obtieneNodo(x, y, empieza.abajo);
        }
    }

    public int ponerPuntos(int x, int y, int puntos, int cualPuntos) {
        nodoMatriz aux = inicio;
        while (aux != null) {
            if (aux.getY() == y) {
                while (aux != null) {
                    if (aux.getX() == x) {
                        if (cualPuntos == 1) {//puntos default
                            aux.setPuntosDefault(puntos);
                        } else {//puntos letra
                            aux.setPuntosLetra(puntos);
                        }
                        return 1;
                    }
                    aux = aux.derecha;
                }
            } else {
                aux = aux.abajo;
            }
        }
        return 0;
    }

    public int dimension() {
        if (inicio != null) {
            nodoMatriz aux = inicio;
            int tam = 0;
            while (aux != null) {
                tam++;
                aux = aux.derecha;
            }
            return tam;
        }
        return 0;
    }

    public void graficar() {
        File miDir = new File(".");
        try {
            String dotPath = miDir.getCanonicalPath() + File.separator + "grafo_Matriz.txt";
            String jpgPath = miDir.getCanonicalPath() + File.separator + "grafo_Matriz.jpg";

            buffer = new StringBuffer();
            buffer.append("\nDigraph G {\n");
            buffer.append("rank=min;\n");
            buffer.append("node[shape=box, label=\"Start\", style=filled, rankdir=UD];\n");


            GeneradorDot();

            buffer.append("}");
            this.creararchivo(dotPath, buffer.toString());

            doDot(dotPath, jpgPath);

        } catch (Exception e) {
        }
    }

    void GeneradorDot() {
        nodoMatriz aux = inicio;
        nodoMatriz aux2 = inicio;
        int x = dimension();
        System.out.println("Dim= " + x);

        for (int yy = 0; yy < x; yy++) {
            for (int a = 0; a < x; a++) {
                buffer.append("\"Cab").append(a).append(",").append(yy).append("\"[label=\"").append(" X , Y \n(")
                        .append(aux.getX()).append(" , ").append(aux.getY()).append(")\", style=filled, fillcolor=\"#A2E7FF\", fontcolor=\"#00445C\", shape=box];\n");
                aux = aux.derecha;
            }
            aux2 = aux2.abajo;
            aux = aux2;
        }

        for (int yy = 0; yy < x; yy++) {
            for (int a = 0; a < (x - 1); a++) {
                buffer.append("\"Cab").append(a).append(",").append(yy).append("\" -> \"Cab").append(a + 1).append(",").append(yy).append("\"[constraint=false];\n");
                buffer.append("\"Cab").append(a + 1).append(",").append(yy).append("\" -> \"Cab").append(a).append(",").append(yy).append("\"[constraint=false];\n");
                buffer.append("{rank=same; \"Cab").append(a).append(",").append(yy).append("\" \"Cab").append(a + 1).append(",").append(yy).append("\"}\n");
                buffer.append("{rank=same; \"Cab").append(a + 1).append(",").append(yy).append("\" \"Cab").append(a).append(",").append(yy).append("\"}\n");
            }
        }

        for (int a = 0; a < x; a++) {
            for (int yy = 0; yy < (x - 1); yy++) {
                buffer.append("\"Cab").append(a).append(",").append(yy).append("\" -> \"Cab").append(a).append(",").append(yy + 1).append("\"[rankdir=UD];\n");//
                buffer.append("\"Cab").append(a).append(",").append(yy + 1).append("\" -> \"Cab").append(a).append(",").append(yy).append("\"[rankdir=UD];\n");//;\n");// [rankdir=UD];\n");//
            }
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
