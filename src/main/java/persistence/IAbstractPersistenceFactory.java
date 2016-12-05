package persistence;

/**
 * Interface IAbstractPersistenceFactory
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public interface IAbstractPersistenceFactory {
	public IFactoryPersistence getBinPersistence();

	public IFactoryPersistence getDBPersistence();
}
