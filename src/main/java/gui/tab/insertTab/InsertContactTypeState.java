package gui.tab.insertTab;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.tab.ICompPersistUpdatable;
import model.ContactType;
import persistence.IFacadeContactTypePersistence;
import persistence.IFactoryPersistence;

/**
 * InsertContactTypeState. Clase que se encarga de insertar tipos de contacto.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class InsertContactTypeState implements InsertState, ICompPersistUpdatable {

	/**
	 * Vista.
	 */
	private JPanel view;
	/**
	 * Persistencia actial.
	 */
	private IFacadeContactTypePersistence contactTypePersistence;
	/**
	 * Campo de tipos de contacto.
	 */
	private JTextField contactTypeTextField;

	/**
	 * Constructor de la clase.
	 * 
	 * @param contactTypePersistence
	 *            Persistencia a utilizar.
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

	/**
	 * Método insertContactTypeListener. Método que se encarga de insertar un
	 * tipo de contacto.
	 * 
	 * @param btnEjecutar
	 *            Boton asociado.
	 */
	private void insertContactTypeListener(JButton btnEjecutar) {
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ContactType contactTypeName = new ContactType(contactTypeTextField.getText());
				contactTypePersistence.saveContactType(contactTypeName);
			}
		});
	}
	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void updatePersist(IFactoryPersistence cp) {
		this.contactTypePersistence = cp.createContactTypePersistence();

	}
}
