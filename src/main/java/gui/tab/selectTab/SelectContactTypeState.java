package gui.tab.selectTab;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import gui.SelectResultWindow;
import gui.tab.ICompPersistUpdatable;
import model.ContactType;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;

public class SelectContactTypeState implements SelectState, ICompPersistUpdatable {
	
	private JPanel view;
	private IFacadeContactTypePersistence contactTypePersistence;

	public SelectContactTypeState(IFacadeContactTypePersistence contactTypePersistence) {

		this.contactTypePersistence = contactTypePersistence;
		
		JPanel selectPanel = new JPanel();

		Button button = new Button("Ejecutar");
		selectPanel.add(button);
		contactTypeListener(button);

		view = selectPanel;
	}

	private void contactTypeListener(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				List<ContactType> contactTypes = contactTypePersistence.getAllContactTypes();
				
				
				String[] contactsStrings = new String[contactTypes.size()];
				//
				for (int i = 0; i < contactsStrings.length; i++) {
					contactsStrings[i] = contactTypes.get(i).toString();
				}
				// Mostramos contactos
				@SuppressWarnings("unused")
				SelectResultWindow srw = new SelectResultWindow(contactsStrings);
			}
		});
	}
	
	public JPanel getView() {
		return view;
	}

	@Override
	public void updatePersist(IFactoryPersistence persist) {
		this.contactTypePersistence = persist.createContactTypePersistence();	
	}
}
