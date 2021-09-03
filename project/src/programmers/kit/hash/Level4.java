package programmers.kit.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* 베스트앨범
문제 설명
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다.
노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때,
베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.

입출력 예
genres											plays						return
["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
입출력 예 설명
classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

고유 번호 3: 800회 재생
고유 번호 0: 500회 재생
고유 번호 2: 150회 재생
pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

고유 번호 4: 2,500회 재생
고유 번호 1: 600회 재생
따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.
*/

public class Level4 {

	public void main() {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		
		int[] answer = solution(genres, plays);
		
		for (int item : answer) {
			System.out.println(item);	
		}
	}
	
	public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        int songCount = genres.length;
        
        HashMap<String, Integer> totalMap = new HashMap<String, Integer>();
        HashMap<String, HashMap<Integer, Integer>> infoMap = new HashMap<String, HashMap<Integer,Integer>>();
        
        for (int i = 0; i < songCount; i++) {
        	String genre = genres[i];
        	int play = plays[i];
        	
        	int total = totalMap.getOrDefault(genre, 0);
        	totalMap.put(genre, total + play);
        	
        	if (infoMap.containsKey(genre) == false)
        		infoMap.put(genre, new HashMap<Integer, Integer>());
        	
        	HashMap<Integer, Integer> songMap = infoMap.get(genre);
        	songMap.put(i, play);
        }

        List<String> genreRankList = new ArrayList<String>(totalMap.keySet());
        genreRankList.sort((l, r) -> totalMap.get(r) - totalMap.get(l));

        List<Integer> answerList = new ArrayList<Integer>();
        for (int i = 0; i < genreRankList.size(); i++) {
        	HashMap<Integer, Integer> songMap = infoMap.get(genreRankList.get(i));
        	List<Integer> songRankList = new ArrayList<Integer>(songMap.keySet());
        	songRankList.sort((l, r) -> songMap.get(r) - songMap.get(l));
        	
        	List<Integer> tempList = new ArrayList<Integer>();
        	for (int j = 0; j < songRankList.size(); j++) {
        		if (j >= 2)
        			break;
        		
            	tempList.add(songRankList.get(j));
        	}
        	
        	if (tempList.size() == 2) {
        		int firstNumber = tempList.get(0);
        		int secondNumber = tempList.get(1);
        		if (songMap.get(firstNumber) == songMap.get(secondNumber))
        		{
        			if (firstNumber > secondNumber)
        				Collections.reverse(tempList);
        		}
        	}
        	
        	for (int j = 0; j < tempList.size(); j++) {
        		answerList.add(tempList.get(j));
        	}
        }
        
        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
        	answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}
