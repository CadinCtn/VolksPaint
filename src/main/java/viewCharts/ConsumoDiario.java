/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package viewCharts;

import dashboards.ConsumoDashboard;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import relatorio.RelatorioDAO;
import relatorio.ServiceRelatorio;

/**
 *
 * @author Lenovo
 */
public class ConsumoDiario extends javax.swing.JFrame {

    /**
     * Creates new form ConsumoDiario
     */
    public ConsumoDiario() {
        initComponents();
        //Maximizando tela
        this.setExtendedState(MAXIMIZED_BOTH);
        
        //Instanciando classe Service Relatorio
        ServiceRelatorio service = new ServiceRelatorio();
        //Setando datas
        chooserDateNow.setDate(service.hoje());
        chooserDateIni.setDate(service.getFirstDay());
        chooserDateEnd.setDate(service.getLastDay());
        
        //Inicializando Dashboard
        this.dashboard = new ConsumoDashboard();
        
        //Relatorio de consumo do dia atual
        Relatorio relatorioSelecionado = service.getRelatorioDiarioConsumo(chooserDateNow.getDate(), boxProd.getSelectedIndex());
                
        //Grafico de consumo diario por turno
        this.painelConsumoTurnoBar.setLayout(new BorderLayout());
        this.painelConsumoTurnoBar.add(dashboard.painelConsumoChart(relatorioSelecionado));
        
        //Pie Graph Consumo diario por turno
        this.painelPieGraph.setLayout(new BorderLayout());
        this.painelPieGraph.add(dashboard.painelPieConsumo(relatorioSelecionado));
        

        //Painel Dados consumo
        painelValores(relatorioSelecionado);
        
        //Gráfico Consumo Total por dia
        List<Relatorio> list = new RelatorioDAO().selectConsumoTinta(new java.sql.Date(chooserDateIni.getDate().getTime()), new java.sql.Date(chooserDateEnd.getDate().getTime()), boxProd.getSelectedIndex()+1);

        //Grafico de consumo diario total
        this.painelConsumoTotalDiario.setLayout(new BorderLayout());
        ChartPanel panelBarTotal = dashboard.painelConsumoTotalDiario(list);
        getKeyBarChart(panelBarTotal,dashboard);
        this.painelConsumoTotalDiario.add(panelBarTotal);
    }
    
    //Classe dashboard
    private ConsumoDashboard dashboard;
    
    
    //Atualizar painel de valores
    private void painelValores(Relatorio consumoDiario){
        labelConsumoTotal.setText(consumoDiario.getTotalConsumoTinta() + "L");
        labelConsumoT1.setText(consumoDiario.getConsumoTinta(1) + "L");
        labelConsumoT2.setText(consumoDiario.getConsumoTinta(2) + "L");
        labelConsumoT3.setText(consumoDiario.getConsumoTinta(3) + "L");
    }
    
    //Alterar dataset dos graficos a partida do dia
    private void chooseDay(){
        if(this.dashboard != null){
           if(chooserDateNow.getDate() != null){
                //Relatorio do dia Selecionado
                Relatorio consumoDiario = new ServiceRelatorio().getRelatorioDiarioConsumo(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1);

                //Atualiza dataset dos graficos
                this.dashboard.setNewBarDataset(consumoDiario);
                this.dashboard.setNewPieDataset(consumoDiario);

                //Atualiza painel com os valores de cada turno
                painelValores(consumoDiario);
               
           } else {
                JOptionPane.showMessageDialog(null, "Data inserida inválida!","AVISO",JOptionPane.WARNING_MESSAGE);
           }
        }
    }
    
    //Altera o dataset do grafico de consumo total ao dia
    private void chooseInterval(){
        //Verifica se a classe dashboard foi inicializada
        if(this.dashboard != null){
            //Verifica se os campos de data não estao nulos
            if(chooserDateEnd.getDate() != null && chooserDateIni != null){
                //Atualiza dataset
                this.dashboard.setNewBarTotalDataset(new ServiceRelatorio().getRelatorioTotalConsumoDiario(chooserDateIni.getDate(), chooserDateEnd.getDate(), boxProd.getSelectedIndex()+1));
            } else {
                JOptionPane.showMessageDialog(null, "Data inserida inválida!","AVISO",JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }
    
    
    //Adicionando Mouse Listener ao painel do grafico
    private void getKeyBarChart(ChartPanel panel, ConsumoDashboard dashboard){
        panel.addChartMouseListener(new ChartMouseListener(){
            
            @Override   //Clique do mouse
            public void chartMouseClicked(ChartMouseEvent event) {
                if(event.getEntity() instanceof CategoryItemEntity){
                    CategoryItemEntity entity = (CategoryItemEntity) event.getEntity();
                    try {
                        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(entity.getColumnKey().toString());
                        chooserDateNow.setDate(data);
                    } catch (ParseException ex) {
                        Logger.getLogger(ConsumoDiario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //Relatorio do dia Selecionado
                    Relatorio consumoDiario = new ServiceRelatorio().getRelatorioDiarioConsumo(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1);

                    //Atualiza dataset dos graficos
                    dashboard.setNewBarDataset(consumoDiario);
                    dashboard.setNewPieDataset(consumoDiario);

                    //Atualiza painel com os valores de cada turno
                    painelValores(consumoDiario);
                }
            }

            @Override   //Movimentação do mouse
            public void chartMouseMoved(ChartMouseEvent event) {
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
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        painelConsumoTurnoBar = new javax.swing.JPanel();
        painelConsumoTotalDiario = new javax.swing.JPanel();
        a = new javax.swing.JLabel();
        intervaloTempo = new javax.swing.JLabel();
        chooserDateIni = new com.toedter.calendar.JDateChooser();
        chooserDateEnd = new com.toedter.calendar.JDateChooser();
        btnBuscarTotal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        boxProd = new javax.swing.JComboBox<>();
        chooserDateNow = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelConsumoTotal = new javax.swing.JLabel();
        labelConsumoT1 = new javax.swing.JLabel();
        labelConsumoT2 = new javax.swing.JLabel();
        labelConsumoT3 = new javax.swing.JLabel();
        painelPieGraph = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnBuscarTurno = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(10, 6, 37));

        jButton1.setText("Consumo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Relatório");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelConsumoTurnoBarLayout = new javax.swing.GroupLayout(painelConsumoTurnoBar);
        painelConsumoTurnoBar.setLayout(painelConsumoTurnoBarLayout);
        painelConsumoTurnoBarLayout.setHorizontalGroup(
            painelConsumoTurnoBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );
        painelConsumoTurnoBarLayout.setVerticalGroup(
            painelConsumoTurnoBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        painelConsumoTotalDiario.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout painelConsumoTotalDiarioLayout = new javax.swing.GroupLayout(painelConsumoTotalDiario);
        painelConsumoTotalDiario.setLayout(painelConsumoTotalDiarioLayout);
        painelConsumoTotalDiarioLayout.setHorizontalGroup(
            painelConsumoTotalDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1134, Short.MAX_VALUE)
        );
        painelConsumoTotalDiarioLayout.setVerticalGroup(
            painelConsumoTotalDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        a.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a.setText("a");

        intervaloTempo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        intervaloTempo.setText("Intervalo de tempo");

        chooserDateIni.setDateFormatString("dd'/'MM'/'yyyy");
        chooserDateIni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chooserDateIni.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chooserDateIniPropertyChange(evt);
            }
        });

        chooserDateEnd.setDateFormatString("dd'/'MM'/'yyyy");
        chooserDateEnd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chooserDateEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chooserDateEndPropertyChange(evt);
            }
        });

        btnBuscarTotal.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarTotal.setText("Atualizar");
        btnBuscarTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTotalActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Relatorio Consumo de Tinta Diário");

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

        boxProd.setBackground(new java.awt.Color(255, 255, 255));
        boxProd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
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

        jPanel4.setBackground(new java.awt.Color(0, 51, 153));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Consumo:");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total:");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Turno 1:");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Turno 2:");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Turno 3:");

        labelConsumoTotal.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConsumoTotal.setForeground(new java.awt.Color(255, 255, 255));
        labelConsumoTotal.setText("total");

        labelConsumoT1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConsumoT1.setForeground(new java.awt.Color(255, 255, 255));
        labelConsumoT1.setText("turno1");

        labelConsumoT2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConsumoT2.setForeground(new java.awt.Color(255, 255, 255));
        labelConsumoT2.setText("turno2");

        labelConsumoT3.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConsumoT3.setForeground(new java.awt.Color(255, 255, 255));
        labelConsumoT3.setText("turno3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelConsumoT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelConsumoT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelConsumoT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelConsumoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelConsumoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelConsumoT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelConsumoT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelConsumoT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout painelPieGraphLayout = new javax.swing.GroupLayout(painelPieGraph);
        painelPieGraph.setLayout(painelPieGraphLayout);
        painelPieGraphLayout.setHorizontalGroup(
            painelPieGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        painelPieGraphLayout.setVerticalGroup(
            painelPieGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Linha de Produção");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Data");

        btnBuscarTurno.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarTurno.setText("Atualizar");
        btnBuscarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTurnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelConsumoTotalDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(boxProd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(chooserDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarTurno)
                                        .addGap(89, 89, 89)
                                        .addComponent(painelConsumoTurnoBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(painelPieGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(intervaloTempo)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(chooserDateIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chooserDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarTotal))))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(painelPieGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(355, 355, 355)
                                        .addComponent(intervaloTempo))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(painelConsumoTurnoBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(boxProd)
                                    .addComponent(btnBuscarTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chooserDateNow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(19, 19, 19)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chooserDateIni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chooserDateEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarTotal, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelConsumoTotalDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBuscarTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTotalActionPerformed
       //Atualiza dataset
       chooseInterval();
    }//GEN-LAST:event_btnBuscarTotalActionPerformed

    private void btnBuscarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTurnoActionPerformed
        //Altera dataset
        chooseDay();
    }//GEN-LAST:event_btnBuscarTurnoActionPerformed

    private void chooserDateNowPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserDateNowPropertyChange
        //Altera o dataset
        chooseDay();
    }//GEN-LAST:event_chooserDateNowPropertyChange

    private void boxProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxProdActionPerformed
        //Atualiza dataset
        chooseDay();
        chooseInterval();
    }//GEN-LAST:event_boxProdActionPerformed

    private void chooserDateIniPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserDateIniPropertyChange
        //Atualiza dataset
        chooseInterval();
    }//GEN-LAST:event_chooserDateIniPropertyChange

    private void chooserDateEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserDateEndPropertyChange
       //Atualiza dataset
       chooseInterval();
    }//GEN-LAST:event_chooserDateEndPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       new ConsumoDiario().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JComboBox<String> boxProd;
    private javax.swing.JButton btnBuscarTotal;
    private javax.swing.JButton btnBuscarTurno;
    private com.toedter.calendar.JDateChooser chooserDateEnd;
    private com.toedter.calendar.JDateChooser chooserDateIni;
    private com.toedter.calendar.JDateChooser chooserDateNow;
    private javax.swing.JLabel intervaloTempo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel labelConsumoT1;
    private javax.swing.JLabel labelConsumoT2;
    private javax.swing.JLabel labelConsumoT3;
    private javax.swing.JLabel labelConsumoTotal;
    private javax.swing.JPanel painelConsumoTotalDiario;
    private javax.swing.JPanel painelConsumoTurnoBar;
    private javax.swing.JPanel painelPieGraph;
    // End of variables declaration//GEN-END:variables
}
