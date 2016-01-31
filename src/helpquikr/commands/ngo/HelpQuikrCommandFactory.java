package helpquikr.commands.ngo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import helpquikr.core.Appeal;
import helpquikr.core.AppealCategory;
import helpquikr.core.AppealToBeShown;
import helpquikr.core.CoreEngine;
import helpquikr.core.UserRequest;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.Command;
import io.github.nixtabyte.telegram.jtelebot.server.CommandFactory;

public class HelpQuikrCommandFactory implements CommandFactory {

	private static final Logger logger = Logger.getLogger(HelpQuikrCommandFactory.class.getName());

	CoreEngine coreEngine = new CoreEngine();
	//UserRequest userRequest = new UserRequest();
	
	@Override
	public Command createCommand(Message message, RequestHandler requestHandler) {
		// TODO Auto-generated method stub
		System.out.println("Creating command1");
		
		switch (message.getText()) {
		case "getappeals" :
			String command = HelpQuikrContext.getInstance().currentCommandList.get(message.getFromUser().getId());
			if (command != null && !command.isEmpty()){
				
			}else {
				HelpQuikrContext.getInstance().currentCommandList.put(message.getFromUser().getId(),"getAppeals");
				return new HelpQuikrCommand1(message, requestHandler);
			}
			break;
		case "registerngo" :
		case "addappeal" :
			command = HelpQuikrContext.getInstance().currentCommandList.get(message.getFromUser().getId());
			if (command != null && !command.isEmpty()){
				//String benificiaryName, String ngoName, long amount, 
				//AppealCategory category, double lat, double lon
			}else {				
				HelpQuikrContext.getInstance().currentCommandList.put(message.getFromUser().getId(),"addappeal");
				return new HelpQuikrCommand1(message, requestHandler);
			}
		case "done" :
			command = HelpQuikrContext.getInstance().currentCommandList.get(message.getFromUser().getId());
			if (command != null && !command.isEmpty()){				
				switch (command) {
					case "addappeal" :
						Appeal appeal = new Appeal();
						Set<String> keys = HelpQuikrContext.getInstance().props.stringPropertyNames();				
						for(String key : keys){
							String value = HelpQuikrContext.getInstance().props.getProperty(key);
							switch(key) {
								case "ngoname":
									appeal.setNgoName(value);
									break;
								case "benificiaryname":
									appeal.setBenificiaryName(value);
									break;
								case "category":
									appeal.setCategory(AppealCategory.valueOf(value));
									break;
								case "amount":
									appeal.setAmount(Long.parseLong(value));
									break;
								case "location":
									String[] location = value.split(",");
									appeal.setLatitude(Double.parseDouble(location[0]));
									appeal.setLongitude(Double.parseDouble(location[1]));
									break;
							}
						}
						coreEngine.addAppeal(appeal);
					case "getappeals" :
						UserRequest userRequest = HelpQuikrContext.getInstance().currentUserRequest.get(message.getFromUser().getId());
						if(userRequest == null){
							userRequest = new UserRequest();
							userRequest.setChatId(message.getChat().getId());
							userRequest.setUserId(message.getFromUser().getId());
						}
						
						userRequest.setLatitude(message.getLocation().getLatitude());
						userRequest.setLongitude(message.getLocation().getLongitude());
						
						Set<String> keys = HelpQuikrContext.getInstance().props.stringPropertyNames();				
						for(String key : keys){
							String value = HelpQuikrContext.getInstance().props.getProperty(key);
							switch(key) {
								case "setAmountRange":
									userRequest.setAmountThreshold(Long.parseLong(value));
								case "setDistanceRange":
									userRequest.setDistanceThreshold(Integer.parseInt(value));
								case "setCategory":
									userRequest.setCategoriesInterested(value.split(","));
							}
						}
						message.getLocation().getLatitude();
						List<AppealToBeShown> appeals = coreEngine.fetchAppeals(userRequest);
						break;
				}
			}else {
				return new HelpQuikrErrorCommand(message, requestHandler);
			}
			HelpQuikrContext.getInstance().currentCommandList.remove(message.getFromUser().getId());
		default :				
		}	
		return new HelpQuikrCommand1(message, requestHandler);
	}
}