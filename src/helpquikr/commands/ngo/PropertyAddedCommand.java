package helpquikr.commands.ngo;

import java.util.logging.Logger;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

public class PropertyAddedCommand extends AbstractCommand {

	private static final Logger logger = Logger.getLogger(PropertyAddedCommand.class.getName());
	private String prop;
	public PropertyAddedCommand(Message message, RequestHandler requestHandler, String prop) {
		super(message, requestHandler);
		this.prop = prop;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		logger.info("Executing Prop added command");
		try {
			TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(
					message.getChat().getId(), prop + " successfully added", true, message.getId(), 
					null);
			
			requestHandler.sendRequest(request);
			logger.info("Executed Prop added command");
		} catch (JsonParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TelegramServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}
