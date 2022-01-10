package pt.ulusofona.lp2.deisiGreatGame;


import java.util.ArrayList;
import java.util.Arrays;


public class Programmer {
    private int id;
    private String nome;
    private ProgrammerColor cor;
    private String estado = "Em Jogo";
    private int posicao = 1;
    private ArrayList<AbismoOrFerramenta> ferramentas = new ArrayList<>();
    private boolean stuck = false;
    private int posicaoAnterior = 1;
    private int posicao2Anterior = 1;
    private ArrayList<String> linguagensList;

    public String getNome() {
        return nome;
    }

    public ArrayList<String> getLinguagensList() {
        return linguagensList;
    }


    public Programmer(int id, String nome, ProgrammerColor cor, String estado, int posicao, String[] ferramentas, boolean stuck, int posicaoAnterior, int posicao2Anterior, String linguagensFavoritas) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.estado = estado;
        this.posicao = posicao;
        this.stuck = stuck;
        this.posicaoAnterior = posicaoAnterior;
        this.posicao2Anterior = posicao2Anterior;
        this.linguagensList = new ArrayList<>(Arrays.asList(linguagensFavoritas.split(";")));
        int idFerramentas;
        for (String ferramenta : ferramentas) {
            if (ferramenta.equals("NOTOOLS")){
                break;
            }
            idFerramentas = Integer.parseInt(ferramenta);
            switch (idFerramentas) {
                case 0 -> this.ferramentas.add(new Heranca(idFerramentas));
                case 1 -> this.ferramentas.add(new ProgramacaoFuncional(idFerramentas));
                case 2 -> this.ferramentas.add(new TstUnitarios(idFerramentas));
                case 3 -> this.ferramentas.add(new TratamentoDeExcepcoes(idFerramentas));
                case 4 -> this.ferramentas.add(new Ide(idFerramentas));
                case 5 -> this.ferramentas.add(new AjudaDoProfessor(idFerramentas));

            }
        }


    }

    public Programmer(int id, String nome, ProgrammerColor cor, String linguagensFavoritas) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.linguagensList = new ArrayList<>(Arrays.asList(linguagensFavoritas.split(";")));
    }

    public ArrayList<AbismoOrFerramenta> getFerramentas() {
        return ferramentas;
    }

    public void mudarEstadoPreso() {
        this.stuck = true;
    }

    public void mudarEstadoLivre() {
        this.stuck = false;
    }

    public void perdeu() {
        this.estado = "Derrotado";
    }

    public int getPosicaoAnterior() {
        return posicaoAnterior;
    }

    public int getPosicao2Anterior() {
        return posicao2Anterior;
    }

    public boolean getStuck() {
        return stuck;
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

    public ProgrammerColor getColor() {
        return cor;
    }

    public String getProgrammersInfo() {

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

    public boolean adicionaFerramenta(AbismoOrFerramenta ferramenta) {

        if (!ferramenta.souFerramenta()) {
            return false;
        }

        for (AbismoOrFerramenta value : ferramentas) {
            if (value.getTitulo().equals(ferramenta.getTitulo())) {
                return false;
            }
        }

        ferramentas.add(ferramenta);
        return true;

    }

    public void retiraFerramenta(AbismoOrFerramenta ferramenta) {

        for (AbismoOrFerramenta value : ferramentas) {
            if (value.getTitulo().equals(ferramenta.getTitulo())) {
                ferramentas.remove(value);
                break;
            }
        }

    }

    public void mover(int dado, int tamanhoTabuleiro) {
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


    @Override
    public String toString() {
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
