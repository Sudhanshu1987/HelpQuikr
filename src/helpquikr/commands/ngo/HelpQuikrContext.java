package helpquikr.commands.ngo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HelpQuikrContext {

	public static Map<String, ArrayList<String>> CommandMap;
	// UserId Vs CurrentOngoingCommand
	public Map<Long, String> currentCommandList = new HashMap<Long, String>();

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
