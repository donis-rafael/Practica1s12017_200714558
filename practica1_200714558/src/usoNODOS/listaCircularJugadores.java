/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usoNODOS;

import NODOS.nodoJugadores;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class listaCircularJugadores {

    nodoJugadores inicio;
    nodoJugadores fin;

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
}
