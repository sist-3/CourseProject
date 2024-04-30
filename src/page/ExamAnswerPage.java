package page;

import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.JongDAO;
import vo.ExamSubmitVO;
import javax.swing.JRadioButton;

public class ExamAnswerPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	List<ExamSubmitVO> es_list;

	public ExamAnswerPage(String code) {
		JongDAO jdao = new JongDAO();
		es_list = jdao.examSubmit(code);
		
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("답안 확인");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 26));
		panel.add(lblNewLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		scrollPane.setColumnHeaderView(rdbtnNewRadioButton);

		setTable();
	}

	public void setTable() {
		String[] es_header = { "번호", "제출한 답", "정답" };
		String[][] es_data = new String[es_list.size()][es_header.length];

		for (int i = 0; i < es_list.size(); i++) {
			ExamSubmitVO esvo = es_list.get(i);
			es_data[i][0] = esvo.getEsu_idx();
			es_data[i][1] = esvo.getEsu_answer();
			es_data[i][2] = esvo.getQvo().getQ_answer();
		}

		table.setModel(new DefaultTableModel(es_data, es_header));

	}

}
