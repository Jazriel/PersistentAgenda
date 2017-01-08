package gui.selectTab;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.SelectResultWindow;
import model.Contact;
import persistence.IFacadeContactPersistence;

public class SelectContactState implements SelectState {

	private IFacadeContactPersistence contactPersitence;
	private JPanel view;

	public SelectContactState(IFacadeContactPersistence contactPersitence) {

		this.contactPersitence = contactPersitence;

		JPanel selectPanel = new JPanel();

		JLabel lblPor = new JLabel("por");

		JTextField filterTextField = new JTextField();
		filterTextField.setColumns(10);
		selectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JComboBox filOrdCombo = new JComboBox();
		filOrdCombo.setModel(new DefaultComboBoxModel(new String[] { "Contacto", "Fecha" }));
		selectPanel.add(filOrdCombo);
		selectPanel.add(lblPor);

		filOrdListener(filOrdCombo, filterTextField);

		JComboBox fieldCombo = new JComboBox();
		fieldCombo.setModel(new DefaultComboBoxModel(new String[] { "Apellido", "Nombre" }));
		selectPanel.add(fieldCombo);
		selectPanel.add(filterTextField);

		Button button = new Button("Ejecutar");
		selectPanel.add(button);
		contactListener(button, filOrdCombo, fieldCombo, filterTextField);

		this.view = selectPanel;
	}

	private void contactListener(Button button, JComboBox filOrdCombo, JComboBox fieldCombo,
			JTextField filterTextField) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				List<Contact> contacts = new ArrayList<>();
				if (filOrdCombo.getSelectedIndex() == 0) {
					if (fieldCombo.getSelectedIndex() == 0) {
						contacts = contactPersitence.getFilterContacts("name", filterTextField.getText());
					} else {
						contacts = contactPersitence.getFilterContacts("surname", filterTextField.getText());
					}
				} else {
					if (fieldCombo.getSelectedIndex() == 0) {
						contacts = contactPersitence.getOrderContacts("name");
					} else {
						contacts = contactPersitence.getOrderContacts("surname");
					}
				}
				String[] contactsStrings = new String[contacts.size()];
				//
				for (int i = 0; i < contactsStrings.length; i++) {
					contactsStrings[i] = contacts.get(i).toString();
				}
				// Mostramos contactos
				@SuppressWarnings("unused")
				SelectResultWindow srw = new SelectResultWindow(contactsStrings);
			}
		});
	}

	private void filOrdListener(JComboBox filOrdCombo, JTextField filterTextField) {
		filOrdCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filOrdCombo.getSelectedIndex() == 0) {
					filterTextField.setVisible(true);
				} else {
					filterTextField.setVisible(false);
				}
				// This was tried before and didnt work->selectPanel.repaint();
			}
		});
	}

	@Override
	public JPanel getView() {
		return view;
	}

}
