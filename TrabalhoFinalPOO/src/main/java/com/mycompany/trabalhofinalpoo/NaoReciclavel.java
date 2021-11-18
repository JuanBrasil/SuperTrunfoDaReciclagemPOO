/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo;

/**
 *
 * @author Juan
 */
public class NaoReciclavel extends Carta{
    public NaoReciclavel(char[] codigo, String nome, String descricao, 
            String tipo, Cor cor, double decomposicao, int ataque){
            super(codigo, nome, descricao, cor, tipo, decomposicao, ataque);
    }
    
    public boolean ehReciclavel(){
        return false;
    }
    
    public String toString(){
        return super.toString() + "\nNao Reciclavel";
    }
}
