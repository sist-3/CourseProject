package util;

import javax.swing.JPanel;

import client.Main;

public class BannerManger {

	private Main main;


	private BannerManger() {
	}

	private static class BannerMangerHelper {
		private static final BannerManger INSTANCE = new BannerManger();
	}

	public static BannerManger getInstance() {
		return BannerMangerHelper.INSTANCE;
	}

	public void init(Main main) {
		setMain(main);
	}

	private void setMain(Main main) {
		this.main = main;
	}

	public void changeBanner(JPanel name) {
		main.banner.removeAll();
		main.banner.add(name);
		main.banner.revalidate();
	}
}
