/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import tinta.ConsumoTinta;
import viewConsumo.ConsumoDiario;

/**
 *
 * @author Lenovo
 */
public class ConsumoDashboard {
    

    // Grafico de barras consumo diario por turno
    
    
    private JFreeChart consumoChart;
    
    //Criando dataset
    private CategoryDataset createDatasetBarChart(ConsumoTinta consumoTinta){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Adicionando valores ao dataset
        dataset.addValue(consumoTinta.getTurno1(), "Turno 1", "");
        dataset.addValue(consumoTinta.getTurno2(), "Turno 2", "");
        dataset.addValue(consumoTinta.getTurno3(), "Turno 3", "");
        
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

            //Sequencia de cores das barras
            Paint[] barColors = new Paint[]{
                new Color(255,255,0),  // Laranja
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
    
    
    public ChartPanel painelConsumoChart(ConsumoTinta consumoTinta){
        //Carregando dataset
        CategoryDataset dataset = createDatasetBarChart(consumoTinta);
        //Carregando grafico
        consumoChart = createChartConsumo(dataset);

        //Criando painel        
        ChartPanel painelChart = new ChartPanel(consumoChart);
        painelChart.setPreferredSize(new Dimension(400,300)); //Redimensionando painel
        
        return painelChart;
    }
    
/////////////////////////////////////

    //Grafico de barras consumo total diário
    
    private JFreeChart consumoTotalDiarioChart;
    
    //Criando dataset
    private CategoryDataset createDatasetTotalDiario(List<ConsumoTinta> listConsumo){
        //Criando dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
        
        //Adicionando dados no dataset
        for(ConsumoTinta consumo : listConsumo){
            dataset.addValue(consumo.getTotal(), "", dateForm.format(consumo.getData_registro()));
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
        plot.setBackgroundPaint(new Color(51,51,51));
        plot.setDomainGridlinePaint(new Color(51,51,51));
        plot.setRangeGridlinePaint(new Color(51,51,51));
        plot.setOutlineVisible(false);

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
    public ChartPanel painelConsumoTotalDiario(List<ConsumoTinta> listConsumo){
        
        //Carregando dataset
        CategoryDataset dataset = createDatasetTotalDiario(listConsumo);
        
        //Carregando grafico
        consumoTotalDiarioChart = createChartConsumoTotalDiario(dataset);
        
        //Criando painel
        ChartPanel painelConsumoTotalDiario = new ChartPanel(consumoTotalDiarioChart);
        painelConsumoTotalDiario.setPreferredSize(new Dimension(800,240));
        
        return painelConsumoTotalDiario;
    }
    
}
