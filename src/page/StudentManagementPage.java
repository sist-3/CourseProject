package page;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mysql.cj.xdevapi.SessionFactory;

import dao.gummoDAO;
import dialog.AddStudentDialog;
import dialog.DetailStudentDialog;
import dialog.UpdateStudentDialog;
import util.LoginManager;
import util.MybatisManager;
import vo.MajorVO;
import vo.StudentVO;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	JComboBox comboBox;
	List<StudentVO> list;
	StudentVO vo;
	MajorVO mvo;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	gummoDAO gdao = new gummoDAO();
	private boolean start;

	/**
	 * Create the panel.
	 */
	//학생관리페이지
	public StudentManagementPage() {
		setBounds(100, 100, 800, 600);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("학생관리");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 23));
		lblNewLabel.setBounds(34, 27, 107, 35);
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("추가");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentDialog diglog = new AddStudentDialog(StudentManagementPage.this, vo);
				
			}
		});
		btnNewButton.setBounds(18, 88, 69, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("수정");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row >= 0) {
					int index = table.getSelectedRow();
					StudentVO vo = list.get(index); // 선택된 행의 데이터를 가져옵니다.
					new UpdateStudentDialog(StudentManagementPage.this, vo); // 수정 다이얼로그를 호출하면서 데이터 전달합니다.
					totalStudent(null);
				} else {
					JOptionPane.showMessageDialog(null, "수정할 행을 선택해주세요");
				}

			}
		});
		btnNewButton_1.setBounds(99, 88, 69, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("삭제");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();

				if (row >= 0) {
				int result = JOptionPane.showConfirmDialog(StudentManagementPage.this, "삭제하시겠습니까?", null,
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {

					delete();
					gdao.deleteStudent(null);
					
					totalStudent(null);
				}

				}
				else {
					JOptionPane.showMessageDialog(StudentManagementPage.this, "삭제할 행을 선택해주세요");
				}
			

			}
				
				
				
			
		});
		btnNewButton_2.setBounds(180, 88, 69, 23);
		panel.add(btnNewButton_2);

		textField = new JTextField();
		textField.setBounds(563, 89, 116, 21);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_3 = new JButton("검색");
		btnNewButton_3.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {
				if(textField == null) {
					totalStudent(null);
				}
				else{
					searchData();
				}
			}
		});
		btnNewButton_3.setBounds(691, 88, 97, 23);
		panel.add(btnNewButton_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 121, 776, 450);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     int row = table.getSelectedRow();
						StudentVO vo = list.get(row);
						
						DetailStudentDialog Ddialog = new DetailStudentDialog(StudentManagementPage.this, vo);
				}
				
			}
		});
	
			

		table.setBackground(new Color(255, 255, 255));
		table.setShowGrid(true);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setDefaultEditor(Object.class, null);

		scrollPane.setViewportView(table);
		totalStudent(null);

		comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] {"학번", "이름", "전공","연락처","생년월일","주소", "입학일", "졸업일"}));
		comboBox.setBounds(468, 88, 83, 23);
		panel.add(comboBox);

	}
	// instance table model

	public void totalStudent(Map<String, String> map) {

		SqlSession ss = factory.openSession();
		boolean isProfessor = LoginManager.getInstance().getLoginMember().getChk_role().equals(LoginManager.PROFESSOR);	
		if(isProfessor) {
		if(map == null) {
			
				map = new HashMap<>();
				
		}
			String m_idx = LoginManager.getInstance().getProfessorInfo().getMvo().getM_idx();
			map.put("m_idx", m_idx);
		}
		list = ss.selectList("gummo.search_student", map);
		viewTable(list);

	}

	private void viewTable(List<StudentVO> list) {

		String[] c_name = { "학번", "이름", "전공", "연락처", "생년월일", "주소", "입학일", "졸업일" };

		String[][] data = new String[list.size()][c_name.length];

		for (int i = 0; i < list.size(); i++) {

			StudentVO vo = list.get(i);

			data[i][0] = vo.getSt_num();
			data[i][1] = vo.getSt_name();
			data[i][2] = vo.getMvo().getM_name();
			data[i][3] = vo.getSt_tel();
			data[i][4] = vo.getSt_birth();
			data[i][5] = vo.getSt_addr();
			data[i][6] = vo.getSt_indate();
			data[i][7] = vo.getSt_outdate();

		}
		table.setModel(new DefaultTableModel(data, c_name

		) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(82);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
	}

	private void searchData() {

		int index = comboBox.getSelectedIndex();

		String str = textField.getText().trim();
		if (str.length() > 0) {

		}

		Map<String, String> map = new HashMap<>();

		switch (index) {

		case 0:
			map.put("st_num", str);
			break;
		case 1:
			map.put("st_name", str);
			break;
		case 2:
			map.put("m_name", str);
			break;
		case 3:
			map.put("st_tel", str);
			break;
		case 4:
			map.put("st_birth", str);
			break;
		case 5:
			map.put("st_addr", str);
			break;
		case 6:
			map.put("st_indate", str);
			break;
		case 7:
			map.put("st_outdate", str);
			break;

		}
		
		totalStudent(map);
		
	}

	public StudentVO getVo() {
		int index = table.getSelectedRow();
		if (index >= 0 && index < list.size()) {
			return list.get(index);
		} else {
			return null;
		}
	}

	private void delete() {
		int index = table.getSelectedRow();
		

			// 데이터베이스에서도 해당 데이터 삭제
			StudentVO vo = list.get(index);
			if (gdao.deleteStudent(vo)) {
				gdao.deleteStLogin(vo);
				JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삭제되었습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "데이터 삭제에 실패하였습니다.");
			}
		}
	

}