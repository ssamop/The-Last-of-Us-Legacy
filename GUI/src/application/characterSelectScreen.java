package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import engine.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.characters.Fighter;
import model.characters.Hero;

public class characterSelectScreen {
	
	public static HBox characters=new HBox(10);

	public static Hero selectedHero;
    
	public static void display() throws IOException {
		Stage window=new Stage();
		window.initStyle(StageStyle.UNDECORATED);
		VBox layout=new VBox(10);
		characters.setAlignment(Pos.CENTER);
		Text selectCharacterText=new Text("CHOOSE YOUR HERO");
		setHeroButtons(window);
		
		Font font = Font.loadFont(characterSelectScreen.class.getResourceAsStream("/application/Pixeboy-z8XGD.ttf"), 24);
		selectCharacterText.setFont(Font.font("Pixeboy", FontWeight.BOLD, 36));
		selectCharacterText.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: transparent;");
		
		Image backgroundImage=new Image(characterSelectScreen.class.getResourceAsStream("pixelbackground.gif"));

        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        layout.setBackground(new Background(backgroundImg));
		layout.getChildren().addAll(selectCharacterText,characters);
		layout.setAlignment(Pos.CENTER);
		Scene scene=new Scene(layout,1200,700);
		window.setScene(scene);
		window.show();

		
	}
	
	private static Hero setHeroButtons(Stage window) throws IOException {
		Game.loadHeroes("csv/Heros.csv");
		for(int i=0;i<Game.availableHeroes.size();i++) {
			Hero h=Game.availableHeroes.get(i);
			
			ImageView imageView = new ImageView(getHeroImage(h));
			Button button=new Button(h.getName());
			button.setGraphic(imageView);
			button.setContentDisplay(button.getContentDisplay().TOP);
			
			Font font = Font.loadFont(characterSelectScreen.class.getResourceAsStream("/application/Pixeboy-z8XGD.ttf"), 24);
			
			button.setTooltip(new Tooltip("Type:"+h.getClass().getSimpleName()+"\n" + "Health:" + h.getMaxHp() + "\n" + "actions:" + h.getMaxActions()
            + "\n" + "attack Damage:" + h.getAttackDmg()));
			
			button.setFont(font);
			button.setTextFill(Color.BLACK);
			button.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: transparent;");
			
			characters.getChildren().add(button);
			button.setOnAction(e->{
				Game.startGame(h);
				selectedHero=h;

				GamePlayScreen.Display();
				window.close();
			});

			
		}
		return selectedHero;
	}
	
	public static Image getHeroImage(Hero hero) {
	    String imagePath;
	    switch(hero.getName()) {
	    case "Joel Miller":
            imagePath = "./src/application/joelremovebg.png";
            break;
        case "Ellie Williams":
            imagePath = "./src/application/ellieremovebg.png";
            break;
        case "Tess":
            imagePath = "./src/application/tessremovebg.png";
            break;
        case "Riley Abel":
            imagePath = "./src/application/rileyremovebg.png";
            break;
        case "Tommy Miller":
            imagePath = "./src/application/tommyremovebg.png";
            break;
        case "Bill":
            imagePath = "./src/application/billremovebg.png";
            break;
        case "David":
            imagePath = "./src/application/davidremovebg.png";
            break;
        case "Henry Burell":
            imagePath = "./src/application/henryremovebg.png";
            break;
	        default:
	            // Set a default image path if the hero's name is not recognized
	            imagePath = "./src/application/joelremovebg.png";
	            break;
	    }
	    try {
	        InputStream stream = new FileInputStream(new File(imagePath));
	        return new Image(stream);
	    } catch (Exception e) {
	        // Handle any exceptions that occur while loading the image
	        e.printStackTrace();
	        return null;
	    }
	}
}
