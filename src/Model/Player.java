//this file allows a player to be constructed, so the game can keep track of
//how many lives they have left, and their score
//written by Jesper

package Model;

public class Player {

    private long score;
    private int lives;
    private int maxLives;
    private int level;
    private String name;

    public Player() {
        this.score = 0;
        this.maxLives = 3;
        this.lives = 3;
        this.level = 1;
    }

    public void addLives(int extra) {
        this.lives += extra;
        this.maxLives += extra;
    }

    public void fullHP() {
        this.lives = this.maxLives;
    }

    public void updateScore(double points) { //Skal fungere sammen med blocks.
        this.score += points;
    }

    // Getters Setters
    public double getCurrentScore() { return score; }

    public int getLives() { return lives; }
    public int getMaxLives() { return maxLives; }
    public void setLives(int lives) { this.lives = lives; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    
    public int getLevel() { return this.level; }
    public void levelUp() { this.level++; } 
}