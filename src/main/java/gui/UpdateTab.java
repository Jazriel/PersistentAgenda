package gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class UpdateTab {

	private JTextField textField;
	private DefaultComboBoxModel comboBoxModel;
	private String[] comboStrings;
	private JPanel contactTypePanel;
	private JPanel contactPanel;
	private List<JTextField> contactTextFields;

	public UpdateTab(JTabbedPane tabbedPane) {

		JPanel updatePanel = new JPanel();
		tabbedPane.addTab("Actualizar", null, updatePanel, null);

		
		createContactTypePanel();
		createContactPanel();
		updatePanel.add(contactPanel);
	}

	private void createContactTypePanel() {
		contactTypePanel = new JPanel();

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
	}

	private void createContactPanel() {
		
		contactPanel = new JPanel();
		contactPanel.setLayout(new GridLayout(20, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Contacto a modificar:");
		contactPanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();
		comboStrings = new String[] { "a", "b" }; // TODO llamar persistencia.
		comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
		contactPanel.add(comboBox);

		createContactFields();

		JLabel label = new JLabel("");
		contactPanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
		contactPanel.add(btnEjecutar);
	}

	private void createContactFields() {
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
			textField.setColumns(10);
		}
	}
	
	private void createCallPanel() {
		contactPanel = new JPanel();
		contactPanel.setLayout(new GridLayout(20, 2, 2, 2));

		JLabel lblTipoDeContacto = new JLabel("Llamada a modificar:");
		contactPanel.add(lblTipoDeContacto);

		JComboBox comboBox = new JComboBox();
		comboStrings = new String[] { "a", "b" }; // TODO llamar persistencia.
		comboBoxModel = new DefaultComboBoxModel(comboStrings);
		comboBox.setModel(comboBoxModel);
		contactPanel.add(comboBox);

		createCallFields();

		JLabel label = new JLabel("");
		contactPanel.add(label);

		JButton btnEjecutar = new JButton("Ejecutar");
		contactPanel.add(btnEjecutar);
	}	
	
	private void createCallFields() {
		contactTextFields = new ArrayList<>();
		String[] fieldsString = { "Contacto", "Fecha", "Asunto", "Notas"};
		List<String> fields = Arrays.asList(fieldsString);
		for (String field : fields) {
			JLabel fieldLabel = new JLabel(field);
			contactPanel.add(fieldLabel);

			JTextField jTextField = new JTextField();
			contactTextFields.add(jTextField);
			contactPanel.add(jTextField);
			textField.setColumns(10);
		}
	}
	
}