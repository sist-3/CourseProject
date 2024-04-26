package page;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Reader;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import util.MybatisManager;
import vo.ExamVO;

import javax.swing.ImageIcon;
import java.awt.Font;

public class ExamSelectListManagementPage extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable table;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	private List<ExamVO> e_list;
	
	Object[][] data = new Object[3][4];
	String[] e_header = {"시험명", "수정", "채점"};
	
	ExamVO vo;

	/**
	 * Create the panel.
	 */
	public ExamSelectListManagementPage() {
		setLayout(null);
		SqlSession ss = factory.openSession();
		
		e_list = ss.selectList("jong.exam_join");
		makeData();
		table = new JTable(new ClientTableModel());
		JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
		table.getColumn("수정").setCellRenderer(buttonRenderer);
		table.getColumn("채점").setCellRenderer(buttonRenderer);
		table.setBounds(0, 0, 1, 1);
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if(col == 2 || col == 3) { //踰꾪듉 ���쓣 �늻瑜� �븣留덈떎
					System.out.println(e_list.get(row).getE_idx());
				}
			}
			
		});

		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 61, 760, 490);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("시험관리");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 26));
		lblNewLabel.setIcon(new ImageIcon("src/resources/image/menu/admin/0.png"));
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
			data[i][0] = vo.getE_name();
			data[i][1] = new JButton("수정");
			data[i][2] = new JButton("채점");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}