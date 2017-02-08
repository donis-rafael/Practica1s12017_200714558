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
public class nodoPalabras {
    String palabra;
    public nodoPalabras siguiente;
    
    public nodoPalabras(String word){
        palabra = word;
    }
    
    public String getPalabra(){
        return palabra;
    }
    
    public void setPalabra(String word){
        palabra = word;
    }
}
