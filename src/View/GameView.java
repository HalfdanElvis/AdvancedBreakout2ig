//this file shows the game while its running, and allows the player to type their name.
//it also shows the death screen and upgrade screen when appropriate
//written by Halfdan & Jesper

package View;

import Model.*;
import View.Buttons.*;
import java.util.ArrayList;
import Controller.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameView extends Pane{
    private Player player;
    private Platform platform;
    private Ball ball;
    private ArrayList<Block> blockList;
    private ArrayList<Card> commonList;
    private ArrayList<Card> rareList;
    private ArrayList<Card> legendaryList;
    private VBox deathScreen;
    private HBox upgradeScreen;
    private VBox upgradeTitle;
    private double sceneWidth = OptionsModel.getSceneWidth();
    private double sceneHeight = OptionsModel.getSceneHeight();
    private VBox nameScreen;
    private TextField nameField;
    private String commonTextColor = "rgb(241, 241, 241)";
    private String rareTextColor = "rgb(40, 183, 255)";
    private String legendaryTextColor = "rgb(253, 229, 43)";

    // HUD
    private Text lives;
    private Text level;
    private Text score;
    private Text playerName;
    private Text combo;
    private Rectangle HUDBackground;

    public GameView (){
        player = new Player();
        setupObjects(sceneWidth, sceneHeight);
        initializeHUD();
    }

    public void setupObjects(double sceneWidth, double sceneHeight) {
        //Background
        Image image = new Image("/resources/ForestLevel.png");
        ImageView bg = new ImageView(image);
        bg.setFitWidth(sceneWidth);
        bg.setFitHeight(sceneHeight);

        platform = new Platform();

        ball = new Ball(platform);

        //UpgradeScreen
        initializeUpgradeScreen(sceneWidth, sceneHeight);

        getChildren().addAll(bg, platform, ball);

        // Generates and adds blocks
        blockList = Model.GenerateBlocks.generateBlocks(player.getLevel());
        for (int i = 0; i < blockList.size(); i++){
            getChildren().add(blockList.get(i));
        }

        getChildren().add(upgradeScreen);
        getChildren().add(upgradeTitle);

    }

    public void initializeHUD(){
        HUDBackground = new Rectangle(0,0, sceneWidth, sceneHeight*0.03);
        HUDBackground.setFill(Color.WHITE);
        lives = new Text(sceneWidth*0.8, 24, "Lives: " + player.getLives()+"/"+player.getMaxLives());
        lives.setFill(Color.BLACK);
        level = new Text(sceneWidth*0.6,24,"Level: " + (player.getLevel()));
        score = new Text(sceneWidth*0.4,24,"Score: " + (player.getCurrentScore()));
        combo = new Text(sceneWidth*0.2,24,"Combo: " + (ball.getCombo()));
        playerName = new Text(2,24,player.getName());
        playerName.setStyle("-fx-font-size: 24px;");
        lives.setStyle("-fx-font-size: 24px;");
        level.setStyle("-fx-font-size: 24px;");
        score.setStyle("-fx-font-size: 24px;");
        combo.setStyle("-fx-font-size: 24px;");
        getChildren().addAll(HUDBackground, playerName, lives, level, score, combo);
    }

    public void updateHUD() {
        playerName.setText(player.getName());
        lives.setText("Lives: " + player.getLives()+"/"+player.getMaxLives());
        level.setText("Level: " + (player.getLevel()));
        score.setText("Score " + (long)(player.getCurrentScore()));
        combo.setText("Combo: " + (ball.getCombo()));
    }

    public void initializeNameScreen() {
        nameScreen = new VBox();
        Text text = new Text("Name:");
        text.setStyle("-fx-font-size: 80px; -fx-fill: white;");
        
        nameField = new TextField();
        
        nameField.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 112px; -fx-border-color: white;-fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-outline-width: 0;");
        nameField.setAlignment(Pos.CENTER);
        nameField.setMaxWidth(sceneWidth / 5);   
        nameField.setMinWidth(sceneWidth / 5);

        int maxLength = 5;
        nameField.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() > maxLength) { //limits name length to maxLength characters
                return null;
            }
            return change;
        }));

        nameField.setOnAction(event -> {
            player.setName(nameField.getText());
            updateHUD();
            removeNameScreen();
        });

        nameScreen.setPrefWidth(sceneWidth);
        nameScreen.setPrefHeight(sceneHeight);
        nameScreen.setStyle("-fx-background-color: rgba(0, 0, 0, 0.90);");
        nameScreen.setAlignment(Pos.CENTER);
        nameScreen.getChildren().addAll(text, nameField);

        getChildren().add(nameScreen);

    }

    public void removeNameScreen() {
        getChildren().remove(nameScreen);
    }

    public void initializeDeathScreen(double sceneWidth, double sceneHeight) {
        Highscore.writeHighscore(player);
        Text deathMsg = new Text("You died.");
        deathMsg.setStyle("-fx-font-size: 36px; -fx-fill: white;");
        Text deathScore = new Text("Score " + player.getName()+": "+(int)(player.getCurrentScore()));
        deathScore.setStyle("-fx-font-size: 36px; -fx-fill: white;");

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefSize(OptionsModel.getSceneWidth()/3, OptionsModel.getSceneHeight()*0.75/9);
        mainMenuButton.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        mainMenuButton.setOnAction(event -> SceneManager.getInstance().switchToMainMenuView());

        ExitButton exitButton = new ExitButton();

        Text spacing = new Text("");
        spacing.setStyle("-fx-font-size: 36px; -fx-fill: white;");

        Button newGameButton = new Button("New Game");
        newGameButton.setPrefSize(OptionsModel.getSceneWidth()/3, OptionsModel.getSceneHeight()*0.75/9);
        newGameButton.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        newGameButton.setOnAction(event -> SceneManager.getInstance().switchToGameView());

        deathScreen = new VBox(deathMsg, deathScore, spacing, newGameButton, mainMenuButton, exitButton);
        deathScreen.setPrefSize(sceneWidth, sceneHeight);
        deathScreen.setStyle("-fx-background-color: rgba(0, 0, 0, 0.55);");
        deathScreen.setVisible(false);
        deathScreen.setAlignment(Pos.CENTER);

        getChildren().add(deathScreen);
    }

    public void deathScreenShow() {
        deathScreen.setVisible(true);
        SceneManager.getInstance().playMusic("/resources/deathSong.mp3");
    }

    public void initializeUpgradeScreen(double sceneWidth, double sceneHeight) {
        Text upgradeMsg = new Text("Choose an upgrade");
        upgradeMsg.setStyle("-fx-font-size: 36px; -fx-fill: white;");

        commonList = Model.GenerateCards.generateCommonCards();
        rareList = Model.GenerateCards.generateRareCards();
        legendaryList = Model.GenerateCards.generateLegendaryCards();

        Card upgradeButton1 = initializeUpgradeButton();
        Card upgradeButton2 = initializeUpgradeButton();
        Card upgradeButton3 = initializeUpgradeButton();

        upgradeScreen = new HBox(upgradeButton1, upgradeButton2, upgradeButton3);
        upgradeScreen.setPrefSize(sceneWidth, sceneHeight);
        upgradeScreen.setStyle("-fx-background-color: rgba(0, 0, 0, 0.55);");
        upgradeScreen.setVisible(false);
        upgradeScreen.setAlignment(Pos.CENTER);
        
        upgradeTitle = new VBox(upgradeMsg);
        upgradeTitle.setPrefSize(sceneWidth, sceneHeight/3);
        upgradeTitle.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        upgradeTitle.setVisible(false);
        upgradeTitle.setAlignment(Pos.CENTER);    
        
    }

    public Card initializeUpgradeButton() {
        Card button = Model.Upgrade.getUpgrade(commonList, rareList, legendaryList);
        button.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        if (commonList.contains(button)) {
            button.setStyle("-fx-text-fill:"+commonTextColor);
            commonList.remove(button);
        }
        else if (rareList.contains(button)) {
            button.setStyle("-fx-text-fill:"+rareTextColor);
            rareList.remove(button);
        }
        else if (legendaryList.contains(button)) {
            button.setStyle("-fx-text-fill:"+legendaryTextColor);
            legendaryList.remove(button);
        } 
        button.setPrefSize(OptionsModel.getSceneWidth()/3, OptionsModel.getSceneHeight()/2);
        button.setText(button.getName());
        button.setOnAction(event -> {
            button.applyUpgrades(ball, platform, player);
            SceneManager.getInstance().levelUp();
        });

        return button;
    }

    public void upgradeScreenShow() {
        upgradeScreen.setVisible(true);
        upgradeTitle.setVisible(true);
    }

    public void newLevel() {
        player.levelUp();
        ball.levelUp();
        upgradeScreen.setVisible(false);
        upgradeTitle.setVisible(false);

        if (killIfDead()) {
            return;
        }

        updateHUD();
        blockList = Model.GenerateBlocks.generateBlocks(player.getLevel());
        for (int i = 0; i < blockList.size(); i++){
            getChildren().add(blockList.get(i));
        }

        platform.reset();
        ball.reset(platform);
    }

    public void lifeLost(){
        platform.reset();
        ball.reset(platform);
    }
    
    public boolean killIfDead() {
        if (getPlayer().getLives() <= 0) {
            initializeDeathScreen(getWidth(), getHeight());
            deathScreenShow();
            return true;
        }
        return false;
    }

    public Platform getPlatform() { return platform; }
    public Ball getBall() { return ball; }
    public ArrayList<Block> getBlockList() { return blockList; }
    public Player getPlayer() { return player; }

    public ArrayList<Card> getCommonList() { return commonList; }
    public ArrayList<Card> getRareList() { return rareList; }
    public ArrayList<Card> getLegendaryList() { return legendaryList; }

    public VBox getUpgradeTitle() { return upgradeTitle; }
    public HBox getUpgradeScreen() { return upgradeScreen; }
    public double getHudHeight() { return HUDBackground.getHeight(); }
    public TextField getTextField() { return nameField; }
}
