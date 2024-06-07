/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboards;

import java.awt.Color;
import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import relatorio.Relatorio;

/**
 *
 * @author Lenovo
 */
public class PecasProduzidasDashboard {
    
    //Grafico de barras - Quantidade de peças por turno
    
    private JFreeChart barChart;
    
    //Criando dataset
    private CategoryDataset createBarDataset(Relatorio relatorio){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Adicionando valores ao dataset
        dataset.addValue(relatorio.getQtdPecas(1), "", "Turno 1");
        dataset.addValue(relatorio.getQtdPecas(2), "", "Turno 2");
        dataset.addValue(relatorio.getQtdPecas(3), "", "Turno 3");
        dataset.addValue(relatorio.getTotalQtdPecas(), "", "Total");
        
        return dataset;
    }
    
    
    //Criando grafico
    private JFreeChart createBarChart(CategoryDataset dataset){
        
        barChart = ChartFactory.createBarChart("Peças produzidas por turno", 
                                               "", 
                                               "", 
                                               dataset,
                                               PlotOrientation.HORIZONTAL, 
                                               true,
                                               false, 
                                               false);
        
        return styleBarChart(barChart);
    }
    
    
    //Personalizando grafico
    private JFreeChart styleBarChart(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();

        // Background
        plot.setBackgroundPaint(Color.WHITE);

        // Barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultPaint(Color.MAGENTA); // Define a cor padrão para todas as barras
        renderer.setShadowVisible(false);

        for(int i = 0; i < plot.getDatasetCount(); i++){
            renderer.setSeriesPaint(i, Color.ORANGE);
        }
        
        // Externo
        chart.setBorderVisible(false);

        // Alinhamento do título
        chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        
        return chart;
    }
    
    
    //Criando painel do gafico
    public ChartPanel createPainelBarChartPecas(Relatorio relatorio){
        //Carregando dataset
        CategoryDataset dataset = createBarDataset(relatorio);
        
        //Carregando grafico
        barChart = createBarChart(dataset);
        
        //Criando painel do grafico
        ChartPanel panelGraph = new ChartPanel(barChart);
        panelGraph.setPreferredSize(new Dimension(415,265));
        
        return panelGraph;
    }
    
    //Atualizando dataset
    public void setNewBarDataset(Relatorio relatorio){
        CategoryDataset dataset = createBarDataset(relatorio);
        this.barChart.getCategoryPlot().setDataset(dataset);
    }
    
}
