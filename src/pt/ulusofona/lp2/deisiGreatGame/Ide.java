package pt.ulusofona.lp2.deisiGreatGame;

public class Ide extends AbismoOrFerramenta{// Evita os efeitos de:Erro de Sintaxe
    public Ide(int id) {
        super(id);
        titulo="IDE";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        return "Caiu no IDE, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        return 0;
    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return nomeAbismo.equals("Erro de sintaxe");
    }
}
