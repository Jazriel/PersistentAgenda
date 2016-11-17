package persistence.bin;

import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.FactoryPersistence;
import persistence.IFacadeCallPersistence;

public class FactoryBinFile implements FactoryPersistence{

	@Override
	public IFacadeContactPersistence createContactPersistence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFacadeCallPersistence createCallPersistence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFacadeContactTypePersistence createContactTypePersistence() {
		// TODO Auto-generated method stub
		return null;
	}

}
