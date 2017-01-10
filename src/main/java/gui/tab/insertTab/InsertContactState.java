package gui.tab.insertTab;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.tab.ICompPersistUpdatable;
import model.Contact;
import model.ContactType;
import persistence.IFacadeContactPersistence;
import persistence.IFactoryPersistence;

/**
 * InsertContactState. Clase que se encarga de insertar contactos.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class InsertContactState implements InsertState, ICompPersistUpdatable {

	/**
	 * Persistencia del contacto.
	 */
	private IFacadeContactPersistence contactPersistence;
	/**
	 * Vista sobre la que trabajamos.
	 */
	private JPanel view;
	/**
	 * Conjunto de campos de contacto.
	 */
	private List<JTextField> contactTextFields;

	/**
	 * Constructor de la clase.
	 * 
	 * @param contactPersistence
	 *            Persistencia de contacto.
	 */
	public InsertContactState(IFacadeContactPersistence contactPersistence) {
		this.contactPersistence = contactPersistence;
		JPanel contactPanel = new JPanel();
		contactPanel.setLayout(new GridLayout(20, 2, 2, 2));

		createContactFields(contactPanel);

		JLabel label = new JLabel("");
		contactPanel.add(label);

		JButton btnEjecutar = new JButton("Insertar");
		contactPanel.add(btnEjecutar);
		insertContactListener(btnEjecutar);

		view = contactPanel;
	}

	/**
	 * Método insertConcatactListener. Método que se encarga de guardar el
	 * contacto.
	 * 
	 * @param btnEjecutar
	 *            Boton asociado a insertar.
	 */
	private void insertContactListener(JButton btnEjecutar) {
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lista de atributos del contacto
				List<String> attribs = new ArrayList<>();

				int i = 0;
				for (; i < contactTextFields.size() - 1; i++) {
					attribs.add(contactTextFields.get(i).getText());
				}

				Contact contact = new Contact(attribs,
						new ContactType(Integer.parseInt(contactTextFields.get(i).getText()), ""));
				contactPersistence.saveContact(contact);
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
				"Fax", "Email", "Notas", "Tipo de contacto" };
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
	public void updatePersist(IFactoryPersistence cp) {
		this.contactPersistence = cp.createContactPersistence();

	}

}
