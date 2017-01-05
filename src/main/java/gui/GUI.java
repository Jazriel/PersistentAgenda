package gui;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField filterTextField;
	private JComboBox typeCombo;
	private final String[] types = new String[] { "Contactos", "Llamadas", "Tipos de contacto" };
	private final String[] persistenceTypes = new String[] { "Base de datos", "Ficheros binario" };
	private DefaultComboBoxModel[] fieldComboModel = { new DefaultComboBoxModel(new String[] { "Apellido", "Nombre" }),
			new DefaultComboBoxModel(new String[] { "Contacto", "Fecha" }) };
	private JComboBox consultFieldCombo;
	private JLabel lblPor;
	private JComboBox consultFilOrdCombo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		initializeMainWindow();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);

		initializeComboButsBar(tabbedPane, gl_contentPane);

		initializeTabBar(tabbedPane, gl_contentPane);

		initializeActionListeners();
	}

	private void initializeActionListeners() {
		typeCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultFilOrdCombo.setSelectedIndex(0);
				if (typeCombo.getSelectedItem().toString() == types[0]) {
					consultFieldCombo.setModel(fieldComboModel[0]);
					setVisibilityConsultTab(true);
				} else if (typeCombo.getSelectedItem().toString() == types[1]) {
					consultFieldCombo.setModel(fieldComboModel[1]);
					setVisibilityConsultTab(true);
				} else {
					setVisibilityConsultTab(false);
				}
			}

			
		});
	}
	
	private void setVisibilityConsultTab(boolean visibility) {
		filterTextField.setVisible(visibility);
		lblPor.setVisible(visibility);
		consultFieldCombo.setVisible(visibility);
		consultFilOrdCombo.setVisible(visibility);
	}
	
	private void initializeTabBar(JTabbedPane tabbedPane, GroupLayout gl_contentPane) {
		JPanel updatePanel = new JPanel();
		tabbedPane.addTab("Actualizar", null, updatePanel, null);

		JPanel requestPanel = new JPanel();
		tabbedPane.addTab("Consultar", null, requestPanel, null);

		JPanel insertPanel = new JPanel();
		tabbedPane.addTab("Insertar", null, insertPanel, null);
		contentPane.setLayout(gl_contentPane);

		initializeConsultTab(requestPanel);
	}

	private void initializeConsultTab(JPanel requestPanel) {
		DefaultComboBoxModel filOrdComboModel = new DefaultComboBoxModel(new String[] { "Filtrar", "Ordenar" });

		lblPor = new JLabel("por");

		filterTextField = new JTextField();
		filterTextField.setColumns(10);
		requestPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		consultFilOrdCombo = new JComboBox();
		consultFilOrdCombo.setModel(filOrdComboModel);
		requestPanel.add(consultFilOrdCombo);
		requestPanel.add(lblPor);

		consultFieldCombo = new JComboBox();
		consultFieldCombo.setModel(fieldComboModel[0]);
		requestPanel.add(consultFieldCombo);
		requestPanel.add(filterTextField);

		Button button = new Button("Ejecutar");
		requestPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}

	private void initializeComboButsBar(JTabbedPane tabbedPane, GroupLayout gl_contentPane) {
		JPanel panel = new JPanel();
		gl_contentPane
				.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 262,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(162, Short.MAX_VALUE))
								.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)));

		JComboBox persistCombo = new JComboBox();
		panel.add(persistCombo);
		persistCombo.setModel(new DefaultComboBoxModel(persistenceTypes));

		typeCombo = new JComboBox();

		typeCombo.setModel(new DefaultComboBoxModel(types));
		panel.add(typeCombo);
	}

	private void initializeMainWindow() {
		// Inicialización de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}
}
