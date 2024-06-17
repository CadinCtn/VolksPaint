/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package viewCharts;

import dashboards.DesperdicioDashboard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import javax.swing.JOptionPane;
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
public class Desperdicio extends javax.swing.JFrame {

    /**
     * Creates new form Desperdicio
     */
    public Desperdicio() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); //Maximiza janela
        
        //Instanciando classe service
        ServiceRelatorio service = new ServiceRelatorio();
        
        //Setando data de hoje
        chooserDateNow.setDate(service.hoje());
        
        //Instanciando classe dashboard
        this.dashboard = new DesperdicioDashboard();
        
        //Gerando grafico de linhas de consumo e limite de tinta
        this.panelLineChartTurno.setLayout(new BorderLayout());
        Relatorio consumoLimite = service.getRelatorioConsumoDesperdicioUnidadeTurno(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1);
        this.panelLineChartTurno.add(dashboard.painelLineChartTurno(consumoLimite));
        
        //Gerando grafico de barras desperdicio de tinta
        this.panelBarChartTurno.setLayout(new BorderLayout());
        Relatorio desperdicio = service.getRelatorioPercDespercio(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1);
        this.panelBarChartTurno.add(dashboard.painelBarChartPerc(desperdicio));
        
        //Setando valores nos labels
        setValues(consumoLimite);
        
        //Gerando grafico de barras desperdicio total por mes
        this.panelBarChartMes.setLayout(new BorderLayout());
        this.panelBarChartMes.add(dashboard.painelTotalBarChart(service.getRelatorioTotalDesperdicio(yearChooser.getValue(), boxProd.getSelectedIndex()+1)));
        
    }
    
    private DesperdicioDashboard dashboard;

    //Atualiza dataset
    private void chooseDay(){
        //Verifica se a classe dashboard foi inicializada
        if(this.dashboard != null){
            //Verifica se a data não é vazia
            if(chooserDateNow.getDate() != null){
                ServiceRelatorio service = new ServiceRelatorio();
                //Grafico de linhas
                Relatorio consumoLimite = service.getRelatorioConsumoDesperdicioUnidadeTurno(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1);
                dashboard.setNewLineDataset(consumoLimite);
                //Grafico de barras
                Relatorio desperdicio = service.getRelatorioPercDespercio(chooserDateNow.getDate(), boxProd.getSelectedIndex()+1);
                dashboard.setNewBarDatasetPerc(desperdicio);
                
                //Setando valores nos labels
                setValues(consumoLimite);
                
            } else {
              JOptionPane.showMessageDialog(null, "Data inserida inválida!","AVISO", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    //Setando valores no painel
    private void setValues( Relatorio consumoLimite){
        //Consumo unidade
        labelConT1.setText(consumoLimite.getConsumoUnidades(1) + "L");
        labelConT2.setText(consumoLimite.getConsumoUnidades(2)+ "L");
        labelConT3.setText(consumoLimite.getConsumoUnidades(3)+ "L");
        labelConTotal.setText(consumoLimite.getTotalConsumoUnidade()+ "L");
        
        //Limite Unidade
        labelLimiteT1.setText(consumoLimite.getLimiteConsumoUnidade(1)+ "L");
        labelLimiteT2.setText(consumoLimite.getLimiteConsumoUnidade(2)+ "L");
        labelLimiteT3.setText(consumoLimite.getLimiteConsumoUnidade(3)+ "L");
        labelLimiteTotal.setText(consumoLimite.getTotalLimiteConsumo()+ "L");
        
        //Desperdicio
        float d1 = new BigDecimal(consumoLimite.getConsumoUnidades(1) - consumoLimite.getLimiteConsumoUnidade(1)).setScale(4,RoundingMode.HALF_UP).floatValue();
        float d2 = new BigDecimal(consumoLimite.getConsumoUnidades(2) - consumoLimite.getLimiteConsumoUnidade(2)).setScale(4,RoundingMode.HALF_UP).floatValue();
        float d3 = new BigDecimal(consumoLimite.getConsumoUnidades(3) - consumoLimite.getLimiteConsumoUnidade(3)).setScale(4,RoundingMode.HALF_UP).floatValue();
        float dT = new BigDecimal(consumoLimite.getTotalConsumoUnidade() - consumoLimite.getTotalLimiteConsumo()).setScale(4,RoundingMode.HALF_UP).floatValue();
        
        
        //Caso seja menor que 0
        if(d1 <= 0){
            //Não houve desperdicio
            //Alterando cor do texto para verde
            labelConT1.setForeground(new Color(0,255,0));
            d1 = 0;
        } else {
            //houve desperdicio
            //alterando cor do texto para vermelho
            labelConT1.setForeground(new Color(252,0,57));
        }
        //
        if(d2 <= 0){
            labelConT2.setForeground(new Color(0,255,0));
            d2 = 0;
        } else {
            labelConT2.setForeground(new Color(252,0,57));
        }
        //
        if(d3 <= 0){
            labelConT3.setForeground(new Color(0,255,0));
            d3 = 0;
        } else {
            labelConT3.setForeground(new Color(252,0,57));
        }
        //
        if(dT <= 0){
            labelConTotal.setForeground(new Color(0,255,0));
            dT = 0;
        } else {
            labelConTotal.setForeground(new Color(252,0,57));
        }
        
        //Setando valores desperdicio
        labelDespT1.setText(d1+"L");
        labelDespT2.setText(d2+"L");
        labelDespT3.setText(d3+"L");
        labelDespTotal.setText(dT+"L");
        
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
        boxProd = new javax.swing.JComboBox<>();
        chooserDateNow = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnBuscarTurno = new javax.swing.JButton();
        yearChooser = new com.toedter.calendar.JYearChooser();
        jLabel9 = new javax.swing.JLabel();
        panelLineChartTurno = new javax.swing.JPanel();
        panelBarChartTurno = new javax.swing.JPanel();
        panelBarChartMes = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        labelConT1 = new javax.swing.JLabel();
        labelConT2 = new javax.swing.JLabel();
        labelConT3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        labelLimiteT1 = new javax.swing.JLabel();
        labelLimiteT2 = new javax.swing.JLabel();
        labelLimiteT3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        labelDespT1 = new javax.swing.JLabel();
        labelDespT2 = new javax.swing.JLabel();
        labelDespT3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        labelConTotal = new javax.swing.JLabel();
        labelLimiteTotal = new javax.swing.JLabel();
        labelDespTotal = new javax.swing.JLabel();
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
        setTitle("Desperdício");

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Relatório de Desperdício");

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

        boxProd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        boxProd.setForeground(new java.awt.Color(51, 51, 51));
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Linha de Produção");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Data");

        btnBuscarTurno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscarTurno.setForeground(new java.awt.Color(51, 51, 51));
        btnBuscarTurno.setText("Atualizar");
        btnBuscarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTurnoActionPerformed(evt);
            }
        });

        yearChooser.setBackground(new java.awt.Color(255, 255, 255));
        yearChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yearChooserPropertyChange(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Ano");

        javax.swing.GroupLayout panelLineChartTurnoLayout = new javax.swing.GroupLayout(panelLineChartTurno);
        panelLineChartTurno.setLayout(panelLineChartTurnoLayout);
        panelLineChartTurnoLayout.setHorizontalGroup(
            panelLineChartTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLineChartTurnoLayout.setVerticalGroup(
            panelLineChartTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBarChartTurnoLayout = new javax.swing.GroupLayout(panelBarChartTurno);
        panelBarChartTurno.setLayout(panelBarChartTurnoLayout);
        panelBarChartTurnoLayout.setHorizontalGroup(
            panelBarChartTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );
        panelBarChartTurnoLayout.setVerticalGroup(
            panelBarChartTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBarChartMesLayout = new javax.swing.GroupLayout(panelBarChartMes);
        panelBarChartMes.setLayout(panelBarChartMesLayout);
        panelBarChartMesLayout.setHorizontalGroup(
            panelBarChartMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBarChartMesLayout.setVerticalGroup(
            panelBarChartMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(12, 55, 139));

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 25)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Consumo:");

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Turno 1:");

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Turno 2:");

        jLabel19.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Turno 3:");

        labelConT1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConT1.setForeground(new java.awt.Color(255, 255, 255));
        labelConT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConT1.setText("turno1");

        labelConT2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConT2.setForeground(new java.awt.Color(255, 255, 255));
        labelConT2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConT2.setText("turno2");

        labelConT3.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConT3.setForeground(new java.awt.Color(255, 255, 255));
        labelConT3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConT3.setText("turno3");

        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 25)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Limite:");

        labelLimiteT1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelLimiteT1.setForeground(new java.awt.Color(255, 255, 255));
        labelLimiteT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLimiteT1.setText("turno1");

        labelLimiteT2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelLimiteT2.setForeground(new java.awt.Color(255, 255, 255));
        labelLimiteT2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLimiteT2.setText("turno2");

        labelLimiteT3.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelLimiteT3.setForeground(new java.awt.Color(255, 255, 255));
        labelLimiteT3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLimiteT3.setText("turno3");

        jLabel25.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 25)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Desperdício:");

        labelDespT1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelDespT1.setForeground(new java.awt.Color(255, 255, 255));
        labelDespT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDespT1.setText("turno1");

        labelDespT2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelDespT2.setForeground(new java.awt.Color(255, 255, 255));
        labelDespT2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDespT2.setText("turno2");

        labelDespT3.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelDespT3.setForeground(new java.awt.Color(255, 255, 255));
        labelDespT3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDespT3.setText("turno3");

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Total:");

        labelConTotal.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConTotal.setForeground(new java.awt.Color(255, 255, 255));
        labelConTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConTotal.setText("total");

        labelLimiteTotal.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelLimiteTotal.setForeground(new java.awt.Color(255, 255, 255));
        labelLimiteTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLimiteTotal.setText("total");

        labelDespTotal.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelDespTotal.setForeground(new java.awt.Color(255, 255, 255));
        labelDespTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDespTotal.setText("total");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelConTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelConT3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelConT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelConT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelLimiteTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelLimiteT3, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(labelLimiteT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelLimiteT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDespT3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDespT2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDespT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDespTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel25)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(labelLimiteT1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLimiteT2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLimiteT3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(labelDespT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDespT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDespT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(labelConT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelConT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelConT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19))))
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelDespTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelLimiteTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelConTotal)))
                .addContainerGap())
        );

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
        partsProductionButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/produtividade.png"))); // NOI18N
        partsProductionButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partsProductionButton9ActionPerformed(evt);
            }
        });

        wasteButton9.setBackground(new java.awt.Color(0, 0, 51));
        wasteButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/desperdicio-de-dinheiro (1).png"))); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
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
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(0, 143, Short.MAX_VALUE))
                                    .addComponent(panelLineChartTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(chooserDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDecDay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddDay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarTurno))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(boxProd, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelBarChartTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelBarChartMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addGap(0, 0, 0)
                                .addComponent(boxProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chooserDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnBuscarTurno)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnAddDay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDecDay, javax.swing.GroupLayout.Alignment.TRAILING)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelBarChartTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelLineChartTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBarChartMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lateralPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void boxProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxProdActionPerformed
        //Atualiza dataset
            //Consumo Limite e porcentagem de desperdicio por turno
            chooseDay();
            
            //Total de desperdicio por mes
            dashboard.setNewTotalBarChartDataset(new ServiceRelatorio().getRelatorioTotalDesperdicio(yearChooser.getValue(), boxProd.getSelectedIndex()+1));
    }//GEN-LAST:event_boxProdActionPerformed

    private void chooserDateNowPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserDateNowPropertyChange
        //Atualiza dataset
        chooseDay();
    }//GEN-LAST:event_chooserDateNowPropertyChange

    private void btnBuscarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTurnoActionPerformed
        //Atualiza dataset
        chooseDay();
    }//GEN-LAST:event_btnBuscarTurnoActionPerformed

    private void yearChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yearChooserPropertyChange
        if(dashboard != null){
            //Atualiza dataset
            dashboard.setNewTotalBarChartDataset(new ServiceRelatorio().getRelatorioTotalDesperdicio(yearChooser.getValue(), boxProd.getSelectedIndex()+1));
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
        new PecasProduzidas().setVisible(true);
        dispose();
    }//GEN-LAST:event_partsProductionButton9ActionPerformed

    private void wasteButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wasteButton9ActionPerformed
        
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
        new Desperdicio().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxProd;
    private javax.swing.JButton btnAddDay;
    private javax.swing.JButton btnBuscarTurno;
    private javax.swing.JButton btnDecDay;
    private com.toedter.calendar.JDateChooser chooserDateNow;
    private javax.swing.JButton comsumptionButton9;
    private javax.swing.JButton employeesButton9;
    private javax.swing.JButton exitButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JLabel labelConT1;
    private javax.swing.JLabel labelConT2;
    private javax.swing.JLabel labelConT3;
    private javax.swing.JLabel labelConTotal;
    private javax.swing.JLabel labelDespT1;
    private javax.swing.JLabel labelDespT2;
    private javax.swing.JLabel labelDespT3;
    private javax.swing.JLabel labelDespTotal;
    private javax.swing.JLabel labelLimiteT1;
    private javax.swing.JLabel labelLimiteT2;
    private javax.swing.JLabel labelLimiteT3;
    private javax.swing.JLabel labelLimiteTotal;
    private javax.swing.JPanel lateralPanel9;
    private javax.swing.JButton paintingButton9;
    private javax.swing.JButton paintsButton9;
    private javax.swing.JPanel panelBarChartMes;
    private javax.swing.JPanel panelBarChartTurno;
    private javax.swing.JPanel panelLineChartTurno;
    private javax.swing.JButton partsButton9;
    private javax.swing.JButton partsProductionButton9;
    private javax.swing.JButton wasteButton9;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
