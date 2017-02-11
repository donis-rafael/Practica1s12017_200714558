/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoColaFichas;
import NODOS.nodoListaFichas;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class colaFichas {

    public nodoColaFichas inicio;

    public colaFichas() {

    }

    public void ingresar(nodoColaFichas nuevoNodo) {
        if (inicio == null) {
            inicio = nuevoNodo;

        } else {
            nodoColaFichas aux = inicio;
            nodoColaFichas aux2 = inicio;

            while (aux != null) {
                aux2 = aux;
                aux = aux.siguiente;
            }

            aux = nuevoNodo;
            aux2.siguiente = aux;
            aux.siguiente = null;
        }
    }

    public void imprime() {
        if (inicio != null) {
            nodoColaFichas aux = inicio;

            while (aux != null) {
                System.out.println(aux.getLetra());
                aux = aux.siguiente;
            }
        }
    }

    public String eliminar() {//FIFO
        if (inicio != null) {
            nodoColaFichas aux = inicio;
            inicio = inicio.siguiente;
            aux.siguiente = null;
            String a = aux.getLetra() + "," + aux.getPuntos();
            aux = null;
            return a;
        }
        return null;
    }
}
