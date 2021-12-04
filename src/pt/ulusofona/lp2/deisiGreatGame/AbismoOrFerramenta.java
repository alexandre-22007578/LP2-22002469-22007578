package pt.ulusofona.lp2.deisiGreatGame;

public abstract class AbismoOrFerramenta {
    protected int id;
    protected String titulo;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public AbismoOrFerramenta(int id) {
        this.id = id;
    }

    public abstract String reactToAbyssOrTool(boolean levouCounter);

    public abstract int getQuantidadePosicoesAnda(int dado,int posicaoAtual,int posicaoAnterior,int posicaoAnterior2,boolean levouCounter) throws Exception;



    public abstract boolean daCounter(String nomeAbismo);


    @Override
    public String toString() {
        return titulo;
    }
}
