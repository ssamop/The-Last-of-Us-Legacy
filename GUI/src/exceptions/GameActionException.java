package exceptions;

import application.popUpWindows;

public abstract class GameActionException extends Exception {

	public GameActionException() {
	}

	public GameActionException(String message) {
		super(message);

	}

}
