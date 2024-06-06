/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

import java.sql.Date;

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
        
        //Consumo tinta por unidade
        float[] consumoUnidades = new float[3];
        
        //Limite de consumo por unidade
        float[] limiteConsumoUnidade = new float[3];
        
        //Desperdício de tinta por unidade
        float[] desperdicioTinta = new float[3];

        
        public Date getData() {
            return data;
        }

        public int getLinhaProducao() {
            return linhaProducao;
        }
        
        
        //Relatorio de Consumo tinta
        public Relatorio(Date data, int linhaProducao, float consumoTinta1, float consumoTinta2, float consumoTinta3){
            this.data = data;
            this.linhaProducao = linhaProducao;
            
            this.consumoTintas[0] = consumoTinta1;
            this.consumoTintas[1] = consumoTinta2;
            this.consumoTintas[2] = consumoTinta3;
        }
        
        
        //Relatorio de Pecas Produzidas
        public Relatorio(Date data, int linhaProducao, int qtdPecas1, int qtdPecas2, int qtdPecas3){
            this.data = data;
            this.linhaProducao = linhaProducao;
            
            this.qtdPecas[0] = qtdPecas1;
            this.qtdPecas[1] = qtdPecas2;
            this.qtdPecas[2] = qtdPecas3;
        }
        
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
        
        
        //Get
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
        
        
}
