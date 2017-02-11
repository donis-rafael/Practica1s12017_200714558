/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoListaFichas;
import practica1_200714558.*;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class listaSimpleFichasJugador {

    nodoListaFichas inicio, ultimo, nuevoNodo;

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
        nodoListaFichas aux = inicio;
        int size = 0;
        while (aux.siguiente != null) {
            size++;
            aux = aux.siguiente;
        }
        return size;
    }
}
