/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DateFormatSymbols;

/**
 *
 * @author Lenovo
 */
public class Relatorio {
    
    //Index
    Date data;
    int linhaProducao;
    //valores
        //Consumo Tinta por turno
        float[] consumoTintas = new float[3];
        
        //Pecas produzidas
        int[] qtdPecas = new int[3];
        int mes = 0; // mes em que a quantidade de pecas foi produzida
        
        //Consumo tinta por unidade
        float[] consumoUnidades = new float[3];
        
        //Limite de consumo por unidade
        float[] limiteConsumoUnidade = new float[3];
        
        //Desperdício de tinta por unidade
        float[] desperdicioTinta = new float[3];

        ////////////////////////////
        //Relatorio Geral
        public Relatorio(){}
        
        
        ////////////////////
        //Relatorio de Consumo tinta
        public Relatorio(Date data, int linhaProducao, float consumoTinta1, float consumoTinta2, float consumoTinta3){
            this.data = data;
            this.linhaProducao = linhaProducao;
            
            this.consumoTintas[0] = consumoTinta1;
            this.consumoTintas[1] = consumoTinta2;
            this.consumoTintas[2] = consumoTinta3;
        }
        
        ////////////////// 
        //
        //Relatorio de Pecas Produzidas
        public Relatorio(int mes, int linhaProducao, int qtdPecas1, int qtdPecas2, int qtdPecas3){
            this.mes = mes;
            this.linhaProducao = linhaProducao;
            
            this.qtdPecas[0] = qtdPecas1;
            this.qtdPecas[1] = qtdPecas2;
            this.qtdPecas[2] = qtdPecas3;
        }
        
        //Convertendo numero int(Mes) em String(Mes)
        public String toMonth(){
            String[] months = new DateFormatSymbols().getMonths();
            return months[mes - 1];
        }
        //////////////////
        //
        //Relatorio de Consumo de Unidade por dia
        public Relatorio(float consUnidade1, float consUnidade2, float consUnidade3, 
                         float limite1, float limite2, float limite3){
            
            //Consumo por unidade
            this.consumoUnidades[0] = consUnidade1;
            this.consumoUnidades[1] = consUnidade2;
            this.consumoUnidades[2] = consUnidade3;
            
            //Limite de consumo por unidade
            this.limiteConsumoUnidade[0] = limite1;
            this.limiteConsumoUnidade[1] = limite2;
            this.limiteConsumoUnidade[2] = limite3;
            
            
        }
        ///////////////////
        //
        //Relatorio de Porcentagem de despercio por turno
        public Relatorio(float perDesperdicio1, float perDesperdicio2, float perDesperdicio3){
            //Porcentagem de desperdicio em cima do limite
            this.desperdicioTinta[0] = new BigDecimal(perDesperdicio1).setScale(2, RoundingMode.HALF_UP).floatValue();
            this.desperdicioTinta[1] = new BigDecimal(perDesperdicio2).setScale(2, RoundingMode.HALF_UP).floatValue();
            this.desperdicioTinta[2] = new BigDecimal(perDesperdicio3).setScale(2, RoundingMode.HALF_UP).floatValue();
        }
        //////////////////
        //
        //Relatorio de desperdicio mensal por ano
        public Relatorio(int mes, float desperdicio1, float desperdicio2, float desperdicio3){
            this.mes = mes;
            //valores de desperdicio
            this.desperdicioTinta[0] = desperdicio1;
            this.desperdicioTinta[1] = desperdicio2;
            this.desperdicioTinta[2] = desperdicio3;
        }
        ////////////////
        
        //Set
        public void setConsumoTinta(float valor, int turno){
            consumoTintas[turno-1] = valor;
        }
        
        public void setQtdPecas(int valor, int turno){
            qtdPecas[turno-1] = valor;
        }
        
        public void setLimiteConsumo(float valor, int turno){
            limiteConsumoUnidade[turno-1] = valor;
        }
        
        public void setDesperdicioTinta(float valor, int turno){
            desperdicioTinta[turno-1] = valor;
        }
        
        public void setConsumoUnidade(float valor, int turno){
            consumoUnidades[turno-1] = valor;
        }
        
        
        //Get
        public Date getData() {
            return data;
        }
        
        public float getConsumoTinta(int turno){
            return consumoTintas[turno -1];
        }
        
        public int getQtdPecas(int turno) {
            return qtdPecas[turno-1];
        }

        public float getConsumoUnidades(int turno) {
            return consumoUnidades[turno-1];
        }

        public float getLimiteConsumoUnidade(int turno) {
            return limiteConsumoUnidade[turno -1];
        }

        public float getDesperdicioTinta(int turno) {
            return desperdicioTinta[turno-1];
        }
        
        
        //Get Total
        public float getTotalConsumoTinta(){
            return consumoTintas[0] + consumoTintas[1] + consumoTintas[2];
        }
        
        public int getTotalQtdPecas(){
            return qtdPecas[0] + qtdPecas[1] + qtdPecas[2];
        }
        
        public float getTotalDesperdicioTinta(){
            return desperdicioTinta[0] + desperdicioTinta[1] + desperdicioTinta[2];
        }
        
        public float getTotalLimiteConsumo(){
            return limiteConsumoUnidade[0] + limiteConsumoUnidade[1] + limiteConsumoUnidade[2];
        }
        
        public float getTotalConsumoUnidade(){
            return consumoUnidades[0] + consumoUnidades[1] + consumoUnidades[2];
        }
        
}
