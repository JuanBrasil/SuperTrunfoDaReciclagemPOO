/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo;

/**
 *
 * @author Juan
 */
public abstract class Carta {
    private char[] codigo;
    private String nome;
    private String descricao;
    private String tipo;
    private Cor cor;
    private double decomposicao;
    private int ataque;
    
    
    public abstract boolean ehReciclavel();
    
    Carta (){}
    
    Carta (char[] codigo, String nome, String descricao, 
            Cor cor, String tipo, double decomposicao, int ataque){
        
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.cor = cor;
        this.tipo = tipo;
        this.decomposicao = decomposicao;
        this.ataque = ataque;
    }
    
    public String toString(){
        return "\n-----------------------------" +
                "\nNome: " + nome + "(" + codigo[0] + codigo[1] + ")" +
                "\nCor: " + cor +
                "\nTipo: " + tipo +
                "\nDescricao: " + descricao +
                "\nDecomposicao: " + decomposicao + " anos" +
                "\nAtaque: " + ataque;
    }
    
    // menor decomp ganha
    public int comparaDecomposicao(Carta cartaJogador2){
        if(this.decomposicao == cartaJogador2.decomposicao){
            return 2;
        } else if(this.decomposicao < cartaJogador2.decomposicao){
            return 0;
        } else {
            return 1;
        }
    }
    // maior ataque ganha
    public int comparaAtaque(Carta cartaJogador2){
        if(this.ataque == cartaJogador2.ataque){
            return 2;
        } else if(this.ataque > cartaJogador2.ataque){
            return 0;
        }
            return 1;
    }
    
    public int comparaReciclavel(Carta carta){ //reciclavel ganha de nao reciclavel
        if(this.ehReciclavel() == carta.ehReciclavel()){ //ambos reciclaveis (draw)
            return 2;
        } else if(this.ehReciclavel() == true  && carta.ehReciclavel() == false){ //primeiro reciclavel e o segundo nao
            return 0;
        } else { //primeiro nao reciclavel e o segundo reciclavel
            return 1;
        }
    }
    
    public int comparaCor(Carta carta){
        if(this.cor.equals(carta.cor)){
            return 2;
        }
        if(this.cor == Cor.VERMELHO && (carta.cor == Cor.AMARELO) ||
               (carta.cor == Cor.VERDE) ||
               (carta.cor == Cor.MARROM) ||
               (carta.cor == Cor.CINZA) ||
               (carta.cor == Cor.PRETO)){
            return 0;
        }
        if(this.cor == Cor.VERDE && (carta.cor == Cor.MARROM) ||
               (carta.cor == Cor.CINZA) ||
               (carta.cor == Cor.PRETO) ||
               (carta.cor == Cor.BRANCO) ||
               (carta.cor == Cor.LARANJA)){
            return 0;
        }
        if(this.cor == Cor.AZUL && (carta.cor == Cor.VERMELHO) ||
               (carta.cor == Cor.AMARELO) ||
               (carta.cor == Cor.VERDE) ||
               (carta.cor == Cor.MARROM) ||
               (carta.cor == Cor.CINZA)){
            return 0;
        }
        if(this.cor == Cor.CINZA && (carta.cor == Cor.PRETO) ||
                (carta.cor == Cor.BRANCO) ||
                (carta.cor == Cor.LARANJA) ||
                (carta.cor == Cor.ROXO) ||
                (carta.cor == Cor.AZUL)){
            return 0;
        }
        if(this.cor == Cor.AMARELO && (carta.cor == Cor.VERDE) ||
                (carta.cor == Cor.MARROM) ||
                (carta.cor == Cor.CINZA) ||
                (carta.cor == Cor.PRETO) ||
                (carta.cor == Cor.BRANCO)){
            return 0;
        }
        if(this.cor == Cor.LARANJA && (carta.cor == Cor.ROXO) ||
                (carta.cor == Cor.AZUL) ||
                (carta.cor == Cor.VERMELHO) ||
                (carta.cor == Cor.AMARELO) ||
                (carta.cor == Cor.VERDE)){
            return 0;
        }
        if(this.cor == Cor.MARROM && (carta.cor == Cor.CINZA) ||
                (carta.cor == Cor.PRETO) ||
                (carta.cor == Cor.BRANCO) ||
                (carta.cor == Cor.LARANJA) ||
                (carta.cor == Cor.ROXO)){
            return 0;
        }
        if(this.cor == Cor.PRETO && (carta.cor == Cor.BRANCO) ||
                (carta.cor == Cor.LARANJA) ||
                (carta.cor == Cor.ROXO) ||
                (carta.cor == Cor.AZUL) ||
                (carta.cor == Cor.VERMELHO)){
            return 0;
        }
        if(this.cor == Cor.ROXO && (carta.cor == Cor.AZUL) ||
                (carta.cor == Cor.VERMELHO) ||
                (carta.cor == Cor.AMARELO) ||
                (carta.cor == Cor.VERDE) ||
                (carta.cor == Cor.MARROM)){
            return 0;
        }
        if(this.cor == Cor.BRANCO && (carta.cor == Cor.LARANJA) ||
                (carta.cor == Cor.ROXO) ||
                (carta.cor == Cor.AZUL) ||
                (carta.cor == Cor.VERMELHO) ||
                (carta.cor == Cor.AMARELO)){
            return 0;
        }
        if(this.cor == Cor.MAIOR){
            if(carta.ehReciclavel() == true){
                return 0;
            } else if(carta.ehReciclavel() == false){
                return 1;
            }
        }    
        if(this.cor == Cor.MENOR){
            if(carta.ehReciclavel() == false){
                return 0;
            } else {
                return 1;
            }
        }
    return -1;
    }
}
