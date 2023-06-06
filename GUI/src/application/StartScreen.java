package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.characters.Fighter;
import model.characters.Hero;

import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import engine.Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import application.GamePlayScreen;






public class StartScreen extends Application {
	 private MediaPlayer mediaPlayer;

    
	public void start(Stage primaryStage) throws Exception{
		Game.loadHeroes("csv/Heros.csv");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		//start screen
		StackPane startScreen=new StackPane();
		VBox root=new VBox(30);
        root.setAlignment(Pos.TOP_CENTER);


		Button startButton=new Button("START GAME");

		startButton.setOnAction(e -> {
		    try {
		        characterSelectScreen.display();
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		    primaryStage.close();
		});

		Font font = Font.loadFont(getClass().getResourceAsStream("/application/Pixeboy-z8XGD.ttf"), 36);
		//startButton.setFont(font);
		//startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2); -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Pixeboy Regular';");
		
		startButton.setFont(font);

	     // Set the fill and stroke colors of the button text
	    startButton.setTextFill(Color.BLACK);
	    startButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: transparent;");
		
		
		Button exitButton=new Button("EXIT");
		
		exitButton.setFont(font);
		exitButton.setTextFill(Color.BLACK);
		exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: transparent;");
		
		exitButton.setOnAction(e->primaryStage.close());
		//exitButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2);-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");

		Image logo=new Image(getClass().getResourceAsStream("logo.png"));
		ImageView logopng=new ImageView(logo);
		logopng.setFitWidth(400);
		logopng.setFitHeight(400);
		Image backgroundImage=new Image(getClass().getResourceAsStream("pixelbackground.gif"));

        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        
        /*String musicFile = "./src/application/The Last Of us - Theme song.mp3";
	    Media sound = new Media(new File(musicFile).toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(sound);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.setVolume(0.50);
	    mediaPlayer.play();*/
	    
	    
        startScreen.getChildren().add(root);
        Scene startScene=new Scene(startScreen,1200,700);
        root.setBackground(new Background(backgroundImg));
        
 
		root.getChildren().addAll(logopng,startButton,exitButton);
		primaryStage.setScene(startScene);
		primaryStage.show();


	}



	public static void main(String[] args) {
		
		launch(args);
	}

}
