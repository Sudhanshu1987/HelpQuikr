package helpquikr.commands;

import java.util.logging.Logger;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.Command;
import io.github.nixtabyte.telegram.jtelebot.server.CommandFactory;

public class HelpQuikrCommandFactory implements CommandFactory {

	private static final Logger logger = Logger.getLogger(HelpQuikrCommandFactory.class.getName());

	@Override
	public Command createCommand(Message message, RequestHandler requestHandler) {
		// TODO Auto-generated method stub
		System.out.println("Creating command1");
		return new HelpQuikrCommand1(message, requestHandler);
	}

}
