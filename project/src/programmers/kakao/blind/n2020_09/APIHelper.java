package programmers.kakao.blind.n2020_09;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APIHelper {

	private static final String BASE_URL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users/";
	private static final String CONTENT_TYPE = "application/json";
	private static final String X_AUTH_TOKEN = "a9617a7aee9f8baa46d4284b471b8fb9";
	
	private static String authKey;
	
	public static int problem;
	public static int time;
	
	public static void startAPI(int problem) {
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("X-Auth-Token", X_AUTH_TOKEN);
		properties.put("Content-Type", CONTENT_TYPE);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("problem", Integer.toString(problem));
		
		String response = HttpURLConnUtil.doPostRequest(BASE_URL + "start", properties, params);
		
		parseStartAPI(response);
	}
	
	private static void parseStartAPI(String json) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			
			authKey = (String) rootObj.get("auth_key");
			problem = ((Long) rootObj.get("problem")).intValue();
			time = ((Long) rootObj.get("time")).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<Integer, Integer> LocationsAPI() {
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("Authorization", authKey);
		properties.put("Content-Type", CONTENT_TYPE);
		
		String response = HttpURLConnUtil.doGetRequest(BASE_URL + "locations", properties, null);
		
		return parseLocationAPI(response);
	}
	
	@SuppressWarnings("unchecked")
	private static HashMap<Integer, Integer> parseLocationAPI(String json) {
		HashMap<Integer, Integer> locationMap = new HashMap<Integer, Integer>();
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			JSONArray locations = (JSONArray) rootObj.get("locations");
			Iterator<JSONObject> iter = locations.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();
				int id = ((Long) obj.get("id")).intValue();
				int count = ((Long) obj.get("located_bikes_count")).intValue();
				locationMap.put(id, count);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return locationMap;
	}
	
	public static HashMap<Integer, Truck> TrucksAPI() {
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("Authorization", authKey);
		properties.put("Content-Type", CONTENT_TYPE);
		
		String response = HttpURLConnUtil.doGetRequest(BASE_URL + "trucks", properties, null);
		
		return parseTrucksAPI(response);
	}
	
	@SuppressWarnings("unchecked")
	private static HashMap<Integer, Truck> parseTrucksAPI(String json) {
		HashMap<Integer, Truck> truckMap = new HashMap<Integer, Truck>();
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			JSONArray trucks = (JSONArray) rootObj.get("trucks");
			Iterator<JSONObject> iter = trucks.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();
				int id = ((Long) obj.get("id")).intValue();
				int locationId = ((Long) obj.get("location_id")).intValue();
				int loadedBikesCount = ((Long) obj.get("loaded_bikes_count")).intValue();
				
				Truck truck = new Truck(id, locationId, loadedBikesCount);
				truckMap.put(id, truck);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return truckMap;
	}
	
	@SuppressWarnings("unchecked")
	public static SimulateResponse SimulateAPI(HashMap<Integer, Queue<TruckWorkType>> commandMap) {
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("Authorization", authKey);
		properties.put("Content-Type", CONTENT_TYPE);

		JSONObject root = new JSONObject();
		JSONArray commands = new JSONArray();
		for (Map.Entry<Integer, Queue<TruckWorkType>> entry : commandMap.entrySet()) {
			JSONObject commandItem = new JSONObject();
			commandItem.put("truck_id", entry.getKey());
			
			JSONArray commandArr = new JSONArray();
			Queue<TruckWorkType> commandQueue = entry.getValue();
			int pollCount = 0;
			while (commandQueue.isEmpty() == false) {
				commandArr.add(commandQueue.poll().getValue());
				
				if (++pollCount >= 10)
					break;
			}
			commandItem.put("command", commandArr);

			commands.add(commandItem);
		}
		root.put("commands", commands);
		
		System.out.println(root.toString());
		
		String response = HttpURLConnUtil.doPutRequest(BASE_URL + "simulate", properties, root.toString());
		
		return parseSimulateAPI(response);
	}
	
	private static SimulateResponse parseSimulateAPI(String json) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			String status = (String) rootObj.get("status");
			int time = ((Long) rootObj.get("time")).intValue();
			String failedRequestsCount = (String) rootObj.get("failed_requests_count");
			String distance = (String) rootObj.get("distance");
			
			return new SimulateResponse(status, time, failedRequestsCount, distance);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void ScoreAPI() {
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("Authorization", authKey);
		properties.put("Content-Type", CONTENT_TYPE);
		
		String response = HttpURLConnUtil.doGetRequest(BASE_URL + "score", properties, null);
		
		parseScoreAPI(response);
	}
	
	private static void parseScoreAPI(String json) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			double score = (Double) rootObj.get("score");
			System.out.println("score : " + score);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
