//this file randomly chooses an upgrade card to pick

package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Upgrade {
    private static double[] thresholds = {0,6,7.5,9,11}; //describes rarity
    private static Random random = new Random();

    public static Card getUpgrade(List<ArrayList<Card>> cardListArray, double luck) {
        luck = Math.pow(luck, 0.66); //controls how much luck scales
        double[] probabilities = NormalDistribution.generateProbabilities(thresholds.length, luck, 4, thresholds);
        //for (int i = 0; i < probabilities.length; i++) {System.out.println("tier "+i+" "+probabilities[i]);} System.out.println();

        ArrayList<Card> choosenCardList = cardListArray.get(NormalDistribution.chooseTier(probabilities));
        int randInt = random.nextInt(choosenCardList.size());
        return choosenCardList.get(randInt);
    }
}
