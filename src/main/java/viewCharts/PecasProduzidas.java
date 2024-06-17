/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package viewCharts;

import dashboards.ConsumoDashboard;
import dashboards.PecasProduzidasDashboard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.CategoryItemEntity;
import relatorio.Relatorio;
import relatorio.ServiceRelatorio;
import view.FuncionariosGUI;
import view.PecasGUI;
import view.PinturaGUI;
import view.TintasGUI;

/**
 *
 * @author Lenovo
 */
public class PecasProduzidas extends javax.swing.JFrame {

    /**
     * Creates new form PecasProduzidas
     */
    public PecasProduzidas() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); // Maximiza tela
        
        //Instanciando classe ServiceRelatorio
        ServiceRelatorio service = new ServiceRelatorio();
        
        //Setando data de hoje
        chooserDateNow.setDate(service.hoje());
        
        //Instanciando classe do dashboard
        this.dashboard = new PecasProduzidasDashboard();
        
        //Criando grafico de barras Pecas por turno no dia
        this.painelDayBarChart.setLayout(new BorderLayout());
        this.painelDayBarChart.add(dashboard.painelBarChartPecasTurno(service.getRelatorioPecasTurno(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1)));
        
        //Criando grafico de rosquinha por turno no dia
        this.painelPieChart.setLayout(new BorderLayout());
        this.painelPieChart.add(dashboard.painelRingChartTurno(service.getRelatorioPecasTurno(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1)));
        
        //Criando grafico de linhas Pecas por mes no ano
        this.painelLineChart.setLayout(new BorderLayout());
        ChartPanel painelPecasMes = dashboard.painelLineChartPecasMes(service.getRelatorioPecasMes(yearChooser.getValue(), boxProd.getSelectedIndex()+1));
        this.painelLineChart.add(painelPecasMes);
        
        //Mostrando valores 
        showValueLineChart(painelPecasMes);
        
    }

    private PecasProduzidasDashboard dashboard;
    
    //Atualiza dataset
    private void chooseDay(){
        //Verifica se classe do dashboard ja foi inicializada
        if(dashboard != null){
            //Verifica se a data não é nula
            if(chooserDateNow.getDate() != null){
                //Grafico de barras
                dashboard.setNewBarChartDataset(new ServiceRelatorio().getRelatorioPecasTurno(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1));
                //Grafico de Rosquinha
                dashboard.setNewPieDataset(new ServiceRelatorio().getRelatorioPecasTurno(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1));
                //Grafico de Linhas
                dashboard.setNewLineChartDataset(new ServiceRelatorio().getRelatorioPecasMes(yearChooser.getValue(), boxProd.getSelectedIndex()+1));
                
            } else {
              JOptionPane.showMessageDialog(null, "Data inserida inválida!","AVISO",JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }
    
    
    //Adicionando Mouse Listener ao painel do grafico
    private void showValueLineChart(ChartPanel panel){
        panel.addChartMouseListener(new ChartMouseListener(){
            
            @Override   //Clique do mouse
            public void chartMouseClicked(ChartMouseEvent event) {
            }
            //Criando popup
            JPopupMenu popupMenu;

            @Override   //Movimentação do mouse
            public void chartMouseMoved(ChartMouseEvent event) {
                if(event.getEntity() instanceof CategoryItemEntity){
                    CategoryItemEntity entity = (CategoryItemEntity) event.getEntity();
                    //Data
                    String data = entity.getColumnKey().toString();
                    int value = entity.getDataset().getValue("Quantidade de Peças", data).intValue();
                    
                    // Criar o popup com os valores
                    popupMenu = new JPopupMenu();
                    popupMenu.add(new JLabel(value + " Peças"));

                    // Traduzir a localização da barra para a posição do mouse
                    Rectangle barBounds = entity.getArea().getBounds();
                    int x = (int) barBounds.getCenterX();
                    int y = (int) barBounds.getY()-20;

                    //Alterar cor do background
                    popupMenu.setBackground(Color.WHITE);
                    
                    // Mostrar o popup no topo da barra
                    popupMenu.show(panel, x, y);
                } else {
                    if(popupMenu != null){
                        popupMenu.setVisible(false);
                    }
                }
            }
        });
        
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        boxProd = new javax.swing.JComboBox<>();
        chooserDateNow = new com.toedter.calendar.JDateChooser();
        painelLineChart = new javax.swing.JPanel();
        painelDayBarChart = new javax.swing.JPanel();
        painelPieChart = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        yearChooser = new com.toedter.calendar.JYearChooser();
        btnDecDay = new javax.swing.JButton();
        btnAddDay = new javax.swing.JButton();
        lateralPanel9 = new javax.swing.JPanel();
        employeesButton9 = new javax.swing.JButton();
        partsProductionButton9 = new javax.swing.JButton();
        wasteButton9 = new javax.swing.JButton();
        comsumptionButton9 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        exitButton9 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        partsButton9 = new javax.swing.JButton();
        paintsButton9 = new javax.swing.JButton();
        paintingButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Peças Produzidas");

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Relatório de Peças Produzidas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Linha de Produção");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Data");

        boxProd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        boxProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Produção 1", "Produção 2", "Produção 3" }));
        boxProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxProdActionPerformed(evt);
            }
        });

        chooserDateNow.setDateFormatString("dd'/'MM'/'yyyy");
        chooserDateNow.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chooserDateNow.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chooserDateNowPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout painelLineChartLayout = new javax.swing.GroupLayout(painelLineChart);
        painelLineChart.setLayout(painelLineChartLayout);
        painelLineChartLayout.setHorizontalGroup(
            painelLineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelLineChartLayout.setVerticalGroup(
            painelLineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelDayBarChartLayout = new javax.swing.GroupLayout(painelDayBarChart);
        painelDayBarChart.setLayout(painelDayBarChartLayout);
        painelDayBarChartLayout.setHorizontalGroup(
            painelDayBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );
        painelDayBarChartLayout.setVerticalGroup(
            painelDayBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelPieChartLayout = new javax.swing.GroupLayout(painelPieChart);
        painelPieChart.setLayout(painelPieChartLayout);
        painelPieChartLayout.setHorizontalGroup(
            painelPieChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );
        painelPieChartLayout.setVerticalGroup(
            painelPieChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(51, 51, 51));
        jButton5.setText("Atualizar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Ano");

        yearChooser.setBackground(new java.awt.Color(255, 255, 255));
        yearChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yearChooserPropertyChange(evt);
            }
        });

        btnDecDay.setBackground(new java.awt.Color(248, 248, 248));
        btnDecDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-seta-esquerda-24.png"))); // NOI18N
        btnDecDay.setBorder(null);
        btnDecDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecDayActionPerformed(evt);
            }
        });

        btnAddDay.setBackground(new java.awt.Color(248, 248, 248));
        btnAddDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-seta-direita-24.png"))); // NOI18N
        btnAddDay.setBorder(null);
        btnAddDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDayActionPerformed(evt);
            }
        });

        lateralPanel9.setBackground(new java.awt.Color(10, 6, 37));

        employeesButton9.setBackground(new java.awt.Color(0, 0, 51));
        employeesButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/adicionar-funcionario.png"))); // NOI18N
        employeesButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeesButton9ActionPerformed(evt);
            }
        });

        partsProductionButton9.setBackground(new java.awt.Color(0, 0, 51));
        partsProductionButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/produtividade (1).png"))); // NOI18N
        partsProductionButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partsProductionButton9ActionPerformed(evt);
            }
        });

        wasteButton9.setBackground(new java.awt.Color(0, 0, 51));
        wasteButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/desperdicio-de-dinheiro.png"))); // NOI18N
        wasteButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wasteButton9ActionPerformed(evt);
            }
        });

        comsumptionButton9.setBackground(new java.awt.Color(0, 0, 51));
        comsumptionButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/consumo-responsavel.png"))); // NOI18N
        comsumptionButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comsumptionButton9ActionPerformed(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo versao 3.png"))); // NOI18N

        exitButton9.setBackground(new java.awt.Color(0, 0, 102));
        exitButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitButton9.setForeground(new java.awt.Color(240, 240, 240));
        exitButton9.setText("Sair");
        exitButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButton9ActionPerformed(evt);
            }
        });

        partsButton9.setBackground(new java.awt.Color(0, 0, 51));
        partsButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pistao.png"))); // NOI18N
        partsButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partsButton9ActionPerformed(evt);
            }
        });

        paintsButton9.setBackground(new java.awt.Color(0, 0, 51));
        paintsButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/balde-de-tinta.png"))); // NOI18N
        paintsButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paintsButton9ActionPerformed(evt);
            }
        });

        paintingButton9.setBackground(new java.awt.Color(0, 0, 51));
        paintingButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paint-airbrush.png"))); // NOI18N
        paintingButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paintingButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lateralPanel9Layout = new javax.swing.GroupLayout(lateralPanel9);
        lateralPanel9.setLayout(lateralPanel9Layout);
        lateralPanel9Layout.setHorizontalGroup(
            lateralPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lateralPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel14)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lateralPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lateralPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employeesButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator11)
                    .addGroup(lateralPanel9Layout.createSequentialGroup()
                        .addComponent(exitButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(partsProductionButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(wasteButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comsumptionButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(partsButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paintsButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paintingButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        lateralPanel9Layout.setVerticalGroup(
            lateralPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lateralPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel14)
                .addGap(20, 20, 20)
                .addComponent(comsumptionButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wasteButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partsProductionButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeesButton9)
                .addGap(18, 18, 18)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(partsButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paintsButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paintingButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                .addComponent(exitButton9)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lateralPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(chooserDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDecDay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddDay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(boxProd, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(painelDayBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                        .addComponent(painelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(2, 2, 2)
                        .addComponent(boxProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chooserDateNow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAddDay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDecDay, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(painelDayBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(painelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lateralPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void chooserDateNowPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserDateNowPropertyChange
       //Atualiza dataset
        chooseDay();
    }//GEN-LAST:event_chooserDateNowPropertyChange

    private void boxProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxProdActionPerformed
        //Atualiza dataset
        chooseDay();
    }//GEN-LAST:event_boxProdActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Atualiza dataset
        chooseDay();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void yearChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yearChooserPropertyChange
        //Verifica se dashboard ja foi inicializado
        if(dashboard != null){
            //atualiza dataset
            dashboard.setNewLineChartDataset(new ServiceRelatorio().getRelatorioPecasMes(yearChooser.getValue(), boxProd.getSelectedIndex()+1));
        }
    }//GEN-LAST:event_yearChooserPropertyChange

    private void btnDecDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecDayActionPerformed
        //Verifica se a data está vazia
        if(chooserDateNow.getDate() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(chooserDateNow.getDate());
            calendar.add(Calendar.DAY_OF_MONTH, -1); // Increment one day
            chooserDateNow.setDate(calendar.getTime());
        }
    }//GEN-LAST:event_btnDecDayActionPerformed

    private void btnAddDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDayActionPerformed
        //Verifica se a data está vazia
        if(chooserDateNow.getDate() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(chooserDateNow.getDate());
            calendar.add(Calendar.DAY_OF_MONTH, 1); // Increment one day
            chooserDateNow.setDate(calendar.getTime());
        }
    }//GEN-LAST:event_btnAddDayActionPerformed

    private void employeesButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeesButton9ActionPerformed
        new FuncionariosGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_employeesButton9ActionPerformed

    private void partsProductionButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partsProductionButton9ActionPerformed
        
    }//GEN-LAST:event_partsProductionButton9ActionPerformed

    private void wasteButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wasteButton9ActionPerformed
        new Desperdicio().setVisible(true);
        dispose();
    }//GEN-LAST:event_wasteButton9ActionPerformed

    private void comsumptionButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comsumptionButton9ActionPerformed
        new ConsumoDiario().setVisible(true);
        dispose();
    }//GEN-LAST:event_comsumptionButton9ActionPerformed

    private void exitButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton9ActionPerformed
        //Encerrar
        switch(JOptionPane.showConfirmDialog(null, "Deseja encerrar o sistema?","Sair",JOptionPane.YES_NO_OPTION)){
            case JOptionPane.YES_OPTION:
            System.exit(0);
            break;
        }
    }//GEN-LAST:event_exitButton9ActionPerformed

    private void partsButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partsButton9ActionPerformed
        // TODO add your handling code here:
        new PecasGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_partsButton9ActionPerformed

    private void paintsButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paintsButton9ActionPerformed
        // TODO add your handling code here:
        new TintasGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_paintsButton9ActionPerformed

    private void paintingButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paintingButton9ActionPerformed
        // TODO add your handling code here:
        new PinturaGUI().setVisible(true);
        dispose();
    }//GEN-LAST:event_paintingButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       new PecasProduzidas().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxProd;
    private javax.swing.JButton btnAddDay;
    private javax.swing.JButton btnDecDay;
    private com.toedter.calendar.JDateChooser chooserDateNow;
    private javax.swing.JButton comsumptionButton9;
    private javax.swing.JButton employeesButton9;
    private javax.swing.JButton exitButton9;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JPanel lateralPanel9;
    private javax.swing.JPanel painelDayBarChart;
    private javax.swing.JPanel painelLineChart;
    private javax.swing.JPanel painelPieChart;
    private javax.swing.JButton paintingButton9;
    private javax.swing.JButton paintsButton9;
    private javax.swing.JButton partsButton9;
    private javax.swing.JButton partsProductionButton9;
    private javax.swing.JButton wasteButton9;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
