package gui.tab.updateTab;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.tab.ICompPersistUpdatable;
import model.Contact;
import model.ContactType;
import persistence.IFacadeContactPersistence;
import persistence.IFactoryPersistence;

public class UpdateContactState implements UpdateState, ICompPersistUpdatable {

	private IFacadeContactPersistence contactPersistence;
	private JPanel view;
	private List<JTextField> contactTextFields;

	/**
	 * Método createConatactPanel. Método que se encarga de especificar el panel
	 * para la creación de contacto.
	 * 
	 * @return contactPanel Se devuelve la instancia del panel de contacto.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UpdateContactState(IFacadeContactPersistence contactPersistence) {
		this.contactPersistence = contactPersistence;

		JPanel contactPanel = new JPanel();
		contactPanel.setLayout(new GridLayout(20, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Contacto a modificar:");
		contactPanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();
		List<Contact> contacts = contactPersistence.getAllContacts();

		String[] comboStrings = new String[contacts.size()];

		for (int i = 0; i < contacts.size(); i++) {
			comboStrings[i] = String.valueOf(contacts.get(i).getId());
		}
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
		contactPanel.add(comboBox);

		createContactFields(contactPanel);

		JLabel label = new JLabel("");
		contactPanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
		contactPanel.add(btnEjecutar);

		insertContactListener(btnEjecutar, comboBox);

		view = contactPanel;
	}

	@SuppressWarnings("rawtypes")
	private void insertContactListener(JButton btnEjecutar, JComboBox comboBox) {
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lista de atributos del contacto
				List<String> attribs = new ArrayList<>();

				int i = 0;
				for (; i < contactTextFields.size() - 1; i++) {
					attribs.add(contactTextFields.get(i).getText());
				}

				Contact contact = new Contact(Integer.parseInt(comboBox.getSelectedItem().toString()), attribs,
						new ContactType(Integer.parseInt(contactTextFields.get(i).getText()), ""));
				contactPersistence.updateContact(contact);
			}
		});
	}

	/**
	 * Método createContactFields. Método que se encarga de meter los campos de
	 * contacto dentro del panel de contactos.
	 * 
	 * @param contactPanel
	 *            Panel sobre el que se quieren insertar los campos.
	 */
	private void createContactFields(JPanel contactPanel) {
		contactTextFields = new ArrayList<>();
		String[] fieldsString = { "Nombre", "Apellido", "Titulo", "Direccion", "Ciudad", "Provincia", "Codigo postal",
				"Region", "Pais", "Empresa", "Centro de trabajo", "Telefono de trabajo", "Extension", "Telefono movil",
				"Fax", "Email", "Tipo de contacto", "Notas" };
		List<String> fields = Arrays.asList(fieldsString);
		for (String field : fields) {
			JLabel fieldLabel = new JLabel(field);
			contactPanel.add(fieldLabel);

			JTextField jTextField = new JTextField();
			contactTextFields.add(jTextField);
			contactPanel.add(jTextField);
			jTextField.setColumns(10);
		}
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void updatePersist(IFactoryPersistence persist) {
		this.contactPersistence = persist.createContactPersistence();
	}

}
