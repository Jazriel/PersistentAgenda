package gui.selectTab;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import persistence.IFacadeContactTypePersistence;

public class SelectContactTypeState implements SelectState {
	
	private JPanel view;


	public SelectContactTypeState(IFacadeContactTypePersistence iFacadeContactTypePersistence) {

		JPanel selectPanel = new JPanel();

		Button button = new Button("Ejecutar");
		selectPanel.add(button);
		contactTypeListener(button);

		view = selectPanel;
	}

	private void contactTypeListener(Button button) {
		// TODO Auto-generated method stub
	}
	
	private void filOrdListener(JComboBox filOrdCombo, JTextField filterTextField) {
		filOrdCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filOrdCombo.getSelectedIndex() == 1) {
					filterTextField.setVisible(true);
				} else {
					filterTextField.setVisible(false);
				}
				// selectPanel.repaint();
			}
		});
	}
	
	
	@Override
	public JPanel getView() {
		return view;
	}
}
