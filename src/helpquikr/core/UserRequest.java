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

	public UserRequest(long amountThreshold, int distanceThreshold, DayOfWeek remindEvery, List<AppealCategory> categoriesInterested,
			String chatId, String userId) {
		this.amountThreshold = amountThreshold;
		this.remindEvery = remindEvery;
		this.categoriesInterested = categoriesInterested;
		this.chatId = chatId;
		this.userId = userId;
		this.distanceThreshold = distanceThreshold;
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
}
