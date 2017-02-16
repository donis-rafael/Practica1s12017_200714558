/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_200714558;

import NODOS.*;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import usoNODOS.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class tablero extends javax.swing.JFrame {

    public nodoPalabras nodoPalabra;
    public nodoJugadores nodoUsuario;
    public nodoColaFichas nodoFichasCola;
    public nodoMatriz nodoMatrix;
    public listaSimplePalabras listaPalabras = new listaSimplePalabras();
    public listaCircularJugadores listaJugadores = new listaCircularJugadores();
    public colaFichas fichasCola = new colaFichas();
    public matriz matrix = new matriz();
    String jugadorTurnando;

    public tablero() {
        initComponents();
        formaTablero();
    }

    public void formaTablero() {
        JFileChooser filechooserabrir = new JFileChooser();
        filechooserabrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos XML (*.xml)", "xml");
        filechooserabrir.setFileFilter(filtro);
        int seleccion = filechooserabrir.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader bufferreader;
                File file = filechooserabrir.getSelectedFile();
                bufferreader = new BufferedReader(new FileReader(file.getAbsolutePath()));

                // URL url = new URL("https://sites.google.com/site/falfiles/Home/archivos/ejemplo.xml");
                //BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String entrada;
                String cadena = "";

                while ((entrada = bufferreader.readLine()) != null) {
                    cadena = cadena + entrada;
                }

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                InputSource archivo = new InputSource();
                archivo.setCharacterStream(new StringReader(cadena));

                Document documento = db.parse(archivo);
                documento.getDocumentElement().normalize();

                NodeList nodeLista = documento.getElementsByTagName("descarga");
                System.out.println("Informacionos libros");

                for (int s = 0; s < nodeLista.getLength(); s++) {

                    Node primerNodo = nodeLista.item(s);
                    String titulo;
                    String autor;
                    String hits;

                    if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {

                        Element primerElemento = (Element) primerNodo;

                        NodeList primerNombreElementoLista = primerElemento.getElementsByTagName("titulo");
                        Element primerNombreElemento = (Element) primerNombreElementoLista.item(0);
                        NodeList primerNombre = primerNombreElemento.getChildNodes();
                        titulo = ((Node) primerNombre.item(0)).getNodeValue().toString();
                        System.out.println("Titulo + titulo");

                        NodeList segundoNombreElementoLista = primerElemento.getElementsByTagName("autor");
                        Element segundoNombreElemento = (Element) segundoNombreElementoLista.item(0);
                        NodeList segundoNombre = segundoNombreElemento.getChildNodes();

                        autor = ((Node) segundoNombre.item(0)).getNodeValue().toString();
                        System.out.println("Autor + autor");

                        NodeList tercerNombreElementoLista = primerElemento.getElementsByTagName("hits");
                        Element tercerNombreElemento = (Element) tercerNombreElementoLista.item(0);
                        NodeList tercerNombre = tercerNombreElemento.getChildNodes();
                        hits = ((Node) tercerNombre.item(0)).getNodeValue().toString();
                        System.out.println("Hits + hits");

                    }

                    bufferreader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(575, 520));
        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(396, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    public void llenaColaLetras(char let, int val) {
        nodoFichasCola = new nodoColaFichas(let, val);
        fichasCola.ingresar(nodoFichasCola);
    }

    public void llenaJugadores(String nombre) {
        nodoUsuario = new nodoJugadores(nombre);
        listaJugadores.ingresar(nodoUsuario);
    }

    public void llenaDiccionario(String palabra) {
        nodoPalabra = new nodoPalabras(palabra);
        listaPalabras.insertar(nodoPalabra);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    // End of variables declaration//GEN-END:variables
}
