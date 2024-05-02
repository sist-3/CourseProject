package page;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import component.piechart.PieChart;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import component.piechart.PieChart.PeiChartType;
import dao.YubinDAO;
import util.LoginManager;
import vo.LoginVO;
import vo.SubjectVO;

import javax.swing.DefaultComboBoxModel;

public class ProgressMonitoringManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	PieChart examSubmint_pc;
	PieChart point_pc;
	PieChart pass_pc;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public ProgressMonitoringManagementPage() {
		setSize(new Dimension(800, 600));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setSize(new Dimension(800, 600));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel title = new JLabel("성취도 관리");
		title.setFont(new Font("나눔고딕", Font.BOLD, 30));
		title.setBounds(12, 30, 372, 38);
		panel.add(title);
		
		// 권한에 맞는 과목이 나오는 ComboBox
		JComboBox subject_cb = new JComboBox();
		// 1. 권한을 비교해서 관리자이면 모든 과목을 교수이면 담당 과목을 가져옴
		LoginVO loginMember = LoginManager.getInstance().getLoginMember();
		// 2. p_idx의 정보를 가져온다. 관리자 이면 null이 되게끔 코드 작성
		String p_idx = null;
		if(loginMember.getChk_role().equals(LoginManager.PROFESSOR)) {
			p_idx = LoginManager.getInstance().getProfessorInfo().getP_idx();
		}
		// 3. p_idx를 기반으로 과목리스트를 검색한다.
		YubinDAO dao = new YubinDAO();
		List<SubjectVO> subjectList = dao.getSubjectList(p_idx);
		// 4. 과목 리스트에서 과목 이름을 가져와 ComboBox에 추가한다.
		if(subjectList != null) {			
			ArrayList<String> subjectNameList = new ArrayList<>();
			for(SubjectVO vo : subjectList) {
				subjectNameList.add(vo.getSb_name());
			}
			subject_cb.setModel(new DefaultComboBoxModel(subjectNameList.toArray()));
		} else {
			subject_cb.setModel(new DefaultComboBoxModel(new String[] {}));
		}
		subject_cb.setBounds(12, 106, 345, 30);
		panel.add(subject_cb);

		// 시험 응시 현황 타이틀과 파이차트
		JLabel lblNewLabel = new JLabel("시험 응시 현황");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 158, 140, 19);
		panel.add(lblNewLabel);
		examSubmint_pc = new PieChart();
		examSubmint_pc.setLocation(0, 184);
		examSubmint_pc.setSize(new Dimension(262, 242));
		examSubmint_pc.setFont(new Font("프리젠테이션 4 Regular", Font.PLAIN, 19));
		examSubmint_pc.setChartType(PieChart.PeiChartType.DONUT_CHART);
		
		// 점수 분포 타이틀과 파이차트
		JLabel lblNewLabel_1 = new JLabel("점수 분포");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel_1.setBounds(278, 158, 140, 19);
		panel.add(lblNewLabel_1);
		PieChart point_pc = new PieChart();
		point_pc.setSize(new Dimension(262, 242));
		point_pc.setFont(new Font("Dialog", Font.PLAIN, 19));
		point_pc.setChartType(PeiChartType.DONUT_CHART);
		point_pc.setBounds(263, 184, 262, 242);
		panel.add(point_pc);
		
		// 합격 현황 타이틀과 파이차트
		JLabel lblNewLabel_1_1 = new JLabel("합격 현황");
		lblNewLabel_1_1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(545, 158, 140, 19);
		panel.add(lblNewLabel_1_1);
		pass_pc = new PieChart();
		pass_pc.setSize(new Dimension(262, 242));
		pass_pc.setFont(new Font("Dialog", Font.PLAIN, 19));
		pass_pc.setChartType(PeiChartType.DONUT_CHART);
		pass_pc.setBounds(538, 184, 262, 242);
		panel.add(pass_pc);
		
		// 눌린 파이차트 요소에 속하는 학생 목록 테이블
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 436, 776, 154);
		panel.add(scrollPane);
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"학생 이름", "과목명", "점수", "합격여부"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(210);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(66);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(62);
		
	}
	
	private Color makeColor() {
		int r = (int)(Math.random() * 255);
		int g = (int)(Math.random() * 255);
		int b = (int)(Math.random() * 255);
		return new Color(r, g, b);
	}
}
