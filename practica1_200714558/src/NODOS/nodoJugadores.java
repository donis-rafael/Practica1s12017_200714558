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
public class nodoJugadores {
    String id;
    public nodoJugadores siguiente;
    
    public nodoJugadores(String sobre){
        id = sobre;
    }
    
    public void setId(String sobre){
        id=sobre;
    }
    
    public String getId(){
        return id;
    }
}
