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

public class UpdateContactTypeState implements UpdateState, ICompPersistUpdatable {

	private JPanel view;
	private IFacadeContactTypePersistence contactTypePersistence;
	private JTextField contactTypeTextField;

	/**
	 * Método createConatactTypePanel. Método que se encarga de especificar el
	 * panel para la creación de tipos de contacto.
	 * 
	 * @return contactTypePanel Se devuelve la instancia del panel de tipo de
	 *         contacto.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UpdateContactTypeState(IFacadeContactTypePersistence contactTypePersistence) {

		this.contactTypePersistence = contactTypePersistence;

		JPanel contactTypePanel = new JPanel();

		contactTypePanel.setLayout(new GridLayout(3, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto a modificar:");
		contactTypePanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();

		List<ContactType> contactTypes = contactTypePersistence.getAllContactTypes();

		String[] comboStrings = new String[contactTypes.size()];

		for (int i = 0; i < contactTypes.size(); i++) {
			comboStrings[i] = String.valueOf(contactTypes.get(i).getId());
		}
		
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
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

		insertContactTypeListener(btnEjecutar, comboBox);

		view = contactTypePanel;
	}

	@SuppressWarnings("rawtypes")
	private void insertContactTypeListener(JButton btnEjecutar, JComboBox comboBox) {
		btnEjecutar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ContactType contactType = new ContactType(Integer.parseInt(comboBox.getSelectedItem().toString()),
						contactTypeTextField.getText());
				contactTypePersistence.updateContactType(contactType);
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
