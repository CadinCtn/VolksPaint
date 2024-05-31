/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peca;

import tinta.Tinta;

/**
 *
 * @author Victor
 */
public class Peca {
     //Atributos
    private Tinta tinta_cor;
    private String modelo;
    private float area_pintura;
    private int qtd_estoque;
    private int id;
    
    public Peca(Tinta tinta_cor, String modelo, float area_pintura, int qtd_estoque, int id) {
        this.tinta_cor = tinta_cor;
        this.modelo = modelo;
        this.area_pintura = area_pintura;
        this.id = id;
    }

    public Tinta getTinta_cor() {
        return tinta_cor;
    }

    public String getModelo() {
        return modelo;
    }

    public float getArea_pintura() {
        return area_pintura;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public int getId() {
        return id;
    }
       
}

