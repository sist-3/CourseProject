package page.panel;


import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

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

import page.StudentExamPage;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class QuizSubjectivePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizSubjectivePanel panel = new QuizSubjectivePanel();
					panel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuizSubjectivePanel() {
		setLayout(null);
		
		JPanel StudentExamPanel = new JPanel();
		StudentExamPanel.setBounds(12, 10, 774, 579);
		add(StudentExamPanel);
		StudentExamPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("시험풀기");
		lblNewLabel.setBounds(0, 0, 103, 30);
		StudentExamPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		
		JPanel solvedQuizPanel = new JPanel();
		solvedQuizPanel.setBounds(540, 0, 234, 24);
		StudentExamPanel.add(solvedQuizPanel);
		solvedQuizPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JButton allQuiz_btn = new JButton("전체문제");
		allQuiz_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		solvedQuizPanel.add(allQuiz_btn);
		
		JButton yetQuiz_btn = new JButton("풀지않은문제");
		solvedQuizPanel.add(yetQuiz_btn);
		
		JPanel subjectivePanel = new JPanel();
		subjectivePanel.setLayout(null);
		subjectivePanel.setBounds(0, 34, 786, 283);
		StudentExamPanel.add(subjectivePanel);
		
		JLabel lblNewLabel_1 = new JLabel("1.");
		lblNewLabel_1.setBounds(0, 0, 43, 48);
		subjectivePanel.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(55, 10, 699, 143);
		subjectivePanel.add(textArea);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(523, 171, 229, 50);
		subjectivePanel.add(textField);
		
		JPanel leftRight_btn_panel = new JPanel();
		leftRight_btn_panel.setBounds(255, 549, 231, 30);
		StudentExamPanel.add(leftRight_btn_panel);
		leftRight_btn_panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JButton left_bt = new JButton("◀");
		leftRight_btn_panel.add(left_bt);
		
		JButton right_bt = new JButton("▶");
		leftRight_btn_panel.add(right_bt);
		
		JButton submit_bt = new JButton("제출");
		submit_bt.setBounds(692, 549, 70, 30);
		StudentExamPanel.add(submit_bt);
		
	}
}
