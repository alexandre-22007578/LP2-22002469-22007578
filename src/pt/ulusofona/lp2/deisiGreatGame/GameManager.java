package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {
    private int dado;
    private int tamanhoTabuleiro;
    private int playerAtual;
    private int numeroTotalDeTurnos;
    private ArrayList<Programmer> players = new ArrayList<>();
    private HashMap<Integer, AbismoOrFerramenta> abismosEFerramentas = new HashMap<>();
    private HashMap<Integer, Integer> casasPisadas = new HashMap<>();
    private HashMap<String, Integer> abismosPisadas = new HashMap<>();

    public HashMap<Integer, Integer> getCasasPisadas() {
        return casasPisadas;
    }

    public GameManager() {
    }

    public HashMap<String, Integer> getAbismosPisadas() {
        return abismosPisadas;
    }

    public HashMap<Integer, AbismoOrFerramenta> getAbismosEFerramentas() {
        return abismosEFerramentas;
    }

    public void createAbismoEAdiciona(int id, int posicao) {


        switch (id) {
            case 0 -> {
                ErroDeSintaxe erroDeSintaxe = new ErroDeSintaxe(id);
                abismosEFerramentas.put(posicao, erroDeSintaxe);
                abismosPisadas.put(erroDeSintaxe.getTitulo(), 0);
            }
            case 1 -> {
                ErroDeLogica erroDeLogica = new ErroDeLogica(id);
                abismosEFerramentas.put(posicao, erroDeLogica);
                abismosPisadas.put(erroDeLogica.getTitulo(), 0);
            }
            case 2 -> {
                Exceptionn exceptionn = new Exceptionn(id);
                abismosEFerramentas.put(posicao, exceptionn);
                abismosPisadas.put(exceptionn.getTitulo(), 0);
            }
            case 3 -> {
                FileNotFoundException fileNotFoundException = new FileNotFoundException(id);
                abismosEFerramentas.put(posicao, fileNotFoundException);
                abismosPisadas.put(fileNotFoundException.getTitulo(), 0);
            }
            case 4 -> {
                CrashAkaRebentanco crashAkaRebentanco = new CrashAkaRebentanco(id);
                abismosEFerramentas.put(posicao, crashAkaRebentanco);
                abismosPisadas.put(crashAkaRebentanco.getTitulo(), 0);
            }
            case 5 -> {
                DuplicatedCode duplicatedCode = new DuplicatedCode(id);
                abismosEFerramentas.put(posicao, new DuplicatedCode(id));
                abismosPisadas.put(duplicatedCode.getTitulo(), 0);
            }
            case 6 -> {
                EfeitosSecundarios efeitosSecundarios = new EfeitosSecundarios(id);
                abismosEFerramentas.put(posicao, efeitosSecundarios);
                abismosPisadas.put(efeitosSecundarios.getTitulo(), 0);
            }
            case 7 -> {
                BlueScreeOfDeath blueScreeOfDeath = new BlueScreeOfDeath(id);
                abismosEFerramentas.put(posicao, blueScreeOfDeath);
                abismosPisadas.put(blueScreeOfDeath.getTitulo(), 0);
            }
            case 8 -> {
                CicloInfinito cicloInfinito = new CicloInfinito(id);
                abismosEFerramentas.put(posicao, cicloInfinito);
                abismosPisadas.put(cicloInfinito.getTitulo(), 0);
            }
            case 9 -> {
                SegmentationFault segmentationFault = new SegmentationFault(id);
                abismosEFerramentas.put(posicao, segmentationFault);
                abismosPisadas.put(segmentationFault.getTitulo(), 0);
            }


        }
    }

    public void createFerramentaEAdiciona(int id, int posicao) {
        switch (id) {
            case 0 -> abismosEFerramentas.put(posicao, new Heranca(id));
            case 1 -> abismosEFerramentas.put(posicao, new ProgramacaoFuncional(id));
            case 2 -> abismosEFerramentas.put(posicao, new TstUnitarios(id));
            case 3 -> abismosEFerramentas.put(posicao, new TratamentoDeExcepcoes(id));
            case 4 -> abismosEFerramentas.put(posicao, new Ide(id));
            case 5 -> abismosEFerramentas.put(posicao, new AjudaDoProfessor(id));

        }
    }

    public void createInitialBoard(String[][] playerInfo, int worldSize) throws InvalidInitialBoardException {

        createInitialBoard(playerInfo, worldSize, null);

    }

    public void createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools) throws InvalidInitialBoardException {

        abismosEFerramentas.clear();
        casasPisadas.clear();
        abismosPisadas.clear();
        int abismoOrFerramenta;
        int id;
        int posicao;
        numeroTotalDeTurnos = 1;
        players.clear();
        int idPlayer;
        String nome;
        ProgrammerColor color;
        String linguagensProgramacao;
        HashSet<Integer> ids = new HashSet<>();
        HashSet<ProgrammerColor> cores = new HashSet<>();
        playerAtual = 0;
        if (abyssesAndTools != null) {
            try {

                for (String[] abyssesAndTool : abyssesAndTools) {
                    abismoOrFerramenta = Integer.parseInt(String.valueOf(abyssesAndTool[0]));
                    posicao = Integer.parseInt(String.valueOf(abyssesAndTool[2]));
                    id = Integer.parseInt(String.valueOf(abyssesAndTool[1]));

                    if (posicao > worldSize || posicao < 0) { // check posicao
                        throw new InvalidInitialBoardException("posicao errada", 0, -1);
                    }
                    if (abismoOrFerramenta != 0 && abismoOrFerramenta != 1) { // check abismo ou ferramenta
                        throw new InvalidInitialBoardException("identificador de ferramenta ou abismo inv??lido", 0, -1);
                    }

                    if (abismoOrFerramenta == 0) {
                        if (id < 0 || id > 9) { // check  id abismos

                            throw new InvalidInitialBoardException("ID abismo invalido", 1, id);
                        }

                        createAbismoEAdiciona(id, posicao);

                    }
                    if (abismoOrFerramenta == 1) {
                        if (id < 0 || id > 5) { // check  id ferramenta

                            throw new InvalidInitialBoardException("ID ferramenta invalido", 2, id);

                        }
                        createFerramentaEAdiciona(id, posicao);
                    }
                }
            } catch (InvalidInitialBoardException e) {
                if (e.getMessage() == null) {
                    throw new InvalidInitialBoardException("Parametros fora do tipo  de vari??vel pedida", 0, -1);
                } else {
                    throw e;
                }
            }
        }


        if (worldSize < playerInfo.length * 2 || playerInfo.length < 2 || playerInfo.length > 4) {
            throw new InvalidInitialBoardException("Tamanho so tabuleiro ou numero de jogadores inv??lidos", 0, -1);
        }
        try {
            for (String[] strings : playerInfo) {
                idPlayer = Integer.parseInt(String.valueOf(strings[0]));
                if (strings[1] == null || strings[1].equals("")) {
                    throw new InvalidInitialBoardException("Nome do jogador inv??lido", 0, -1);
                }

                nome = strings[1];
                linguagensProgramacao = String.valueOf(strings[2]);
                color = switch (strings[3]) {
                    case "Purple" -> ProgrammerColor.PURPLE;
                    case "Green" -> ProgrammerColor.GREEN;
                    case "Brown" -> ProgrammerColor.BROWN;
                    case "Blue" -> ProgrammerColor.BLUE;
                    default -> throw new InvalidInitialBoardException("Cor do jogador invalido", 0, -1);
                };
                if (idPlayer < 1 || !(ids.add(idPlayer)) || !(cores.add(color))) {
                    throw new InvalidInitialBoardException("Id invalido ou repetido ou cor repetida", 0, -1);
                }
                Programmer programmer = new Programmer(idPlayer, nome, color, linguagensProgramacao);
                players.add(programmer);


            }
            this.tamanhoTabuleiro = worldSize;
        } catch (InvalidInitialBoardException e) {

            if (e.getMessage() == null) {
                throw new InvalidInitialBoardException("Parametros fora do tipo  de vari??vel pedida", 0, -1);
            } else {
                throw e;
            }

        }

        players.sort(Comparator.comparingInt(Programmer::getId));


    }

    public String getTitle(int position) {

        if (position < 0 || position > tamanhoTabuleiro) {
            return null;
        }
        if (abismosEFerramentas.containsKey(position)) {
            return abismosEFerramentas.get(position).getTitulo();
        }
        return null;
    }

    public String getImagePng(int position) {

        if (position < 0 || position > tamanhoTabuleiro) {
            return null;
        }

        if (position == tamanhoTabuleiro) {
            return "podium.png";
        }

        if (abismosEFerramentas.containsKey(position)) {
            return abismosEFerramentas.get(position).getTitulo() + ".png";
        }


        return null;

    }

    public List<Programmer> getProgrammers(boolean includeDefeated) {

        ArrayList<Programmer> jogadores = new ArrayList<>();


        if (!includeDefeated) {

            for (Programmer player : players) {
                if (player.getEstado().equals("Em Jogo")) {
                    jogadores.add(player);
                }
            }
            return jogadores;
        }
        return players;

    }

    public List<Programmer> getProgrammers(int position) {

        ArrayList<Programmer> programmersInPosition = new ArrayList<>();
        if (position < 1 || position > tamanhoTabuleiro) {
            return null;
        }
        for (Programmer player : players) {
            if (player.getPosicao() == position) {
                programmersInPosition.add(player);
            }
        }
        if (programmersInPosition.size() == 0) {
            return null;
        }
        return programmersInPosition;
    }

    public String getProgrammersInfo() {
        List<Programmer> players = getProgrammers(false);
        StringBuilder info = new StringBuilder();

        for (int i = 0; i < players.size(); i++) {
            if (i == players.size() - 1) {
                info.append(players.get(i).getProgrammersInfo());
            } else {
                info.append(players.get(i).getProgrammersInfo()).append(" | ");
            }
        }

        return info.toString();
    }

    public int getCurrentPlayerID() {
        String estado = players.get(playerAtual).getEstado();

        if (estado.equals("Derrotado")) {

            if (playerAtual == players.size() - 1) {
                playerAtual = 0;
            } else {
                playerAtual++;
            }
            return getCurrentPlayerID();
        }
        return players.get(playerAtual).getId();

    }

    public boolean moveCurrentPlayer(int nrSpaces) {
        int nrPisadelas;
        dado = nrSpaces;

        if (nrSpaces < 1 || nrSpaces > 6) {
            return false;
        }

        if (abismosEFerramentas.containsKey(players.get(playerAtual).getPosicao())) { // so para o ciclo infinito
            if (players.get(playerAtual).getStuck()) {
                return false;
            }
        }


        players.get(playerAtual).mover(nrSpaces, tamanhoTabuleiro);

        // casas pisadas
        if (casasPisadas.containsKey(players.get(playerAtual).getPosicao())) {
            nrPisadelas = casasPisadas.get(players.get(playerAtual).getPosicao()) + 1;

            casasPisadas.put(players.get(playerAtual).getPosicao(), nrPisadelas);
        } else {
            casasPisadas.put(players.get(playerAtual).getPosicao(), 1);
        }


        // abismos pissadas
        if (abismosEFerramentas.containsKey(players.get(playerAtual).getPosicao())) { // nesta posi????o existe um abismo ou ferramenta
            if (!abismosEFerramentas.get(players.get(playerAtual).getPosicao()).souFerramenta()) { // apenas abismos
                String titulo = abismosEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo();
                if (abismosPisadas.containsKey(titulo)) {
                    int pisadas = abismosPisadas.get(titulo) + 1;
                    abismosPisadas.put(titulo, pisadas);
                }
            }
        }
        return true;
    }

    public void mudaTurno() {

        if (playerAtual == players.size() - 1) {
            playerAtual = 0;
        } else {
            playerAtual++;
        }

        numeroTotalDeTurnos++;
    }

    public String reactToAbyssOrTool() {


        if (abismosEFerramentas.containsKey(players.get(playerAtual).getPosicao())) {

            players.get(playerAtual).adicionaFerramenta(abismosEFerramentas.get(players.get(playerAtual).getPosicao()));


            boolean daCounter = false;
            for (int i = 0; i < players.get(playerAtual).getFerramentas().size(); i++) {

                daCounter = players.get(playerAtual).getFerramentas().get(i).daCounter(abismosEFerramentas.get(players.get(playerAtual).getPosicao()).getTitulo());
                if (daCounter) {
                    players.get(playerAtual).retiraFerramenta(players.get(playerAtual).getFerramentas().get(i));
                    break;
                }
            }
            String resulado = abismosEFerramentas.get(players.get(playerAtual).getPosicao()).move(dado, players.get(playerAtual), tamanhoTabuleiro, daCounter, players);

            mudaTurno();
            return resulado;
        }

        mudaTurno();
        return null;
    }

    public boolean gameIsOver() {

        if (getProgrammers(false).size() == 1) {
            return true;
        }

        for (Programmer player : getProgrammers(false)) {
            if (player.getPosicao() == tamanhoTabuleiro) {
                return true;
            }
        }
        return false;

    }

    public List<String> getGameResults() {
        int i;
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("O GRANDE JOGO DO DEISI");
        resultados.add("");
        resultados.add("NR. DE TURNOS");
        resultados.add("" + numeroTotalDeTurnos);
        resultados.add("");
        resultados.add("VENCEDOR");
        players.sort(Comparator.comparingInt(Programmer::getPosicao).reversed().thenComparing(Programmer::getName));
        resultados.add(players.get(0).getName());
        resultados.add("");
        resultados.add("RESTANTES");
        for (i = 1; i < players.size() - 1; i++) {
            resultados.add(players.get(i).getName() + " " + players.get(i).getPosicao());
        }
        resultados.add(players.get(i).getName() + " " + players.get(i).getPosicao() + "");

        return resultados;
    }

    public JPanel getAuthorsPanel() {

        JPanel creditos = new JPanel();
        creditos.setSize(300, 300);
        ArrayList<JLabel> labels = new ArrayList<>();

        labels.add(new JLabel("Parte 3 do projeto de LP2\n"));
        labels.add(new JLabel("                                                                  "));
        labels.add(new JLabel("Programadores do DEISI GREAT GAME:"));
        labels.add(new JLabel("              Alexandre Costa            "));
        labels.add(new JLabel("              Daniel Granja              "));
        labels.add(new JLabel("                                                                  "));
        labels.add(new JLabel("Professores da cadeira de LP2 2021 / 2022"));
        labels.add(new JLabel("              Pedro Alves            "));
        labels.add(new JLabel("              L??cio Ferreira              "));
        labels.add(new JLabel("              Bruno Ciperiano            "));


        for (JLabel label : labels) {
            creditos.add(label);
        }


        return creditos;
    }


    public boolean saveGame(File file) {

        try {

            FileWriter writer = new FileWriter(file);
            PrintWriter print = new PrintWriter(writer);

            print.println(tamanhoTabuleiro);
            print.println(playerAtual);
            print.println(numeroTotalDeTurnos);
            print.println(dado);
            boolean souFerramenta;// abismo ou Ferrammenta
            int id;

            for (int posicao = 0; posicao < tamanhoTabuleiro; posicao++) {
                if (abismosEFerramentas.containsKey(posicao)) {
                    souFerramenta = abismosEFerramentas.get(posicao).souFerramenta();
                    id = abismosEFerramentas.get(posicao).getId();
                    print.println(posicao + ":" + id + ":" + souFerramenta);
                }
            }

            String nome;
            ProgrammerColor cor;
            String estado;
            int posicao;

            boolean stuck;
            int posicaoAnterior;
            int posicao2Anterior;


            for (Programmer player : players) {
                StringBuilder ferramentas = new StringBuilder();
                StringBuilder linguagens = new StringBuilder();
                id = player.getId();
                nome = player.getNome();
                cor = player.getColor();
                estado = player.getEstado();
                posicao = player.getPosicao();
                if (player.getFerramentas() == null || player.getFerramentas().size() == 0) {
                    ferramentas.append("NOTOOLS");
                }
                for (int i = 0; i < player.getFerramentas().size(); i++) {
                    if (i == 0) {

                        ferramentas.append(player.getFerramentas().get(i).getId());
                    } else {
                        ferramentas.append("@").append(player.getFerramentas().get(i).getId());
                    }

                }
                for (int i = 0; i < player.getLinguagensList().size(); i++) {
                    if (i == 0) {

                        linguagens.append(player.getLinguagensList().get(i));
                    } else {
                        linguagens.append(";").append(player.getLinguagensList().get(i));
                    }

                }
                stuck = player.getStuck();
                posicaoAnterior = player.getPosicaoAnterior();
                posicao2Anterior = player.getPosicao2Anterior();
                print.println(id + ":" + nome + ":" + cor + ":" + estado + ":" + posicao + ":" + ferramentas + ":" + stuck + ":" + posicaoAnterior + ":" + posicao2Anterior + ":" + linguagens);
            }
            writer.close();

        } catch (Exception e) {
            return false;
        }


        return true;
    }

    public boolean loadGame(File file) {

        try {


            abismosEFerramentas.clear();
            players.clear();

            FileReader reader = new FileReader(file);
            BufferedReader buffered = new BufferedReader(reader);
            int numeroLinha = 0;

            String linha;

            while ((linha = buffered.readLine()) != null) {

                //partir a linha no caractere separador
                String[] dados = linha.split(":");

                switch (numeroLinha) {
                    case 0 -> tamanhoTabuleiro = Integer.parseInt(dados[0]);
                    case 1 -> playerAtual = Integer.parseInt(dados[0]);
                    case 2 -> numeroTotalDeTurnos = Integer.parseInt(dados[0]);
                    case 3 -> dado = Integer.parseInt(dados[0]);
                }
                numeroLinha++;

                if (dados.length == 3) { //feramentas e abismos tabuleiro
                    int id = Integer.parseInt(dados[1]);
                    int posicao = Integer.parseInt(dados[0]);
                    if (!Boolean.parseBoolean(dados[2])) {
                        createAbismoEAdiciona(id, posicao);
                    } else {
                        createFerramentaEAdiciona(id, posicao);
                    }
                }
                if (dados.length == 10) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];

                    ProgrammerColor cor = switch (dados[2]) {
                        case "PURPLE" -> ProgrammerColor.PURPLE;
                        case "GREEN" -> ProgrammerColor.GREEN;
                        case "BROWN" -> ProgrammerColor.BROWN;
                        case "BLUE" -> ProgrammerColor.BLUE;
                        default -> null;
                    };

                    String estado = dados[3];
                    int posicao = Integer.parseInt(dados[4]);

                    // fazer ferramentas

                    String[] ferramentas = dados[5].split("@");

                    boolean stuck = Boolean.parseBoolean(dados[6]);
                    int posicaoAnterior = Integer.parseInt(dados[7]);
                    int posicao2Anterior = Integer.parseInt(dados[8]);
                    String linguagens = dados[9];
                    Programmer programmer = new Programmer(id, nome, cor, estado, posicao, ferramentas, stuck, posicaoAnterior, posicao2Anterior, linguagens);
                    players.add(programmer);
                }

            }

            buffered.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}