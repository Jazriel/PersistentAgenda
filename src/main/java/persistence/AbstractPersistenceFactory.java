package persistence;

import persistence.bin.FactoryBinFile;
import persistence.database.FactoryDataBase;

public class AbstractPersistenceFactory implements IAbstractPersistenceFactory {

	@Override
	public IFactoryPersistence getBinPersistence() {
		return new FactoryBinFile();
	}

	@Override
	public IFactoryPersistence getDBPersistence() {
		return new FactoryDataBase();
	}

}
