package page;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mysql.cj.xdevapi.SessionFactory;

import dialog.AddStudentDialog;
import dialog.DetailStudentDialog;
import util.MybatisManager;
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
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();

	/**
	 * Create the panel.
	 */
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
				
				new AddStudentDialog(StudentManagementPage.this);
			}
		});
		btnNewButton.setBounds(18, 88, 57, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("수정");
		btnNewButton_1.setBounds(84, 88, 57, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("삭제");
		btnNewButton_2.setBounds(153, 88, 57, 23);
		panel.add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(563, 89, 116, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("검색");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData();
			}
		});
		btnNewButton_3.setBounds(691, 88, 97, 23);
		panel.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 121, 776, 469);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				int row = table.getSelectedRow();
				StudentVO vo = list.get(row); // list는 StudentManagementPage의 리스트 필드
		        new DetailStudentDialog(vo);
	            //StudentManagePage 에서 마우스클릭할때 안에있는 데이터를 vo로 저장한걸 활용해서 데이터를 누르면 DetailStudentDialog창에 있는 텍스트필드에 각각 표시해줘
 			}
		});
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"학번", "이름", "연락처", "주소", "입학일", "졸업일", "생년월일", "존재여부"}));
		comboBox.setBounds(482, 88, 69, 23);
		panel.add(comboBox);
	}
	
	public void totalStudent(Map<String, String> map) {
		// Mybatis환경의 sql문을 호출하기 위해 sqlsession을 준비하자
		
		SqlSession ss = factory.openSession();
		list = ss.selectList("search_student", map);
		// 받은 list를 jtable로 표현해야 한다.
		viewTable(list);
	}

	private void viewTable(List<StudentVO> list) {
		String[] c_name = { "학번", "이름", "연락처", "주소", "입학일", "졸업일", "생년월일", "존재여부" };
		// 인자로 받으 list를 2차원배열로 만들어보자!
		String[][] data = new String[list.size()][c_name.length];

		for (int i = 0; i < list.size(); i++) {
			// list로부터 EmpVO를 하나 얻어낸다.
			StudentVO vo = list.get(i);
			// 얻어낸 사원 정보를 JTable에 하나의 행으로 표현하기
			// 위해 1차원 배열에 채워넣는다.
			data[i][0] = vo.getSt_num();
			data[i][1] = vo.getSt_name();
			data[i][2] = vo.getSt_tel();
			data[i][3] = vo.getSt_addr();
			data[i][4] = vo.getSt_indate();
			data[i][5] = vo.getSt_outdate();
			data[i][6] = vo.getSt_birth();
			data[i][7] = vo.getSt_yn();

		}
		table.setModel(new DefaultTableModel(data, c_name));
	}

	private void searchData() {

		int index = comboBox.getSelectedIndex();

		String str = textField.getText().trim();
		if (str.length() > 0) {

		}

		Map<String, String> map = new HashMap<String, String>();

		switch (index) {

		case 0:
			map.put("St_num", str);
			break;
		case 1:
			map.put("St_name", str);
			break;
		case 2:
			map.put("St_tel", str);
			break;
		case 3:
			map.put("St_addr", str);
			break;
		case 4:
			map.put("St_indate", str);
			break;
		case 5:
			map.put("St_outdate", str);
			break;
		case 6:
			map.put("St_birth", str);
			break;
		case 7:
			map.put("St_yn", str);
			break;

		}
		totalStudent(map);
	}

	public int addStudent(StudentVO vo) {
		SqlSession ss = factory.openSession();

		int cnt = ss.insert("gummo.add", vo);
		if (cnt > 0)
			ss.commit();
		else
			ss.rollback();

		if (ss != null)
			ss.close();
		return cnt;

	}

}
