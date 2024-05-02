package page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.HyeyoonDAO;
import page.panel.QuizMultiplePanel;
import page.panel.QuizSubjectivePanel;
import util.LoginManager;
import util.PageManager;
import vo.ExamSubmitVO;
import vo.QuizVO;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private static final String MULTI = "0";
	private static final String SUBJECT = "1";
	StudentExamListManagementPage se_page;
	QuizVO qvo;
	int i = 0;
	String st_idx;
	String e_idx;
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
	private JLabel multipleQuizLabel, subjectiveQuizLabel, q_q1_cnt, q_q2_cnt, q_q3_cnt, q_q4_cnt, q_q1_lb, q_q2_lb,
			q_q3_lb, q_q4_lb;
	private JTextArea multipleQuizTa, subjectiveTa;
	ButtonGroup group = new ButtonGroup();;
	JRadioButton q_q1_ans, q_q2_ans, q_q3_ans, q_q4_ans, other;
	Map<String, String> map;
	String chk_radio;
	List<ExamSubmitVO> es_list;
	

	public StudentExamPage(StudentExamListManagementPage se_page) {
		System.out.println("시험풀기 페이지로 이동 성공");
		hdao = new HyeyoonDAO();
		st_idx = se_page.st_idx;
		e_idx = se_page.e_idx;
		q_list = hdao.quizList(se_page.e_idx);
		es_list = new ArrayList<>();

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

		chageQuizPanel = new JPanel();
		chageQuizPanel.setBounds(0, 34, 786, 503);
		StudentExamPanel.add(chageQuizPanel);
		chageQuizPanel.setLayout(card);

		JPanel multipleQuizPanel = new JPanel();
		chageQuizPanel.add(multipleQuizPanel, "multiple");
		multipleQuizPanel.setLayout(null);

		

		multipleQuizLabel = new JLabel("1.");
		multipleQuizLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		multipleQuizLabel.setBounds(12, -1, 43, 48);
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
		q_q1_ans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chk_radio = "1";
			}
		});
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

		q_q2_ans = new JRadioButton("");
		q_q2_ans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chk_radio = "2";
			}
		});
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

		q_q3_ans = new JRadioButton("");
		q_q3_ans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chk_radio = "3";
			}
		});
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

		q_q4_ans = new JRadioButton("");
		q_q4_ans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chk_radio = "4";
			}
		});
		q_q4.add(q_q4_ans);
		
		other = new JRadioButton();
		other.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chk_radio = "";
			}
		});

		 group.add(q_q1_ans);
		 group.add(q_q2_ans);
		 group.add(q_q3_ans);
		 group.add(q_q4_ans);
		 group.add(other);

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

		if (q_list.get(i) != null)
			showQuiz(i);

		JPanel leftRight_btn_panel = new JPanel();
		leftRight_btn_panel.setBounds(255, 549, 231, 30);
		StudentExamPanel.add(leftRight_btn_panel);
		leftRight_btn_panel.setLayout(new GridLayout(1, 2, 0, 0));

		JButton left_bt = new JButton("◀");
		JButton right_bt = new JButton("▶");

		left_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEsList(i);
				i--;
				if (i < 0) {
					JOptionPane.showMessageDialog(null, "첫번째 문제입니다.");
					i++;
				}
				showQuiz(i);
			}
		});

		right_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEsList(i);
				i++;

				if (i >= q_list.size()) {
					JOptionPane.showMessageDialog(null, "마지막 문제입니다.");
					i--;
				}
				showQuiz(i);
			}
		});
		leftRight_btn_panel.add(left_bt);
		leftRight_btn_panel.add(right_bt);

		JButton submit_bt = new JButton("제출");
		submit_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEsList(i);
				insertEsList();
			}
		});

		submit_bt.setBounds(692, 549, 70, 30);
		StudentExamPanel.add(submit_bt);

	}


	// 시험문제 출력함수
	public void showQuiz(int i) {
		String q_type = q_list.get(i).getQ_type();
		String qcnt = q_list.get(i).getQ_cnt();
		String q_quiz = q_list.get(i).getQ_quiz();
		String q_q1 = q_list.get(i).getQ_q1();
		String q_q2 = q_list.get(i).getQ_q2();
		String q_q3 = q_list.get(i).getQ_q3();
		String q_q4 = q_list.get(i).getQ_q4();
		chk_radio = "";
		subjectiveTf.setText("");

		if(chk_radio == "") {
			other.setSelected(true);
		}
		
		if (q_type.equals("0")) { // 객관식
			multipleQuizLabel.setText(qcnt);
			multipleQuizTa.setText(q_quiz);
			q_q1_lb.setText(q_q1);
			q_q2_lb.setText(q_q2);
			q_q3_lb.setText(q_q3);
			q_q4_lb.setText(q_q4);

			if (es_list.size() > i || q_list.size() == i) {
				chk_radio = es_list.get(i).getEsu_answer();
				
				if (es_list.get(i).getEsu_answer().equals("1")) {
					q_q1_ans.setSelected(true);
				} else if (es_list.get(i).getEsu_answer().equals("2")) {
					q_q2_ans.setSelected(true);
				} else if (es_list.get(i).getEsu_answer().equals("3")) {
					q_q3_ans.setSelected(true);
				} else if (es_list.get(i).getEsu_answer().equals("4")) {
					q_q4_ans.setSelected(true);
				}
			}
			
			card.show(chageQuizPanel, "multiple");

		} else if (q_type.equals("1")) { // 주관식
			subjectiveQuizLabel.setText(qcnt);
			subjectiveTa.setText(q_quiz);
			if (es_list.size() > i || q_list.size() == i) {
				String answer = es_list.get(i).getEsu_answer();
				subjectiveTf.setText(answer);
			}
			card.show(chageQuizPanel, "subjective");
		}

	}

	// 버튼 누를때마다 list.add
	public void addEsList(int i) {
		if (i + 1 > es_list.size() || es_list.size() == 0) {
			System.out.println("처음 저장" + chk_radio);
			
			ExamSubmitVO vo = new ExamSubmitVO();
			vo.setE_idx(e_idx);
			vo.setSt_idx(st_idx);
			vo.setQ_idx(q_list.get(i).getQ_idx());
			
			if (q_list.get(i).getQ_type().equals(MULTI)) {
				vo.setEsu_answer(chk_radio);
			} else {
				vo.setEsu_answer(subjectiveTf.getText());
			}
			es_list.add(vo);
		} else {
			System.out.println("데이터 재저장" + chk_radio);
			// ExamSubmitVO 생성
			ExamSubmitVO vo = new ExamSubmitVO();
			// ExamSubmitVO에 데이터 추가
			es_list.get(i).setE_idx(e_idx);
			es_list.get(i).setSt_idx(st_idx);
			es_list.get(i).setQ_idx(q_list.get(i).getQ_idx());
			if (q_list.get(i).getQ_type().equals(MULTI)) {
				es_list.get(i).setEsu_answer(chk_radio);
			} else {
				es_list.get(i).setEsu_answer(subjectiveTf.getText());
			}
		}
	}

	// submit 누르면 for돌려서 저장
	public void insertEsList() {
		if(es_list.size() < q_list.size()) {
			JOptionPane.showMessageDialog(null, "풀지 않은 문제가 있습니다");
			return;
		}
		hdao.insertAns(es_list);
		JOptionPane.showMessageDialog(null, "제출 완료!");
		
		StudentExamListManagementPage selmp = new StudentExamListManagementPage();
		PageManager.getInstance().changePage(selmp);
	}

}
