package util;

import java.util.ArrayList;

import javax.swing.JPanel;

import banner.AdminBanner;
import banner.DefaultBanner;
import banner.ProfessorBanner;
import banner.StudentBanner;
import client.Main;

public class BannerManager {

	private Main main;
	
	private static ArrayList<JPanel> bannerList = new ArrayList<>();
	private static ArrayList<String> bannerNameList = new ArrayList<>();

	private BannerManager() {
	}

	private static class BannerMangerHelper {
		private static final BannerManager INSTANCE = new BannerManager();
	}

	public static BannerManager getInstance() {
		return BannerMangerHelper.INSTANCE;
	}

	public void init(Main main) {
		setMain(main);
		setBanner();
	}
		
	private void setMain(Main main) {
		this.main = main;
	}
	
	private void setBanner() {
		addBanner(new DefaultBanner());
		addBanner(new AdminBanner());
		addBanner(new ProfessorBanner());
		addBanner(new StudentBanner());
	}
	
	public void addBanner(JPanel banner) {
		bannerList.add(banner);
		bannerNameList.add(banner.getClass().getSimpleName());
	}
	
	public ArrayList<JPanel> getBanner() {
		return bannerList;
	}

	public void changeBanner(String name) {
		if(bannerNameList.contains(name)) {
			main.bannerCard.show(main.banner, name);
		} else {
			System.out.println("등록되지 않은 배너거나 오타가 있습니다.");
		}
	}
}




