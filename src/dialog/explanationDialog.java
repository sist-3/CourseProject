package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class explanationDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public ArrayList<ImageIcon> img_list = new ArrayList<>();
	public int idx;
	public JPanel buttonPane;
	public JButton beforebt;
	public JButton nextbt;



	/**
	 * Create the dialog.
	 */
	public explanationDialog() {
//		for(int i=0;i<7;i++) {
//			StringBuffer sb = new StringBuffer();
//			sb.append("/resources/explantion/");
//			sb.append(1);
//			sb.append(".png");
////			Image im = get
//			ImageIcon img = new ImageIcon(sb.toString());
//			img_list.add(img);
//		}
		img_list.add(new ImageIcon(explanationDialog.class.getResource("/resources/explantion/1.png")));
		img_list.add(new ImageIcon(explanationDialog.class.getResource("/resources/explantion/2.png")));
		img_list.add(new ImageIcon(explanationDialog.class.getResource("/resources/explantion/3.png")));
		img_list.add(new ImageIcon(explanationDialog.class.getResource("/resources/explantion/4.png")));
		img_list.add(new ImageIcon(explanationDialog.class.getResource("/resources/explantion/5.png")));
		img_list.add(new ImageIcon(explanationDialog.class.getResource("/resources/explantion/6.png")));
		img_list.add(new ImageIcon(explanationDialog.class.getResource("/resources/explantion/7.png")));
		
		setBounds(100, 100, 850, 696);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setSize(830,620);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		
		lblNewLabel.setIcon(img_list.get(0));
		lblNewLabel.setBounds(0, 0, 830, 624);
		contentPanel.add(lblNewLabel);
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(15, 151, 29));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				beforebt = new JButton("이전");
				beforebt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//현재 첫번째 문제가 아닐경우 실행
						if(idx>0) {
							if(idx==img_list.size()-1) {
								nextbt.setText("다음");
							}
							idx--;
							lblNewLabel.setIcon(img_list.get(idx));
							contentPanel.revalidate();
							
							
						}
					}
				});
				beforebt.setActionCommand("OK");
				buttonPane.add(beforebt);
				getRootPane().setDefaultButton(beforebt);
			}
			{
				nextbt = new JButton("다음");
				nextbt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(idx<img_list.size()-1) {
							idx++;
							lblNewLabel.setIcon(img_list.get(idx));
							contentPanel.revalidate();
						}else if(idx==img_list.size()-2) {
							idx++;
							nextbt.setText("마침");
							lblNewLabel.setIcon(img_list.get(idx));
							contentPanel.revalidate();
						}else if(idx==img_list.size()-1) {
							setVisible(false);
						}
					}
				});
				nextbt.setActionCommand("Cancel");
				buttonPane.add(nextbt);
			}
		}
	}
}
