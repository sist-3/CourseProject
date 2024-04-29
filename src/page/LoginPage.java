package page;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import dao.Yubin;
import util.BannerManager;
import util.LoginManager;
import util.PageManager;
import vo.LoginVO;

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

	private static final int ADMIN = 0;
	private static final int PROFESSOR = 1;
	private static final int STUDENT = 2;

	private JTextField id_tf;
	private JPasswordField pw_tf;

	
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
		id_tf.setBorder(
				BorderFactory.createCompoundBorder(id_tf.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
		panel.add(id_tf);
		id_tf.setColumns(10);

		pw_tf = new JPasswordField();
		pw_tf.setBackground(new Color(156, 211, 161));
		pw_tf.setBounds(245, 306, 300, 40);
		pw_tf.setBorder(
				BorderFactory.createCompoundBorder(pw_tf.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 5)));
		panel.add(pw_tf);

		JButton login = new JButton();
		login.addActionListener(new ActionListener() {
			// 로그인 버튼 클릭 시, 로그인 기능 사용
			public void actionPerformed(ActionEvent e) {
				login(id_tf.getText().trim(), String.valueOf(pw_tf.getPassword()).trim());
			}
		});
		login.setIcon(new ImageIcon(LoginPage.class.getResource("/resources/image/login.png")));
		login.setBounds(294, 396, 206, 46);
		panel.add(login);
	}

	private void login(String id, String pw) {
		// 1. 회원 아이디와 회원 비밀번호를 확인
		Yubin yubin = new Yubin();
		LoginVO login_mem = yubin.login(id);
		// 2. 유효성 검사
		// 2-1. 아이디가 검색되지 않을 때
		if (login_mem != null) {
			// 2-2. 비밀번호가 틀렸을 때
			if (!(login_mem.getLog_pw().trim().equals(pw))) {
				JOptionPane.showMessageDialog(this, "비밀번호를 확인해주세요.");
				return;
			}
		} else {
			JOptionPane.showMessageDialog(this, "아이디를 확인해주세요.");
			return;
		}

		// 3. 로그인 한 회원의 정보를 LoginManager에 저장시켜 관리한다.
		LoginManager.getInstance().setLoginMember(login_mem);

		// 4. 로그인 한 회원의 권한을 확인 후 권한에 맞게 페이지와 배너 변경
		switch (Integer.parseInt(login_mem.getChk_role())) {
		case ADMIN:
			PageManager.getInstance().changePage("AdminPage");
			BannerManager.getInstance().changeBanner("AdminBanner");
			break;
		case PROFESSOR:
			PageManager.getInstance().changePage("ProfessorPage");
			BannerManager.getInstance().changeBanner("ProfessorBanner");
			break;
		case STUDENT:
			PageManager.getInstance().changePage("StudentPage");
			BannerManager.getInstance().changeBanner("StudentBanner");
			break;
		}
	}
}
