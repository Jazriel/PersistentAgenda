	package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class SelectResultWindow  extends JFrame {

	private JPanel contentPane;
	private JList<String> list;

	/**
	 * Launch the application.
	 */

	public SelectResultWindow(String[] list) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectResultWindow window = new SelectResultWindow(list,true);
					window.setVisible(true);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private SelectResultWindow(String[] list, boolean b) {
		initialize(list);
	}

	/**
	 * Initialize the contents of the pane.
	 */
	private void initialize(String[] list) {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane scroll = new JScrollPane(contentPane);

		setContentPane(scroll);
		
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.list = new JList<>(list);
		contentPane.add(this.list);
	}

}
