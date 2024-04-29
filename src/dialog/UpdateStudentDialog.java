package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JTextField birth_tf;
	private JTextField indate_tf;
	private JTextField tel_tf;
	StudentManagementPage p;
	JButton okButton;
	JButton cancelButton;
	StudentVO vo;

	/**
	 * Create the dialog.
	 */
	// 생성자를 수정하여 StudentVO 객체를 받아오고 데이터를 보여주는 메서드를 호출합니다.
	public UpdateStudentDialog(StudentManagementPage p, StudentVO vo) {
		this.p = p;
		this.vo = vo;
		init(); // 다이얼로그 초기화
		viewDialog(); // 데이터를 텍스트 필드에 표시

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
		        int cnt = p.updateStudent(vo);

		        if (cnt > 0) {
		            JOptionPane.showMessageDialog(UpdateStudentDialog.this, "변경완료!");
		            dispose(); // 다이얼로그 닫기
		        }
		    }
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // 다이얼로그 닫기
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

			JComboBox birth_Y = new JComboBox();
			birth_Y.setBounds(89, 111, 57, 23);
			panel.add(birth_Y);

			JComboBox birth_M = new JComboBox();
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
				JComboBox birth_D = new JComboBox();
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

}
