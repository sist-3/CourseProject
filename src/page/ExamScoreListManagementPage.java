package page;

import java.util.List;

import javax.swing.JPanel;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.ExamJoinVO;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExamScoreListManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	private JTextField textField;
	private JTable table;
	List<ExamJoinVO> ejvo_list;
	JPanel panel;

	/**
	 * Create the frame.
	 */
	public ExamScoreListManagementPage() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\uACFC\uBAA9\uBA85");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\uAC80\uC0C9");
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		SqlSession ss = factory.openSession();
		ejvo_list = ss.selectList("jong.exam_join");		
		
		setTable();
	}
	
	private void setTable() {
		String[] st_header = {"학생명", "점수", "답변확인"};
		Object[][] st_data = new Object[ejvo_list.size()][st_header.length];
		
		for(int i=0;i<ejvo_list.size();i++) {
			ExamJoinVO ejvo = ejvo_list.get(i);
			
			st_data[i][0] = ejvo.getStvo();
			st_data[i][1] = ejvo.getEj_score();
			st_data[i][2] = ejvo.getEvo();
		}
	}

}
