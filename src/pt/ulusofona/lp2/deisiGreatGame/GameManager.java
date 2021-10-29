package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.util.ArrayList;

public class GameManager {

    public GameManager() {
    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {

        return true;
    }

    public String getImagePng(int position) {
        return "";
    }

    public ArrayList<Programmer> getProgrammers() {
        return new ArrayList<>();
    }

    public ArrayList<Programmer> getProgrammers(int position) {
        return new ArrayList<>();
    }

    public int getCurrentPlayerID() {
        return 0;
    }

    public boolean moveCurrentPlayer(int nrPositions){
        return true;
    }
    public boolean gameIsOver(){
        return true;
    }

    public ArrayList<String> getGameResults(){
        return new ArrayList<>();
    }

    public JPanel getAuthorsPanel(){
        return new JPanel();
    }




}
