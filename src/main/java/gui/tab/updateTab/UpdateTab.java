package gui.tab.updateTab;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.tab.ITab;
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
public class UpdateTab implements ITab {

	/**
	 * updatePanel
	 */
	private JPanel updatePanel;
	/**
	 * viewDict
	 */
	private Map<Integer, UpdateState> viewDict;

	/**
	 * Método UptadeTab. Constructor de la clase.
	 * 
	 * @param tabbedPane
	 *            Panel sobre el que trabajar.
	 */
	public UpdateTab(IFactoryPersistence persistence, JTabbedPane tabbedPane) {

		updatePanel = new JPanel();
		tabbedPane.addTab("Actualizar", null, updatePanel, null);

		viewDict = new HashMap<>();
		viewDict.put(0, new UpdateContactState(persistence.createContactPersistence()));
		viewDict.put(1, new UpdateCallState(persistence.createCallPersistence()));
		viewDict.put(2, new UpdateContactTypeState(persistence.createContactTypePersistence()));
		updatePanel.add(viewDict.get(0).getView());
	}

	/**
	 * Método setView. Método que se encarga de establecer una vista.
	 * 
	 * @param view
	 *            Vista a establecer.
	 */
	public void setView(int view) {
		updatePanel.removeAll();
		updatePanel.add(viewDict.get(view).getView());

	}

}