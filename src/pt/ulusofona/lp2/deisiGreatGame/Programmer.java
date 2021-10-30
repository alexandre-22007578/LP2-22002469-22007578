package pt.ulusofona.lp2.deisiGreatGame;



public class Programmer {
    int id;
    String nome;
    ProgrammerColor cor;
    String estado ="Em Jogo";
    int posicao=1;
    String linguegnsFavoritas;

    public Programmer(int id, String nome, ProgrammerColor cor, String linguegnsFavoritas) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.linguegnsFavoritas = linguegnsFavoritas;
    }

     public int getId(){
        return id;
    }
    public String getName(){
        return nome;
    }
    public int getPosicao(){
        return posicao;
    }
    void aumentaPosicao(int posicao){

        this.posicao+=posicao;
    }
    void diminuiPosicao(int posicao,int tamanhoTabuleiro){
        int posicaoSuposta = this.posicao + posicao;
        int numeroDeCasaQueAndaParaTras=posicaoSuposta-tamanhoTabuleiro;
        this.posicao=tamanhoTabuleiro-numeroDeCasaQueAndaParaTras;
    }

    public ProgrammerColor getColor(){
        return cor;
    }

    @Override
    public String toString()
    {
        String[] linguagens = linguegnsFavoritas.split(";");
        StringBuilder linguagensString = new StringBuilder();
        linguagensString.append(linguagens[0]);
        for (int i = 1; i < linguagens.length; i++){
            linguagensString.append("; ").append(linguagens[i]);
        }

        return id + " | " + nome + " | " + posicao + " | " + linguagensString.toString() + " | " + estado;
    }
}
