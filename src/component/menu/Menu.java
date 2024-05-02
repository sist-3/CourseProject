package component.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import page.ExamAllListManagementPage;
import page.ExamManagementPage;
import page.ProfessorManagementPage;
import page.ProgressMonitoringManagementPage;
import page.StudentExamListManagementPage;
import page.StudentManagementPage;
import page.StudentMyPage;
import page.StudentSubjectManagementPage;
import util.PageManager;

public class Menu extends JComponent {
	
	private String role;
	
    public MenuEvent getEvent() {
        return event;
    }

    public void setEvent(MenuEvent event) {
        this.event = event;
    }

    private MenuEvent event;
    private MigLayout layout;
    private String[][] menuItems = new String[][]{
        {"기본 메뉴"},
        {"String[][] 인자가"},
        {"있는 생성자를"},
        {"사용하세요"},
    };

    public Menu() {
        init();
    }
    
    public Menu(String[][] menuItems, String role) {
    	this.menuItems = menuItems;
    	this.role = role;
        init();
    }

    private void init() {
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);
        //  Init MenuItem
        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i);
        }

    }

    private Icon getIcon(int index) {
        URL url = getClass().getResource("/resources/image/menu/" + role + "/" + index + ".png");
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }
    }

    private void addMenu(String menuName, int index) {
        int length = menuItems[index].length;
        MenuItem item = new MenuItem(menuName, index, length > 1);
        item.setFont(new Font("나눔고딕", Font.BOLD, 20));
        Icon icon = getIcon(index);
        if (icon != null) {
            item.setIcon(icon);
        }
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (length > 1) {
                    if (!item.isSelected()) {
                        item.setSelected(true);
                        addSubMenu(item, index, length, getComponentZOrder(item));
                    } else {
                        //  Hide menu
                        hideMenu(item, index);
                        item.setSelected(false);
                    }
                } else {
                    if (event != null) {
                        event.selected(index, 0);
                    } else {
                    	selectItem(item.getText());
                    }
                }
            }
        });
        add(item);
        revalidate();
        repaint();
    }

    private void addSubMenu(MenuItem item, int index, int length, int indexZorder) {
        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setBackground(new Color(18, 99, 63));
        for (int i = 1; i < length; i++) {
            MenuItem subItem = new MenuItem(menuItems[index][i], i, false);
            subItem.setFont(new Font("나눔고딕", Font.PLAIN, 15));
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (event != null) {
                        event.selected(index, subItem.getIndex());
                    }
                }
            });
            subItem.initSubMenu(i, length);
            panel.add(subItem);
        }
        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        MenuAnimation.showMenu(panel, item, layout, true);
    }

    private void hideMenu(MenuItem item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                MenuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(21, 110, 71));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(grphcs);
    }
    
    private void firstSelectItem(String item_name) {
    	switch(item_name) {
    	case "교수 관리":
    		PageManager.getInstance().changePage(new ProfessorManagementPage());
    		break;
    	case "학생 관리":
    		PageManager.getInstance().changePage(new StudentManagementPage());
    		break;
    	case "시험 관리":
    		PageManager.getInstance().changePage(new ExamAllListManagementPage());
    		break;
    	case "성취도 관리":
    		PageManager.getInstance().changePage(new ProgressMonitoringManagementPage());
    		break;
    	case "나의 정보":
    		PageManager.getInstance().changePage(new StudentMyPage());
    		break;
    	case "나의 과목":
    		PageManager.getInstance().changePage(new StudentSubjectManagementPage());
    		break;
    	case "시험":
    		PageManager.getInstance().changePage(new StudentExamListManagementPage());
    		break;
    	default :
    		System.out.println("등록된 아이템이 아닙니다.");
    	}
    }
    
    private void selectItem(String item_name) {
        String pageName = null;
        switch(item_name) {
        case "교수 관리":
            pageName = "ProfessorManagementPage";
            break;
        case "학생 관리":
            pageName = "StudentManagementPage";
            break;
        case "시험 관리":
            pageName = "ExamManagementPage";
            break;
        case "성취도 관리":
            pageName = "ProgressMonitoringManagementPage";
            break;
        case "나의 정보":
            pageName = "StudentMyPage";
            break;
        case "나의 과목":
            pageName = "StudentSubjectManagementPage";
            break;
        case "시험":
            pageName = "StudentExamListManagementPage";
            break;
        default :
            System.out.println("등록된 아이템이 아닙니다.");
        }
        
        // 페이지가 등록되어 있다면 이미 눌렀던 아이템
        // 페이지가 등록되어 있지 않다면 처음 누른 아이템
        if(pageName != null) {
        	if(PageManager.getInstance().isPageCreated(pageName)) {        		
        		PageManager.getInstance().changePage(pageName);
        	} else {
        		firstSelectItem(item_name);
        	}
        } 
    }

}
