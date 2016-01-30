package helpquikr.core;

import java.util.Random;

public class Appeal {

	private String appealId;
	private String ngoName;
	private AppealCategory category;
	private String benificiaryName;	
	private double amount;
	private double latitude;
	private double longitude;
	
	public Appeal(String benificiaryName, String ngoName, AppealCategory category, double amount, double lat, double lon) {
		appealId = "APL-" + (new Random()).nextLong();
		this.ngoName = ngoName;
		this.benificiaryName = benificiaryName;
		this.category = category;
		this.amount = amount;
		this.latitude = lat;
		this.longitude = lon;
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

	@Override
	public String toString() {
		return "Appeal [appealId=" + appealId + ", ngoName=" + ngoName + ", category=" + category + ", benificiaryName="
				+ benificiaryName + ", amount=" + amount + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	
}
