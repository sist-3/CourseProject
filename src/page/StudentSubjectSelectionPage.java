package page;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.jeong2_DAO;
import page.ExamScoreListManagementPage.JTableButtonRenderer;
import util.LoginManager;
import util.MybatisManager;
import vo.StudentSubjectVO;
import vo.SubjectVO;

import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileLockInterruptionException;
import java.awt.event.ActionEvent;

public class StudentSubjectSelectionPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable subjectSelection_table;
	private JTextField search_text;
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	//String st_idx = "2"; //로그인한 학생의 st_idx값
	jeong2_DAO jDAO = new jeong2_DAO();

	/**
	 * Create the panel.
	 */
	public StudentSubjectSelectionPage(String n) {
		setLayout(null);
		
		JPanel subjectSelection_panel = new JPanel();
		subjectSelection_panel.setBounds(0, 0, 800, 600);
		add(subjectSelection_panel);
		subjectSelection_panel.setLayout(null);
		
		JLabel subjectSelection_label = new JLabel("");
		subjectSelection_label.setBounds(15, 15, 120, 30);
		subjectSelection_label.setIcon(new ImageIcon("src/resources/image/jeong2/selection.png"));
		subjectSelection_panel.add(subjectSelection_label);
		
		subjectSelection_table = new JTable();
		subjectSelection_table.setBounds(15, 60, 745, 525);
		//subjectSelection_panel.add(subjectSelection_table);
		
		JScrollPane scrollPane = new JScrollPane(subjectSelection_table);
		scrollPane.setBounds(15, 60, 745, 428);
		subjectSelection_panel.add(scrollPane);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setForeground(new Color(128, 128, 128));
		scrollBar.setBackground(new Color(255, 255, 255));
		scrollBar.setBounds(771, 15, 17, 575);
		subjectSelection_panel.add(scrollBar);
		
		JButton search_button = new JButton("검색");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stsb();
			}
		});
		search_button.setBounds(663, 22, 97, 23);
		subjectSelection_panel.add(search_button);
		
		search_text = new JTextField();
		search_text.setBounds(479, 22, 172, 23);
		subjectSelection_panel.add(search_text);
		search_text.setColumns(10);
		
		JPanel button_panel = new JPanel();
		button_panel.setBounds(15, 498, 745, 92);
		subjectSelection_panel.add(button_panel);
		button_panel.setLayout(null);
		
		JButton sub_selection_Button = new JButton("수강신청");
		sub_selection_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sbse(n);
			}
		});
		sub_selection_Button.setBounds(648, 10, 97, 72);
		button_panel.add(sub_selection_Button);
		
		total();

	}
	
	public void total() { //전체 과목 목록 표기

		List<SubjectVO> list = jDAO.total();
		String[] set_head = {"과목명","담당교수","학점","등록여부","과목시작일","과목종료일","과목등록일","강의계획서"};
		String[][] list2 = new String[list.size()][set_head.length];
		
		for(int i=0; i<list.size(); i++) {
			SubjectVO vo = list.get(i);

				list2[i][0] = vo.getSb_name();
				list2[i][1] = vo.getSb_mgr();
				list2[i][2] = vo.getSb_point();
				list2[i][3] = vo.getSb_yn();
				list2[i][4] = vo.getSb_start_date();
				list2[i][5] = vo.getSb_end_date();
				list2[i][6] = vo.getSb_date();
				//list2[i][7] = vo.getSb_plan_file();				
		}
		
		// -- JTable 셀 내용 수정과 컬럼 이동 차단 , 클릭은 가능 --
		DefaultTableModel model = new DefaultTableModel(list2, set_head) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    subjectSelection_table.setModel(model);
		
	    subjectSelection_table.getTableHeader().setReorderingAllowed(false);
	    
	    subjectSelection_table.setColumnSelectionAllowed(true);
	     // -- ---- --	    
	    // -- JTable에 버튼 구현 --   
	    DefaultTableModel dtm = new DefaultTableModel(list2, set_head);
	    subjectSelection_table.setModel(dtm);
	    subjectSelection_table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
	    subjectSelection_table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
	    
	}
	public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
	    private JButton subPlanFile_button;
	    String path = "src\\resources\\subplan\\subplanfile.txt";
	    String path2 = System.getProperty("user.home") + File.separator + "Downloads";

	    public ButtonEditor() { //다운로드 버튼을 클릭했을 때 수행
	        subPlanFile_button = new JButton("다운로드");
	        subPlanFile_button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	File planfile = new File(path);
	            	BufferedInputStream bis = null;
	            	BufferedOutputStream bos = null;
	            	
	            	try {
	            		String subName = (String) subjectSelection_table.getValueAt(subjectSelection_table.getSelectedRow(), 0);
	            		bis = new BufferedInputStream(new FileInputStream(planfile));
	            		bos = new BufferedOutputStream(new FileOutputStream(path2+ File.separator + subName + planfile.getName()));
	            		
	            		byte[] buf = new byte[2048];
	            		int size = -1;
	        			
	        			while((size = bis.read(buf)) != -1) {
	        				bos.write(buf, 0, size);
	        				bos.flush();
	        			}
					} catch (Exception e2) {
						e2.printStackTrace();
					}finally {
						try {
							if(bis != null)
								bis.close();
							if(bos != null)
								bos.close();
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}
	               
	            }
	        });
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        return subPlanFile_button;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        return null;
	    }
	}
	
	class ButtonRenderer extends JButton implements TableCellRenderer {

	    public ButtonRenderer() {
	        setOpaque(false);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        if (isSelected) {
	            setForeground(table.getSelectionForeground());
	            setBackground(table.getSelectionBackground());
	        } else {
	            setForeground(table.getForeground());
	            setBackground(UIManager.getColor("Button.background"));
	        }
	        setText((value == null) ? "" : value.toString());
	        setText("다운로드");
	        return this;
	    }
	}// -- ---- --
	
	
	public void stsb() { //테이블에 표시된 과목 중 과목명으로 검색

		String str = search_text.getText().trim();
		List<SubjectVO> list = jDAO.stsb(str);
		String[] set_head = {"과목명","담당교수","학점","등록여부","과목시작일","과목종료일","과목등록일","강의계획서"};
		String[][] list2 = new String[list.size()][set_head.length];
		
		if(str.length() > 0) {
			for(int i=0; i<list.size(); i++) {
				SubjectVO vo = list.get(i);

					list2[i][0] = vo.getSb_name();
					list2[i][1] = vo.getSb_mgr();
					list2[i][2] = vo.getSb_point();
					list2[i][3] = vo.getSb_yn();
					list2[i][4] = vo.getSb_start_date();
					list2[i][5] = vo.getSb_end_date();
					list2[i][6] = vo.getSb_date();
					list2[i][7] = vo.getSb_plan_file();
					
			}
			DefaultTableModel model = new DefaultTableModel(list2, set_head) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			subjectSelection_table.setModel(model);
		
			subjectSelection_table.getTableHeader().setReorderingAllowed(false);
	    
			subjectSelection_table.setColumnSelectionAllowed(true);
	    
			DefaultTableModel dtm = new DefaultTableModel(list2, set_head);
			subjectSelection_table.setModel(dtm);
			subjectSelection_table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
			subjectSelection_table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
	    
			}else
				JOptionPane.showMessageDialog(null, "검색어를 입력하세요.", "알림", JOptionPane.ERROR_MESSAGE);

		
	}
	
	public void sbse(String n) {//수강신청 버튼을 클릭하면 해당 과목인덱스, 학생인덱스, 교수인덱스, 현재날짜, 수강신청 yn유무 값 db에 저장
		
		n = LoginManager.getInstance().getStudentInfo().getSt_idx().trim();
		String subName = (String) subjectSelection_table.getValueAt(subjectSelection_table.getSelectedRow(), 0);	
		String subIdx = jDAO.deleteDAO(subName);
			
		 Map<String, Object> map = new HashMap<>(); //인자로 받은 st_idx와 테이블행을 통해 변환한 sb_idx값을 map에 저장
		    map.put("sb_idx", subIdx);
		    map.put("st_idx", n);
		    
		 jDAO.sbse(map);
	}
}
