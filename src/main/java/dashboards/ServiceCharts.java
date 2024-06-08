/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboards;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author Lenovo
 */
public class ServiceCharts {
    
    //Redimensiona escala de valores do grafico de linhas
    public void resizeScaleLineChart(JFreeChart chart){
            CategoryPlot plot = chart.getCategoryPlot();

            // Calcular menor e maior valor da série
            int minValue = 0;
            int maxValue = 1;
            
            //Dataset
            CategoryDataset dataset = plot.getDataset();
            
            //Percorrendo valores do datset
            for (int row = 0; row < dataset.getRowCount(); row++) {
                for (int column = 0; column < dataset.getColumnCount(); column++) {
                    Number value = dataset.getValue(row, column);
                    
                    //Setando maior e manor valor
                    if (value != null) {
                        int intValue = value.intValue();
                        if (intValue < minValue || minValue == 0) {
                            minValue = intValue;
                        }
                        if (intValue > maxValue) {
                            maxValue = intValue;
                        }
                    }
                }
            }

            // Ajustar valores com 10% de margem
            int rangeLower = (int) (minValue - minValue*0.1);
            int rangeMax = (int) Math.ceil(maxValue + maxValue*0.1);
            
            // Configurar a escala do eixo Y
            NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
            yAxis.setRange(rangeLower, rangeMax);
        }
    

    //Redimensiona escala de valores do grafico de barras
    public void resizeScaleBarChart(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryDataset dataset = plot.getDataset();
        
        double maxValue = 1;

        for (int row = 0; row < dataset.getRowCount(); row++) {
            for (int column = 0; column < dataset.getColumnCount(); column++) {
                Number value = dataset.getValue(row, column);
                if (value != null && value.doubleValue() > maxValue) {
                    maxValue = value.doubleValue();
                }
            }
        }
        
        //Ajustando para 10% a mais do valor maximo
        float rangeMax = (float) (maxValue + maxValue*0.1);
            
            NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
            yAxis.setRange(0.0, rangeMax);
    }

    
    //Personalizando grafico Ring Chart por turno
        public JFreeChart styleRingChartTurno(JFreeChart graph){
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
            
            //Legenda
            graph.getLegend().setItemFont(new Font("SansSerif", Font.BOLD, 14));
            return graph;
        }
        
    
}
