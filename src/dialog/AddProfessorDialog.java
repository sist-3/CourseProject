package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.gummoDAO;
import dao.jeong2_DAO;
import page.ProfessorManagementPage;
import page.StudentManagementPage;
import vo.MajorVO;
import vo.ProfessorVO;
import vo.StudentVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddProfessorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name_tf;
	private JComboBox major_selectbox;
	private JComboBox birth_Y;
	private JComboBox birth_M;
	private JComboBox birth_D;
	private JTextField addr_tf;
	private JComboBox yn_cb;
	private JTextField tel_tf;
	ProfessorManagementPage p;
	ProfessorVO vo;
	JButton save_button;
	JButton cancel_Button;
	jeong2_DAO jDAO = new jeong2_DAO();

	public AddProfessorDialog(ProfessorManagementPage p, ProfessorVO vo) {
		this.p = p;
		this.vo = vo;
		init(); //화면 세팅
	}

	private void init() {
		setBounds(100, 100, 511, 423);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 495, 351);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("이름:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 39, 34, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("전공:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(276, 39, 34, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("생년월일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 183, 57, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("연락처:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 107, 46, 15);
				panel.add(lblNewLabel);
			}
			{
				name_tf = new JTextField();
				name_tf.setBounds(87, 36, 111, 21);
				panel.add(name_tf);
				name_tf.setColumns(10);
			}

			birth_Y = new JComboBox();
			birth_Y.setModel(new DefaultComboBoxModel(new String[] { "1940", "1941", "1942", "1943", "1944", "1945",
					"1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957",
					"1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
					"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981",
					"1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993",
					"1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005",
					"2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
					"2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
			birth_Y.setEditable(true);
			birth_Y.setBounds(101, 179, 71, 23);
			panel.add(birth_Y);

			birth_M = new JComboBox();
			birth_M.setModel(new DefaultComboBoxModel(
					new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
			birth_M.setEditable(true);
			birth_M.setBounds(208, 179, 71, 23);
			panel.add(birth_M);

			birth_D = new JComboBox();
			birth_D.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30", "31" }));
			birth_D.setEditable(true);
			birth_D.setBounds(322, 179, 71, 23);
			panel.add(birth_D);

			JLabel lblNewLabel_1 = new JLabel("년");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(184, 183, 12, 15);
			panel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("월");
			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_2.setBounds(291, 183, 19, 15);
			panel.add(lblNewLabel_2);

			JLabel lblNewLabel_3 = new JLabel("일");
			lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_3.setBounds(405, 183, 12, 15);
			panel.add(lblNewLabel_3);

			tel_tf = new JTextField();
			tel_tf.setBounds(90, 104, 189, 21);
			panel.add(tel_tf);
			tel_tf.setColumns(10);

			JLabel lblNewLabel_4_1 = new JLabel("등록여부:");
			lblNewLabel_4_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_4_1.setBounds(336, 104, 57, 21);
			panel.add(lblNewLabel_4_1);

			JLabel addr = new JLabel("주소:");
			addr.setFont(new Font("굴림", Font.BOLD, 12));
			addr.setBounds(32, 254, 34, 15);
			panel.add(addr);

			addr_tf = new JTextField();
			addr_tf.setBounds(87, 251, 373, 21);
			panel.add(addr_tf);
			addr_tf.setColumns(10);

			yn_cb = new JComboBox();
			yn_cb.setModel(new DefaultComboBoxModel(new String[] { "Y", "N" }));

			yn_cb.setBounds(414, 103, 46, 23);
			panel.add(yn_cb);

			major_selectbox = new JComboBox();
			ArrayList<MajorVO> majorlist = new ArrayList<>(); //DB에 있는 전공목록 불러오기
			majorlist = (ArrayList<MajorVO>) jDAO.totalMajor();
			for (MajorVO major : majorlist) {
			    major_selectbox.addItem(major.getM_name());
			}
					
			major_selectbox.setEditable(true);
			major_selectbox.setBounds(322, 35, 138, 23);
			panel.add(major_selectbox);

			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				save_button = new JButton("저장");
				save_button.setActionCommand("OK");
				buttonPane.add(save_button);
				getRootPane().setDefaultButton(save_button); //엔터키 작동
				save_button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {	
						saveData();
						p.ProfessorList();
						dispose();
					}
				});
			}
			{
				cancel_Button = new JButton("취소");
				cancel_Button.setActionCommand("Cancel");
				buttonPane.add(cancel_Button);
				cancel_Button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void saveData() {
		
		//입력한 정보 받아내기
		String p_name = name_tf.getText().trim();
		String p_tel = tel_tf.getText().trim();
		String p_addr = addr_tf.getText().trim();
		String p_birth = birth_Y.getSelectedItem().toString() + birth_M.getSelectedItem().toString()
						+ birth_D.getSelectedItem().toString();
		String p_yn = yn_cb.getSelectedItem().toString();
		//String m_idx = null;
		String selectedItem = (String) major_selectbox.getSelectedItem();
		
		//if(!p_name.isEmpty() && !p_tel.isEmpty() && !p_addr.isEmpty() && !p_birth.isEmpty() && !p_yn.isEmpty() && selectedIndex >= 0) {
			//if (selectedIndex != -1) {    
			  //  m_idx = String.valueOf(selectedIndex + 1); // 선택된 콤보박스 인덱스에 1을 더하여 m_idx로 설정
		String m_idx = jDAO.getProfessorMajorIdx(selectedItem);	
			    Map<String, String> map = new HashMap<>();		
				map.put("m_idx", m_idx);
				map.put("p_name", p_name);
				map.put("p_tel", p_tel);
				map.put("p_addr", p_addr);
				map.put("p_birth", p_birth);
				map.put("p_yn", p_yn);
				
				jDAO.addProfessor(map);
			
			//} else {
			  //  JOptionPane.showMessageDialog(null, "전공을 선택하세요", "알림", JOptionPane.ERROR_MESSAGE);
			//}
		//}else {
		//	JOptionPane.showMessageDialog(null, "내용을 모두 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
		//}
		
	}
	

}
