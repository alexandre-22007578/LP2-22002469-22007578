package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public abstract class AbismoOrFerramenta {
    protected int id;
    protected String titulo;


    public int getId() {
        return id;
    }

    public AbismoOrFerramenta(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }


    public abstract String reactToAbyssOrTool(boolean levouCounter);

    public abstract String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players);

    public abstract boolean souFerramenta();

    public abstract boolean daCounter(String nomeAbismo);


    @Override
    public String toString() {
        return titulo;
    }
}
