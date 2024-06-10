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

    Date data;
    int linhaProducao;
    int turno
    Funcionario funcionario;
    Peca peca;
    Tinta tinta;
    float consumoTintaUnidade;
    float limiteConsumoTinta;

    public Pintura(Date data, int linhaProducao, Funcionario funcionario, Peca peca, Tinta tinta, float consumoTintaUnidade, float limiteConsumoTinta) {
        this.data = data;
        this.linhaProducao = linhaProducao;
        this.funcionario = funcionario;
        this.peca = peca;
        this.tinta = tinta;
        this.consumoTintaUnidade = consumoTintaUnidade;
        this.limiteConsumoTinta = limiteConsumoTinta;
    }
    
    

}
