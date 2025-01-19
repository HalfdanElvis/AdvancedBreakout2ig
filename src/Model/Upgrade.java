//this file randomly chooses an upgrade card to pick
//written by Jesper
package Model;

import java.util.ArrayList;
import java.util.Random;

public class Upgrade {

    public static Card getUpgrade(ArrayList<Card> commonList, ArrayList<Card> rareList, ArrayList<Card> legendaryList) {
        Random random = new Random();
        double rarity = Math.random();

        if (rarity < 0.8) { // Common Card 80% chance
            int rand_int = random.nextInt(commonList.size());
            return commonList.get(rand_int);

        } else if (rarity < 0.95) { // Rare Card 15% Chance
            int rand_int = random.nextInt(rareList.size());
            return rareList.get(rand_int);

        } else { // Legendary Card 5%
            int rand_int = random.nextInt(legendaryList.size());
            return legendaryList.get(rand_int);
        }
    }
}
