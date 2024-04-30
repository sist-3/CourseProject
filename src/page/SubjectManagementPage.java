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

import dialog.AddSubjectDialog;
import dialog.DetailStudentDialog;
import dialog.DetailSubjectDialog;
import dialog.UpdateStudentDialog;
import dialog.UpdateSubjectDialog;
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

public class SubjectManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	JComboBox comboBox;
	List<SubjectVO> list;
	private JTable table;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();

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
				delete();
				deleteSubject(null);
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
				searchData();
			}
		});
		btnNewButton_3.setBounds(691, 88, 97, 23);
		panel.add(btnNewButton_3);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "과목번호", "과목명", "과목학점", "과목담당교수", "과목시작일", "과목종료일", "과목등록일", "존재여부", "강의계획서파일" }));
		comboBox.setBounds(482, 88, 69, 23);
		panel.add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 149, 770, 441);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = table.getSelectedRow();
				SubjectVO vo = list.get(row); // list는 StudentManagementPage의 리스트 필드
//				System.out.println(vo.getSt_name());
				DetailSubjectDialog dialog = new DetailSubjectDialog(vo);
				// StudentManagePage 에서 마우스클릭할때 안에있는 데이터를 vo로 저장한걸 활용해서 데이터를 누르면
				// DetailStudentDialog창에 있는 텍스트필드에 각각 표시해줘
			}
		});
		scrollPane.setViewportView(table);
		table.setDefaultEditor(Object.class, null);
		totalSubject(null);

		JButton btnNewButton_2_1 = new JButton("수정");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row >= 0) {
					int index = table.getSelectedRow();
					SubjectVO vo = list.get(index); // 선택된 행의 데이터를 가져옵니다.
					new UpdateSubjectDialog(SubjectManagementPage.this, vo); // 수정 다이얼로그를 호출하면서 데이터 전달합니다.
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
		list = ss.selectList("student_subject", map);

		viewTable(list);

	}

	private void viewTable(List<SubjectVO> list) {

		String[] c_name = { "과목번호", "과목명", "과목학점", "과목담당교수", "과목시작일", "과목종료일", "과목등록일", "존재여부", "강의계획서파일" };

		String[][] data = new String[list.size()][c_name.length];

		for (int i = 0; i < list.size(); i++) {

			SubjectVO vo = list.get(i);

			data[i][0] = vo.getSb_idx();
			data[i][1] = vo.getSb_name();
			data[i][2] = vo.getSb_point();
			data[i][3] = vo.getSb_mgr();
			data[i][4] = vo.getSb_start_date();
			data[i][5] = vo.getSb_end_date();
			data[i][6] = vo.getSb_date();
			data[i][7] = vo.getSb_yn();
			data[i][8] = vo.getSb_plan_file();

		}
		table.setModel(new DefaultTableModel(data, c_name));
	}

	private void searchData() {

		int index = comboBox.getSelectedIndex();
		String str = textField.getText().trim();

		if (str.length() > 0) {

		}

		Map<String, String> map = new HashMap<>();

		switch (index) {
		case 0:
			map.put("Sb_idx", str);
			break;
		case 1:
			map.put("Sb_name", str);
			break;
		case 2:
			map.put("Sb_point", str);
			break;
		case 3:
			map.put("Sb_mgr", str);
			break;
		case 4:
			map.put("Sb_start_date", str);
			break;
		case 5:
			map.put("Sb_end_date", str);
			break;
		case 6:
			map.put("Sb_date", str);
			break;
		case 7:
			map.put("Sb_yn", str);
			break;
		case 8:
			map.put("Sb_plan_file", str);
			break;

		}
		totalSubject(map);
	}

	public int addSubject(SubjectVO vo) {
		SqlSession ss = factory.openSession();

		int cnt = ss.insert("gummo.add_subject", vo);
		if (cnt > 0)
			ss.commit();
		else
			ss.rollback();

		if (ss != null)
			ss.close();
		return cnt;

	}
	public SubjectVO getVo() {
		int index = table.getSelectedRow();
		if (index >= 0 && index < list.size()) {
			return list.get(index);
		} else {
			return null;
		}
	}

	public int updateSubject(SubjectVO vo) {
		SqlSession ss = factory.openSession();

		try {
			int cnt = ss.update("gummo.update_subject", vo); // UPDATE 쿼리 사용
			if (cnt > 0) {
				ss.commit();
				
			} else {
				ss.rollback();
				
			}
			return cnt;
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
	}



	private void delete() {
		int index = table.getSelectedRow();
		if (index < 0) {
			JOptionPane.showMessageDialog(null, "삭제할 행을 선택해주세요");
		} else {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(index);

			// 데이터베이스에서도 해당 데이터 삭제
			SubjectVO vo = list.get(index);
			if (deleteSubject(vo)) {
				JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삭제되었습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "데이터 삭제에 실패하였습니다.");
			}
		}
	}

	private boolean deleteSubject(SubjectVO vo) {
		SqlSession ss = factory.openSession();
		try {
			int cnt = ss.delete("gummo.delete_subject", vo);
			if (cnt > 0) {
				ss.commit();
				return true;
			} else {
				ss.rollback();
				return false;
			}
		} finally {
			ss.close();
		}
	}
}
