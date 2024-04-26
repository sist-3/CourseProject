package page;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.SubjectVO;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;

public class ExamAllListManagementPage extends JPanel {

	private JPanel panel;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	List<SubjectVO> list;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public ExamAllListManagementPage() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\uC2DC\uD5D8\uAD00\uB9AC");
		lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 30));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		table = new JTable();
		panel.add(table, BorderLayout.CENTER);
		
		SqlSession ss = factory.openSession();
		list = ss.selectList("subject.all");
		
		setTable();
		
		if(ss != null) 
			ss.close();
		
	}
	
	private void setTable() {
		String sb_name[] = {"가나다", "가나다", "가나다", "가나다", "가나다", "가나다", "가나다", "가나다"};
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
