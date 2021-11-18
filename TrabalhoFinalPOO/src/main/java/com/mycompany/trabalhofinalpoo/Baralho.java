/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinalpoo;

import static com.mycompany.trabalhofinalpoo.Cor.stringToCor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author Juan
 */
public class Baralho {
    private List<Carta> baralho;
    private Random indice;
    
    Baralho(){
        baralho = new ArrayList<>();
        leituraDoArquivo("Super Trunfo da Reciclagem.csv");
        indice = new Random();
    }
    
    public Carta selecionaCarta(){
        Carta carta = null;
        if(!baralho.isEmpty()){
          carta = baralho.remove(indice.nextInt(baralho.size()));
        }
        return carta;
    }
    
    private final void leituraDoArquivo(String arquivo){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            do{
                linha = reader.readLine();
                if(linha != null){
                    baralho.add(criarCarta(linha));
                }
            } while (linha != null);
            reader.close();
        } catch(IOException e){
            System.err.println("Erro: Arquivo '" + arquivo + "'não encontrado");
        }
    }
    
    public String toString(){
        return baralho.toString();
    }

    private Carta criarCarta(String linha) {
        String[] proximoAtributo = linha.split(";"); //a cada ; ele separa como um atributo
        
        if(proximoAtributo[7].equals("sim")){
            return new Reciclavel(proximoAtributo[0].toCharArray(), proximoAtributo[1],
            proximoAtributo[2], proximoAtributo[3], stringToCor(proximoAtributo[4]),
            Double.parseDouble(proximoAtributo[5]), Integer.parseInt(proximoAtributo[6]));
        } 
        return new NaoReciclavel(proximoAtributo[0].toCharArray(), proximoAtributo[1],
            proximoAtributo[2], proximoAtributo[3], stringToCor(proximoAtributo[4]),
            Double.parseDouble(proximoAtributo[5]), Integer.parseInt(proximoAtributo[6]));
    }
    
    public void cartasDoBaralho(){
        for(int i = 0; i < baralho.size(); i++){
            System.out.println(baralho.toString());
        }
    }
    
    public int TamanhoDoBaralho(){
        return baralho.size();
    }
}
