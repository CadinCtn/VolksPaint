/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class RelatorioDAO {
    
    
    private Connection connection;
    
    public RelatorioDAO(){
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void closeConnection(){
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }    
    
    //INSERT
    public void insertRelatorio(Relatorio relatorio, int turno){
        String sql = "INSERT INTO relatorio (data_relatorio, linha_producao, turno, consumo_tinta, qtd_pecas, consumo_unidade, limite_consumo, desperdicio_tinta) VALUES (?,?,?,?,?,?,?,?) "; // Terminar a String SQL
        
        try(PreparedStatement stmt = connection.prepareStatement(sql);){
            //Chaves
            stmt.setDate(1, relatorio.data);
            stmt.setInt(2, relatorio.linhaProducao);
            stmt.setInt(3, turno);
            //Valores
            stmt.setFloat(4, relatorio.getConsumoTinta(turno));
            stmt.setInt(5, relatorio.getQtdPecas(turno));
            stmt.setFloat(6, relatorio.getConsumoUnidades(turno));
            stmt.setFloat(7, relatorio.getLimiteConsumoUnidade(turno));
            stmt.setFloat(8, relatorio.getDesperdicioTinta(turno));
            
            //INSERT
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Relatorio inserido com sucesso");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o relatório.\n" + e.getMessage(),"AVISO",JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    //DELETE
    public void deleteRelatorio(Relatorio relatorio){
        String sql = "DELETE relatorio WHERE data_relatorio = ? AND linha_producao = ?;";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, relatorio.data);
            stmt.setInt(2, relatorio.linhaProducao);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel deletar o relatorio.\n" + e.getMessage(),"AVISO",JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    

    //Select Consumo Tinta
    public List<Relatorio> selectConsumoTinta(Date dataInicio, Date dataFim, int linha){
        //Selecionando Consumo de tintas
        String sql = "SELECT data_relatorio, " +
                     "SUM(CASE WHEN turno = 1 THEN consumo_tinta ELSE 0 END) AS consumo_tinta1, " +
                     "SUM(CASE WHEN turno = 2 THEN consumo_tinta ELSE 0 END) AS consumo_tinta2, " +
                     "SUM(CASE WHEN turno = 3 THEN consumo_tinta ELSE 0 END) AS consumo_tinta3 " +
                     "FROM relatorio WHERE data_relatorio BETWEEN ? AND ? AND linha_producao = ? " +
                     "GROUP BY data_relatorio, linha_producao;";
        
        //Criando lista
        List<Relatorio> listRelatorio = new ArrayList<>();
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, dataInicio);
            stmt.setDate(2, dataFim);
            stmt.setInt(3, linha);
            
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    listRelatorio.add(new Relatorio(rs.getDate("data_relatorio"),
                                                    linha,
                                                    rs.getFloat("consumo_tinta1"),
                                                    rs.getFloat("consumo_tinta2"),
                                                    rs.getFloat("consumo_tinta3")));
                }
            }
            
        }catch(SQLException  e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return listRelatorio;
    }
    
    //Select Pecas Produzidas Por Dia
    public Relatorio selectPecasProduzidasDia(Date data, int linha){
        //Selecionando pecas produzidas
        String sql = "SELECT " +
                     "SUM(CASE WHEN turno = 1 THEN qtd_pecas ELSE 0 END) AS qtd_pecas1, " +
                     "SUM(CASE WHEN turno = 2 THEN qtd_pecas ELSE 0 END) AS qtd_pecas2, " +
                     "SUM(CASE WHEN turno = 3 THEN qtd_pecas ELSE 0 END) AS qtd_pecas3 " +
                     "FROM relatorio WHERE data_relatorio = ? AND linha_producao = ? " +
                     "GROUP BY data_relatorio, linha_producao;";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setDate(1, data);
            stmt.setInt(2, linha);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    //Retorna relatorio
                    return new Relatorio(1,  // Irrelevante
                                         linha, 
                                         rs.getInt("qtd_pecas1"),
                                         rs.getInt("qtd_pecas2"),
                                         rs.getInt("qtd_pecas3"));
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
             // Fecha conexão
             closeConnection();
        }
        
        //Relatorio vazio
        return new Relatorio(data, linha, 0, 0, 0);
    }
    
    //Select Pecas Produzidas Mensalmente
    public List<Relatorio> selectPecasProduzidasMes(int ano, int linha){
        String sql = "SELECT month(data_relatorio) as mes, " +
                     "sum(CASE WHEN turno = 1 THEN qtd_pecas ELSE 0 END) as qtd_pecas1, " +
                     "sum(CASE WHEN turno = 2 THEN qtd_pecas ELSE 0 END) as qtd_pecas2, " +
                     "sum(CASE WHEN turno = 3 THEN qtd_pecas ELSE 0 END) as qtd_pecas3 " +
                     "FROM relatorio WHERE Year(data_relatorio) = ? AND linha_producao = ? " +
                     "GROUP BY month(data_relatorio), linha_producao;";

        //Criando lista
        List<Relatorio> listRelatorio = new ArrayList<>();
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            //Setando atributos
            stmt.setInt(1, ano);
            stmt.setInt(2, linha);
            
            try(ResultSet rs = stmt.executeQuery()){
                //Percorre lista de resultados
                while(rs.next()){
                    //Adiciona a lista o relatorio
                    listRelatorio.add(new Relatorio(rs.getInt("mes"),
                                                    linha, 
                                                    rs.getInt("qtd_pecas1"),
                                                    rs.getInt("qtd_pecas2"), 
                                                    rs.getInt("qtd_pecas3")));
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            //Encerra a conexão
            closeConnection();
        }

        //Retorna lista com os resultados obtidos        
        return listRelatorio;
    }
    
    
    //Select de consumo  e limite de tinta por unidade
    public Relatorio selectConsumoLimiteUnidadeTurno(Date data, int linha){
        //String da consulta
        String sql = "SELECT " +
                     //Limite de consumo por unidade
                     "SUM(CASE WHEN turno = 1 THEN limite_consumo ELSE 0 END) AS limite1, " +
                     "SUM(CASE WHEN turno = 2 THEN limite_consumo ELSE 0 END) AS limite2, " +
                     "SUM(CASE WHEN turno = 3 THEN limite_consumo ELSE 0 END) AS limite3, " +
                     //Consumo de tinta por unidade 
                     "SUM(CASE WHEN turno = 1 THEN consumo_unidade ELSE 0 END) AS consumo_u1, " +
                     "SUM(CASE WHEN turno = 2 THEN consumo_unidade ELSE 0 END) AS consumo_u2, " +
                     "SUM(CASE WHEN turno = 3 THEN consumo_unidade ELSE 0 END) AS consumo_u3 " +
                     
                     //Condições
                     "FROM relatorio  " +
                     "WHERE data_relatorio = ? AND linha_producao = ? " +
                     "GROUP BY data_relatorio, linha_producao;";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, data);
            stmt.setInt(2, linha);
            
            try(ResultSet rs = stmt.executeQuery()){
                //Se obtiver resultado
                if(rs.next()){
                    //Retorna relatorio
                    return new Relatorio( //Consumo Unidade
                                         rs.getFloat("consumo_u1"), 
                                         rs.getFloat("consumo_u2"), 
                                         rs.getFloat("consumo_u3"), 
                            
                                         //Limite Consumo Unidade
                                         rs.getFloat("limite1"), 
                                         rs.getFloat("limite2"), 
                                         rs.getFloat("limite3") 
                                       );
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            //Fecha conexão
            closeConnection();
        }

        //Retorna relatorio vazio
        return new Relatorio(0, 0, 0, 
                             0, 0, 0);
    }
    
    
    //Select relatorio de despercio em porcentagem por turno
    public Relatorio selectPercDesperdicio(Date data, int linha){
        //String sql que retorna o calculo de porcentagem de despercicio em relação ao limite
        String sql = "Select " +
                     "(SUM(CASE WHEN turno = 1 THEN desperdicio_tinta ELSE 0 END) * 100 / " +
                     "NULLIF(SUM(CASE WHEN turno = 1 THEN limite_consumo ELSE 0 END), 0)) AS perc_desperdicio1, " +
                     //Turno 1
                     "(SUM(CASE WHEN turno = 2 THEN desperdicio_tinta ELSE 0 END) * 100 / " +
                     "NULLIF(SUM(CASE WHEN turno = 2 THEN limite_consumo ELSE 0 END), 0)) AS perc_desperdicio2, " +
                     //Turno 3
                     "(SUM(CASE WHEN turno = 3 THEN desperdicio_tinta ELSE 0 END) * 100 /  " +
                     "NULLIF(SUM(CASE WHEN turno = 3 THEN limite_consumo ELSE 0 END), 0)) AS perc_desperdicio3  " +
                     //Condições
                     "FROM relatorio  " +
                     "WHERE data_relatorio = ? AND linha_producao = ? " +
                     "GROUP BY data_relatorio, linha_producao;";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, data);
            stmt.setInt(2, linha);
            
            //Percorrendo lista de resultados
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    //Retorna relatorio de porcentagem de desperdicio
                    return new Relatorio(rs.getFloat("perc_desperdicio1"), 
                                         rs.getFloat("perc_desperdicio2"), 
                                         rs.getFloat("perc_desperdicio3"));
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //fecha a conexão
            closeConnection();
        }
        
        //retorna relatorio vazio
        return new Relatorio(0,0,0);
    }
    
    
    //Select de relatorio de desperdicio mensal por ano
    public List<Relatorio> selectRelatorioDesperdicioMensal(int ano, int linha){

        String sql = "SELECT month(data_relatorio) as mes, " +
                     "SUM(CASE WHEN turno = 1 THEN desperdicio_tinta ELSE 0 END ) as desp1, " +
                     "SUM(CASE WHEN turno = 2 THEN desperdicio_tinta ELSE 0 END ) as desp2, " +
                     "SUM(CASE WHEN turno = 3 THEN desperdicio_tinta ELSE 0 END ) as desp3 " +

                     "FROM relatorio " +
                     "WHERE year(data_relatorio) = ? AND linha_producao = ? " +
                     "group by month(data_relatorio), linha_producao;";
        
        //Criando lista
        List<Relatorio> listRelatorio = new ArrayList<>();
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            //setando atributos
            stmt.setInt(1, ano);
            stmt.setInt(2, linha);
            
            //Percorrendo lista de resultados
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    //Adiciona a lista
                    listRelatorio.add(new Relatorio(rs.getInt("mes"),
                                                    rs.getFloat("desp1"),
                                                    rs.getFloat("desp2"),
                                                    rs.getFloat("desp3")));
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //fecha a conexão
            closeConnection();
        }        
        
        return listRelatorio;
    }
    
}

 
