package co.edu.uco.crosscutting.unitteting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.uco.crosscutting.helpers.ObjectHelper;

class HelpersUnitTestCase {

	@Test
	void givenWantValidateIfObjectIsNullWentObjectIsNullThenResultIsTrue() {
		// Arrange: Initial Context
		String object = null;
		// Act
		boolean result = ObjectHelper.isNull(object);
		// Assert
		assertTrue(result);
		
		//Cuando el objeto es nulo me devuelve True
	}
	
	@Test
	void givenWantValidateIfObjectIsNotNullWentObjectIsNotNullThenResultIsFalse() {
		// Arrange: Initial Context
		var object = "";
		// Act: Execute action
		boolean result = ObjectHelper.isNull(object);
		// Assert: Validate result
		assertFalse(result);
		
		//Cuando el objeto no es nulo me devuelve False
	}
	
	@Test
	void givenWantGetOriginalValueWentObjectIsNotNullThenResultIsTheSameObject() {
		// Arrange: Initial Context
		var object = "Casa";
		var defaultValue = "Default";
		// Act: Execute action
		var result = ObjectHelper.getDefault(object, defaultValue);
		// Assert: Validate result
		assertEquals(object,result);
		
		// Valor original cuando no es nulo me devuelve el objetoOriginal
	}
	
	@Test
	void givenWantGetDefaultValueWentObjectIsNullThenResultIsDefaultObject() {
		// Arrange: Initial Context
		String object = null;
		var defaultValue = "Default";
		// Act: Execute action
		var result = ObjectHelper.getDefault(object, defaultValue);
		// Assert: Validate result
		assertEquals(defaultValue,result);
		
		// Valor porDefecto cuando Objeto es nulo me devuelve el ValorDefecto
	}

}
