package materials;

import java.util.Random;

public class Coin {
    private static Coin instanceCoin;
    private CoinState coinState;

    /**
     * Change l'état de la pièce.
     * 50% de probabilité d'obtenir HEADS, 50% de probabilité d'obtenir TAILS
     */
    public void throwCoin() {
        int etat = new Random().nextInt(CoinState.values().length);
        instanceCoin.coinState = CoinState.values()[etat];
    }

    public CoinState getState() {
        return instanceCoin.coinState;
    }

    public static Coin getInstance() {
        if (instanceCoin == null) {
            instanceCoin = new Coin();
        }
        return instanceCoin;
    }
}
