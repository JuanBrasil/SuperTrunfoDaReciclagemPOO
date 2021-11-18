/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo;

/**
 *
 * @author Juan
 */
public class Reciclavel extends Carta{
    public Reciclavel(char[] codigo, String nome, String descricao, 
            String tipo, Cor cor, double decomposicao, int ataque){
            super(codigo, nome, descricao, cor, tipo, decomposicao, ataque);
    }
    
    public boolean ehReciclavel(){
        return true;
    }
    
    public String toString(){
        return super.toString() + "\nReciclavel";
    }
}
