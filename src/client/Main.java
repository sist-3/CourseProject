package client;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banner.DefaultBanner;
import banner.ProfessorBanner;
import component.form.Header;
import component.menu.Menu;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;

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
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(470, 200, 1000, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ProfessorBanner banner = new ProfessorBanner();
		Header header = new Header();
		banner.setBounds(0, 115, 200, 550);
		header.setBounds(0, 0, 1000, 66);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(21, 110, 71));
		panel_1.setBounds(0, 65, 200, 50);
		contentPane.add(panel_1);
		contentPane.add(banner);
		contentPane.add(header);
		
		panel = new JPanel();
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(199, 65, 800, 600);
		contentPane.add(panel);
		panel.setLayout(null);
	}
}
