package helpquikr.commands.ngo;

import java.util.logging.Logger;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class AddLocationCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(AddLocationCommand.class.getName());
	public AddLocationCommand(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
	}

	@Override
	public void execute() {
		logger.info("Executing AddLocationCommand");
		try {		
			TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(
					message.getChat().getId(), "Please Attach the Location", true, message.getId(), 
					null);			
			requestHandler.sendRequest(request);
			logger.info("Executed AddLocationCommand");
		} catch (JsonParsingException e) {
			e.printStackTrace();
		} catch (TelegramServerException e) {
			e.printStackTrace();
		}
	}
}