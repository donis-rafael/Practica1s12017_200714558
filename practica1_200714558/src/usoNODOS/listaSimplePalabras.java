/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoPalabras;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class listaSimplePalabras {
    nodoPalabras inicio;

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

    public void recorrer() {
        if (inicio != null) {
            nodoPalabras temp = inicio;
            while (temp != null) {
                System.out.println(temp.getPalabra());
                temp = temp.siguiente;
            }
        }
    }
}
