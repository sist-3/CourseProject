package page.panel;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class MultiplePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JButton btnNewButton = new JButton("문항추가");
	public ArrayList<ExamItem> item_list = new ArrayList<ExamItem>();
	// 문제들이 표시될 패널
	public JPanel itemPanel;
	public JTextField scorer_tf;
	public JTextArea content;
	public JLabel idxLabel;
	
	/**
	 * Create the panel.
	 */
	public MultiplePanel() {
		setSize(800,450);
		setLayout(new BorderLayout(0, 0));
		 
		//기본패널
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		add(panel);
		panel.setLayout(null);
		
		idxLabel = new JLabel("0");
		idxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idxLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		idxLabel.setBounds(1, 24, 28, 20);
		panel.add(idxLabel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(null);
		panel_1.setBounds(15, 150, 785, 250);
		panel.add(panel_1);
		panel_1.setLayout(null);
		itemPanel = new JPanel();
		itemPanel.setBounds(10, 0, 775, 210);
		panel_1.add(itemPanel);
		itemPanel.setBackground(Color.WHITE);
		itemPanel.setBorder(null);
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		
		
		
		JLabel lblNewLabel_1 = new JLabel("배점");
		lblNewLabel_1.setBounds(0, 223, 50, 30);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		scorer_tf = new JTextField();
		scorer_tf.setBounds(51, 225, 61, 24);
		panel_1.add(scorer_tf);
		scorer_tf.setColumns(10);
		for(int i=0;i<2;i++) {
			// 문제항목들을 추가
			add_Item();
		}
		btnNewButton.setIcon(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(item_list.size()<4) {
					add_Item();
				}
			}
		});
		btnNewButton.setBounds(568, 24, 90, 30);
		panel.add(btnNewButton);
		
		//문항내용
		content = new JTextArea();
		content.setBounds(45, 27, 500, 100);
		content.setBackground(Color.lightGray);
		panel.add(content);
		
		JLabel lblNewLabel = new JLabel(".");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(32, 19, 8, 30);
		panel.add(lblNewLabel);
		
		
		
		
	}
	
	public void add_Item() {
		ExamItem item = new ExamItem(MultiplePanel.this);
		item_list.add(item);
		itemPanel.add(item);
		itemPanel.revalidate();
		
	}
	public void add_Item(String str) {
	    ExamItem item = new ExamItem(MultiplePanel.this);
	    item.textField.setText(str);
	    item_list.add(item);
	    itemPanel.add(item);
	    itemPanel.revalidate();
	}
}
