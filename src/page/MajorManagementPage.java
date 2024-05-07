package page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.reflection.SystemMetaObject;

import dao.JongDAO;
import dao.jeong2_DAO;
import dialog.AddMajorDialog;
import dialog.AddProfessorDialog;
import dialog.AddStudentDialog;
import dialog.UpdateMajorDialog;
import dialog.UpdateProfessorDialog;
import dialog.UpdateStudentDialog;
import page.StudentSubjectManagementPage.ButtonEditor;
import page.StudentSubjectManagementPage.ButtonRenderer;
import util.LoginManager;
import util.PageManager;
import vo.MajorVO;
import vo.ProfessorVO;
import vo.StudentVO;
import vo.SubjectVO;

public class MajorManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField search_text;
	private JTable MajorManagement_table;
	List<MajorVO> m_list;
	JongDAO jdao = new JongDAO();
	/**
	 * Create the panel.
	 */
	public MajorManagementPage() {
		m_list = jdao.majorAll("");
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		JPanel subject_panel = new JPanel();
		subject_panel.setBounds(5, 150, 790, 445);
		panel.add(subject_panel);
		subject_panel.setLayout(null);
		
		MajorManagement_table = new JTable();
		MajorManagement_table.setBounds(0, 0, 790, 445);
		MajorManagement_table.setShowGrid(true);
		MajorManagement_table.setGridColor(Color.lightGray);
		
		JScrollPane scrollPane = new JScrollPane(MajorManagement_table);
		scrollPane.setBounds(0, 0, 790, 445);
		subject_panel.add(scrollPane);
		
		JLabel title_label = new JLabel("");
		title_label.setBounds(15, 15, 120, 30);
		panel.add(title_label);
		title_label.setIcon(new ImageIcon("src/resources/image/jeong2/Major.png"));
		
		JButton add_button = new JButton("추가");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMajorDialog diglog = new AddMajorDialog();
				diglog.setVisible(true);
			}
		});
		add_button.setBounds(5, 105, 91, 23);
		panel.add(add_button);
		
		JButton delete_button = new JButton("삭제");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMajor();
			}
		});
		delete_button.setBounds(211, 105, 91, 23);
		panel.add(delete_button);
		
		JButton search_button = new JButton("검색");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				searchMajor();
			}
		});
		search_button.setBounds(704, 105, 91, 23);
		panel.add(search_button);
		
		search_text = new JTextField();
		search_text.setBounds(511, 106, 183, 21);
		panel.add(search_text);
		search_text.setColumns(10);
		
		JButton fix_button = new JButton("수정");
		fix_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = MajorManagement_table.getSelectedRow();

				if (row >= 0) {		
					MajorVO vo = m_list.get(row);
					UpdateMajorDialog diglog = new UpdateMajorDialog(MajorManagementPage.this, vo);
					diglog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "수정할 행을 선택해주세요");
				}		
			}
		});
		fix_button.setBounds(108, 105, 91, 23);
		panel.add(fix_button);
		
		MajorList();
	
	}
	
	public void MajorList() {
		
		String[] set_head = {"전공코드","전공명","필요학점"};
		String[][] list2 = new String[m_list.size()][set_head.length];
		
		
		for(int i=0; i<m_list.size(); i++) {
			MajorVO mvo = m_list.get(i);
			
			list2[i][0] = mvo.getM_code();
			list2[i][1] = mvo.getM_name();
			list2[i][2] = mvo.getM_need_point();
			
		}
		DefaultTableModel model = new DefaultTableModel(list2, set_head) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    // JTable에 DefaultTableModel 설정
	    MajorManagement_table.setModel(model);
		
		// 컬럼 이동 비활성화
	    MajorManagement_table.getTableHeader().setReorderingAllowed(false);
	    
	    // 셀 선택 가능하게 설정
	    MajorManagement_table.setColumnSelectionAllowed(true);	
	    
	}
		
	public void searchMajor() {
		
		String str = search_text.getText().trim();
		
		if(str.length() > 0) {
			String[] set_head = {"전공코드","전공명","필요학점"};
			String[][] list2 = new String[m_list.size()][set_head.length];
			
			
			for(int i=0; i<m_list.size(); i++) {
				MajorVO mvo = m_list.get(i);
				
				list2[i][0] = mvo.getM_code();
				list2[i][1] = mvo.getM_name();
				list2[i][2] = mvo.getM_need_point();
				
			}
			DefaultTableModel model = new DefaultTableModel(list2, set_head) {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return false;
		        }
		    };
		    MajorManagement_table.setModel(model);
			
		    MajorManagement_table.getTableHeader().setReorderingAllowed(false);
		    
		    MajorManagement_table.setColumnSelectionAllowed(true);
		}else {
			JOptionPane.showMessageDialog(null, "검색어를 입력하세요.", "알림", JOptionPane.ERROR_MESSAGE);
			//return;
		}
	}	
	
	public void deleteMajor() {
		int row = MajorManagement_table.getSelectedRow();
		String m_code = MajorManagement_table.getValueAt(row, 0).toString();
		
		jdao.majorDelete(m_code);
		MajorList(); //새로고침(목록 다시 불러오기)
	}

}
