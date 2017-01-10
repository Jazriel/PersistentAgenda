package gui.tab.selectTab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.tab.ICompPersistUpdatable;
import gui.tab.ITab;
import persistence.IFactoryPersistence;

/**
 * SelectTab. Clase que se encarga del tab de las selecciones.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class SelectTab implements ITab, ICompPersistUpdatable {

	private Map<Integer, SelectState> viewDict;

	private JPanel selectPanel;

	private List<ICompPersistUpdatable> persistUpdatables;

	/**
	 * Método SelectTab. Contructor de la clase.
	 * 
	 * @param tabbedPane
	 *            Panel sobre el que trabajar.
	 */

	public SelectTab(IFactoryPersistence persistence, JTabbedPane tabbedPane) {

		selectPanel = new JPanel();
		tabbedPane.addTab("Consultar", null, selectPanel, null);

		SelectContactState insertContactState = new SelectContactState(persistence.createContactPersistence());
		SelectCallState insertCallState = new SelectCallState(persistence.createCallPersistence());
		SelectContactTypeState insertContactTypeState = new SelectContactTypeState(
				persistence.createContactTypePersistence());

		viewDict = new HashMap<>();
		viewDict.put(0, insertContactState);
		viewDict.put(1, insertCallState);
		viewDict.put(2, insertContactTypeState);
		selectPanel.add(viewDict.get(0).getView());

		persistUpdatables = new ArrayList<>();
		persistUpdatables.add(insertContactTypeState);
		persistUpdatables.add(insertCallState);
		persistUpdatables.add(insertContactState);
	}

	/**
	 * Método setView. Se encarga de establecer la vista.
	 * 
	 * @param Vista
	 *            a establecer.
	 */
	public void setView(int view) {
		selectPanel.removeAll();
		selectPanel.add(viewDict.get(view).getView());
	}

	@Override
	public void updatePersist(IFactoryPersistence persistence) {
		for (ICompPersistUpdatable persistUpdate : persistUpdatables) {
			persistUpdate.updatePersist(persistence);
		}
	}

}
