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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	private final String[] TABLE_ATTRIBUTE = {"학번" ,"학생 이름", "과목명","시험명", "점수"};  
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
	
	private String currSubjectIndex;
	private String currExamIndex;

	public enum ChartItem {
		SUBMIT("제출"), NOTSUBMIT("미제출"), 
		SEC1("0~39"), SEC2("40~59"), SEC3("60~79"), SEC4("80~100"), 
		PASS("합격"),FAIL("불합격");

		private final String message;

		private ChartItem(String message) {
			this.message = message;
		}

		public String get() {
			return message;
		}
	}

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
		examSubmit_pc = new PieChart(this);
		examSubmit_pc.setLocation(0, 158);
		examSubmit_pc.setSize(new Dimension(258, 278));
		examSubmit_pc.setFont(new Font("프리젠테이션 4 Regular", Font.PLAIN, 19));
		examSubmit_pc.setChartType(PieChart.PeiChartType.DONUT_CHART);
		panel.add(examSubmit_pc);

		point_pc = new PieChart(this);
		point_pc.setSize(new Dimension(262, 242));
		point_pc.setFont(new Font("Dialog", Font.PLAIN, 19));
		point_pc.setChartType(PeiChartType.DONUT_CHART);
		point_pc.setBounds(259, 158, 269, 278);
		panel.add(point_pc);

		pass_pc = new PieChart(this);
		pass_pc.setSize(new Dimension(262, 242));
		pass_pc.setFont(new Font("Dialog", Font.PLAIN, 19));
		pass_pc.setChartType(PeiChartType.DONUT_CHART);
		pass_pc.setBounds(530, 158, 269, 278);
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
				setPiechart();
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
		table.setShowGrid(true);
		table.setGridColor(Color.LIGHT_GRAY);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			TABLE_ATTRIBUTE
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(112);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(99);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(143);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(141);
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
		currSubjectIndex = subjectList.get(subject_cb.getSelectedIndex()).getSb_idx();
		examList = dao.getExamList(currSubjectIndex);
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

	private void setPiechart() {
		currExamIndex = examList.get(exam_cb.getSelectedIndex()).getE_idx();
		// 1. 해당 과목을 수강하는 수강생 정보를 StudentSubjectVO에서 가져온다.
		studentSubjectList = dao.getStudentSubjectList(currSubjectIndex);
		// 2. 해당 과목 해당 시험 제출 정보와 학생 정보를 examJoinVO에서 가져온다.
		examJoinList = dao.getExamJoinList(currExamIndex);
		// piechart1 : 제출 현황 => 해당 과목의 수강생 - 응시학생 = 미응시 학생
		examSubmit_pc.clearData();
		examSubmit_pc.setSelectedIndex(-1);
		examSubmit_pc.addData(new ModelPieChart("제출", examJoinList.size(), makeColor()));
		examSubmit_pc.addData(new ModelPieChart("미제출", studentSubjectList.size() - examJoinList.size(), makeColor()));
		// piechart2 : 점수 분포
		int sec1 = 0; // 0 ~ 39
		int sec2 = 0; // 40 ~ 59
		int sec3 = 0; // 60 ~ 79
		int sec4 = 0; // 80 ~ 100
		for (ExamJoinVO vo : examJoinList) {
			if (Integer.parseInt(vo.getEj_score()) >= 80) {
				sec4++;
			} else if (Integer.parseInt(vo.getEj_score()) >= 60) {
				sec3++;
			} else if (Integer.parseInt(vo.getEj_score()) >= 40) {
				sec2++;
			} else if (Integer.parseInt(vo.getEj_score()) >= 0) {
				sec1++;
			}
		}
		point_pc.clearData();
		point_pc.setSelectedIndex(-1);
		point_pc.addData(new ModelPieChart("0~39", sec1, makeColor()));
		point_pc.addData(new ModelPieChart("40~59", sec2, makeColor()));
		point_pc.addData(new ModelPieChart("60~79", sec3, makeColor()));
		point_pc.addData(new ModelPieChart("80~100", sec4, makeColor()));

		// piechart3 : 합격 현황 제출한 학생 중 60점 이상인 학생의 수
		int passCnt = 0;
		for (ExamJoinVO vo : examJoinList) {
			if (Integer.parseInt(vo.getEj_score()) >= 60) {
				passCnt++;
			}
		}
		pass_pc.clearData();
		pass_pc.setSelectedIndex(-1);
		pass_pc.addData(new ModelPieChart("합격", passCnt, new Color(0, 204, 0)));
		pass_pc.addData(new ModelPieChart("불합격", examJoinList.size() - passCnt, new Color(160, 160, 160)));
	}

	private Color makeColor() {
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		return new Color(r, g, b);
	}
	
	public void makeTableItem(String chartItem) {
		List<ExamJoinVO> list = new ArrayList<>();
		if(chartItem.trim().equals("미제출")) {
			setNotSubmitTable();
			return;
		}
		switch (chartItem) {
		case "제출":
			// examJoinList에 있는 학생 정보
			list = examJoinList;
			break;
		case "0~39":
			// examJoinList에 점수가 해당 범위에 속하는 학생 정보
			list = getListByScoreInRange(0, 39);
			break;
		case "40~59":
			list = getListByScoreInRange(40, 59);
			break;
		case "60~79":
			list = getListByScoreInRange(60, 79);
			break;
		case "80~100":
			list = getListByScoreInRange(80, 100);
			break;
		case "합격":
			// examJoinList에 점수가 60점 이상인 학생 정보
			list = getListByScoreInRange(60, 100);
			break;
		case "불합격":
			// examJoinList에 점수가 60점 미만인 학생 정보
			list = getListByScoreInRange(0, 59);
			break;
		}
		ArrayList<Map<String, String>> mapList = new ArrayList<>();
		for(ExamJoinVO vo : list) {			
			Map<String, String> map = new HashMap<>();
			map.put(TABLE_ATTRIBUTE[0], vo.getStvo().getSt_num());
			map.put(TABLE_ATTRIBUTE[1], vo.getStvo().getSt_name());
			map.put(TABLE_ATTRIBUTE[2], (String)subject_cb.getSelectedItem());
			map.put(TABLE_ATTRIBUTE[3], (String)exam_cb.getSelectedItem());
			map.put(TABLE_ATTRIBUTE[4], vo.getEj_score());
			mapList.add(map);
		}
		setTable(mapList);
	}
	
	private void setNotSubmitTable() {
		// studentSubjectList에는 있지만 examJoinList에 없는 학생 정보
		List<ExamJoinVO> examJoinList_copy = examJoinList;
		List<StudentSubjectVO> studentSubjectList_copy = studentSubjectList;
		for (ExamJoinVO sb_idx : examJoinList_copy) {
			for (int i = 0; i < studentSubjectList_copy.size(); i++) {
				StudentSubjectVO a_idx = studentSubjectList_copy.get(i);
				if (a_idx.getStvo().getSt_idx().contains(sb_idx.getStvo().getSt_idx())) {
					studentSubjectList_copy.remove(i);
					break;
				}
			}
		}
		String[] c_name = { "학번", "학생 이름", "과목명", "시험명" };
		String[][] data = new String[studentSubjectList_copy.size()][c_name.length];
		for (int i = 0; i < studentSubjectList_copy.size(); i++) {
			data[i][0] = studentSubjectList_copy.get(i).getStvo().getSt_num();
			data[i][1] = studentSubjectList_copy.get(i).getStvo().getSt_name();
			data[i][2] = (String) subject_cb.getSelectedItem();
			data[i][3] = (String) exam_cb.getSelectedItem();
		}
		table.setModel(new DefaultTableModel(data, c_name));
	}
	
	private void setTable(ArrayList<Map<String, String>> list) {
		String[][] data = new String[list.size()][TABLE_ATTRIBUTE.length];
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<TABLE_ATTRIBUTE.length; j++) {
				data[i][j] = list.get(i).get(TABLE_ATTRIBUTE[j]);
			}
		}
		table.setModel(new DefaultTableModel(
				data,
				TABLE_ATTRIBUTE));
	}
	
	private List<ExamJoinVO> getListByScoreInRange(int minRange, int maxRange) {
		List<ExamJoinVO> list = new ArrayList<>();
		for(ExamJoinVO vo : examJoinList) {
			int score = Integer.parseInt(vo.getEj_score());
			if(score >= minRange && score <= maxRange) {
				list.add(vo);
			}
		}
		return list;
	}
	
	public void resetSelectedIndex(PieChart pie) {
		if(pie == examSubmit_pc) {
			point_pc.setSelectedIndex(-1);
			pass_pc.setSelectedIndex(-1);
		} else if(pie == point_pc) {
			examSubmit_pc.setSelectedIndex(-1);
			pass_pc.setSelectedIndex(-1);
		} else if(pie == pass_pc) {
			examSubmit_pc.setSelectedIndex(-1);
			point_pc.setSelectedIndex(-1);
		} 
	}
}
