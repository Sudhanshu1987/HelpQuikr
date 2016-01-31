package helpquikr.commands.ngo;

import java.util.List;

import helpquikr.core.AppealToBeShown;
import helpquikr.core.CoreEngine;
import helpquikr.core.UserRequest;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.ReplyKeyboardMarkup;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class SendAppealsCommand extends AbstractCommand {

	private UserRequest userRequest;
	public SendAppealsCommand(Message message, RequestHandler requestHandler, UserRequest userRequest) {
		super(message, requestHandler);
		this.userRequest = userRequest;
	}

	@Override
	public void execute() {
		CoreEngine coreEngine = CoreEngine.INST;
		List<AppealToBeShown> appeals = coreEngine.fetchAppeals(userRequest);
		try {
			sendAppealsToUser(message, requestHandler, appeals);
		} catch (JsonParsingException | TelegramServerException e) {
			e.printStackTrace();
		}
	}

	private void sendAppealsToUser(Message message, RequestHandler requestHandler, List<AppealToBeShown> appeals) throws JsonParsingException, TelegramServerException {
		ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
		rkm.setResizeKeyboard(true);
		rkm.setOneTimeKeyboard(true);
		rkm.setSelective(false);
		String results[][] = new String[appeals.size()][1]; 
		for (int i = 0; i < appeals.size(); i++) {
			results[i][0] = appeals.get(i).toString();
		}
		rkm.setKeyboard(results);

		TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(message.getChat().getId(), 
				"Here are the appeals which you may be interested in! Please choose to donate ..", true, message.getId(), rkm);
		requestHandler.sendRequest(request);
		HelpQuikrContext.getInstance().props.clear();
	}
	

}
