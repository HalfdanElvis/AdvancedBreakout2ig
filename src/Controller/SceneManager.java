//this files creates and switches between scenes (menus) based on user input and controls the music

package Controller;

import Model.OptionsModel;
import View.*;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SceneManager {

    private static SceneManager instance;
    private Stage stage;
    private Scene currentScene;

    // Sounds
    private MediaPlayer musicPlayer;
    private String musicFilePath;
    private AudioClip hit = new AudioClip(getClass().getResource("/resources/hit.wav").toExternalForm());
    private AudioClip blockBreak = new AudioClip(getClass().getResource("/resources/blockBreak.wav").toExternalForm());
    private AudioClip buttonPress = new AudioClip(getClass().getResource("/resources/buttonPress.wav").toExternalForm());
    
    

    // Views
    private MainMenuView mainMenuView;
    private GameView gameView;
    private OptionsView optionsView;
    private HighscoreView highscoreView;

    // Controllers
    private GameController gameController;

    private SceneManager(Stage stage) {
        this.stage = stage;
    }

    // Singleton instance
    public static void initializeInstance(Stage stage) {
        if (instance == null) {
            instance = new SceneManager(stage);
        }
    }
    public static SceneManager getInstance() {
        return instance;
    }

    // Start Game
    public void switchToGameView() {
        gameView = new GameView();
        currentScene = new Scene(gameView, OptionsModel.getSceneWidth(), OptionsModel.getSceneHeight());
        gameController = new GameController(gameView, currentScene);  
        playMusic("/resources/Judas.mp3");
        stage.setScene(currentScene);
        stage.show();
    }

    // Increases Level in Game
    public void levelUp() {
        gameView.newLevel();
        gameController.setGameRunning(false);
    }

    // Switch to Main Menu
    public void switchToMainMenuView() {
        updateVolume();
        mainMenuView = new MainMenuView();
        currentScene = new Scene(mainMenuView, OptionsModel.getSceneWidth(), OptionsModel.getSceneHeight());
        playMusic("/resources/mainMenuSong.mp3");
        stage.setScene(currentScene);
        stage.show();
    }

    // Switch to Options
    public void switchToOptionsView() {
        optionsView = new OptionsView();
        currentScene = new Scene(optionsView, OptionsModel.getSceneWidth(), OptionsModel.getSceneHeight());
        stage.setScene(currentScene);
        stage.show();
    }

    // Switch to HighscoreView
    public void switchToHighscoreView() {
        highscoreView = new HighscoreView();
        currentScene = new Scene(highscoreView, OptionsModel.getSceneWidth(), OptionsModel.getSceneHeight());
        stage.setScene(currentScene);
        stage.show();
    }

    // Backgroundmusic Player
    public void playMusic(String musicFilePath) {

        if (musicPlayer == null){
            this.musicFilePath = musicFilePath;
            Media media = new Media(getClass().getResource(musicFilePath).toExternalForm());
            musicPlayer = new MediaPlayer(media);
            musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            musicPlayer.setVolume(OptionsModel.getMusicVolume());
            musicPlayer.play();
        }
        
        else {
            if (!(this.musicFilePath.equals(musicFilePath))) {
                this.musicFilePath = musicFilePath;
                stopMusic();
                Media media = new Media(getClass().getResource(musicFilePath).toExternalForm());
                musicPlayer = new MediaPlayer(media);
                musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                musicPlayer.setVolume(OptionsModel.getMusicVolume());
                musicPlayer.play();
            }
        }
    }

    public void stopMusic() {
        if (musicPlayer != null) {
            musicPlayer.stop();
        }
    }

    // Volume Control
    public void updateVolume() {
        OptionsModel.setMusicVolume(OptionsView.getMusicVolume());
        OptionsModel.setSoundVolume(OptionsView.getSoundVolume());
        if (musicPlayer != null){
            musicPlayer.setVolume(OptionsModel.getMusicVolume());
        }
        try {
            OptionsModel.updateOptions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        hit.setVolume(OptionsModel.getSoundVolume());
        blockBreak.setVolume(OptionsModel.getSoundVolume());
        buttonPress.setVolume(OptionsModel.getSoundVolume());
    }

    // SFX
    public void playHitSFX() {
        hit.play();
    }

    public void playBlockBreakSFX(){
        blockBreak.play();
    }

    public void playbuttonPressSFX(){
        buttonPress.play();
    }

    //button presses
    public static void backButtonPressed() {
        getInstance().playbuttonPressSFX();
        getInstance().switchToMainMenuView();
    }

    public static void exitButtonPressed() {
        getInstance().playbuttonPressSFX();
        System.exit(0);
    }

    public static void highscoreButtonPressed() {
        getInstance().playbuttonPressSFX();
        getInstance().switchToHighscoreView();
    }

    public static void optionsButtonPressed() {
        getInstance().playbuttonPressSFX();
        getInstance().switchToOptionsView();
    }

    public static void startButtonPressed() {
        getInstance().playbuttonPressSFX();
        getInstance().switchToGameView();
    }
    
}
