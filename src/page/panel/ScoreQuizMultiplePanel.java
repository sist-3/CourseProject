package page.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class ScoreQuizMultiplePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public ArrayList<ScoreQuizExamItem> item_list = new ArrayList<ScoreQuizExamItem>();
	// 문제들이 표시될 패널
	public JPanel itemPanel;
	public JTextField scorer_tf;
	public JTextArea content;
	public JLabel idxLabel;
	public JCheckBox correctCkb;
	public JCheckBox wrongCkb;

	/**
	 * Create the panel.
	 */
	public ScoreQuizMultiplePanel() {
		setSize(800,450);
		setLayout(new BorderLayout(0, 0));
		 
		//기본패널
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		add(panel);
		panel.setLayout(null);
		
		idxLabel = new JLabel("0");
		idxLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		idxLabel.setBounds(12, 24, 21, 20);
		panel.add(idxLabel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(null);
		panel_1.setBounds(15, 150, 785, 250);
		panel.add(panel_1);	
		panel_1.setLayout(null);
		itemPanel = new JPanel();
		itemPanel.setBounds(10, 0, 775, 210);
		panel_1.add(itemPanel);
		itemPanel.setBackground(Color.WHITE);
		itemPanel.setBorder(null);
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		
		
		
		JLabel lblNewLabel_1 = new JLabel("배점");
		lblNewLabel_1.setBounds(0, 223, 50, 30);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		scorer_tf = new JTextField();
		scorer_tf.setBackground(Color.WHITE);
		scorer_tf.setEditable(false);
		scorer_tf.setBounds(51, 225, 61, 24);
		panel_1.add(scorer_tf);
		scorer_tf.setColumns(10);
		
		//문항내용
		content = new JTextArea();
		content.setEditable(false);
		content.setBounds(45, 27, 500, 100);
		content.setBackground(Color.lightGray);
		panel.add(content);
		
		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(25, 19, 8, 30);
		panel.add(lblNewLabel);
		
		correctCkb = new JCheckBox("");
		
		correctCkb.setBounds(655, 54, 21, 23);
		panel.add(correctCkb);
		
		JLabel lblNewLabel_3 = new JLabel("정답:");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(596, 52, 59, 30);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("오답:");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(596, 95, 59, 30);
		panel.add(lblNewLabel_4);
		
		wrongCkb = new JCheckBox("");
		wrongCkb.setBounds(655, 100, 19, 23);
		panel.add(wrongCkb);
		
		correctCkb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wrongCkb.setSelected(false);
			}
		});
		
		wrongCkb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				correctCkb.setSelected(false);
				
			}
		});
		
		
	}
	
	public void add_Item(String str) {
		ScoreQuizExamItem item = new ScoreQuizExamItem();
		item.textField.setText(str);
		item_list.add(item);
		itemPanel.add(item);
		itemPanel.revalidate();
	}
	
	
}
