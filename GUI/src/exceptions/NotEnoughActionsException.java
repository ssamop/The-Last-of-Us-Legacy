package exceptions;

import application.popUpWindows;

public class NotEnoughActionsException extends GameActionException {

	public NotEnoughActionsException() {
	}

	public NotEnoughActionsException(String message) {
		super(message);
		popUpWindows.display(message);
	}

}
