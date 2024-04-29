package page.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ExamItem extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField textField;
	MultiplePanel exam;
	public JCheckBox CorrectCkb;
	
	/**
	 * Create the panel.
	 */
	public ExamItem(MultiplePanel exam) {
		// 사이즈 너비540,높이30
		setPreferredSize(new Dimension(600, 40));
		setLayout(new BorderLayout(0, 0));
		// 상위폴더 객체정보 가져오기
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		textField = new JTextField();
		textField.setBounds(12, 10, 352, 21);
		panel.add(textField);
		textField.setColumns(20);
		
		CorrectCkb = new JCheckBox("");
		CorrectCkb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//체크박스 중복선택을 허용하지 않음
				for(int i=0;i<exam.item_list.size();i++) {
					exam.item_list.get(i).CorrectCkb.setSelected(false);	
				}
				CorrectCkb.setSelected(true);
			}
		});
		CorrectCkb.setBounds(383, 10, 21, 21);
		panel.add(CorrectCkb);
		
		JButton btnNewButton = new JButton("-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(exam.item_list.size()>1) {
					exam.itemPanel.remove(ExamItem.this);
					exam.item_list.remove(ExamItem.this);
					exam.itemPanel.revalidate();;
				}
			}
		});
		btnNewButton.setBounds(410, 9, 39, 23);
		panel.add(btnNewButton);

	}
}
