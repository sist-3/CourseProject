package page;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import component.bar.CircleBar;

public class ProgressMonitoringManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProgressMonitoringManagementPage() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		CircleBar circleBar = new CircleBar();
		circleBar.setBounds(128, 222, 205, 188);
		circleBar.setForeground(Color.RED);
		panel.add(circleBar);
	}

}
