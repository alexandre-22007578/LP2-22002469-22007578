package pt.ulusofona.lp2.deisiGreatGame;

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
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        return 0;
    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return nomeAbismo.equals("Exception")||nomeAbismo.equals("File Not Found Exception");
    }
}
