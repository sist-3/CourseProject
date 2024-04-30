package page;

import java.util.List;

import javax.swing.JPanel;

import dao.JongDAO;
import util.PageManager;
import vo.ExamJoinVO;
import vo.StudentVO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExamScoreListManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	List<ExamJoinVO> e_list;
	private JTable table_1;

	String[] st_header = {"학번", "학생명", "점수", "답변확인" };
	Object[][] st_data = new Object[4][3];
	

	/**
	 * Create the frame.
	 */
	public ExamScoreListManagementPage(String idx) {
		JongDAO jdao = new JongDAO();
		e_list = jdao.examJoin(idx);
		
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("\uACFC\uBAA9\uBA85");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 26));
		lblNewLabel.setBounds(0, 0, 450, 46);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 48, 800, 35);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_1);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\uAC80\uC0C9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(btnNewButton);
		
		table_1 = new JTable(new ClientTableModel());
		setTable();
		JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
		table_1.getColumn("답변확인").setCellRenderer(buttonRenderer);
		table_1.setBounds(0, 0, 1, 1);

		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(1, 85, 800, 453);
		panel.add(scrollPane_1);
		
		
		table_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int column = table_1.getSelectedColumn();
				
				// 답변확인 버튼 눌렀을 때
				if(column == 3) {
					String num = table_1.getValueAt(table_1.getSelectedRow(),0).toString();
					String code = jdao.studentNumIdx(num);
					ExamAnswerPage eap = new ExamAnswerPage(code);
					PageManager.getInstance().changePage(eap);
				}
				
			}

		});
	}

	private void setTable() {
		st_data = new Object[e_list.size()][st_header.length];
		
		for (int i = 0; i < e_list.size(); i++) {
			ExamJoinVO ejvo = e_list.get(i);
			StudentVO stvo = ejvo.getStvo();

			st_data[i][0] = stvo.getSt_num();
			st_data[i][1] = stvo.getSt_name();
			st_data[i][2] = ejvo.getEj_score();
			st_data[i][3] = new JButton("답변확인");
		}
		table_1.setModel(new DefaultTableModel(st_data, st_header));
	}

	class ClientTableModel extends DefaultTableModel {

		private final Class<?>[] columnTypes = new Class<?>[] { String.class, String.class, JButton.class,
				JButton.class };

		@Override
		public int getColumnCount() {
			return st_header.length;
		}

		@Override
		public int getRowCount() {
			return st_data.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return st_header[columnIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return st_data[rowIndex][columnIndex];
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
}
