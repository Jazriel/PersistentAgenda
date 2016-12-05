package persistence;

/**
 * Interface IFactoryPersistence
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sag�illo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public interface IFactoryPersistence {

	public IFacadeContactPersistence createContactPersistence();

	public IFacadeCallPersistence createCallPersistence();

	public IFacadeContactTypePersistence createContactTypePersistence();

}
