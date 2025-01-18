package View;

import Model.*;
import Model.Buttons.BackButton;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HighscoreView extends Pane{

    // SKAL METHODS I SEDRISÆST

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
        int paneWidth = 350;
        int paneHeight = 450;
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); "
        + "-fx-border-color:rgb(117, 117, 117); "
        + "-fx-border-width: 4; "
        + "-fx-border-radius: 10; "
        + "-fx-background-radius: 10; ");
        pane.setPrefSize(paneWidth, paneHeight);
        pane.setLayoutX(OptionsModel.getSceneWidth()/2-paneWidth/2);
        pane.setLayoutY(OptionsModel.getSceneHeight()/2.5);
        //getStylesheets().add(getClass().getResource("/resources/stylesVBox.css").toExternalForm());
        VBox highscoreBox1 = new VBox();
        highscoreBox1.getStyleClass().add("vbox");
        if (Highscores.length<=10 || Highscores.length>10){
            highscoreBox1.setSpacing(10); // Afstand mellem elementer
            highscoreBox1.setLayoutX(paneWidth/8); // Placering på skærmen (x-koordinat)
            highscoreBox1.setLayoutY(pane.getHeight()); // Placering på skærmen (y-koordinat)

            Label headRank=new Label("RANK");
            headRank.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
            headRank.setMinHeight(30);
            headRank.setMaxHeight(30);;
            highscoreBox1.getChildren().add(headRank);

            for (int i=1; i<Math.min(11,Highscores.length+1);i++){
                String rank = String.valueOf(i);
                Label labelrank = new Label(rank);
                labelrank.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
                labelrank.setMinHeight(30);
                labelrank.setMaxHeight(30);
                highscoreBox1.getChildren().add(labelrank);
            }
        }
        VBox highscoreBox2 = new VBox();
        highscoreBox2.getStyleClass().add("vbox");
        if(Highscores.length<=10 || Highscores.length>10){
            highscoreBox2.setSpacing(10);
            highscoreBox2.setLayoutX(paneWidth/2.8); // Placering på skærmen (x-koordinat)
            highscoreBox2.setLayoutY(pane.getHeight()); // Placering på skærmen (y-koordinat)
            Label headName =new Label("Name");
            headName.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
            headName.setMinHeight(30);
            headName.setMaxHeight(30);
            highscoreBox2.getChildren().add(headName);
            
            for (int i=0; i<Math.min(10,Highscores.length);i++){
                String names[] = Highscores[i].split("=");
                String name = names[0];
                Label labelname = new Label(name);
                labelname.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
                labelname.setMinHeight(30);
                labelname.setMaxHeight(30);
                highscoreBox2.getChildren().addAll(labelname);
            }
        }
        
        VBox highscoreBox3 = new VBox();
        highscoreBox3.getStyleClass().add("vbox");
        if(Highscores.length<=10 || Highscores.length>10){
            highscoreBox3.setSpacing(10);
            highscoreBox3.setLayoutX(paneWidth/8+1.5*paneWidth/3); // Placering på skærmen (x-koordinat)
            highscoreBox3.setLayoutY(pane.getHeight()); // Placering på skærmen (y-koordinat)
            Label headScore = new Label("Score");
            headScore.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
            headScore.setMinHeight(30);
            headScore.setMaxHeight(30);
            highscoreBox3.getChildren().addAll(headScore);

            for (int i=0; i<Math.min(10,Highscores.length);i++){
                String scores[] = Highscores[i].split("=");
                String score = scores[1].trim();
                Label labelscore = new Label(score);
                if(Long.valueOf(score)>99999999){

                    pane.setPrefSize(paneWidth+100, paneHeight);
                    pane.setLayoutX((OptionsModel.getSceneWidth()/2-paneWidth/2)-100/2);
                    pane.setLayoutY(OptionsModel.getSceneHeight()/4);
                }
                labelscore.setStyle("-fx-font-size: 24px; -fx-text-fill: white;-fx-font-family: monospace; "); // Styling
                labelscore.setMinHeight(30);
                labelscore.setMaxHeight(30);
                highscoreBox3.getChildren().addAll(labelscore);
            }
        }

        pane.getChildren().addAll(highscoreBox1,highscoreBox2,highscoreBox3);
        getChildren().addAll(bg, backButton,pane);
    }
}
