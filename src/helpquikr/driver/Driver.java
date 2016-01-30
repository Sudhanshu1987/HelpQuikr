package helpquikr.driver;

import helpquikr.commands.HelpQuikrCommandFactory;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandDispatcher;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandQueue;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandWatcher;

public class Driver {

	public static void main(String[] args) {
		DefaultCommandDispatcher dispatcher = new DefaultCommandDispatcher(10, 100, new DefaultCommandQueue());
		dispatcher.startUp();
		
		//DefaultCommandWatcher watcher = new DefaultCommandWatcher(2000, 100, "146585990:AAFnYLsYaEZvbiyhIIaG1cV2LscLPng7cVo", dispatcher, new HelpQuikrCommandFactory());
		
		DefaultCommandWatcher watcher = new DefaultCommandWatcher(2000, 100, "159709116:AAHKiCMjH_UHg7JoxhrVLIDjpowQoqxDqfg", dispatcher, new HelpQuikrCommandFactory());
		watcher.startUp();
	}
}
