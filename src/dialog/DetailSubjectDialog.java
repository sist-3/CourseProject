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
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailSubjectDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField file_tf;
	private JTable table;
	private JTextField name_tf;
	private JTextField mgr_tf;
	SubjectManagementPage p;
	SubjectVO vo;

	/**
	 * Launch the application.
	 * @wbp.parser.constructor
	 */

	public DetailSubjectDialog(SubjectManagementPage p) {
		this.p = p;
		init();

	}

	public DetailSubjectDialog(SubjectVO vo) {
		this.vo = vo;
		init();

	}

	/**
	 * Create the dialog.
	 */
	public void init() {
		setBounds(100, 100, 267, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 434, 228);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("과목명:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(23, 20, 57, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("담당교수:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(130, 20, 57, 15);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("강의계획서:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(23, 48, 66, 15);
				panel.add(lblNewLabel);
			}
			{
				file_tf = new JTextField();
				file_tf.setBounds(92, 45, 116, 21);
				panel.add(file_tf);
				file_tf.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("New label");
				lblNewLabel_1.setIcon(
						new ImageIcon(DetailSubjectDialog.class.getResource("/resources/image/filelink1.png")));
				lblNewLabel_1.setBounds(209, 45, 20, 20);
				panel.add(lblNewLabel_1);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 76, 228, 142);
				panel.add(scrollPane);
				{
					table = new JTable();
					scrollPane.setViewportView(table);
				}
			}

			name_tf = new JTextField();
			name_tf.setBounds(68, 17, 57, 21);
			panel.add(name_tf);
			name_tf.setColumns(10);

			mgr_tf = new JTextField();
			mgr_tf.setBounds(190, 17, 57, 21);
			panel.add(mgr_tf);
			mgr_tf.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("확인");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			if (vo != null) {
				name_tf.setText(vo.getSb_name());
				mgr_tf.setText(vo.getSb_mgr());
				file_tf.setText(vo.getSb_plan_file());
				

			}

			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
	}
}
