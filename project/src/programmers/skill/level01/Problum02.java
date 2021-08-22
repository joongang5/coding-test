package programmers.skill.level01;

public class Problum02 {
	
	public int[] solution(long n) {
		
		String strValue = n + "";
		
		StringBuilder sb = new StringBuilder(strValue);
		
		String reversedValue = sb.reverse().toString();
		
		int[] answer = new int[reversedValue.length()];
		
		for (int i = 0; i < reversedValue.length(); i++) {
			answer[i] = Integer.parseInt(reversedValue.charAt(i) + ""); 
		}
		
		return answer;
	}
}
