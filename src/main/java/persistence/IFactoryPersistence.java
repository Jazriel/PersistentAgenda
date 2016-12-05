package persistence;
/**
 * Interface IFactoryPersistence
 * @author Javier Martinez, Daniel Puente, Jaime Sag�illo, Jorge Zamora y Oscar Fernandez
 *
 */
public interface IFactoryPersistence {

	public IFacadeContactPersistence createContactPersistence();
	public IFacadeCallPersistence createCallPersistence();
	public IFacadeContactTypePersistence createContactTypePersistence();
	
}
