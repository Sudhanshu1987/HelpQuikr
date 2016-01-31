package helpquikr.driver;

import java.util.ArrayList;
import java.util.List;

import helpquikr.commands.ngo.HelpQuikrCommandFactory;
import helpquikr.core.AppealCategory;
import helpquikr.core.CoreEngine;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandDispatcher;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandQueue;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandWatcher;

public class Driver {

	public static void main(String[] args) {
		CoreEngine engine = CoreEngine.INST;
		engine.populateDummyData();		
		List<AppealCategory> categories = new ArrayList<AppealCategory>();
		categories.add(AppealCategory.EDUCATION);
		categories.add(AppealCategory.ELDERLY);
		
		DefaultCommandDispatcher dispatcher = new DefaultCommandDispatcher(10, 100, new DefaultCommandQueue());
		dispatcher.startUp();	
		
		//DefaultCommandWatcher watcher = new DefaultCommandWatcher(2000, 100, "146585990:AAFnYLsYaEZvbiyhIIaG1cV2LscLPng7cVo", dispatcher, new HelpQuikrCommandFactory());
		System.out.println("Initializing");
		DefaultCommandWatcher watcher = new DefaultCommandWatcher(2000, 100, "142924483:AAHC1eP6Axf7Y7geULsTiyPC5767wZUJZPI", dispatcher, new HelpQuikrCommandFactory());
		watcher.startUp(); 
	}
	
	
}
