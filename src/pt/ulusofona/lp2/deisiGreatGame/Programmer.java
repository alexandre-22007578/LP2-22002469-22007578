package pt.ulusofona.lp2.deisiGreatGame;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Programmer {
    private int id;
    private String nome;
    private ProgrammerColor cor;
    private String estado = "Em Jogo";
    private int posicao = 1;
    private String linguegnsFavoritas;
    private ArrayList<Ferramenta> ferramentas = new ArrayList<>();
    private String stuck = "Livre";
    private int posicaoAnterior = 1;
    private int posicao2Anterior = 1;


    public ArrayList<Ferramenta> getFerramentas() {
        return ferramentas;
    }

    public void setStuck(String stuck) {
        this.stuck = stuck;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPosicaoAnterior() {
        return posicaoAnterior;
    }

    public int getPosicao2Anterior() {
        return posicao2Anterior;
    }

    public String getStuck() {
        return stuck;
    }

    public Programmer(int id, String nome, ProgrammerColor cor, String linguegnsFavoritas) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.linguegnsFavoritas = linguegnsFavoritas;
    }

    public boolean adicionaFerramenta(Ferramenta ferramenta) {

        for (Ferramenta value : ferramentas) {
            if (value.getTitulo().equals(ferramenta.getTitulo())) {

                return false;
            }
        }

        ferramentas.add(ferramenta);
        return true;

    }

    public void retiraFerramenta(Ferramenta ferramenta) {

        for (Ferramenta value : ferramentas) {
            if (value.getTitulo().equals(ferramenta.getTitulo())) {
                ferramentas.remove(value);
                break;
            }
        }

    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getName() {
        return nome;
    }

    public int getPosicao() {
        return posicao;
    }

    void mover(int dado, int tamanhoTabuleiro) {
        posicao2Anterior = posicaoAnterior;
        posicaoAnterior = posicao;

        if ((posicao + dado) > tamanhoTabuleiro) { // vai demasiado para a frente
            posicao = tamanhoTabuleiro + (tamanhoTabuleiro - posicao - dado);
        } else if ((posicao + dado) < 1) { // vai demasiado para tras
            posicao = 1;

        } else { //normal
            posicao += dado;
        }
    }

    public String getProggramesInfo() {

        StringBuilder info = new StringBuilder(nome + " : ");
        if (ferramentas.size() == 0) {
            info.append("No tools");
        } else {
            for (int i = 0; i < ferramentas.size(); i++) {
                if (i == ferramentas.size() - 1) {
                    info.append(ferramentas.get(i));
                } else {
                    info.append(ferramentas.get(i)).append(";");
                }
            }
        }
        return info.toString();

    }

    public ProgrammerColor getColor() {
        return cor;
    }

    @Override
    public String toString()    {
        ArrayList<String> linguagensList = new ArrayList<>(Arrays.asList(linguegnsFavoritas.split(";")));
        linguagensList.sort(String::compareTo);
        StringBuilder linguagensString = new StringBuilder();
        StringBuilder ferramentasString = new StringBuilder();
        linguagensString.append(linguagensList.get(0));

        for (int i = 1; i < linguagensList.size(); i++) {
            linguagensString.append("; ").append(linguagensList.get(i));
        }

        if (ferramentas.size() == 0) {
            ferramentasString.append("No tools");
        }
        for (int i = 0; i < ferramentas.size(); i++) {
            if (i == 0) {
                ferramentasString.append(ferramentas.get(i).getTitulo());
            } else {
                ferramentasString.append(";").append(ferramentas.get(i).getTitulo());
            }
        }

        return id + " | " + nome + " | " + posicao + " | " + ferramentasString + " | " + linguagensString + " | " + estado;
    }
}
