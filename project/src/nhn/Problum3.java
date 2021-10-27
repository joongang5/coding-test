package nhn;

import java.util.Scanner;

public class Problum3 {

	private class InputData {
		int numOfConflict;
		int[][] conflicts;
	}
	
	private InputData processStdin() {
		InputData inputData = new InputData();
		
		try (Scanner scanner = new Scanner(System.in)) {
			inputData.numOfConflict = Integer.parseInt(scanner.nextLine());
			inputData.conflicts = new int[inputData.numOfConflict][2];
			
			for (int i = 0; i < inputData.numOfConflict; i++) {
				String[] temp = scanner.nextLine().split(" ");
				inputData.conflicts[i][0] = Integer.parseInt(temp[0]);
				inputData.conflicts[i][1] = Integer.parseInt(temp[1]);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return inputData;
	}
	
	public void main() {
		InputData inputData = processStdin();
		
		solution(inputData.numOfConflict, inputData.conflicts);
	}

	private void solution(int numOfConflict, int[][] conflicts) {
		
	}
}
