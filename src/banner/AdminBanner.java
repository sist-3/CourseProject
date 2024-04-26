package banner;

import javax.swing.JPanel;

import component.menu.Menu;

public class AdminBanner extends JPanel{

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private String[][] menuItems = new String[][]{
        {"교수 관리"},
        {"학생 관리"},
        {"시험 관리"},
        {"성취도 관리"},
    };
	
	public AdminBanner() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 600);
		add(panel);
		panel.setLayout(null);
		menu = new Menu(menuItems, "admin");
		menu.setBounds(0, 0, 200, 600);
		panel.add(menu);
	}
}
