package page.panel;


import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class SubjectivePenel extends JPanel {

	private static final long serialVersionUID = 1L;
	ArrayList<ExamItem> item_list = new ArrayList<ExamItem>();
	// 문제들이 표시될 패널
	JPanel itemPanel;
	public JTextField score_tf;
	public JTextField answer_tf;
	public JTextArea content;
	public JLabel idxLabel;
	/**
	 * Create the panel.
	 */
	public SubjectivePenel() {
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
		itemPanel.add(content, BorderLayout.CENTER);
		content.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel_1 = new JLabel("배점");
		lblNewLabel_1.setBounds(0, 223, 50, 30);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		score_tf = new JTextField();
		score_tf.setBounds(51, 225, 61, 24);
		panel_1.add(score_tf);
		score_tf.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("정답:");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(441, 220, 50, 30);
		panel_1.add(lblNewLabel_1_1);
		
		answer_tf = new JTextField();
		answer_tf.setColumns(10);
		answer_tf.setBounds(492, 222, 199, 24);
		panel_1.add(answer_tf);
		
		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(29, 14, 21, 30);
		panel.add(lblNewLabel);
	}
}
