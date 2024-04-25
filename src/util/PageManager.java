package util;


import javax.swing.JPanel;

import client.Main;

public class PageManager {

	private Main main;


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
	}

	private void setMain(Main main) {
		this.main = main;
	}

	public void changePage(JPanel name) {
		main.page.removeAll();
		main.page.add(name);
		main.page.revalidate();
	}
}
