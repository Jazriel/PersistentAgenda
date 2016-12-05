package persistence;

import persistence.bin.FactoryBinFile;
import persistence.database.FactoryDataBase;

/**
 * Clase abstracta AbstractPersistenceFactory
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sag�illo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class AbstractPersistenceFactory implements IAbstractPersistenceFactory {
	/**
	 * getBinPersistence().
	 * 
	 * @return new FactoryBinFile()
	 */
	@Override
	public IFactoryPersistence getBinPersistence() {
		return new FactoryBinFile();
	}

	/**
	 * getDBPersistence().
	 * 
	 * @return new FactoryDataBase()
	 */
	@Override
	public IFactoryPersistence getDBPersistence() {
		return new FactoryDataBase();
	}

}
