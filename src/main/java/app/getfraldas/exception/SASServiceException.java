package app.getfraldas.exception;

import java.util.logging.Logger;

public class SASServiceException extends Exception {
	private static final Logger LOGGER = Logger.getLogger(SASServiceException.class.getName());

	public SASServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SASServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SASServiceException(String message) {
		super(message);
	}

	public SASServiceException(Throwable cause) {
		super(cause);
	}
}
