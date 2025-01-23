//this file controls how the main menu looks

package View;

import Model.OptionsModel;
import View.Buttons.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainMenuView extends Pane{
    private OptionsButton optionsButton;
    private StartButton startButton;
    private HighscoreButton highscoreButton;
    private ExitButton exitButton;

    public MainMenuView() {

        // Create a buttons
        startButton = new StartButton();
        optionsButton = new OptionsButton();
        highscoreButton = new HighscoreButton();
        exitButton = new ExitButton();

        Image image = new Image("/resources/MainMenuBackground.png");
        ImageView bg = new ImageView(image);
        bg.setFitWidth(OptionsModel.getSceneWidth());
        bg.setFitHeight(OptionsModel.getSceneHeight());

        getChildren().addAll(bg, startButton, highscoreButton, optionsButton, exitButton);
    }
}
