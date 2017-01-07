package gui;

import java.awt.EventQueue;

import persistence.AbstractPersistenceFactory;
import persistence.IAbstractPersistenceFactory;
import persistence.IFacadeCallPersistence;
import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;

/**
 * GUI. Clase main de nuestra api en modo gráfico.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class GUI {
	
	/**
	 * persistence
	 */
	private static IFactoryPersistence persistence;

	/**
	 * contactPersistence
	 */
	protected static IFacadeContactPersistence contactPersitence;
	/**
	 * callPersistence
	 */
	protected static IFacadeCallPersistence callPersitence;
	/**
	 * contactTypePersistence
	 */
	protected static IFacadeContactTypePersistence contactTypePersitence;
	/**
	 * abstractPersistenceFactory
	 */
	protected static IAbstractPersistenceFactory abstractPersistenceFactory;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		abstractPersistenceFactory = new AbstractPersistenceFactory();
		
		persistence = abstractPersistenceFactory.getDBPersistence();
		
		contactPersitence = persistence.createContactPersistence();
		callPersitence = persistence.createCallPersistence();
		contactTypePersitence = persistence.createContactTypePersistence();
		runGUI();
		
	}

	/**
	 * Método runGUI. Método que se encarga de lanzar la interfaz gráfica de nuestra aplicación.
	 */
	private static void runGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * Método changePersistence. Método que se encarga de seleccionar la persistencia sobre la que se quiere trabajar.
	 * @param selection Persistencia a elegir.
	 */
	protected static void changePersistence(int selection){
		if(selection==0){
			persistence = abstractPersistenceFactory.getDBPersistence();		
		}else{
			persistence = abstractPersistenceFactory.getBinPersistence();
		}
		contactPersitence = persistence.createContactPersistence();
		callPersitence = persistence.createCallPersistence();
		contactTypePersitence = persistence.createContactTypePersistence();
	}

}
