package helpquikr.commands.ngo;

import java.util.Set;
import java.util.logging.Logger;

import helpquikr.core.ReminderFrequency;
import helpquikr.core.UserRequest;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class RegisterReminderCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(AddAppealCommand.class.getName());
	
	public RegisterReminderCommand(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
	}
	
	@Override
	public void execute() {
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
				case "reminderFrequency":
					switch(value) {
						case "0":
							userRequest.setRemindEvery(ReminderFrequency.EVERY_MINUTE);
							break;
						case "1":
							userRequest.setRemindEvery(ReminderFrequency.EVERY_DAY);
							break;
						case "2":
							userRequest.setRemindEvery(ReminderFrequency.EVERY_WEEK);
							break;
						case "3":
							userRequest.setRemindEvery(ReminderFrequency.EVERY_FORTNIGHT);
							break;
						case "4":
							userRequest.setRemindEvery(ReminderFrequency.EVERY_MONTH);
							break;
						default:
							userRequest.setRemindEvery(ReminderFrequency.EVERY_MINUTE);
					}
			}
			HelpQuikrContext.getInstance().currentUserRequest.put(userRequest.getUserId(),userRequest);
			try {
				TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(
						message.getChat().getId(), "Reminder Added Successfully", true, message.getId(), 
						null);
				requestHandler.sendRequest(request);
				logger.info("Executed AddAppealCommand");
			} catch (JsonParsingException e) {
				e.printStackTrace();
			} catch (TelegramServerException e) {
				e.printStackTrace();
			}
		}
	}

}
