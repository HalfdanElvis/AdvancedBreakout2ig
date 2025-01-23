//this files manages the timeline while the game is running, checks if the player has died
//and creates a new level. It also takes input from the player when the game is running
//and decides what to do with the input

package Controller;

import Model.*;
import View.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
public class GameController {
    private GameView gameView;
    private Scene scene;
    static Timeline gameplay;
    private boolean gameRunning = false;
    private Ball ball;

    public GameController(GameView gameView, Scene scene) {
        this.gameView = gameView;
        this.scene = scene;
        gameView.initializeNameScreen();
        ball = gameView.getBall();
        setupControls();
        startGameplay();
    }

    public void startGameplay() {
        gameplay = new Timeline(new KeyFrame(Duration.millis(8), e -> {
            update();
        }));
        gameplay.setCycleCount(Timeline.INDEFINITE);
    }

    public void update() {

        //Checks if ball is out of bounds (dead)
        ballOutofBounds();

        // checks for levelup
        if (gameView.getBlockList().size() == 0) {
            gameView.initializeUpgradeScreen(OptionsModel.getSceneWidth(), OptionsModel.getSceneHeight());
            gameView.getChildren().add(gameView.getUpgradeScreen());
            gameView.getChildren().add(gameView.getUpgradeTitle());
            gameView.upgradeScreenShow();
            gameplay.pause();
        } else {

            // Updates ball and platform position
            ball.updatePosition();
            gameView.getPlatform().updatePosition();

            // Checks balls collisions
            CollisionManager.checkBorderCollision(ball, gameView);
            CollisionManager.blockCollision(ball, gameView.getBlockList(), gameView);
            CollisionManager.collisionWithPlatform(ball, gameView.getPlatform());

            gameView.updateHUD();

            // Increases ball speed
            ball.setVelocity(ball.getVelocity()+(ball.getVelocity()*0.0001));
        }
    }

 public void ballOutofBounds() {
        // checks for death
        if (ball.getY() - ball.getRadius()*2 > OptionsModel.getSceneHeight()){
                    
            // Decreases Player Life
            gameView.getPlayer().setLives(gameView.getPlayer().getLives()-1);
            gameView.updateHUD();

            // Checks Player life to see if game continues
            if (gameView.killIfDead()) {
            } else {
                gameView.lifeLost();
            }

            // Stops the game until Space is pressed.
            gameplay.stop();
            gameRunning = false;
        }
    }

    public void newLevel() {
        gameView.setupObjects(OptionsModel.getSceneWidth(), OptionsModel.getSceneHeight());
        gameplay.play();
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public void setupControls() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                leftPressed();
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                rightPressed();
            } else if (event.getCode() == KeyCode.SPACE) {
                spacePressed();
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                leftReleased();
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                rightReleased();
            }
        });
    }

    // KeyPresses
    public void leftPressed() { gameView.getPlatform().setMovingLeft(true); }
    public void rightPressed() { gameView.getPlatform().setMovingRight(true); }
    public void leftReleased() { gameView.getPlatform().setMovingLeft(false); }
    public void rightReleased() { gameView.getPlatform().setMovingRight(false); }
    
    public void spacePressed() {
        if (!gameRunning) {
            gameplay.play();  
            gameRunning = true;
        }
    }
}
