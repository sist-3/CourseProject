package page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import dao.jeong2_DAO;
import dialog.AddProfessorDialog;
import dialog.AddStudentDialog;
import dialog.DetailProfesserDialog;
import dialog.DetailStudentDialog;
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

public class ProfessorManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField search_text;
	private JTable ProfessorManagement_table;
	ProfessorVO vo;
	List<ProfessorVO> list;
	jeong2_DAO jDAO = new jeong2_DAO();
	/**
	 * Create the panel.
	 */
	public ProfessorManagementPage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		JPanel subject_panel = new JPanel();
		subject_panel.setBounds(5, 150, 790, 445);
		panel.add(subject_panel);
		subject_panel.setLayout(null);
		
		ProfessorManagement_table = new JTable();
		ProfessorManagement_table.setBounds(0, 0, 790, 445);
		ProfessorManagement_table.setShowGrid(true);
		ProfessorManagement_table.setGridColor(Color.lightGray);
		
		JScrollPane scrollPane = new JScrollPane(ProfessorManagement_table);
		scrollPane.setBounds(0, 0, 790, 445);
		subject_panel.add(scrollPane);
		
		JLabel title_label = new JLabel("");
		title_label.setBounds(15, 15, 120, 30);
		panel.add(title_label);
		title_label.setIcon(new ImageIcon("src/resources/image/jeong2/professor.png"));
		
		JButton add_button = new JButton("추가");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProfessorDialog diglog = new AddProfessorDialog(ProfessorManagementPage.this, vo);
				diglog.setVisible(true);
			}
		});
		add_button.setBounds(5, 105, 91, 23);
		panel.add(add_button);
		
		JButton delete_button = new JButton("삭제");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteProfessor();
			}
		});
		delete_button.setBounds(211, 105, 91, 23);
		panel.add(delete_button);
		
		JButton search_button = new JButton("검색");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				searchProfessor();
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
				int row = ProfessorManagement_table.getSelectedRow();

				if (row >= 0) {		
					ProfessorVO vo = list.get(row);
					UpdateProfessorDialog diglog = new UpdateProfessorDialog(ProfessorManagementPage.this, vo);
					diglog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "수정할 행을 선택해주세요");
				}		
			}
		});
		fix_button.setBounds(108, 105, 91, 23);
		panel.add(fix_button);
		
		ProfessorManagement_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     int row = ProfessorManagement_table.getSelectedRow();
						ProfessorVO vo = list.get(row);				
						DetailProfesserDialog Ddialog = new DetailProfesserDialog(ProfessorManagementPage.this, vo);
						Ddialog.setVisible(true);
				}
				
			}
		});
		
		ProfessorList();
	
	}
	
	public void ProfessorList() {
		list = jDAO.ProfessorListDAO();
		
		String[] set_head = {"이름","전공","연락처","주소","생년월일","등록여부"};
		String[][] list2 = new String[list.size()][set_head.length];
		
		
		for(int i=0; i<list.size(); i++) {
			ProfessorVO pvo = list.get(i);	
			List<MajorVO> m_list = pvo.getList();
			
			for (MajorVO major : m_list) {
			    list2[i][1] = major.getM_name();
			}
				list2[i][0] = pvo.getP_name();				
				list2[i][2] = pvo.getP_tel();
				list2[i][3] = pvo.getP_addr();
				list2[i][4] = pvo.getP_birth();
				list2[i][5] = pvo.getP_yn();				
		}
		DefaultTableModel model = new DefaultTableModel(list2, set_head) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    // JTable에 DefaultTableModel 설정
	    ProfessorManagement_table.setModel(model);
		
		// 컬럼 이동 비활성화
	    ProfessorManagement_table.getTableHeader().setReorderingAllowed(false);
	    
	    // 셀 선택 가능하게 설정
	    ProfessorManagement_table.setColumnSelectionAllowed(true);	
	    
	}
		
	public void searchProfessor() {
		
		String str = search_text.getText().trim();
		Map<String, String> map = new HashMap<>();
		
		if(str.length() > 0) {
			map.put("p_name", str);
			List<ProfessorVO> list = jDAO.SearchProDAO(map);
			
			String[] set_head = {"이름","전공","연락처","주소","생년월일","등록여부"};
			String[][] list2 = new String[list.size()][set_head.length];
			
			
			for(int i=0; i<list.size(); i++) {
				ProfessorVO pvo = list.get(i);	
				List<MajorVO> m_list = pvo.getList();
				
				for (MajorVO major : m_list) {
				    list2[i][1] = major.getM_name();
				}
					list2[i][0] = pvo.getP_name();				
					list2[i][2] = pvo.getP_tel();
					list2[i][3] = pvo.getP_addr();
					list2[i][4] = pvo.getP_birth();
					list2[i][5] = pvo.getP_yn();				
			}
			DefaultTableModel model = new DefaultTableModel(list2, set_head) {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return false;
		        }
		    };
		    ProfessorManagement_table.setModel(model);
			
		    ProfessorManagement_table.getTableHeader().setReorderingAllowed(false);
		    
		    ProfessorManagement_table.setColumnSelectionAllowed(true);
		}else {
			JOptionPane.showMessageDialog(null, "검색어를 입력하세요.", "알림", JOptionPane.ERROR_MESSAGE);
			//return;
		}
	}	
	
	public void deleteProfessor() {
		
		String p_Name = (String) ProfessorManagement_table.getValueAt(ProfessorManagement_table.getSelectedRow(), 0);
		String mName = (String) ProfessorManagement_table.getValueAt(ProfessorManagement_table.getSelectedRow(), 1);
		
		String m_idx = jDAO.getProfessorMajorIdx(mName);
		
		System.out.println(m_idx);
		Map<String, String> del_map = new HashMap<>();
		del_map.put("p_Name", p_Name);
		del_map.put("m_idx", m_idx);
		String del_pidx = jDAO.deleteProDAO(del_map);

		//System.out.println(del_pidx);
		jDAO.deleteProDAO2(del_pidx);
		ProfessorList(); //새로고침(목록 다시 불러오기)
	}

}
