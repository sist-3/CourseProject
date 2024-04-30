package page;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.jeong2_DAO;
import util.MybatisManager;
import util.PageManager;
import vo.StudentSubjectVO;
import vo.SubjectVO;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class StudentSubjectManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField search_text;
	private JTable subject_table;
	String n = "2"; //로그인한 학생의 idx값
	private Object st_idx;
	private Object sb_idx;
	jeong2_DAO jDAO = new jeong2_DAO();
	
	/**
	 * Create the panel.
	 */
	public StudentSubjectManagementPage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		JPanel subject_panel = new JPanel();
		subject_panel.setBounds(5, 150, 790, 445);
		panel.add(subject_panel);
		subject_panel.setLayout(null);
		
		subject_table = new JTable();
		subject_table.setBounds(0, 0, 790, 445);
		//subject_panel.add(new JScrollPane(subject_table), BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(subject_table);
		scrollPane.setBounds(0, 0, 790, 445);
		subject_panel.add(scrollPane);
		
		JLabel title_label = new JLabel("");
		title_label.setBounds(15, 15, 120, 30);
		panel.add(title_label);
		title_label.setIcon(new ImageIcon("C:\\Users\\wjddl\\Downloads\\과목관리.png"));
		
		JButton selection_button = new JButton("수강신청");
		selection_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentSubjectSelectionPage name = new StudentSubjectSelectionPage(n);
				PageManager.getInstance().changePage(name);
			}
		});
		selection_button.setBounds(5, 105, 91, 23);
		panel.add(selection_button);
		
		JButton delete_button = new JButton("삭제");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMySubject(n);
			}
		});
		delete_button.setBounds(108, 105, 91, 23);
		panel.add(delete_button);
		
		JButton search_button = new JButton("검색");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				search();
			}
		});
		search_button.setBounds(704, 105, 91, 23);
		panel.add(search_button);
		
		search_text = new JTextField();
		search_text.setBounds(511, 106, 183, 21);
		panel.add(search_text);
		search_text.setColumns(10);
		
		mySubject();

	}
	
	public void mySubject() {
		
		List<SubjectVO> list = jDAO.StudentSubjectManagementPageDAO(n);
			
		String[] set_head = {"과목명","담당교수","학점","등록여부","과목시작일","과목종료일","과목종료일","강의계획서"};
		String[][] list2 = new String[list.size()][set_head.length];
		
		for(int i=0; i<list.size(); i++) {
			SubjectVO svo = list.get(i);

				list2[i][0] = svo.getSb_name();
				list2[i][1] = svo.getSb_mgr();
				list2[i][2] = svo.getSb_point();
				list2[i][3] = svo.getSb_yn();
				list2[i][4] = svo.getSb_start_date();
				list2[i][5] = svo.getSb_end_date();
				list2[i][6] = svo.getSb_date();
				list2[i][7] = svo.getSb_plan_file();
					
		}
		subject_table.setModel(new DefaultTableModel(list2, set_head));

		//jDAO.StudentSubjectManagementPageDAO(vo);
	}
	
	public void search() {
		
		String str = search_text.getText().toString().trim();
		Map<String, String> map = new HashMap<>();
		
		if(str != null) {
			map.put("sb_name", str);
			map.put("st_idx", n);
			List<SubjectVO> list = jDAO.SearchDAO(map);
			
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
			subject_table.setModel(new DefaultTableModel(list2, set_head));
		}else
			JOptionPane.showMessageDialog(null, "검색어를 입력하세요.", "알림", JOptionPane.ERROR_MESSAGE);
			return;
	}
	
	
	public void deleteMySubject(String n) {
		
		String subName = (String) subject_table.getValueAt(subject_table.getSelectedRow(), 0);//선택한 행의 0열의 값을 sunName에 저장
		//System.out.println(subName);
		String subIdx = jDAO.deleteDAO(subName); //추출한 과목명sunName으로 과목인덱스 값 subIdx에 저장
		//System.out.println(subIdx);

		Map<String, String> map = new HashMap<>();
		map.put("st_idx", n); //map에 학생인덱스 저장
		map.put("sb_idx", subIdx); //map에 과목인덱스 저장
		
		jDAO.deleteDAO2(map);
		mySubject(); //새로고침(목록 다시 불러오기)
	}
	
}
