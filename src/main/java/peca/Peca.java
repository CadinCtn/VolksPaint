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
    private int id;
    private String modelo;
    private float area_pintura;
    private int qtd_estoque;
    
    public Peca(String modelo, float area_pintura, int qtd_estoque, int id) {
        this.modelo = modelo;
        this.area_pintura = area_pintura;
        this.qtd_estoque = qtd_estoque;
        this.id = id;
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
       
    public Object[] pecaInTable() {
        Object[] row = {id, modelo, area_pintura, qtd_estoque};
        return row;
    }
    
}

