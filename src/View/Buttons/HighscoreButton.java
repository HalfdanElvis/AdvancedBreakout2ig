//this file controls how the highscore button looks
//written by Halfdan
package View.Buttons;

import Controller.SceneManager;
import Model.OptionsModel;
import javafx.scene.control.Button;

public class HighscoreButton extends Button{
    private double buttonX;
    private double buttonY;
    private double buttonWidth;
    private double buttonHeight;

    public HighscoreButton() {
        buttonX = OptionsModel.getSceneWidth()/3;
        buttonY = OptionsModel.getSceneHeight()*0.7;
        buttonWidth = OptionsModel.getSceneWidth()/3;
        buttonHeight = OptionsModel.getSceneHeight()*0.75/9;
        setText("Highscore");
        getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        setPrefSize(buttonWidth, buttonHeight);
        setLayoutX(buttonX);
        setLayoutY(buttonY);
        setOnAction(event -> {
            SceneManager.highscoreButtonPressed();
        });
    }
}
