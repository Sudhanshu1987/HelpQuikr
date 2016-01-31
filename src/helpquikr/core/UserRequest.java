package helpquikr.core;

public class UserRequest {

	private long amountThreshold;
	private int distanceThreshold = 5;
	private ReminderFrequency remindEvery;
	private String[] categoriesInterested;
	private Long chatId;
	private Long messageId;
	private Long userId;
	private double latitude;
	private double longitude;
	
	public UserRequest() {		
	}
	
	public UserRequest(long amountThreshold, int distanceThreshold, ReminderFrequency remindEvery, String[] categoriesInterested,
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

	public void setCategoriesInterested(String[] categoriesInterested) {
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

	public String[] getCategoriesInterested() {
		return categoriesInterested;
	}

	public Long getChatId() {
		return chatId;
	}

	public Long getUserId() {
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

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	
}
