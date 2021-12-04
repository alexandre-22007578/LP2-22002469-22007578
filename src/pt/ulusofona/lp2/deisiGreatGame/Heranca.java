package pt.ulusofona.lp2.deisiGreatGame;

public class Heranca extends AbismoOrFerramenta{// Evita os efeitos de: duplicação de código
    public Heranca(int id) {
        super(id);
        titulo="Herança";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        return "Caiu numa Herança, esta ferramenta foi adicionada ao seu conjunto de ferramentas";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        return 0;
    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return nomeAbismo.equals("Duplicated Code");
    }
}
