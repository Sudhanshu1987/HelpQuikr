package helpquikr.commands.ngo;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.ReplyKeyboardMarkup;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class ProcessingCommand extends AbstractCommand {
	
	public ProcessingCommand(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
	}

	@Override
	public void execute() {
		ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
		rkm.setResizeKeyboard(true);
		rkm.setOneTimeKeyboard(true);
		rkm.setSelective(false);
		String results[][] = new String[1][2];
		results[0][0] = "Donate";
		results[0][1] = "GetDirections";
		rkm.setKeyboard(results);

		TelegramRequest request;
		try {
			request = TelegramRequestFactory.createSendMessageRequest(message.getChat().getId(), 
					"Thanks! What do you want to do next ?", true, message.getId(), rkm);
			requestHandler.sendRequest(request);
		} catch (JsonParsingException | TelegramServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
