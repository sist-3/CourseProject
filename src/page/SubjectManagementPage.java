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

import dao.gummoDAO;
import dialog.AddSubjectDialog;
import dialog.DetailSubjectDialog;
import dialog.UpdateSubjectDialog;
import util.LoginManager;
import util.MybatisManager;
import vo.StudentVO;
import vo.SubjectVO;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//과목관리페이지
public class SubjectManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	JComboBox comboBox;
	List<SubjectVO> list;
	private JTable table;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	gummoDAO gdao = new gummoDAO();
	
	public SubjectManagementPage() {
		setBounds(100, 100, 800, 600);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("과목관리");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 23));
		lblNewLabel.setBounds(34, 27, 107, 35);
		panel.add(lblNewLabel);

		JButton btnNewButton_2 = new JButton("삭제");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row >= 0) {
				int result = JOptionPane.showConfirmDialog(SubjectManagementPage.this, "삭제하시겠습니까?", null,
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {

					delete();
					gdao.deleteSubject(null);
					totalSubject(null);
				}

				}
				else {
					JOptionPane.showMessageDialog(SubjectManagementPage.this, "삭제할 행을 선택해주세요");
				}
			}
		});
		btnNewButton_2.setBounds(184, 88, 69, 23);
		panel.add(btnNewButton_2);

		textField = new JTextField();
		textField.setBounds(563, 89, 116, 21);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_3 = new JButton("검색");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField == null) {
					totalSubject(null);
				} else {
					searchData();
				}

			}
		});
		btnNewButton_3.setBounds(691, 88, 97, 23);
		panel.add(btnNewButton_3);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "과목명", "학점", "담당교수", "시작일", "종료일", "등록일", "강의계획서" }));
		comboBox.setBounds(454, 88, 97, 23);
		panel.add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 149, 770, 441);
		panel.add(scrollPane);

		table = new JTable();
		table.setShowGrid(true);
		table.setGridColor(Color.LIGHT_GRAY);
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					int row = table.getSelectedRow();
					SubjectVO vo = list.get(row);

					DetailSubjectDialog dialog = new DetailSubjectDialog(SubjectManagementPage.this, vo);
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setDefaultEditor(Object.class, null);
		String p_idx = "";
		Map<String, String> map = new HashMap<>();
		if(LoginManager.getInstance().getLoginMember().getChk_role().equals(LoginManager.PROFESSOR)) {
			p_idx = LoginManager.getInstance().getProfessorInfo().getP_idx();
			map.put("p_idx", p_idx);
			professorSubject(map);
		}else if(LoginManager.getInstance().getLoginMember().getChk_role().equals(LoginManager.ADMIN)) {
			totalSubject(null);
		}
		JButton btnNewButton_2_1 = new JButton("수정");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row >= 0) {
					int index = table.getSelectedRow();
					SubjectVO vo = list.get(index);
					new UpdateSubjectDialog(SubjectManagementPage.this, vo);
				} else {
					JOptionPane.showMessageDialog(null, "수정할 행을 선택해주세요");
				}

			}
		});
		btnNewButton_2_1.setBounds(103, 88, 69, 23);
		panel.add(btnNewButton_2_1);

		JButton btnNewButton_2_2 = new JButton("추가");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddSubjectDialog(SubjectManagementPage.this);
			}
		});
		btnNewButton_2_2.setBounds(22, 88, 69, 23);
		panel.add(btnNewButton_2_2);

	}

	public void totalSubject(Map<String, String> map) {

		SqlSession ss = factory.openSession();
		list = ss.selectList("gummo.search_subject", map);

		viewTable(list);

	}
	public void professorSubject(Map<String, String> map) {

		SqlSession ss = factory.openSession();
		list = ss.selectList("gummo.professor_subject", map);

		viewTable(list);

	}


	private void viewTable(List<SubjectVO> list) {

		String[] c_name = { "과목명", "학점", "담당교수", "시작일", "종료일", "등록일", "강의계획서" };

		String[][] data = new String[list.size()][c_name.length];

		for (int i = 0; i < list.size(); i++) {

			SubjectVO vo = list.get(i);

			// data[i][0] = vo.getSb_idx();
			data[i][0] = vo.getSb_name();
			data[i][1] = vo.getSb_point();
			data[i][2] = vo.getSb_mgr();
			data[i][3] = vo.getSb_start_date();
			data[i][4] = vo.getSb_end_date();
			data[i][5] = vo.getSb_date();
			data[i][6] = vo.getSb_plan_file();

		}
		table.setModel(new DefaultTableModel(data , c_name) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(38);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);

	}

	private void searchData() {
		int index = comboBox.getSelectedIndex();
		String str = textField.getText().trim();

		// 검색할 컬럼명을 설정하기 위한 변수
		String columnName = null;

		switch (index) {

		case 0:
			columnName = "sb_name";
			break;
		case 1:
			columnName = "sb_point";
			break;
		case 2:
			columnName = "sb_mgr";
			break;
		case 3:
			columnName = "sb_start_date";
			break;
		case 4:
			columnName = "sb_end_date";
			break;
		case 5:
			columnName = "sb_date";
			break;

		case 6:
			columnName = "sb_plan_file";
			break;
		}

		// 검색할 컬럼명과 입력값을 맵에 담아서 MyBatis에 전달
		Map<String, String> map = new HashMap<>();
		map.put(columnName, str);

		// totalSubject() 메서드 호출하여 테이블에 검색 결과를 표시
		try {
			totalSubject(map);

		} catch (Exception e) {
			System.out.println("실패");
		}
	}

	public SubjectVO getVo() {
		int index = table.getSelectedRow();
		if (index >= 0 && index < list.size()) {
			return list.get(index);

		} else {
			return null;
		}
	}

	private void delete() {
		int index = table.getSelectedRow();
		
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(index);

			// 데이터베이스에서도 해당 데이터 삭제
			SubjectVO vo = list.get(index);
			if (gdao.deleteSubject(vo)) {
				JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삭제되었습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "데이터 삭제에 실패하였습니다.");
			}
		
	}

}
