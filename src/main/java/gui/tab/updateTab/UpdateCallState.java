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

import model.Call;
import model.Contact;
import persistence.IFacadeCallPersistence;

public class UpdateCallState implements UpdateState {

	private IFacadeCallPersistence callPersistence;

	private JPanel view;

	private List<JTextField> callTextFields;

	/**
	 * Método createCallPanel. Método que se encarga de especificar el panel
	 * para la creación de llamadas.
	 * 
	 * @return callPanel Se devuelve la instancia del panel de llamdas.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UpdateCallState(IFacadeCallPersistence callPersistence) {
		this.callPersistence = callPersistence;

		JPanel callPanel = new JPanel();
		callPanel.setLayout(new GridLayout(10, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Llamada a modificar:");
		callPanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();
		List<Call> calls = callPersistence.getAllCalls();

		String[] comboStrings = new String[calls.size()];

		for (int i = 0; i < calls.size(); i++) {
			comboStrings[i] = String.valueOf(calls.get(i).getId());
		}

		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
		callPanel.add(comboBox);

		createCallFields(callPanel);

		JLabel label = new JLabel("");
		callPanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
		callPanel.add(btnEjecutar);

		insertCallListener(btnEjecutar, comboBox);

		view = callPanel;
	}

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
}
