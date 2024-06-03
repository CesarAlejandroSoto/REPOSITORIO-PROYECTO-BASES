package uis.edu.co.banco_de_sangre.vista;


import javax.swing.JOptionPane;
import uis.edu.co.banco_de_sangre.controlador.ControladorPantallas;


public class VMenuPrincipal extends javax.swing.JFrame {


    public VMenuPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnDonantes = new javax.swing.JButton();
        btnUnidadesDeSangre = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnDistribucion = new javax.swing.JButton();
        btnDonaciones = new javax.swing.JButton();
        btnAnalisisDeSangre = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LA CRUZ ROJA COLOMBIANA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        btnDonantes.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Escritorio\\Bases de Datos\\Iconos\\icono 2.png")); // NOI18N
        btnDonantes.setText("jButton1");
        btnDonantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonantesActionPerformed(evt);
            }
        });
        getContentPane().add(btnDonantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 120, 90));

        btnUnidadesDeSangre.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Escritorio\\Bases de Datos\\Iconos\\icono 1.png")); // NOI18N
        btnUnidadesDeSangre.setText("jButton3");
        btnUnidadesDeSangre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnidadesDeSangreActionPerformed(evt);
            }
        });
        getContentPane().add(btnUnidadesDeSangre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 120, 90));

        btnSalir.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Escritorio\\Bases de Datos\\Iconos\\salir.png")); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 110, 90));

        btnDistribucion.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Escritorio\\Bases de Datos\\Iconos\\Distribucion.png")); // NOI18N
        btnDistribucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDistribucionActionPerformed(evt);
            }
        });
        getContentPane().add(btnDistribucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 120, 90));

        btnDonaciones.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Escritorio\\Bases de Datos\\Iconos\\donantes.png")); // NOI18N
        btnDonaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonacionesActionPerformed(evt);
            }
        });
        getContentPane().add(btnDonaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 120, 80));

        btnAnalisisDeSangre.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Escritorio\\Bases de Datos\\Iconos\\Analisis.png")); // NOI18N
        btnAnalisisDeSangre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisDeSangreActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnalisisDeSangre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 120, 80));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DONANTES");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("UNIDADES");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DISTRIBUCION");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DONACIONES");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ANALISIS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Escritorio\\Bases de Datos\\Iconos\\fondo 2.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 658, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUnidadesDeSangreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnidadesDeSangreActionPerformed
        // TODO add your handling code here:
        ControladorPantallas.abrirPantallaUnidadesSangre();
    }//GEN-LAST:event_btnUnidadesDeSangreActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
    
    int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea salir de la aplicación?", "Confirmación", JOptionPane.YES_NO_OPTION);
    
   
    if (opcion == JOptionPane.YES_OPTION) {
        // Cerrar toda la aplicación
        System.exit(0);
    }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnDonantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonantesActionPerformed
        // TODO add your handling code here:
        ControladorPantallas.abrirPantallaDonante();
    }//GEN-LAST:event_btnDonantesActionPerformed

    private void btnDistribucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDistribucionActionPerformed
        // TODO add your handling code here:
        ControladorPantallas.abrirPantallaDistribucion();
    }//GEN-LAST:event_btnDistribucionActionPerformed

    private void btnDonacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonacionesActionPerformed
        // TODO add your handling code here:
        ControladorPantallas.abrirPantallaDonacion();
    }//GEN-LAST:event_btnDonacionesActionPerformed

    private void btnAnalisisDeSangreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisDeSangreActionPerformed
        // TODO add your handling code here:
        ControladorPantallas.abrirPantallaAnalisisDeSangre();
    }//GEN-LAST:event_btnAnalisisDeSangreActionPerformed

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
            java.util.logging.Logger.getLogger(VMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalisisDeSangre;
    private javax.swing.JButton btnDistribucion;
    private javax.swing.JButton btnDonaciones;
    private javax.swing.JButton btnDonantes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUnidadesDeSangre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
