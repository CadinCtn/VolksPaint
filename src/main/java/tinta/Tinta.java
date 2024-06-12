/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinta;

/**
 *
 * @author Lenovo
 */
public class Tinta {

    //Atributos
    private int id;
    private String cor;
    private String textura;
    private float qtd_estoque;

    //Construtor da classe
    public Tinta(int id, String cor, String textura, float qtd_estoque) {
        this.id = id;
        this.cor = cor;
        this.textura = textura;
        this.qtd_estoque = qtd_estoque;
    }

    public int getId() {
        return id;
    }

    public String getCor() {
        
        return cor;
    }

    public String getTextura() {
        return textura;
    }

    public float getQtdEstoque() {
        return qtd_estoque;
    }

    public Object[] tintaInTable() {
        Object[] row = {id, cor, textura, qtd_estoque};
        return row;
    }

}
