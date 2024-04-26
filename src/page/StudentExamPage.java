package page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.HyeyoonDAO;
import vo.QuizVO;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JRadioButton;




public class StudentExamPage extends JPanel {
	StudentExamListManagementPage se_page;
	int q_cnt; //문제 몇번인지
	HyeyoonDAO hdao;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public StudentExamPage(StudentExamListManagementPage se_page) {
		System.out.println("시험풀기 페이지로 이동 성공");
		hdao = new HyeyoonDAO();
		List<QuizVO> q_list = hdao.quizList(se_page.e_idx);
		
		for(int i=0; i<q_list.size(); i++) {
			System.out.println(q_list.get(i).getQ_quiz());
		}
		//문제가 있는동안 보여줌
		while((q_cnt > 0) && (q_cnt < q_list.size())) {
			//q_cnt 1이거나 마지막이면 left, right 버튼 안보임
			//전체문제보기 버튼 vs 안푼문제보기 버튼 누르면
				//전체면 안푼 가장 작은숫자 문제부터 
					//객관식이면 multiple로보이고
					//주관식이면 Sub
				//안푼문제면 안푼문제중 가장 작은숫자문제부터
					//객관식이면 multiple로보이고
					//주관식이면 Sub
			
			//submit 버튼 누르면 commit
					
			}
		}
	}
	
}
