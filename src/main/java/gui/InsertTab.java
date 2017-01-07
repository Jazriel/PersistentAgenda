package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.Call;
import model.Contact;

/**
 * InsertTab. Clase que se encarga de las inserciones.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class InsertTab {

	/**
	 * textField
	 */
	private JTextField textField;
	/**
	 * comboBoxModel
	 */
	private DefaultComboBoxModel comboBoxModel;
	/**
	 * comboStrings
	 */
	private String[] comboStrings;
	/**
	 * contactTextFields
	 */
	private List<JTextField> contactTextFields;
	/**
	 * insertPanel
	 */
	private JPanel insertPanel;
	/**
	 * insertPanel
	 */
	private Map<Integer, JPanel> viewDict;
	private ArrayList<JTextField> callTextFields;

	/**
	 * Método InsertTab. Método que es el contructor de la clase.
	 * @param tabbedPane
	 */
	public InsertTab(JTabbedPane tabbedPane) {

		insertPanel = new JPanel();
		tabbedPane.addTab("Insertar", null, insertPanel, null);

		viewDict = new HashMap<>();
		viewDict.put(0, createContactPanel());
		viewDict.put(1, createCallPanel());
		viewDict.put(2, createContactTypePanel());
		insertPanel.add(viewDict.get(0));
	}

	/**
	 * Método createConatactTypePanel. Método que se encarga de especificar el panel para la creación de tipos de contacto.
	 * @return contactTypePanel Se devuelve la instancia del panel de tipo de contacto.
	 */
	private JPanel createContactTypePanel() {
		JPanel contactTypePanel = new JPanel();

		contactTypePanel.setLayout(new GridLayout(3, 2, 2, 2));

		JLabel lblNombreDelTipo = new JLabel("Nombre del tipo de contacto:");
		contactTypePanel.add(lblNombreDelTipo);

		textField = new JTextField();
		contactTypePanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("");
		contactTypePanel.add(label);

		JButton btnEjecutar = new JButton("Insertar");
		contactTypePanel.add(btnEjecutar);

		return contactTypePanel;
	}
	/**
	 * Método createConatactPanel. Método que se encarga de especificar el panel para la creación de contacto.
	 * @return contactPanel Se devuelve la instancia del panel de contacto.
	 */

	private JPanel createContactPanel() {

		JPanel contactPanel = new JPanel();
		contactPanel.setLayout(new GridLayout(20, 2, 2, 2));

		createContactFields(contactPanel);

		JLabel label = new JLabel("");
		contactPanel.add(label);

		JButton btnEjecutar = new JButton("Insertar");
		contactPanel.add(btnEjecutar);

		return contactPanel;
	}

	/**
	 * Método createContactFields. Método que se encarga de meter los campos de contacto dentro del panel de contactos.
	 * @param contactPanel Panel sobre el que se quieren insertar los campos.
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
	
	/**
	 * Método createCallPanel. Método que se encarga de especificar el panel para la creación de llamadas.
	 * @return callPanel Se devuelve la instancia del panel de llamdas.
	 */
	private JPanel createCallPanel() {
		JPanel callPanel = new JPanel();
		callPanel.setLayout(new GridLayout(5, 2, 2, 2));

		createCallFields(callPanel);

		JLabel label = new JLabel("");
		callPanel.add(label);

		JButton btnEjecutar = new JButton("Insertar");

		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Call call = new Call(new Contact(Integer.parseInt(callTextFields.get(0).getText()), null, null),
						callTextFields.get(1).getText(), callTextFields.get(2).getText());
				GUI.callPersitence.saveCall(call);
			}
		});
		callPanel.add(btnEjecutar);
		return callPanel;
	}
	
	
	/**
	 * Método createCallFields. Método que se encarga de meter los campos de llamada dentro del panel de las llamadas.
	 * @param callPanel Panel sobre el que se tendrán que meter los campos.
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

	/**
	 * Método setView. Método que se encarga de establecer una vista.
	 * @param view Vista a establecer.
	 */
	public void setView(int view) {
		insertPanel.removeAll();
		insertPanel.add(viewDict.get(view));
	}

}