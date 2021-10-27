package nhn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Problum2 {

	private class InputData {
		int numOfRegion;
		int numOfAttackableFrequency;
		int[][] frequencies;
	}
	
	private InputData processStdin() {
		InputData inputData = new InputData();
		
		try (Scanner scanner = new Scanner(System.in)) {
			String[] temp = scanner.nextLine().split(" ");
			inputData.numOfRegion = Integer.parseInt(temp[0]);
			inputData.numOfAttackableFrequency = Integer.parseInt(temp[1]);
			inputData.frequencies = new int[inputData.numOfRegion][];
			for (int i = 0; i < inputData.numOfRegion; i++) {
				temp = scanner.nextLine().split(" ");
				
				int numOfFrequency = Integer.valueOf(temp[0]);
				inputData.frequencies[i] = new int[numOfFrequency];
				for (int j = 0; j < numOfFrequency; j++) {
					inputData.frequencies[i][j] = Integer.parseInt(temp[j + 1]);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		
		return inputData;
	}
	
	public void main() {
		InputData inputData = processStdin();
		
		solution(inputData.numOfRegion, inputData.numOfAttackableFrequency, inputData.frequencies);
	}

	private void solution(int numOfRegion, int numOfAttackableFrequency, int[][] frequencies) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < numOfRegion; i++) {
			
			for (int j = 0; j < frequencies.length; j++) {
				int key = frequencies[i][j];
				int count = map.getOrDefault(key, 0) + 1;
				map.put(key, count);
			}
		}
		
        List<Integer> list = new ArrayList<Integer>(map.keySet());
        Collections.sort(list, (l, r) -> (map.get(r).compareTo(map.get(l))));
        
        int result = 0;
        for (int i = 0; i < numOfAttackableFrequency; i++) {
        	int key = list.get(i);
        	result += map.get(key);
        }
        
        System.out.println(result);
	}
}
