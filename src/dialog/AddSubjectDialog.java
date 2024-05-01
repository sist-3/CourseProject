package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import page.SubjectManagementPage;
import vo.SubjectVO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class AddSubjectDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name_tf;
	private JTextField point_tf;
	private JComboBox start_Y;
	private JComboBox start_M;
	private JComboBox start_D;
	private JComboBox end_Y;
	private JComboBox end_M;
	private JComboBox end_D;
	private JTextField file_tf;
	SubjectManagementPage p;
	SubjectVO vo;
	JButton okButton;
	JButton cancelButton;
	private JTextField end_tf;
	private JTextField start_tf;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @wbp.parser.constructor
	 */
	public AddSubjectDialog(SubjectManagementPage p) {
		this.p = p;
		init();
		start_Y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatestartTextField();
			}
		});

		start_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatestartTextField();
			}
		});

		start_D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatestartTextField();
			}
		});
		end_Y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateendTextField();
			}
		});

		end_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateendTextField();
			}
		});

		end_D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateendTextField();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sb_name = name_tf.getText().trim();
				String sb_point = point_tf.getText().trim();
				String sb_start_date = start_tf.getText().trim();
				String sb_end_date = end_tf.getText().trim();
				String sb_plan_file = file_tf.getText().trim();
				
				SubjectVO vo = new SubjectVO();

				vo.setSb_name(sb_name);
				vo.setSb_point(sb_point);
				vo.setSb_start_date(sb_start_date);
				vo.setSb_end_date(sb_end_date);
				vo.setSb_plan_file(sb_plan_file);

				int cnt = p.addSubject(vo);

				if (cnt > 0) {
					JOptionPane.showMessageDialog(AddSubjectDialog.this, "저장완료!");
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

	public AddSubjectDialog(SubjectVO vo) {
		this.vo = vo;
		init();
		viewDialog();
	}

	private void init() {
		setBounds(100, 100, 360, 344);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 352, 274);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("과목명:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(21, 26, 46, 15);
				panel.add(lblNewLabel);
			}
			{
				name_tf = new JTextField();
				name_tf.setBounds(79, 23, 123, 21);
				panel.add(name_tf);
				name_tf.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("과목학점:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(221, 26, 58, 15);
				panel.add(lblNewLabel);
			}
			{
				point_tf = new JTextField();
				point_tf.setColumns(10);
				point_tf.setBounds(283, 23, 29, 21);
				panel.add(point_tf);
			}
			{
				JLabel lblNewLabel = new JLabel("과목 시작일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(21, 70, 70, 15);
				panel.add(lblNewLabel);
			}
			{
				start_Y = new JComboBox();
				start_Y.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"}));
				start_Y.setEditable(true);
				start_Y.setBounds(103, 66, 53, 23);
				panel.add(start_Y);
			}
			{
				JLabel lblNewLabel = new JLabel("년");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(160, 70, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("월");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(228, 70, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("일");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(300, 70, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				start_M = new JComboBox();
				start_M.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
				start_M.setEditable(true);
				start_M.setBounds(184, 66, 43, 23);
				panel.add(start_M);
			}
			{
				start_D = new JComboBox();
				start_D.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
				start_D.setEditable(true);
				start_D.setBounds(252, 66, 43, 23);
				panel.add(start_D);
			}
			{
				JLabel lblNewLabel = new JLabel("과목 종료일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(21, 153, 70, 15);
				panel.add(lblNewLabel);
			}
			{
				end_Y = new JComboBox();
				end_Y.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"}));
				end_Y.setEditable(true);
				end_Y.setBounds(103, 149, 53, 23);
				panel.add(end_Y);
			}
			{
				JLabel lblNewLabel = new JLabel("년");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(160, 153, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				end_M = new JComboBox();
				end_M.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
				end_M.setEditable(true);
				end_M.setBounds(184, 149, 43, 23);
				panel.add(end_M);
			}
			{
				JLabel lblNewLabel = new JLabel("월");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(228, 153, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				end_D = new JComboBox();
				end_D.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
				end_D.setEditable(true);
				end_D.setBounds(252, 149, 43, 23);
				panel.add(end_D);
			}
			{
				JLabel lblNewLabel = new JLabel("일");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(300, 153, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("강의계획서:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(21, 234, 70, 15);
				panel.add(lblNewLabel);
			}
			{
				file_tf = new JTextField();
				file_tf.setColumns(10);
				file_tf.setBounds(103, 231, 180, 21);
				panel.add(file_tf);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1
						.setIcon(new ImageIcon(AddSubjectDialog.class.getResource("/resources/image/filelink1.png")));
				lblNewLabel_1.setBounds(291, 231, 20, 20);
				panel.add(lblNewLabel_1);
			}
			
			end_tf = new JTextField();
			end_tf.setBounds(103, 190, 189, 21);
			panel.add(end_tf);
			end_tf.setColumns(10);
			
			start_tf = new JTextField();
			start_tf.setBounds(103, 110, 192, 21);
			panel.add(start_tf);
			start_tf.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("저장");
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
		name_tf.setText(vo.getSb_name());
		point_tf.setText(vo.getSb_point());
		file_tf.setText(vo.getSb_plan_file());

	}
	private void updatestartTextField() {
		String year = (String) start_Y.getSelectedItem();
		String month = (String) start_M.getSelectedItem();
		String day = (String) start_D.getSelectedItem();

		// 선택한 연도, 월, 일을 결합하여 생년월일 형식으로 텍스트 필드에 설정
		start_tf.setText(year + "-" + month + "-" + day);
	}
	private void updateendTextField() {
		String year = (String) end_Y.getSelectedItem();
		String month = (String) end_M.getSelectedItem();
		String day = (String) end_D.getSelectedItem();

		// 선택한 연도, 월, 일을 결합하여 생년월일 형식으로 텍스트 필드에 설정
		end_tf.setText(year + "-" + month + "-" + day);
	}
}
