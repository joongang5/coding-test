package programmers.kakao.blind.n2022_09;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* 문제 설명
[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

N x M 크기의 행렬 모양의 게임 맵이 있습니다. 이 맵에는 내구도를 가진 건물이 각 칸마다 하나씩 있습니다. 적은 이 건물들을 공격하여 파괴하려고 합니다. 건물은 적의 공격을 받으면 내구도가 감소하고 내구도가 0이하가 되면 파괴됩니다. 반대로, 아군은 회복 스킬을 사용하여 건물들의 내구도를 높이려고 합니다.

적의 공격과 아군의 회복 스킬은 항상 직사각형 모양입니다.
예를 들어, 아래 사진은 크기가 4 x 5인 맵에 내구도가 5인 건물들이 있는 상태입니다.

* 이미지 없음

첫 번째로 적이 맵의 (0,0)부터 (3,4)까지 공격하여 4만큼 건물의 내구도를 낮추면 아래와 같은 상태가 됩니다.

* 이미지 없음

두 번째로 적이 맵의 (2,0)부터 (2,3)까지 공격하여 2만큼 건물의 내구도를 낮추면 아래와 같이 4개의 건물이 파괴되는 상태가 됩니다.

* 이미지 없음

세 번째로 아군이 맵의 (1,0)부터 (3,1)까지 회복하여 2만큼 건물의 내구도를 높이면 아래와 같이 2개의 건물이 파괴되었다가 복구되고 2개의 건물만 파괴되어있는 상태가 됩니다.

* 이미지 없음

마지막으로 적이 맵의 (0,1)부터 (3,3)까지 공격하여 1만큼 건물의 내구도를 낮추면 아래와 같이 8개의 건물이 더 파괴되어 총 10개의 건물이 파괴된 상태가 됩니다. (내구도가 0 이하가 된 이미 파괴된 건물도, 공격을 받으면 계속해서 내구도가 하락하는 것에 유의해주세요.)

* 이미지 없음

최종적으로 총 10개의 건물이 파괴되지 않았습니다.

건물의 내구도를 나타내는 2차원 정수 배열 board와 적의 공격 혹은 아군의 회복 스킬을 나타내는 2차원 정수 배열 skill이 매개변수로 주어집니다. 적의 공격 혹은 아군의 회복 스킬이 모두 끝난 뒤 파괴되지 않은 건물의 개수를 return하는 solution함수를 완성해 주세요.

제한사항
1 ≤ board의 행의 길이 (= N) ≤ 1,000
1 ≤ board의 열의 길이 (= M) ≤ 1,000
1 ≤ board의 원소 (각 건물의 내구도) ≤ 1,000
1 ≤ skill의 행의 길이 ≤ 250,000
skill의 열의 길이 = 6
skill의 각 행은 [type, r1, c1, r2, c2, degree]형태를 가지고 있습니다.
type은 1 혹은 2입니다.
type이 1일 경우는 적의 공격을 의미합니다. 건물의 내구도를 낮춥니다.
type이 2일 경우는 아군의 회복 스킬을 의미합니다. 건물의 내구도를 높입니다.
(r1, c1)부터 (r2, c2)까지 직사각형 모양의 범위 안에 있는 건물의 내구도를 degree 만큼 낮추거나 높인다는 뜻입니다.
0 ≤ r1 ≤ r2 < board의 행의 길이
0 ≤ c1 ≤ c2 < board의 열의 길이
1 ≤ degree ≤ 500
type이 1이면 degree만큼 건물의 내구도를 낮춥니다.
type이 2이면 degree만큼 건물의 내구도를 높입니다.
건물은 파괴되었다가 회복 스킬을 받아 내구도가 1이상이 되면 파괴되지 않은 상태가 됩니다. 즉, 최종적으로 건물의 내구도가 1이상이면 파괴되지 않은 건물입니다.
정확성 테스트 케이스 제한 사항
1 ≤ board의 행의 길이 (= N) ≤ 100
1 ≤ board의 열의 길이 (= M) ≤ 100
1 ≤ board의 원소 (각 건물의 내구도) ≤ 100
1 ≤ skill의 행의 길이 ≤ 100
1 ≤ degree ≤ 100
효율성 테스트 케이스 제한 사항
주어진 조건 외 추가 제한사항 없습니다.
입출력 예
board	skill	result
[[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5]]	[[1,0,0,3,4,4],[1,2,0,2,3,2],[2,1,0,3,1,2],[1,0,1,3,3,1]]	10
[[1,2,3],[4,5,6],[7,8,9]]	[[1,1,1,2,2,4],[1,0,0,1,1,2],[2,2,0,2,0,100]]	6
입출력 예 설명
입출력 예 #1

문제 예시와 같습니다.

입출력 예 #2

<초기 맵 상태>

* 이미지 없음

첫 번째로 적이 맵의 (1,1)부터 (2,2)까지 공격하여 4만큼 건물의 내구도를 낮추면 아래와 같은 상태가 됩니다.

* 이미지 없음

두 번째로 적이 맵의 (0,0)부터 (1,1)까지 공격하여 2만큼 건물의 내구도를 낮추면 아래와 같은 상태가 됩니다.

* 이미지 없음

마지막으로 아군이 맵의 (2,0)부터 (2,0)까지 회복하여 100만큼 건물의 내구도를 높이면 아래와 같은 상황이 됩니다.

* 이미지 없음

총, 6개의 건물이 파괴되지 않았습니다. 따라서 6을 return 해야 합니다.
 * 
 */

public class Problum6 {

	public void main() {
		int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
		int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
		
		int answer = solution(board, skill);
		System.out.println(answer);
	}
	
	public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        HashMap<Integer, HashMap<Integer, Integer>> boardMap = new HashMap<Integer, HashMap<Integer,Integer>>();
        for (int i = 0; i < board.length; i++) {
    		HashMap<Integer, Integer> columnMap;
    		if (boardMap.containsKey(i) == false) {
    			columnMap = new HashMap<Integer, Integer>();
    			boardMap.put(i, columnMap);	
    		}
    		else {
    			columnMap = boardMap.get(i);
    		}
    		
        	for (int j = 0; j < board[i].length; j++) {
        		columnMap.put(j, board[i][j]);
        	}
        }
        
        Queue<Skill> skillQueue = new LinkedList<Skill>();
        for (int i = 0; i < skill.length; i++) {
        	int type = skill[i][0];
        	int r1 = skill[i][1];
        	int c1 = skill[i][2];
        	int r2 = skill[i][3];
        	int c2 = skill[i][4];
        	int degree = skill[i][5];
        	skillQueue.add(new Skill(type, r1, c1, r2, c2, degree));
        }
        
        while (skillQueue.isEmpty() == false) {
        	Skill s = skillQueue.poll();
        	s.fire(boardMap);
        }

        for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : boardMap.entrySet()) {
        	for (Map.Entry<Integer, Integer> cEntry : entry.getValue().entrySet()) {
        		if (cEntry.getValue() > 0)
        			answer++;
        	}
        }
        
        return answer;
    }
}

class Skill {
	
	private int type;
	private int r1;
	private int c1;
	private int r2;
	private int c2;
	private int degree;
	
	public Skill(int type, int r1, int c1, int r2, int c2, int degree) {
		this.type = type;
		this.r1 = r1;
		this.c1 = c1;
		this.r2 = r2;
		this.c2 = c2;
		this.degree = degree;
	}

	public void fire(HashMap<Integer, HashMap<Integer, Integer>> boardMap) {
		int value = degree;
		if (type == 1) {
			value *= -1;
		}

		for (int r = r1; r <= r2; r++) {
			HashMap<Integer, Integer> columnMap = boardMap.get(r);
			for (int c = c1; c <= c2; c++) {
				int prevHp = columnMap.get(c); 
				columnMap.put(c, prevHp + value);
			}
		}
	}
}