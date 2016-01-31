package helpquikr.commands.ngo;

import java.util.Set;
import java.util.logging.Logger;

import helpquikr.commands.help.ShowHelpCommand;
import helpquikr.core.UserRequest;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.Command;
import io.github.nixtabyte.telegram.jtelebot.server.CommandFactory;

public class HelpQuikrCommandFactory implements CommandFactory {

	private static final Logger logger = Logger.getLogger(HelpQuikrCommandFactory.class.getName());
	
	@Override
	public Command createCommand(Message message, RequestHandler requestHandler) {
		String command = HelpQuikrContext.getInstance().currentCommandList.get(message.getFromUser().getId());
		
		if (command == null) {
			String msg = message.getText();
			if (msg.startsWith("[")) {
				return new ProcessingCommand(message, requestHandler);
			} else if (msg.equalsIgnoreCase("/getappeals") || msg.equalsIgnoreCase("/addappeal") || 
					msg.equalsIgnoreCase("/registerngo") || msg.equalsIgnoreCase("/notifyme")) { 
				HelpQuikrContext.getInstance().currentCommandList.put(message.getFromUser().getId(), message.getText());
				return new ShowHelpCommand(message, requestHandler);
			} else {
				return new SuccessfullyExecutedCommand(message,requestHandler);
			}
		}
		
		if (message.getText().equalsIgnoreCase("/done")) {
			return handleDone(message, requestHandler);
		}
		
		String[] params = message.getText().split(" ");
		HelpQuikrContext.getInstance().props.setProperty(params[0], params[1]);
		logger.info("Property : " + params[0] + " " + params[1]);
		return new PropertyAddedCommand(message, requestHandler, params[0]);
	}

	private Command handleDone(Message message, RequestHandler requestHandler) {
		String command = HelpQuikrContext.getInstance().currentCommandList.remove(message.getFromUser().getId());

		if (command != null && !command.isEmpty()){				
			switch (command) {
				case "/addappeal" :
					return new AddAppealCommand(message, requestHandler);
				case "/getappeals" : 
					return new SendAppealsCommand(message, requestHandler);
				case "/notifyme": 
					return new PushAsyncAppealsCommand(message, requestHandler);
				case "/registerngo" : 
					return new RegisterNGOCommand(message, requestHandler);
			}
		}
		return new HelpQuikrErrorCommand(message, requestHandler);
	}
}