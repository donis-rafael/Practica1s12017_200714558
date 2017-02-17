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
public class nodoMatriz {
    int X,Y, puntosDefault, puntosLetra, totalPTOS;
    char letra;
    
    public nodoMatriz derecha, izquierda, arriba, abajo;
    
    public nodoMatriz(int x, int y, int ptsDef, int ptosLetra, char letra){
        this.X = x;
        this.Y = y;
        this.puntosDefault = ptsDef;
        this.puntosLetra = ptosLetra;
        this.letra = letra;
        actualizaTotalPuntos();
    }
    
    public void setX(int x){
        this.X = x;
    }
    
    public int getX(){
        return X;
    }
    
    public void setY(int y){
        this.Y = y;
    }
    
    public int getY(){
        return Y;
    }
    
    public void setPuntosDefault(int ptos){
        this.puntosDefault = ptos;
        actualizaTotalPuntos();
    }
    
    public int getPuntosDefault(){
        return puntosDefault;
    }
    
    public void setPuntosLetra(int ptosLetra){
        this.puntosLetra = ptosLetra;
        actualizaTotalPuntos();
    }
    
    public int getPuntosLetra(){
        return puntosLetra;
    }
    
    public void setLetra(char letra){
        this.letra = letra;
    }
    
    public char getLetra(){
        return letra;
    }
    
    public void actualizaTotalPuntos(){
        totalPTOS = puntosDefault * puntosLetra;
    }
    
    public int getTotal(){
        return totalPTOS;
    }
}
