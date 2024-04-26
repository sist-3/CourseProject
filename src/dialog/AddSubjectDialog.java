package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import page.SubjectManagementPage;
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

public class AddSubjectDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name_tf;
	private JTextField point_tf;
	private JTextField file_tf;
	SubjectManagementPage p;
	SubjectVO vo;
	JButton okButton;
	JButton cancelButton;

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
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sb_name = name_tf.getText().trim();
				String sb_point = point_tf.getText().trim();
				String sb_plan_file = file_tf.getText().trim();

				SubjectVO vo = new SubjectVO();

				vo.setSb_name(sb_name);
				vo.setSb_point(sb_point);
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
		setBounds(100, 100, 361, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 352, 228);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("과목명:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(58, 26, 46, 15);
				panel.add(lblNewLabel);
			}
			{
				name_tf = new JTextField();
				name_tf.setBounds(106, 23, 206, 21);
				panel.add(name_tf);
				name_tf.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("과목학점:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(46, 64, 58, 15);
				panel.add(lblNewLabel);
			}
			{
				point_tf = new JTextField();
				point_tf.setColumns(10);
				point_tf.setBounds(106, 61, 206, 21);
				panel.add(point_tf);
			}
			{
				JLabel lblNewLabel = new JLabel("과목 시작일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(28, 104, 70, 15);
				panel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(106, 100, 53, 23);
				panel.add(comboBox);
			}
			{
				JLabel lblNewLabel = new JLabel("년");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(160, 104, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("월");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(228, 104, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("일");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(300, 104, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(184, 100, 43, 23);
				panel.add(comboBox);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(252, 100, 43, 23);
				panel.add(comboBox);
			}
			{
				JLabel lblNewLabel = new JLabel("과목 종료일:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(28, 144, 70, 15);
				panel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(106, 140, 53, 23);
				panel.add(comboBox);
			}
			{
				JLabel lblNewLabel = new JLabel("년");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(160, 144, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(184, 140, 43, 23);
				panel.add(comboBox);
			}
			{
				JLabel lblNewLabel = new JLabel("월");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(228, 144, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(252, 140, 43, 23);
				panel.add(comboBox);
			}
			{
				JLabel lblNewLabel = new JLabel("일");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(300, 144, 12, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("강의계획서:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(28, 184, 70, 15);
				panel.add(lblNewLabel);
			}
			{
				file_tf = new JTextField();
				file_tf.setColumns(10);
				file_tf.setBounds(106, 181, 180, 21);
				panel.add(file_tf);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1
						.setIcon(new ImageIcon(AddSubjectDialog.class.getResource("/resources/image/filelink1.png")));
				lblNewLabel_1.setBounds(289, 182, 20, 20);
				panel.add(lblNewLabel_1);
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

	private void viewDialog() {
		name_tf.setText(vo.getSb_name());
		point_tf.setText(vo.getSb_point());
		file_tf.setText(vo.getSb_plan_file());

	}

}
