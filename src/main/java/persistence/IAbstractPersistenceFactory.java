package persistence;
/**
 * Interface IAbstractPersistenceFactory
 * @author Javier Martinez, Daniel Puente, Jaime Sagüillo, Jorge Zamora y Oscar Fernandez
 *
 */
public interface IAbstractPersistenceFactory {
	public IFactoryPersistence getBinPersistence();
	public IFactoryPersistence getDBPersistence();
}
