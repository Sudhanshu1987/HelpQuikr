package helpquikr.driver;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import helpquikr.commands.ngo.HelpQuikrCommandFactory;
import helpquikr.core.AppealCategory;
import helpquikr.core.AppealToBeShown;
import helpquikr.core.CoreEngine;
import helpquikr.core.ReminderFrequency;
import helpquikr.core.UserRequest;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandDispatcher;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandQueue;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandWatcher;

public class Driver {

	public static void main(String[] args) {
		CoreEngine engine = new CoreEngine();
		engine.populateDummyData();		
		List<AppealCategory> categories = new ArrayList<AppealCategory>();
		categories.add(AppealCategory.EDUCATION);
		categories.add(AppealCategory.ELDERLY);
		List<AppealToBeShown> results = engine.fetchAppeals(new UserRequest(10000, 10, ReminderFrequency.EVERY_DAY, categories, "test", "test", 17.412109, 78.381556));
		System.out.println(results);
		
		DefaultCommandDispatcher dispatcher = new DefaultCommandDispatcher(10, 100, new DefaultCommandQueue());
		dispatcher.startUp();	
		
		//DefaultCommandWatcher watcher = new DefaultCommandWatcher(2000, 100, "146585990:AAFnYLsYaEZvbiyhIIaG1cV2LscLPng7cVo", dispatcher, new HelpQuikrCommandFactory());
		System.out.println("Initializing");
		DefaultCommandWatcher watcher = new DefaultCommandWatcher(2000, 100, "159709116:AAHKiCMjH_UHg7JoxhrVLIDjpowQoqxDqfg", dispatcher, new HelpQuikrCommandFactory());
		watcher.startUp(); 
	}
	
	
}
