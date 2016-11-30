package persistence.database;

import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;
import persistence.IFacadeCallPersistence;

public class FactoryDataBase implements IFactoryPersistence{
	
	@Override
	public IFacadeContactPersistence createContactPersistence() {
		return new FacadeContactDataBase();
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
