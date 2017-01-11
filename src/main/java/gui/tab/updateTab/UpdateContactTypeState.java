package gui.tab.updateTab;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.tab.ICompPersistUpdatable;
import model.ContactType;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;

/**
 * UpdateContactTypeState. Clase que se encarga de la actualización de tipos de
 * contacto.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class UpdateContactTypeState implements UpdateState, ICompPersistUpdatable {

	/**
	 * Vista.
	 */
	private JPanel view;

	/**
	 * Persistencia de tipo de contacto.
	 */
	private IFacadeContactTypePersistence contactTypePersistence;

	/**
	 * Campo de tipo de contacto.
	 */
	private JTextField contactTypeTextField;

	/**
	 * Combo box para seleccionar la llamada a modificar.
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	/**
	 * Método UpdateContactTypeState. Constructor de la clase.
	 * 
	 * @param contactTypePersistence
	 *            Peristencia de tipos de contacto.
	 */
	@SuppressWarnings({ "rawtypes" })
	public UpdateContactTypeState(IFacadeContactTypePersistence contactTypePersistence) {

		this.contactTypePersistence = contactTypePersistence;

		JPanel contactTypePanel = new JPanel();

		contactTypePanel.setLayout(new GridLayout(3, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto a modificar:");
		contactTypePanel.add(lblTipoDeContacto);

		comboBox = new JComboBox();

		updateComboBox(contactTypePersistence);

		contactTypePanel.add(comboBox);

		JLabel lblNombreDelTipo = new JLabel("Nombre del tipo de contacto:");
		contactTypePanel.add(lblNombreDelTipo);

		contactTypeTextField = new JTextField();
		contactTypePanel.add(contactTypeTextField);
		contactTypeTextField.setColumns(10);

		JLabel label = new JLabel("");
		contactTypePanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
		contactTypePanel.add(btnEjecutar);

		updateContactTypeListener(btnEjecutar, comboBox);

		view = contactTypePanel;
	}

	/**
	 * Metodo para actualizar la combo box
	 * 
	 * @param contactTypePersistence
	 *            Persistencia de tipos de contactos.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void updateComboBox(IFacadeContactTypePersistence contactTypePersistence) {
		List<ContactType> contactTypes = contactTypePersistence.getAllContactTypes();

		String[] comboStrings = new String[contactTypes.size()];

		for (int i = 0; i < contactTypes.size(); i++) {
			comboStrings[i] = String.valueOf(contactTypes.get(i).getId());
		}

		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
	}

	/**
	 * Método updateContactTypeListener. Método que se encarga de realizar la
	 * actualización de un tipo de contacto.
	 * 
	 * @param btnEjecutar
	 *            Botón asociado.
	 * @param comboBox
	 *            Combo box de actualizar.
	 */
	@SuppressWarnings("rawtypes")
	private void updateContactTypeListener(JButton btnEjecutar, JComboBox comboBox) {
		btnEjecutar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ContactType contactType = new ContactType(Integer.parseInt(comboBox.getSelectedItem().toString()),
						contactTypeTextField.getText());
				contactTypePersistence.updateContactType(contactType);
			}
		});
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void updatePersist(IFactoryPersistence persist) {
		this.contactTypePersistence = persist.createContactTypePersistence();
		updateComboBox(contactTypePersistence);
	}
}
