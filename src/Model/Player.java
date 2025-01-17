package Model;

public class Player {

    private long currentscore;
    private long highscore;
    private int lives;
    private int maxLives;
    private boolean isAlive;
    private int level;
    private String name;

    public Player() {
        this.currentscore = 0;
        this.highscore = 0;
        this.maxLives = 3;
        this.lives = 3;
        isAlive = true;
        this.level = 1;
    }

    public void updateHighscore() {
        if (this.currentscore > this.highscore) {
            this.highscore = this.currentscore;
        }
    }

    public void addLives(int extra) {
        this.lives += extra;
        this.maxLives = Math.max(this.lives, this.maxLives);
    }

    public void fullHP(int lives) {
        this.lives = this.maxLives;
    }

    public void updateCurrentScore(double points) { //Skal fungere sammen med blocks.
        this.currentscore += points;
    }

    public void updateIsAlive() { // Bliver den her nogensinde brugt? Vi skal måske checke alle vores metoder igennem
        if (this.lives <= 0) {
            this.isAlive = false;
        }
    }

    // Getters Setters & Adders
    public double getCurrentScore() { return currentscore; }
    public double getHighscore() { return highscore; }

    public int getLives() { return lives; }
    public int getMaxLives() { return maxLives; }
    public void setLives(int lives) { this.lives = lives; }
    public void removeLive() { this.lives--; } // Stavet forkert
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public int getLevel() { return this.level; }
    public void levelUp() { this.level++; } 
}