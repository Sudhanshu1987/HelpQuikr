package helpquikr.commands.ngo;

import java.util.Set;
import java.util.logging.Logger;

import helpquikr.commands.help.ShowHelpCommand;
import helpquikr.core.UserRequest;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.Command;
import io.github.nixtabyte.telegram.jtelebot.server.CommandFactory;

public class HelpQuikrCommandFactory implements CommandFactory {

	private static final Logger logger = Logger.getLogger(HelpQuikrCommandFactory.class.getName());
	
	@Override
	public Command createCommand(Message message, RequestHandler requestHandler) {
		String command = HelpQuikrContext.getInstance().currentCommandList.get(message.getFromUser().getId());
		
		if (command == null) {
			if (message.getText().startsWith("[")) {
				return new ProcessingCommand(message, requestHandler);
			}
			HelpQuikrContext.getInstance().currentCommandList.put(message.getFromUser().getId(), message.getText());
			return new ShowHelpCommand(message, requestHandler);
		}
		
		if (message.getText().equalsIgnoreCase("/done")) {
			return handleDone(message, requestHandler);
		}
		
		String[] params = message.getText().split(" ");
		HelpQuikrContext.getInstance().props.setProperty(params[0], params[1]);
		logger.info("Property : " + params[0] + " " + params[1]);
		return new PropertyAddedCommand(message, requestHandler, params[0]);
	}

	private Command handleDone(Message message, RequestHandler requestHandler) {
		String command = HelpQuikrContext.getInstance().currentCommandList.remove(message.getFromUser().getId());

		if (command != null && !command.isEmpty()){				
			switch (command) {
				case "/addappeal" : {
					return new AddAppealCommand(message, requestHandler);
				}
				case "/getappeals" : {
					UserRequest userRequest = HelpQuikrContext.getInstance().currentUserRequest.get(message.getFromUser().getId());
					if(userRequest == null){
						userRequest = new UserRequest();
						userRequest.setChatId(message.getChat().getId());
						userRequest.setUserId(message.getFromUser().getId());
					}				
					
					Set<String> keys = HelpQuikrContext.getInstance().props.stringPropertyNames();				
					for(String key : keys){
						String value = HelpQuikrContext.getInstance().props.getProperty(key);
						switch(key) {
							case "setAmountRange":
								userRequest.setAmountThreshold(Long.parseLong(value));
								break;
							case "setDistanceRange":
								userRequest.setDistanceThreshold(Integer.parseInt(value));
								break;
							case "setCategory":
								userRequest.setCategoriesInterested(value.split(","));
								break;
							case "userLocation":
								String[] params = value.split(",");
								userRequest.setLatitude(Double.parseDouble(params[0]));
								userRequest.setLongitude(Double.parseDouble(params[1]));
								break;
						}
					}
					return new SendAppealsCommand(message, requestHandler, userRequest);
				}
				case "/registerngo" : {
					return new RegisterNGOCommand(message, requestHandler);
				}
				case "/registerreminder" : {
					return new RegisterReminderCommand(message, requestHandler);
				}
			}
		}
		return new HelpQuikrErrorCommand(message, requestHandler);
	}
}