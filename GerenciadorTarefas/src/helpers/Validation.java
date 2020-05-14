package helpers;

public class Validation {
	public Boolean isValidString(String s) {
		if (s == "" || s == null || s.length() <= 0 || s.isEmpty()) {
			return false;
		}
		return true;
	}
}
