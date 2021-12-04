package pt.ulusofona.lp2.deisiGreatGame;

public class SegmentationFault extends AbismoOrFerramenta{
    //Este Abismo apenas é activado caso existam dois ou mais programadores na mesma casa.
    //Todos os jogadores nessa casa recuam 3 casas.Caso apenas esteja um programador neste Abismo, então não existe nenhum efeito a aplicar.
    public SegmentationFault(int id) {
        super(id);
        titulo="Segmentation Fault";
    }

    @Override
    public String reactToAbyssOrTool(boolean levouCounter) {

        if (levouCounter){
            return "Caiu num Segmentation Fault, mas utilizou uma ferramenta para evitar";
        }
        return "Caiu num Segmentation Fault, Não acontece nada, pois está sozinho na casa";
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
