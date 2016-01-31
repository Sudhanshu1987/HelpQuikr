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
	
	public Map<String, String> commandsHelp = new HashMap<String, String>();

	private static HelpQuikrContext INST = new HelpQuikrContext();
	
	private HelpQuikrContext() {
		initializeCommandsHelp();
	}
	
	private void initializeCommandsHelp(){
		String addAppeals = "Please enter the below commands-\n" +
							"amount          <amountvalue>\n" +
							"ngoname         <ngoname>\n" +
							"benificiaryname <benificiaryname>\n" +
							"category        <category1,category2...>\n" +
							"\tCategory Options - EDUCATION, MEDICAL, CLOTHES, ELDERLY \n" +
							"location        <lattitude,longitude>\n\n" +
							"Type /done to store the appeal";
		commandsHelp.put("/addappeal", addAppeals);
		
		String getAppeals = "Please enter the below commands-\n" +
				"setAmountRange      <amountvalue>\n" +
				"setDistanceRange    <distance>\n" +
				"setCategory         <category1,category2...>\n" +	
				"\tCategory Options - EDUCATION, MEDICAL, CLOTHES, ELDERLY \n" +
				"userLocation        <lattitude,longitude> \n\n" +
				"Type /done to fetch the appeals around you";	
		commandsHelp.put("/getappeals", getAppeals);
		
		String registerNgo = "Please enter the below commands-\n" +
				"ngoname     <ngoname>\n\n" +							
				"Type /done to register the NGO";
		commandsHelp.put("/registerngo", registerNgo);
		
		String notifyme = "Please enter the below commands-\n" +
				"setAmountRange      <amountvalue>\n" +
				"setDistanceRange    <distance>\n" +
				"setCategory         <category1,category2...>\n" +	
				"\tCategory Options - EDUCATION, MEDICAL, CLOTHES, ELDERLY \n" +
				"userLocation        <lattitude,longitude> \n" +
				"reminderFrequency   <frequency>\n" +
				"\tfrequency is an integer-> 0-EVERY_MINUTE, 1-EVERY_DAY, 2-EVERY_WEEK, 3-EVERY_FORTNIGHT, 4-EVERY_MONTH\n\n" +
				"Type /done to register the reminder";
		commandsHelp.put("/notifyme", notifyme);
	}
	
	public static HelpQuikrContext getInstance() {
		return INST;
	}
}