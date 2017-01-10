package gui.tab.selectTab;

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
import gui.tab.ICompPersistUpdatable;
import model.Contact;
import persistence.IFacadeContactPersistence;
import persistence.IFactoryPersistence;

/**
 * SelectContactState. Clase que se encarga de la selección de contactos.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class SelectContactState implements SelectState, ICompPersistUpdatable {

	/**
	 * Persistencia de contacto.
	 */
	private IFacadeContactPersistence contactPersitence;
	/**
	 * Vista.
	 */
	private JPanel view;

	/**
	 * Método SelectContactState. Constructor de la clase.
	 * 
	 * @param contactPersitence
	 *            Persistencia de contacto.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SelectContactState(IFacadeContactPersistence contactPersitence) {

		this.contactPersitence = contactPersitence;

		JPanel selectPanel = new JPanel();

		JLabel lblPor = new JLabel("por");

		JTextField filterTextField = new JTextField();
		filterTextField.setColumns(10);
		selectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JComboBox filOrdCombo = new JComboBox();
		filOrdCombo.setModel(new DefaultComboBoxModel(new String[] { "Filtrar", "Ordenar" }));
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

	/**
	 * Método contactListener. Método que se encarga de la selección.
	 * 
	 * @param button
	 *            Botón asociado.
	 * @param filOrdCombo
	 *            Tipo de operación.
	 * @param fieldCombo
	 *            Campo por que que realizar la operación.
	 * @param filterTextField
	 *            Texto por el que filtrar.
	 */
	@SuppressWarnings("rawtypes")
	private void contactListener(Button button, JComboBox filOrdCombo, JComboBox fieldCombo,
			JTextField filterTextField) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				List<Contact> contacts = new ArrayList<>();
				String field;
				if (fieldCombo.getSelectedIndex() == 0) {
					field = "name";
				} else {
					field = "surname";
				}
				if (filOrdCombo.getSelectedIndex() == 0) {
					contacts = contactPersitence.getFilterContacts(field, filterTextField.getText());
				} else {
					contacts = contactPersitence.getOrderContacts(field);
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

	/**
	 * Método filOrdListener. Método que se encarga de la visibilidad del cuadro
	 * de texto.
	 * 
	 * @param filOrdCombo
	 *            Tipo de operación.
	 * @param filterTextField
	 *            Objeto a cambiar de visiblidad.
	 */
	@SuppressWarnings("rawtypes")
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

	@Override
	public void updatePersist(IFactoryPersistence persist) {
		this.contactPersitence = persist.createContactPersistence();
	}

}
