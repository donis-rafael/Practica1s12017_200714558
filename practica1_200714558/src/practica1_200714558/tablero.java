/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_200714558;

import NODOS.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.mortennobel.imagescaling.ResampleOp;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import usoNODOS.colaFichas;
import usoNODOS.listaCircularJugadores;
import usoNODOS.listaSimplePalabras;
import usoNODOS.matriz;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class tablero extends javax.swing.JFrame {

    public nodoPalabras nodoPalabra;
    public nodoColaFichas nodoFichasCola;
    public nodoMatriz nodoMatrix;
    public nodoJugadores nodoUsuario;
    public listaSimplePalabras listaPalabras = new listaSimplePalabras();
    public colaFichas fichasCola = new colaFichas();
    public listaCircularJugadores listaJugadores = new listaCircularJugadores();
    public matriz matrix = new matriz();
    String jugadorTurnando, path;
    int dimension;
    JPanel squares[][];
    Label[][] label;
    JLabel etiqueta[][];
    double zoom = 0.0;
    private BufferedImage image = null;
    File miDir = new File(".");

    public tablero() {
        initComponents();
        this.setLocationRelativeTo(null);
        jLabel7.setText("");
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

                NodeList nodeLista = documento.getElementsByTagName("dimension");
                Element elDim = (Element) nodeLista.item(0);
                NodeList numDim = elDim.getChildNodes();
                String dato = ((Node) numDim.item(0)).getNodeValue();
                dimension = Integer.parseInt(dato); //CREAR LA MATRIZ
                //System.out.println("La Dimension Es: " + dato);

                for (int ys = 0; ys < dimension; ys++) {
                    for (int xs = 0; xs < dimension; xs++) {
                        matrix.ingresar(xs, ys, 0, 0, ' ');
                    }
                }

                NodeList listaDobles = documento.getElementsByTagName("dobles");
                Element elDob = (Element) listaDobles.item(0);
                NodeList listaCasilla = elDob.getElementsByTagName("casilla");

                for (int a = 0; a < listaCasilla.getLength(); a++) {
                    Node inicianNodos = listaCasilla.item(a);

                    if (inicianNodos.getNodeType() == Node.ELEMENT_NODE) {
                        Element iniciar = (Element) inicianNodos;

                        NodeList casillaX = iniciar.getElementsByTagName("x");
                        Element primerX = (Element) casillaX.item(0);
                        NodeList valorX = primerX.getChildNodes();
                        String Xval = ((Node) valorX.item(0)).getNodeValue();
                        int X = Integer.parseInt(Xval);
                        //System.out.println("X = " + Xval);

                        NodeList casillaY = iniciar.getElementsByTagName("y");
                        Element primerY = (Element) casillaY.item(0);
                        NodeList valorY = primerY.getChildNodes();
                        String Yval = ((Node) valorY.item(0)).getNodeValue();
                        int Y = Integer.parseInt(Yval);
                        //System.out.println("Y = " + Yval);

                        matrix.ponerPuntos(X, Y, 2, 1);
                    }
                }

                NodeList listaTriples = documento.getElementsByTagName("triples");
                Element elTrip = (Element) listaTriples.item(0);
                NodeList listaCasillaTr = elTrip.getElementsByTagName("casilla");

                for (int a = 0; a < listaCasillaTr.getLength(); a++) {
                    Node inicianNodos = listaCasillaTr.item(a);

                    if (inicianNodos.getNodeType() == Node.ELEMENT_NODE) {
                        Element iniciar = (Element) inicianNodos;

                        NodeList casillaX = iniciar.getElementsByTagName("x");
                        Element primerX = (Element) casillaX.item(0);
                        NodeList valorX = primerX.getChildNodes();
                        String Xval = ((Node) valorX.item(0)).getNodeValue();
                        int X = Integer.parseInt(Xval);
                        //System.out.println("X = " + Xval);

                        NodeList casillaY = iniciar.getElementsByTagName("y");
                        Element primerY = (Element) casillaY.item(0);
                        NodeList valorY = primerY.getChildNodes();
                        String Yval = ((Node) valorY.item(0)).getNodeValue();
                        int Y = Integer.parseInt(Yval);
                        //System.out.println("Y = " + Yval);

                        matrix.ponerPuntos(X, Y, 3, 1);
                    }
                }

                NodeList listaDiccionario = documento.getElementsByTagName("diccionario");
                Element elDicc = (Element) listaDiccionario.item(0);
                NodeList listaDiccion = elDicc.getElementsByTagName("palabra");

                for (int a = 0; a < listaDiccion.getLength(); a++) {
                    Node inicianNodos = listaDiccion.item(a);

                    if (inicianNodos.getNodeType() == Node.ELEMENT_NODE) {
                        Element iniciar = (Element) inicianNodos;

                        NodeList valorPal = iniciar.getChildNodes();
                        String palabra = ((Node) valorPal.item(0)).getNodeValue();
                        System.out.println("palabra= " + palabra);

                        nodoPalabra = new nodoPalabras(palabra);
                        listaPalabras.insertar(nodoPalabra);
                    }
                }
                matrix.graficar();
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

                squares[i][j].setBackground(new java.awt.Color(112, 170, 107));

                squares[i][j].add(etiqueta[i][j]);
                this.jInternalFrame1.add(squares[i][j]);

            }
        }
    }

    public void frame2() {
        squares = new JPanel[1][7];
        etiqueta = new JLabel[1][7];
        this.jInternalFrame2.setLayout(new GridLayout(1, 7));

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 7; j++) {

//                squares[i][j] = new JPanel();
                squares[i][j] = new JPanel();
                //etiqueta[i][j] = new JLabel("   " + i + " " + j);
                etiqueta[i][j] = new JLabel();
                etiqueta[i][j].setBounds(50 * i, 50 * j, 50, 50);
                etiqueta[i][j].setName("etiqueta," + i + "," + j);

                squares[i][j].setBackground(new java.awt.Color(112, 170, 107));

                squares[i][j].add(etiqueta[i][j]);
                this.jInternalFrame2.add(squares[i][j]);

            }
        }
    }

    public void recorreListaFichasJugadores() {
        frame2();
        
        nodoListaFichas nodoFichasLista = listaJugadores.obtenerJugador(jugadorTurnando).listaFichas.inicio;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 7; j++) {
                squares[i][j].add(etiqueta[i][j]);
                etiqueta[i][j].setText(nodoFichasLista.getLetra()+"");
                etiqueta[i][j].setVisible(true);
                nodoFichasLista = nodoFichasLista.siguiente;
            }
        }
        
        listaJugadores.obtenerJugador(jugadorTurnando).listaFichas.graficar(jugadorTurnando);
    }

    public void recorreJugadores() {
        jugadorTurnando = listaJugadores.obtenerJugador(jugadorTurnando).siguiente.getId();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel7 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("nombre jugador");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Turno De:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Puntuacion:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Nueva Palabra:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(575, 520));
        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jInternalFrame2.setIconifiable(true);
        jInternalFrame2.setMaximizable(true);
        jInternalFrame2.setMinimumSize(new java.awt.Dimension(575, 520));
        jInternalFrame2.setVisible(true);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, Short.MAX_VALUE)
        );

        jButton3.setText("jButton3");

        jButton4.setText("jButton3");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Letras Activas");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Cambio de Letras:");

        jCheckBox1.setText("jCheckBox1");

        jCheckBox4.setText("jCheckBox4");

        jCheckBox5.setText("jCheckBox5");

        jCheckBox6.setText("jCheckBox6");

        jCheckBox7.setText("jCheckBox7");

        jCheckBox8.setText("jCheckBox8");

        jCheckBox9.setText("jCheckBox9");

        jButton5.setText("Cambiar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Matriz Tablero");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Cola de Fichas");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Lista Jugadores");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Lista Diccionario");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Lista Fichas Activas");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel7.setText("jLabel7");
        jScrollPane2.setViewportView(jLabel7);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox9))
                                    .addComponent(jCheckBox6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jButton5)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(329, 329, 329))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jCheckBox7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jCheckBox8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox5)
                                    .addComponent(jCheckBox9)))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        nodoPalabra = new nodoPalabras(jTextField1.getText());
        listaPalabras.insertar(nodoPalabra);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            listaPalabras.graficar();
            path = miDir.getCanonicalPath() + File.separator + "grafo_Diccionario.jpg";
            image = ImageIO.read(new File(path));
            ImageIcon diccionarioIcon = new ImageIcon(path);
            ImageIcon diccionario = new ImageIcon(diccionarioIcon.getImage().getScaledInstance(jLabel7.getWidth(), 50, Image.SCALE_SMOOTH));
            jLabel7.setIcon(diccionario);
            jLabel7.addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int notches = e.getWheelRotation();
                    double temp = zoom - (notches * 0.2);
                    // minimum zoom factor is 1.0
                    temp = Math.max(temp, 1.0);
                    if (temp != zoom) {
                        zoom = temp;
                        resizeImage(jLabel7);
                    }
                }
            });
            this.jScrollPane2.setViewportView(jLabel7);
        } catch (IOException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            path = miDir.getCanonicalPath() + File.separator + "grafo_Matriz.jpg";
            image = ImageIO.read(new File(path));
            ImageIcon diccionarioIcon = new ImageIcon(path);
            ImageIcon diccionario = new ImageIcon(diccionarioIcon.getImage().getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_SMOOTH));
            jLabel7.setIcon(diccionario);
            jLabel7.addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int notches = e.getWheelRotation();
                    double temp = zoom - (notches * 0.2);
                    // minimum zoom factor is 1.0
                    temp = Math.max(temp, 1.0);
                    if (temp != zoom) {
                        zoom = temp;
                        resizeImage(jLabel7);
                    }
                }
            });
            this.jScrollPane2.setViewportView(jLabel7);
        } catch (IOException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            path = miDir.getCanonicalPath() + File.separator + "grafo_Jugadores.jpg";
            image = ImageIO.read(new File(path));
            ImageIcon diccionarioIcon = new ImageIcon(path);
            ImageIcon diccionario = new ImageIcon(diccionarioIcon.getImage().getScaledInstance(jLabel7.getWidth(), (jLabel7.getHeight() - 250), Image.SCALE_SMOOTH));
            jLabel7.setIcon(diccionario);
            jLabel7.addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int notches = e.getWheelRotation();
                    double temp = zoom - (notches * 0.2);
                    // minimum zoom factor is 1.0
                    temp = Math.max(temp, 1.0);
                    if (temp != zoom) {
                        zoom = temp;
                        resizeImage(jLabel7);
                    }
                }
            });
            this.jScrollPane2.setViewportView(jLabel7);
        } catch (IOException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            fichasCola.graficar();
            path = miDir.getCanonicalPath() + File.separator + "grafo_Fichas.jpg";
            image = ImageIO.read(new File(path));
            ImageIcon diccionarioIcon = new ImageIcon(path);
            ImageIcon diccionario = new ImageIcon(diccionarioIcon.getImage().getScaledInstance(jLabel7.getWidth(), (jLabel7.getHeight() - 500), Image.SCALE_SMOOTH));
            jLabel7.setIcon(diccionario);
            jLabel7.addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int notches = e.getWheelRotation();
                    double temp = zoom - (notches * 0.2);
                    // minimum zoom factor is 1.0
                    temp = Math.max(temp, 1.0);
                    if (temp != zoom) {
                        zoom = temp;
                        resizeImage(jLabel7);
                    }
                }
            });
            this.jScrollPane2.setViewportView(jLabel7);
        } catch (IOException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            path = miDir.getCanonicalPath() + File.separator + "grafo_FichasJugador.jpg";
            image = ImageIO.read(new File(path));
            ImageIcon diccionarioIcon = new ImageIcon(path);
            ImageIcon diccionario = new ImageIcon(diccionarioIcon.getImage().getScaledInstance(jLabel7.getWidth(), (jLabel7.getHeight() - 500), Image.SCALE_SMOOTH));
            jLabel7.setIcon(diccionario);
            jLabel7.addMouseWheelListener(new MouseWheelListener() {
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int notches = e.getWheelRotation();
                    double temp = zoom - (notches * 0.2);
                    // minimum zoom factor is 1.0
                    temp = Math.max(temp, 1.0);
                    if (temp != zoom) {
                        zoom = temp;
                        resizeImage(jLabel7);
                    }
                }
            });
            this.jScrollPane2.setViewportView(jLabel7);
        } catch (IOException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    public void resizeImage(JLabel a) {
        //System.out.println(zoom);
        ResampleOp resampleOp = new ResampleOp((int) (image.getWidth() * zoom), (int) (image.getHeight() * zoom));
        BufferedImage resizedIcon = resampleOp.filter(image, null);
        Icon imageIcon = new ImageIcon(resizedIcon);
        a.setIcon(imageIcon);
    }

    public void llenaColaLetras(char let, int val) {
        nodoFichasCola = new nodoColaFichas(let, val);
        fichasCola.ingresar(nodoFichasCola);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
