package helpquikr.commands.ngo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import helpquikr.core.UserRequest;

public class HelpQuikrContext {

	public static Map<String, ArrayList<String>> CommandMap;
	// UserId Vs CurrentOngoingCommand
	public Map<Long, String> currentCommandList = new HashMap<Long, String>();

	public Map<Long, UserRequest> currentUserRequest = new HashMap<Long, UserRequest>();
	
	public Properties props = new Properties();
	
	private static HelpQuikrContext INST = new HelpQuikrContext();
	private HelpQuikrContext() {
		CommandMap = new HashMap<String, ArrayList<String>>();
		ArrayList questions = new ArrayList<String>();
		questions.add("setAmountRange");
		questions.add("setDistanceRange");
		questions.add("setCategory");
    	CommandMap.put("getAppeals", questions);
    	
	}
	
	public static HelpQuikrContext getInstance() {
		return INST;
	}
}
