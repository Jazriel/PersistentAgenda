package persistence;

public interface FactoryPersistence {

	public IFacadeContactPersistence createContactPersistence();
	public IFacadeCallPersistence createCallPersistence();
	public IFacadeContactTypePersistence createContactTypePersistence();
	
}
