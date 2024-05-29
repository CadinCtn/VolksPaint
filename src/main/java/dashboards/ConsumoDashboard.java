/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboards;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import tinta.ConsumoTinta;

/**
 *
 * @author Lenovo
 */
public class ConsumoDashboard {
    
    private JFreeChart consumoChart;
    
    //Criando dataset
    private CategoryDataset createDatasetBarChart(ConsumoTinta consumoTinta){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Adicionando valores ao dataset
        dataset.addValue(consumoTinta.getTurno1(), "", "Turno 1");
        dataset.addValue(consumoTinta.getTurno2(), "", "Turno 2");
        dataset.addValue(consumoTinta.getTurno3(), "", "Turno 3");
        
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
        
        
        
        
        return chart;
    }
    
    
    
    
}
