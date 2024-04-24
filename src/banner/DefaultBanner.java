package banner;

import javax.swing.JPanel;

import component.menu.Menu;

public class DefaultBanner extends JPanel {

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private String[][] menuItems = new String[][]{

    };

	/**
	 * Create the panel.
	 */
	public DefaultBanner() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 600);
		add(panel);
		panel.setLayout(null);
		menu = new Menu(menuItems, null);
		menu.setBounds(0, 0, 200, 600);
		panel.add(menu);
	}
}
