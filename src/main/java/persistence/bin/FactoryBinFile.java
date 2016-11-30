package persistence.bin;

import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;
import persistence.IFacadeCallPersistence;

public class FactoryBinFile implements IFactoryPersistence {

	@Override
	public IFacadeContactPersistence createContactPersistence() {
		return new FacadeContactBinFile();
	}

	@Override
	public IFacadeCallPersistence createCallPersistence() {
		return new FacadeCallBinFile();
	}

	@Override
	public IFacadeContactTypePersistence createContactTypePersistence() {
		return new FacadeContactTypeBinFile();
	}

}
