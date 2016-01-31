package helpquikr.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import helpquikr.utils.CommonUtils;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.ReplyKeyboardMarkup;

public class CoreEngine {
	
	private Map<String, NGO> ngoMap = new HashMap<String, NGO>();
	private List<Appeal> appealList = new ArrayList<Appeal>();
	private ScheduledExecutorService execService = Executors.newScheduledThreadPool(20);
	
	public static final CoreEngine INST = new CoreEngine();
	
	private CoreEngine() {
	}

	public void registerNGO(String ngoName) {
		ngoMap.put(ngoName, new NGO(ngoName));
	}
	
	public void addAppeal(String benificiaryName, String ngoName, long amount, AppealCategory category, double lat, double lon) {
		appealList.add(new Appeal(benificiaryName, ngoName, category, amount, lat, lon));		
	}
	
	public void addAppeal(Appeal appeal) {
		appealList.add(appeal);		
	}
	
	public List<AppealToBeShown> fetchAppeals(UserRequest req) {
		List<AppealToBeShown> filteredList = new ArrayList<AppealToBeShown>();
		List<String> categoryList = Arrays.asList(req.getCategoriesInterested());
		for (Appeal appeal : appealList) {
			if (categoryList.contains(appeal.getCategory().name()) && appeal.getAmount() <= req.getAmountThreshold()) {
				double distance = CommonUtils.CalculateDistance(appeal.getLatitude(), appeal.getLongitude(), req.getLatitude(), req.getLongitude());
				System.out.println("Shorlisted appeal --> " + appeal + ". Distance : " + distance);
				if (distance <= req.getDistanceThreshold()) {
					filteredList.add(new AppealToBeShown(appeal, distance));
				}
			}
		}
		
		filteredList.sort(new Comparator<AppealToBeShown>() {
			@Override
			public int compare(AppealToBeShown arg0, AppealToBeShown arg1) {
				if (arg0.getDistanceFromUser() == arg1.getDistanceFromUser()) {
					return 0;
				} else if (arg0.getDistanceFromUser() < arg1.getDistanceFromUser()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		
		return filteredList;
	}
	
	public void sendAppealsToUser(Message message, RequestHandler requestHandler, List<AppealToBeShown> appeals) throws JsonParsingException, TelegramServerException {
		ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
		rkm.setResizeKeyboard(true);
		rkm.setOneTimeKeyboard(true);
		rkm.setSelective(true);
		String results[][] = new String[appeals.size()][]; 
		for (int i = 0; i < appeals.size(); i++) {
			results[i][0] = appeals.get(i).toString();
		}
		rkm.setKeyboard(results);

		TelegramRequest request = TelegramRequestFactory.createSendMessageRequest(message.getChat().getId(), 
				"Here are the top appeals near you ...", true, message.getId(), rkm);
		requestHandler.sendRequest(request);
	}
	
	public void raiseAsyncFetchRequest(final UserRequest req) {
		execService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				List<AppealToBeShown> appeals = fetchAppeals(req);
				// Emran - Implement this
//				sendAppealsToUser(req, appeals);
			}
			
		}, 0, 1, TimeUnit.MINUTES);
	}

	public void populateDummyData() {
		for (int i = 1; i <= 10; i++) {
			ngoMap.put("NGO" + i, new NGO("NGO" + i));
		}
		
		appealList.add(new Appeal("P1", "NGO1", AppealCategory.EDUCATION, 9000, 17.409186, 78.390415));
		appealList.add(new Appeal("P2", "NGO2", AppealCategory.EDUCATION, 7000, 17.412109, 78.381556));
		appealList.add(new Appeal("P3", "NGO3", AppealCategory.EDUCATION, 20000, 17.420851, 78.384839));
		appealList.add(new Appeal("P4", "NGO4", AppealCategory.MEDICAL, 5000, 17.423103, 78.411232));
		appealList.add(new Appeal("P5", "NGO5", AppealCategory.MEDICAL, 5600, 17.429839, 78.426445));
		appealList.add(new Appeal("P6", "NGO6", AppealCategory.ELDERLY, 8000, 17.437980, 78.456887));
		appealList.add(new Appeal("P7", "NGO7", AppealCategory.ELDERLY, 100000, 12.979965, 77.581481));
		appealList.add(new Appeal("P8", "NGO8", AppealCategory.CLOTHES, 12000, 12.976907, 77.589456));
	}
	
}
