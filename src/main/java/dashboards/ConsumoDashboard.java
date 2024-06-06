/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import relatorio.Relatorio;

/**
 *
 * @author Lenovo
 */
public class ConsumoDashboard {
    

    // Grafico de barras consumo diario por turno
    
    
    private JFreeChart consumoChart;
    
    //Criando dataset
    private CategoryDataset createDatasetBarChart(Relatorio consumoTinta){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Adicionando valores ao dataset
        dataset.addValue(consumoTinta.getConsumoTinta(1), "Turno 1", "");
        dataset.addValue(consumoTinta.getConsumoTinta(2), "Turno 2", "");
        dataset.addValue(consumoTinta.getConsumoTinta(3), "Turno 3", "");
        
        return dataset;
    }
    
    //Criando grafico
    private JFreeChart createChartConsumo(CategoryDataset dataset){
        consumoChart = ChartFactory.createBarChart("Consumo diário por turno", 
                                                   "",
                                                   "", 
                                                   dataset,
                                                   PlotOrientation.VERTICAL,
                                                   true,
                                                   false,
                                                   false);
        
        return  styleConsumoChart(consumoChart);
    }
    
    //Personalizando grafico
    private JFreeChart styleConsumoChart(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();

            //Background
            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(Color.WHITE);
            plot.setRangeGridlinePaint(Color.WHITE);
            plot.setOutlineVisible(false);
            
            
            //Barras
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setShadowVisible(false);
            
            //Mostrando Valores
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 12));
            renderer.setDefaultItemLabelsVisible(true);
            
            
            //Sequencia de cores das barras
            Paint[] barColors = new Paint[]{
                new Color(255,255,20),  // Amarelo
                new Color(239,70,55), // Vermelho
                new Color(0,253,0),    // Verde
            };

            //Inserindo cores
            for(int i = 0; i < 4; i++){
                renderer.setSeriesPaint(i, barColors[i % barColors.length]);
            }
            
            //Externo
            chart.setBorderVisible(false);
            
            //Alinhamento do titulo
            chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        
        return chart;
    }
    
    //Criando painel do grafico
    public ChartPanel painelConsumoChart(Relatorio consumoTinta){
        //Carregando dataset
        CategoryDataset dataset = createDatasetBarChart(consumoTinta);
        //Carregando grafico
        consumoChart = createChartConsumo(dataset);

        //Criando painel        
        ChartPanel painelChart = new ChartPanel(consumoChart);
        painelChart.setPreferredSize(new Dimension(500,300)); //Redimensionando painel
        
        return painelChart;
    }
    
    //Atualizando dataset
    public void setNewBarDataset(Relatorio consumoTinta){
        CategoryDataset dataset = createDatasetBarChart(consumoTinta);
        this.consumoChart.getCategoryPlot().setDataset(dataset);
    }
    
/////////////////////////////////////

    //Grafico de barras consumo total diário
    
    private JFreeChart consumoTotalDiarioChart;
    
    //Criando dataset
    private CategoryDataset createDatasetTotalDiario(List<Relatorio> listConsumo){
        //Criando dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
        
        //Adicionando dados no dataset
        for(Relatorio consumo : listConsumo){
            dataset.addValue(consumo.getTotalConsumoTinta(), "", dateForm.format(consumo.getData()));
        }
        
        return dataset;
    }
    
    //Criando grafico
    private JFreeChart createChartConsumoTotalDiario(CategoryDataset dataset){
        
        consumoTotalDiarioChart = ChartFactory.createBarChart("Consumo Total Diário", 
                                                   "", 
                                                   "", 
                                                   dataset,
                                                   PlotOrientation.VERTICAL,
                                                   false,
                                                   false,
                                                   false);

        return styleChartConsumoTotalDiario(consumoTotalDiarioChart);
    }
    
    //Personalizando grafico
    private JFreeChart styleChartConsumoTotalDiario(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();

        // Background
        plot.setBackgroundPaint(Color.WHITE);

        // Barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultPaint(Color.MAGENTA); // Define a cor padrão para todas as barras
        renderer.setShadowVisible(false);

        for(int i = 0; i < plot.getDatasetCount(); i++){
            renderer.setSeriesPaint(i, Color.MAGENTA);
        }
        
        // Externo
        chart.setBorderVisible(false);

        // Alinhamento do título
        chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        
        return chart;
    }

    
    //Criando painel do grafico
    public ChartPanel painelConsumoTotalDiario(List<Relatorio> listConsumo){
        
        //Carregando dataset
        CategoryDataset dataset = createDatasetTotalDiario(listConsumo);
        
        //Carregando grafico
        consumoTotalDiarioChart = createChartConsumoTotalDiario(dataset);
        
        //Criando painel
        ChartPanel painelConsumoTotalDiario = new ChartPanel(consumoTotalDiarioChart);
        painelConsumoTotalDiario.setPreferredSize(new Dimension(800,240));
        
        return painelConsumoTotalDiario;
    }
    
    //Atualizando Dataset
    public void setNewBarTotalDataset(List<Relatorio> listConsumo){
        CategoryDataset dataset = createDatasetTotalDiario(listConsumo);
        this.consumoTotalDiarioChart.getCategoryPlot().setDataset(dataset);
    }
    
    ////////
    //PieChart
    
    //Grafico
    private JFreeChart ringChart;
    
    //Criando dataset 
    private PieDataset createPieDataset(Relatorio consumo){
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        dataset.setValue("Turno 1", consumo.getConsumoTinta(1));
        dataset.setValue("Turno 2", consumo.getConsumoTinta(2));
        dataset.setValue("Turno 3", consumo.getConsumoTinta(3));
        
        return dataset;
    }
    
     
    //Criando grafico    
    private JFreeChart createRingChart(PieDataset dataset){
        
        ringChart = ChartFactory.createRingChart("", 
                                                       dataset,
                                                       true,
                                                       false,
                                                       false);
                                                       
        return styleRingGraph(ringChart);
    }
     
    //Personalizando grafico
        private JFreeChart styleRingGraph(JFreeChart graph){
            RingPlot plot = (RingPlot) graph.getPlot();
            //plot.setSimpleLabels(true);
            plot.setSectionDepth(0.5);
            plot.setSeparatorsVisible(false);
            plot.setSectionOutlinesVisible(false);
            plot.setBackgroundPaint(Color.WHITE);
            plot.setShadowPaint(null);
            plot.setOutlinePaint(null);
            graph.setBorderVisible(false);
            
            plot.setSectionPaint("Turno 1", new Color(255,255,20)); // Amarelo
            plot.setSectionPaint("Turno 2", new Color(239,70,55)); // Vermelho
            plot.setSectionPaint("Turno 3", new Color(0,253,0));  // Verde
            
            
            //Label
            plot.setLabelBackgroundPaint(null);
            plot.setLabelShadowPaint(null);
            plot.setLabelOutlinePaint(null);
            plot.setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}", new DecimalFormat("#,##0"), new DecimalFormat("0.0%")));
            plot.setLabelPaint(Color.BLACK);
            plot.setSimpleLabels(true);
            
            return graph;
        }
        
        
        //Criando painel do grafico
        public ChartPanel painelPieConsumo(Relatorio consumo){
            //Carregando dataset
            PieDataset dataset = createPieDataset(consumo);
            
            //Carregando grafico
            JFreeChart chart = createRingChart(dataset);
            
            //Gerando painel do grafico
            ChartPanel painelGraph = new ChartPanel(chart);
            painelGraph.setPreferredSize(new Dimension(400,400));
            
            return painelGraph;
        }
        
        //Atualizando Dataset
        public void setNewPieDataset(Relatorio consumo){
            PiePlot plot = (PiePlot) ringChart.getPlot();
            plot.setDataset(createPieDataset(consumo));
        }
    
}
