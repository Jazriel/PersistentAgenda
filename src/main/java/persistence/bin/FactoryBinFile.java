package persistence.bin;

import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;
import persistence.IFacadeCallPersistence;

/**
 * Clase FactoryBinFile. Clase que se encarga de la factoria de los tipos
 * binarios.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 */
public class FactoryBinFile implements IFactoryPersistence {

	/**
	 * Clase que se encarga de crear una instancia para el tratamiento de los
	 * contactos desde ficheros binarios.
	 * 
	 * @return FacadeContactBinFile. Instancia de los contactos por fichero
	 *         binario.
	 */
	@Override
	public IFacadeContactPersistence createContactPersistence() {
		return new FacadeContactBinFile();
	}

	/**
	 * Clase que se encarga de crear una instancia para el tratamiento de las
	 * llamadas desde ficheros binarios
	 * 
	 * @return FacadeCallBinFile. Instancia de las llamadas por fichero binario.
	 */
	@Override
	public IFacadeCallPersistence createCallPersistence() {
		return new FacadeCallBinFile();
	}

	/**
	 * Clase que se encarga de crear una instancia para el tratamiento de los
	 * tipos de contactos desde ficheros binarios.
	 * 
	 * @return FacadeContactTypeBinFile. Instancia de los tipos de llamada por
	 *         fichero binario.
	 */
	@Override
	public IFacadeContactTypePersistence createContactTypePersistence() {
		return new FacadeContactTypeBinFile();
	}

}
