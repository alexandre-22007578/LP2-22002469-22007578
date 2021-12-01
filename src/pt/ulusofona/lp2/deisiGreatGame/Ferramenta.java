package pt.ulusofona.lp2.deisiGreatGame;

public class Ferramenta extends AbismoOrFerramenta {

    public Ferramenta(int id) {
        super(id);
        darTitulo();
    }

    public boolean daCounter(String nomeAbismo) {


        switch (nomeAbismo) {
            case "Erro de sintaxe":
                if (titulo.equals("IDE") || titulo.equals("Ajuda Do Professor")) {
                    return true;
                }
                break;
            case "Erro de lógica":
                if (titulo.equals("Testes unitários") || titulo.equals("Ajuda Do Professor")) {
                    return true;
                }
                break;
            case "Exception":
            case "File Not Found Exception":
                if (titulo.equals("Tratamento de Excepções") || titulo.equals("Ajuda Do Professor")) {
                    return true;
                }
                break;
            case "Crash (aka Rebentanço)":// perguntar ao professor
                break;
            case "Duplicated Code":
                if (titulo.equals("Herança")) {
                    return true;
                }
                break;
            case "Efeitos secundários":
                if (titulo.equals("Programação funcional")) {
                    return true;
                }
                break;
            case "Blue Screen of Death":
                break;
            case "Ciclo infinito":// perguntar ao professor

                break;
            default:
                break;


        }

        return false;
    }


    @Override
    void darTitulo() {
        switch (id) {
            case 0 -> titulo = "Herança"; // Evita os efeitos de: duplicação de código
            case 1 -> titulo = "Programação funcional"; // Evita os efeitos de: efeitos secundarios
            case 2 -> titulo = "Testes unitários"; //  Evita os efeitos de:Erro de Lógica
            case 3 -> titulo = "Tratamento de Excepções"; // Evita os efeitos de:Exception,File Not Found Exception
            case 4 -> titulo = "IDE"; // Evita os efeitos de:Erro de Sintaxe
            case 5 -> titulo = "Ajuda Do Professor"; // Evita os efeitos de:Erro de Sintaxe,Erro de Lógica, Exception, File Not Found Exception

        }
    }

    @Override
    public String toString() {
        return titulo;
    }
}
