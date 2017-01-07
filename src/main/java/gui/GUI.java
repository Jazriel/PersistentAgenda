package gui;

import java.awt.EventQueue;

import persistence.AbstractPersistenceFactory;
import persistence.IAbstractPersistenceFactory;
import persistence.IFacadeCallPersistence;
import persistence.IFacadeContactPersistence;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;

public class GUI {
	
	
	private static IFactoryPersistence persistence;
	protected static IFacadeContactPersistence contactPersitence;
	protected static IFacadeCallPersistence callPersitence;
	protected static IFacadeContactTypePersistence contactTypePersitence;
	private static IAbstractPersistenceFactory abstractPersistenceFactory;
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
