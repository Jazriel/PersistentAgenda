package gui.updateTab;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.MainGUI;
import model.Call;
import model.Contact;
import model.ContactType;
import persistence.IFacadeContactTypePersistence;

public class UpdateContactTypeState implements UpdateState {
	
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
	public UpdateContactTypeState(IFacadeContactTypePersistence contactTypePersistence) {
		
		this.contactTypePersistence = contactTypePersistence;
		
		JPanel contactTypePanel = new JPanel();

		contactTypePanel.setLayout(new GridLayout(3, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto a modificar:");
		contactTypePanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();
		String[] comboStrings = new String[] { "a", "b" }; // TODO llamar persistencia.
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

	public JPanel getView(){
		return view;
	}
}
