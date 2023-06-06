package application;

import java.awt.Insets;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class popUpWindows {
	public static void display(String message) {
		Stage window=new Stage();
		window.initStyle(StageStyle.UNDECORATED);
		Label messageLabel=new Label(message);
		messageLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
		Button ok=new Button("ok");
		ok.setStyle("-fx-background-color: transparent;-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
		ok.setOnAction(e->window.close());
		VBox layout=new VBox(10);
		layout.setAlignment(Pos.CENTER);
		BackgroundFill backgroundFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY,null);
		Background background = new Background(backgroundFill);
		layout.setBackground(background);
		layout.getChildren().addAll(messageLabel,ok);
		Scene scene=new Scene(layout,550,100);


		window.setScene(scene);
		window.show();
		
	}
}
