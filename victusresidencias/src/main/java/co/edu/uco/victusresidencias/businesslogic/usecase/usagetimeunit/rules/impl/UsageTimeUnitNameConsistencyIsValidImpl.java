package co.edu.uco.victusresidencias.businesslogic.usecase.usagetimeunit.rules.impl;

import co.edu.uco.victusresidencias.businesslogic.usecase.usagetimeunit.rules.UsageTimeUnitNameConsistencyIsValid;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;

public final class UsageTimeUnitNameConsistencyIsValidImpl implements UsageTimeUnitNameConsistencyIsValid{
	@Override
	public void execute(final String data) {
		validateNotNull(data);
		validateLen(data);
		validateFormat(data);
	}
	
	private void validateLen(final String data) {
		if(!TextHelper.maxLenIsValid(data, 50)) {
			var userMessage = "El nombre del tiempo de uso debe ser valido con el formato ...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage);
			
		}
	}
	
	private void validateFormat(final String data) {
		if (!TextHelper.containsOnlyLettersAndSpaces(data)) {
			var userMessage = "El nombre del tiempo de uso solo puede contener  letras y espacios...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage);
			
		}	
	}
	
	private void  validateNotNull(final String data) {
		if (TextHelper.isEmpty(data)) {
			var userMessage = "El nombre del tiempo de uso no puede estar vacio...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage);
			
		}
	}
}