package game;

import materials.Coin;
import materials.CoinState;
import player.Player;
import utils.Statistics;

import java.util.*;

public class Game {

    private Rules rules;
    private Coin coin;
    private Map<Player, List<CoinState>> history;

    public Game() {
        history = new HashMap<>();
    }

    /**
     * Ajouter un nouveau joueur au jeu
     *
     * @param player le nouveau joueur
     */
    public void addPlayer(Player player) {
        history.put(player, new ArrayList<>());
    }

    /**
     * Faire jouer tous les joueurs et stocker chaque partie dans history
     */
    public void play() {
        coin = Coin.getInstance();
        rules = Rules.getInstance();
        for (Player p: history.keySet()) {
            while(!rules.checkWin(history.get(p))) {
                p.play(coin);
                history.get(p).add(coin.getState());
            }
        }
    }

    /**
     * Calculer des statistiques de la partie précédente
     *
     * @return Statistics
     */
    public Statistics getStatistics() {
        int totalMoves = 0;
        int minMoves = Integer.MAX_VALUE;
        int maxMoves = 0;
        float avgMoves = 0f;
        for(Player p : history.keySet()){
              // statsAverageToWin
            List<CoinState> currentPlayerList = getSpecificHistory(p);
            totalMoves += currentPlayerList.size();
            if(currentPlayerList.size() < minMoves){
                minMoves = currentPlayerList.size();
            }
            if(currentPlayerList.size() > maxMoves){
                maxMoves = currentPlayerList.size();
            }
        }
        avgMoves = (float)totalMoves / (float)history.keySet().size();
        return new Statistics(avgMoves, minMoves, maxMoves, totalMoves);
    }

    /**
     * Obtenir l'historique de tous les joueurs de la partie précédente
     *
     * @return Map contenant chaque joueur et la liste des ses lancers
     */
    public Map<Player, List<CoinState>> getHistory() {
      return history;
    }


    /**
     * Obtenir l'historique d'un joueur spécifique
     *
     * @param player instance du joueur pour lequel on veut avoir l'historique
     * @return la liste des lancers d'un joueur
     */
    public List<CoinState> getSpecificHistory(Player player) {
      return history.get(player);
    }

}
