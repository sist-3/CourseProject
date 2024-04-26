package page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.SubjectVO;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.Reader;
import java.util.List;

import javax.swing.JTable;
import java.awt.FlowLayout;

public class ExamAllListManagementPage extends JFrame {

	private JPanel contentPane;
	SqlSessionFactory factory;
	List<SubjectVO> list;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamAllListManagementPage frame = new ExamAllListManagementPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExamAllListManagementPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\uC2DC\uD5D8\uAD00\uB9AC");
		lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 30));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		table = new JTable();
		panel.add(table, BorderLayout.CENTER);
		
		createFactory();
		
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
	
	private void createFactory() {
		try {
			Reader r = Resources.getResourceAsReader("config/config.xml"); 
			
			factory = new SqlSessionFactoryBuilder().build(r);
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
