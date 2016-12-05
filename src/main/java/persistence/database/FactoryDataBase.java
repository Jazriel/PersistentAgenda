package persistence.database;

import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;
import persistence.IFacadeCallPersistence;

/**
 * Clase FactoryDataBase
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class FactoryDataBase implements IFactoryPersistence {
	/**
	 * createContactPersistence(). Crea la persistencia de contactos.
	 * 
	 * @return new FacadeContactDataBase().
	 */
	@Override
	public IFacadeContactPersistence createContactPersistence() {
		return new FacadeContactDataBase();
	}

	/**
	 * createCallPersistence(). Crea la persistencia de llamadas.
	 * 
	 * @return new FacadeCallDataBase().
	 */
	@Override
	public IFacadeCallPersistence createCallPersistence() {
		return new FacadeCallDataBase();
	}

	/**
	 * createContactTypePersistence(). Crea la persistencia de tipos de
	 * contactos.
	 * 
	 * @return new FacadeContactTypeDataBase().
	 */
	@Override
	public IFacadeContactTypePersistence createContactTypePersistence() {
		return new FacadeContactTypeDataBase();
	}

}
