package helpquikr.core;

import java.time.DayOfWeek;
import java.util.List;

public class UserRequest {

	private long amountThreshold;
	private int distanceThreshold = 5;
	private ReminderFrequency remindEvery;
	private List<AppealCategory> categoriesInterested;
	private Long chatId;
	private Long userId;
	private double latitude;
	private double longitude;
	
	public UserRequest() {		
	}
	
	public UserRequest(long amountThreshold, int distanceThreshold, ReminderFrequency remindEvery, List<AppealCategory> categoriesInterested,
			Long chatId, Long userId, double latitude, double longitude) {
		this.amountThreshold = amountThreshold;
		this.remindEvery = remindEvery;
		this.categoriesInterested = categoriesInterested;
		this.chatId = chatId;
		this.userId = userId;
		this.distanceThreshold = distanceThreshold;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setAmountThreshold(long amountThreshold) {
		this.amountThreshold = amountThreshold;
	}

	public void setDistanceThreshold(int distanceThreshold) {
		this.distanceThreshold = distanceThreshold;
	}

	public void setRemindEvery(ReminderFrequency remindEvery) {
		this.remindEvery = remindEvery;
	}

	public void setCategoriesInterested(List<AppealCategory> categoriesInterested) {
		this.categoriesInterested = categoriesInterested;
	}

	public void setChatId(Long long1) {
		this.chatId = long1;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getAmountThreshold() {
		return amountThreshold;
	}

	public ReminderFrequency getRemindEvery() {
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
