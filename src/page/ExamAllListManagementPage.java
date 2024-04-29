package page;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.JongDAO;
import util.MybatisManager;
import vo.SubjectVO;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ExamAllListManagementPage extends JPanel {

	// 멤버변수
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	List<SubjectVO> list;
	private JTable table;
	JongDAO jdao;

	/**
	 * Create the frame.
	 */
	public ExamAllListManagementPage() {
		// mapper 함수 생성
		jdao = new JongDAO();
		
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
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		SqlSession ss = factory.openSession();
		list = ss.selectList("jong.subject"); // 로그인 인덱스 넣어줘야됨
		
		setTable();
		
		if(ss != null) 
			ss.close();
		
	}
	
	// 테이블 세팅
	private void setTable() {
		String sb_name[] = {"과목명", "과목점수", "담당교수", "과목여부", "수업시작일", "수업종료일", "과목등록일", "강의계획서"};
		String data[][] = new String[list.size()][sb_name.length];
		
		for(int i=0; i<list.size();i++) {
			SubjectVO svo = list.get(i);
			
			data[i][0] = svo.getSb_name();
			data[i][1] = svo.getSb_point();
			data[i][2] = svo.getSb_mgr();
			data[i][3] = svo.getSb_yn();
			data[i][4] = svo.getSb_start_date();
			data[i][5] = svo.getSb_end_date();
			data[i][6] = svo.getSb_date();
			data[i][7] = svo.getSb_plan_file();
			
		}
		
		table.setModel(new DefaultTableModel(data, sb_name));
	}
}
