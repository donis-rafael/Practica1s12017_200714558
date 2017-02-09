/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NODOS;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class nodoColaFichas {
    char letra;
    int puntos;
    public nodoColaFichas siguiente;
    
    public nodoColaFichas(char letra, int ptos){
        this.letra = letra;
        this.puntos = ptos;
    }
    
    public void setLetra(char letra){
        this.letra = letra;
    }
    
    public char getLetra(){
        return letra;
    }
    
    public void setPuntos(int ptos){
        puntos = ptos;
    }
    
    public int getPuntos(){
        return puntos;
    }
}
