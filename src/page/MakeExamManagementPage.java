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

import page.panel.MultiplePanel;
import page.panel.SubjectivePenel;
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
	
	private ArrayList<QuizVO> qz_list = new ArrayList<QuizVO>();
	
	int status ;
	// 시험문제 idx 
	// ex_list에서 불러올때사용
	int idx;
	
	SubjectivePenel subjective_panel;
	
	/**
	 * Create the panel.
	 */
	public MakeExamManagementPage() {
		this.setSize(800,600);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 600);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("문제출제");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(24, 21, 133, 41);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("문제삭제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(623, 35, 97, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("문제추가");
		btnNewButton_1.setBounds(521, 35, 97, 23);
		panel.add(btnNewButton_1);
		
		JComboBox category_cb = new JComboBox();
		category_cb.setModel(new DefaultComboBoxModel(new String[] {"객관식", "주관식"}));
		category_cb.setBounds(433, 35, 76, 23);
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
		
		JButton btnNewButton_2 = new JButton("이전");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idx>1) {
					idx--;
					showQuiz(idx);
				}
				
			}
		});
		btnNewButton_2.setBounds(538, 560, 97, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("다음");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_3.setBounds(647, 560, 97, 23);
		panel.add(btnNewButton_3);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (category_cb.getSelectedIndex()) {
					case 0: 
						addExample_list();
						clear();
						status=0;
						idx = qz_list.size();
						card.show(panel_2, "multiple");
						break;
					case 1:
						addExample_list();
						status=1;
						idx = qz_list.size();
						card.show(panel_2, "Subjective");
				}
				
			}
		});
	}
	
	// 추가하기
	public void addExample_list() {
		QuizVO qz = new QuizVO();
		
		switch (status) {
		//현재 창이 객관식일때
		case 0:
			
			//멀티플 패널에 추가한 항목 만큼 반복문
			qz.setQ_quiz(multiple_panel.content.getText());

			qz.setQ_type("0");
			if(multiple_panel.item_list.size()>0) {
				qz.setQ_q1(multiple_panel.item_list.get(0).textField.getText());
				if(multiple_panel.item_list.get(0).CorrectCkb.isSelected())
					qz.setQ_anwer("1");
			}
			if(multiple_panel.item_list.size()>1) {
				qz.setQ_q2(multiple_panel.item_list.get(1).textField.getText());
				if(multiple_panel.item_list.get(1).CorrectCkb.isSelected())
					qz.setQ_anwer("2");
				
			}
			if(multiple_panel.item_list.size()>2) {
				qz.setQ_q3(multiple_panel.item_list.get(2).textField.getText());
				if(multiple_panel.item_list.get(2).CorrectCkb.isSelected())
					qz.setQ_anwer("3");
			}
			if(multiple_panel.item_list.size()>3) {
				qz.setQ_q4(multiple_panel.item_list.get(3).textField.getText());
				if(multiple_panel.item_list.get(3).CorrectCkb.isSelected())
					qz.setQ_anwer("4");
			}
			qz.setQ_point(multiple_panel.scorer_tf.getText());
			//출력 테스트
			System.out.println(qz.getQ_anwer());
			break;
		//현재 창이 주관식 일때
		case 1:
			//문제 타이틀 저장
			qz.setQ_quiz(subjective_panel.content.getText());
			qz.setQ_anwer(subjective_panel.answer_tf.getText());
			qz.setQ_type("1");
			qz.setQ_point(subjective_panel.score_tf.getText());
//			System.out.println(ex.getTitle()+"/"+ex.getCategory()+"/"+ex.getAnswer()+"/"+ex.getContent()+"/"+ex.getScore());
		}
		qz_list.add(qz);
		idx = qz_list.size();
	}
	
	// 문제 수정하기
	public void updateQ(int idx) {
		qz_list.get(idx).setQ_quiz(multiple_panel.content.getText());
	}
	
	// 패널 초기화
	public void clear() {
		// 객관식 초기화
		multiple_panel.content.setText("");
		multiple_panel.scorer_tf.setText("");
		multiple_panel.item_list.clear();
		multiple_panel.itemPanel.removeAll();
		multiple_panel.add_Item();
		multiple_panel.add_Item();
		//주관식 초기화
		subjective_panel.title_textfield.setText("");
		subjective_panel.content.setText("");
		subjective_panel.answer_tf.setText("");
		subjective_panel.score_tf.setText("");
	}
	
	// 넘길때사용
	public void showQuiz(int idx) {
		QuizVO qz = qz_list.get(idx);
		
		if(qz.getQ_type().equals("0")) {
			status=0;
			multiple_panel.content.setText(qz.getQ_quiz());
			multiple_panel.itemPanel.removeAll();
			if(qz.getQ_q1()!=null)
				multiple_panel.add_Item(qz.getQ_q1());
			if(qz.getQ_q2()!=null)
				multiple_panel.add_Item(qz.getQ_q2());
			if(qz.getQ_q3()!=null)
				multiple_panel.add_Item(qz.getQ_q3());
			if(qz.getQ_q4()!=null)
				multiple_panel.add_Item(qz.getQ_q4());
			System.out.println(qz.getQ_anwer());
			multiple_panel.item_list.get(Integer.valueOf(qz.getQ_anwer())-1).CorrectCkb.setSelected(true);;
			multiple_panel.scorer_tf.setText(qz.getQ_point());
			card.show(panel_2, "multiple");
		}else if(qz.getQ_type().equals("1")) {
			status=1;
			
		}
	}
}
