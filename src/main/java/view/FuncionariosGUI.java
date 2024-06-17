/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import funcionario.Funcionario;
import funcionario.ServiceFuncionario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import viewCharts.ConsumoDiario;
import viewCharts.Desperdicio;
import viewCharts.PecasProduzidas;

/**
 *
 * @author Lenovo
 */
public class FuncionariosGUI extends javax.swing.JFrame {

    /**
     * Creates new form FuncionariosGUI
     */
    public FuncionariosGUI() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        new ServiceFuncionario().tabelaFuncionario(tabFuncionarios);
        
        centralizarCelulas();
    }
    
    private void centralizarCelulas(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        
        tabFuncionarios.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabFuncionarios.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
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
        tabFuncionarios = new javax.swing.JTable();
        btnDel = new javax.swing.JButton();
        btnUpd = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
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
        setTitle("Funcionarios");

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        tabFuncionarios.setBackground(new java.awt.Color(248, 248, 248));
        tabFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Data Nascimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabFuncionarios);

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

        btnAdd.setBackground(new java.awt.Color(240, 240, 240));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(51, 51, 51));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/adicionar.png"))); // NOI18N
        btnAdd.setText("Adicionar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lateralPanel5.setBackground(new java.awt.Color(10, 6, 37));

        employeesButton5.setBackground(new java.awt.Color(0, 0, 51));
        employeesButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/adicionar-funcionario (1).png"))); // NOI18N
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
        paintingButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paint-airbrush.png"))); // NOI18N
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
        jLabel3.setText("FUNCIONÁRIOS");

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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDel))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDel)
                    .addComponent(btnUpd)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lateralPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        NewFuncionarioGUI window = new NewFuncionarioGUI(null,true,null);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        new ServiceFuncionario().tabelaFuncionario(tabFuncionarios);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdActionPerformed
        int selectedRow = tabFuncionarios.getSelectedRow();
        if(selectedRow >=0){
            //Seleciona funcionario
            String cpf = tabFuncionarios.getValueAt(selectedRow, 0).toString();
            Funcionario funcionario = new ServiceFuncionario().selectFuncionarioByCpf(cpf);
            
            //Abre janela para atualizar funcionario
            NewFuncionarioGUI window = new NewFuncionarioGUI(null,true,funcionario);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            new ServiceFuncionario().tabelaFuncionario(tabFuncionarios);
        }else{
            JOptionPane.showMessageDialog(null,"Selecione uma linha");
        }
    }//GEN-LAST:event_btnUpdActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int selectedRow = tabFuncionarios.getSelectedRow();
        if(selectedRow >=0){
            switch(JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar o funcionario?","Deletar funcionario",JOptionPane.YES_NO_OPTION)){
                case JOptionPane.YES_OPTION:
                        new ServiceFuncionario().deleteFuncionario(tabFuncionarios.getValueAt(selectedRow, 0).toString());
                        new ServiceFuncionario().tabelaFuncionario(tabFuncionarios);
                        break;
            }
        }else{
            JOptionPane.showMessageDialog(null,"Selecione uma linha");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void employeesButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeesButton5ActionPerformed
        
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
        new PinturaGUI().setVisible(true);
        dispose();
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
            java.util.logging.Logger.getLogger(FuncionariosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncionariosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncionariosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionariosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncionariosGUI().setVisible(true);
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
    private javax.swing.JTable tabFuncionarios;
    private javax.swing.JButton wasteButton5;
    // End of variables declaration//GEN-END:variables
}
