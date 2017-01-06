package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class SelectTab {

	private JLabel lblPor;
	private JTextField filterTextField;
	private JComboBox fieldCombo;
	private JComboBox filOrdCombo;
	private final String[] filOrdStrings = new String[] { "Filtrar", "Ordenar" };
	private DefaultComboBoxModel[] fieldComboModel = { new DefaultComboBoxModel(new String[] { "Apellido", "Nombre" }),
			new DefaultComboBoxModel(new String[] { "Contacto", "Fecha" }) };

	
	public SelectTab(JTabbedPane tabbedPane) {

		JPanel requestPanel = new JPanel();
		tabbedPane.addTab("Consultar", null, requestPanel, null);
		
		DefaultComboBoxModel filOrdComboModel = new DefaultComboBoxModel(filOrdStrings);

		lblPor = new JLabel("por");

		filterTextField = new JTextField();
		filterTextField.setColumns(10);
		requestPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		filOrdCombo = new JComboBox();
		filOrdCombo.setModel(filOrdComboModel);
		requestPanel.add(filOrdCombo);
		requestPanel.add(lblPor);

		fieldCombo = new JComboBox();
		fieldCombo.setModel(fieldComboModel[0]);
		requestPanel.add(fieldCombo);
		requestPanel.add(filterTextField);

		Button button = new Button("Ejecutar");
		requestPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
	
	
	public void setListeners() {
		filOrdCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filOrdCombo.getSelectedItem().toString() == filOrdStrings[0]) {
					filterTextField.setVisible(true);
				} else {
					filterTextField.setVisible(false);
				}
			}
		});
	}
	
	public void setVisibility(boolean visibility) {
		filOrdCombo.setSelectedIndex(0);
		filterTextField.setVisible(visibility);
		lblPor.setVisible(visibility);
		fieldCombo.setVisible(visibility);
		filOrdCombo.setVisible(visibility);
	}
	
	public void setVisibility(boolean visibility, int index) {
		setVisibility(visibility);
		fieldCombo.setModel(fieldComboModel[index]);
	}
	
}
