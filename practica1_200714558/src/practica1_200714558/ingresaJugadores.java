/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_200714558;

import NODOS.letras;
import NODOS.nodoColaFichas;
import NODOS.nodoJugadores;
import javax.swing.JOptionPane;
import usoNODOS.colaFichas;
import usoNODOS.listaCircularJugadores;

/**
 *
 * @author Rafael Antonio Morales Donis
 */
public class ingresaJugadores extends javax.swing.JFrame {

    public nodoJugadores nodoUsuario;
    public tablero tab = new tablero();
    letras[] letra = new letras[95];
    boolean bandera = false;

    /**
     * Creates new form ingresaJugadores
     */
    public ingresaJugadores() {
        initComponents();
        llenarLetras();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 255, 255));
        jLabel1.setText("INGRESO DE JUGADORES");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 255, 255));
        jLabel2.setText("Nombre De Usuario:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jButton1)))))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.jTextField1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese Nombre de Jugador");
        } else {
            nodoUsuario = new nodoJugadores(this.jTextField1.getText());
            tab.listaJugadores.ingresar(nodoUsuario);
            if(bandera == false){
                tab.jugadorTurnando = jTextField1.getText();
                bandera=true;
            }
            int resp = JOptionPane.showConfirmDialog(null, "Ingresar Otro Jugador", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp == 1) {
                this.setVisible(false);
                tab.setVisible(true);
                tab.listaJugadores.graficar();
                tab.frame2();
                tab.recorreListaFichasJugadores();
                tab.llenarTextArea();
            }
            this.jTextField1.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ingresaJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingresaJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingresaJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingresaJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ingresaJugadores().setVisible(true);
            }
        });
    }

    void llenarLetras() {
        int num = 0;
        for (int i = 0; i < 12; i++) {
            letra[num] = new letras('A', 1);
            num++;
        }

        for (int i = 0; i < 12; i++) {
            letra[num] = new letras('E', 1);
            num++;
        }

        for (int i = 0; i < 9; i++) {
            letra[num] = new letras('O', 1);
            num++;
        }

        for (int i = 0; i < 6; i++) {
            letra[num] = new letras('I', 1);
            num++;
        }

        for (int i = 0; i < 6; i++) {
            letra[num] = new letras('S', 1);
            num++;
        }

        for (int i = 0; i < 5; i++) {
            letra[num] = new letras('N', 1);
            num++;
        }

        for (int i = 0; i < 4; i++) {
            letra[num] = new letras('L', 1);
            num++;
        }

        for (int i = 0; i < 5; i++) {
            letra[num] = new letras('R', 1);
            num++;
        }

        for (int i = 0; i < 5; i++) {
            letra[num] = new letras('U', 1);
            num++;
        }

        for (int i = 0; i < 4; i++) {
            letra[num] = new letras('T', 1);
            num++;
        }

        for (int i = 0; i < 5; i++) {
            letra[num] = new letras('D', 2);
            num++;
        }

        for (int i = 0; i < 2; i++) {
            letra[num] = new letras('G', 2);
            num++;
        }

        for (int i = 0; i < 4; i++) {
            letra[num] = new letras('C', 3);
            num++;
        }

        for (int i = 0; i < 2; i++) {
            letra[num] = new letras('B', 3);
            num++;
        }

        for (int i = 0; i < 2; i++) {
            letra[num] = new letras('M', 3);
            num++;
        }

        for (int i = 0; i < 2; i++) {
            letra[num] = new letras('P', 3);
            num++;
        }

        for (int i = 0; i < 2; i++) {
            letra[num] = new letras('H', 4);
            num++;
        }

        letra[num] = new letras('F', 4);
        num++;

        letra[num] = new letras('V', 4);
        num++;

        letra[num] = new letras('Y', 4);
        num++;

        letra[num] = new letras('Q', 5);
        num++;

        letra[num] = new letras('J', 8);
        num++;

        letra[num] = new letras('Ñ', 8);
        num++;

        letra[num] = new letras('X', 8);
        num++;

        letra[num] = new letras('Z', 10);
        llenarCola();
    }

    void llenarCola() {
        int num = (letra.length-1);
        while (num >= 0) {
            int numero = (int) (Math.random() * num);
            tab.llenaColaLetras(letra[numero].getLetra(), letra[numero].getValor());
            
            for(int i=0; i<letra.length; i++){
                if(i==numero){
                    for(int j=i; j<(letra.length-1); j++){
                        letra[j] = letra[j+1];
                    }
                    letra[letra.length - 1] = null;
                }
            }
            
            num--;
        }
        letra = null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
