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
		System.out.println("페이지 이동 성공");
		hdao = new HyeyoonDAO();
		List<QuizVO> q_list = hdao.quizList(se_page.e_idx);
		
			
		System.out.println(q_list.get(0).getQ_quiz());
		
		//while((q_cnt > 0) && (q_cnt < q_list.size())) {
			
		//}
	}
	
}
