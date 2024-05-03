package page;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import vo.ExamJoinVO;
import vo.ExamSubmitVO;
import vo.QuizVO;

public class ExamScoreManagemenPage extends JPanel {

	//문제가 생성되는 위치의 y값
		private static final long serialVersionUID = 1L;
		private JTextField textField;
		private  ScoreQuizMultiplePanel multiple_panel;
		CardLayout card;
		JPanel panel_2;
		String ename;
		hyuk dao;
		String e_idx,st_idx;
		
		private List<QuizVO> qz_list;
		private List<ExamSubmitVO> as_list;
		private List<Map<String, String>> map_list;
		private ArrayList<Score> sc_list= new ArrayList<Score>();
		
		int status =2;
		// 시험문제 idx 
		// ex_list에서 불러올때사용
		int idx;
		
		ScoreQuizSubjectivePanel subjective_panel;

	/**
	 * Create the panel.
	 */
	public ExamScoreManagemenPage(String e,String st) {
		this.setSize(800,600);
		setLayout(null);
		e_idx=e;
		st_idx=st;
		init(e_idx,st_idx);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ename);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(24, 21, 409, 41);
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
				update();
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
				update();
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
				ExamJoinVO jvo = new ExamJoinVO();
				update();
				jvo.setE_idx(e_idx);
				jvo.setSt_idx(st_idx);
				int sum=0;
				for(int i=0;i<qz_list.size();i++) {
					if (sc_list.get(i).isCorrect) {
						sum+=Integer.parseInt(sc_list.get(i).point);
					}
				}
				System.out.println(sum);
				jvo.setEj_score(Integer.toString(sum));
				dao.add_Score(jvo);
				PageManager pagemanager = PageManager.getInstance();
				pagemanager.changePage(new ExamAllListManagementPage());
			}
		});
		btnNewButton_4.setBounds(676, 560, 97, 23);
		panel.add(btnNewButton_4);
		
		//첫화면보여주기
		showQuiz(idx);
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
			if (sc_list.get(idx).isCorrect) {
				multiple_panel.correctCkb.setSelected(true);
				multiple_panel.wrongCkb.setSelected(false);
			}else {
				multiple_panel.wrongCkb.setSelected(true);
				multiple_panel.correctCkb.setSelected(false);
			}
			
			card.show(panel_2, "multiple");
			status=0;
		}else if(qz.getQ_type().equals("1")) {
			subjective_panel.content.setText(qz.getQ_quiz());
			subjective_panel.answer_tf.setText(qz.getQ_answer());
			subjective_panel.score_tf.setText(qz.getQ_point());
			subjective_panel.idxLabel.setText(Integer.toString(idx+1));
			if(sc_list.get(idx).isCorrect) {
				subjective_panel.correctCkb.setSelected(true);
				subjective_panel.wrongCkb.setSelected(false);
			}else {
				subjective_panel.correctCkb.setSelected(false);
				subjective_panel.wrongCkb.setSelected(true);		
			}
			card.show(panel_2, "Subjective");
			status=1;
		}
	}
	
	public void init(String e,String st) {
		dao = new hyuk();
		
		qz_list = dao.quizList(e);
		ename = dao.getEname(e);
		map_list = dao.getAssess(e, st);
		for(int i=0;i<map_list.size();i++) {
			Map<String, String> map = map_list.get(i);
			Score score = new Score(map.get("q_point"),map.get("q_answer").equals(map.get("esu_answer")));
			sc_list.add(score);
			System.out.println(score.point);
		}
	}
	
	public void update() {
		switch (status) {
			//현재 창이 객관식일때
			case 0:
				if(multiple_panel.correctCkb.isSelected()) {
					sc_list.get(idx).isCorrect=true;
				}else {
					sc_list.get(idx).isCorrect=false;
				}
				
				//현재창이 주관식일때
			case 1:
				if(subjective_panel.correctCkb.isSelected()) {
					sc_list.get(idx).isCorrect=true;
				}else {
					sc_list.get(idx).isCorrect=false;
				}
			    
				
		};
	}
	
	class Score {
		String point;
		boolean isCorrect;
		
		public Score(String p, boolean c) {
			point=p;
			isCorrect = c;
		}
	}
}
