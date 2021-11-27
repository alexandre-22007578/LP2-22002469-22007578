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
    abstract void darTitulo();
}
