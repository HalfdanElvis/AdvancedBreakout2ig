package View;

import Model.Buttons.BackButton;
import Model.OptionsModel;
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



        musicSlider.setPrefWidth(sceneWidth*0.2);
        musicSlider.setLayoutX(sceneWidth*0.2);
        musicSlider.setLayoutY(sceneHeight*0.45);
        musicSlider.setShowTickLabels(true);
        musicSlider.setMajorTickUnit(0.2);
        musicSlider.setShowTickMarks(true);
        musicSlider.setSnapToTicks(true);

        Label musicLabel = new Label("Music Volume");
        musicLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white; "
                   + "-fx-background-color: rgba(0, 0, 0, 0.8); "   
                   + "-fx-background-radius: 2px;");
        musicLabel.setLayoutX(sceneWidth * 0.2);
        musicLabel.setLayoutY(sceneHeight * 0.4); 

        musicSlider.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());


        soundSlider.setPrefWidth(sceneWidth*0.2);
        soundSlider.setLayoutX(sceneWidth*0.6);
        soundSlider.setLayoutY(sceneHeight*0.45);
        soundSlider.setShowTickLabels(true);
        soundSlider.setMajorTickUnit(0.2);
        soundSlider.setShowTickMarks(true);
        soundSlider.setSnapToTicks(true);

        Label soundLabel = new Label("Sound Volume");
        soundLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white; "
                   + "-fx-background-color: rgba(0, 0, 0, 0.8); "   
                   + "-fx-background-radius: 2px;");
        soundLabel.setLayoutX(sceneWidth * 0.6);
        soundLabel.setLayoutY(sceneHeight * 0.4); 

        soundSlider.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        
        getChildren().addAll(bg, backButton, musicSlider, musicLabel, soundSlider, soundLabel);
    }

    public static double getMusicVolume() {
        return musicSlider.getValue();
    }

    public static double getSoundVolume() {
        return soundSlider.getValue();
    }
}
