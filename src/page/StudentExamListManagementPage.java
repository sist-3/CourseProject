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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.ExamVO;

import javax.swing.ImageIcon;

public class StudentExamListManagementPage extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable table;
	SqlSessionFactory factory;
	private List<ExamVO> e_list;
	StudentExamPage sep;
	Object[][] data = new Object[3][4];
	String[] e_header = {"과목명", "시험명", "응시", "결과"};
	String e_idx;
	ExamVO vo;

	/**
	 * Create the panel.
	 */
	public StudentExamListManagementPage() {
		setLayout(null);
		createFactory();
		SqlSession ss = factory.openSession();
		
		Map<String, String> map = new HashMap<>();
		map.put("st_yn", "Y");
		map.put("ss_yn", "Y");
		map.put("st_idx", "1");
		
		e_list = ss.selectList("exam.examlist",map);
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
				if(col == 2 || col == 3) { //버튼 셀을 누를 때마다
					//System.out.println(e_list.get(row).getE_idx());
					e_idx = e_list.get(row).getE_idx();
					StudentExamPage sdep = new StudentExamPage(StudentExamListManagementPage.this);
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
		
		if(ss != null) {
			ss.close();
		}	
	}
	
	private void makeData() {
		data = new Object[e_list.size()][e_header.length];
		int i=0;
		for(ExamVO vo : e_list) {
			data[i][0] = vo.getSvo().getSb_name();
			data[i][1] = vo.getE_name();
			data[i][2] = new JButton("응시");
			data[i][3] = new JButton("결과");
			i++;
		}
	}
	
	class ClientTableModel extends DefaultTableModel {
        
        private final Class<?>[] columnTypes = 
                new  Class<?>[] {String.class, String.class,  JButton.class,  JButton.class};
 
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
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, 
                boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;  
        }
    }




	
	private void createFactory() {
		try {
			Reader r = Resources.getResourceAsReader("config/config.xml"); 
			
			factory = new SqlSessionFactoryBuilder().build(r);
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
