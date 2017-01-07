package gui;

import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.Call;
import model.Contact;

/**
 * SelectTab. Clase que se encarga de las selecciones.
 * 
 * @author Javier Martinez
 * @author Daniel Puente
 * @author Jaime Sagüillo
 * @author Jorge Zamora
 * @author Oscar Fernandez
 *
 */
public class SelectTab {

	/**
	 * lblPor.
	 */
	private JLabel lblPor;

	/**
	 * filOrdStrings.
	 */
	private final String[] filOrdStrings = new String[] { "Filtrar", "Ordenar" };
	/**
	 * fieldComboModel.
	 */
	private DefaultComboBoxModel[] fieldComboModel = { new DefaultComboBoxModel(new String[] { "Apellido", "Nombre" }),
			new DefaultComboBoxModel(new String[] { "Contacto", "Fecha" }) };

	private Map<Integer, JPanel> viewDict;
	private JPanel selectPanel;
	private DefaultComboBoxModel filOrdComboModel;

	/**
	 * Método SelectTab. Contructor de la clase.
	 * 
	 * @param tabbedPane
	 *            Panel sobre el que trabajar.
	 */

	public SelectTab(JTabbedPane tabbedPane) {

		selectPanel = new JPanel();
		tabbedPane.addTab("Consultar", null, selectPanel, null);

		filOrdComboModel = new DefaultComboBoxModel(filOrdStrings);

		viewDict = new HashMap<>();
		viewDict.put(0, createContactPanel());
		viewDict.put(1, createCallPanel());
		viewDict.put(2, createContactTypePanel());
		selectPanel.add(viewDict.get(0));
	}

	private JPanel createContactPanel() {

		JPanel selectPanel = new JPanel();

		lblPor = new JLabel("por");

		JTextField filterTextField = new JTextField();
		filterTextField.setColumns(10);
		selectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JComboBox filOrdCombo = new JComboBox();
		filOrdCombo.setModel(filOrdComboModel);
		selectPanel.add(filOrdCombo);
		selectPanel.add(lblPor);

		filOrdListener(filOrdCombo, filterTextField);

		JComboBox fieldCombo = new JComboBox();
		fieldCombo.setModel(fieldComboModel[0]);
		selectPanel.add(fieldCombo);
		selectPanel.add(filterTextField);

		Button button = new Button("Ejecutar");
		selectPanel.add(button);
		contactListener(button, filOrdCombo, fieldCombo, filterTextField);

		return selectPanel;
	}

	private void contactListener(Button button, JComboBox filOrdCombo, JComboBox fieldCombo,
			JTextField filterTextField) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Contact> contacts = new ArrayList<>();
				if (filOrdCombo.getSelectedIndex() == 0) {
					if (fieldCombo.getSelectedIndex() == 0) {
						contacts = MainGUI.contactPersitence.getFilterContacts("name", filterTextField.getText());
					} else {
						contacts = MainGUI.contactPersitence.getFilterContacts("surname", filterTextField.getText());
					}
				} else {
					if (fieldCombo.getSelectedIndex() == 0) {
						contacts = MainGUI.contactPersitence.getOrderContacts("name");
					} else {
						contacts = MainGUI.contactPersitence.getOrderContacts("surname");
					}
				}
				// Mostramos contactos
				
			}
		});
	}

	private JPanel createCallPanel() {

		JPanel selectPanel = new JPanel();

		lblPor = new JLabel("por");

		JTextField filterTextField = new JTextField();
		filterTextField.setColumns(10);
		selectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JComboBox filOrdCombo = new JComboBox();
		filOrdCombo.setModel(filOrdComboModel);
		selectPanel.add(filOrdCombo);
		selectPanel.add(lblPor);

		filOrdListener(filOrdCombo, filterTextField);

		JComboBox fieldCombo = new JComboBox();
		fieldCombo.setModel(fieldComboModel[1]);
		selectPanel.add(fieldCombo);
		selectPanel.add(filterTextField);

		Button button = new Button("Ejecutar");
		selectPanel.add(button);
		callListener(button);

		return selectPanel;
	}

	private void callListener(Button button) {
		// TODO Auto-generated method stub

	}

	private JPanel createContactTypePanel() {

		JPanel selectPanel = new JPanel();

		Button button = new Button("Ejecutar");
		selectPanel.add(button);
		contactTypeListener(button);

		return selectPanel;
	}

	private void contactTypeListener(Button button) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método setListeners. Método que se encarga de establecer los listeners.
	 */
	private void filOrdListener(JComboBox filOrdCombo, JTextField filterTextField) {
		filOrdCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filOrdCombo.getSelectedItem().toString() == filOrdStrings[0]) {
					filterTextField.setVisible(true);
				} else {
					filterTextField.setVisible(false);
				}
				selectPanel.repaint();
			}
		});
	}

	/**
	 * Método setView. Método que se encarga de establecer una vista.
	 * 
	 * @param view
	 *            Vista a establecer.
	 */
	public void setView(int view) {
		selectPanel.removeAll();
		selectPanel.add(viewDict.get(view));

	}
}
