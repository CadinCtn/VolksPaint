/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.protobuf.Service;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import peca.ServicePeca;
import viewCharts.ConsumoDiario;
import viewCharts.Desperdicio;
import viewCharts.PecasProduzidas;

/**
 *
 * @author Senai
 */
public class PecasGUI extends javax.swing.JFrame {

    /**
     * Creates new for  m viewPeca
     */
   public PecasGUI() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        new ServicePeca().tabelaPecas(tabPecas);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabPecas = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btn_lessQtd = new javax.swing.JButton();
        btn_plusQtd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        spinQtdAtual = new javax.swing.JSpinner();
        btn_altQtd = new javax.swing.JButton();
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
        jLabel4 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Peças");

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));
        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        tabPecas.setForeground(new java.awt.Color(51, 51, 51));
        tabPecas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Modelo", "Área de Pintura", "Quantidade em Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPecas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabPecasMouseReleased(evt);
            }
        });
        tabPecas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabPecasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabPecas);

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

        btn_lessQtd.setBackground(new java.awt.Color(240, 240, 240));
        btn_lessQtd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_lessQtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menos (1).png"))); // NOI18N
        btn_lessQtd.setText("1");
        btn_lessQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lessQtdActionPerformed(evt);
            }
        });

        btn_plusQtd.setBackground(new java.awt.Color(240, 240, 240));
        btn_plusQtd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_plusQtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/mais (1).png"))); // NOI18N
        btn_plusQtd.setText("1");
        btn_plusQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plusQtdActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Quantidade Atual:");

        spinQtdAtual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btn_altQtd.setBackground(new java.awt.Color(240, 240, 240));
        btn_altQtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/troca.png"))); // NOI18N
        btn_altQtd.setText("Alterar");
        btn_altQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_altQtdActionPerformed(evt);
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
        partsButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pistao (1).png"))); // NOI18N
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

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("PEÇAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lateralPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDel))
                            .addComponent(jLabel4))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spinQtdAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_lessQtd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_plusQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_altQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 110, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lateralPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_altQtd)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_lessQtd)
                            .addComponent(btn_plusQtd)
                            .addComponent(spinQtdAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDel)
                            .addComponent(btnUpd)
                            .addComponent(btnAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        NewPecaGUI window = new NewPecaGUI(null, true, null);
        window.setVisible(true);
        new ServicePeca().tabelaPecas(tabPecas);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdActionPerformed
        int selectedRow = tabPecas.getSelectedRow();
        if(selectedRow >= 0){
            int id = Integer.parseInt(tabPecas.getValueAt(selectedRow, 0).toString());

            NewPecaGUI window = new NewPecaGUI(null, true, new ServicePeca().selectByID(id));
            window.setVisible(true);
            new ServicePeca().tabelaPecas(tabPecas);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
        }
    }//GEN-LAST:event_btnUpdActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int selectedRow = tabPecas.getSelectedRow();
        if(selectedRow >= 0){
            switch(JOptionPane.showConfirmDialog(null,"Deseja mesmo deletar essa peça?","Deletar Peça",JOptionPane.YES_NO_OPTION)){
                case JOptionPane.YES_OPTION:
                    int id = Integer.parseInt(tabPecas.getValueAt(selectedRow, 0).toString());
                        new ServicePeca().deletePeca(id);
                        new ServicePeca().tabelaPecas(tabPecas);

                    break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
        }

    }//GEN-LAST:event_btnDelActionPerformed

    private void btn_lessQtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lessQtdActionPerformed
        int qtd = Integer.parseInt(spinQtdAtual.getValue().toString());
        spinQtdAtual.setValue(qtd-1);
    }//GEN-LAST:event_btn_lessQtdActionPerformed

    private void btn_plusQtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plusQtdActionPerformed
        int qtd = Integer.parseInt(spinQtdAtual.getValue().toString());
        spinQtdAtual.setValue(qtd+1);
    }//GEN-LAST:event_btn_plusQtdActionPerformed

    private void btn_altQtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_altQtdActionPerformed
        int selectedRow = tabPecas.getSelectedRow();
        if(selectedRow >= 0){
            int qtd = Integer.parseInt(spinQtdAtual.getValue().toString());
            int id = Integer.parseInt(tabPecas.getValueAt(selectedRow, 0).toString());
            
            new ServicePeca().changeQtdPeca(qtd, id);
            new ServicePeca().tabelaPecas(tabPecas);
            
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
        }
        
    }//GEN-LAST:event_btn_altQtdActionPerformed

    private void tabPecasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabPecasKeyReleased
        int selectedRow = tabPecas.getSelectedRow();
        int qtd = Integer.parseInt(tabPecas.getValueAt(selectedRow, 3).toString());
        spinQtdAtual.setValue(qtd);
    }//GEN-LAST:event_tabPecasKeyReleased

    private void tabPecasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPecasMouseReleased
        int selectedRow = tabPecas.getSelectedRow();
        int qtd = Integer.parseInt(tabPecas.getValueAt(selectedRow, 3).toString());
        spinQtdAtual.setValue(qtd);
    }//GEN-LAST:event_tabPecasMouseReleased

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
        java.awt.EventQueue.invokeLater(() -> {
            new PecasGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnUpd;
    private javax.swing.JButton btn_altQtd;
    private javax.swing.JButton btn_lessQtd;
    private javax.swing.JButton btn_plusQtd;
    private javax.swing.JButton comsumptionButton5;
    private javax.swing.JButton employeesButton5;
    private javax.swing.JButton exitButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel lateralPanel5;
    private javax.swing.JButton paintingButton5;
    private javax.swing.JButton paintsButton5;
    private javax.swing.JButton partsButton5;
    private javax.swing.JButton partsProductionButton5;
    private javax.swing.JSpinner spinQtdAtual;
    private javax.swing.JTable tabPecas;
    private javax.swing.JButton wasteButton5;
    // End of variables declaration//GEN-END:variables

}     
