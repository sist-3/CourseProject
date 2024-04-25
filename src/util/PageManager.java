package util;


import java.util.ArrayList;

import javax.swing.JPanel;

import client.Main;
import page.ExamManagementPage;
import page.LoginPage;

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
	}
		
	private void setMain(Main main) {
		this.main = main;
	}
	
	private void setPage() {
		addPage(new ExamManagementPage());
		addPage(new LoginPage());
	}
	
	private void addPage(JPanel page) {
		pageList.add(page);
		pageNameList.add(page.getClass().getSimpleName());
	}
	
	public ArrayList<JPanel> getPage() {
		return pageList;
	}

	public void changePage(String name) {
		if(pageNameList.contains(name)) {			
			main.pageCard.show(main.page, name);
		} else {
			System.out.println("등록되지 않은 페이지거나 오타가 있습니다.");
		}
	}
}
