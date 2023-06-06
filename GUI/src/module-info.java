module GUI {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	requires junit;
	requires javafx.media;




	
	opens application to javafx.graphics, javafx.fxml;
}
