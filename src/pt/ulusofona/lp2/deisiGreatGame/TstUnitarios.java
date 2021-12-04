package pt.ulusofona.lp2.deisiGreatGame;

public class TstUnitarios extends AbismoOrFerramenta{ //  Evita os efeitos de:Erro de Lógica
    public TstUnitarios(int id) {
        super(id);
        titulo="Testes unitários";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        return "Caiu nos Testes unitários, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        return 0;
    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return nomeAbismo.equals("Erro de lógica");
    }
}
