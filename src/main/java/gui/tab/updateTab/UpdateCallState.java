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
import model.Call;
import model.Contact;
import persistence.IFacadeCallPersistence;
import persistence.IFactoryPersistence;

/**
 * UpdateCallTypeState. Clase que se encarga de la actualización de llamadas.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class UpdateCallState implements UpdateState, ICompPersistUpdatable {

	/**
	 * Persistencia de llamadas
	 */
	private IFacadeCallPersistence callPersistence;

	/**
	 * Vista
	 */
	private JPanel view;

	/**
	 * Campos de llamadas.
	 */
	private List<JTextField> callTextFields;

	/**
	 * Combo box para seleccionar la llamada a modificar.
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	/**
	 * Méto UpdateCallState. Constructor de la clase.
	 * 
	 * @param callPersistence
	 *            Persistencia de llamadas.
	 */
	@SuppressWarnings({ "rawtypes" })
	public UpdateCallState(IFacadeCallPersistence callPersistence) {
		this.callPersistence = callPersistence;

		JPanel callPanel = new JPanel();
		callPanel.setLayout(new GridLayout(10, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Llamada a modificar:");
		callPanel.add(lblTipoDeContacto);

		comboBox = new JComboBox();

		updateComboBox(callPersistence);

		callPanel.add(comboBox);

		createCallFields(callPanel);

		JLabel label = new JLabel("");
		callPanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
		callPanel.add(btnEjecutar);

		insertCallListener(btnEjecutar, comboBox);

		view = callPanel;
	}

	/**
	 * Metodo para actualizar la combo box
	 * 
	 * @param callPersistence
	 *            Persistencia de llamadas.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updateComboBox(IFacadeCallPersistence callPersistence) {
		List<Call> calls = callPersistence.getAllCalls();

		String[] comboStrings = new String[calls.size()];

		for (int i = 0; i < calls.size(); i++) {
			comboStrings[i] = String.valueOf(calls.get(i).getId());
		}

		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
	}

	/**
	 * Método updateCallListener. Método que se encarga de de realizar el
	 * actualizado de llamadas.
	 * 
	 * @param btnEjecutar
	 *            Botón asociado.
	 * @param comboBox
	 *            Combo box de selección de llamada.
	 */
	@SuppressWarnings("rawtypes")
	private void insertCallListener(JButton btnEjecutar, JComboBox comboBox) {
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Call call = new Call(Integer.parseInt(comboBox.getSelectedItem().toString()),
						new Contact(Integer.parseInt(callTextFields.get(0).toString()), null, null),
						callTextFields.get(1).getText(), callTextFields.get(2).getText(),
						callTextFields.get(3).getText());
				callPersistence.updateCall(call);
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
		String[] fieldsString = { "Contacto", "Fecha", "Asunto", "Notas" };
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
		updateComboBox(callPersistence);
	}
}
