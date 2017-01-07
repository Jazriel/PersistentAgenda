package gui;

import java.awt.GridLayout;
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
/**
 * UpdateTab. Clase que se encarga de las actualizaciones.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class UpdateTab {

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
	 * updatePanel
	 */
	private JPanel updatePanel;
	/**
	 * viewDict
	 */
	private Map<Integer, JPanel> viewDict;

	/**
	 * Método UptadeTab. Constructor de la clase.
	 * @param tabbedPane Panel sobre el que trabajar.
	 */
	public UpdateTab(JTabbedPane tabbedPane) {

		updatePanel = new JPanel();
		tabbedPane.addTab("Actualizar", null, updatePanel, null);
		
		viewDict = new HashMap<>();
		viewDict.put(0, createContactPanel());
		viewDict.put(1, createCallPanel());
		viewDict.put(2, createContactTypePanel());
		updatePanel.add(viewDict.get(0));
	}

	/**
	 * Método createConatactTypePanel. Método que se encarga de especificar el panel para la creación de tipos de contacto.
	 * @return contactTypePanel Se devuelve la instancia del panel de tipo de contacto.
	 */
	private JPanel createContactTypePanel() {
		JPanel contactTypePanel = new JPanel();

		contactTypePanel.setLayout(new GridLayout(3, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto a modificar:");
		contactTypePanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();
		comboStrings = new String[] { "a", "b" }; // TODO llamar persistencia.
		comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
		contactTypePanel.add(comboBox);

		JLabel lblNombreDelTipo = new JLabel("Nombre del tipo de contacto:");
		contactTypePanel.add(lblNombreDelTipo);

		textField = new JTextField();
		contactTypePanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("");
		contactTypePanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
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

		JLabel lblTipoDeContacto = new JLabel("Contacto a modificar:");
		contactPanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();
		comboStrings = new String[] { "a", "b" }; // TODO llamar persistencia.
		comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
		contactPanel.add(comboBox);

		createContactFields(contactPanel);

		JLabel label = new JLabel("");
		contactPanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
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
		callPanel.setLayout(new GridLayout(10, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Llamada a modificar:");
		callPanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();
		comboStrings = new String[] { "a", "b" }; // TODO llamar persistencia.
		comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
		callPanel.add(comboBox);

		createCallFields(callPanel);

		JLabel label = new JLabel("");
		callPanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
		callPanel.add(btnEjecutar);
		return callPanel;
	}	
	
	/**
	 * Método createCallFields. Método que se encarga de meter los campos de llamada dentro del panel de las llamadas.
	 * @param callPanel Panel sobre el que se tendrán que meter los campos.
	 */
	private void createCallFields(JPanel callPanel) {
		contactTextFields = new ArrayList<>();
		String[] fieldsString = { "Contacto", "Fecha", "Asunto", "Notas"};
		List<String> fields = Arrays.asList(fieldsString);
		for (String field : fields) {
			JLabel fieldLabel = new JLabel(field);
			callPanel.add(fieldLabel);

			JTextField jTextField = new JTextField();
			contactTextFields.add(jTextField);
			callPanel.add(jTextField);
			jTextField.setColumns(10);
		}
	}
	
	/**
	 * Método setView. Método que se encarga de establecer una vista.
	 * @param view Vista a establecer.
	 */
	public void setView(int view) {
		updatePanel.removeAll();
		updatePanel.add(viewDict.get(view));
		
	}
	
	
	
}