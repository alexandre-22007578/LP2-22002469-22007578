package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class ProgramacaoFuncional extends AbismoOrFerramenta { // duplicade code

    public ProgramacaoFuncional(int id) {
        super(id);
        titulo = "Programação Funcional";
    }


    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {


        return "Caiu numa Programação funcional, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
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
        return nomeAbismo.equals("Efeitos secundários") || nomeAbismo.equals("Ciclo infinito");
    }
}
