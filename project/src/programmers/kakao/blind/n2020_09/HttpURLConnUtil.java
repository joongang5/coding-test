package programmers.kakao.blind.n2020_09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpURLConnUtil {

	public static String authKey;
	public static int problem;
	public static int time;
	
	private static final String BASE_URL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";
	
	@SuppressWarnings("unchecked")
	public static void startAPI() {
		try {
			URL reqUrl = new URL(BASE_URL);
			HttpURLConnection con = (HttpURLConnection) reqUrl.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("X-Auth-Token", "bd6ef7f6dc954583de0fb3caad267376");
	        con.setRequestProperty("Content-Type", "application/json");

	        con.setDoOutput(true);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
	        String param = "problem=1";
	        //request에 쓰기
	        bw.write(param);
	        bw.flush();
	        bw.close();
	        
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}  else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				
				if (responseCode == 400) {
		            System.out.println("400 Bad Request / Parameter가 잘못됨 (범위, 값 등)");
		        } else if (responseCode == 401) {
		            System.out.println("401 Unauthorized / 인증을 위한 Header가 잘못됨");
		        } else if (responseCode == 500) {
		            System.out.println("500 Internal Server Error / 서버 에러, 채팅으로 문의 요망");
		        }
	        }
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			parseStartAPI(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
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
	
	@SuppressWarnings("unchecked")
	public static void LocationsAPI() {
		try {
			URL reqUrl = new URL(BASE_URL);
			HttpURLConnection con = (HttpURLConnection) reqUrl.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Authorization", authKey);
	        con.setRequestProperty("Content-Type", "application/json");

	        con.setDoOutput(true);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
	        JSONObject problem = new JSONObject();
	        problem.put("problem", "1");
	        //request에 쓰기
	        bw.write(problem.toString());
	        bw.flush();
	        bw.close();
	        
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}  else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				
				if (responseCode == 400) {
		            System.out.println("400 Bad Request / Parameter가 잘못됨 (범위, 값 등)");
		        } else if (responseCode == 401) {
		            System.out.println("401 Unauthorized / 인증을 위한 Header가 잘못됨");
		        } else if (responseCode == 500) {
		            System.out.println("500 Internal Server Error / 서버 에러, 채팅으로 문의 요망");
		        }
	        }
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			parseStartAPI(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static void parseLocationAPI(String json) {
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
}
