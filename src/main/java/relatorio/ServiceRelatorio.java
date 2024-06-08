/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

import com.toedter.calendar.JCalendar;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ServiceRelatorio {
    
    
    //Retorna a data de hoje
    public Date hoje(){
        return new JCalendar().getCalendar().getTime();
    }
    
    //Primerio dia do mes
    public Date getFirstDay() {
        // Obter o calendário atual
        Calendar calendar = Calendar.getInstance();
        // Definir o dia como 1
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // Obter a data resultante
        return calendar.getTime();
    }
    
    //Ultimo dia do mes
    public Date getLastDay() {
        // Obter o calendário atual
        Calendar calendar = Calendar.getInstance();
        // Adicionar um mês à data atual
        calendar.add(Calendar.MONTH, 1);
        // Definir o dia como 1
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // Subtrair um dia para obter o último dia do mês atual
        calendar.add(Calendar.DATE, -1);
        // Obter a data resultante
        return calendar.getTime();
    }
    
    
    //Função que retorna o relatório de consumoDiario do dia específico
    public Relatorio getRelatorioDiarioConsumo(Date data, int linhaProducao){
        //Requisitando do banco o relatorio do dia
        List<Relatorio> listRelatorio = new RelatorioDAO().selectConsumoTinta(new java.sql.Date(data.getTime()), new java.sql.Date(data.getTime()), linhaProducao);
        //Verificando se existe relatorio nesse dia
        if(!listRelatorio.isEmpty()){
            //Retorna o relatorio
            return listRelatorio.get(0);
        }
        
        //Retorna relatorio vazio
        return new Relatorio(new java.sql.Date(data.getTime()), linhaProducao, 0, 0, 0);
    }
    
    
    //Função que retorna lista de relatórios em um intervalo de dias
    public List<Relatorio> getRelatorioTotalConsumoDiario(Date dataInicio, Date dataFim, int linhaProducao){
        //Retorna Lista de relatorios em umm intervalo de tempo especificoo
        return new RelatorioDAO().selectConsumoTinta(new java.sql.Date(dataInicio.getTime()), new java.sql.Date(dataFim.getTime()), linhaProducao);
    }

    //Retorna relatorio de Pecas produzidas por turno no dia
    public Relatorio getRelatorioPecasTurno(Date data, int linha){
        return new RelatorioDAO().selectPecasProduzidasDia(new java.sql.Date(data.getTime()), linha);
    }
    
    //Retorna relatorio de pecas produzidas por mes no ano
    public List<Relatorio> getRelatorioPecasMes(int ano, int linha){
        return new RelatorioDAO().selectPecasProduzidasMes(ano, linha);
    }
    
    //Retorna relatorio de desperdicio, consumo, limite | por unidade em cada turno no dia
    public Relatorio getRelatorioConsumoDesperdicioUnidadeTurno(Date data, int linha){
        return new RelatorioDAO().selectConsumoLimiteUnidadeTurno(new java.sql.Date(data.getTime()), linha);
    }
    
    //Retorna relatorio de porcentagem de despercio
    public Relatorio getRelatorioPercDespercio(Date data, int linha){
        return new RelatorioDAO().selectPercDesperdicio(new java.sql.Date(data.getTime()), linha);
    }
    
}
