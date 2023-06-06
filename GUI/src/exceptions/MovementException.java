package exceptions;

import application.popUpWindows;

public class MovementException extends GameActionException {

	public MovementException() {
	}

	public MovementException(String message) {
		super(message);
		popUpWindows.display(message);
	}

}
