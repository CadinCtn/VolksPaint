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
    
    //Grafico
    private JFreeChart barChart;
    
    //Criando dataset pecas produzidas por turno
    private CategoryDataset createBarDataset(Relatorio relatorio){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(relatorio.getConsumoUnidades(1), "", "Turno 1");
        dataset.addValue(relatorio.getConsumoUnidades(2), "", "Turno 2");
        dataset.addValue(relatorio.getConsumoUnidades(3), "", "Turno 3");
        
        return dataset;
    }

    //Criando grafico pecas produzidas por turno
    private JFreeChart createBarchart(CategoryDataset dataset){
        
        barChart = ChartFactory.createBarChart("Pecas produzidas por turno",
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
        renderer.setDefaultPaint(Color.RED); // Define a cor padrão para todas as barras
        renderer.setShadowVisible(false);

        for(int i = 0; i < plot.getDatasetCount(); i++){
            renderer.setSeriesPaint(i, Color.RED);
        }
        
        return chart;
    }
    
    //Criando painel do grafico
    public ChartPanel painelBarChartPecasTurno(Relatorio relatorio){
        //Carregando dataset
        CategoryDataset dataset = createBarDataset(relatorio);
        
        //Carregando grafico
        barChart = createBarchart(dataset);
        
        //Criando painel
        ChartPanel panelChart = new ChartPanel(barChart);
        panelChart.setPreferredSize(new Dimension(415,275));
        
        return panelChart;
    }
    
    //Atualizando dataset
    public void setNewBarChartDataset(Relatorio relatorio){
        CategoryDataset dataset = createBarDataset(relatorio);
        barChart.getCategoryPlot().setDataset(dataset);
    }
    
    
    
}
