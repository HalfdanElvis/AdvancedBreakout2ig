package Model;

import java.util.ArrayList;

public class GenerateCards {

    public static ArrayList<Card> generateCommonCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card("+Attack", 1, 0, 0, 0, 0, 0, 0));
        cardList.add(new Card("+Pierce", 0, 0, 1, 0, 0, 0, 0));
        cardList.add(new Card("+Size", 0, 0, 0, Ball.getInitialBallRadius()*0.18, 0, 0, 0));
        cardList.add(new Card("+Platform Speed", 0, 0, 0, 0, Platform.getInitialPlatformVelocity()*0.15, 0, 0));
        cardList.add(new Card("+Lives", 0, 0, 0, 0, 0, 0, 1));
        return cardList;
    }

    public static ArrayList<Card> generateRareCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card("Sniper", 2, Ball.getInitialBallVelocity()*0.05, 1, 0, 0, 0, 0));
        cardList.add(new Card("+Platform Width", 0, 0, 0, 0, 0, Platform.getIntialPlatformWidth()*0.12, 0));
        cardList.add(new Card("Cannonball", 2, 0, 0, Ball.getInitialBallRadius()*0.08, 0, 0, 0));
        cardList.add(new Card("++Attack", 2, 0, 0, 0, 0, 0, 0));
        cardList.add(new Card("++Pierce", 0, 0, 2, 0, 0, 0, 0));
        cardList.add(new Card("++Lives", 0, 0, 0, 0, 0, 0, 2));
        return cardList;
    }

    public static ArrayList<Card> generateLegendaryCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card("Rocketman", 3, 0, 2, 0, 0, 0, 0));
        cardList.add(new Card("Better Platform", 0, 0, 0, 0, Platform.getInitialPlatformVelocity()*0.12, Platform.getIntialPlatformWidth()*0.15, 0));
        cardList.add(new Card("Bullet", 5, Ball.getInitialBallVelocity()*0.1, 1, 0, 0, 0, 0));
        cardList.add(new Card("Wreckingball", 0, 0, 3, 2, 1, 0, 0));
        cardList.add(new Card("Devil's Deal", 8, Ball.getInitialBallVelocity()*0.08, 0, 0, 0, 0, -2));
        cardList.add(new Card("Giant", 0, -Ball.getInitialBallVelocity()*0.1, 0, Ball.getInitialBallRadius()*0.3, 0, 0, 0));
        cardList.add(new Card("Full Hp", 0, 0, 0, 0, 0, 0, 0));
        return cardList;
    }
}