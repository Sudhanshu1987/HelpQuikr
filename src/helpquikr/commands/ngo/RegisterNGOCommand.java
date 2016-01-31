package helpquikr.commands.ngo;

import java.util.Set;
import java.util.logging.Logger;

import helpquikr.core.AppealCategory;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class RegisterNGOCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(RegisterNGOCommand.class.getName());
	public RegisterNGOCommand(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		logger.info("Executing RegisterNGO command ..");
		Set<String> keys = HelpQuikrContext.getInstance().props.stringPropertyNames();				
		for(String key : keys){
			String value = HelpQuikrContext.getInstance().props.getProperty(key);
/*			switch(key) {
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
			}*/
		}
		try {
			message.getText();
			TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(message.getChat().getId(), "NGO registered Successfully", true, message.getId(), null);
			requestHandler.sendRequest(request);
			logger.info("Executed RegisterNGO command");
		} catch (JsonParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TelegramServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
