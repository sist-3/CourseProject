package page.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class ScoreQuizExamItem extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField textField;
	private final JLabel lblNewLabel_1 = new JLabel(".");
	public JLabel qz_idx;
	public JLabel answer_label;
	/**
	 * Create the panel.
	 */
	public ScoreQuizExamItem() {
		// 사이즈 너비540,높이30
		setPreferredSize(new Dimension(600, 40));
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(Color.WHITE);
		textField.setBounds(42, 11, 352, 21);
		panel.add(textField);
		textField.setColumns(20);
		
		qz_idx = new JLabel("1");
		qz_idx.setHorizontalAlignment(SwingConstants.RIGHT);
		qz_idx.setFont(new Font("굴림", Font.PLAIN, 18));
		qz_idx.setBounds(0, 13, 28, 15);
		panel.add(qz_idx);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 8, 16, 24);
		panel.add(lblNewLabel_1);
		
		answer_label = new JLabel("");
		answer_label.setIcon(null);
		answer_label.setBounds(444, 0, 67, 40);
		panel.add(answer_label);
	}
}
