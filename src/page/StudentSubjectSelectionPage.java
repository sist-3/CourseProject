package page;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.jeong2_DAO;
import util.MybatisManager;
import vo.StudentSubjectVO;
import vo.SubjectVO;

import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentSubjectSelectionPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable subjectSelection_table;
	private JTextField search_text;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	//String st_idx = "2"; //로그인한 학생의 st_idx값
	jeong2_DAO jDAO = new jeong2_DAO();

	/**
	 * Create the panel.
	 */
	public StudentSubjectSelectionPage(String n) {
		setLayout(null);
		
		JPanel subjectSelection_panel = new JPanel();
		subjectSelection_panel.setBounds(0, 0, 800, 600);
		add(subjectSelection_panel);
		subjectSelection_panel.setLayout(null);
		
		JLabel subjectSelection_label = new JLabel("");
		subjectSelection_label.setBounds(15, 15, 120, 30);
		subjectSelection_label.setIcon(new ImageIcon("C:\\Users\\wjddl\\Downloads\\수강신청.png"));
		subjectSelection_panel.add(subjectSelection_label);
		
		subjectSelection_table = new JTable();
		subjectSelection_table.setBounds(15, 60, 745, 525);
		//subjectSelection_panel.add(subjectSelection_table);
		
		JScrollPane scrollPane = new JScrollPane(subjectSelection_table);
		scrollPane.setBounds(15, 60, 745, 428);
		subjectSelection_panel.add(scrollPane);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setForeground(new Color(128, 128, 128));
		scrollBar.setBackground(new Color(255, 255, 255));
		scrollBar.setBounds(771, 15, 17, 575);
		subjectSelection_panel.add(scrollBar);
		
		JButton search_button = new JButton("검색");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stsb();
			}
		});
		search_button.setBounds(663, 22, 97, 23);
		subjectSelection_panel.add(search_button);
		
		search_text = new JTextField();
		search_text.setBounds(479, 22, 172, 23);
		subjectSelection_panel.add(search_text);
		search_text.setColumns(10);
		
		JPanel button_panel = new JPanel();
		button_panel.setBounds(15, 498, 745, 92);
		subjectSelection_panel.add(button_panel);
		button_panel.setLayout(null);
		
		JButton sub_selection_Button = new JButton("수강신청");
		sub_selection_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sbse(n);
			}
		});
		sub_selection_Button.setBounds(648, 10, 97, 72);
		button_panel.add(sub_selection_Button);
		
		total();

	}
	
	public void total() { //전체 과목 목록 표기

		List<SubjectVO> list = jDAO.total();
		String[] set_head = {"과목명","학점","담당교수","등록여부","과목시작일","과목종료일","과목종료일","강의계획서"};
		String[][] list2 = new String[list.size()][set_head.length];
		
		for(int i=0; i<list.size(); i++) {
			SubjectVO vo = list.get(i);

				list2[i][0] = vo.getSb_name();
				list2[i][1] = vo.getSb_point();
				list2[i][2] = vo.getSb_mgr();
				list2[i][3] = vo.getSb_yn();
				list2[i][4] = vo.getSb_start_date();
				list2[i][5] = vo.getSb_end_date();
				list2[i][6] = vo.getSb_date();
				list2[i][7] = vo.getSb_plan_file();
					
		}
		subjectSelection_table.setModel(new DefaultTableModel(list2, set_head));

	}
	
	public void stsb() { //테이블에 표시된 과목 중 과목명으로 검색

		String str = search_text.getText().toString();
		List<SubjectVO> list = jDAO.stsb(str);
		String[] set_head = {"과목명","담당교수","학점","등록여부","과목시작일","과목종료일","과목종료일","강의계획서"};
		String[][] list2 = new String[list.size()][set_head.length];
		
		for(int i=0; i<list.size(); i++) {
			SubjectVO vo = list.get(i);

				list2[i][0] = vo.getSb_name();
				list2[i][1] = vo.getSb_mgr();
				list2[i][2] = vo.getSb_point();
				list2[i][3] = vo.getSb_yn();
				list2[i][4] = vo.getSb_start_date();
				list2[i][5] = vo.getSb_end_date();
				list2[i][6] = vo.getSb_date();
				list2[i][7] = vo.getSb_plan_file();
					
		}
		subjectSelection_table.setModel(new DefaultTableModel(list2, set_head));

		
	}
	
	public void sbse(String n) {//수강신청 버튼을 클릭하면 해당 과목인덱스, 학생인덱스, 교수인덱스, 현재날짜, 수강신청 yn유무 값 db에 저장
		
		int i = subjectSelection_table.getSelectedRow()+1;
		String ii = i+"";
		
		
		 Map<String, Object> map = new HashMap<>(); //인자로 받은 st_idx와 테이블행을 통해 변환한 sb_idx값을 map에 저장
		    map.put("sb_idx", ii);
		    map.put("st_idx", n);
		    
		 jDAO.sbse(map);
	}
	
}
