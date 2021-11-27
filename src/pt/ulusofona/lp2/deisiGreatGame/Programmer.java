package pt.ulusofona.lp2.deisiGreatGame;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Programmer {
    int id;
    String nome;
    ProgrammerColor cor;
    String estado ="Em Jogo";
    int posicao=1;
    String linguegnsFavoritas;

    public Programmer(int id, String nome, ProgrammerColor cor, String linguegnsFavoritas) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.linguegnsFavoritas = linguegnsFavoritas;
    }

    public int getId(){
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getName(){
        return nome;
    }

    public int getPosicao(){
        return posicao;
    }

    void aumentaPosicao(int posicao){

        this.posicao+=posicao;
    }

    void diminuiPosicao(int posicao,int tamanhoTabuleiro){

        int posicaoSuposta = this.posicao + posicao;
        int numeroDeCasaQueAndaParaTras=posicaoSuposta-tamanhoTabuleiro;
        this.posicao=tamanhoTabuleiro-numeroDeCasaQueAndaParaTras;
    }

    public ProgrammerColor getColor(){
        return cor;
    }

    @Override
    public String toString()
    {
        ArrayList<String> linguagensList= new ArrayList<>(Arrays.asList(linguegnsFavoritas.split(";")));
        linguagensList.sort(String::compareTo);
        StringBuilder linguagensString = new StringBuilder();
        linguagensString.append(linguagensList.get(0));
        for (int i = 1; i < linguagensList.size(); i++){
            linguagensString.append("; ").append(linguagensList.get(i));
        }

        return id + " | " + nome + " | " + posicao + " | " + linguagensString + " | " + estado;
    }
}
