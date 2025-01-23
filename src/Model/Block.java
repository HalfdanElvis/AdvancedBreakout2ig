//this file keeps track of the current state of a block
//and how much hp, score and what color each tier has

package Model;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    private long score;
    private double hp;
    private double originalHp;
    private static long[] scoreArray = {10, 15, 25, 40, 60, 100, 150, 250};
    private static long[] hpArray = {1, 2, 3, 5, 7, 10, 15, 20};
    private static Color[] colorArray = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.VIOLET, Color.BLACK, Color.WHITE};

    public Block(int tier, int level, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.hp = hpArray[tier] + (long) (hpArray[tier] * level/5.0);
        this.originalHp = this.hp;
        this.score = scoreArray[tier] + (long) (scoreArray[tier] * level/20.0);
        this.setFill(colorArray[tier]);
    }

    public void updateOpacity() {
        this.setOpacity(Math.max(Math.sqrt(hp/originalHp), 0.3));
    }

    public void loseHp(double attack) { hp -= attack; }

    // Getters
    public double getHp() { return hp; }
    public static int getMaxTier() { return colorArray.length-1; }
    public long getScore(){ return score; }

}