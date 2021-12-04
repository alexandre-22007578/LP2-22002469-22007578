package pt.ulusofona.lp2.deisiGreatGame;

public class CrashAkaRebentanco extends AbismoOrFerramenta{// O programador volta à primeira casa do jogo.
    public CrashAkaRebentanco(int id) {
        super(id);
        titulo="Crash (aka Rebentanço)";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {
        if (levouCounter){
            return "Caiu num Crash (aka Rebentanço), mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Crash (aka Rebentanço), irá voltar ao inicio do tabuleiro";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        if (levouCounter){
            return 0;
        }
        return posicaoAtual;
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
