package page;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import component.piechart.ModelPieChart;
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
import vo.ExamJoinVO;
import vo.ExamVO;
import vo.LoginVO;
import vo.StudentSubjectVO;
import vo.SubjectVO;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgressMonitoringManagementPage extends JPanel {

	private static final long serialVersionUID = 1L;
	// 컴포넌트 멤버변수
	JPanel panel;
	private JTable table;
	private PieChart examSubmit_pc;
	private PieChart point_pc;
	private PieChart pass_pc;
	private JComboBox subject_cb;
	private JComboBox exam_cb;
	
	// 추가 클래스 내부에서 사용하는 멤버변수
	YubinDAO dao = new YubinDAO();
	private List<SubjectVO> subjectList;
	private List<ExamVO> examList;
	private List<ExamJoinVO> examJoinList;
	private List<StudentSubjectVO> studentSubjectList;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public ProgressMonitoringManagementPage() {
		setSize(new Dimension(800, 600));
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setSize(new Dimension(800, 600));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		examSubmit_pc = new PieChart();
		examSubmit_pc.setLocation(0, 184);
		examSubmit_pc.setSize(new Dimension(262, 242));
		examSubmit_pc.setFont(new Font("프리젠테이션 4 Regular", Font.PLAIN, 19));
		examSubmit_pc.setChartType(PieChart.PeiChartType.DONUT_CHART);
		panel.add(examSubmit_pc);
		
		point_pc = new PieChart();
		point_pc.setSize(new Dimension(262, 242));
		point_pc.setFont(new Font("Dialog", Font.PLAIN, 19));
		point_pc.setChartType(PeiChartType.DONUT_CHART);
		point_pc.setBounds(263, 184, 262, 242);
		panel.add(point_pc);
		
		
		pass_pc = new PieChart();
		pass_pc.setSize(new Dimension(262, 242));
		pass_pc.setFont(new Font("Dialog", Font.PLAIN, 19));
		pass_pc.setChartType(PeiChartType.DONUT_CHART);
		pass_pc.setBounds(538, 184, 262, 242);
		panel.add(pass_pc);

		JLabel title = new JLabel("성취도 관리");
		title.setFont(new Font("나눔고딕", Font.BOLD, 30));
		title.setBounds(12, 30, 372, 38);
		panel.add(title);

		// 권한에 맞는 과목이 나오는 ComboBox
		subject_cb = new JComboBox();
		subject_cb.setBounds(12, 106, 155, 30);
		setSubjectCombobox();
		subject_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setExamCombobox();
			}
		});
		panel.add(subject_cb);
		
		// 과목별 시험이 나오면 ComboBox
		exam_cb = new JComboBox();
		exam_cb.setBounds(183, 106, 235, 30);
		exam_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makePiechart(exam_cb.getSelectedIndex());
			}
		});
		panel.add(exam_cb);

		// 시험 응시 현황 타이틀과 파이차트
		JLabel lblNewLabel = new JLabel("시험 응시 현황");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 158, 140, 19);
		panel.add(lblNewLabel);

		// 점수 분포 타이틀과 파이차트
		JLabel lblNewLabel_1 = new JLabel("점수 분포");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel_1.setBounds(278, 158, 140, 19);
		panel.add(lblNewLabel_1);

		// 합격 현황 타이틀과 파이차트
		JLabel lblNewLabel_1_1 = new JLabel("합격 현황");
		lblNewLabel_1_1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(545, 158, 140, 19);
		panel.add(lblNewLabel_1_1);

		// 눌린 파이차트 요소에 속하는 학생 목록 테이블
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 436, 776, 154);
		panel.add(scrollPane);
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "학생 이름", "과목명", "점수", "합격여부" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

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
	
	private void setSubjectCombobox() {
		// 1. 권한을 비교해서 관리자이면 모든 과목을 교수이면 담당 과목을 가져옴
		LoginVO loginMember = LoginManager.getInstance().getLoginMember();
		// 2. p_idx의 정보를 가져온다. 관리자 이면 null이 되게끔 코드 작성
		String p_idx = null;
		if (loginMember.getChk_role().equals(LoginManager.PROFESSOR)) {
			p_idx = LoginManager.getInstance().getProfessorInfo().getP_idx();
		}
		// 3. p_idx를 기반으로 과목리스트를 검색한다.
		subjectList = dao.getSubjectList(p_idx);
		// 4. 과목 리스트에서 과목 이름을 가져와 ComboBox에 추가한다.
		if (subjectList != null) {
			ArrayList<String> subjectNameList = new ArrayList<>();
			for (SubjectVO vo : subjectList) {
				subjectNameList.add(vo.getSb_name());
			}
			subject_cb.setModel(new DefaultComboBoxModel(subjectNameList.toArray()));
		} else {
			subject_cb.setModel(new DefaultComboBoxModel(new String[] {}));
		}
	}
	
	private void setExamCombobox() {
		String sb_idx = subjectList.get(subject_cb.getSelectedIndex()).getSb_idx();
		examList = dao.getExamList(sb_idx);
		if (examList != null) {
			ArrayList<String> examNameList = new ArrayList<>();
			for (ExamVO vo : examList) {
				examNameList.add(vo.getE_name());
			}
			exam_cb.setModel(new DefaultComboBoxModel(examNameList.toArray()));
		} else {
			exam_cb.setModel(new DefaultComboBoxModel(new String[] {}));
		}
	}
	
	private void makePiechart(int index) {
		String sb_idx = subjectList.get(subject_cb.getSelectedIndex()).getSb_idx();
		String e_idx = examList.get(exam_cb.getSelectedIndex()).getE_idx();
		// 1. 해당 과목을 수강하는 수강생 정보를 StudentSubjectVO에서 가져온다.
		studentSubjectList = dao.getStudentSubjectList(sb_idx);
		// 2. 해당 과목 해당 시험 제출 정보와 학생 정보를 examJoinVO에서 가져온다.
		examJoinList = dao.getExamJoinList(e_idx);
		System.out.println(studentSubjectList.size());
		// piechart1 : 제출 현황 => 해당 과목의 수강생 - 응시학생 = 미응시 학생
		examSubmit_pc.clearData();
		examSubmit_pc.addData(new ModelPieChart("제출", examJoinList.size(), makeColor()));
		examSubmit_pc.addData(new ModelPieChart("미제출", studentSubjectList.size() - examJoinList.size(), makeColor()));
		// piechart2 : 점수 분포
		int sel1 = 0; // 0 ~ 39
		int sel2 = 0; // 40 ~ 59
		int sel3 = 0; // 60 ~ 79
		int sel4 = 0; // 80 ~ 100
		for(ExamJoinVO vo : examJoinList) {
			if(Integer.parseInt(vo.getEj_score()) >= 80) {
				sel4++;
			} else if(Integer.parseInt(vo.getEj_score()) >= 60) {
				sel3++;
			} else if(Integer.parseInt(vo.getEj_score()) >= 40) {
				sel2++;
			} else if(Integer.parseInt(vo.getEj_score()) >= 0) {
				sel1++;
			}
		}
		point_pc.clearData();
		point_pc.addData(new ModelPieChart("0~39", sel1, makeColor()));
		point_pc.addData(new ModelPieChart("40~59", sel2, makeColor()));
		point_pc.addData(new ModelPieChart("60~79", sel3, makeColor()));
		point_pc.addData(new ModelPieChart("80~100", sel4, makeColor()));
		
		// piechart3 : 합격 현황 제출한 학생 중 60점 이상인 학생의 수
		int passCnt = 0;
		for(ExamJoinVO vo : examJoinList) {
			if(Integer.parseInt(vo.getEj_score()) >= 60) {
				passCnt++;
			}
		}
		pass_pc.clearData();
		pass_pc.addData(new ModelPieChart("합격", passCnt, new Color(0, 204, 0)));
		pass_pc.addData(new ModelPieChart("불합격", examJoinList.size() - passCnt, new Color(160, 160, 160)));
	}

	private Color makeColor() {
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		return new Color(r, g, b);
	}
}
