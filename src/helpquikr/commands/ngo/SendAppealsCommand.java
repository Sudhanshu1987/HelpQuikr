package helpquikr.commands.ngo;

import java.util.List;
import java.util.Set;

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

	public SendAppealsCommand(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
	}

	@Override
	public void execute() {
		CoreEngine coreEngine = CoreEngine.INST;
		UserRequest userRequest = new UserRequest();
		userRequest.setChatId(message.getChat().getId());
		userRequest.setUserId(message.getFromUser().getId());
		userRequest.setMessageId(message.getId());
		
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

		List<AppealToBeShown> appeals = coreEngine.fetchAppeals(userRequest);
		try {
			sendAppealsToUser(message, requestHandler, appeals);
		} catch (JsonParsingException | TelegramServerException e) {
			e.printStackTrace();
		}
	}

	private void sendAppealsToUser(Message message, RequestHandler requestHandler, List<AppealToBeShown> appeals) throws JsonParsingException, TelegramServerException {
		ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
		rkm.setResizeKeyboard(false);
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
