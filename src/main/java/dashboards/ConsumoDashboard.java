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
import java.awt.Stroke;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
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
    private JFreeChart turnoBarChart;
    
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
        turnoBarChart = ChartFactory.createBarChart("Consumo diário por turno", 
                                                   "",
                                                   "", 
                                                   dataset,
                                                   PlotOrientation.VERTICAL,
                                                   true,
                                                   false,
                                                   false);
        
        return  styleConsumoChart(turnoBarChart);
    }
    
    //Personalizando grafico
    private JFreeChart styleConsumoChart(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();

            //Background
            chart.setBackgroundPaint(new Color(248,248,248));
            plot.setBackgroundPaint(new Color(248,248,248));
            chart.getLegend().setBackgroundPaint(new Color(248,248,248));
            plot.setDomainGridlinePaint(Color.WHITE);
            plot.setRangeGridlinePaint(Color.WHITE);
            plot.setOutlineVisible(false);
            
            //Barras
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setBarPainter(new StandardBarPainter()); 
            renderer.setShadowVisible(true); // Sombra
            renderer.setShadowPaint(new Color(51,51,51)); //Cor da sombra
            renderer.setDrawBarOutline(true); // Contorno da barra
            
            //Espessura do contorno
            renderer.setSeriesOutlineStroke(0, new BasicStroke(5.0f));
            renderer.setSeriesOutlineStroke(1, new BasicStroke(5.0f));
            renderer.setSeriesOutlineStroke(2, new BasicStroke(5.0f));
            
            //Cor do contorno
            renderer.setSeriesOutlinePaint(0, new Color(255,255,20,100));
            renderer.setSeriesOutlinePaint(1, new Color(239,70,55,100));
            renderer.setSeriesOutlinePaint(2, new Color( 0,253,0,100));
            
            
            //Sequencia de cores das barras
            Paint[] barColors = new Paint[]{
                new Color(255,200,59),  // Amarelo
                new Color(202,28,28), // Vermelho
                new Color(46,207,63),    // Verde
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
            
            
            //Externo
            chart.setBorderVisible(false);
            
            //Alinhamento do titulo
            chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        
            new ServiceCharts().resizeScaleBarChart(turnoBarChart); // Redimensiona o grafico
            
        return chart;
    }
    
    //Criando painel do grafico
    public ChartPanel painelConsumoChart(Relatorio consumoTinta){
        //Carregando dataset
        CategoryDataset dataset = createDatasetBarChart(consumoTinta);
        //Carregando grafico
        turnoBarChart = createChartConsumo(dataset);

        //Criando painel        
        ChartPanel painelChart = new ChartPanel(turnoBarChart);
        painelChart.setPreferredSize(new Dimension(500,300)); //Redimensionando painel
        
        return painelChart;
    }
    
    //Atualizando dataset
    public void setNewBarDataset(Relatorio consumoTinta){
        CategoryDataset dataset = createDatasetBarChart(consumoTinta);
        this.turnoBarChart.getCategoryPlot().setDataset(dataset); // Atualiza dataset
        new ServiceCharts().resizeScaleBarChart(turnoBarChart); // Redimensiona o grafico
    }
    
/////////////////////////////////////

    //Grafico de barras consumo total diário
    
    private JFreeChart totalBarChart;
    
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
        
        totalBarChart = ChartFactory.createBarChart("Consumo Total Diário", 
                                                   "", 
                                                   "", 
                                                   dataset,
                                                   PlotOrientation.VERTICAL,
                                                   false,
                                                   false,
                                                   false);

        return styleChartConsumoTotalDiario(totalBarChart);
    }
    
    //Personalizando grafico
    private JFreeChart styleChartConsumoTotalDiario(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();

        // Background
        chart.setBackgroundPaint(new Color(248,248,248));
        plot.setBackgroundPaint(new Color(248,248,248));
        plot.setRangeGridlinePaint(new Color(51,51,51));
        
        // Barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setShadowPaint(new Color(51,51,51));
        renderer.setShadowVisible(true);
        renderer.setSeriesPaint(0, new Color(195,43,233)); //Cor das barras
        renderer.setDrawBarOutline(true); //contorno
        
        //Espessura do contorno
        renderer.setSeriesOutlineStroke(0, new BasicStroke(5.0f));

        //Cor do contorno
        renderer.setSeriesOutlinePaint(0, new Color(195,43,233,100));
        
        // Externo
        chart.setBorderVisible(false);
        
        //Alterando fonte do eixo X (datas)
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
            domainAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD, 12));
            
        
        // Alinhamento do título
        chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        
        new ServiceCharts().resizeScaleBarChart(chart); // Redimensiona o grafico
        
        return chart;
    }

    //Criando painel do grafico
    public ChartPanel painelConsumoTotalDiario(List<Relatorio> listConsumo){
        
        //Carregando dataset
        CategoryDataset dataset = createDatasetTotalDiario(listConsumo);
        
        //Carregando grafico
        totalBarChart = createChartConsumoTotalDiario(dataset);
        
        //Criando painel
        ChartPanel painelConsumoTotalDiario = new ChartPanel(totalBarChart);
        painelConsumoTotalDiario.setPreferredSize(new Dimension(800,240));
        
        return painelConsumoTotalDiario;
    }
    
    //Atualizando Dataset
    public void setNewBarTotalDataset(List<Relatorio> listConsumo){
        CategoryDataset dataset = createDatasetTotalDiario(listConsumo);
        this.totalBarChart.getCategoryPlot().setDataset(dataset);
        new ServiceCharts().resizeScaleBarChart(totalBarChart); // Redimensiona o grafico
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
                                                       
        return new ServiceCharts().styleRingChartTurno(ringChart);
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
