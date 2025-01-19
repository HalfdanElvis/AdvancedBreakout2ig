//this file controls how the options menu look
//it also allows the scenemanger to get the current sound/music volume
//written by Halfdan & Marcus

package View;

import Model.OptionsModel;
import View.Buttons.BackButton;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class OptionsView extends Pane{
    static Slider musicSlider = new Slider(0, 1, OptionsModel.getMusicVolume());
    static Slider soundSlider = new Slider(0, 1, OptionsModel.getSoundVolume());
    static double sceneWidth = OptionsModel.getSceneWidth();
    static double sceneHeight = OptionsModel.getSceneHeight();
    public OptionsView() {

        BackButton backButton = new BackButton();

        Image image = new Image("/resources/OptionsBackground.png");
        ImageView bg = new ImageView(image);
        bg.setFitWidth(sceneWidth);
        bg.setFitHeight(sceneHeight);

        Label musicLabel = new Label("Music Volume");
        initializeSliders(musicSlider, musicLabel, sceneWidth*0.2);
        musicSlider.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());

        Label soundLabel = new Label("Sound Volume");
        initializeSliders(soundSlider, soundLabel, sceneWidth*0.6);
        soundSlider.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        
        getChildren().addAll(bg, backButton, musicSlider, musicLabel, soundSlider, soundLabel);
    }

    public static void initializeSliders(Slider slider, Label label, double x) {
        slider.setPrefWidth(sceneWidth*0.2);
        slider.setLayoutX(x);
        slider.setLayoutY(sceneHeight*0.45);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(0.2);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: white; "
        + "-fx-background-color: rgba(0, 0, 0, 0.8); "   
        + "-fx-background-radius: 2px;");
        label.setLayoutX(x);
        label.setLayoutY(sceneHeight * 0.4); 
    }

    public static double getMusicVolume() { return musicSlider.getValue(); }
    public static double getSoundVolume() { return soundSlider.getValue(); }
}
