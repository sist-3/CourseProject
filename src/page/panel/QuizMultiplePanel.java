package page.panel;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.border.SoftBevelBorder;

import page.StudentExamListManagementPage;
import page.StudentExamPage;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class QuizMultiplePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizMultiplePanel frame = new QuizMultiplePanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuizMultiplePanel() {
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
		
		JPanel multipleQuizPanel = new JPanel();
		multipleQuizPanel.setBounds(0, 28, 774, 511);
		StudentExamPanel.add(multipleQuizPanel);
		multipleQuizPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("1.");
		lblNewLabel_1.setBounds(0, 0, 43, 48);
		multipleQuizPanel.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(49, 10, 699, 143);
		multipleQuizPanel.add(textArea);
		
		JPanel multiple = new JPanel();
		multiple.setBounds(12, 191, 750, 281);
		multipleQuizPanel.add(multiple);
		multiple.setLayout(new GridLayout(4, 3, 0, 0));
		
		JPanel q_q1 = new JPanel();
		q_q1.setBorder(new EmptyBorder(10, 20, 10, 20));
		multiple.add(q_q1);
		q_q1.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel q_q1_cnt = new JLabel("New label");
		q_q1.add(q_q1_cnt);
		
		JLabel q_q1_lb = new JLabel("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		q_q1.add(q_q1_lb);
		
		JRadioButton q_q1_ans = new JRadioButton("");
		q_q1.add(q_q1_ans);
		
		JPanel q_q2 = new JPanel();
		q_q2.setBorder(new EmptyBorder(10, 20, 10, 20));
		multiple.add(q_q2);
		q_q2.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel q_q2_cnt = new JLabel("New label");
		q_q2.add(q_q2_cnt);
		
		JLabel q_q2_lb = new JLabel("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		q_q2.add(q_q2_lb);
		
		JRadioButton q_q2_ans = new JRadioButton("");
		q_q2.add(q_q2_ans);
		
		JPanel q_q3 = new JPanel();
		q_q3.setBorder(new EmptyBorder(10, 20, 10, 20));
		multiple.add(q_q3);
		q_q3.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel q_q3_cnt = new JLabel("New label");
		q_q3.add(q_q3_cnt);
		
		JLabel q_q3_lb = new JLabel("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		q_q3.add(q_q3_lb);
		
		JRadioButton q_q3_ans = new JRadioButton("");
		q_q3.add(q_q3_ans);
		
		JPanel q_q4 = new JPanel();
		q_q4.setBorder(new EmptyBorder(10, 20, 10, 20));
		multiple.add(q_q4);
		q_q4.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel q_q4_cnt = new JLabel("New label");
		q_q4.add(q_q4_cnt);
		
		JLabel q_q4_lb = new JLabel("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		q_q4.add(q_q4_lb);
		
		JRadioButton q_q4_ans = new JRadioButton("");
		q_q4.add(q_q4_ans);
		

		
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

