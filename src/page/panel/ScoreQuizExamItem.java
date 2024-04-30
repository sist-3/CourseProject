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

public class ScoreQuizExamItem extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField textField;
	MultiplePanel exam;
	
	/**
	 * Create the panel.
	 */
	public ScoreQuizExamItem(ScoreQuizMultiplePanel exam) {
		// 사이즈 너비540,높이30
		setPreferredSize(new Dimension(600, 40));
		setLayout(new BorderLayout(0, 0));
		// 상위폴더 객체정보 가져오기
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(12, 10, 352, 21);
		panel.add(textField);
		textField.setColumns(20);
	}

}
