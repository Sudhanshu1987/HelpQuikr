package helpquikr.core;

import java.util.Random;

public class User {
	private String userId;
	private String userName;
	
	public User(String userName) {
		this.userName = userName;
		this.userId = userName + (new Random()).nextInt();
	}

}
