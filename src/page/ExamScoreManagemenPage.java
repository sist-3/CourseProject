package page;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.hyuk;
import page.panel.MultiplePanel;
import page.panel.ScoreQuizMultiplePanel;
import page.panel.ScoreQuizSubjectivePanel;
import page.panel.SubjectivePenel;
import util.PageManager;
import vo.ExamSubmitVO;
import vo.QuizVO;

public class ExamScoreManagemenPage extends JPanel {

	//문제가 생성되는 위치의 y값
		private static final long serialVersionUID = 1L;
		private JTextField textField;
		private  ScoreQuizMultiplePanel multiple_panel;
		CardLayout card;
		JPanel panel_2;
		
		private List<QuizVO> qz_list;
		private List<ExamSubmitVO> as_list;
		
		int status =2;
		// 시험문제 idx 
		// ex_list에서 불러올때사용
		int idx;
		
		ScoreQuizSubjectivePanel subjective_panel;

	/**
	 * Create the panel.
	 */
	public ExamScoreManagemenPage(String e) {
		this.setSize(800,600);
		setLayout(null);
		hyuk dao = new hyuk();
		
		qz_list = dao.quizList(e);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("수정");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(24, 21, 133, 41);
		panel.add(lblNewLabel);
		

		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(15, 70, 781, 480);
		panel.add(panel_2);
		card = new CardLayout();
		panel_2.setLayout(card);
		
		multiple_panel = new ScoreQuizMultiplePanel();
		panel_2.add(multiple_panel, "multiple");
		multiple_panel.setBackground(Color.WHITE);
		multiple_panel.setBorder(null);
		multiple_panel.setLayout(new BoxLayout(multiple_panel, BoxLayout.Y_AXIS)); // Y 축으로 배치
		multiple_panel.setPreferredSize(new Dimension(780, 480));
		
		subjective_panel = new ScoreQuizSubjectivePanel();
		panel_2.add(subjective_panel, "Subjective");
		
		JButton btnNewButton_2 = new JButton("이전");
		//이전페이지 보여주기
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idx>0) {
					if(idx<qz_list.size()) {
						//
						idx--;
					}else {
						idx-=2;
					}
					showQuiz(idx);
				}
				
			}
		});
		btnNewButton_2.setBounds(292, 560, 97, 23);
		panel.add(btnNewButton_2);
		
		//다음페이지 보여주기
		JButton btnNewButton_3 = new JButton("다음");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idx<qz_list.size()-1) {
					idx++;
					showQuiz(idx);
				}
			}
		});
		btnNewButton_3.setBounds(401, 560, 97, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("저장");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<qz_list.size();i++) {
					QuizVO qvo = qz_list.get(i);
					qvo.setE_idx("3");
					qvo.setQ_cnt(Integer.toString(i));
					dao.addQuiz(qvo);
					
				}
				PageManager pagemanager = PageManager.getInstance();
			}
		});
		btnNewButton_4.setBounds(676, 560, 97, 23);
		panel.add(btnNewButton_4);
		
		//첫화면보여주기
		showQuiz(idx);
	}
	
	
	
	// 문제 수정하기
	public void updateQ(int idx) {
		switch (status) {
			//현재 창이 객관식일때
			case 0:
				qz_list.get(idx).setQ_answer(null);
				qz_list.get(idx).setQ_quiz(multiple_panel.content.getText());
				//1번문항 업데이트
				if(multiple_panel.item_list.size()>0) {
					qz_list.get(idx).setQ_q1(multiple_panel.item_list.get(0).textField.getText());
				}else {
					qz_list.get(idx).setQ_q1(null);
				}
				// 2번문항 업데이트
				if(multiple_panel.item_list.size()>1) {
					qz_list.get(idx).setQ_q2(multiple_panel.item_list.get(1).textField.getText());
				}else {
					qz_list.get(idx).setQ_q2(null);
				}
				// 3번문항 업데이트
				if(multiple_panel.item_list.size()>2) {
					qz_list.get(idx).setQ_q3(multiple_panel.item_list.get(2).textField.getText());
				}else {
					qz_list.get(idx).setQ_q3(null);
				}
				// 4번문항업데이트
				if(multiple_panel.item_list.size()>1) {
					qz_list.get(idx).setQ_q2(multiple_panel.item_list.get(1).textField.getText());
				}else {
					qz_list.get(idx).setQ_q1(null);
				}
				qz_list.get(idx).setQ_point(multiple_panel.scorer_tf.getText());
				break;
				
				//현재창이 주관식일때
			case 1:
				qz_list.get(idx).setQ_quiz(subjective_panel.content.getText());
				qz_list.get(idx).setQ_answer(subjective_panel.answer_tf.getText());
				qz_list.get(idx).setQ_point(subjective_panel.score_tf.getText());
				
		}
	}
	
	// 패널 초기화
	public void clear() {
		// 객관식 초기화
		multiple_panel.idxLabel.setText("1");
		multiple_panel.content.setText("");
		multiple_panel.scorer_tf.setText("");
		multiple_panel.item_list.clear();
		multiple_panel.itemPanel.removeAll();
		//주관식 초기화
		subjective_panel.idxLabel.setText("1");
		subjective_panel.content.setText("");
		subjective_panel.answer_tf.setText("");
		subjective_panel.score_tf.setText("");
	}
	
	// 넘길때사용
	public void showQuiz(int idx) {
		QuizVO qz = qz_list.get(idx);
		
		if(qz.getQ_type().equals("0")) {
			multiple_panel.content.setText(qz.getQ_quiz());
			multiple_panel.itemPanel.removeAll();
			multiple_panel.item_list.clear();
			System.out.println(qz.getQ_q1());
			if(qz.getQ_q1()!=null)
				multiple_panel.add_Item(qz.getQ_q1());
			if(qz.getQ_q2()!=null)
				multiple_panel.add_Item(qz.getQ_q2());
			if(qz.getQ_q3()!=null)
				multiple_panel.add_Item(qz.getQ_q3());
			if(qz.getQ_q4()!=null)
				multiple_panel.add_Item(qz.getQ_q4());
			multiple_panel.scorer_tf.setText(qz.getQ_point());
			multiple_panel.idxLabel.setText(Integer.toString(idx+1));
			card.show(panel_2, "multiple");
			status=0;
		}else if(qz.getQ_type().equals("1")) {
			subjective_panel.content.setText(qz.getQ_quiz());
			subjective_panel.answer_tf.setText(qz.getQ_answer());
			subjective_panel.score_tf.setText(qz.getQ_point());
			subjective_panel.idxLabel.setText(Integer.toString(idx+1));
			card.show(panel_2, "Subjective");
			status=1;
		}
	}
}
