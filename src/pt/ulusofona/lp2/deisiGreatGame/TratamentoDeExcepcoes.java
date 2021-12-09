package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

public class TratamentoDeExcepcoes extends AbismoOrFerramenta{// Evita os efeitos de:Exception,File Not Found Exception
    public TratamentoDeExcepcoes(int id) {
        super(id);
        titulo="Tratamento de Excepções";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        return "Caiu no Tratamento de Excepções, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
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
        return nomeAbismo.equals("Exception")||nomeAbismo.equals("File Not Found Exception");
    }
}
