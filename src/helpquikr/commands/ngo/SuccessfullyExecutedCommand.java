package helpquikr.commands.ngo;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class SuccessfullyExecutedCommand extends AbstractCommand {

	public SuccessfullyExecutedCommand(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
	}

	@Override
	public void execute() {
		TelegramRequest request;
		try {
			request = TelegramRequestFactory.createSendMessageRequest(message.getChat().getId(), 
					message.getText() + " executed successfully !", true, message.getId(), null);
			requestHandler.sendRequest(request);
		} catch (JsonParsingException | TelegramServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
