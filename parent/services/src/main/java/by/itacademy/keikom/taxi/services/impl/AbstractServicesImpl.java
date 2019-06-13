package by.itacademy.keikom.taxi.services.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractServicesImpl {

	// the format of email addresses: ИмяПользователя@ИмяПочтовогоСервера (домен)
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	// the format of email phone number: 80(29)(25)(44)(33).......(seven digits) or
	// +375(29)(25)(44)(33).......(seven digits)
	private static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern
			.compile("^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$");

	// the format of birthday: yyyy-MM-dd
	private static final Pattern VALID_BIRTHDAY_REGEX = Pattern
			.compile("(19|20)\\d\\d-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)");

	public static boolean validateEmailAddress(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	public static boolean validatePhoneNumber(String phoneNumber) {
		Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
		return matcher.find();
	}

	public static boolean validateBirthday(String birthday) {
		Matcher matcher = VALID_BIRTHDAY_REGEX.matcher(birthday);
		return matcher.find();
	}

}
