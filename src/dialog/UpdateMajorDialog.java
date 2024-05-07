package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.HyeyoonDAO;
import dao.jeong2_DAO;
import page.MajorManagementPage;
import page.ProfessorManagementPage;
import vo.MajorVO;
import vo.ProfessorVO;

public class UpdateMajorDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JButton save_button;
	JButton cancel_Button;
	private JTextField m_code_tf, m_name_tf, m_need_point_tf;
	MajorVO vo;
	MajorManagementPage p;
	HyeyoonDAO hdao = new HyeyoonDAO();

	
	
	public UpdateMajorDialog(MajorManagementPage p, MajorVO vo) {
		this.p = p;
		this.vo = vo;
		
		
		init();
		viewDialog();
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
				JLabel lblNewLabel = new JLabel("전공명:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 39, 50, 35);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("전공코드:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 104, 68, 35);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("졸업학점:");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
				lblNewLabel.setBounds(32, 183, 68, 15);
				panel.add(lblNewLabel);
			}
			
			{
				m_name_tf = new JTextField();
				m_name_tf.setBounds(117, 46, 189, 21);
				panel.add(m_name_tf);
				m_name_tf.setColumns(10);
			}
			m_code_tf = new JTextField();
			m_code_tf.setBounds(117, 111, 189, 21);
			panel.add(m_code_tf);
			m_code_tf.setColumns(10);


			m_need_point_tf = new JTextField();
			m_need_point_tf.setBounds(117, 180, 189, 21);
			panel.add(m_need_point_tf);
			m_need_point_tf.setColumns(10);


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
						updateMajor();
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

	private void viewDialog() {
		m_name_tf.setText(vo.getM_name());
		m_code_tf.setText(vo.getM_code());
		m_need_point_tf.setText(vo.getM_need_point());
	}
	
	public void updateMajor() {
		String m_name = m_name_tf.getText().trim();
		String m_code = m_code_tf.getText().trim();
		String m_need_point = m_need_point_tf.getText().trim();

		Map<String, String> map = new HashMap<>();
		
		map.put("m_idx", vo.getM_idx());
		map.put("m_name", m_name);
		map.put("m_code", m_code);
		map.put("m_need_point", m_need_point);

		hdao.updateMajor(map);
		p.MajorList();
		dispose();
	}
}
