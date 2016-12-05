package persistence;
/**
 * Interface IFactoryPersistence
 * @author Javier Martinez, Daniel Puente, Jaime Sagüillo, Jorge Zamora y Oscar Fernandez
 *
 */
public interface IFactoryPersistence {

	public IFacadeContactPersistence createContactPersistence();
	public IFacadeCallPersistence createCallPersistence();
	public IFacadeContactTypePersistence createContactTypePersistence();
	
}
