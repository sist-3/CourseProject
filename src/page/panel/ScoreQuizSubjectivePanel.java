package page.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class ScoreQuizSubjectivePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	ArrayList<ExamItem> item_list = new ArrayList<ExamItem>();
	// 문제들이 표시될 패널
	JPanel itemPanel;
	public JTextField score_tf;
	public JTextField answer_tf;
	public JTextArea content;
	public JLabel idxLabel;
	public JCheckBox wrongCkb;
	public JCheckBox correctCkb;
	/**
	 * Create the panel.
	 */
	public ScoreQuizSubjectivePanel() {
		setSize(800,350);
		setLayout(new BorderLayout(0, 0));
		
		//기본패널
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		add(panel);
		panel.setLayout(null);
		 
		idxLabel = new JLabel("0");
		idxLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		idxLabel.setBounds(12, 22, 21, 22);
		panel.add(idxLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(null);
		panel_1.setBounds(15, 70, 785, 250);
		panel.add(panel_1);
		panel_1.setLayout(null);
		itemPanel = new JPanel();
		itemPanel.setBounds(10, 0, 775, 210);
		panel_1.add(itemPanel);
		itemPanel.setBackground(Color.WHITE);
		itemPanel.setBorder(null);
		itemPanel.setLayout(new BorderLayout(0, 0));
		
		content = new JTextArea();
		content.setEditable(false);
		itemPanel.add(content, BorderLayout.CENTER);
		content.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel_1 = new JLabel("배점");
		lblNewLabel_1.setBounds(0, 223, 50, 30);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		score_tf = new JTextField();
		score_tf.setEditable(false);
		score_tf.setBounds(51, 225, 61, 24);
		panel_1.add(score_tf);
		score_tf.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("정답:");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(441, 220, 50, 30);
		panel_1.add(lblNewLabel_1_1);
		
		answer_tf = new JTextField();
		answer_tf.setEditable(false);
		answer_tf.setColumns(10);
		answer_tf.setBounds(492, 222, 199, 24);
		panel_1.add(answer_tf);
		
		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(29, 14, 21, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("정답:");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(507, 23, 57, 23);
		panel.add(lblNewLabel_2);
		
		correctCkb = new JCheckBox("");
		correctCkb.setBounds(563, 21, 21, 23);
		panel.add(correctCkb);
		
		
		JLabel lblNewLabel_3 = new JLabel("오답:");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(598, 23, 57, 22);
		panel.add(lblNewLabel_3);
		
		wrongCkb = new JCheckBox("");
		wrongCkb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				correctCkb.setSelected(false);
			}
		});
		wrongCkb.setBounds(650, 22, 21, 23);
		panel.add(wrongCkb);
		
		correctCkb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wrongCkb.setSelected(false);
				
			}
		});
	}

}
