package exceptions;

import application.popUpWindows;

public class NoAvailableResourcesException extends GameActionException {

	public NoAvailableResourcesException() {
	}

	public NoAvailableResourcesException(String message) {
		super(message);
		popUpWindows.display(message);
	}

}
