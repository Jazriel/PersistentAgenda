package gui;

import java.awt.EventQueue;

import persistence.AbstractPersistenceFactory;
import persistence.IAbstractPersistenceFactory;

/**
 * MainGUI. Clase main de nuestra api en modo gr�fico.
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
	 * Persistencia sobre la que trabajar
	 */
	protected static IAbstractPersistenceFactory abstractPersistenceFactory;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            argumentos de ejecucion (no hacen nada).
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
					MainWindow frame = new MainWindow(abstractPersistenceFactory);
					frame.setVisible(true);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
	}

}
