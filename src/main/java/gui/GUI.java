package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	/**
	 * Auto generated by eclipse
	 */
	private static final long serialVersionUID = -7203217001393533800L;
	
	private JPanel contentPane;
	private JComboBox typeCombo;
	private static final String[] types = new String[] { "Contactos", "Llamadas", "Tipos de contacto" };
	private static final String[] persistenceTypes = new String[] { "Base de datos", "Ficheros binario" };
	private SelectTab selectTab;
	private UpdateTab updateTab;
	private InsertTab insertTab;

	private JComboBox persistCombo;


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
		setTitle("Persistent Agenda");
		initializeMainWindow();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);

		initializeComboButsBar(tabbedPane, gl_contentPane);

		initializeTabBar(tabbedPane, gl_contentPane);

		initializeActionListeners();
	}

	private void initializeActionListeners() {
		typeComboListener();
		selectTab.setListeners();
	}

	

	private void typeComboListener() {
		typeCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeCombo.getSelectedItem().toString() == types[0]) {
					selectTab.setVisibility(true, 0);
					updateTab.setView(0);
					insertTab.setView(0);
				} else if (typeCombo.getSelectedItem().toString() == types[1]) {
					selectTab.setVisibility(true, 1);
					updateTab.setView(1);
					insertTab.setView(1);
				} else {
					selectTab.setVisibility(false);
					updateTab.setView(2);
					insertTab.setView(2);
				}
				contentPane.repaint();
			}
		});
	}

	private void persistenceComboListener() {
		persistCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	private void initializeTabBar(JTabbedPane tabbedPane, GroupLayout gl_contentPane) {

		contentPane.setLayout(gl_contentPane);

		insertTab = new InsertTab(tabbedPane);
		
		updateTab = new UpdateTab(tabbedPane); // :D
		
		selectTab = new SelectTab(tabbedPane);
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

		persistCombo = new JComboBox();
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
		
		JScrollPane scroll = new JScrollPane(contentPane);
		
		setContentPane(scroll);
	}
}
