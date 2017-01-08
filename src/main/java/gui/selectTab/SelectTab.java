package gui.selectTab;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import persistence.IFactoryPersistence;

/**
 * SelectTab. Clase que se encarga de las selecciones.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class SelectTab {

	private Map<Integer, SelectState> viewDict;
	private JPanel selectPanel;

	/**
	 * Método SelectTab. Contructor de la clase.
	 * 
	 * @param tabbedPane
	 *            Panel sobre el que trabajar.
	 */

	public SelectTab(IFactoryPersistence persistence, JTabbedPane tabbedPane) {

		selectPanel = new JPanel();
		tabbedPane.addTab("Consultar", null, selectPanel, null);


		viewDict = new HashMap<>();
		viewDict.put(0, new SelectContactState(persistence.createContactPersistence()));
		viewDict.put(1, new SelectCallState(persistence.createCallPersistence()));
		viewDict.put(2, new SelectContactTypeState(persistence.createContactTypePersistence()));
		selectPanel.add(viewDict.get(0).getView());
	}


	/**
	 * Método setView. Método que se encarga de establecer una vista.
	 * 
	 * @param view
	 *            Vista a establecer.
	 */
	public void setView(int view) {
		selectPanel.removeAll();
		selectPanel.add(viewDict.get(view).getView());
	}

}
