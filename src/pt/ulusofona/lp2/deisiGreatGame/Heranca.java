package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class Heranca extends AbismoOrFerramenta {// Evita os efeitos de: duplicação de código

    public Heranca(int id) {
        super(id);
        titulo = "Herança";
    }


    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        return "Caiu numa Herança, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
    }

    @Override
    public String move(int dado, Programmer player, int tamanhoTabuleiro, boolean levouCounter, ArrayList<Programmer> players) {
        return reactToAbyssOrTool(levouCounter);
    }


    @Override
    public boolean souFerramenta() {
        return true;
    }


    @Override
    public boolean daCounter(String nomeAbismo) {
        return nomeAbismo.equals("Duplicated Code");
    }
}
