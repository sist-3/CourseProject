package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import page.StudentManagementPage;
import util.MybatisManager;
import vo.StudentVO;
import vo.SubjectVO;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class DetailStudentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField num_tf;
	private JTextField tel_tf;
	private JTextField addr_tf;
	private JTextField name_tf;
	private JTable table;
	StudentManagementPage p;
	StudentVO vo;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	
	/**
	 * Launch the application.
	 * @return 
	 * @wbp.parser.constructor
	 */
	public DetailStudentDialog(StudentManagementPage p, StudentVO vo) {
		this.p = p;
		this.vo = vo;
		init();
		
		
	}

	/**
	 * Create the dialog.
	 */
	
	public void init() {
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 484, 328);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("사진");
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(30, 22, 68, 72);
			panel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("학번:\r\n");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(131, 22, 34, 15);
			panel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("번호:");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(131, 70, 34, 15);
			panel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("이름:");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(301, 22, 34, 15);
			panel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("주소:");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(301, 70, 34, 15);
			panel.add(lblNewLabel_1);
		}
		{
			num_tf = new JTextField();
			num_tf.setEditable(false);
			num_tf.setBounds(167, 22, 112, 21);
			panel.add(num_tf);
			num_tf.setColumns(10);
		}
		{
			tel_tf = new JTextField();
			tel_tf.setEditable(false);
			tel_tf.setColumns(10);
			tel_tf.setBounds(167, 67, 112, 21);
			panel.add(tel_tf);
		}
		{
			addr_tf = new JTextField();
			addr_tf.setEditable(false);
			addr_tf.setColumns(10);
			addr_tf.setBounds(337, 67, 112, 21);
			panel.add(addr_tf);
		}
		{
			name_tf = new JTextField();
			name_tf.setEditable(false);
			name_tf.setColumns(10);
			name_tf.setBounds(337, 22, 112, 21);
			panel.add(name_tf);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 107, 460, 211);
			panel.add(scrollPane);
			{
				table = new JTable();
				table.setShowGrid(true);
				table.setGridColor(Color.LIGHT_GRAY);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("확인");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
		}
		
		if (vo != null) {
	        num_tf.setText(vo.getSt_num());
	        tel_tf.setText(vo.getSt_tel());
	        addr_tf.setText(vo.getSt_addr());
	        name_tf.setText(vo.getSt_name());
	        
	        
	    }
		enrollSubject(vo.getSt_idx());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public void enrollSubject(String st_idx) {
        SqlSession session = factory.openSession();
        List<SubjectVO> enrollSubjects = session.selectList("enroll_student", st_idx);
        session.close();
        
        viewTable(enrollSubjects);
    }
	private void viewTable(List<SubjectVO> enrollSubjects) {

		String[] c_name = { "과목명", "과목학점", "과목담당교수", "강의계획서파일" };

		String[][] data = new String[enrollSubjects.size()][c_name.length];

		for (int i = 0; i < enrollSubjects.size(); i++) {

			SubjectVO vo = enrollSubjects.get(i);

			
			data[i][0] = vo.getSb_name();
			data[i][1] = vo.getSb_point();
			data[i][2] = vo.getSb_mgr();
			data[i][3] = vo.getSb_plan_file();

		}
		table.setModel(new DefaultTableModel(data, c_name));
	}
	
	
}
