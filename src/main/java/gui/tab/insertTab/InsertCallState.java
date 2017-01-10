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
import model.Call;
import model.Contact;
import persistence.IFacadeCallPersistence;
import persistence.IFactoryPersistence;
/**
 * InsertCallState. Clase que se encarga de insertar llamadas.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class InsertCallState implements InsertState, ICompPersistUpdatable {

	/**
	 * Persistencia de llamada.
	 */
	private IFacadeCallPersistence callPersistence;

	/**
	 * Vista.
	 */
	private JPanel view;

	/**
	 * Campos de llamada.
	 */
	private List<JTextField> callTextFields;

	/**
	 * Método InsertCallState. Constructor de la clase.
	 * @param callPersistence Persistencia sobre la que trabajamos.
	 */
	public InsertCallState(IFacadeCallPersistence callPersistence) {
		this.callPersistence = callPersistence;
		
		JPanel callPanel = new JPanel();
		callPanel.setLayout(new GridLayout(4, 2, 2, 2));

		createCallFields(callPanel);

		JLabel label = new JLabel("");
		callPanel.add(label);

		JButton btnEjecutar = new JButton("Insertar");

		insertCallListener(btnEjecutar);
		callPanel.add(btnEjecutar);
		view = callPanel;
	}

	/**
	 * Método insertCallListener. Método que se encarga de insertar una llamada.
	 * @param btnEjecutar Boton asociado a insertar llamada.
	 */
	private void insertCallListener(JButton btnEjecutar) {
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Call call = new Call(new Contact(Integer.parseInt(callTextFields.get(0).getText()), null, null),
						callTextFields.get(1).getText(), callTextFields.get(2).getText());
				callPersistence.saveCall(call);
			}
		});
	}

	/**
	 * Método createCallFields. Método que se encarga de meter los campos de
	 * llamada dentro del panel de las llamadas.
	 * 
	 * @param callPanel
	 *            Panel sobre el que se tendrán que meter los campos.
	 */
	private void createCallFields(JPanel callPanel) {
		callTextFields = new ArrayList<>();
		String[] fieldsString = { "Contacto", "Asunto", "Notas" };
		List<String> fields = Arrays.asList(fieldsString);
		for (String field : fields) {
			JLabel fieldLabel = new JLabel(field);
			callPanel.add(fieldLabel);

			JTextField jTextField = new JTextField();
			callTextFields.add(jTextField);
			callPanel.add(jTextField);
			jTextField.setColumns(10);
		}
	}


	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void updatePersist(IFactoryPersistence persist) {
		this.callPersistence = persist.createCallPersistence();
	}
}
