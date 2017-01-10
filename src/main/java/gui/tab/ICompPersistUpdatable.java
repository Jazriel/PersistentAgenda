package gui.tab;

import persistence.IFactoryPersistence;

/**
 * ICompPersistUpdatable. Interfaz para las actualizaciones de persistencia.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sag�illo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public interface ICompPersistUpdatable {
	/**
	 * M�todo que se encarga de la actualizacion de la persistencia.
	 * @param persist Nueva persistencia a asignar.
	 */
	public void updatePersist(IFactoryPersistence persist);
}
