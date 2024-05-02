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

public class ScoreQuizExamItem extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField textField;
	private final JLabel lblNewLabel_1 = new JLabel(".");
	
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
		
		JLabel lblNewLabel = new JLabel("1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 13, 28, 15);
		panel.add(lblNewLabel);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 8, 16, 24);
		panel.add(lblNewLabel_1);
	}

}
