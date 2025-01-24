//this file contains all the possible upgrade cards and what they upgrade

package Model;

import java.util.ArrayList;

public class GenerateCards {

    public static ArrayList<Card> generateCommonCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card("+Attack", "attack = 1"));
        cardList.add(new Card("+Pierce", "pierce = 1"));
        cardList.add(new Card("+Size", "ballsize = 0.12"));
        cardList.add(new Card("+Platform Speed", "platformSpeed = 0.1"));
        cardList.add(new Card("+Lives", "lives = 1"));
        cardList.add(new Card("+Luck", "luck = 0.9"));
        cardList.add(new Card("+Crit Dmg", "critdamage = 0.3"));
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setRarity(0);
        }
        return cardList;
    }

    public static ArrayList<Card> generateRareCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card("+Platform Width", "platformwidth = 0.12"));
        cardList.add(new Card("Cannonball", "attack = 2, ballsize = 0.06"));
        cardList.add(new Card("++Attack", "attack = 2"));
        cardList.add(new Card("++Pierce", "pierce = 2"));
        cardList.add(new Card("++Lives", "lives = 2"));
        cardList.add(new Card("Slow Ball", "ballspeed = -0.1"));
        cardList.add(new Card("+Crit Chance", "critchance = 0.08"));
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setRarity(1);
        }
        return cardList;
    }

    public static ArrayList<Card> generateEpicCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card("Sniper", "attack = 3, ballspeed = 0.03, pierce = 1"));
        cardList.add(new Card("Wreckingball", "attack = 1, pierce = 3, ballsize = 0.08"));
        cardList.add(new Card("+++Attack", "attack = 3"));
        cardList.add(new Card("++Luck", "luck = 1.6"));
        cardList.add(new Card("Crit Master", "critchance = 0.06, critdamage = 0.5"));
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setRarity(2);
        }
        return cardList;
    }

    public static ArrayList<Card> generateLegendaryCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card("Rocketman", "attack = 3, pierce = 2"));
        cardList.add(new Card("Better Platform", "platformspeed = 0.08, platformwidth = 0.16"));
        cardList.add(new Card("Bullet", "attack = 5, ballspeed = 0.07, pierce = 1"));
        cardList.add(new Card("Devil's Deal", "attack = 8, ballspeed = 0.05, ballsize = -0.15, lives = -2"));
        cardList.add(new Card("Giant", "attack = 1, ballspeed = -0.08, ballsize = 0.25"));
        cardList.add(new Card("Full Hp", "fullhp = 1"));
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setRarity(3);
        }
        return cardList;
    }

    public static ArrayList<Card> generateMythicCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card("Railgun", "attack = 5, pierce = 3"));
        cardList.add(new Card("Prolonged Life", "fullhp = 1, lives = 1, ballspeed = -0.07"));
        cardList.add(new Card("Lucifer's Contract", "attack = 13, ballspeed = 0.12, ballsize = -0.23, lives = -3, critdamage = 0.66, critchance = 0.066, luck = -0.66"));
        cardList.add(new Card("The Honored One", "attack = 4, ballsize = 0.11, critchance = 0.044, critdamage = 0.33"));
        cardList.add(new Card("God's Blessing", "attack = 2, pierce = 2, lives = 2, ballspeed = -0.02, platformwidth = 0.02, platformspeed = 0.02, luck = 0.2, critchance = 0.02, critdamage = 0.2"));
        
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setRarity(4);
        }
        return cardList;
    }
}