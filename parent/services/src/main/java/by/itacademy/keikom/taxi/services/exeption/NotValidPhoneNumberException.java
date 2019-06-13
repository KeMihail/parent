package by.itacademy.keikom.taxi.services.exeption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotValidPhoneNumberException extends RuntimeException {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotValidPhoneNumberException.class);

	public NotValidPhoneNumberException() {
		LOGGER.error("You have entered an invalid phone number");
	}
}
