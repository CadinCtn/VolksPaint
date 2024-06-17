/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JOptionPane;
import pintura.ServicePintura;
import relatorio.ServiceRelatorio;
import viewCharts.*;

/**
 *
 * @author Home
 */
public class PinturaGUI extends javax.swing.JFrame {

    /**
     * Creates new form PinturaGUI
     */
    public PinturaGUI() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        new ServicePintura().tabelaPintura(tabPintura);
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabPintura = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        relatoryButton = new javax.swing.JButton();
        lateralPanel5 = new javax.swing.JPanel();
        employeesButton5 = new javax.swing.JButton();
        partsProductionButton5 = new javax.swing.JButton();
        wasteButton5 = new javax.swing.JButton();
        comsumptionButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        exitButton5 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        partsButton5 = new javax.swing.JButton();
        paintsButton5 = new javax.swing.JButton();
        paintingButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pinturas");

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        tabPintura.setForeground(new java.awt.Color(51, 51, 51));
        tabPintura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Linha de produção", "Turno", "Peca", "Quantidade Pecas", "Tinta", "Quantidade Tinta", "Limite de Consumo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPintura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabPinturaMouseReleased(evt);
            }
        });
        tabPintura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabPinturaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabPintura);

        btnAdd.setBackground(new java.awt.Color(240, 240, 240));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(51, 51, 51));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/adicionar.png"))); // NOI18N
        btnAdd.setText("Registrar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpd.setBackground(new java.awt.Color(240, 240, 240));
        btnUpd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpd.setForeground(new java.awt.Color(51, 51, 51));
        btnUpd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editar (2).png"))); // NOI18N
        btnUpd.setText("Atualizar");
        btnUpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(240, 240, 240));
        btnDel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDel.setForeground(new java.awt.Color(51, 51, 51));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/excluir.png"))); // NOI18N
        btnDel.setText("Deletar");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        relatoryButton.setBackground(new java.awt.Color(240, 240, 240));
        relatoryButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        relatoryButton.setText("Gerar Relatório");
        relatoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatoryButtonActionPerformed(evt);
            }
        });

        lateralPanel5.setBackground(new java.awt.Color(10, 6, 37));

        employeesButton5.setBackground(new java.awt.Color(0, 0, 51));
        employeesButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/adicionar-funcionario.png"))); // NOI18N
        employeesButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeesButton5ActionPerformed(evt);
            }
        });

        partsProductionButton5.setBackground(new java.awt.Color(0, 0, 51));
        partsProductionButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/produtividade.png"))); // NOI18N
        partsProductionButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partsProductionButton5ActionPerformed(evt);
            }
        });

        wasteButton5.setBackground(new java.awt.Color(0, 0, 51));
        wasteButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/desperdicio-de-dinheiro.png"))); // NOI18N
        wasteButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wasteButton5ActionPerformed(evt);
            }
        });

        comsumptionButton5.setBackground(new java.awt.Color(0, 0, 51));
        comsumptionButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/consumo-responsavel.png"))); // NOI18N
        comsumptionButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comsumptionButton5ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo versao 3.png"))); // NOI18N

        exitButton5.setBackground(new java.awt.Color(0, 0, 102));
        exitButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitButton5.setForeground(new java.awt.Color(240, 240, 240));
        exitButton5.setText("Sair");
        exitButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton5ActionPerformed(evt);
            }
        });

        partsButton5.setBackground(new java.awt.Color(0, 0, 51));
        partsButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pistao.png"))); // NOI18N
        partsButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partsButton5ActionPerformed(evt);
            }
        });

        paintsButton5.setBackground(new java.awt.Color(0, 0, 51));
        paintsButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/balde-de-tinta.png"))); // NOI18N
        paintsButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paintsButton5ActionPerformed(evt);
            }
        });

        paintingButton5.setBackground(new java.awt.Color(0, 0, 51));
        paintingButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paint-airbrush (1).png"))); // NOI18N
        paintingButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paintingButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lateralPanel5Layout = new javax.swing.GroupLayout(lateralPanel5);
        lateralPanel5.setLayout(lateralPanel5Layout);
        lateralPanel5Layout.setHorizontalGroup(
            lateralPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lateralPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lateralPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lateralPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employeesButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator7)
                    .addGroup(lateralPanel5Layout.createSequentialGroup()
                        .addComponent(exitButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(partsProductionButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(wasteButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comsumptionButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(partsButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paintsButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paintingButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        lateralPanel5Layout.setVerticalGroup(
            lateralPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lateralPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addGap(20, 20, 20)
                .addComponent(comsumptionButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wasteButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partsProductionButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeesButton5)
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partsButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paintsButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paintingButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitButton5)
                .addGap(14, 14, 14))
        );

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("PINTURAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lateralPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 516, Short.MAX_VALUE)
                        .addComponent(relatoryButton))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDel)
                    .addComponent(btnUpd)
                    .addComponent(relatoryButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lateralPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabPinturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPinturaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabPinturaMouseReleased

    private void tabPinturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabPinturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabPinturaKeyReleased

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        NewPinturaGUI window = new NewPinturaGUI(null, true, null);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        new ServicePintura().tabelaPintura(tabPintura);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdActionPerformed
        int selectedRow = tabPintura.getSelectedRow();
        if(selectedRow >= 0){
            int id = Integer.parseInt(tabPintura.getValueAt(selectedRow, 0).toString());

            NewPinturaGUI window = new NewPinturaGUI(null, true, new ServicePintura().selectByID(id));
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            new ServicePintura().tabelaPintura(tabPintura);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
        }
    }//GEN-LAST:event_btnUpdActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int selectedRow = tabPintura.getSelectedRow();
        if(selectedRow >= 0){
            switch(JOptionPane.showConfirmDialog(null,"Deseja mesmo deletar essa pintura?","Deletar Pintura",JOptionPane.YES_NO_OPTION)){
                case JOptionPane.YES_OPTION:
                int id = Integer.parseInt(tabPintura.getValueAt(selectedRow, 0).toString());
                new ServicePintura().deletePintura(id);
                new ServicePintura().tabelaPintura(tabPintura);

                break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void relatoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatoryButtonActionPerformed
        int selectedRow = tabPintura.getSelectedRow();
        if(selectedRow >= 0){
            switch(JOptionPane.showConfirmDialog(null, "ATENÇÃO!\nApós gerar o relatorio não sera possivel alterar.\nDeseja gerar o relatorio desse dia?","AVISO",JOptionPane.YES_NO_OPTION)){
                case JOptionPane.YES_OPTION:
                        new ServiceRelatorio().insertRelatorio(new ServicePintura().selectByID(Integer.parseInt(String.valueOf(tabPintura.getValueAt(selectedRow, 0)))));
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
        }
        
    }//GEN-LAST:event_relatoryButtonActionPerformed

    private void employeesButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeesButton5ActionPerformed
        new FuncionariosGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_employeesButton5ActionPerformed

    private void partsProductionButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partsProductionButton5ActionPerformed
        new PecasProduzidas().setVisible(true);
        dispose();
    }//GEN-LAST:event_partsProductionButton5ActionPerformed

    private void wasteButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wasteButton5ActionPerformed
        new Desperdicio().setVisible(true);
        dispose();
    }//GEN-LAST:event_wasteButton5ActionPerformed

    private void comsumptionButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comsumptionButton5ActionPerformed
        new ConsumoDiario().setVisible(true);
        dispose();
    }//GEN-LAST:event_comsumptionButton5ActionPerformed

    private void exitButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton5ActionPerformed
        //Encerrar
        switch(JOptionPane.showConfirmDialog(null, "Deseja encerrar o sistema?","Sair",JOptionPane.YES_NO_OPTION)){
            case JOptionPane.YES_OPTION:
            System.exit(0);
            break;
        }
    }//GEN-LAST:event_exitButton5ActionPerformed

    private void partsButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partsButton5ActionPerformed
        // TODO add your handling code here:
        new PecasGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_partsButton5ActionPerformed

    private void paintsButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paintsButton5ActionPerformed
        // TODO add your handling code here:
        new TintasGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_paintsButton5ActionPerformed

    private void paintingButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paintingButton5ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_paintingButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(PinturaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PinturaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PinturaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PinturaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PinturaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnUpd;
    private javax.swing.JButton comsumptionButton5;
    private javax.swing.JButton employeesButton5;
    private javax.swing.JButton exitButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel lateralPanel5;
    private javax.swing.JButton paintingButton5;
    private javax.swing.JButton paintsButton5;
    private javax.swing.JButton partsButton5;
    private javax.swing.JButton partsProductionButton5;
    private javax.swing.JButton relatoryButton;
    private javax.swing.JTable tabPintura;
    private javax.swing.JButton wasteButton5;
    // End of variables declaration//GEN-END:variables
}
