/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Simulacion.Colonia;
import javax.swing.JOptionPane;

/**
 *
 * @author nelsoncarrillo
 */
public class MenuPrincipalGUI extends javax.swing.JFrame {
    
    private GestionArchivosGUI interfazArchivos = new GestionArchivosGUI(MenuPrincipalGUI.this);
    private AgregarCiudadGUI interfazGrafos = new AgregarCiudadGUI(MenuPrincipalGUI.this);
    private IndicarSimulacionGUI interfazSimulacion = new IndicarSimulacionGUI(MenuPrincipalGUI.this);
    Colonia coloniaAST;

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipalGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    public void setColonia(Colonia nueva){
        this.coloniaAST=nueva;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Exit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        OpenArchivos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        OpenGrafos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        AbrirSimulacion = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        jLabel5.setFont(new java.awt.Font("Malayalam MN", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 0));
        jLabel5.setText("Cargar o Guardar Grafo");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Exit.setBackground(null);
        Exit.setForeground(null);
        Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton.png"))); // NOI18N
        Exit.setBorder(null);
        Exit.setBorderPainted(false);
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jPanel1.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 40, -1));

        jLabel1.setFont(new java.awt.Font("Malayalam MN", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 204, 0));
        jLabel1.setText("BIENVENIDO AL ANT SYSTEM SIMULATOR.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 390, 50));

        jLabel3.setFont(new java.awt.Font("Malayalam MN", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 255, 204));
        jLabel3.setText("BIENVENIDO AL ANT SYSTEM SIMULATOR.");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        OpenArchivos.setBackground(null);
        OpenArchivos.setForeground(null);
        OpenArchivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6401621-2.png"))); // NOI18N
        OpenArchivos.setBorder(null);
        OpenArchivos.setBorderPainted(false);
        OpenArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenArchivosActionPerformed(evt);
            }
        });
        jPanel1.add(OpenArchivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 70, 60));

        jLabel2.setFont(new java.awt.Font("Malayalam MN", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 0));
        jLabel2.setText("Cargar o Guardar Grafo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Malayalam MN", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 255, 51));
        jLabel4.setText("Modificar Grafo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 30));

        OpenGrafos.setBackground(null);
        OpenGrafos.setForeground(null);
        OpenGrafos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4525943-2.png"))); // NOI18N
        OpenGrafos.setBorder(null);
        OpenGrafos.setBorderPainted(false);
        OpenGrafos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenGrafosActionPerformed(evt);
            }
        });
        jPanel1.add(OpenGrafos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, 50));

        jLabel6.setFont(new java.awt.Font("Malayalam MN", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 255, 51));
        jLabel6.setText("Nueva Simulación");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));

        AbrirSimulacion.setBackground(null);
        AbrirSimulacion.setForeground(null);
        AbrirSimulacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/119-1191261_this-free-icons-png-design-of-button-2.png"))); // NOI18N
        AbrirSimulacion.setBorder(null);
        AbrirSimulacion.setBorderPainted(false);
        AbrirSimulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirSimulacionActionPerformed(evt);
            }
        });
        jPanel1.add(AbrirSimulacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/5213870201_b6c98f7202_o.jpg"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void OpenArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenArchivosActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.interfazArchivos.setVisible(true);
    }//GEN-LAST:event_OpenArchivosActionPerformed

    private void OpenGrafosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenGrafosActionPerformed
        // TODO add your handling code here:
        if(!this.interfazArchivos.isGuardar()){
            JOptionPane.showMessageDialog(null, "No Hay Grafo Guardado Para Editar.","ERROR", JOptionPane.WARNING_MESSAGE);
        }else{
            this.setVisible(false);
            this.interfazGrafos.setVisible(true);
        }
    }//GEN-LAST:event_OpenGrafosActionPerformed

    private void AbrirSimulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirSimulacionActionPerformed
        // TODO add your handling code here:
        //if(!this.interfazArchivos.isGuardar()){
            //JOptionPane.showMessageDialog(null, "No Hay Grafo Guardado Para Iniciar.","ERROR", JOptionPane.WARNING_MESSAGE);
        //}else{
            this.setVisible(false);
            this.interfazSimulacion.setVisible(true);
        //}
    }//GEN-LAST:event_AbrirSimulacionActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AbrirSimulacion;
    private javax.swing.JButton Exit;
    private javax.swing.JButton OpenArchivos;
    private javax.swing.JButton OpenGrafos;
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
