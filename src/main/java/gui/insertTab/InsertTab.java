package gui.insertTab;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import persistence.IFactoryPersistence;

/**
 * UpdateTab. Clase que se encarga de las actualizaciones.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class InsertTab {

	/**
	 * updatePanel
	 */
	private JPanel insertPanel;
	/**
	 * viewDict
	 */
	private Map<Integer, InsertState> viewDict;

	/**
	 * Método InsertTab. Método que es el contructor de la clase.
	 * 
	 * @param tabbedPane
	 */
	public InsertTab(IFactoryPersistence persistence, JTabbedPane tabbedPane) {

		insertPanel = new JPanel();
		tabbedPane.addTab("Insertar", null, insertPanel, null);

		viewDict = new HashMap<>();
		viewDict.put(0, new InsertContactState(persistence.createContactPersistence()));
		viewDict.put(1, new InsertCallState(persistence.createCallPersistence()));
		viewDict.put(2, new InsertContactTypeState(persistence.createContactTypePersistence()));
		insertPanel.add(viewDict.get(0).getView());
	}

	/**
	 * Método setView. Método que se encarga de establecer una vista.
	 * 
	 * @param view
	 *            Vista a establecer.
	 */
	public void setView(int view) {
		insertPanel.removeAll();
		insertPanel.add(viewDict.get(view).getView());

	}

}