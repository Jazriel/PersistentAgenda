package gui.tab.insertTab;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.tab.IContactTypePersistUser;
import model.ContactType;
import persistence.IFacadeContactTypePersistence;

public class InsertContactTypeState implements InsertState, IContactTypePersistUser {
	
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
	public InsertContactTypeState(IFacadeContactTypePersistence contactTypePersistence) {
		
		this.contactTypePersistence = contactTypePersistence;
		
		JPanel contactTypePanel = new JPanel();

		contactTypePanel.setLayout(new GridLayout(3, 2, 2, 2));

		JLabel lblNombreDelTipo = new JLabel("Nombre del tipo de contacto:");
		contactTypePanel.add(lblNombreDelTipo);

		contactTypeTextField = new JTextField();
		contactTypePanel.add(contactTypeTextField);
		contactTypeTextField.setColumns(10);

		JLabel label = new JLabel("");
		contactTypePanel.add(label);

		JButton btnEjecutar = new JButton("Insertar");
		contactTypePanel.add(btnEjecutar);
		
		insertContactTypeListener(btnEjecutar);
		
		view = contactTypePanel;
	}

	private void insertContactTypeListener(JButton btnEjecutar) {
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ContactType contactTypeName = new ContactType(contactTypeTextField.getText());
				contactTypePersistence.saveContactType(contactTypeName );
			}
		});
	}
	
	public JPanel getView(){
		return view;
	}

	@Override
	public void setPersistence(IFacadeContactTypePersistence cp) {
		this.contactTypePersistence = cp;
		
	}
}
