package gui.tab.updateTab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.tab.ICompPersistUpdatable;
import gui.tab.ITab;
import gui.tab.selectTab.SelectCallState;
import gui.tab.selectTab.SelectContactState;
import gui.tab.selectTab.SelectContactTypeState;
import persistence.IFactoryPersistence;

/**
 * UpdateTab. Clase que se encarga de las actualizaciones.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sag�illo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class UpdateTab implements ITab, ICompPersistUpdatable {

	/**
	 * updatePanel
	 */
	private JPanel updatePanel;
	/**
	 * viewDict
	 */
	private Map<Integer, UpdateState> viewDict;
	private List<ICompPersistUpdatable> persistUpdatables;

	/**
	 * M�todo UptadeTab. Constructor de la clase.
	 * 
	 * @param tabbedPane
	 *            Panel sobre el que trabajar.
	 */
	public UpdateTab(IFactoryPersistence persistence, JTabbedPane tabbedPane) {

		updatePanel = new JPanel();
		tabbedPane.addTab("Actualizar", null, updatePanel, null);

		UpdateContactState updateContactState = new UpdateContactState(persistence.createContactPersistence());
		UpdateCallState updateCallState = new UpdateCallState(persistence.createCallPersistence());
		UpdateContactTypeState updateContactTypeState = new UpdateContactTypeState(
				persistence.createContactTypePersistence());

		viewDict = new HashMap<>();
		viewDict.put(0, updateContactState);
		viewDict.put(1, updateCallState);
		viewDict.put(2, updateContactTypeState);
		updatePanel.add(viewDict.get(0).getView());

		persistUpdatables = new ArrayList<>();
		persistUpdatables.add(updateContactTypeState);
		persistUpdatables.add(updateCallState);
		persistUpdatables.add(updateContactState);
	}

	/**
	 * M�todo setView. M�todo que se encarga de establecer una vista.
	 * 
	 * @param view
	 *            Vista a establecer.
	 */
	public void setView(int view) {
		updatePanel.removeAll();
		updatePanel.add(viewDict.get(view).getView());

	}

	@Override
	public void updatePersist(IFactoryPersistence persistence) {
		for (ICompPersistUpdatable persistUpdate : persistUpdatables) {
			persistUpdate.updatePersist(persistence);
		}
	}

}