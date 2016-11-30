package persistence;

public interface IAbstractPersistenceFactory {
	public IFactoryPersistence getBinPersistence();
	public IFactoryPersistence getDBPersistence();
}
