package exceptions;

import application.popUpWindows;

public class InvalidTargetException extends GameActionException {

	public InvalidTargetException() {
	}

	public InvalidTargetException(String message) {
		super(message);
		popUpWindows.display(message.replace(".", "!"));
	}

}
