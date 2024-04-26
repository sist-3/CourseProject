package page;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import util.MybatisManager;
import vo.MajorVO;
import vo.StudentVO;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.sql.Date;
//import java.lang.invoke.ClassSpecializer.Factory;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StudentMyPage extends JPanel {

	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	JTextField st_num_text;
	JTextField st_name_text;
	JTextField st_maj_text;
	Panel panel_1 ;
	String n = "4"; //로그인한 학생의 st_idx값으로 변경
	JComboBox birth_y_comboBox;
	JComboBox birth_m_comboBox;
	JComboBox birth_d_comboBox;
	JTextPane tel_text;
	JTextPane addr_text;
	
	
	
	/*private void makeFactory() {
		try {
			Reader r = Resources.getResourceAsReader("config/config.xml");
			factory = new SqlSessionFactoryBuilder().build(r);
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/

	/**
	 * Create the panel.
	 */
	public StudentMyPage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		panel_1 = new Panel();
		panel_1.setBounds(0, 0, 800, 170);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		Label image = new Label("image");
		image.setAlignment(Label.CENTER);
		image.setBackground(new Color(255, 255, 255));
		image.setBounds(30, 30, 110, 110);
		panel_1.add(image);
		
		Label st_num_label = new Label("학번 :");
		st_num_label.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		st_num_label.setBounds(221, 44, 44, 22);
		panel_1.add(st_num_label);
		
		Label st_name_label = new Label("이름 :");
		st_name_label.setBounds(221, 105, 44, 22);
		panel_1.add(st_name_label);
		
		Label st_maj_label = new Label("전공 :");
		st_maj_label.setBounds(422, 44, 44, 22);
		panel_1.add(st_maj_label);
		
		st_num_text = new JTextField();
		st_num_text.setEditable(false);
		st_num_text.setBounds(270, 44, 130, 22);
		panel_1.add(st_num_text);
		
		st_name_text = new JTextField();
		st_name_text.setEditable(false);
		st_name_text.setBounds(270, 105, 130, 22);
		panel_1.add(st_name_text);
		
		st_maj_text = new JTextField();
		st_maj_text.setEditable(false);
		st_maj_text.setBounds(471, 44, 130, 22);
		panel_1.add(st_maj_text);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(0, 170, 800, 350);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel main_label = new JLabel("New label");
		main_label.setIcon(new ImageIcon("C:\\Users\\wjddl\\Downloads\\Group 126.png"));
		main_label.setBounds(54, 35, 707, 285);
		panel_2.add(main_label);
		
		JLabel birth_label = new JLabel("생년월일");
		birth_label.setBounds(112, 77, 111, 42);
		panel_2.add(birth_label);
		
		JLabel tel_label = new JLabel("전화번호");
		tel_label.setBounds(112, 170, 50, 15);
		panel_2.add(tel_label);
		
		JLabel addr_label = new JLabel(" 주소록 ");
		addr_label.setBounds(112, 247, 50, 15);
		panel_2.add(addr_label);
		
		birth_y_comboBox = new JComboBox();
		birth_y_comboBox.setModel(new DefaultComboBoxModel(new String[] {"1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005"}));
		birth_y_comboBox.setMaximumRowCount(10);
		birth_y_comboBox.setBounds(259, 82, 126, 32);
		panel_2.add(birth_y_comboBox);
		
		
		birth_m_comboBox = new JComboBox();
		birth_m_comboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		birth_m_comboBox.setMaximumRowCount(10);
		birth_m_comboBox.setBounds(449, 82, 90, 32);
		panel_2.add(birth_m_comboBox);
		
		birth_d_comboBox = new JComboBox();
		birth_d_comboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		birth_d_comboBox.setMaximumRowCount(10);
		birth_d_comboBox.setBounds(602, 82, 90, 32);
		panel_2.add(birth_d_comboBox);
		
		JLabel birth_y_label = new JLabel("년");
		birth_y_label.setBounds(397, 77, 21, 42);
		panel_2.add(birth_y_label);
		
		JLabel birth_m_label = new JLabel("월");
		birth_m_label.setBounds(551, 77, 21, 42);
		panel_2.add(birth_m_label);
		
		JLabel birth_d_label = new JLabel("일");
		birth_d_label.setBounds(702, 77, 21, 42);
		panel_2.add(birth_d_label);
		
		tel_text = new JTextPane();
		tel_text.setBounds(259, 153, 218, 32);
		panel_2.add(tel_text);
		
		addr_text = new JTextPane();
		addr_text.setBounds(259, 236, 435, 32);
		panel_2.add(addr_text);
		
		JButton save_button = new JButton("저장"); //저장버튼을 클릭
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addStudentMyPage();
			
			}
		});
		save_button.setBounds(670, 546, 91, 23);
		panel.add(save_button);
		
		//makeFactory();
		SqlSession ss = factory.openSession();
		StudentVO vo = ss.selectOne("jeong2.getStudentMyPage",n); //n = st_idx
	
		
		String yyyy = vo.getSt_birth().substring(0, 4);
		int yyyy1 = Integer.parseInt(yyyy);
		
		String mm = vo.getSt_birth().substring(5, 7);	
		int mm1 = Integer.parseInt(mm);
		
		String dd = vo.getSt_birth().substring(8, 10);
		int dd1 = Integer.parseInt(dd);
		
		
		
		st_num_text.setText(vo.getSt_num()); //저장되어 있는 학번 출력
		st_name_text.setText(vo.getSt_name()); //저장되어 있는 이름 출력
		st_maj_text.setText(vo.getMvo().getM_name()); //저장되어 있는 전공 출력	
		
		tel_text.setText(vo.getSt_tel()); //저장되어 있는 연락처 출력
		addr_text.setText(vo.getSt_addr()); //저장되어 있는 주소 출력
		
		birth_y_comboBox.setSelectedIndex(yyyy1-1985); //저장되어 있는 생년월일 출력
		birth_m_comboBox.setSelectedIndex(mm1-1);
		birth_d_comboBox.setSelectedIndex(dd1-1);	
		
		if(ss != null)
			ss.close();

	}
	
	public void addStudentMyPage() {
		
		String st_birth = birth_y_comboBox.getSelectedItem().toString()+"-"+
				birth_m_comboBox.getSelectedItem().toString()+"-"+
				birth_d_comboBox.getSelectedItem().toString();
		String st_addr = addr_text.getText().toString();
		String st_tel = tel_text.getText().toString();
		
		StudentVO vo = new StudentVO();
		vo.setSt_birth(st_birth);
		vo.setSt_addr(st_addr);
		vo.setSt_tel(st_tel);
		vo.setSt_idx(n);
		SqlSession ss = factory.openSession();
		int a = ss.update("jeong2.addStudentMyPage",vo);
		
		if(a > 0) {
			System.out.println("저장완료");
			ss.commit();
		}
		else {
			System.out.println("저장실패");
		    ss.rollback();
		}
		
		if(ss != null)
			ss.close();
	}
	
	
	
	
}
