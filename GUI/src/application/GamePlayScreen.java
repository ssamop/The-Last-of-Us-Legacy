package application;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.characters.Direction;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;


public class GamePlayScreen {
	public static Hero selectedHero=characterSelectScreen.selectedHero;
	public static BorderPane gameScreenLayout=new BorderPane();
	public static GridPane grid=new GridPane();
	public static VBox stats=new VBox(10);
	public static VBox heroesList=new VBox(10);
	public static HBox menu=new HBox(10);
	public static Scene scene=new Scene(gameScreenLayout,1300,800);
	public static Image hero;
	//public static Font font;
	
	 private MediaPlayer mediaPlayer;

	private static final int GRID_SIZE = 15;
    private static final int CELL_SIZE = 45;
    
    
    
	public static void Display() {
		//Button exit=new Button("exit");
		
		// Load the icon image
		Image iconImage = new Image(GamePlayScreen.class.getResourceAsStream("exitwhiteremovebg.png"));

		// Create the exit button
		Button exit = new Button();
		exit.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
		exit.setGraphic(new ImageView(iconImage));

		// Set up the button action to exit the application
		exit.setOnAction(e -> Platform.exit());
		
		Image backgroundImage=new Image(GamePlayScreen.class.getResourceAsStream("pixel.jpg"));

	        BackgroundImage backgroundImg = new BackgroundImage(
	                backgroundImage,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundPosition.DEFAULT,
	                BackgroundSize.DEFAULT
	        );
	        gameScreenLayout.setBackground(new Background(backgroundImg));
		Stage window=new Stage();
		window.initStyle(StageStyle.UNDECORATED);
		exit.setOnAction(e->window.close());

		Image controlImage = new Image(GamePlayScreen.class.getResourceAsStream("controls2removebg.png"));
		Button controls=new Button();
		controls.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
		controls.setGraphic(new ImageView(controlImage));
		controls.setOnAction(e->controlScreen.display());
		
		//menu.getChildren().addAll(controls,exit);
		//menu.setAlignment(Pos.CENTER_RIGHT);
		//gameScreenLayout.setTop(menu);
		//generateGrid();
		
		String musicFile = "./src/application/The Last of Us - Relaxing Music Compilation.mp3";
	    Media sound = new Media(new File(musicFile).toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(sound);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.setVolume(0.50);
	    mediaPlayer.play();
	    
	    // Create the mute button
	   // ToggleButton muteButton = new ToggleButton("Mute");
	    //muteButton.setOnAction(event -> {
	     //  boolean isMuted = muteButton.isSelected();
	       // mediaPlayer.setMute(isMuted);
	   // });
	    
	    // Load the mute and unmute icon images
	    Image muteImage = new Image(GamePlayScreen.class.getResourceAsStream("mutere.png"));
	    Image unmuteImage = new Image(GamePlayScreen.class.getResourceAsStream("soundre.png"));

	    // Create the mute button
	    ToggleButton muteButton = new ToggleButton();
	    muteButton.setGraphic(new ImageView(muteImage));
	    muteButton.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");

	    // Set up the button action to toggle between mute and unmute
	    muteButton.setOnAction(e -> {
	        boolean isMuted = muteButton.isSelected();
	        if (isMuted) {
	            muteButton.setGraphic(new ImageView(unmuteImage));
	            mediaPlayer.setMute(true);
	            // Code to mute the audio goes here
	        } else {
	            muteButton.setGraphic(new ImageView(muteImage));
	            mediaPlayer.setMute(false);
	            // Code to unmute the audio goes here
	        }
	    });

	    // Add the mute button to the pane
	    menu.getChildren().addAll(controls, muteButton,exit);
	    menu.setAlignment(Pos.CENTER_RIGHT);
	    gameScreenLayout.setTop(menu);
	    generateGrid();
	    
		//gameScreenLayout.setStyle("-fx-background-color: #000000;");
		System.out.println(selectedHero.getName());
		System.out.println(((CharacterCell) Game.map[0][0]).getCharacter().getName());

		updateMap();
		movementbuttons();
		//collectedsound();
		window.setScene(scene);
		window.show();
		
	}
	
	/*public static void collectedsound(Collectible collectible) {
	    String musicFile = "./src/application/collected.mp3";
	    try {
	        Media collectSound = new Media(new File(musicFile).toURI().toString());
	        MediaPlayer mediaPlayer = new MediaPlayer(collectSound);
	        mediaPlayer.setVolume(0.5);
	        
	        if (collectible instanceof Vaccine) {
	            mediaPlayer.play();
	            // play sound for vaccine collection
	        } else if (collectible instanceof Supply) {
	            mediaPlayer.play();
	            // play sound for supply collection
	        }
	    } catch (Exception e) {
	        // handle any exceptions that may occur during sound playback
	        e.printStackTrace();
	    }
	 // handle player movement and collectibles
	 // ...

	 if (Game.map[((Object) hero).getLoctaion())][player.getY()] instanceof Collectible) {
	     Collectible collectible = (Collectible) Game.map[player.getX()][player.getY()];
	     collectible.collect();
	     collectedsound(collectible);
	 }
	}*/
	
	 private static GridPane generateGrid() {

	        grid.setGridLinesVisible(false);
	        grid.setAlignment(Pos.CENTER);
	        gameScreenLayout.setCenter(grid);

	        Image cellimage = new Image(GamePlayScreen.class.getResourceAsStream("grass2.jpg"));
	        ImagePattern cellPattern = new ImagePattern(cellimage);

	        for (int row = 0; row < GRID_SIZE; row++) {
	            for (int col = 0; col < GRID_SIZE; col++) {
	            	int x=row;
	            	int y=col;
	                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
	                cell.setFill(cellPattern);
	                cell.setStroke(Color.BLACK);
	                cell.setOnMouseClicked(e -> {
	                    handleCellClick(x, y);   
	                });
	                grid.add(cell, row, col);
	            }
	        }

	        return grid;
	    }
	 
	 private static void handleCellClick(int row, int col) {
		    // Check if the clicked cell contains a CharacterCell
		    if (Game.map[row][col] instanceof CharacterCell) {
		        CharacterCell characterCell = (CharacterCell) Game.map[row][col];
		        selectedHero.setTarget(characterCell.getCharacter());
		        System.out.println(selectedHero.getTarget().getName());
		    }
		}
	 
	 public static void setHeroImage(Hero chero) {
         String heroName=chero.getName();
         switch(heroName) {
             case "Joel Miller":
                 hero=new Image(GamePlayScreen.class.getResourceAsStream("joel.jpg"));
             break;
             case "Ellie Williams":
                 hero=new Image(GamePlayScreen.class.getResourceAsStream("ellie.jpg"));
                 break;
             default:
             case "Bill":
                 hero=new Image(GamePlayScreen.class.getResourceAsStream("bill.jpg"));
                 break;
             case "Riley Abel":
                 hero=new Image(GamePlayScreen.class.getResourceAsStream("riley.jpg"));
                 break;
             case "Henry Burell":
                 hero=new Image(GamePlayScreen.class.getResourceAsStream("henry.jpg"));
                 break;
             case "David":
                 hero=new Image(GamePlayScreen.class.getResourceAsStream("david.jpg"));
                 break;
             case "Tommy Miller":
                 hero=new Image(GamePlayScreen.class.getResourceAsStream("tommy.jpg"));
                 break;
             case "Tess":
                 hero=new Image(GamePlayScreen.class.getResourceAsStream("tess.jpg"));
                 break;

         }
     }
	 
	 public static void setCurrentHerosButtons() { 
		 Font font = Font.loadFont(GamePlayScreen.class.getResourceAsStream("/application/Pixeboy-z8XGD.ttf"), 30);

		 for(int i=0;i<Game.heroes.size();i++) {
			 Hero h=Game.heroes.get(i);
			 VBox statsBox=new VBox();			 
			 Text name=new Text(h.getName());
			 
			 name.setFont(font);
			 name.setFont(Font.font("Pixeboy", FontWeight.BOLD, 20));
			 name.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
			 
				//name.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
				name.setFill(Color.WHITE);

				Text hp=new Text("HP:"+h.getCurrentHp());
				
				hp.setFont(font);
				hp.setFill(Color.WHITE);
				hp.setFont(Font.font("Pixeboy", FontWeight.BOLD, 15));
				hp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
				
				//hp.setStyle( "-fx-font-size: 12px; -fx-font-weight: bold;");
				//hp.setFill(Color.WHITE);
				
				Text actions=new Text("actions:"+h.getActionsAvailable());
				actions.setFill(Color.WHITE);
				actions.setFont(Font.font("Pixeboy", FontWeight.BOLD, 15));
				actions.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
				//actions.setStyle("-fx-text-fill: #f5f3ed; -fx-font-size: 12px; -fx-font-weight: bold;");
				
				Text supplyInventory=new Text("SUPPLIES COLLECTED:"+h.getSupplyInventory().size());
				supplyInventory.setFill(Color.WHITE);
				supplyInventory.setFont(Font.font("Pixeboy", FontWeight.BOLD, 18));
				supplyInventory.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
				
				//supplyInventory.setStyle("-fx-text-fill: #f5f3ed; -fx-font-size: 12px; -fx-font-weight: bold;");
				Text vaccineInventory=new Text("VACCINES COLLECTED:"+h.getVaccineInventory().size());
				vaccineInventory.setFill(Color.WHITE);
				vaccineInventory.setFont(Font.font("Pixeboy", FontWeight.BOLD, 18));
				vaccineInventory.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
				//vaccineInventory.setStyle("-fx-text-fill: #f5f3ed; -fx-font-size: 12px; -fx-font-weight: bold;");
				Text type=new Text("Type: "+h.getClass().getSimpleName());
				type.setFill(Color.WHITE);
				type.setFont(Font.font("Pixeboy", FontWeight.BOLD, 18));
				type.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
				//type.setStyle("-fx-text-fill: #f5f3ed; -fx-font-size: 12px; -fx-font-weight: bold;");

			 ProgressBar healthBar = new ProgressBar();
			 healthBar.setStyle("-fx-accent: red;");
			 healthBar.setPrefWidth(200);
			 double healthPercentage=(double)h.getCurrentHp()/(double)h.getMaxHp();
			 healthBar.setProgress(healthPercentage);
			 ProgressBar actionsBar = new ProgressBar();
			 actionsBar.setStyle("-fx-accent: blue;");
			 actionsBar.setPrefWidth(200);
			 double actionsPercentage=(double)h.getActionsAvailable()/(double)h.getMaxActions();
			 actionsBar.setProgress(actionsPercentage);
			 
			 statsBox.getChildren().addAll(name,type,hp,healthBar,actions,actionsBar,supplyInventory,vaccineInventory);
			 Button button=new Button(/*Game.heroes.get(i).getName()+"\n"+"Type:"+h.getClass().getSimpleName()+"\n" + "Health:" + h.getCurrentHp() + "\n" + "actions:" + h.getActionsAvailable()
	            + "\n" + "attack Damage:" + h.getAttackDmg()*/);
			 button.setGraphic(statsBox);
// Set background color with opacity
			 button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);  -fx-font-size: 15px; -fx-font-weight: bold;");
				button.setTooltip(new Tooltip("Type:"+h.getClass().getSimpleName()+"\n" + "Health:" + h.getCurrentHp() + "\n" + "actions:" + h.getActionsAvailable()
	            + "\n" + "attack Damage:" + h.getAttackDmg()));
			 int x=i;
			 button.setOnAction(e->{
				 selectedHero=Game.heroes.get(x);
				 setHeroImage(selectedHero);
				 Button highlight=new Button();
				 highlight.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
				 highlight.setPrefSize(45, 45);
				 updateMap();
				grid.add(highlight, selectedHero.getLocation().x, 14-selectedHero.getLocation().y);
					actionsPane();
					
					
			 });
			 heroesList.getChildren().add(button);
		 }
	 }
	 
	 public static void updateMap() {
		
		 grid.getChildren().clear();
		 for(int i=0;i<15;i++) {
			 for(int j=0;j<15;j++) {
				 if(Game.map[i][j].isVisible()) {
					 if(Game.map[i][j] instanceof TrapCell) {
						 Image hero=new Image(GamePlayScreen.class.getResourceAsStream("grass2.jpg"));
						 ImageView heroView=new ImageView(hero);
						 grid.add(heroView, i,14-j );
					 }
				 if(Game.map[i][j] instanceof CharacterCell) {
					 int row=i;
					 int col=j;
					 if(((CharacterCell) Game.map[i][j]).getCharacter()==null) {
						 hero=new Image(GamePlayScreen.class.getResourceAsStream("grass2.jpg"));
						 ImageView heroView=new ImageView(hero);
						 grid.add(heroView, i,14-j );
					 }
					 
					 
					 if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero) {
						 setHeroImage((Hero)((CharacterCell) Game.map[i][j]).getCharacter());
						 ImageView heroView=new ImageView(hero);
						 heroView.setOnMouseClicked(e->{
							 if ( e.getClickCount() == 2) {
							 	 selectedHero=(Hero)((CharacterCell) Game.map[row][col]).getCharacter();
						 }
							 selectedHero.setTarget(((CharacterCell) Game.map[row][col]).getCharacter());
						 });
						 grid.add(heroView, i,14-j );
						
						 
					 }
					 if(((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie) {
						 Image zombie=new Image(GamePlayScreen.class.getResourceAsStream("zombie2.jpg"));
						 ImageView zombieView=new ImageView(zombie);
						 
						 zombieView.setOnMouseClicked(e->{
							 selectedHero.setTarget(((CharacterCell) Game.map[row][col]).getCharacter());
							 if(selectedHero.getTarget().getCurrentHp()<=0) {
								 selectedHero.setTarget(null);
							 }
						 });
						 grid.add(zombieView, i, 14-j);
						 
						 
				 }
			 }
				 if(Game.map[i][j] instanceof CollectibleCell) {
					 if(((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine) {
						 
						 //container for vacc
						 StackPane vaccinecontainer = new StackPane();
						 vaccinecontainer.setPrefSize(CELL_SIZE, CELL_SIZE);
						 
						 Image vaccine=new Image(GamePlayScreen.class.getResourceAsStream("vaccine.gif"));
						 ImageView vaccineView=new ImageView(vaccine);
						 
						 vaccineView.setFitWidth(CELL_SIZE);
						 vaccineView.setFitHeight(CELL_SIZE);
						 vaccineView.setPreserveRatio(true);
						 
						 vaccinecontainer.getChildren().add(vaccineView);
						 
						 grid.add(vaccineView, i, 14-j);
						
						
					 }
					 if(((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Supply) {
						 
						 //container for vacc
						 StackPane supplycontainer = new StackPane();
						 supplycontainer.setPrefSize(CELL_SIZE, CELL_SIZE);
						 
						 Image supply=new Image(GamePlayScreen.class.getResourceAsStream("supplygif.gif"));
						 ImageView supplyView=new ImageView(supply);
						 
						 supplyView.setFitWidth(CELL_SIZE);
						 supplyView.setFitHeight(CELL_SIZE);
						 supplyView.setPreserveRatio(true);
						 
						 grid.add(supplyView, i, 14-j);
						
						
					 }
				 }
				 }
				 else {
					 Image hero=new Image(GamePlayScreen.class.getResourceAsStream("grassnotvisible.jpg"));
					 ImageView heroView=new ImageView(hero);

					 grid.add(heroView, i,14-j );
				 }
				 
				 
		 }
			 
			 
		 }
		 updateHeroesListPane();
		 actionsPane();

		 
}
	 public static void actionsPane() {
		 stats.getChildren().clear();

		 stats.setAlignment(Pos.CENTER);
			Button attackButton=new Button("ATTACK");
			
			Font font = Font.loadFont(GamePlayScreen.class.getResourceAsStream("/application/Pixeboy-z8XGD.ttf"), 30);
			attackButton.setFont(font);
			attackButton.setTextFill(Color.WHITE);
			attackButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
			//attackButton.setStyle("-fx-text-fill: #f5f3ed; -fx-font-size: 20px; -fx-font-weight: bold;");
			
			Image image = new Image(GamePlayScreen.class.getResourceAsStream("PUNCH.png"));
			
	        ImageView imageView = new ImageView(image);
	        attackButton.setGraphic(imageView);
	        attackButton.setContentDisplay(attackButton.getContentDisplay().LEFT);
	        attackButton.setPrefSize(200, 50);
	        //attackButton.setFont(font);
	        //attackButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);-fx-text-fill: #f5f3ed; -fx-font-size: 20px;");
	        //attackButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);-fx-text-fill: #f5f3ed; -fx-font-size: 20px; -fx-font-weight: bold;");
			attackButton.setOnAction(e->{
				try {
					selectedHero.attack();

					updateMap();
				} catch (NotEnoughActionsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			Button useSpecial=new Button("USE SPECIAL");
			useSpecial.setFont(font);
			useSpecial.setTextFill(Color.WHITE);
			useSpecial.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
			//useSpecial.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);-fx-text-fill: #f5f3ed; -fx-font-size: 20px; -fx-font-weight: bold;");
			useSpecial.setPrefSize(200, 50);
			useSpecial.setOnAction(e->{
				try {
					selectedHero.useSpecial();

					updateMap();
				} catch (NoAvailableResourcesException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			});
			Button cure=new Button("CURE");
			cure.setFont(font);
			cure.setTextFill(Color.WHITE);
			cure.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
			//cure.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);-fx-text-fill: #f5f3ed; -fx-font-size: 20px; -fx-font-weight: bold;");
			
			Image image2 = new Image(GamePlayScreen.class.getResourceAsStream("redcross.png"));
			
	        ImageView imageView2 = new ImageView(image2);
	        cure.setGraphic(imageView2);
	        cure.setContentDisplay(cure.getContentDisplay().LEFT);
	        cure.setPrefSize(200, 50);
	        
	        
			cure.setOnAction(e->{
				try {
					selectedHero.cure();

					updateMap();
				} catch (NoAvailableResourcesException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughActionsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			Button endturn=new Button("END TURN");
			endturn.setFont(font);
			endturn.setTextFill(Color.WHITE);
			endturn.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-background-color: #ff0000; -fx-text-fill: black; -fx-border-color: transparent;");
			//endturn.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);-fx-background-color: #ff0000;-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
			endturn.setPrefSize(200, 50);
			endturn.setOnAction(e->{
				try {
					Game.endTurn();

					updateMap();
				} catch (NotEnoughActionsException | InvalidTargetException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			});
			
			
			
			stats.getChildren().addAll(attackButton,useSpecial,cure,endturn);
			gameScreenLayout.setRight(stats);
			
		
	 }
	 public static void updateHeroesListPane() {
		 
		 
		 heroesList.getChildren().clear();
		 heroesList.setAlignment(Pos.CENTER_LEFT);
		 Text label=new Text("Your current heroes:");
		 
		 Font font = Font.loadFont(GamePlayScreen.class.getResourceAsStream("/application/Pixeboy-z8XGD.ttf"), 30);
		 label.setFont(font);
		 label.setFont(Font.font("Pixeboy", FontWeight.BOLD, 30));
		 label.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-text-fill: white; -fx-border-color: transparent;");
		 
		 label.setFill(Color.WHITE);
		 //label.setStyle("-fx-font-family: Impact;-fx-background-color: transparent; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
		 heroesList.getChildren().add(label);
		 setCurrentHerosButtons();
		 
		 gameScreenLayout.setLeft(heroesList);
		
	 }

	 
	 public static void movementbuttons() {
		 
	        scene.setOnKeyPressed(event -> {
	            KeyCode keyCode = event.getCode();
	            switch (keyCode) {
	                case W:
					try {
						selectedHero.move(Direction.RIGHT);
					} catch (MovementException | NotEnoughActionsException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
	                    updateMap();
	                    actionsPane();
	                    updateHeroesListPane();
	                    break;
	                case S:
					try {
						selectedHero.move(Direction.LEFT);
					} catch (MovementException | NotEnoughActionsException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
	                    updateMap();
	                    actionsPane();
	                    updateHeroesListPane();
	                    break;
	                case A:
					try {
						selectedHero.move(Direction.DOWN);
					} catch (MovementException | NotEnoughActionsException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                    updateMap();
	                    actionsPane();
	                    updateHeroesListPane();
	                    break;
	                case D:
					try {
						selectedHero.move(Direction.UP);
					} catch (MovementException | NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                    updateMap();
	                    actionsPane();
	                    updateHeroesListPane();
	                    break;
	                case E:
					try {
						Game.endTurn();
					} catch (NotEnoughActionsException | InvalidTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						updateMap();
						actionsPane();
						updateHeroesListPane();
						break;
	                case X:
					try {
						selectedHero.attack();
					} catch (NotEnoughActionsException | InvalidTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						updateMap();
						actionsPane();
						updateHeroesListPane();
						break;
	                case C:
					try {
						selectedHero.cure();
					} catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					updateMap();
					actionsPane();
					break;
	                case Z:
					try {
						selectedHero.useSpecial();
					} catch (NoAvailableResourcesException | InvalidTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						updateMap();
						actionsPane();
						updateHeroesListPane();
						break;
	                default:
	                    break;
	            }
	        });
		 
	 }	
}

