package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.gummoDAO;
import page.StudentManagementPage;
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

public class UpdateStudentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name_tf;
	private JTextField sub_tf;
	private JComboBox birth_Y;
	private JComboBox birth_M;
	private JComboBox birth_D;
	private JTextField birth_tf;
	private JTextField indate_tf;
	private JTextField tel_tf;
	StudentManagementPage p;
	JButton okButton;
	JButton cancelButton;
	StudentVO vo;
	gummoDAO gdao = new gummoDAO();
	private JTextField num_tf;
	private JTextField addr_tf;
	private JTextField outdate_tf;
	private JComboBox yn_cb;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @wbp.parser.constructor
	 */
	public UpdateStudentDialog(StudentManagementPage p, StudentVO vo) {
		this.p = p;
		this.vo = vo;
		init();

		// JComboBox에서 선택한 값을 받아서 JTextField에 넣는 기능 추가
		birth_Y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBirthTextField();
			}
		});

		birth_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBirthTextField();
			}
		});

		birth_D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBirthTextField();
			}
		});

		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 사용자가 입력한 사원의 정보들을 받아낸다.

				String st_num = num_tf.getText().trim();
				String st_name = name_tf.getText().trim();
				String st_tel = tel_tf.getText().trim();
				String st_addr = addr_tf.getText().trim();
				String st_indate = indate_tf.getText().trim();
				String st_outdate = outdate_tf.getText().trim();
				String st_yn = yn_cb.getSelectedItem().toString();
				
			
				StudentVO vo = new StudentVO();

				vo.setSt_num(st_num);
				vo.setSt_name(st_name);
				vo.setSt_tel(st_tel);
				vo.setSt_addr(st_addr);
				vo.setSt_indate(st_indate);
				vo.setSt_outdate(st_outdate);
				vo.setSt_yn(st_yn);
				

				int cnt = gdao.updateStudent(vo);

				if (cnt > 0) {
					JOptionPane.showMessageDialog(UpdateStudentDialog.this, "저장완료!");
					dispose();
				}
			}

		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
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
			panel.setBounds(0, 0, 495, 370);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("이름:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(169, 55, 34, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("전공:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(290, 55, 34, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("생년월일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 157, 57, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel in = new JLabel("입학일:");
				in.setFont(new Font("굴림", Font.BOLD, 12));
				in.setBounds(32, 287, 46, 15);
				panel.add(in);
			}
			{
				JLabel lblNewLabel = new JLabel("연락처:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 108, 46, 15);
				panel.add(lblNewLabel);
			}
			{
				name_tf = new JTextField();
				name_tf.setBounds(215, 52, 61, 21);
				panel.add(name_tf);
				name_tf.setColumns(10);
			}
			{
				sub_tf = new JTextField();
				sub_tf.setColumns(10);
				sub_tf.setBounds(332, 52, 135, 21);
				panel.add(sub_tf);
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
			birth_Y.setBounds(101, 153, 68, 23);
			panel.add(birth_Y);

			birth_M = new JComboBox();
			birth_M.setModel(new DefaultComboBoxModel(
					new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
			birth_M.setEditable(true);
			birth_M.setBounds(203, 153, 68, 23);
			panel.add(birth_M);

			birth_D = new JComboBox();
			birth_D.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
					"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
					"27", "28", "29", "30", "31" }));
			birth_D.setEditable(true);
			birth_D.setBounds(305, 153, 60, 23);
			panel.add(birth_D);

			JLabel lblNewLabel_1 = new JLabel("년");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(172, 157, 19, 15);
			panel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("월");
			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_2.setBounds(271, 157, 19, 15);
			panel.add(lblNewLabel_2);

			JLabel lblNewLabel_3 = new JLabel("일");
			lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_3.setBounds(371, 157, 12, 15);
			panel.add(lblNewLabel_3);

			birth_tf = new JTextField();
			birth_tf.setEditable(false);
			birth_tf.setBounds(101, 186, 189, 21);
			panel.add(birth_tf);
			birth_tf.setColumns(10);

			indate_tf = new JTextField();
			indate_tf.setBounds(88, 284, 138, 21);
			panel.add(indate_tf);
			indate_tf.setColumns(10);

			tel_tf = new JTextField();
			tel_tf.setBounds(88, 105, 189, 21);
			panel.add(tel_tf);
			tel_tf.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("학번:");
			lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_4.setBounds(32, 55, 34, 15);
			panel.add(lblNewLabel_4);
			
			JLabel lblNewLabel_4_1 = new JLabel("재학여부:");
			lblNewLabel_4_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_4_1.setBounds(308, 105, 57, 21);
			panel.add(lblNewLabel_4_1);
			
			num_tf = new JTextField();
			num_tf.setBounds(76, 52, 81, 21);
			panel.add(num_tf);
			num_tf.setColumns(10);
			
			JLabel addr = new JLabel("주소:");
			addr.setFont(new Font("굴림", Font.BOLD, 12));
			addr.setBounds(32, 232, 34, 15);
			panel.add(addr);
			
			addr_tf = new JTextField();
			addr_tf.setBounds(76, 229, 380, 21);
			panel.add(addr_tf);
			addr_tf.setColumns(10);
			
			JLabel end = new JLabel("졸업일:");
			end.setFont(new Font("굴림", Font.BOLD, 12));
			end.setBounds(238, 287, 46, 15);
			panel.add(end);
			
			outdate_tf = new JTextField();
			outdate_tf.setColumns(10);
			outdate_tf.setBounds(288, 284, 154, 21);
			panel.add(outdate_tf);
			
			yn_cb = new JComboBox();
			yn_cb.setModel(new DefaultComboBoxModel(new String[] {"Y", "N"}));
			yn_cb.setBounds(377, 104, 46, 23);
			panel.add(yn_cb);

			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("변경");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("취소");

				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		viewDialog();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	private void viewDialog() {
		num_tf.setText(vo.getSt_num());
		name_tf.setText(vo.getSt_name());
		tel_tf.setText(vo.getSt_tel());
		addr_tf.setText(vo.getSt_addr());
		indate_tf.setText(vo.getSt_indate());
		outdate_tf.setText(vo.getSt_outdate());
		birth_tf.setText(vo.getSt_birth());
		yn_cb.setSelectedItem(vo.getSt_yn());
		birth_Y.setSelectedItem(vo.getSt_birth().substring(0,4));
		birth_M.setSelectedItem(vo.getSt_birth().substring(5, 7));
		birth_D.setSelectedItem(vo.getSt_birth().substring(8,10));
	}

	// JComboBox에서 선택한 값을 JTextField에 넣는 메서드
	private void updateBirthTextField() {
		String year = (String) birth_Y.getSelectedItem();
		String month = (String) birth_M.getSelectedItem();
		String day = (String) birth_D.getSelectedItem();

		// 선택한 연도, 월, 일을 결합하여 생년월일 형식으로 텍스트 필드에 설정
		birth_tf.setText(year + "-" + month + "-" + day);
	}
}
