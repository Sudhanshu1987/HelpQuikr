package helpquikr.commands.ngo;

import java.util.Set;
import java.util.logging.Logger;

import helpquikr.core.Appeal;
import helpquikr.core.AppealCategory;
import helpquikr.core.CoreEngine;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class AddAppealCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(AddAppealCommand.class.getName());
	
	public AddAppealCommand(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
	}
	
	@Override
	public void execute() {
		logger.info("Executing AddAppealCommand");
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
					String[] categories = value.split(",");
					for(String category : categories){
						appeal.setCategory(AppealCategory.valueOf(category));
					}
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
		CoreEngine.INST.addAppeal(appeal);
		HelpQuikrContext.getInstance().props.clear();
		try {			
			TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(
					message.getChat().getId(), "Appeals Added Successfully", true, message.getId(), 
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
