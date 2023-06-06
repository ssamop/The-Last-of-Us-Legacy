package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class controlScreen {
	public static void display() {
		Stage window=new Stage();
		Label messageLabel=new Label("Controls:"+"\n"+"W-A-S-D    move up, down, left and right"+"\n"+"click on buttons on right pane for actions");
		messageLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
		Button ok=new Button("ok");
		ok.setStyle("-fx-background-color: transparent;-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
		ok.setOnAction(e->window.close());
		VBox layout=new VBox(10);
		layout.setAlignment(Pos.CENTER);
		BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,null);
		Background background = new Background(backgroundFill);
		layout.setBackground(background);
		layout.getChildren().addAll(messageLabel,ok);
		Scene scene=new Scene(layout,400,400);


		window.setScene(scene);
		window.show();
		
	}
}
