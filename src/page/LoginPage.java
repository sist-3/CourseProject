package page;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import banner.AdminBanner;
import util.BannerManager;
import util.PageManager;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Cursor;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField id_tf;
	private JPasswordField pw_tf;

	/**
	 * Create the panel.
	 */
	public LoginPage() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(new Color(15, 151, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("프리젠테이션 4 Regular", Font.PLAIN, 50));
		lblNewLabel.setBounds(307, 116, 173, 105);
		panel.add(lblNewLabel);
		
		id_tf = new JTextField();
		id_tf.setText("  ");
		id_tf.setFont(new Font("프리젠테이션 4 Regular", Font.PLAIN, 15));
		id_tf.setHorizontalAlignment(SwingConstants.LEFT);
		id_tf.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		id_tf.setBackground(new Color(156, 211, 161));
		id_tf.setBounds(245, 231, 300, 40);
		id_tf.setBorder(BorderFactory.createCompoundBorder(
				id_tf.getBorder(), 
				BorderFactory.createEmptyBorder(5, 10, 5, 5)));
		panel.add(id_tf);
		id_tf.setColumns(10);
		
		
		pw_tf = new JPasswordField();
		pw_tf.setBackground(new Color(156, 211, 161));
		pw_tf.setBounds(245, 306, 300, 40);
		pw_tf.setBorder(BorderFactory.createCompoundBorder(
				pw_tf.getBorder(), 
				BorderFactory.createEmptyBorder(5, 10, 5, 5)));
		panel.add(pw_tf);

		JButton login = new JButton();
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BannerManager.getInstance().changeBanner("StudentBanner");
				PageManager.getInstance().changePage("ExamManagementPage");
			}
		});
		login.setIcon(new ImageIcon(LoginPage.class.getResource("/resources/image/login.png")));
		login.setBounds(294, 396, 206, 46);
		panel.add(login);
	}
}
