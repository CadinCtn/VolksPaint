package dashboards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.text.NumberFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import relatorio.Relatorio;

/**
 *
 * @author Lenovo
 */
public class DesperdicioDashboard {
 
    //Grafico de linhas
    private JFreeChart lineChart;
    
    //Criando dataset
    private CategoryDataset createLineDataset(Relatorio relatorio){
        //Criando dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Inserindo valores
            //Consumo por unidade
            dataset.addValue(relatorio.getConsumoUnidades(1), "Consumo por Unidade", "Turno 1");
            dataset.addValue(relatorio.getConsumoUnidades(2), "Consumo por Unidade", "Turno 2");
            dataset.addValue(relatorio.getConsumoUnidades(3), "Consumo por Unidade", "Turno 3");
            dataset.addValue(relatorio.getTotalConsumoUnidade(), "Consumo por Unidade", "Total");
            
        
            //Limite de consumo
            dataset.addValue(relatorio.getLimiteConsumoUnidade(1), "Limite de Consumo", "Turno 1");
            dataset.addValue(relatorio.getLimiteConsumoUnidade(2), "Limite de Consumo", "Turno 2");
            dataset.addValue(relatorio.getLimiteConsumoUnidade(3), "Limite de Consumo", "Turno 3");
            dataset.addValue(relatorio.getTotalLimiteConsumo(), "Limite de Consumo", "Total");
        //
        
        return dataset;
    }
    
    //Criando Grafico
    private JFreeChart createLineChart(CategoryDataset dataset){
        //Criando grafico
        JFreeChart chart = ChartFactory.createLineChart("Consumo de Tinta por unidade",
                                                 "",
                                                 "",
                                                 dataset,
                                                 PlotOrientation.VERTICAL, 
                                                 true,
                                                 false,
                                                 false);
                                                 
        return styleLineChart(chart);
    }
    
    //Estilisando grafico
        private JFreeChart styleLineChart(JFreeChart chart){
            
            CategoryPlot plot = chart.getCategoryPlot();
            
            LineAndShapeRenderer renderer = new LineAndShapeRenderer();
            renderer.setSeriesPaint(0, Color.BLUE); //Cor da linha
            renderer.setSeriesPaint(1, Color.RED); //Cor da linha
            renderer.setSeriesStroke(0,new BasicStroke(2.3f)); //Espessura da linha
            renderer.setSeriesStroke(1,new BasicStroke(2.3f)); //Espessura da linha
            //Alterando fonte da legenda
            renderer.setLegendTextFont(0, new Font("SansSerif", Font.BOLD, 14));
            renderer.setLegendTextFont(1, new Font("SansSerif", Font.BOLD, 14));
            
            plot.setRenderer(renderer);
            
            plot.setBackgroundPaint(new Color(248,248,248)); //Cor do fundo
            chart.setBackgroundPaint(new Color(248,248,248));
            chart.getLegend().setBackgroundPaint(new Color(248,248,248));
            
            
            plot.setRangeGridlinesVisible(true);
            plot.setRangeGridlinePaint(Color.BLACK);

            plot.setDomainGridlinesVisible(true);
            plot.setDomainGridlinePaint(Color.BLACK);
            
            
            //Alterando fonte do eixo X
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
            domainAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD, 14));
            //Ajute no intervalo do eixo X
            domainAxis.setLowerMargin(0);
            
            new ServiceCharts().resizeScaleLineChart(chart); //Redimensiona Line Chart
            
            return chart;
        }
    
    
    //Criando painel do grafico
    public ChartPanel painelLineChartTurno(Relatorio relatorio){
        //Carregando dataset
        CategoryDataset dataset = createLineDataset(relatorio);
        
        //Carregando grafico
        lineChart = createLineChart(dataset);
        
        //Criando painel
        ChartPanel panelChart = new ChartPanel(lineChart);
        panelChart.setPreferredSize(new Dimension(705,295));
        
        return panelChart;
    }
    
    //Atualiza dataset
    public void setNewLineDataset(Relatorio relatorio){
        CategoryDataset dataset = createLineDataset(relatorio);
        lineChart.getCategoryPlot().setDataset(dataset); // Atualiza dataset
        new ServiceCharts().resizeScaleLineChart(lineChart); //Redimensiona escala do grafico
    }
    
 ///////////
    //Grafico de barras horizontal | porcentagem de desperdicio
    
    private JFreeChart barChartPerc;
    
    //Criando dataset
    private CategoryDataset createBarPercDataset(Relatorio relatorio){
        //Criando dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Adicionando valores
        dataset.addValue(relatorio.getDesperdicioTinta(1), "Turno 1", "");
        dataset.addValue(relatorio.getDesperdicioTinta(2), "Turno 2", "");
        dataset.addValue(relatorio.getDesperdicioTinta(3), "Turno 3", "");
        dataset.addValue(relatorio.getTotalDesperdicioTinta(), "Total", "");
        
        return dataset;
    }


    //Criando grafico
    private JFreeChart createBarChartPerc(CategoryDataset dataset){
        //Criando grafico
        JFreeChart chart = ChartFactory.createBarChart("Porcentagem de Desperdício de Tinta ", 
                                                       "",
                                                       "",
                                                       dataset,
                                                       PlotOrientation.HORIZONTAL,
                                                       true,
                                                       false,
                                                       false);
         
        return styleBarChart(chart);                          
    }


    //Personalizando grafico
    private JFreeChart styleBarChart(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();

        // Background
        plot.setBackgroundPaint(new Color(248,248,248));
        chart.setBackgroundPaint(new Color(248,248,248));
        chart.getLegend().setBackgroundPaint(new Color(248,248,248));

        // Barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultPaint(new Color(210,46,46)); // Define a cor padrão para todas as barras
        renderer.setShadowPaint(new Color(51,51,51));
        renderer.setShadowVisible(true);

        //Sequencia de cores das barras
            Paint[] barColors = new Paint[]{
                new Color(255,200,59), // Amarelo
                new Color(202,28,28),  // Vermelho
                new Color(46,207,63),    // Verde
                new Color( 192,100,235) // Roxo
            };

            //Inserindo cores
            for(int i = 0; i < 4; i++){
                renderer.setSeriesPaint(i, barColors[i % barColors.length]);
            }
        
        
        //Mostrando Valores
            renderer.setDefaultItemLabelGenerator(new CustomLabelGenerator());
            renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 12));
            renderer.setDefaultItemLabelsVisible(true);
            
            //Alterando fonte da legenda
            renderer.setLegendTextFont(0,new Font("SansSerif", Font.BOLD, 14));
            renderer.setLegendTextFont(1,new Font("SansSerif", Font.BOLD, 14));
            renderer.setLegendTextFont(2,new Font("SansSerif", Font.BOLD, 14));
            renderer.setLegendTextFont(3,new Font("SansSerif", Font.BOLD, 14));
            
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
    
    //Personalizando formato do label Generator
    private static class CustomLabelGenerator extends StandardCategoryItemLabelGenerator {
        private static final NumberFormat format = NumberFormat.getInstance();

        @Override
        public String generateLabel(CategoryDataset dataset, int row, int column) {
            Number value = dataset.getValue(row, column);
            if (value != null) {
                return " "+format.format(value) + "%";
            }
            return "";
        }
    }
    
    
    //Criando painel do grafico
    public ChartPanel painelBarChartPerc(Relatorio relatorio){
        //Carregando dataset
        CategoryDataset dataset = createBarPercDataset(relatorio);
        //Carregando grafico
        barChartPerc = createBarChartPerc(dataset);
        
        ChartPanel panelGraph = new ChartPanel(barChartPerc);
        panelGraph.setPreferredSize(new Dimension(537,295));
         
        return panelGraph;
    }
    
    //Atualizando dataset
    public void setNewBarDatasetPerc(Relatorio relatorio){
        CategoryDataset dataset = createBarPercDataset(relatorio);
        barChartPerc.getCategoryPlot().setDataset(dataset); //Setando novo dataset
        new ServiceCharts().resizeScaleBarChart(barChartPerc); // Redimensionando escala do grafico
    }
    
    
/////////////////
    //Grafico de Desperdicio Total Por mes
    
    private JFreeChart barChartTotalMes;
    
    //Criando dataset grafico de barras desperdicio total por mes
    private CategoryDataset createTotalBarChartDataset(List<Relatorio> listRelatorio){
        //Criando dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //adicionando valores
        for(Relatorio desperdicio : listRelatorio){
            dataset.addValue(desperdicio.getTotalDesperdicioTinta(), "Desperdicio de Tinta", desperdicio.toMonth());
        }
        
        return dataset;
    }
    
    
    //Criando grafico
    private JFreeChart createTotalBarChart(CategoryDataset dataset){
        //Criando grafico
        JFreeChart chart = ChartFactory.createBarChart("    Total de Desperdicio de Tinta por mes no ano", 
                                                       "",
                                                       "",
                                                       dataset,
                                                       PlotOrientation.VERTICAL,
                                                       false,
                                                       false, 
                                                       false);
        
        return styleTotalBarChart(chart);
    }
    
    
    //Personalizando grafico
    private JFreeChart styleTotalBarChart(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();

        // Background
        plot.setBackgroundPaint(new Color(248,248,248));
        chart.setBackgroundPaint(new Color(248,248,248));

        // Barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultPaint(new Color(195,43,233)); // Define a cor padrão para todas as barras
        renderer.setShadowPaint(new Color(51,51,51));
        renderer.setShadowVisible(true);
        plot.setOutlineVisible(false);
        
        renderer.setSeriesPaint(0, new Color(195,43,233));
        
        renderer.setDrawBarOutline(true); //contorno
        
        //Espessura do contorno
        renderer.setSeriesOutlineStroke(0, new BasicStroke(5.0f));

        //Cor do contorno
        renderer.setSeriesOutlinePaint(0, new Color(195,43,233,100));
        
        //Mostrando Valores
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 12));
        renderer.setDefaultItemLabelsVisible(true);

        //Alterando fonte eixo X
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.BOLD, 12));
        
        // Externo
        chart.setBorderVisible(false);
        
        
        // Alinhamento do título
        chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        
        new ServiceCharts().resizeScaleBarChart(chart); // Redimensiona o grafico
        
        return chart;
    }
    
    //Criando painel do grafico
    public ChartPanel painelTotalBarChart(List<Relatorio> listRelatorio){
        //Carregando dataset
        CategoryDataset dataset = createTotalBarChartDataset(listRelatorio);
        
        //Carregando grafico
        barChartTotalMes = createTotalBarChart(dataset);
        
        //Criando painel do grafico
        ChartPanel panelChart = new ChartPanel(barChartTotalMes);
        panelChart.setPreferredSize(new Dimension(800,235));
        
        return panelChart;
    }
    
    //Atualiza dataset
    public void setNewTotalBarChartDataset(List<Relatorio> listRelatorio){
        CategoryDataset dataset = createTotalBarChartDataset(listRelatorio);
        this.barChartTotalMes.getCategoryPlot().setDataset(dataset); //Atualiza dataset
        new ServiceCharts().resizeScaleBarChart(barChartTotalMes); //Redimensiona escala
    }
}
