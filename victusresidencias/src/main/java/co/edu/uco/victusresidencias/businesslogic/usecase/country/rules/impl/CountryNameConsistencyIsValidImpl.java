package co.edu.uco.victusresidencias.businesslogic.usecase.country.rules.impl;

import co.edu.uco.victusresidencias.businesslogic.usecase.country.rules.CountryNameConsistencyIsValid;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;

public final class CountryNameConsistencyIsValidImpl implements CountryNameConsistencyIsValid{
	@Override
	public void execute(final String data) {
		validateNotNull(data);
		validateLen(data);
		validateFormat(data);
	}
	
	private void validateLen(final String data) {
		if(!TextHelper.maxLenIsValid(data, 50)) {
			var userMessage = "El nombre del pais no puede contener máximo 50 ...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage);
			
		}
	}
	
	private void validateFormat(final String data) {
		if (!TextHelper.containsOnlyLettersAndSpaces(data)) {
			var userMessage = "El nombre del pais solo puede contener letras y espacios...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage);
			
		}	
	}
	
	private void  validateNotNull(final String data) {
		if (TextHelper.isEmpty(data)) {
			var userMessage = "El nombre de la ciudad no puede estar vacio...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage);
			
		}
	}
}