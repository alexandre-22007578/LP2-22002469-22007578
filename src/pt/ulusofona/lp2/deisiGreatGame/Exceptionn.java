package pt.ulusofona.lp2.deisiGreatGame;

public class Exceptionn extends AbismoOrFerramenta {// O programador recua 2 casa

    public Exceptionn(int id) {
        super(id);
        titulo = "Exception";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter) {
            return "Caiu numa Exception, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu numa Exception, irá recuar 2 casas";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        if (levouCounter) {
            return 0;
        }
        return 2;
    }

    @Override
    public boolean souFerramenta() {
        return false;
    }


    @Override
    public boolean daCounter(String nomeAbismo) {
        return false;
    }
}
