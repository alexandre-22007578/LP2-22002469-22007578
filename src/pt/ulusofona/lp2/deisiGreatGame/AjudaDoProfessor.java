package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class AjudaDoProfessor extends AbismoOrFerramenta {// Evita os efeitos de:Erro de Sintaxe,Erro de Lógica, Exception, File Not Found Exception

    public AjudaDoProfessor(int id) {
        super(id);
        titulo = "Ajuda Do Professor";
    }


    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        return "Caiu na Ajuda Do Professor, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
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
        return nomeAbismo.equals("Erro de sintaxe") || nomeAbismo.equals("Erro de lógica") || nomeAbismo.equals("Exception") || nomeAbismo.equals("File Not Found Exception");
    }
}
