package pt.ulusofona.lp2.deisiGreatGame;

public class BlueScreeOfDeath extends AbismoOrFerramenta {//O programador perde imediatamente o jogo
    public BlueScreeOfDeath(int id) {
        super(id);
        titulo = "Blue Screen of Death";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        if (levouCounter){
            return "Caiu num Blue Screen of Death, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Blue Screen of Death, Perdeu\n Better Luck next time;)";
    }

    @Override
    public int getQuantidadePosicoesAnda(int dado, int posicaoAtual, int posicaoAnterior, int posicaoAnterior2, boolean levouCounter) throws Exception {
        throw new Exception(titulo);
    }



    @Override
    public boolean daCounter(String nomeAbismo) {
        return false;
    }


}
