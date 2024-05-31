/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peca;

/**
 *
 * @author Victor
 */
public class Peca {
     //Atributos
    private String tinta_cor;
    private String modelo;
    private String area_pintura;
    private int qtd_estoque;
    private int id;
    
    public Peca(String tinta_cor, String modelo, String area_pintura, int qtd_estoque, int id) {
        this.tinta_cor = tinta_cor;
        this.modelo = modelo;
        this.area_pintura = area_pintura;
        this.id = id;
    }
       
}

