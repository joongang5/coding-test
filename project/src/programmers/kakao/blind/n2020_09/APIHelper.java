package programmers.kakao.blind.n2020_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APIHelper {

	private static final String BASE_URL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users/";
	private static final String CONTENT_TYPE = "application/json";
	private static final String X_AUTH_TOKEN = "f942541d659f673f00f316d89a37abb7";
	
	private static String authKey;
	
	public static int problem;
	public static int time;
	
	public static void startAPI() {
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("X-Auth-Token", X_AUTH_TOKEN);
		properties.put("Content-Type", CONTENT_TYPE);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("problem", "1");
		
		String response = HttpURLConnUtil.doPostRequest(BASE_URL + "start", properties, params);
		
		parseStartAPI(response);
	}
	
	private static void parseStartAPI(String json) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			
			authKey = (String) rootObj.get("auth_key");
			problem = Integer.parseInt((String) rootObj.get("problem"));
			time = Integer.parseInt((String) rootObj.get("time"));
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
		
		String response = HttpURLConnUtil.doPostRequest(BASE_URL + "locations", properties, null);
		
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
				int id = Integer.parseInt((String) obj.get("id"));
				int count = Integer.parseInt((String) obj.get("located_bikes_count"));
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
		
		String response = HttpURLConnUtil.doPostRequest(BASE_URL + "trucks", properties, null);
		
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
				int id = Integer.parseInt((String) obj.get("id"));
				int locationId = Integer.parseInt((String) obj.get("location_id"));
				int loadedBikesCount = Integer.parseInt((String) obj.get("loaded_bikes_count"));
				
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
	public static SimulateResponse SimulateAPI(HashMap<Integer, ArrayList<Integer>> commandMap) {
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("Authorization", authKey);
		properties.put("Content-Type", CONTENT_TYPE);

		JSONObject root = new JSONObject();
		JSONArray commands = new JSONArray();
		for (Map.Entry<Integer, ArrayList<Integer>> entry : commandMap.entrySet()) {
			JSONObject commandItem = new JSONObject();
			commandItem.put("truck_id", entry.getKey());
			
			JSONArray commandArr = new JSONArray();
			for (Integer commandId : entry.getValue()) {
				commandArr.add(commandId);	
			}
			commandItem.put("command", commandArr);

			commands.add(commandItem);
		}
		root.put("commands", commands);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("commands", root.toString());
		
		String response = HttpURLConnUtil.doPostRequest(BASE_URL + "simulate", properties, params);
		
		return parseSimulateAPI(response);
	}
	
	private static SimulateResponse parseSimulateAPI(String json) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			String status = (String) rootObj.get("status");
			int time = Integer.parseInt((String) rootObj.get("time"));
			int failedRequestsCount = Integer.parseInt((String) rootObj.get("failed_requests_count"));
			float distance = Float.parseFloat((String) rootObj.get("distance"));
			
			return new SimulateResponse(status, time, failedRequestsCount, distance);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
}
