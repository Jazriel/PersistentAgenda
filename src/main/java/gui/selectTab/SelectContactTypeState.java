package gui.selectTab;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import gui.SelectResultWindow;
import model.ContactType;
import persistence.IFacadeContactTypePersistence;

public class SelectContactTypeState implements SelectState {
	
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
}
