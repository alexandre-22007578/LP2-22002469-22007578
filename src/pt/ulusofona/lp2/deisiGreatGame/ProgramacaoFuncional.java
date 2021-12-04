package pt.ulusofona.lp2.deisiGreatGame;

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
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        return 0;
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
