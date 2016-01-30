package helpquikr.core;

import java.util.Random;

public class AppealToBeShown {

	private String appealId;
	private String ngoName;
	private AppealCategory category;
	private String benificiaryName;	
	private double amount;
	private double latitude;
	private double longitude;
	private double distanceFromUser;
	
	public AppealToBeShown(Appeal appeal, double distanceFromUser) {
		appealId = appeal.getAppealId();
		this.ngoName = appeal.getNgoName();
		this.benificiaryName = appeal.getBenificiaryName();
		this.category = appeal.getCategory();
		this.amount = appeal.getAmount();
		this.latitude = appeal.getLatitude();
		this.longitude = appeal.getLongitude();
		this.distanceFromUser = distanceFromUser;
	}

	public String getAppealId() {
		return appealId;
	}

	public String getNgoName() {
		return ngoName;
	}

	public AppealCategory getCategory() {
		return category;
	}

	public String getBenificiaryName() {
		return benificiaryName;
	}

	public double getAmount() {
		return amount;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getDistanceFromUser() {
		return distanceFromUser;
	}

	@Override
	public String toString() {
		return "AppealToBeShown [appealId=" + appealId + ", ngoName=" + ngoName + ", category=" + category
				+ ", benificiaryName=" + benificiaryName + ", amount=" + amount + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", distanceFromUser=" + distanceFromUser + "]";
	}

	
}