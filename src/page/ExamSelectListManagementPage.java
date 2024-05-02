package page;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import dao.JongDAO;
import util.PageManager;
import vo.ExamVO;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ExamSelectListManagementPage extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private List<ExamVO> e_list;
	JComboBox exam_y_comboBox;
	JComboBox exam_m_comboBox;
	JComboBox exam_d_comboBox;

	Object[][] data = new Object[3][4];
	String[] e_header = { "시험명", "수정", "채점" };

	JongDAO jdao;
	ExamVO vo;
	private JTextField textField;

	int column;
	int row;
	int cnt;
	String name;
	String idx;
	String sb_code;

	/**
	 * Create the panel.
	 */
	public ExamSelectListManagementPage(String code) {
		sb_code = code;
		jdao = new JongDAO();
		setLayout(null);
		e_list = jdao.exam(code);
		table = new JTable(new ClientTableModel());
		makeData();
		JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
		table.getColumn("수정").setCellRenderer(buttonRenderer);
		table.getColumn("채점").setCellRenderer(buttonRenderer);
		table.setBounds(0, 0, 1, 1);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int currentRow = table.getSelectedRow();
				column = table.getSelectedColumn();
				name = table.getValueAt(table.getSelectedRow(), 0).toString();
				idx = jdao.examNameIdx(name, code);
				
				if (row == currentRow) {
					cnt++;
				} else {
					row = currentRow;
					cnt = 1;
				}
				if (column == 0 && cnt > 1) {
					ExamScoreListManagementPage eslmp2 = new ExamScoreListManagementPage(idx, sb_code);
					PageManager.getInstance().changePage(eslmp2);
				} else if (column == 1) {
					MakeExamManagementPage memp = new MakeExamManagementPage(idx);
					PageManager.getInstance().changePage(memp);
				} else if (column == 2) {
					ExamScoreManagemenPage esmp = new ExamScoreManagemenPage(idx);
					PageManager.getInstance().changePage(esmp);
				}
			}

		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 76, 800, 600);
		add(scrollPane);

		JLabel lblNewLabel = new JLabel("시험관리");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 26));
		lblNewLabel.setBounds(15, 15, 120, 30);
		add(lblNewLabel);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBounds(12, 41, 674, 37);
		add(panel);

		JLabel lblNewLabel_1 = new JLabel("시험명");
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("시험일자");
		panel.add(lblNewLabel_2);

		exam_y_comboBox = new JComboBox();
		exam_y_comboBox.setModel(new DefaultComboBoxModel(new String[] { "1965", "1966", "1967", "1968", "1969", "1970",
				"1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983",
				"1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996",
				"1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005" }));
		exam_y_comboBox.setMaximumRowCount(10);
		exam_y_comboBox.setBounds(259, 82, 126, 32);
		panel.add(exam_y_comboBox);
		exam_m_comboBox = new JComboBox();
		exam_m_comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		exam_m_comboBox.setMaximumRowCount(10);
		exam_m_comboBox.setBounds(449, 82, 90, 32);
		panel.add(exam_m_comboBox);
		exam_d_comboBox = new JComboBox();
		exam_d_comboBox.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "26", "27", "28", "29", "30", "31" }));
		exam_d_comboBox.setMaximumRowCount(10);
		exam_d_comboBox.setBounds(602, 82, 90, 32);
		panel.add(exam_d_comboBox);
		
		JButton btnNewButton_1 = new JButton("추가");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = exam_y_comboBox.getSelectedItem() + "-" + exam_m_comboBox.getSelectedItem() + "-" + exam_d_comboBox.getSelectedItem();
				jdao.addExam(code, "1", textField.getText(), date, "Y");
				String value = jdao.examNameIdx(textField.getText(), code);
				MakeExamManagementPage memp = new MakeExamManagementPage(value);
				PageManager.getInstance().changePage(memp);
			}
		});
		panel.add(btnNewButton_1);
		JButton btnNewButton = new JButton("삭제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdao.examNameIdxDelete(idx);
			}
		});
		panel.add(btnNewButton);
	}

	private void makeData() {
		data = new Object[e_list.size()][e_header.length];
		int i = 0;
		for (ExamVO vo : e_list) {
			data[i][0] = vo.getE_name();
			data[i][1] = new JButton("수정");
			data[i][2] = new JButton("채점");
			i++;
		}
		table.setModel(new DefaultTableModel(data, e_header));
	}

	class ClientTableModel extends DefaultTableModel {

		private final Class<?>[] columnTypes = new Class<?>[] { String.class, String.class, JButton.class,
				JButton.class };

		@Override
		public int getColumnCount() {
			return e_header.length;
		}

		@Override
		public int getRowCount() {
			return e_list.size();
		}

		@Override
		public String getColumnName(int columnIndex) {
			return e_header[columnIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}
	}

	class JTableButtonRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JButton button = (JButton) value;
			return button;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
