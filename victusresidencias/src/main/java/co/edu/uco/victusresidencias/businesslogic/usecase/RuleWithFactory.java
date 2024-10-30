package co.edu.uco.victusresidencias.businesslogic.usecase;

import co.edu.uco.victusresidencias.data.dao.DAOFactory;

public interface RuleWithFactory<T> {
	void execute(T data, DAOFactory factory);
}
