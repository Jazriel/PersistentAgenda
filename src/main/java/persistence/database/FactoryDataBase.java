package persistence.database;

import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.FactoryPersistence;
import persistence.IFacadeCallPersistence;

public class FactoryDataBase implements FactoryPersistence{
	
	@Override
	public IFacadeContactPersistence createContactPersistence() {
		return new FacadeUserDataBase();
	}

	@Override
	public IFacadeCallPersistence createCallPersistence() {
		return new FacadeCallDataBase();
	}

	@Override
	public IFacadeContactTypePersistence createContactTypePersistence() {
		return new FacadeContactTypeDataBase();
	}

}
