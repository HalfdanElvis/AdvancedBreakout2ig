package Model.Buttons;

import Main.SceneManager;
import Model.OptionsModel;
import javafx.scene.control.Button;


public class StartButton extends Button{
    private double buttonX;
    private double buttonY;
    private double buttonWidth;
    private double buttonHeight;
    
    public StartButton() {
        buttonX = OptionsModel.getSceneWidth()/3;
        buttonY = OptionsModel.getSceneHeight()*0.55;
        buttonWidth = OptionsModel.getSceneWidth()/3;
        buttonHeight = OptionsModel.getSceneHeight()*0.75/9;
        setText("Start");
        getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        setPrefSize(buttonWidth, buttonHeight);
        setLayoutX(buttonX);
        setLayoutY(buttonY);
        setOnAction(event -> {
            SceneManager.getInstance().playbuttonPressSFX();
            SceneManager.getInstance().switchToGameView();
        });
    }
}