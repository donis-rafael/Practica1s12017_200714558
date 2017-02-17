/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoMatriz;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class matriz {

    public nodoMatriz inicio, ultimo, nuevoNodo;

    public matriz() {
        inicio = ultimo = null;
    }

    public void ingresar(int x, int y, int puntosDef, int puntosLetra, char letra) {
        nuevoNodo = new nodoMatriz(x, y, puntosDef, puntosLetra, letra);

        if (inicio == null) {
            inicio = ultimo = nuevoNodo;

        } else {
            if (x > 0) {
                ultimo.derecha = nuevoNodo;
                ultimo.derecha.izquierda = ultimo;
                ultimo = ultimo.derecha;

            } else {
                ultimo = nuevoNodo;
            }

            if ((y > 0) && (obtieneNodo(x, (y - 1), inicio) != null)) {
                nodoMatriz aux = obtieneNodo(x, (y - 1), inicio);
                aux.abajo = nuevoNodo;
                aux.abajo.arriba = aux;
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
                        if(cualPuntos == 1){//puntos default
                            aux.setPuntosDefault(puntos);
                        }else{//puntos letra
                            aux.setPuntosLetra(puntos);
                        }
                        return 1;
                    }
                    aux = aux.derecha;
                }
            }else{
                aux = aux.abajo;
            }
        }
        return 0;
    }
}
