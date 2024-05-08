package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.gummoDAO;
import page.SubjectManagementPage;
import util.LoginManager;
import vo.LoginVO;
import vo.StudentVO;
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
import java.util.ArrayList;
import java.util.List;

public class UpdateSubjectDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	protected static final AddSubjectDialog AddSubjectDialog = null;
	private final JPanel contentPanel = new JPanel();
	private JTextField name_tf;
	private JTextField point_tf;
	private JComboBox start_Y;
	private JComboBox start_M;
	private JComboBox start_D;
	private JComboBox end_Y;
	private JComboBox end_M;
	private JComboBox end_D;
	private JComboBox date_Y;
	private JComboBox date_M;
	private JComboBox date_D;
	private JComboBox yn_cb;
	private JTextField file_tf;
	private JComboBox mgr_cb;
	SubjectManagementPage p;
	AddSubjectDialog a;
	SubjectVO vo;
	JButton okButton;
	JButton cancelButton;
	gummoDAO gdao = new gummoDAO();
	private String filePath;
	private String filename;
	private List<SubjectVO> mgrList;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @wbp.parser.constructor
	 */
	//과목변경창
	public UpdateSubjectDialog(SubjectManagementPage p, SubjectVO vo) {
		this.p = p;
		this.vo = vo;
		init();

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sb_name = name_tf.getText().trim();
				String sb_point = point_tf.getText().trim();
				String sb_mgr = mgr_cb.getSelectedItem().toString();
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
				vo.setSb_idx(p.getVo().getSb_idx()); // 과목의 st_idx 값을 가져옵니다.
				int result = JOptionPane.showConfirmDialog(UpdateSubjectDialog.this, "변경하시겠습니까?", null,
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {

					int cnt = gdao.updateSubject(vo);

					if (cnt > 0) {
						JOptionPane.showMessageDialog(UpdateSubjectDialog.this, "변경완료!");
						dispose();
						p.totalSubject(null);
					} else {
						JOptionPane.showMessageDialog(UpdateSubjectDialog.this, "변경실패...");
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
		setBounds(100, 100, 467, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 451, 343);
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
				name_tf.setBounds(68, 37, 123, 21);
				panel.add(name_tf);
				name_tf.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("학점:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(213, 40, 29, 15);
				panel.add(lblNewLabel);
			}
			{
				point_tf = new JTextField();
				point_tf.setColumns(10);
				point_tf.setBounds(245, 37, 29, 21);
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
				lblNewLabel.setBounds(262, 93, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("일");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(348, 93, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				start_M = new JComboBox();
				start_M.setModel(new DefaultComboBoxModel(
						new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
				start_M.setEditable(true);
				start_M.setBounds(214, 89, 43, 23);
				panel.add(start_M);
			}
			{
				start_D = new JComboBox();
				start_D.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
						"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
						"25", "26", "27", "28", "29", "30", "31" }));
				start_D.setEditable(true);
				start_D.setBounds(286, 89, 50, 23);
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
				end_Y.setModel(new DefaultComboBoxModel(new String[] { "1940", "1941", "1942", "1943", "1944", "1945",
						"1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957",
						"1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
						"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981",
						"1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993",
						"1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005",
						"2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
						"2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
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
				end_M.setBounds(214, 149, 43, 23);
				panel.add(end_M);
			}
			{
				JLabel lblNewLabel = new JLabel("월");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(262, 153, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				end_D = new JComboBox();
				end_D.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
						"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
						"25", "26", "27", "28", "29", "30", "31" }));
				end_D.setEditable(true);
				end_D.setBounds(286, 149, 50, 23);
				panel.add(end_D);
			}
			{
				JLabel lblNewLabel = new JLabel("일");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(348, 153, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("강의계획서:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(21, 287, 70, 15);
				panel.add(lblNewLabel);
			}
			{
				file_tf = new JTextField();
				file_tf.setColumns(10);
				file_tf.setBounds(103, 284, 180, 21);
				panel.add(file_tf);
			}

			JLabel lblNewLabel_2 = new JLabel("담당교수:");
			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_2.setBounds(295, 40, 57, 15);
			panel.add(lblNewLabel_2);

			JLabel lblNewLabel = new JLabel("과목 등록일:");
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel.setBounds(43, 214, 70, 15);
			panel.add(lblNewLabel);

			date_Y = new JComboBox();
			date_Y.setModel(new DefaultComboBoxModel(new String[] { "1940", "1941", "1942", "1943", "1944", "1945",
					"1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957",
					"1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
					"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981",
					"1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993",
					"1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005",
					"2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
					"2018", "2019", "2020", "2021", "2022", "2023", "2024" }));
			date_Y.setEditable(true);
			date_Y.setBounds(125, 210, 53, 23);
			panel.add(date_Y);

			date_M = new JComboBox();
			date_M.setModel(new DefaultComboBoxModel(
					new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
			date_M.setEditable(true);
			date_M.setBounds(214, 210, 43, 23);
			panel.add(date_M);

			date_D = new JComboBox();
			date_D.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30", "31" }));
			date_D.setEditable(true);
			date_D.setBounds(286, 210, 50, 23);
			panel.add(date_D);

			JLabel lblNewLabel_1 = new JLabel("년");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_1.setBounds(190, 214, 12, 15);
			panel.add(lblNewLabel_1);

			JLabel lblNewLabel_3 = new JLabel("월");
			lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_3.setBounds(262, 214, 12, 15);
			panel.add(lblNewLabel_3);

			JLabel lblNewLabel_4 = new JLabel("일");
			lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
			lblNewLabel_4.setBounds(348, 214, 12, 15);
			panel.add(lblNewLabel_4);
			{
				yn_cb = new JComboBox();
				yn_cb.setModel(new DefaultComboBoxModel(new String[] { "Y", "N" }));
				yn_cb.setEditable(true);
				yn_cb.setBounds(393, 283, 46, 23);
				panel.add(yn_cb);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("개설여부:");
				lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel_5.setBounds(334, 287, 57, 15);
				panel.add(lblNewLabel_5);
			}
			
			JLabel file_lb = new JLabel("");
			file_lb.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					String subjectName = name_tf.getText().trim();
					//파일 업로드 다이얼로그를 띄움
					UpdateFileDialog updateDialog = new UpdateFileDialog(UpdateSubjectDialog.this, subjectName);
				 //파일 선택 후 filePath를 설정
					setFilePath( updateDialog.getSelectedFilePath());
					 //파일명을 file_tf에 표시
					file_tf.setText(updateDialog.getFileName());
				
				}
			});
			file_lb.setIcon(new ImageIcon(UpdateSubjectDialog.class.getResource("/resources/image/filelink1.png")));
			file_lb.setHorizontalAlignment(SwingConstants.CENTER);
			file_lb.setBounds(286, 282, 29, 23);
			panel.add(file_lb);
			
			mgr_cb = new JComboBox();
			mgr_cb.setBounds(364, 36, 75, 23);
			panel.add(mgr_cb);
			mgrCombobox();
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
		viewDialog();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void viewDialog() {
		name_tf.setText(vo.getSb_name());
		point_tf.setText(vo.getSb_point());
		file_tf.setText(vo.getSb_plan_file());
		yn_cb.setSelectedItem(vo.getSb_yn());
		start_Y.setSelectedItem(vo.getSb_start_date().substring(0, 4));
		start_M.setSelectedItem(vo.getSb_start_date().substring(5, 7));
		start_D.setSelectedItem(vo.getSb_start_date().substring(8, 10));
		end_Y.setSelectedItem(vo.getSb_end_date().substring(0, 4));
		end_M.setSelectedItem(vo.getSb_end_date().substring(5, 7));
		end_D.setSelectedItem(vo.getSb_end_date().substring(8, 10));
		date_Y.setSelectedItem(vo.getSb_date().substring(0, 4));
		date_M.setSelectedItem(vo.getSb_date().substring(5, 7));
		date_D.setSelectedItem(vo.getSb_date().substring(8, 10));
		mgr_cb.setSelectedItem(vo.getSb_mgr());
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setFileName(String filename) {
       this.filename = filename;//name_tf.getText();   
    }
	public void mgrCombobox() {

		LoginVO loginMember = LoginManager.getInstance().getLoginMember();
		String professorIdx = null;
		boolean isProfessor = loginMember.getChk_role().equals(LoginManager.PROFESSOR);
		if (isProfessor) {
			professorIdx = LoginManager.getInstance().getProfessorInfo().getP_idx();
		}
		mgrList = gdao.professorList(professorIdx);

		if (mgrList != null) {

			ArrayList<String> mgrNameList = new ArrayList<>();

			for (SubjectVO vo : mgrList) {
				mgrNameList.add(vo.getSb_mgr());
			}
			mgr_cb.setModel(new DefaultComboBoxModel(mgrNameList.toArray()));

		} 
	}
}
