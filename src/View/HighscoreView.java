//this file controls how the highscore menu looks
//written by Simon
package View;

import Model.*;
import View.Buttons.BackButton;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HighscoreView extends Pane{

    public HighscoreView() {

        BackButton backButton = new BackButton();

        Image image = new Image("/resources/HighscoreBackground.png");
        ImageView bg = new ImageView(image);
        bg.setFitWidth(OptionsModel.getSceneWidth());
        bg.setFitHeight(OptionsModel.getSceneHeight());

        Highscore.readHighscore();
        String[] Highscores = Highscore.getHighscore();
        Highscores = Highscore.arrayRankArrange(Highscores);

        Pane pane = new Pane();
        double paneWidth = OptionsModel.getSceneWidth()/4;
        double paneHeight = OptionsModel.getSceneHeight()/2;
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); "
        + "-fx-border-color:rgb(117, 117, 117); "
        + "-fx-border-width: 4; "
        + "-fx-border-radius: 10; "
        + "-fx-background-radius: 10; ");
        pane.setPrefSize(paneWidth, paneHeight);
        pane.setLayoutX(OptionsModel.getSceneWidth()/2-paneWidth/2);
        pane.setLayoutY(OptionsModel.getSceneHeight()/2.5);

        //initializing the vbox containing ranks
        VBox highscoreBox1 = new VBox();
        highscoreBox1.getStyleClass().add("vbox");
        highscoreBox1.setSpacing(paneHeight/200);
        highscoreBox1.setLayoutX(paneWidth/8);
        highscoreBox1.setLayoutY(pane.getHeight());

        Label headRank=new Label("RANK");
        headRank.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
        headRank.setMinHeight(paneHeight/12);
        headRank.setMaxHeight(paneHeight/12);;
        highscoreBox1.getChildren().add(headRank);

        for (int i = 1; i < Math.min(11, Highscores.length+1); i++){
            String rank = String.valueOf(i);
            Label labelrank = new Label(rank);
            labelrank.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
            labelrank.setMinHeight(paneHeight/12);
            labelrank.setMaxHeight(paneHeight/12);
            highscoreBox1.getChildren().add(labelrank);
        }
        

        //initializing the vbox containing names
        VBox highscoreBox2 = new VBox();
        highscoreBox2.getStyleClass().add("vbox");
        highscoreBox2.setSpacing(paneHeight/200);
        highscoreBox2.setLayoutX(paneWidth/8+paneWidth/4);
        highscoreBox2.setLayoutY(pane.getHeight());

        Label headName =new Label("Name");
        headName.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
        headName.setMinHeight(paneHeight/12);
        headName.setMaxHeight(paneHeight/12);
        highscoreBox2.getChildren().add(headName);
            
        for (int i = 0; i < Math.min(10, Highscores.length); i++){
            String names[] = Highscores[i].split("=");
            String name = names[0];
            Label labelname = new Label(name);
            labelname.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
            labelname.setMinHeight(paneHeight/12);
            labelname.setMaxHeight(paneHeight/12);
            highscoreBox2.getChildren().addAll(labelname);
        }
        
        
        //initializing the vbox containing highscores
        VBox highscoreBox3 = new VBox();
        highscoreBox3.getStyleClass().add("vbox");
        highscoreBox3.setSpacing(paneHeight/200);
        highscoreBox3.setLayoutX(paneWidth/8+paneWidth/2);
        highscoreBox3.setLayoutY(pane.getHeight());

        Label headScore = new Label("Score");
        headScore.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; ");
        headScore.setMinHeight(paneHeight/12);
        headScore.setMaxHeight(paneHeight/12);
        highscoreBox3.getChildren().addAll(headScore);

        for (int i = 0; i < Math.min(10, Highscores.length); i++){
            String scores[] = Highscores[i].split("=");
            String score = scores[1].trim();
            Label labelscore = new Label(score);
            //If the score is very big we make the pane wider to make it fit.
            //We still want it to possible to go out of the pane if the number is big enough because that is awesome
            if(Long.valueOf(score) > 99999999){
                pane.setPrefSize(paneWidth+paneWidth/8, paneHeight);
                pane.setLayoutX((OptionsModel.getSceneWidth()/2-paneWidth/2)-(paneWidth/8)/2);
                pane.setLayoutY(OptionsModel.getSceneHeight()/2.5);
            }
            labelscore.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
            labelscore.setMinHeight(paneHeight/12);
            labelscore.setMaxHeight(paneHeight/12);
            highscoreBox3.getChildren().addAll(labelscore);
        }

        pane.getChildren().addAll(highscoreBox1,highscoreBox2,highscoreBox3);
        getChildren().addAll(bg, backButton,pane);
    }
    public void VBoxLabel(int number, String label){

    }
}
