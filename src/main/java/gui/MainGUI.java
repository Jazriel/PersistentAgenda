package gui;

import java.awt.EventQueue;

import persistence.AbstractPersistenceFactory;
import persistence.IAbstractPersistenceFactory;

/**
 * GUI. Clase main de nuestra api en modo gr�fico.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sag�illo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class MainGUI {

	/**
	 * abstractPersistenceFactory
	 */
	protected static IAbstractPersistenceFactory abstractPersistenceFactory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		abstractPersistenceFactory = new AbstractPersistenceFactory();
		runGUI();

	}

	/**
	 * M�todo runGUI. M�todo que se encarga de lanzar la interfaz gr�fica de
	 * nuestra aplicaci�n.
	 */
	private static void runGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(abstractPersistenceFactory); // TODO
					frame.setVisible(true);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
	}

}
