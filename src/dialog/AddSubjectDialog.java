package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FileChooserUI;

import dao.gummoDAO;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

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
	private JComboBox yn_cb;
	private JComboBox date_Y;
	private JComboBox date_M;
	private JComboBox date_D;
	private JTextField file_tf;
	SubjectManagementPage p;
	SubjectVO vo;
	JButton okButton;
	JButton cancelButton;
	gummoDAO gdao = new gummoDAO();
	private JTextField mgr_tf;
	JLabel file_lb;
	FileUploadDialog f;
	private String filePath;
	private String filename;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @wbp.parser.constructor
	 */
	//과목추가창
	public AddSubjectDialog(SubjectManagementPage p) {
		this.p = p;
		init();

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sb_name = name_tf.getText().trim();
				String sb_point = point_tf.getText().trim();
				String sb_mgr = mgr_tf.getText().trim();
				String sb_start_date = start_Y.getSelectedItem().toString() + start_M.getSelectedItem().toString()
						+ start_D.getSelectedItem().toString();
				String sb_end_date = end_Y.getSelectedItem().toString() + end_M.getSelectedItem().toString()
						+ end_D.getSelectedItem().toString();
				String sb_date = date_Y.getSelectedItem().toString() + date_M.getSelectedItem().toString()
						+ date_D.getSelectedItem().toString();
				String sb_plan_file = file_tf.getText().trim();
				String sb_yn = yn_cb.getSelectedItem().toString();

				SubjectVO vo = new SubjectVO();

				vo.setSb_name(sb_name);
				vo.setSb_point(sb_point);
				vo.setSb_mgr(sb_mgr);
				vo.setSb_start_date(sb_start_date);
				vo.setSb_end_date(sb_end_date);
				vo.setSb_date(sb_date);
				vo.setSb_plan_file(sb_plan_file);
				vo.setSb_yn(sb_yn);
				int result = JOptionPane.showConfirmDialog(AddSubjectDialog.this, "저장하시겠습니까?",null, JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION) {
					int cnt = gdao.addSubject(vo);
					
					if (cnt > 0) {
						JOptionPane.showMessageDialog(AddSubjectDialog.this, "저장완료!");
						dispose();
						p.totalSubject(null);
					}
					
				}
				else {
					
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
		setBounds(100, 100, 467, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 460, 343);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("과목명:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(21, 40, 46, 15);
				panel.add(lblNewLabel);
			}
			{
				name_tf = new JTextField();
				name_tf.setBounds(67, 37, 123, 21);
				panel.add(name_tf);
				name_tf.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("학점:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(209, 40, 35, 15);
				panel.add(lblNewLabel);
			}
			{
				point_tf = new JTextField();
				point_tf.setColumns(10);
				point_tf.setBounds(243, 37, 29, 21);
				panel.add(point_tf);
			}
			{
				JLabel lblNewLabel = new JLabel("과목 시작일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(43, 93, 70, 15);
				panel.add(lblNewLabel);
			}
			{
				start_Y = new JComboBox();
				start_Y.setModel(new DefaultComboBoxModel(new String[] { "1940", "1941", "1942", "1943", "1944", "1945",
						"1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957",
						"1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
						"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981",
						"1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993",
						"1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005",
						"2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
						"2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
				start_Y.setEditable(true);
				start_Y.setBounds(125, 89, 53, 23);
				panel.add(start_Y);
			}
			{
				JLabel lblNewLabel = new JLabel("년");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(190, 93, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("월");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(276, 93, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("일");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(357, 97, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				start_M = new JComboBox<>();
				start_M.setModel(new DefaultComboBoxModel(
						new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
				start_M.setEditable(true);
				start_M.setBounds(221, 89, 43, 23);
				panel.add(start_M);
				for (int i = 1; i <= 12; i++) {
					start_M.addItem(String.valueOf(i));
				}
				{
					start_D = new JComboBox();
					start_D.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07",
							"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
							"23", "24", "25", "26", "27", "28", "29", "30", "31" }));
					start_D.setEditable(true);
					start_D.setBounds(302, 89, 43, 23);
					panel.add(start_D);
				}
				{
					JLabel lblNewLabel = new JLabel("과목 종료일:");
					lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel.setBounds(43, 153, 70, 15);
					panel.add(lblNewLabel);
				}
				{
					end_Y = new JComboBox();
					end_Y.setModel(new DefaultComboBoxModel(new String[] { "1940", "1941", "1942", "1943", "1944",
							"1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955",
							"1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966",
							"1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977",
							"1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988",
							"1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999",
							"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
							"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021",
							"2022", "2023", "2024" }));
					end_Y.setEditable(true);
					end_Y.setBounds(125, 149, 53, 23);
					panel.add(end_Y);
				}
				{
					JLabel lblNewLabel = new JLabel("년");
					lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel.setBounds(190, 153, 12, 15);
					panel.add(lblNewLabel);
				}
				{
					end_M = new JComboBox();
					end_M.setModel(new DefaultComboBoxModel(
							new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
					end_M.setEditable(true);
					end_M.setBounds(221, 149, 43, 23);
					panel.add(end_M);
				}
				{
					JLabel lblNewLabel = new JLabel("월");
					lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel.setBounds(276, 153, 12, 15);
					panel.add(lblNewLabel);
				}
				{
					end_D = new JComboBox();
					end_D.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07",
							"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
							"23", "24", "25", "26", "27", "28", "29", "30", "31" }));
					end_D.setEditable(true);
					end_D.setBounds(302, 149, 43, 23);
					panel.add(end_D);
				}
				{
					JLabel lblNewLabel = new JLabel("일");
					lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel.setBounds(357, 153, 12, 15);
					panel.add(lblNewLabel);
				}
				{
					JLabel lblNewLabel = new JLabel("강의계획서:");
					lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel.setBounds(21, 280, 70, 15);
					panel.add(lblNewLabel);
				}
				{
					file_tf = new JTextField();
					file_tf.setEditable(false);
					file_tf.setColumns(10);
					file_tf.setBounds(92, 277, 180, 21);
					panel.add(file_tf);
				}
				{
					file_lb = new JLabel("");
					file_lb.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							String subjectName = name_tf.getText().trim();
							//파일 업로드 다이얼로그를 띄움
							FileUploadDialog uploadDialog = new FileUploadDialog(AddSubjectDialog.this, subjectName);
						 //파일 선택 후 filePath를 설정
							setFilePath( uploadDialog.getSelectedFilePath());
							 //파일명을 file_tf에 표시
							file_tf.setText(uploadDialog.getFileName());
						}
					});
					
							
							
							
	
						

					file_lb.setHorizontalAlignment(SwingConstants.CENTER);
					file_lb.setIcon(
							new ImageIcon(AddSubjectDialog.class.getResource("/resources/image/filelink1.png")));
					file_lb.setBounds(276, 277, 29, 23);
				}
					panel.add(file_lb);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("담당교수:");
					lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel_2.setBounds(288, 40, 57, 15);
					panel.add(lblNewLabel_2);
				}
				{
					mgr_tf = new JTextField();
					mgr_tf.setBounds(345, 37, 81, 21);
					panel.add(mgr_tf);
					mgr_tf.setColumns(10);
				}
				{
					JLabel lblNewLabel_3 = new JLabel("과목 등록일:");
					lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel_3.setBounds(43, 211, 70, 15);
					panel.add(lblNewLabel_3);
				}

				date_Y = new JComboBox();
				date_Y.setEditable(true);
				date_Y.setModel(new DefaultComboBoxModel(new String[] { "1940", "1941", "1942", "1943", "1944", "1945",
						"1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957",
						"1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
						"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981",
						"1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993",
						"1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005",
						"2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
						"2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
				date_Y.setBounds(125, 207, 53, 23);
				panel.add(date_Y);

				date_M = new JComboBox();
				date_M.setEditable(true);
				date_M.setModel(new DefaultComboBoxModel(
						new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
				date_M.setBounds(221, 207, 43, 23);
				panel.add(date_M);

				date_D = new JComboBox();
				date_D.setEditable(true);
				date_D.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
						"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
						"25", "26", "27", "28", "29", "30", "31" }));
				date_D.setBounds(302, 207, 43, 23);
				panel.add(date_D);

				JLabel lblNewLabel = new JLabel("년");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(190, 211, 12, 15);
				panel.add(lblNewLabel);
				{
					JLabel lblNewLabel_1 = new JLabel("월");
					lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel_1.setBounds(276, 211, 12, 15);
					panel.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("일");
					lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel_1.setBounds(357, 211, 12, 15);
					panel.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("개설여부:");
					lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
					lblNewLabel_4.setBounds(317, 280, 57, 15);
					panel.add(lblNewLabel_4);
				}
				{
					yn_cb = new JComboBox();
					yn_cb.setModel(new DefaultComboBoxModel(new String[] { "Y", "N" }));
					yn_cb.setBounds(383, 276, 43, 23);
					panel.add(yn_cb);
				}
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
	public void viewDialog() {
		name_tf.setText(vo.getSb_name());
		file_tf.setText(vo.getSb_plan_file());
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setFileName(String filename) {
       this.filename = filename;//name_tf.getText();
       
        
    }
	
	
	 
}
