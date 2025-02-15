package co.edu.uco.crosscutting.helpers;

public class TextHelper {
	
	public static final String EMPTY = "vacio";
	
	private TextHelper() {
		
	}
	
	public static boolean isNull(final String string) {
		return ObjectHelper.isNull(string);
		
	}
	//utilizo el metodo de ObjectHelper cuando es null = TRUE
	
	public static String getDefault(final String string,final String defaultValue) {
		return ObjectHelper.getDefault(string,defaultValue);
		 
	}
	//utilizo el metodo de ObjectHelper para dar valor por defecto si es NULL el objeto pasandolo por el parametro
	
	public static String getDefault(final String string) {
		return ObjectHelper.getDefault(string,EMPTY);
		//Este metodo es para definir el valor por defecto para el String ya definido
	}
	
	public static boolean isEmpty(final String string) {
		return EMPTY.equals(getDefault(string));
		//return string.intern() == EMPTY;
		//debemos usar intern para analizar el interior no el objeto como tal
	}
	
	public static String applyTrim(final String string) {
		return getDefault(string).trim();
	}

}
					