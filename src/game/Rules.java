package game;

import materials.Coin;
import materials.CoinState;

import java.util.List;

public class Rules {
    private static Rules instanceRules;

    private Rules() {
    }

    public static Rules getInstance() {
        if (instanceRules == null) {
            instanceRules = new Rules();
        }
        return instanceRules;
    }

    /**
     * Cette méthode permet de déterminer si une suite d'états de pièce permet de gagner à une partie
     *
     * @param states liste d'états pour un joueur
     * @return true si un joueur a gagné, false sinon
     */
    public boolean checkWin(List<CoinState> states) {
        int j = 0;
        for (CoinState state : states) {
            // Nombre de tails à la suite
            if (state == CoinState.TAILS) {
                j++;
            } else {
                j = 0;
            }
            // Si on en as 3, on sort
            if (j == 3) {
                return true;
            }
        }
        // Si on a tout parcouru sans trouver, c'est qu'on a pas win
        return false;
    }
}

