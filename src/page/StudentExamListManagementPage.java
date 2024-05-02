package page;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.HyeyoonDAO;
import util.LoginManager;
import util.MybatisManager;
import util.PageManager;
import vo.ExamSubmitVO;
import vo.ExamVO;

import javax.swing.ImageIcon;

public class StudentExamListManagementPage extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private List<ExamVO> e_list;
	StudentExamPage sep;
	Object[][] data;
	String[] e_header = { "과목명", "시험명", "응시", "결과" };
	String e_idx;
	ExamVO vo;
	HyeyoonDAO hdao;
	String st_idx, sb_idx, e_name;

	/**
	 * Create the panel.
	 */
	public StudentExamListManagementPage() {
		hdao = new HyeyoonDAO();
		setLayout(null);

		st_idx = "1"; //LoginManager.getInstance().getStudentInfo().getSt_idx();
		Map<String, String> map = new HashMap<>();
		map.put("st_yn", "Y");
		map.put("ss_yn", "Y");
		map.put("st_idx", st_idx);
		
		e_list = hdao.examList(map);
		
		makeData();
		
		table = new JTable(new ClientTableModel());
		JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
		table.getColumn("응시").setCellRenderer(buttonRenderer);
		table.getColumn("결과").setCellRenderer(buttonRenderer);
		table.setBounds(0, 0, 1, 1);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				
				e_idx = e_list.get(row).getE_idx();
				e_name = e_list.get(row).getE_name();
	
				if (col == 2) { // 버튼 셀을 누를 때마다
					
					Map<String, String> map2 = new HashMap<>();
					map2.put("e_idx", e_idx);
					map2.put("st_idx", st_idx);
					String a = hdao.solveChk2(map2);

					Map<String,String> map3 = new HashMap<>();
					map3.put("e_idx", e_idx);
					String b = hdao.quizYN(map3);
					
					if(a != null) {
						JOptionPane.showMessageDialog(null, "풀었던 시험입니다");						
					}else if(b == null){
						JOptionPane.showMessageDialog(null, "출제되지 않은 시험입니다");						
					}else {
						StudentExamPage sdep = new StudentExamPage(StudentExamListManagementPage.this);
						PageManager.getInstance().changePage(sdep);
					}
					
					
				}else if(col == 3) {
					JOptionPane.showMessageDialog(null, e_idx + e_name);											
				}
			}

		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 61, 760, 490);
		add(scrollPane);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/resources/image/menu/admin/시험관리.png"));
		lblNewLabel.setBounds(15, 15, 120, 30);
		add(lblNewLabel);

		
	}

	private void makeData() {
		data = new Object[e_list.size()][e_header.length];
		int i = 0;
		for (ExamVO vo : e_list) {
			data[i][0] = vo.getSbvo().getSb_name();
			data[i][1] = vo.getE_name();
			data[i][2] = new JButton("응시");
			data[i][3] = new JButton("결과");
			i++;  
		}
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
