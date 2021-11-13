package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestesUnitariosAutomaticos {

    @Test
    public void testMoveCurrentPlayerNegativePosition() {
        GameManager manager = new GameManager();
        assertFalse("Erro ao andar uma posição negativa", manager.moveCurrentPlayer(-1));
        assertFalse("Erro ao andar uma posição negativa", manager.moveCurrentPlayer(-10));
        assertFalse("Erro ao andar uma posição negativa", manager.moveCurrentPlayer(-100));
        assertFalse("Erro ao andar uma posição negativa", manager.moveCurrentPlayer(-1000));
    }

    @Test
    public void testMoveCurrentPlayerWithZeroPositon() {
        GameManager manager = new GameManager();
        assertFalse("Erro ao andar uma posição zero", manager.moveCurrentPlayer(0));
    }

    @Test
    public void testMoveCurrentPlayerWithHigherThanSixPositon() {
        GameManager manager = new GameManager();
        assertFalse("Erro ao andar uma posição superior a 6", manager.moveCurrentPlayer(7));
        assertFalse("Erro ao andar uma posição superior a 6", manager.moveCurrentPlayer(10));
        assertFalse("Erro ao andar uma posição superior a 6", manager.moveCurrentPlayer(100));
        assertFalse("Erro ao andar uma posição superior a 6", manager.moveCurrentPlayer(1000));
    }

    @Test
    public void testMoveCurrentPlayerWithPositionBetweenOneAndSix() {
        GameManager manager = new GameManager();
        Programmer player = new Programmer(1, "Teste", ProgrammerColor.BLUE, "teste");
        manager.players.add(player);
        manager.players.add(player);
        assertTrue("Erro ao andar 1 posição", manager.moveCurrentPlayer(1));
        assertTrue("Erro ao andar 2 posições", manager.moveCurrentPlayer(2));
        assertTrue("Erro ao andar 3 posições", manager.moveCurrentPlayer(3));
        assertTrue("Erro ao andar 4 posições", manager.moveCurrentPlayer(4));
        assertTrue("Erro ao andar 5 posições", manager.moveCurrentPlayer(5));
        assertTrue("Erro ao andar 6 posições", manager.moveCurrentPlayer(6));
    }
}
