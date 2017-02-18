/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_200714558;

import NODOS.*;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
    int dimension;
    JPanel squares[][];
    Label[][] label;
    JLabel etiqueta[][];

    public tablero() {
        initComponents();
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

                //NodeList nodeLista = documento.getElementsByTagName("descarga");
                //System.out.println("Informacionos libros");
                
                NodeList nodeLista = documento.getElementsByTagName("dimension");
                Element elDim = (Element) nodeLista.item(0);
                NodeList numDim = elDim.getChildNodes();
                String dato = ((Node)numDim.item(0)).getNodeValue();
                dimension = Integer.parseInt(dato); //CREAR LA MATRIZ
                System.out.println("La Dimension Es: "+dato);
                
                for(int xs = 0; xs<dimension; xs++){
                    for(int ys = 0; ys<dimension; ys++){
                        matrix.ingresar(xs, ys, 0, 0, ' ');
                    }
                }
                
                NodeList listaDobles = documento.getElementsByTagName("dobles");
                Element elDob = (Element) listaDobles.item(0);
                NodeList listaCasilla = elDob.getElementsByTagName("casilla");
                
                for(int a=0; a<listaCasilla.getLength(); a++){
                    Node inicianNodos = listaCasilla.item(a);
                    
                    if (inicianNodos.getNodeType() == Node.ELEMENT_NODE) {
                        Element iniciar = (Element) inicianNodos;
                        
                        NodeList casillaX = iniciar.getElementsByTagName("x");
                        Element primerX = (Element) casillaX.item(0);
                        NodeList valorX = primerX.getChildNodes();
                        String Xval = ((Node)valorX.item(0)).getNodeValue();
                        int X = Integer.parseInt(Xval);
                        System.out.println("X = "+Xval);
                        
                        NodeList casillaY = iniciar.getElementsByTagName("y");
                        Element primerY = (Element) casillaY.item(0);
                        NodeList valorY = primerY.getChildNodes();
                        String Yval = ((Node)valorY.item(0)).getNodeValue();
                        int Y = Integer.parseInt(Yval);
                        System.out.println("Y = "+Yval);
                        
                        matrix.ponerPuntos(X, Y, 2, 1);
                    }
                }
                
                NodeList listaTriples = documento.getElementsByTagName("triples");
                Element elTrip = (Element) listaTriples.item(0);
                NodeList listaCasillaTr = elTrip.getElementsByTagName("casilla");
                
                for(int a=0; a<listaCasillaTr.getLength(); a++){
                    Node inicianNodos = listaCasillaTr.item(a);
                    
                    if (inicianNodos.getNodeType() == Node.ELEMENT_NODE) {
                        Element iniciar = (Element) inicianNodos;
                        
                        NodeList casillaX = iniciar.getElementsByTagName("x");
                        Element primerX = (Element) casillaX.item(0);
                        NodeList valorX = primerX.getChildNodes();
                        String Xval = ((Node)valorX.item(0)).getNodeValue();
                        int X = Integer.parseInt(Xval);
                        System.out.println("X = "+Xval);
                        
                        NodeList casillaY = iniciar.getElementsByTagName("y");
                        Element primerY = (Element) casillaY.item(0);
                        NodeList valorY = primerY.getChildNodes();
                        String Yval = ((Node)valorY.item(0)).getNodeValue();
                        int Y = Integer.parseInt(Yval);
                        System.out.println("Y = "+Yval);
                        
                        matrix.ponerPuntos(X, Y, 3, 1);
                    }
                }
                
                NodeList listaDiccionario = documento.getElementsByTagName("diccionario");
                Element elDicc = (Element) listaDiccionario.item(0);
                NodeList listaDiccion = elDicc.getElementsByTagName("palabra");
                
                for(int a=0; a<listaDiccion.getLength(); a++){
                    Node inicianNodos = listaDiccion.item(a);
                    
                    if (inicianNodos.getNodeType() == Node.ELEMENT_NODE) {
                        Element iniciar = (Element) inicianNodos;
                        
                        NodeList valorPal = iniciar.getChildNodes();
                        String palabra = ((Node)valorPal.item(0)).getNodeValue();
                        System.out.println("palabra= "+palabra);
                        
                        nodoPalabra = new nodoPalabras(palabra);
                        listaPalabras.insertar(nodoPalabra);
                    }
                }
                listaPalabras.graficar();
                    bufferreader.close();
                
                
            } catch (IOException ex) {
                Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void frame() {
        squares = new JPanel[dimension][dimension];
        etiqueta = new JLabel[dimension][dimension];
        this.jInternalFrame1.setLayout(new GridLayout(dimension, dimension));

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

//                squares[i][j] = new JPanel();
                squares[i][j] = new JPanel();
                //etiqueta[i][j] = new JLabel("   " + i + " " + j);
                etiqueta[i][j] = new JLabel();
                etiqueta[i][j].setBounds(400 * i, 400 * j, 400, 400);
                etiqueta[i][j].setName("etiqueta," + i + "," + j);

                if (i % 2 == 0 && j % 2 == 0) {
                    squares[i][j].setBackground(new java.awt.Color(255, 255, 255));
                } else if (i % 2 == 0 && j % 2 == 1) {
                    squares[i][j].setBackground(new java.awt.Color(112, 170, 107));
                } else if (i % 2 == 1 && j % 2 == 0) {
                    squares[i][j].setBackground(new java.awt.Color(112, 170, 107));
                } else if (i % 2 == 1 && j % 2 == 1) {
                    squares[i][j].setBackground(new java.awt.Color(255, 255, 255));
                }

                squares[i][j].add(etiqueta[i][j]);
                this.jInternalFrame1.add(squares[i][j]);

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
