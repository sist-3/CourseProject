package page;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import dao.JongDAO;
import util.LoginManager;
import util.PageManager;
import vo.SubjectVO;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ExamAllListManagementPage extends JPanel {

	List<SubjectVO> s_list;
	private JTable table;
	JongDAO jdao;
	String idx;

	/**
	 * Create the frame.
	 */
	public ExamAllListManagementPage() {
		// mapper 함수 생성
		jdao = new JongDAO();
		if(LoginManager.getInstance().getLoginMember().getChk_role().equals(LoginManager.PROFESSOR)) {
			idx = LoginManager.getInstance().getProfessorInfo().getP_idx();
		}
		
		setBounds(100, 100, 800, 600);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\uC2DC\uD5D8\uAD00\uB9AC");
		lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 30));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		table = new JTable();
		table.setShowGrid(true);
		table.setGridColor(Color.LIGHT_GRAY);
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		s_list = jdao.subjectList(idx); // 교수 로그인 인덱스를 넣어줌
		
		setTable();
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String code = table.getValueAt(table.getSelectedRow(),0).toString();
				ExamSelectListManagementPage eslmp = new ExamSelectListManagementPage(code, idx); // 과목, 교수인덱스
				PageManager.getInstance().changePage(eslmp);
			}

		});
		
		
	}
	
	// 테이블 세팅
	private void setTable() {
		String sb_name[] = {"과목코드","과목명", "과목점수", "담당교수", "수업시작일", "수업종료일", "과목등록일", "강의계획서"};
		String data[][] = new String[s_list.size()][sb_name.length];
		
		for(int i=0; i<s_list.size();i++) {
			SubjectVO svo = s_list.get(i);
			
			data[i][0] = svo.getSb_idx();
			data[i][1] = svo.getSb_name();
			data[i][2] = svo.getSb_point();
			data[i][3] = svo.getSb_mgr();
			data[i][4] = svo.getSb_start_date();
			data[i][5] = svo.getSb_end_date();
			data[i][6] = svo.getSb_date();
			data[i][7] = svo.getSb_plan_file();
			
		}
		
		table.setModel(new DefaultTableModel(data, sb_name));
	}
}
