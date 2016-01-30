package helpquikr.commands.ngo;

import java.util.logging.Logger;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.CustomReplyKeyboard;
import io.github.nixtabyte.telegram.jtelebot.response.json.ForceReply;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.ReplyKeyboardMarkup;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class HelpQuikrCommand1 extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(HelpQuikrCommand1.class.getName());
	public HelpQuikrCommand1(Message message, RequestHandler requestHandler) {
		super(message, requestHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		logger.info("Executing command 1");
		try {
/*			ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
			rkm.setKeyboard(new String[][] {
				{"aadsf", "b", "c"}, {"d", "adsf", "adfadsf"}});
			rkm.setResizeKeyboard(true);
			rkm.setOneTimeKeyboard(true);
			rkm.setSelective(true);
*/			
			TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(
					message.getChat().getId(), "HelpQuikr", true, message.getId(), 
					null);
			
			requestHandler.sendRequest(request);
			logger.info("Executed command 1");
		} catch (JsonParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TelegramServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
