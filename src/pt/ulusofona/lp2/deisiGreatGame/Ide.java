package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class Ide extends AbismoOrFerramenta {// Evita os efeitos de:Erro de Sintaxe

    public Ide(int id) {
        super(id);
        titulo = "IDE";
    }


    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        return "Caiu no IDE, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
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
        return nomeAbismo.equals("Erro de sintaxe");
    }
}
