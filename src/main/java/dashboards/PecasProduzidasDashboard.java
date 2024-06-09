/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
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
public class PecasProduzidasDashboard {
    
    //Grafico
    private JFreeChart barChart;
    
    //Criando dataset pecas produzidas por turno
    private CategoryDataset createBarDataset(Relatorio relatorio){

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(relatorio.getQtdPecas(1), "Turno 1", "");
        dataset.addValue(relatorio.getQtdPecas(2), "Turno 2", "");
        dataset.addValue(relatorio.getQtdPecas(3), "Turno 3", "");
        dataset.addValue(relatorio.getTotalQtdPecas(), "Total", "");
        
        return dataset;
    }

    //Criando grafico pecas produzidas por turno
    private JFreeChart createBarchart(CategoryDataset dataset){
        
        barChart = ChartFactory.createBarChart("Pecas produzidas por turno",
                                               "",
                                               "", 
                                               dataset, 
                                               PlotOrientation.VERTICAL,
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
        plot.setOutlineVisible(false);
        
        // Barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultPaint(new Color(210,46,46)); // Define a cor padrão para todas as barras
        renderer.setShadowPaint(new Color(51,51,51));
        renderer.setShadowVisible(true);

        //Sequencia de cores das barras
            Paint[] barColors = new Paint[]{
                new Color(255,200,59),  // Amarelo
                new Color(202,28,28), // Vermelho
                new Color(46,207,63),    // Verde
                new Color(192,100,235) // Roxo
            };

            //Inserindo cores
            for(int i = 0; i < 4; i++){
                renderer.setSeriesPaint(i, barColors[i % barColors.length]);
            }
        
        
        //Mostrando Valores
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 12));
            renderer.setDefaultItemLabelsVisible(true);
            
            //Legenda
            renderer.setLegendTextFont(0, new Font("SansSerif", Font.BOLD, 14));
            renderer.setLegendTextFont(1, new Font("SansSerif", Font.BOLD, 14));
            renderer.setLegendTextFont(2, new Font("SansSerif", Font.BOLD, 14));
            renderer.setLegendTextFont(3, new Font("SansSerif", Font.BOLD, 14));
            
            renderer.setDrawBarOutline(true); // Contorno da barra
            
            //Espessura do contorno
            renderer.setSeriesOutlineStroke(0, new BasicStroke(5.0f));
            renderer.setSeriesOutlineStroke(1, new BasicStroke(5.0f));
            renderer.setSeriesOutlineStroke(2, new BasicStroke(5.0f));
            renderer.setSeriesOutlineStroke(3, new BasicStroke(5.0f));
            
            
            //Cor do contorno
            renderer.setSeriesOutlinePaint(0, new Color(255,255,20,100));
            renderer.setSeriesOutlinePaint(1, new Color(239,70,55,100));
            renderer.setSeriesOutlinePaint(2, new Color( 0,253,0,100));
            renderer.setSeriesOutlinePaint(3, new Color( 195,43,233,100));
            
            

            //Faz eixo Y exibir apenas numeros inteiros
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            

        new ServiceCharts().resizeScaleBarChart(chart); // Redimensiona escala
            
        return chart;
    }
    
    //Criando painel do grafico
    public ChartPanel painelBarChartPecasTurno(Relatorio relatorio){
        //Carregando dataset
        CategoryDataset dataset = createBarDataset(relatorio);
        
        //Carregando grafico
        this.barChart = createBarchart(dataset);
        
        //Criando painel
        ChartPanel panelChart = new ChartPanel(this.barChart);
        panelChart.setPreferredSize(new Dimension(750,285));
        
        return panelChart;
    }
    
    //Atualizando dataset
    public void setNewBarChartDataset(Relatorio relatorio){
        CategoryDataset dataset = createBarDataset(relatorio);
        barChart.getCategoryPlot().setDataset(dataset); // Atualiza dataset
        new ServiceCharts().resizeScaleBarChart(barChart); // Redimensiona escala
    }
    
    
    
 //////////////
    //Grafico de linhas - Pecas produzidas mensalmente por ano
    
    private JFreeChart lineChart;
    
    //Criando dataset
    private CategoryDataset createLineDataset(List<Relatorio> listRelatorio){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Percorrendo lista
        for(Relatorio mesPecas : listRelatorio){
            //Adicionando valores no dataset
            dataset.addValue(mesPecas.getTotalQtdPecas(), "Quantidade de Peças", mesPecas.toMonth());
        }
        
        return dataset;
    }
    
    
    //Criando grafico
    private JFreeChart createLineChart(CategoryDataset dataset){
        
        //Criando grafico
        lineChart = ChartFactory.createLineChart("    Pecas produzidas por mes no ano",
                                                 "",
                                                 "",
                                                 dataset,
                                                 PlotOrientation.VERTICAL,
                                                 true,
                                                 false,
                                                 false);
        
        
        return styleLineChart(lineChart);
    }
    
    //Estilisando grafico
        private JFreeChart styleLineChart(JFreeChart graph){
            
            CategoryPlot plot = graph.getCategoryPlot();
            
            LineAndShapeRenderer renderer = new LineAndShapeRenderer();
            plot.setRenderer(renderer);
            renderer.setSeriesPaint(0, new Color(192, 100, 235)); //Cor da linha
            renderer.setSeriesStroke(0,new BasicStroke(2.2f)); //Espessura da linha
            renderer.setLegendTextFont(0,new Font("SansSerif", Font.BOLD, 14));
            
            
            plot.setBackgroundPaint(Color.WHITE); //Cor do fundo
            
            plot.setRangeGridlinesVisible(true);
            plot.setRangeGridlinePaint(Color.BLACK);

            plot.setDomainGridlinesVisible(true);
            plot.setDomainGridlinePaint(Color.BLACK);

            //Alterando fonte do eixo X (datas)
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
            domainAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD, 12));
            
            //Ajute no intervalo do eixo X
            domainAxis.setLowerMargin(0);
            
            //Faz eixo Y exibir apenas numeros inteiros
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            

            // Alinhamento do título
            graph.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
            
            new ServiceCharts().resizeScaleLineChart(graph); //Redimensiona Line Chart
            
            return graph;
        }
    
        
        //Criando painel
        public ChartPanel painelLineChartPecasMes(List<Relatorio> listRelatorio){
            //Carregando datset
            CategoryDataset dataset = createLineDataset(listRelatorio);
            
            //Carregando grafico
            lineChart = createLineChart(dataset);
            
            //Criando painel do grafico
            ChartPanel panelChart = new ChartPanel(lineChart);
            panelChart.setPreferredSize(new Dimension(800,175));
            
            return panelChart;
        }
        
        
        //Atuzalizando dataset
        public void setNewLineChartDataset(List<Relatorio> listRelatorio){
            CategoryDataset dataset = createLineDataset(listRelatorio);
            lineChart.getCategoryPlot().setDataset(dataset); // Atualiza dataset
            new ServiceCharts().resizeScaleLineChart(lineChart); //Redimensiona Line Chart
        }

        
        
////////////
    //Ring Chart - Pecas produzidas por turno
        
        private JFreeChart ringChart;
        
        //Criando dataset
        private PieDataset createPieDataset(Relatorio relatorio){
            //Criando dataset
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            //Adicionando valores
            dataset.setValue("Turno 1", relatorio.getQtdPecas(1));
            dataset.setValue("Turno 2", relatorio.getQtdPecas(2));
            dataset.setValue("Turno 3", relatorio.getQtdPecas(3));
            
            return dataset;
        }
        
        //Criando grafico
        private JFreeChart createRingChart(PieDataset dataset){
            //Criando grafico
            ringChart = ChartFactory.createRingChart("",
                                                     dataset,
                                                     true,
                                                     false,
                                                     false);
            
            return new ServiceCharts().styleRingChartTurno(ringChart);
        }
        
        //Criando painel do grafico
        public ChartPanel painelRingChartTurno(Relatorio relatorio){
            //Carregando dataset
            PieDataset dataset = createPieDataset(relatorio);
            
            //Carregando grafico
            ringChart = createRingChart(dataset);
            
            //Gerando painel do grafico
            ChartPanel painelGraph = new ChartPanel(ringChart);
            painelGraph.setPreferredSize(new Dimension(400,400));
            
            return painelGraph;
        }
        
        //Atualizando Dataset
        public void setNewPieDataset(Relatorio relatorio){
            PiePlot plot = (PiePlot) ringChart.getPlot();
            plot.setDataset(createPieDataset(relatorio));
        }
        
        
}
