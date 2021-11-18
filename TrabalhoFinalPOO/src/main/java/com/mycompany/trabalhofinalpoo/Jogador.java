/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class Jogador {
    private String nome;
    private List<Carta> cartasDoJogador;
    
    
    Jogador(){}
    
    Jogador(String nome){
        this.nome = nome;
        cartasDoJogador = new ArrayList<>();
    }
     
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int NumeroDeCartas(){
        return cartasDoJogador.size();
    }
    
    public void incluirCarta(Carta carta){
        this.cartasDoJogador.add(carta);
    }
    
    public void removeCarta(Carta carta){
       this.cartasDoJogador.remove(carta);
    }
    public boolean temCartas(){
        return cartasDoJogador.isEmpty();
    }
    
    public Carta retornaCartaDaVez(){
        return cartasDoJogador.get(0);
    }
    
    public void imprimeCartasDoPlayer(){
      for(int i = 0; i < cartasDoJogador.size(); i++){
          System.out.println(cartasDoJogador);
      }
    }
}
