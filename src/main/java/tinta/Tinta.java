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
    private String cor;
    private String textura;
    private float volume;
    
    //Construtor da classe
    public Tinta(String cor, String textura, float volume) {
        this.cor = cor;
        this.textura = textura;
        this.volume = volume;
    }

    public String getCor() {
        return cor;
    }

    public String getTextura() {
        return textura;
    }

    public float getVolume() {
        return volume;
    }
    
}
