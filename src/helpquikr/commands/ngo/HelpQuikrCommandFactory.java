package helpquikr.commands.ngo;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

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
		case "addAppeal" :
		case "done" :
			command = HelpQuikrContext.getInstance().currentCommandList.get(message.getFromUser().getId());
			if (command != null && !command.isEmpty()){
				UserRequest userRequest = HelpQuikrContext.getInstance().currentUserRequest.get(message.getFromUser().getId());
				if(userRequest == null){
					userRequest = new UserRequest();
					userRequest.setChatId(message.getChat().getId());
					userRequest.setUserId(message.getId());
				}
				
				userRequest.setLatitude(message.getLocation().getLatitude());
				userRequest.setLongitude(message.getLocation().getLongitude());
				
				Set<String> keys = HelpQuikrContext.getInstance().props.stringPropertyNames();				
				for(String key : keys){
					String value = HelpQuikrContext.getInstance().props.getProperty(key);
					switch(value) {
						case "setAmountRange":
						case "setDistanceRange":
						case "setCategory":							
					}
				}
				message.getLocation().getLatitude();				
				List<AppealToBeShown> appeals = coreEngine.fetchAppeals(userRequest);
				
				//coreEngine.fetchAppeals(req)
			}else {
				return new HelpQuikrErrorCommand(message, requestHandler);
			}
			HelpQuikrContext.getInstance().currentCommandList.remove(message.getFromUser().getId());
		}
		return new HelpQuikrCommand1(message, requestHandler);
	}
}