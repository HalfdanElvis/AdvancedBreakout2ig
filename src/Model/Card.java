//this file allows cards to be constructed and for the card to apply its' upgrades

package Model;
import javafx.scene.control.Button;

public class Card extends Button {
    private String name;
    private int ballAttack;
    private double ballSpeed;
    private int ballPierce;
    private double ballSize;
    private double platformSpeed;
    private double platformWidth;
    private int lives;
    private double luck;
    private double critDamage;
    private double critChance;
    private int rarity;
    private double fullHP;

    public Card(String name, String stats) {
        this.name = name;
        if (stats != "") {
            String[] stringArray = stats.split(",");
            for (int i = 0; i < stringArray.length; i++) {
                String[] currentStringArray = stringArray[i].split("=");
                initializeField(currentStringArray[0].trim().toLowerCase(), Double.valueOf(currentStringArray[1].trim()));
            }
        }
    }

    private void initializeField(String string, double value) {
        switch (string) {
            case "attack":
                this.ballAttack = (int) value;
                break;
            case "ballspeed":
                this.ballSpeed = value;
                break;
            case "pierce":
                this.ballPierce = (int) value;
                break;
            case "ballsize":
                this.ballSize = value;
                break;
            case "platformspeed":
                this.platformSpeed = value;
                break;
            case "platformwidth":
                this.platformWidth = value;
                break;
            case "lives":
                this.lives = (int) value;
                break;
            case "luck":
                this.luck = value;
                break;
            case "critdamage":
                this.critDamage = value;
                break;
            case "critdmg":
                this.critDamage = value;
                break;
            case "critchance":
                this.critChance = value;
                break;
            case "fullhp":
                this.fullHP = value;
                break;
            default:
                System.out.println("ERROR: invalid upgrade");
                break;
        }
    }

    public void applyUpgrades(Ball ball, Platform platform, Player player) {
        if (fullHP != 0) {
            player.fullHP();
        }
        ball.addAttack(ballAttack);
        ball.addOGVelocity(ballSpeed*Ball.getInitialBallVelocity());
        ball.addMaxPierce(ballPierce);
        ball.addRadius(ballSize*Ball.getInitialBallRadius());
        ball.addCritDamage(critDamage);
        ball.addCritChance(critChance);
        platform.addVelocity(platformSpeed*Platform.getInitialPlatformVelocity());
        platform.addPlatformWidth(platformWidth*Platform.getIntialPlatformWidth());
        player.addLives(lives);
        player.addLuck(luck);
    }

    public String getName() { return name; }
    public int getRarity() { return rarity; }
    public void setRarity(int rarity) { this.rarity = rarity; }
}