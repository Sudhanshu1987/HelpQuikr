package helpquikr.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class CoreEngine {
	
	private Map<String, NGO> ngoMap = new HashMap<String, NGO>();
	private List<Appeal> appealList = new ArrayList<Appeal>();
	
	public CoreEngine() {
		// TODO Auto-generated constructor stub
	}

	public void registerNGO(String ngoName) {
		ngoMap.put(ngoName, new NGO(ngoName));
	}
	
	public void addAppeal(String benificiaryName, String ngoName, long amount, AppealCategory category, double lat, double lon) {
		appealList.add(new Appeal(benificiaryName, ngoName, category, amount, lat, lon));
		
	}
	
	public List<Appeal> fetchAppeals(UserRequest req) {
		List<Appeal> filteredList = new ArrayList<Appeal>();
		for (Appeal appeal : appealList) {
			if (req.getCategoriesInterested().contains(appeal.getCategory()) && appeal.getAmount() <= req.getAmountThreshold() && withinDistanceThreshold(appeal, req)) {
				filteredList.add(appeal);
			}
		}
		
		return filteredList;
	}
	
	private boolean withinDistanceThreshold(Appeal appeal, UserRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	public void raiseAsyncFetchRequest(UserRequest req) {
		
	}
	
}
