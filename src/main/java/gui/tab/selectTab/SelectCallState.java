package gui.tab.selectTab;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.SelectResultWindow;
import gui.tab.ICallPersistUser;
import model.Call;
import persistence.IFacadeCallPersistence;

public class SelectCallState implements SelectState, ICallPersistUser {

	private IFacadeCallPersistence callPersistence;

	private JPanel view;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SelectCallState(IFacadeCallPersistence callPersistence) {
		this.callPersistence = callPersistence;

		JPanel selectPanel = new JPanel();

		JLabel lblPor = new JLabel("por");

		JTextField filterTextField = new JTextField();
		filterTextField.setColumns(10);
		selectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JComboBox filOrdCombo = new JComboBox();
		filOrdCombo.setModel(new DefaultComboBoxModel(new String[] { "Filtrar", "Ordenar" }));
		selectPanel.add(filOrdCombo);
		selectPanel.add(lblPor);

		filOrdListener(filOrdCombo, filterTextField);

		JComboBox fieldCombo = new JComboBox();
		fieldCombo.setModel(new DefaultComboBoxModel(new String[] { "Contacto", "Fecha" }));
		selectPanel.add(fieldCombo);
		selectPanel.add(filterTextField);

		dateComboListener(fieldCombo, filterTextField);

		Button button = new Button("Ejecutar");
		selectPanel.add(button);
		callListener(button, fieldCombo, fieldCombo, filterTextField);

		this.view = selectPanel;
	}

	@SuppressWarnings("rawtypes")
	private void callListener(Button button, JComboBox filOrdCombo, JComboBox fieldCombo, JTextField filterTextField) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Call> calls = new ArrayList<>();
				if (filOrdCombo.getSelectedIndex() == 0) {
					if (fieldCombo.getSelectedIndex() == 0) {
						calls = callPersistence.getFilterCalls("contact_id",
								Integer.parseInt(filterTextField.getText()));
					} else {
						// Parseo a TimeStamp
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date date = null;
						try {
							date = dateFormat.parse(filterTextField.getText());
						} catch (ParseException e) {
							System.err.println(e.getMessage());
						}
						Timestamp timeStamp = new Timestamp(date.getTime());
						System.out.println(timeStamp.toString());
						calls = callPersistence.getFilterCalls("call_Date", timeStamp);
					}
				} else {
					if (fieldCombo.getSelectedIndex() == 0) {
						calls = callPersistence.getOrderCalls("contact_id");
					} else {
						calls = callPersistence.getOrderCalls("call_Date");
					}
				}
				String[] contactsStrings = new String[calls.size()];
				//
				for (int i = 0; i < contactsStrings.length; i++) {
					contactsStrings[i] = calls.get(i).toString();
				}
				// Mostramos contactos
				@SuppressWarnings("unused")
				SelectResultWindow srw = new SelectResultWindow(contactsStrings);
			}
		});
	}

	@SuppressWarnings("rawtypes")
	private void dateComboListener(JComboBox fieldCombo, JTextField filterTextField) {
		fieldCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				filterTextField.setText("");
				if (fieldCombo.getSelectedIndex() == 1) {
					filterTextField.setText("dd/mm/yyyy");
				}
			}
		});
	}

	@SuppressWarnings("rawtypes")
	private void filOrdListener(JComboBox filOrdCombo, JTextField filterTextField) {
		filOrdCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filOrdCombo.getSelectedIndex() == 0) {
					filterTextField.setVisible(true);
				} else {
					filterTextField.setVisible(false);
				}
				// This was tried before and didnt
				// work->selectPanel.repaint();
			}
		});
	}

	public JPanel getView() {
		return view;
	}

	@Override
	public void setPersistence(IFacadeCallPersistence cp) {
		this.callPersistence = cp;
		
	}
}
