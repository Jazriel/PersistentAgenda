package persistence;

public interface IFactoryPersistence {

	public IFacadeContactPersistence createContactPersistence();
	public IFacadeCallPersistence createCallPersistence();
	public IFacadeContactTypePersistence createContactTypePersistence();
	
}
