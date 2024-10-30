package co.edu.uco.victusresidencias.crosscutting.helpers;

public class TextHelper {
	
	public static final String EMPTY = "vacio";
	public static final String LETTER_AND_NUMBER_REGEX = "[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 ]*"; // ajustado para letras y números
	public static final String LETTER_REGEX = "[A-Za-zÁÉÍÓÚáéíóúÑñ ]";
	public static final String NUMBER_REGEX = "[0-9 ]";
	private TextHelper() {
	}
	public static boolean patternIsValid(final String string, final String pattern) {
		return applyTrim(string).matches((concat("^",pattern,"$")));
	}
	
	public static boolean contrainsOnlyLettersAndSapces(final String string) {
		return patternIsValid(string, LETTER_REGEX);
	} 	
	
	public static String concat(final String...strings) {
		var sb =new StringBuilder(EMPTY);
		for(String string :strings) {
			sb.append(getDefault(string));
		}
		return sb.toString();
	}
	public static boolean isNull(final String string) {
		return ObjectHelper.isNull(string);
	}

	public static String getDefault(final String string, final String defaultValue) {
		return ObjectHelper.getDefault(string, defaultValue);
	}

	public static String getDefault(final String string) {
		return ObjectHelper.getDefault(string, EMPTY);
	}

	public static boolean isEmpty(final String string) {
		return EMPTY.equals(getDefault(string));
	}

	public static String applyTrim(final String b) {
		return getDefault(b).trim();
	}
	
}
