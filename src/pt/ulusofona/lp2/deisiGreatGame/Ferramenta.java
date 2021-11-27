package pt.ulusofona.lp2.deisiGreatGame;

public class Ferramenta extends AbismoOrFerramenta {

    public Ferramenta(int id) {
        super(id);
        darTitulo();
    }

    @Override
    void darTitulo() {
        switch (id) {
            case 0 -> titulo = "Herança "; // Evita os efeitos de: duplicação de código
            case 1 -> titulo = "Programação funcional"; // O programador recua N casas, sendo N metade do valor que tiver saído no dado, arredondado para baixo.
            case 2 -> titulo = "Testes unitários"; //  Evita os efeitos de:Erro de Lógica
            case 3 -> titulo = "Tratamento de Excepções "; // Evita os efeitos de:Exception,File Not Found Exception
            case 4 -> titulo = "IDE"; // Evita os efeitos de:Erro de Sintaxe
            case 5 -> titulo = "Ajuda Do Professor"; // Evita os efeitos de:Erro de Sintaxe,Erro de Lógica, Exception, File Not Found Exception




        }
    }
}
