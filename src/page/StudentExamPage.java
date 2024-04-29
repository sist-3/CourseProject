package page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.HyeyoonDAO;
import page.panel.QuizMultiplePanel;
import page.panel.QuizSubjectivePanel;
import util.PageManager;
import vo.ExamSubmitVO;
import vo.QuizVO;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;

public class StudentExamPage extends JPanel {
	StudentExamListManagementPage se_page;
	QuizVO qvo;
	int i = 0;
	String st_idx="1", e_idx = "1";
	JPanel chageQuizPanel;
	HyeyoonDAO hdao;
	public JPanel mq_panel = new QuizMultiplePanel();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<QuizVO> q_list;
	ExamSubmitVO esvo;
	private Component multipleQuizPanel;
	private Component subjectiveQuizPanel;
	private JTextField subjectiveTf;
	private CardLayout card = new CardLayout();
	private JLabel multipleQuizLabel, subjectiveQuizLabel, q_q1_cnt, q_q2_cnt,
	q_q3_cnt, q_q4_cnt, q_q1_lb, q_q2_lb, q_q3_lb, q_q4_lb;
	private JTextArea multipleQuizTa, subjectiveTa;
	int q_cnt;
	ButtonGroup group;
	JRadioButton q_q1_ans, q_q2_ans, q_q3_ans, q_q4_ans;
	Map<String, String> map = new HashMap<>();

	 
	public StudentExamPage(StudentExamListManagementPage se_page) {
		System.out.println("시험풀기 페이지로 이동 성공");
		hdao = new HyeyoonDAO();
		q_list = hdao.quizList(se_page.e_idx);
		q_cnt = Integer.parseInt(q_list.get(0).getQ_cnt()); // 문제 몇번인지
	
		group = new ButtonGroup();
		
		PageManager pageManager = PageManager.getInstance();
		setLayout(null);

		JPanel StudentExamPanel = new JPanel();
		StudentExamPanel.setBounds(12, 10, 774, 579);
		StudentExamPanel.setLayout(null);
		add(StudentExamPanel);

		JLabel lblNewLabel = new JLabel("시험풀기");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 0, 103, 30);
		StudentExamPanel.add(lblNewLabel);
		

		JPanel solvedQuizPanel = new JPanel();
		solvedQuizPanel.setBounds(540, 0, 234, 24);
		StudentExamPanel.add(solvedQuizPanel);
		solvedQuizPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		/* 시간남으면 전체/ 안푼문제 조회 추가 -- 복붙위치 여기 */
		
		chageQuizPanel = new JPanel();
		chageQuizPanel.setBounds(0, 34, 786, 503);
		StudentExamPanel.add(chageQuizPanel);
		chageQuizPanel.setLayout(card);
		
		JPanel multipleQuizPanel = new JPanel();
		chageQuizPanel.add(multipleQuizPanel, "multiple");
		multipleQuizPanel.setLayout(null);
		
		multipleQuizLabel = new JLabel("1.");
		multipleQuizLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		multipleQuizLabel.setBounds(0, 0, 43, 48);
		multipleQuizPanel.add(multipleQuizLabel);
		
		multipleQuizTa = new JTextArea();
		multipleQuizTa.setFont(new Font("Monospaced", Font.PLAIN, 15));
		multipleQuizTa.setBounds(49, 10, 699, 143);
		multipleQuizPanel.add(multipleQuizTa);
		
		JPanel multiple = new JPanel();
		multiple.setBounds(12, 191, 750, 281);
		multipleQuizPanel.add(multiple);
		multiple.setLayout(new GridLayout(4, 3, 0, 0));
		
		JPanel q_q1 = new JPanel();
		q_q1.setBorder(new EmptyBorder(10, 20, 10, 20));
		multiple.add(q_q1);
		q_q1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		q_q1_cnt = new JLabel("1. ");
		q_q1_cnt.setFont(new Font("굴림", Font.BOLD, 15));
		q_q1.add(q_q1_cnt);
		
		q_q1_lb = new JLabel("");
		q_q1_lb.setFont(new Font("굴림", Font.PLAIN, 12));
		q_q1.add(q_q1_lb);
		
		q_q1_ans = new JRadioButton("");
		q_q1.add(q_q1_ans);
		
		JPanel q_q2 = new JPanel();
		q_q2.setBorder(new EmptyBorder(10, 20, 10, 20));
		multiple.add(q_q2);
		q_q2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		q_q2_cnt = new JLabel("2. ");
		q_q2_cnt.setFont(new Font("굴림", Font.BOLD, 15));
		q_q2.add(q_q2_cnt);
		
		q_q2_lb = new JLabel("");
		q_q2_lb.setFont(new Font("굴림", Font.PLAIN, 12));
		q_q2.add(q_q2_lb);
		
		JRadioButton q_q2_ans = new JRadioButton("");
		q_q2.add(q_q2_ans);
		
		JPanel q_q3 = new JPanel();
		q_q3.setBorder(new EmptyBorder(10, 20, 10, 20));
		multiple.add(q_q3);
		q_q3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		q_q3_cnt = new JLabel("3. ");
		q_q3_cnt.setFont(new Font("굴림", Font.BOLD, 15));
		q_q3.add(q_q3_cnt);
		
		q_q3_lb = new JLabel("");
		q_q3_lb.setFont(new Font("굴림", Font.PLAIN, 12));
		q_q3.add(q_q3_lb);
		
		JRadioButton q_q3_ans = new JRadioButton("");
		q_q3.add(q_q3_ans);
		
		JPanel q_q4 = new JPanel();
		q_q4.setBorder(new EmptyBorder(10, 20, 10, 20));
		multiple.add(q_q4);
		q_q4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		q_q4_cnt = new JLabel("4.");
		q_q4_cnt.setFont(new Font("굴림", Font.BOLD, 15));
		q_q4.add(q_q4_cnt);
		
		q_q4_lb = new JLabel("");
		q_q4_lb.setFont(new Font("굴림", Font.PLAIN, 12));
		q_q4.add(q_q4_lb);
		
		JRadioButton q_q4_ans = new JRadioButton("");
		q_q4.add(q_q4_ans);
		
		group.add(q_q1_ans);
		group.add(q_q2_ans);
		group.add(q_q3_ans);
		group.add(q_q4_ans);
		
		JPanel subjectivePanel = new JPanel();
		chageQuizPanel.add(subjectivePanel, "subjective");
		subjectivePanel.setLayout(null);
		
		
		subjectiveQuizLabel = new JLabel("1.");
		subjectiveQuizLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		subjectiveQuizLabel.setBounds(0, 0, 43, 48);
		subjectivePanel.add(subjectiveQuizLabel);
		
	
		subjectiveTa = new JTextArea();
		subjectiveTa.setFont(new Font("Monospaced", Font.PLAIN, 15));
		subjectiveTa.setBounds(55, 10, 699, 143);
		subjectivePanel.add(subjectiveTa);
		
		subjectiveTf = new JTextField();
		subjectiveTf.setColumns(10);
		subjectiveTf.setBounds(523, 171, 229, 50);
		subjectivePanel.add(subjectiveTf);
			
		//처음은 첫번째 문제 조회
		showQuiz(q_list.get(i).getQ_idx(), i);
		
			JPanel leftRight_btn_panel = new JPanel();
			leftRight_btn_panel.setBounds(255, 549, 231, 30);
			StudentExamPanel.add(leftRight_btn_panel);
			leftRight_btn_panel.setLayout(new GridLayout(1, 2, 0, 0));	

				JButton left_bt = new JButton("◀");
				JButton right_bt = new JButton("▶");

				left_bt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						i--;
						q_cnt--;
	
						if(q_cnt == 0) {
							JOptionPane.showMessageDialog(null, "첫번째 문제입니다.");
							i++;
							q_cnt++;
						}
					
						showQuiz(q_list.get(i).getQ_idx(), i);
					}
				});
				
				
				right_bt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						i++;
						q_cnt++;
						
						if(q_cnt > q_list.size()) {
							JOptionPane.showMessageDialog(null, "마지막 문제입니다.");
							i--;
							q_cnt--;
						}
						showQuiz(q_list.get(i).getQ_idx(), i);
					}
				});
				leftRight_btn_panel.add(left_bt);
				leftRight_btn_panel.add(right_bt);
				
				// q_cnt 1이거나 마지막이면 left, right 버튼 안보임
				
				
			JButton submit_bt = new JButton("제출");
			// submit 버튼 누르면 commit	
			submit_bt.setBounds(692, 549, 70, 30);
			StudentExamPanel.add(submit_bt);	
	}
	
	//시험문제 출력함수
	public void showQuiz(String q_idx, int i) {
		String q_type = q_list.get(i).getQ_type();
		String qcnt = q_list.get(i).getQ_cnt();
		String q_quiz = q_list.get(i).getQ_quiz();
		String q_q1 = q_list.get(i).getQ_q1();
		String q_q2 = q_list.get(i).getQ_q2();
		String q_q3 = q_list.get(i).getQ_q3();
		String q_q4 = q_list.get(i).getQ_q4();
		String answer = solveChk("1","1",i).getEsu_answer();
		
		
		
		if(q_type.equals("0")) {	//객관식
			multipleQuizLabel.setText(qcnt);
			multipleQuizTa.setText(q_quiz);
			q_q1_lb.setText(q_q1);
			q_q2_lb.setText(q_q2);
			q_q3_lb.setText(q_q3);
			q_q4_lb.setText(q_q4);
			
			if(solveChk(e_idx,q_idx,i).getEsu_answer() != null) { //문제 안풀었으면 insert
				System.out.println("있다");
				if(answer.equals("1")) {
					q_q1_ans.setSelected(true);
				}else if(answer.equals("2")) {
					q_q2_ans.setSelected(true);
				}else if(answer.equals("3")) {
					q_q3_ans.setSelected(true);
				}else {
					q_q4_ans.setSelected(true);
				}
				hdao.updateAns(e_idx, st_idx, q_idx, group.getElements().toString());
			}else {	//문제 풀었으면 update
				hdao.insertAns(e_idx, st_idx, q_idx, group.getElements().toString());
			}
			card.show(chageQuizPanel, "multiple");
			
		}else if(q_type.equals("1")){ // 주관식
			subjectiveQuizLabel.setText(qcnt);
			subjectiveTa.setText(q_quiz);
			
			if(solveChk("1","1",i).getEsu_answer() != null) { //문제 안풀었으면 insert
				subjectiveTf.setText(answer);
				hdao.updateAns(e_idx, st_idx, q_idx, subjectiveTf.getText());
			}else {	//문제 풀었으면 update
				hdao.insertAns(e_idx, st_idx, q_idx, subjectiveTf.getText() );
			}
			card.show(chageQuizPanel, "subjective");
		}
			
		
	}
	
	//문제 풀었는지 안풀었는지 확인 후 풀었으면 답 set
	public ExamSubmitVO solveChk(String e_idx, String q_idx, int i) {
		map.put("e_idx", e_idx);
		map.put("st_idx", q_idx);
		map.put("q_idx", q_list.get(i).getQ_idx());
		
		esvo = hdao.solveChk(map);
		
		return esvo;
	}
	
	//객관식일때 문제 set
	
	//주관식 일때 문제 set
}
