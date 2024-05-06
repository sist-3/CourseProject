package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.gummoDAO;
import page.StudentManagementPage;
import util.LoginManager;
import vo.MajorVO;
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
import java.util.List;
import java.util.Map;

public class UpdateStudentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name_tf;
	private JComboBox birth_Y;
	private JComboBox birth_M;
	private JComboBox birth_D;
	private JComboBox indate_Y;
	private JComboBox indate_M;
	private JComboBox indate_D;
	private JComboBox outdate_Y;
	private JComboBox outdate_M;
	private JComboBox outdate_D;
	private JComboBox yn_cb;
	private JComboBox sub_cb;
	private JTextField tel_tf;
	StudentManagementPage p;
	JButton okButton;
	JButton cancelButton;
	StudentVO vo;
	gummoDAO gdao = new gummoDAO();
	private JTextField num_tf;
	private JTextField addr_tf;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @wbp.parser.constructor
	 */
	//학생변경창
	public UpdateStudentDialog(StudentManagementPage p, StudentVO vo) {
		this.p = p;
		this.vo = vo;
		init();
		viewDialog();

		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 사용자가 입력한 사원의 정보들을 받아낸다.
				String st_idx = vo.getSt_idx();
				String m_name = sub_cb.getSelectedItem().toString();
				String m_idx = Integer.toString(sub_cb.getSelectedIndex()+1);
				String st_num = num_tf.getText().trim();
				String st_name = name_tf.getText().trim();
				String st_tel = tel_tf.getText().trim();
				String st_addr = addr_tf.getText().trim();
				String st_indate = indate_Y.getSelectedItem().toString()+indate_M.getSelectedItem().toString()+indate_D.getSelectedItem().toString();
				String st_outdate = outdate_Y.getSelectedItem().toString()+outdate_M.getSelectedItem().toString()+outdate_D.getSelectedItem().toString();
				String st_birth = birth_Y.getSelectedItem().toString()+birth_M.getSelectedItem().toString()+birth_D.getSelectedItem().toString();
				String st_yn = yn_cb.getSelectedItem().toString();
				
			
				Map<String,String> map = new HashMap<>();
				map.put("m_idx", m_idx);
				map.put("st_name", st_name);
				map.put("st_num", st_num);
				map.put("st_tel", st_tel);
				map.put("st_addr", st_addr);
				map.put("st_indate", st_indate);
				map.put("st_outdate", st_outdate);
				map.put("st_birth", st_birth);
				map.put("st_yn", st_yn);
				map.put("st_idx", st_idx);
				
				int result = JOptionPane.showConfirmDialog(UpdateStudentDialog.this, "변경하시겠습니까?", null,
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {

					int cnt = gdao.updateStudent(map);
					if (cnt > 0) {
						JOptionPane.showMessageDialog(UpdateStudentDialog.this, "변경완료!");
						dispose();
						p.totalStudent(null);
					}
					else {
						
					}

				} else {
					
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
			panel.setBounds(0, 0, 495, 351);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("이름:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(179, 39, 34, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("전공:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(304, 39, 34, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("생년월일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 144, 57, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel in = new JLabel("입학일:");
				in.setFont(new Font("굴림", Font.BOLD, 12));
				in.setBounds(32, 256, 46, 15);
				panel.add(in);
			}
			{
				JLabel lblNewLabel = new JLabel("연락처:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 93, 46, 15);
				panel.add(lblNewLabel);
			}
			{
				name_tf = new JTextField();
				name_tf.setBounds(215, 36, 61, 21);
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
			birth_Y.setBounds(101, 140, 71, 23);
			panel.add(birth_Y);

			birth_M = new JComboBox();
			birth_M.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
			birth_M.setEditable(true);
			birth_M.setBounds(215, 140, 71, 23);
			panel.add(birth_M);

			birth_D = new JComboBox();
			birth_D.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			birth_D.setEditable(true);
			birth_D.setBounds(321, 140, 71, 23);
			panel.add(birth_D);

			JLabel lblNewLabel_1 = new JLabel("년");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(177, 144, 12, 15);
			panel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("월");
			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_2.setBounds(290, 144, 19, 15);
			panel.add(lblNewLabel_2);

			JLabel lblNewLabel_3 = new JLabel("일");
			lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_3.setBounds(399, 144, 12, 15);
			panel.add(lblNewLabel_3);

			tel_tf = new JTextField();
			tel_tf.setBounds(87, 90, 189, 21);
			panel.add(tel_tf);
			tel_tf.setColumns(10);

			JLabel lblNewLabel_4 = new JLabel("학번:");
			lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_4.setBounds(32, 39, 34, 15);
			panel.add(lblNewLabel_4);

			JLabel lblNewLabel_4_1 = new JLabel("재학여부:");
			lblNewLabel_4_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_4_1.setBounds(296, 90, 57, 21);
			panel.add(lblNewLabel_4_1);

			num_tf = new JTextField();
			num_tf.setBounds(66, 36, 81, 21);
			panel.add(num_tf);
			num_tf.setColumns(10);

			JLabel addr = new JLabel("주소:");
			addr.setFont(new Font("굴림", Font.BOLD, 12));
			addr.setBounds(32, 202, 34, 15);
			panel.add(addr);

			addr_tf = new JTextField();
			addr_tf.setBounds(70, 199, 397, 21);
			panel.add(addr_tf);
			addr_tf.setColumns(10);

			JLabel end = new JLabel("졸업일:");
			end.setFont(new Font("굴림", Font.BOLD, 12));
			end.setBounds(32, 305, 46, 15);
			panel.add(end);

			yn_cb = new JComboBox();
			yn_cb.setModel(new DefaultComboBoxModel(new String[] { "Y", "N" }));

			yn_cb.setBounds(365, 89, 46, 23);
			panel.add(yn_cb);
			
			indate_Y = new JComboBox();
			indate_Y.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"}));
			indate_Y.setEditable(true);
			indate_Y.setBounds(101, 252, 71, 23);
			panel.add(indate_Y);
			
			indate_M = new JComboBox();
			indate_M.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
			indate_M.setEditable(true);
			indate_M.setBounds(215, 252, 71, 23);
			panel.add(indate_M);
			
			indate_D = new JComboBox();
			indate_D.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			indate_D.setEditable(true);
			indate_D.setBounds(321, 252, 71, 23);
			panel.add(indate_D);
			
			outdate_Y = new JComboBox();
			outdate_Y.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"}));
			outdate_Y.setEditable(true);
			outdate_Y.setBounds(101, 301, 71, 23);
			panel.add(outdate_Y);
			
			outdate_M = new JComboBox();
			outdate_M.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
			outdate_M.setEditable(true);
			outdate_M.setBounds(215, 301, 71, 23);
			panel.add(outdate_M);
			
			outdate_D = new JComboBox();
			outdate_D.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			outdate_D.setEditable(true);
			outdate_D.setBounds(321, 301, 71, 23);
			panel.add(outdate_D);
			
			JLabel lblNewLabel_1_1 = new JLabel("년");
			lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1_1.setBounds(177, 256, 12, 15);
			panel.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_1_2 = new JLabel("년");
			lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1_2.setBounds(177, 305, 12, 15);
			panel.add(lblNewLabel_1_2);
			
			JLabel lblNewLabel_2_1 = new JLabel("월");
			lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_2_1.setBounds(290, 256, 19, 15);
			panel.add(lblNewLabel_2_1);
			
			JLabel lblNewLabel_2_2 = new JLabel("월");
			lblNewLabel_2_2.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_2_2.setBounds(290, 305, 19, 15);
			panel.add(lblNewLabel_2_2);
			
			JLabel lblNewLabel_3_1 = new JLabel("일");
			lblNewLabel_3_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_3_1.setBounds(397, 256, 12, 15);
			panel.add(lblNewLabel_3_1);
			
			JLabel lblNewLabel_3_2 = new JLabel("일");
			lblNewLabel_3_2.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_3_2.setBounds(397, 305, 12, 15);
			panel.add(lblNewLabel_3_2);
			
			List<MajorVO> m_list = gdao.majorList();
			ArrayList<String> m_name_list = new ArrayList<>();
			for(MajorVO vo : m_list) {
				m_name_list.add(vo.getM_name());
			}
			
			sub_cb = new JComboBox();
			sub_cb.setModel(new DefaultComboBoxModel(m_name_list.toArray()));
			sub_cb.setBounds(338, 35, 129, 23);
			panel.add(sub_cb);

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

	private void viewDialog() {
		num_tf.setText(vo.getSt_num());
		name_tf.setText(vo.getSt_name());
		tel_tf.setText(vo.getSt_tel());
		addr_tf.setText(vo.getSt_addr());
		sub_cb.setSelectedItem(vo.getMvo().getM_name());
		indate_Y.setSelectedItem(vo.getSt_indate().substring(0,4));
		indate_M.setSelectedItem(vo.getSt_indate().substring(5, 7));
		indate_D.setSelectedItem(vo.getSt_indate().substring(8,10));
		outdate_Y.setSelectedItem(vo.getSt_outdate().substring(0,4));
		outdate_M.setSelectedItem(vo.getSt_outdate().substring(5, 7));
		outdate_D.setSelectedItem(vo.getSt_outdate().substring(8,10));
		birth_Y.setSelectedItem(vo.getSt_birth().substring(0,4));
		birth_M.setSelectedItem(vo.getSt_birth().substring(5, 7));
		birth_D.setSelectedItem(vo.getSt_birth().substring(8,10));


	}
}
