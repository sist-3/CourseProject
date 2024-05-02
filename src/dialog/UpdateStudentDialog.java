package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
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

	/**
	 * Create the dialog.
	 */
	
	public UpdateStudentDialog(StudentManagementPage p, StudentVO vo) {
		this.p = p;
		this.vo = vo;
		init(); 
		viewDialog(); 

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
				
		// "변경" 버튼 액션 리스너에서 데이터를 수정하고 변경을 확인하면 데이터베이스를 업데이트합니다.
		okButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String st_name = name_tf.getText().trim();
		        String st_birth = birth_tf.getText().trim();
		        String st_indate = indate_tf.getText().trim();
		        String st_tel = tel_tf.getText().trim();

		        // 수정된 데이터로 StudentVO 객체를 생성합니다.
		        StudentVO vo = new StudentVO();
		        vo.setSt_name(st_name);
		        vo.setSt_indate(st_indate);
		        vo.setSt_birth(st_birth);
		        vo.setSt_tel(st_tel);
		        vo.setSt_idx(p.getVo().getSt_idx()); // 학생의 st_idx 값을 가져옵니다.

		        // 업데이트 메서드를 호출하여 데이터베이스를 업데이트합니다.
		        int cnt = gdao.updateStudent(vo);

		        if (cnt > 0) {
		            JOptionPane.showMessageDialog(UpdateStudentDialog.this, "변경완료!");
		            dispose(); // 다이얼로그 닫기
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
		setBounds(100, 100, 340, 359);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 321, 287);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("이름:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(43, 40, 34, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("전공:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(43, 78, 34, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("생년월일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(20, 115, 57, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("입학일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(31, 178, 46, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("연락처:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(31, 221, 46, 15);
				panel.add(lblNewLabel);
			}
			{
				name_tf = new JTextField();
				name_tf.setBounds(89, 37, 189, 21);
				panel.add(name_tf);
				name_tf.setColumns(10);
			}
			{
				sub_tf = new JTextField();
				sub_tf.setColumns(10);
				sub_tf.setBounds(89, 75, 189, 21);
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
			birth_Y.setBounds(89, 111, 57, 23);
			panel.add(birth_Y);

			birth_M = new JComboBox();
			birth_M.setModel(new DefaultComboBoxModel(
					new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
			birth_M.setEditable(true);
			birth_M.setBounds(168, 111, 45, 23);
			panel.add(birth_M);

			JLabel lblNewLabel_1 = new JLabel("년");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(148, 115, 19, 15);
			panel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("월");
			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_2.setBounds(215, 115, 19, 15);
			panel.add(lblNewLabel_2);

			JLabel lblNewLabel_3 = new JLabel("일");
			lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_3.setBounds(281, 115, 12, 15);
			panel.add(lblNewLabel_3);

			birth_tf = new JTextField();
			birth_tf.setEditable(false);
			birth_tf.setBounds(89, 144, 189, 21);
			panel.add(birth_tf);
			birth_tf.setColumns(10);

			indate_tf = new JTextField();
			indate_tf.setBounds(89, 175, 189, 21);
			panel.add(indate_tf);
			indate_tf.setColumns(10);

			tel_tf = new JTextField();
			tel_tf.setBounds(89, 218, 189, 21);
			panel.add(tel_tf);
			tel_tf.setColumns(10);
			{
				birth_D = new JComboBox();
				birth_D.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
						"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
						"27", "28", "29", "30", "31" }));
				birth_D.setEditable(true);
				birth_D.setBounds(235, 111, 45, 23);
				panel.add(birth_D);
			}
		}
		{
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

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	// viewDialog 메서드를 추가하여 전달된 StudentVO 객체의 데이터를 텍스트 필드에 표시합니다.
		private void viewDialog() {
			name_tf.setText(vo.getSt_name());
			birth_tf.setText(vo.getSt_birth());
			indate_tf.setText(vo.getSt_indate());
			tel_tf.setText(vo.getSt_tel());
		}
		private void updateBirthTextField() {
			String year = (String) birth_Y.getSelectedItem();
			String month = (String) birth_M.getSelectedItem();
			String day = (String) birth_D.getSelectedItem();

			// 선택한 연도, 월, 일을 결합하여 생년월일 형식으로 텍스트 필드에 설정
			birth_tf.setText(year + "-" + month + "-" + day);
		}

}
