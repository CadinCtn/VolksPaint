/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package viewCharts;

import dashboards.ConsumoDashboard;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import view.FuncionariosGUI;
import view.PecasGUI;
import view.PinturaGUI;
import view.TintasGUI;

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
        shouValueKey(panelBarTotal,dashboard);
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
    private void shouValueKey(ChartPanel panel, ConsumoDashboard dashboard){
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
            //Criando popup
            JPopupMenu popupMenu;

            @Override   //Movimentação do mouse
            public void chartMouseMoved(ChartMouseEvent event) {
                if(event.getEntity() instanceof CategoryItemEntity){
                    CategoryItemEntity entity = (CategoryItemEntity) event.getEntity();
                    //Data
                    String data = entity.getColumnKey().toString();
                    float value = entity.getDataset().getValue("", data).floatValue();
                    
                    // Criar o popup com os valores
                    popupMenu = new JPopupMenu();
                    popupMenu.add(new JLabel(data + ": " + value + "L"));

                    // Traduzir a localização da barra para a posição do mouse
                    Rectangle barBounds = entity.getArea().getBounds();
                    int x = (int) barBounds.getCenterX();
                    int y = (int) barBounds.getY();

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
        btnDecDay = new javax.swing.JButton();
        btnAddDay = new javax.swing.JButton();
        lateralPanel5 = new javax.swing.JPanel();
        employeesButton5 = new javax.swing.JButton();
        partsProductionButton5 = new javax.swing.JButton();
        wasteButton5 = new javax.swing.JButton();
        comsumptionButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        exitButton5 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        partsButton5 = new javax.swing.JButton();
        paintsButton5 = new javax.swing.JButton();
        paintingButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consumo Diário");

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

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

        javax.swing.GroupLayout painelConsumoTotalDiarioLayout = new javax.swing.GroupLayout(painelConsumoTotalDiario);
        painelConsumoTotalDiario.setLayout(painelConsumoTotalDiarioLayout);
        painelConsumoTotalDiarioLayout.setHorizontalGroup(
            painelConsumoTotalDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelConsumoTotalDiarioLayout.setVerticalGroup(
            painelConsumoTotalDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        a.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        a.setForeground(new java.awt.Color(5, 5, 5));
        a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a.setText("a");

        intervaloTempo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        intervaloTempo.setForeground(new java.awt.Color(5, 5, 5));
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

        btnBuscarTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscarTotal.setForeground(new java.awt.Color(51, 51, 51));
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

        jPanel4.setBackground(new java.awt.Color(0, 51, 153));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(248, 248, 248));
        jLabel2.setText("Consumo:");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(248, 248, 248));
        jLabel3.setText("Total:");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(248, 248, 248));
        jLabel4.setText("Turno 1:");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(248, 248, 248));
        jLabel5.setText("Turno 2:");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(248, 248, 248));
        jLabel6.setText("Turno 3:");

        labelConsumoTotal.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConsumoTotal.setForeground(new java.awt.Color(248, 248, 248));
        labelConsumoTotal.setText("total");

        labelConsumoT1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConsumoT1.setForeground(new java.awt.Color(248, 248, 248));
        labelConsumoT1.setText("turno1");

        labelConsumoT2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConsumoT2.setForeground(new java.awt.Color(248, 248, 248));
        labelConsumoT2.setText("turno2");

        labelConsumoT3.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        labelConsumoT3.setForeground(new java.awt.Color(248, 248, 248));
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
            .addGap(0, 439, Short.MAX_VALUE)
        );
        painelPieGraphLayout.setVerticalGroup(
            painelPieGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

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

        btnDecDay.setBackground(new java.awt.Color(248, 248, 248));
        btnDecDay.setForeground(new java.awt.Color(248, 248, 248));
        btnDecDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-seta-esquerda-24.png"))); // NOI18N
        btnDecDay.setBorder(null);
        btnDecDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecDayActionPerformed(evt);
            }
        });

        btnAddDay.setBackground(new java.awt.Color(248, 248, 248));
        btnAddDay.setForeground(new java.awt.Color(248, 248, 248));
        btnAddDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-seta-direita-24.png"))); // NOI18N
        btnAddDay.setBorder(null);
        btnAddDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDayActionPerformed(evt);
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
        comsumptionButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/consumo-responsavel (1).png"))); // NOI18N
        comsumptionButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comsumptionButton5ActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo versao 3.png"))); // NOI18N

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
                .addComponent(jLabel10)
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
                .addComponent(jLabel10)
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lateralPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelConsumoTotalDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(boxProd, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(intervaloTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(chooserDateIni, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(chooserDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarTotal))
                                            .addComponent(jLabel8)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(chooserDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDecDay)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAddDay)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarTurno)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                                .addComponent(painelConsumoTurnoBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(painelPieGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnBuscarTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chooserDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnAddDay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDecDay, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGap(10, 10, 10)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(intervaloTempo)
                                        .addGap(1, 1, 1)
                                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnBuscarTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chooserDateEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chooserDateIni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(painelConsumoTurnoBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(painelPieGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(painelConsumoTotalDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
       new ConsumoDiario().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JComboBox<String> boxProd;
    private javax.swing.JButton btnAddDay;
    private javax.swing.JButton btnBuscarTotal;
    private javax.swing.JButton btnBuscarTurno;
    private javax.swing.JButton btnDecDay;
    private com.toedter.calendar.JDateChooser chooserDateEnd;
    private com.toedter.calendar.JDateChooser chooserDateIni;
    private com.toedter.calendar.JDateChooser chooserDateNow;
    private javax.swing.JButton comsumptionButton5;
    private javax.swing.JButton employeesButton5;
    private javax.swing.JButton exitButton5;
    private javax.swing.JLabel intervaloTempo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel labelConsumoT1;
    private javax.swing.JLabel labelConsumoT2;
    private javax.swing.JLabel labelConsumoT3;
    private javax.swing.JLabel labelConsumoTotal;
    private javax.swing.JPanel lateralPanel5;
    private javax.swing.JPanel painelConsumoTotalDiario;
    private javax.swing.JPanel painelConsumoTurnoBar;
    private javax.swing.JPanel painelPieGraph;
    private javax.swing.JButton paintingButton5;
    private javax.swing.JButton paintsButton5;
    private javax.swing.JButton partsButton5;
    private javax.swing.JButton partsProductionButton5;
    private javax.swing.JButton wasteButton5;
    // End of variables declaration//GEN-END:variables
}
