package helpquikr.commands.help;

import java.util.logging.Logger;

import helpquikr.commands.ngo.HelpQuikrContext;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class ShowHelpCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(ShowHelpCommand.class.getName());
	
	public ShowHelpCommand(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
	}
	
	@Override
	public void execute() {
		logger.info("Executing ShowHelpCommand");
		try {
			String replyMessage = HelpQuikrContext.getInstance().commandsHelp.get(message.getText().toLowerCase());
			TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(
					message.getChat().getId(), replyMessage, true, message.getId(), 
					null);			
			requestHandler.sendRequest(request);
			logger.info("Executed ShowHelpCommand");
		} catch (JsonParsingException e) {
			e.printStackTrace();
		} catch (TelegramServerException e) {
			e.printStackTrace();
		}

	}
}
