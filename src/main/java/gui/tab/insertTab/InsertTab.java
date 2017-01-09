package gui.tab.insertTab;

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
 * UpdateTab. Clase que se encarga de las actualizaciones.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class InsertTab implements ITab, ICompPersistUpdatable {

	/**
	 * updatePanel
	 */
	private JPanel insertPanel;
	/**
	 * viewDict
	 */
	private Map<Integer, InsertState> viewDict;

	private List<ICompPersistUpdatable> persistUpdatables;
	
	/**
	 * Método InsertTab. Método que es el contructor de la clase.
	 * 
	 * @param tabbedPane
	 */
	public InsertTab(IFactoryPersistence persistence, JTabbedPane tabbedPane) {

		insertPanel = new JPanel();
		tabbedPane.addTab("Insertar", null, insertPanel, null);

		InsertContactState insertContactState = new InsertContactState(persistence.createContactPersistence());
		InsertCallState insertCallState = new InsertCallState(persistence.createCallPersistence());
		InsertContactTypeState insertContactTypeState = new InsertContactTypeState(persistence.createContactTypePersistence());
		
		viewDict = new HashMap<>();
		viewDict.put(0, insertContactState);
		viewDict.put(1, insertCallState);
		viewDict.put(2, insertContactTypeState);
		insertPanel.add(viewDict.get(0).getView());
		
		persistUpdatables = new ArrayList<>();
		persistUpdatables.add(insertContactTypeState);
		persistUpdatables.add(insertCallState);
		persistUpdatables.add(insertContactState);
		
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

	@Override
	public void updatePersist(IFactoryPersistence persistence) {
		for (ICompPersistUpdatable persistUpdate: persistUpdatables) {
			persistUpdate.updatePersist(persistence);
		}
	}

}