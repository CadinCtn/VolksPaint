package pintura;

import funcionario.Funcionario;
import java.sql.Date;
import peca.Peca;
import tinta.Tinta;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Senai
 */
public class Pintura {

    private int id;
    private Date data;
    private int linhaProducao;
    private int turno;
    private String funcionario;
    private int peca;
    private int tinta;
    private float consumoTintaUnidade;
    private float limiteConsumoTinta;

    public Pintura(int id, Date data, int linhaProducao, int turno, String funcionario, int peca, int tinta, float consumoTintaUnidade, float limiteConsumoTinta) {
        this.id = id;
        this.data = data;
        this.linhaProducao = linhaProducao;
        this.turno = turno;
        this.funcionario = funcionario;
        this.peca = peca;
        this.tinta = tinta;
        this.consumoTintaUnidade = consumoTintaUnidade;
        this.limiteConsumoTinta = limiteConsumoTinta;
    }
    
    public int getId(){
        return id;
    }

    public Date getData() {
        return data;
    }

    public int getLinhaProducao() {
        return linhaProducao;
    }

    public int getTurno() {
        return turno;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public int getPeca() {
        return peca;
    }

    public int getTinta() {
        return tinta;
    }

    public float getConsumoTintaUnidade() {
        return consumoTintaUnidade;
    }

    public float getLimiteConsumoTinta() {
        return limiteConsumoTinta;
    }
    
    public Object[] pinturaInTable(){
        Object[] row = {id, data, linhaProducao, turno, funcionario, peca, tinta, consumoTintaUnidade, limiteConsumoTinta};
        return row;
    }
    
    
    
    

}
