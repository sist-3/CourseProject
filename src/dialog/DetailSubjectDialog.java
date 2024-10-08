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

import page.SubjectManagementPage;
import util.MybatisManager;
import vo.StudentVO;
import vo.SubjectVO;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class DetailSubjectDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField file_tf;
	private JTable table;
	private JTextField name_tf;
	private JTextField mgr_tf;
	SubjectManagementPage p;
	SubjectVO vo;
	
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();

	/**
	 * Launch the application.
	 * @wbp.parser.constructor
	 */

	public DetailSubjectDialog(SubjectManagementPage p, SubjectVO vo) {
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
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 484, 328);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("과목명:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(23, 20, 57, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("담당교수:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(275, 20, 57, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("강의계획서:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(23, 48, 66, 15);
				panel.add(lblNewLabel);
			}
			{
				file_tf = new JTextField();
				file_tf.setEditable(false);
				file_tf.setBounds(92, 45, 116, 21);
				panel.add(file_tf);
				file_tf.setColumns(10);
			}
			{
				JScrollPane scrollPane =  new JScrollPane();
				scrollPane.setBounds(12, 76, 460, 242);
				panel.add(scrollPane);
				{
					table = new JTable();
					table.setShowGrid(true);
					table.setGridColor(Color.LIGHT_GRAY);
					scrollPane.setViewportView(table);
					
				}
			}

			name_tf = new JTextField();
			name_tf.setEditable(false);
			name_tf.setBounds(68, 17, 161, 21);
			panel.add(name_tf);
			name_tf.setColumns(10);

			mgr_tf = new JTextField();
			mgr_tf.setEditable(false);
			mgr_tf.setBounds(344, 17, 116, 21);
			panel.add(mgr_tf);
			mgr_tf.setColumns(10);
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
			if (vo != null) {
				name_tf.setText(vo.getSb_name());
				mgr_tf.setText(vo.getSb_mgr());
				file_tf.setText(vo.getSb_plan_file());
				
			

			}
			
			enrollStudent(vo.getSb_idx());
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
		
	}
	 public void enrollStudent(String sb_idx) {
	        SqlSession session = factory.openSession();
	        List<StudentVO> enrollStudents = session.selectList("enroll_subject", sb_idx);
	        session.close();
	        
	        viewTable(enrollStudents);
	    }

	 private void viewTable(List<StudentVO> enrollStudents) {
	        String[] columnNames = {"학번", "이름", "생년월일", "연락처", "주소",};
	        String[][] data = new String[enrollStudents.size()][columnNames.length];

	        for (int i = 0; i < enrollStudents.size(); i++) {
	            StudentVO student = enrollStudents.get(i);
	            data[i][0] = student.getSt_num();
	            data[i][1] = student.getSt_name();
	            data[i][2] = student.getSt_birth();
	            data[i][3] = student.getSt_tel();
	            data[i][4] = student.getSt_addr();

	        }

	        table.setModel(new DefaultTableModel(data, columnNames));
	    }
	
	
	
	
}
