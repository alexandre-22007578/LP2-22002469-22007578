package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class Programmer {
    int id;
    String nome;
    ProgrammerColor cor;
    String estado ="Em Jogo";
    int posicao;
    ArrayList<String> linguegnsFavoritas;

    public Programmer(int id, String nome, ProgrammerColor cor, ArrayList<String> linguegnsFavoritas) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.linguegnsFavoritas = linguegnsFavoritas;
    }

    int getId(){
        return id;
    }
    String getName(){
        return nome;
    }

    ProgrammerColor getColor(){
        return cor;
    }

    @Override
    public String toString() {
        return "";
    }
}
