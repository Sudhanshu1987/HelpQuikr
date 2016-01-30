package helpquikr.core;

import java.util.Random;

public class NGO {
	private String name;
	private long id;
	
	public NGO(String name) {
		id = (new Random()).nextLong();
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}


}
