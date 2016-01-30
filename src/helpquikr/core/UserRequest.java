package helpquikr.core;

import java.time.DayOfWeek;
import java.util.List;

public class UserRequest {

	private long amountThreshold;
	private int distanceThreshold = 5;
	private DayOfWeek remindEvery;
	private List<AppealCategory> categoriesInterested;
	private String chatId;
	private String userId;
	private double latitude;
	private double longitude;
	
	public UserRequest(long amountThreshold, int distanceThreshold, DayOfWeek remindEvery, List<AppealCategory> categoriesInterested,
			String chatId, String userId, double latitude, double longitude) {
		this.amountThreshold = amountThreshold;
		this.remindEvery = remindEvery;
		this.categoriesInterested = categoriesInterested;
		this.chatId = chatId;
		this.userId = userId;
		this.distanceThreshold = distanceThreshold;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getAmountThreshold() {
		return amountThreshold;
	}

	public DayOfWeek getRemindEvery() {
		return remindEvery;
	}

	public List<AppealCategory> getCategoriesInterested() {
		return categoriesInterested;
	}

	public String getChatId() {
		return chatId;
	}

	public String getUserId() {
		return userId;
	}

	public int getDistanceThreshold() {
		return distanceThreshold;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	
}
