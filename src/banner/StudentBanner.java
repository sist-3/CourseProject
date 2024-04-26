package banner;

import javax.swing.JPanel;

import component.menu.Menu;

public class StudentBanner extends JPanel {

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private String[][] menuItems = new String[][]{
        {"나의 정보"},
        {"나의 과목"},
        {"시험"},
    };
	
	public StudentBanner() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 600);
		add(panel);
		panel.setLayout(null);
		menu = new Menu(menuItems, "student");
		menu.setBounds(0, 0, 200, 600);
		panel.add(menu);
	}
}
