package com.toss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* 스트링 s가 주어집니다. 스트링 s는 알파벳 소문자('a'~'z')와 대문자('A'~'Z')로만 이루어져 있습니다.
 * 우리는 스트링 s에서 가장 많이 쓰인 알파벳을 찾아 해당 알파벳을 return 하는 solution 함수를 구현하려고 합니다.
 * 이때, 소문자와 대문자는 같다고 판단합니다. 또한, 가장 많이 쓰인 알파벳을 반환할 때는 소문자로 반환하고, 
 * 가장 많이 쓰인 알파벳이 2개 이상이면 알파벳 순서대로 스트링을 이루어 반환합니다.
 * 
 * 단, 알파벳 "T","O","S","t","o","s"의 경우는 대문자로 반환하며, 알파벳 순서에 예외적으로, "T","O","S"순으로 가장 빠른 것으로 처리합니다.
 * 반환시 "S"는 연속된 "SS"로 반환합니다.
 * ============================================================================================================== 
 * 예를 들어,
 * 스트링 s가 "aAb"일때: a가 1개, A가 1개, b가 1개로 이루어져 있고, 소문자와 대문자를 같다고 판단하기 때문에 a가 2개, 
 * b가 1개로 이루어져 있다고 판단할 수 있습니다. 또한, 알파벳을 반환할 때는 소문자로 반환하기 때문에 "a"를 return 합니다.
 * 
 * 스트링 s가 "BA"일때: B가 1개, A가 1개로 이루어져 있습니다. 또한, 알파벳을 반환할 때는 소문자로 반환하고, 
 * 가장 많이 쓰인 알파벳이 2개 이상이면 알파벳 순서대로 스트링을 이루어 반환하기 때문에 "ab"를 return 합니다.
 * 
 * 스트링 s가 "BbA"일때: B가 1개, b가 1개, A가 1개로 이루어져 있고, 소문자와 대문자를 같다고 판단하기 때문에
 * a가 1개, b가 2개로 이루어져 있다고 판단할 수 있습니다. 위 규칙에 따라서, "b"를 return 하면 됩니다.
 * 
 * 스트링 s가 "aaBBTtooSS" 일때: a가 2개, B가 2개, T가 1개, t가 1개, o가 2개, S가 2개로 이루어져 있고,
 * 소문자와 대문자를 같다고 판단하기 때문에 a가 2개, b가 2개, t가 2개, o가 2개, s가 2개로 이루어져 있다고 판단할 수 있으며,
 * 위 규칙에 따라서 t,o,s는 대문자로 반환하고 "T", "O", "S" 순으로 가장 빠른 것으로 처리하며, "S"는 연속된 "SS"로 반환하면
 * "TOSSab"를 return 하면 됩니다.
 * 
 * 문자열이 매개변수 s으로 주어졌을 때, 가장 많이 쓰인 알파벳을 찾아 해당 알파벳을 return 하는 solution 함수를 구현해 보세요.
 * 알파벳을 반환할 때는 string형으로 반환해 주세요.
 * 
 * 제한사항
 * 스트링 s는 알파벳 소문자('a'~'z')와 대문자('A'~'Z')로만 이루어져 있습니다.
 * 스트링 s의 길이: 1,000,000 이하의 자연수
 * 
 * 입출력 예시
 * [s]				[answer]
 * "aAb"			"a"
 * "BA"				"ab"
 * "BbA"			"b"
 * "aaBBTtooSS"		"TOSSab"
*/

public class Problem01 {

	public String solution(String s) {
		
		HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
		HashSet<Character> resultSet = new HashSet<Character>();
		
		for (int i = 0; i < s.length(); i++) {
			char key = Character.toLowerCase(s.charAt(i));
			int value = countMap.getOrDefault(key, 0);
			countMap.put(key, ++value);
		}
		
		int highestValue = -1;
		for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
			int value = entry.getValue();
			if (value > highestValue)
				highestValue = value;
		}
		
		for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
			int value = entry.getValue();
			if (value == highestValue) {
				resultSet.add(entry.getKey());
			}
		}

		ArrayList<Character> reservedKeys = new ArrayList<Character>();
		reservedKeys.add('t');
		reservedKeys.add('o');
		reservedKeys.add('s');
		StringBuilder sb = new StringBuilder();
		
		for (char key : reservedKeys) {
			if (resultSet.contains(key)) {
				char upperKey = Character.toUpperCase(key);
				if (upperKey == 'S') {
					for (int i = 0; i < highestValue; i++) {
						sb.append(upperKey);
					}	
				} else {
					sb.append(upperKey);
				}
			}
		}

		for (int i = 97; i <= 122; i++) {
			if (reservedKeys.contains((char)i))
				continue;
			
			if (resultSet.contains((char)i))
				sb.append((char)i);
		}
		
		return sb.toString();
	}
}
