package page;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JTextField;

import dao.hyuk;
import page.panel.MultiplePanel;
import page.panel.SubjectivePenel;
import util.PageManager;
import vo.QuizVO;

import javax.swing.DefaultComboBoxModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.CardLayout;

public class MakeExamManagementPage extends JPanel {
	//문제가 생성되는 위치의 y값
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private MultiplePanel multiple_panel;
	CardLayout card;
	JPanel panel_2;
	public String e_idx;
	private List<QuizVO> qz_list;
	String ename;
	
	int status =2;
	// 시험문제 idx 
	// ex_list에서 불러올때사용
	int idx;
	
	SubjectivePenel subjective_panel;
	
	/**
	 * Create the panel.
	 */
	public MakeExamManagementPage(String e) {
		this.setSize(800,600);
		setLayout(null);
		hyuk dao = new hyuk();
		e_idx=e;
		qz_list = dao.quizList(e);
		ename = dao.getEname(e);
		if(qz_list.size()==0)
			qz_list = new ArrayList<QuizVO>();

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ename);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(24, 21, 434, 41);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("문제삭제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(qz_list.size()==0) {
					card.show(panel_2, "empty");
					status=2;
				}else if(idx<1) {
					qz_list.remove(idx);
					idx--;
					status=2;
					card.show(panel_2, "empty");
				}else if(idx<qz_list.size()-1) {
					qz_list.remove(idx);
					showQuiz(idx);
				}else if(idx==qz_list.size()-1) {
					qz_list.remove(idx);
					idx--;
					showQuiz(idx);
				}else if(idx>=qz_list.size()) {
					idx=qz_list.size()-1;
					showQuiz(idx);
				}
				System.out.println("크기 :"+qz_list.size());
			}
		});
		btnNewButton.setBounds(660, 36, 97, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("문제추가");
		btnNewButton_1.setBounds(558, 36, 97, 23);
		panel.add(btnNewButton_1);
		
		JComboBox category_cb = new JComboBox();
		category_cb.setModel(new DefaultComboBoxModel(new String[] {"객관식", "주관식"}));
		category_cb.setBounds(470, 36, 76, 23);
		panel.add(category_cb);
		

		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(15, 70, 781, 480);
		panel.add(panel_2);
		card = new CardLayout();
		panel_2.setLayout(card);
		
		multiple_panel = new MultiplePanel();
		panel_2.add(multiple_panel, "multiple");
		multiple_panel.setBackground(Color.WHITE);
		multiple_panel.setBorder(null);
		multiple_panel.setLayout(new BoxLayout(multiple_panel, BoxLayout.Y_AXIS)); // Y 축으로 배치
		multiple_panel.setPreferredSize(new Dimension(780, 480));
		
		subjective_panel = new SubjectivePenel();
		panel_2.add(subjective_panel, "Subjective");
		
		JPanel empty = new JPanel();
		panel_2.add(empty, "empty");
		card.show(panel_2, "empty");
		JButton btnNewButton_2 = new JButton("이전");
		//이전페이지 보여주기
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idx>0) {
					save();
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
					save();
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
				dao.deleteAll(e_idx);
				save();
				for(int i=0;i<qz_list.size();i++) {
					QuizVO qvo = qz_list.get(i);
					qvo.setE_idx(e_idx);
					qvo.setQ_cnt(Integer.toString(i));
					dao.addQuiz(qvo);	
				}
				PageManager pagemanager = PageManager.getInstance();
				pagemanager.changePage(new ExamAllListManagementPage());
			}
		});
		btnNewButton_4.setBounds(676, 560, 97, 23);
		panel.add(btnNewButton_4);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (category_cb.getSelectedIndex()) {
					case 0: 
						save();
						clear();
						status=0;
						idx = qz_list.size();
						multiple_panel.idxLabel.setText(Integer.toString(idx+1));
						card.show(panel_2, "multiple");
						break;
					case 1:
						save();
						clear();
						status=1;
						idx = qz_list.size();
						subjective_panel.idxLabel.setText(Integer.toString(idx+1));
						card.show(panel_2, "Subjective");
						
				}
				
			}
		});
		
		if (qz_list.size()>0) {
			showQuiz(0);
		}
	}
	
	// 추가하기
	public void addExample_list() {
		QuizVO qz = new QuizVO();
		boolean isNumeric = true;
		switch (status) {
		//현재 창이 객관식일때
		case 0:
			
			//멀티플 패널에 추가한 항목 만큼 반복문
			qz.setQ_quiz(multiple_panel.content.getText());
			
			// 퀴즈타입 객관식
			qz.setQ_type("0");
			//1번문항
			if(multiple_panel.item_list.size()>0) {
				qz.setQ_q1(multiple_panel.item_list.get(0).textField.getText());
				if(multiple_panel.item_list.get(0).CorrectCkb.isSelected())
					qz.setQ_answer("1");
			}
			//2번문항
			if(multiple_panel.item_list.size()>1) {
				qz.setQ_q2(multiple_panel.item_list.get(1).textField.getText());
				if(multiple_panel.item_list.get(1).CorrectCkb.isSelected())
					qz.setQ_answer("2");
				
			}
			//3번문항
			if(multiple_panel.item_list.size()>2) {
				qz.setQ_q3(multiple_panel.item_list.get(2).textField.getText());
				if(multiple_panel.item_list.get(2).CorrectCkb.isSelected())
					qz.setQ_answer("3");
			}
			//4번문항
			if(multiple_panel.item_list.size()>3) {
				qz.setQ_q4(multiple_panel.item_list.get(3).textField.getText());
				if(multiple_panel.item_list.get(3).CorrectCkb.isSelected())
					qz.setQ_answer("4");
			}
			
		    
		    for (int i = 0; i < multiple_panel.scorer_tf.getText().length(); i++) {
		        if (!Character.isDigit(multiple_panel.scorer_tf.getText().charAt(i))) {
		            isNumeric = false;
		            break;
		        }
		    }
			//점수 저장
			if(isNumeric)
				qz.setQ_point(multiple_panel.scorer_tf.getText());
			else
				qz.setQ_point("0");
			qz_list.add(qz);
			idx = qz_list.size();
			break;
		//현재 창이 주관식 일때
		case 1:
			//문제 타이틀 저장
			qz.setQ_quiz(subjective_panel.content.getText());
			//문제정답저장
			qz.setQ_answer(subjective_panel.answer_tf.getText());
			//주관식
			qz.setQ_type("1");
			//점수저장
			 for (int i = 0; i < subjective_panel.score_tf.getText().length(); i++) {
			        if (!Character.isDigit(subjective_panel.score_tf.getText().charAt(i))) {
			            isNumeric = false;
			            break;
			        }
			    }
			if(isNumeric)
				qz.setQ_point(subjective_panel.score_tf.getText());
			else
				qz.setQ_point("0");
			qz_list.add(qz);
			idx = qz_list.size();
			break;
//			System.out.println(ex.getTitle()+"/"+ex.getCategory()+"/"+ex.getAnswer()+"/"+ex.getContent()+"/"+ex.getScore());
		}
		
	}
	
	// 문제 수정하기
	public void updateQ(int idx) {
		boolean isNumeric = true;
		switch (status) {
			//현재 창이 객관식일때
			case 0:
				qz_list.get(idx).setQ_answer(null);
				qz_list.get(idx).setQ_quiz(multiple_panel.content.getText());
				//1번문항 업데이트
				if(multiple_panel.item_list.size()>0) {
					qz_list.get(idx).setQ_q1(multiple_panel.item_list.get(0).textField.getText());
					if(multiple_panel.item_list.get(0).CorrectCkb.isSelected())
						qz_list.get(idx).setQ_answer("1");
				}else {
					qz_list.get(idx).setQ_q1(null);
				}
				// 2번문항 업데이트
				if(multiple_panel.item_list.size()>1) {
					qz_list.get(idx).setQ_q2(multiple_panel.item_list.get(1).textField.getText());
					if(multiple_panel.item_list.get(1).CorrectCkb.isSelected())
						qz_list.get(idx).setQ_answer("2");
				}else {
					qz_list.get(idx).setQ_q2(null);
				}
				// 3번문항 업데이트
				if(multiple_panel.item_list.size()>2) {
					qz_list.get(idx).setQ_q3(multiple_panel.item_list.get(2).textField.getText());
					if(multiple_panel.item_list.get(2).CorrectCkb.isSelected())
						qz_list.get(idx).setQ_answer("3");
				}else {
					qz_list.get(idx).setQ_q3(null);
				}
				// 4번문항업데이트
				if(multiple_panel.item_list.size()>1) {
					qz_list.get(idx).setQ_q2(multiple_panel.item_list.get(1).textField.getText());
					if(multiple_panel.item_list.get(1).CorrectCkb.isSelected())
						qz_list.get(idx).setQ_answer("2");
				}else {
					qz_list.get(idx).setQ_q1(null);
				}
				
			    for (int i = 0; i < multiple_panel.scorer_tf.getText().length(); i++) {
			        if (!Character.isDigit(multiple_panel.scorer_tf.getText().charAt(i))) {
			            isNumeric = false;
			            break;
			        }
			    }
				if(isNumeric)
					qz_list.get(idx).setQ_point(multiple_panel.scorer_tf.getText());
				else
					qz_list.get(idx).setQ_point("0");
				break;
				
				//현재창이 주관식일때
			case 1:
				qz_list.get(idx).setQ_quiz(subjective_panel.content.getText());
				qz_list.get(idx).setQ_answer(subjective_panel.answer_tf.getText());
				
			    for (int i = 0; i < subjective_panel.score_tf.getText().length(); i++) {
			        if (!Character.isDigit(subjective_panel.score_tf.getText().charAt(i))) {
			            isNumeric = false;
			            break;
			        }
			    }
				if(isNumeric)
					qz_list.get(idx).setQ_point(subjective_panel.score_tf.getText());
				else
					qz_list.get(idx).setQ_point("0");
				
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
		multiple_panel.add_Item();
		multiple_panel.add_Item();
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
			if(qz.getQ_answer()!=null&&multiple_panel.item_list.size()>0)
				multiple_panel.item_list.get(Integer.valueOf(qz.getQ_answer())-1).CorrectCkb.setSelected(true);;
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
	//업데이트할지 새로만들지
	public void save() {
		if(idx<qz_list.size()) {
			 updateQ(idx);
		}else {
			addExample_list();
		}
	}
}
