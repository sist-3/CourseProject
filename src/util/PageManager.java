package util;

import java.util.ArrayList;

import javax.swing.JPanel;

import client.Main;
import page.AdminPage;
import page.ExamAllListManagementPage;
import page.ExamManagementPage;
import page.ExamScoreListManagementPage;
import page.ExamSelectListManagementPage;
import page.LoginPage;
import page.MakeExamManagementPage;
import page.ProfessorManagementPage;
import page.ProfessorPage;
import page.ProgressMonitoringManagementPage;
import page.StudentExamListManagementPage;
import page.StudentManagementPage;
import page.StudentMyPage;
import page.StudentPage;
import page.SubjectManagementPage;
import page.panel.QuizMultiplePanel;
import page.panel.QuizSubjectivePanel;

public class PageManager {

	private Main main;

	private static ArrayList<JPanel> pageList = new ArrayList<>();
	private static ArrayList<String> pageNameList = new ArrayList<>();

	private PageManager() {
	}

	private static class PageManagerHelper {
		private static final PageManager INSTANCE = new PageManager();
	}

	public static PageManager getInstance() {
		return PageManagerHelper.INSTANCE;
	}

	public void init(Main main) {
		setMain(main);
		setPage();
		setComponentPage();
	}

	private void setMain(Main main) {
		this.main = main;
	}

	private void setPage() {
		addPage(new ExamAllListManagementPage());
		addPage(new ExamManagementPage());
		addPage(new ExamScoreListManagementPage());
		addPage(new ExamSelectListManagementPage());
		addPage(new LoginPage());
		addPage(new ProfessorManagementPage());
		addPage(new StudentExamListManagementPage());
		addPage(new StudentManagementPage());
		addPage(new StudentMyPage());
		addPage(new SubjectManagementPage());
		addPage(new AdminPage());
		addPage(new ProfessorPage());
		addPage(new StudentPage());
		addPage(new ProgressMonitoringManagementPage());
		addPage(new MakeExamManagementPage("4"));
	}

	private void setComponentPage() {
		addPage(new QuizMultiplePanel());
		addPage(new QuizSubjectivePanel());
	}

	private void addPage(JPanel page) {
		pageList.add(page);
		pageNameList.add(page.getClass().getSimpleName());
	}

	public ArrayList<JPanel> getPage() {
		return pageList;
	}

	// Panel을 한번 생성시킬 때 사용한다.
	public void changePage(String name) {
		if (pageNameList.contains(name)) {
			main.pageCard.show(main.page, name);
		} else {
			System.out.println("등록되지 않은 페이지거나 오타가 있습니다.");
		}
	}
	
	
	// 버튼 클릭 시 반복 생성 되어야 하는 Panel이 있을 시 사용한다.
	public void changePage(JPanel page) {
		String pageName = page.getClass().getSimpleName();
		if(pageNameList.contains(pageName)) {
			int index = pageNameList.indexOf(pageName);
			pageList.set(index, null);
			pageList.remove(index);
			pageNameList.remove(index);
		}
		addPage(page);
		main.page.add(page, page.getClass().getSimpleName());
		changePage(pageName);
	}
	
}
