/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Juan
 */
public class SuperTrunfoDaReciclagem {

    public static void main(String[] args){
      String nomeJogador1, nomeJogador2;
      int jogadorComeca, tipoJogo;
      
      Scanner sc = new Scanner(System.in);
      
      Jogador[] vetorDeJogadores = new Jogador[2];
      Jogador jogador;
      
      System.out.println("Bem vindo ao Super Trunfo da Reciclagem - GAME!");
      System.out.println("Vamos comecar!");
      System.out.println("Qual o nome do jogador 1?");
      nomeJogador1 = sc.nextLine();
      System.out.println("Qual o nome do jogador 2?");
      nomeJogador2 = sc.nextLine();
      
      jogador = new Jogador(nomeJogador1);
      vetorDeJogadores[0] = jogador;
      jogador = new Jogador(nomeJogador2);
      vetorDeJogadores[1] = jogador;
      
      // determinar quem começa
      Random determina = new Random();
      jogadorComeca = determina.nextInt(2);
      
      System.out.println("Qual sera o tipo de jogo?");
      System.out.println("1 - Simular");
      System.out.println("2 - Jogar");
      tipoJogo = sc.nextInt();
      
      switch(tipoJogo){
          case 1 -> jogarNoAleatorio(vetorDeJogadores, jogadorComeca); //simula o jogo
              
          case 2 -> jogar(vetorDeJogadores, jogadorComeca); //joga o jogo
      }
      
    }
  
    public static int menuDoJogo(){ //jogador escolhe o atributo
        int opcao = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("---------------------------------");
            System.out.println("Escolha:");
            System.out.println("1 - Tipo");
            System.out.println("2 - Decomposicao");
            System.out.println("3 - reciclavel");
            System.out.println("4 - ataque");
            System.out.println("---------------------------------");
            opcao = sc.nextInt();
        } while (opcao < 1 && opcao > 4);
        return opcao;
    }
    
    public static void jogar(Jogador[] vetorJogadoresFunc, int QuemComeca){
        int opcao, VezDeJogar, indiGanhador = 0, turno = 0, deuEmpate = 0;
        String nomeProvisorio;
        Carta cartaJ1, cartaJ2, cartaProvisoria;
        List<Carta> cartasEmpatadas;
        
        cartasEmpatadas = new ArrayList<>();
        
        VezDeJogar = QuemComeca;
        distribuirCartas(vetorJogadoresFunc);
        
        while((vetorJogadoresFunc[0].NumeroDeCartas()!= 0) || (vetorJogadoresFunc[1].NumeroDeCartas()!= 0)){
           if(VezDeJogar == 0){
              nomeProvisorio = vetorJogadoresFunc[0].getNome();
              System.out.println("A vez eh do jogador 1, cujo nome eh " + nomeProvisorio);
              cartaJ1 = vetorJogadoresFunc[0].retornaCartaDaVez();
              cartaJ2 = vetorJogadoresFunc[1].retornaCartaDaVez();
              System.out.println("Segue os dados da sua carta do topo:");
              System.out.println(cartaJ1);

              opcao = menuDoJogo();
              
              System.out.println("Carta do Jogador 2, id: " + vetorJogadoresFunc[1].getNome());
              System.out.println(cartaJ2);
              System.out.println("-----------------------");
              switch(opcao){
                  case 1: 
                      indiGanhador = cartaJ1.comparaCor(cartaJ2);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 2: 
                      indiGanhador = cartaJ1.comparaDecomposicao(cartaJ2);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 3: 
                      indiGanhador = cartaJ1.comparaReciclavel(cartaJ2);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                          
                      } 
                      else if(indiGanhador == 1){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 4: 
                      indiGanhador = cartaJ1.comparaAtaque(cartaJ2);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    break;
              }
              
              
        } else if(VezDeJogar == 1){
              nomeProvisorio = vetorJogadoresFunc[1].getNome();
              System.out.println("A vez eh do jogador 2, cujo nome eh " + nomeProvisorio);
              cartaJ1 = vetorJogadoresFunc[0].retornaCartaDaVez();
              cartaJ2 = vetorJogadoresFunc[1].retornaCartaDaVez();
              System.out.println("Segue os dados da sua carta do topo:");
              System.out.println(cartaJ2);
              opcao = menuDoJogo();
              
              System.out.println("Carta do Jogador 1, id: " + vetorJogadoresFunc[0].getNome());
              System.out.println(cartaJ1);
              System.out.println("-----------------------");
              switch(opcao){
                  case 1: 
                      indiGanhador = cartaJ2.comparaCor(cartaJ1);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 2: 
                      indiGanhador = cartaJ2.comparaDecomposicao(cartaJ1);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          } 
                      } 
                      else if(indiGanhador == 1){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 3: 
                      indiGanhador = cartaJ2.comparaReciclavel(cartaJ1);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 4: 
                      indiGanhador = cartaJ2.comparaAtaque(cartaJ1);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                      break;
              }  
        }
                    if(vetorJogadoresFunc[0].NumeroDeCartas() == 0 && deuEmpate == 0){
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("|" + "          Quem comecou o jogo foi o Jogador " + (QuemComeca+1) + "!                   |");
                        System.out.println("|" + "  Quem ganhou foi o jogador de id: " + vetorJogadoresFunc[1].getNome() + " em " + turno + " turnos! Parabéns :)" + "  |");
                        System.out.println("------------------------------------------------------------------");
                        System.exit(0);
                    } else if(vetorJogadoresFunc[1].NumeroDeCartas() == 0 && deuEmpate == 0) {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("|" + "          Quem comecou o jogo foi o Jogador " + (QuemComeca+1) + "!                   |");
                        System.out.println("|" + "  Quem ganhou foi o jogador de id: " + vetorJogadoresFunc[0].getNome() + " em " + turno + " turnos! Parabéns :)" + "  |");
                        System.out.println("------------------------------------------------------------------");
                        System.exit(0);
                    }         
    }     
 }
    private static void distribuirCartas(Jogador[] vetorJogadoresFunc) {
        Baralho baralho = new Baralho();
        Carta carta;
        System.out.println("O baralho tem: "+ baralho.TamanhoDoBaralho());
        do {
            carta = baralho.selecionaCarta();
            if (carta != null)
                vetorJogadoresFunc[0].incluirCarta(carta);
            carta = baralho.selecionaCarta();
            if (carta != null)
                vetorJogadoresFunc[1].incluirCarta(carta);
        } while (carta != null);
        System.out.println("O baralho tem após distribuir: "+ baralho.TamanhoDoBaralho());
    }

    public static void jogarNoAleatorio(Jogador[] vetorJogadoresFunc, int QuemComeca) {
        int VezDeJogar, indiGanhador = 0, turno = 0, deuEmpate = 0, opcao;
        String nomeProvisorio;
        Carta cartaJ1, cartaJ2, cartaProvisoria;
        List<Carta> cartasEmpatadas;
        Random qualAtributo = new Random();
        
        cartasEmpatadas = new ArrayList<>();
        
        VezDeJogar = QuemComeca;
        distribuirCartas(vetorJogadoresFunc);
        
        while((vetorJogadoresFunc[0].NumeroDeCartas()!= 0) || (vetorJogadoresFunc[1].NumeroDeCartas()!= 0)){
            if(VezDeJogar == 0){
              nomeProvisorio = vetorJogadoresFunc[0].getNome();
              System.out.println("A vez eh do jogador 1, cujo nome eh " + nomeProvisorio);
              cartaJ1 = vetorJogadoresFunc[0].retornaCartaDaVez();
              cartaJ2 = vetorJogadoresFunc[1].retornaCartaDaVez();
              System.out.println("Segue os dados da sua carta do topo:");
              System.out.println(cartaJ1);
              
              System.out.println("---------------------------------");
              System.out.println("Escolha de Atributos:");
              System.out.println("1 - Tipo");
              System.out.println("2 - Decomposicao");
              System.out.println("3 - reciclavel");
              System.out.println("4 - ataque");
              System.out.println("---------------------------------");

              opcao = qualAtributo.nextInt(4);
              opcao++;
              
              System.out.println("Escolha aleatoria foi na : " + opcao);
              
              System.out.println("Carta do Jogador 2, id: " + vetorJogadoresFunc[1].getNome());
              System.out.println(cartaJ2);
              System.out.println("-----------------------");
              switch(opcao){
                  case 1: 
                      indiGanhador = cartaJ1.comparaCor(cartaJ2);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                          
                      } 
                      else if(indiGanhador == 1){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
           if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 2: 
                      indiGanhador = cartaJ1.comparaDecomposicao(cartaJ2);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 3: 
                      indiGanhador = cartaJ1.comparaReciclavel(cartaJ2);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                          
                      } 
                      else if(indiGanhador == 1){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 4: 
                      indiGanhador = cartaJ1.comparaAtaque(cartaJ2);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    break;
              }
              
              
        } else if(VezDeJogar == 1){
              nomeProvisorio = vetorJogadoresFunc[1].getNome();
              System.out.println("A vez eh do jogador 2, cujo nome eh " + nomeProvisorio);
              cartaJ1 = vetorJogadoresFunc[0].retornaCartaDaVez();
              cartaJ2 = vetorJogadoresFunc[1].retornaCartaDaVez();
              System.out.println("Segue os dados da sua carta do topo:");
              System.out.println(cartaJ2);
              
              System.out.println("---------------------------------");
              System.out.println("Escolha de Atributos:");
              System.out.println("1 - Tipo");
              System.out.println("2 - Decomposicao");
              System.out.println("3 - reciclavel");
              System.out.println("4 - ataque");
              System.out.println("---------------------------------");

              opcao = qualAtributo.nextInt(4);
              opcao++;
              
              System.out.println("Escolha aleatoria foi na : " + opcao);
              
              System.out.println("Carta do Jogador 1, id: " + vetorJogadoresFunc[0].getNome());
              System.out.println(cartaJ1);
              System.out.println("-----------------------");
              
              switch(opcao){
                  case 1: 
                      indiGanhador = cartaJ2.comparaCor(cartaJ1);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 2: 
                      indiGanhador = cartaJ2.comparaDecomposicao(cartaJ1);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          } 
                      } 
                      else if(indiGanhador == 1){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 3: 
                      indiGanhador = cartaJ2.comparaReciclavel(cartaJ1);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                  break;
                  case 4: 
                      indiGanhador = cartaJ2.comparaAtaque(cartaJ1);
                      if(indiGanhador == 2){ //Empate no Atributo
                          System.out.println("Empatou! :/");
                          System.out.println("A proxima rodada vale as cartas empatadas tambem!");
                          
                          deuEmpate = 1;
                          
                          cartasEmpatadas.add(cartaJ1);
                          cartasEmpatadas.add(cartaJ2);
                          
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                      } 
                      else if(indiGanhador == 0){ //Jogador 2 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[1].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          VezDeJogar = 1;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[1].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      } 
                      else if(indiGanhador == 1){ //Jogador 1 ganhou
                          System.out.println("O/A " + vetorJogadoresFunc[0].getNome() + " ganhou o turno!");
                          vetorJogadoresFunc[0].removeCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ1);
                          vetorJogadoresFunc[0].incluirCarta(cartaJ2);
                          vetorJogadoresFunc[1].removeCarta(cartaJ2);
                          VezDeJogar = 0;
                          
                          if(deuEmpate == 1){
                                // carta para fazer a passagem pro vencedor
                                for(int i = 0; i < cartasEmpatadas.size(); i++){
                                  cartaProvisoria = cartasEmpatadas.get(i);
                                  vetorJogadoresFunc[0].incluirCarta(cartaProvisoria);
                                }
                                cartasEmpatadas.clear();
                                deuEmpate = 0;   
                          }
                      }
if((vetorJogadoresFunc[0].NumeroDeCartas()== 0 && deuEmpate == 1) || (vetorJogadoresFunc[1].NumeroDeCartas()== 0 && deuEmpate == 1)){
                for(int i = 0; i < cartasEmpatadas.size()-1; i++){
                   vetorJogadoresFunc[0].incluirCarta(cartasEmpatadas.get(i));
                   vetorJogadoresFunc[1].incluirCarta(cartasEmpatadas.get(i+1));
                }
                cartasEmpatadas.clear();
                deuEmpate = 0;         
           }
                      turno++;
                      System.out.println("Cartas jogador 1: " + vetorJogadoresFunc[0].NumeroDeCartas());
                      System.out.println("Cartas jogador 2: " + vetorJogadoresFunc[1].NumeroDeCartas());
                      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                      break;
              }  
        }
                    if(vetorJogadoresFunc[0].NumeroDeCartas() == 0 && deuEmpate == 0){
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("|" + "          Quem comecou o jogo foi o Jogador " + (QuemComeca+1) + "!                   |");
                        System.out.println("|" + "  Quem ganhou foi o jogador de id: " + vetorJogadoresFunc[1].getNome() + " em " + turno + " turnos! Parabéns :)" + "  |");
                        System.out.println("------------------------------------------------------------------");
                        System.exit(0);
                    } else if(vetorJogadoresFunc[1].NumeroDeCartas() == 0 && deuEmpate == 0) {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("|" + "          Quem comecou o jogo foi o Jogador " + (QuemComeca+1) + "!                   |");
                        System.out.println("|" + "  Quem ganhou foi o jogador de id: " + vetorJogadoresFunc[0].getNome() + " em " + turno + " turnos! Parabéns :)" + "  |");
                        System.out.println("------------------------------------------------------------------");
                        System.exit(0);
                    }         
        }
    }
}

  