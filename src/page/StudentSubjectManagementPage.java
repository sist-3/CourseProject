package page;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.jeong2_DAO;
import page.StudentSubjectSelectionPage.ButtonEditor;
import page.StudentSubjectSelectionPage.ButtonRenderer;
import util.LoginManager;
import util.MybatisManager;
import util.PageManager;
import vo.StudentSubjectVO;
import vo.SubjectVO;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class StudentSubjectManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField search_text;
	private JTable subject_table;
	String n = LoginManager.getInstance().getStudentInfo().getSt_idx().trim(); //로그인한 학생의 idx값
	private Object st_idx;
	private Object sb_idx;
	jeong2_DAO jDAO = new jeong2_DAO();
	
	/**
	 * Create the panel.
	 */
	public StudentSubjectManagementPage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		JPanel subject_panel = new JPanel();
		subject_panel.setBounds(5, 150, 790, 445);
		panel.add(subject_panel);
		subject_panel.setLayout(null);
		
		subject_table = new JTable();
		subject_table.setBounds(0, 0, 790, 445);
		subject_table.setShowGrid(true);
		subject_table.setGridColor(Color.lightGray);
		
		//subject_panel.add(new JScrollPane(subject_table), BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(subject_table);
		scrollPane.setBounds(0, 0, 790, 445);
		subject_panel.add(scrollPane);
		
		JLabel title_label = new JLabel("");
		title_label.setBounds(15, 15, 120, 30);
		panel.add(title_label);
		title_label.setIcon(new ImageIcon("src/resources/image/jeong2/subject.png"));
		
		JButton selection_button = new JButton("수강신청");
		selection_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentSubjectSelectionPage name = new StudentSubjectSelectionPage(n);
				PageManager.getInstance().changePage(name);
			}
		});
		selection_button.setBounds(5, 105, 91, 23);
		panel.add(selection_button);
		
		JButton delete_button = new JButton("삭제");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMySubject(n);
			}
		});
		delete_button.setBounds(108, 105, 91, 23);
		panel.add(delete_button);
		
		JButton search_button = new JButton("검색");
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				search();
			}
		});
		search_button.setBounds(704, 105, 91, 23);
		panel.add(search_button);
		
		search_text = new JTextField();
		search_text.setBounds(511, 106, 183, 21);
		panel.add(search_text);
		search_text.setColumns(10);
		
		mySubject();
	
	}
	
	public void mySubject() {
		
		List<SubjectVO> list = jDAO.StudentSubjectManagementPageDAO(n);
			
		String[] set_head = {"과목명","담당교수","학점","등록여부","과목시작일","과목종료일","과목등록일","강의계획서"};
		String[][] list2 = new String[list.size()][set_head.length];
		
		for(int i=0; i<list.size(); i++) {
			SubjectVO svo = list.get(i);

				list2[i][0] = svo.getSb_name();
				list2[i][1] = svo.getSb_mgr();
				list2[i][2] = svo.getSb_point();
				list2[i][3] = svo.getSb_yn();
				list2[i][4] = svo.getSb_start_date();
				list2[i][5] = svo.getSb_end_date();
				list2[i][6] = svo.getSb_date();
				list2[i][7] = svo.getSb_plan_file();					
		}
		
		DefaultTableModel model = new DefaultTableModel(list2, set_head) {
	        // 셀 수정 비활성화
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    // JTable에 DefaultTableModel 설정
	    subject_table.setModel(model);
		
		// 컬럼 이동 비활성화
	    subject_table.getTableHeader().setReorderingAllowed(false);
	    
	    // 셀 선택 가능하게 설정
	    subject_table.setColumnSelectionAllowed(true);
	    
	 // -- JTable에 버튼 구현 --   
	    DefaultTableModel dtm = new DefaultTableModel(list2, set_head);
	    subject_table.setModel(dtm);
	    subject_table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
	    subject_table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
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
	            		String subName = (String) subject_table.getValueAt(subject_table.getSelectedRow(), 0);
	            		bis = new BufferedInputStream(new FileInputStream(planfile));
	            		bos = new BufferedOutputStream(new FileOutputStream(path2+ File.separator + subName + planfile.getName()));
	            		
	            		byte[] buf = new byte[2048];
	            		int size = -1;
	        			
	        			while((size = bis.read(buf)) != -1) {
	        				bos.write(buf, 0, size);
	        				JOptionPane.showMessageDialog(null, "다운로드가 완료 되었습니다.", "알림", JOptionPane.DEFAULT_OPTION);
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
	
	public void search() {
		
		String str = search_text.getText().trim();
		Map<String, String> map = new HashMap<>();
		
		if(str.length() > 0) {
			map.put("sb_name", str);
			map.put("st_idx", n);
			List<SubjectVO> list = jDAO.SearchDAO(map);
			
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
					list2[i][7] = vo.getSb_plan_file();
					
			}
			DefaultTableModel model = new DefaultTableModel(list2, set_head) {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return false;
		        }
		    };
		    subject_table.setModel(model);
			
		    subject_table.getTableHeader().setReorderingAllowed(false);
		    
		    subject_table.setColumnSelectionAllowed(true);
		    
		    DefaultTableModel dtm = new DefaultTableModel(list2, set_head);
		    subject_table.setModel(dtm);
		    subject_table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
		    subject_table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
		}else {
			JOptionPane.showMessageDialog(null, "검색어를 입력하세요.", "알림", JOptionPane.ERROR_MESSAGE);
			//return;
		}
	}
	
	
	public void deleteMySubject(String n) {
		
		String subName = (String) subject_table.getValueAt(subject_table.getSelectedRow(), 0);//선택한 행의 0열의 값을 sunName에 저장
		//System.out.println(subName);
		String subIdx = jDAO.deleteDAO(subName); //추출한 과목명sunName으로 과목인덱스 값 subIdx에 저장
		//System.out.println(subIdx);

		Map<String, String> map = new HashMap<>();
		map.put("st_idx", n); //map에 학생인덱스 저장
		map.put("sb_idx", subIdx); //map에 과목인덱스 저장
		
		jDAO.deleteDAO2(map);
		mySubject(); //새로고침(목록 다시 불러오기)
	}
}
