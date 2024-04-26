package client;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.form.Header;
<<<<<<< HEAD
import component.menu.Menu;
import page.StudentExamListManagementPage;

import java.awt.Component;
=======
import util.BannerManager;
import util.PageManager;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
>>>>>>> 163b92bff6753ab386cb37f4047eba1d6580b381
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
<<<<<<< HEAD
	private JPanel panel;
	private CardLayout card;
	
	StudentExamListManagementPage s_Page;
=======
	public JPanel banner;
	public JPanel page;
	public CardLayout bannerCard;
	public CardLayout pageCard;
>>>>>>> 163b92bff6753ab386cb37f4047eba1d6580b381

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
<<<<<<< HEAD
//		setUndecorated(true);
=======
		PageManager pageManager= PageManager.getInstance();
		BannerManager bannerManager = BannerManager.getInstance();
		banner = new JPanel();
		page = new JPanel();
		pageManager.init(this);
		bannerManager.init(this);
		
		// 메인 프레임 설정
		setUndecorated(true);
>>>>>>> 163b92bff6753ab386cb37f4047eba1d6580b381
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(470, 200, 1000, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel closeImage = new JLabel("");
		closeImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bannerManager.changeBanner("DefaultBanner");
				pageManager.changePage("LoginPage");
			}
		});
		closeImage.setHorizontalAlignment(SwingConstants.CENTER);
		closeImage.setIcon(new ImageIcon(Main.class.getResource("/resources/image/close_button.png")));
		closeImage.setBounds(955, 15, 33, 33);
		contentPane.add(closeImage);

		// 헤더 Panel 추가
		Header header = new Header();
		header.setBounds(0, 0, 1000, 66);
		contentPane.add(header);
		
<<<<<<< HEAD
		panel = new JPanel();
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(199, 65, 800, 600);
		contentPane.add(panel);
		card = new CardLayout();
		panel.setLayout(card);
		
		s_Page = new StudentExamListManagementPage();
		panel.add("s_card", s_Page);
=======
		// 헤더와 공간을 띄워주는 Panel 추후에 회원정보를 넣으면 좋을거 같음
		JPanel padding = new JPanel();
		padding.setBackground(new Color(21, 110, 71));
		padding.setBounds(0, 65, 200, 50);
		contentPane.add(padding);
		
		// 배너를 위치 시킬 cardLayout Panel 추가
		banner.setBounds(0, 115, 200, 550);
		contentPane.add(banner);
		bannerCard = new CardLayout(0,0);
		banner.setLayout(bannerCard);
		for(JPanel get_banner : bannerManager.getBanner()) {
			banner.add(get_banner, get_banner.getClass().getSimpleName());
		}
		bannerManager.changeBanner("DefaultBanner");

		
		// 페이지를 위치 시킬 cardLayout Panel 추가
		page.setBounds(200, 65, 800, 600);
		contentPane.add(page);
		pageCard = new CardLayout(0,0);
		page.setLayout(pageCard);
		for(JPanel get_page : pageManager.getPage()) {
			page.add(get_page, get_page.getClass().getSimpleName());
		}
		pageManager.changePage("LoginPage");
>>>>>>> 163b92bff6753ab386cb37f4047eba1d6580b381
	}
}
