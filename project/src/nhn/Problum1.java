package nhn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Problum1 {

	private class InputData {
		int numOfOperation;
		Operation[] operations;
	}
	
	private class Operation {
		public OperationType type;
		public Integer value;
		
		public Operation(OperationType type, Integer value) {
			this.type = type;
			this.value = value;
		}
	}
	
	private enum OperationType {
		branch,
		merge;
	}
	
	private InputData processStdin() {
		InputData inputData = new InputData();
		
		try (Scanner scanner = new Scanner(System.in)) {
			inputData.numOfOperation = Integer.parseInt(scanner.nextLine());
			inputData.operations = new Operation[inputData.numOfOperation];
			
			for (int i = 0; i < inputData.numOfOperation; i++) {
				String[] temp = scanner.nextLine().split(" ");
				
				Integer value = null;
				OperationType operationType = OperationType.valueOf(temp[0]);
				if (OperationType.merge == operationType) {
					value = Integer.valueOf(temp[1]);
				}
				
				inputData.operations[i] = new Operation(operationType, value);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return inputData;
	}
	
	public void main() {
		InputData inputData = processStdin();
		
		solution(inputData.numOfOperation, inputData.operations);
	}

	private void solution(int numOfOperation, Operation[] operations) {
		Branch root = new Branch(1);
		
		for (int i = 0; i < numOfOperation; i++) {
			Operation operation = operations[i];
			
			if (operation.type == OperationType.branch) {
				root.addBranch();
			} else if (operation.type == OperationType.merge) {
				root.merge(operation.value);
			}
		}
		
		root.print();
	}
	
	private class Branch {
		public int number;
		public HashSet<Integer> childrens = new HashSet<Integer>();
		
		public Branch(int number) {
			this.number = number;
		}
		
		private int getMinNum() {
			int findNum = number + 1;

			for (int i = 0; i < childrens.size(); i++) {
				if (childrens.contains(findNum))
					findNum += 1;
				else
					break;
	        }
			
			return findNum;
		}
		
		public void addBranch() {
			childrens.add(getMinNum());
		}
		
		public void merge(int number) {
			childrens.remove(number);
		}
		
		public void print() {
			System.out.print(number + " ");
			
	        List<Integer> childList = new ArrayList<Integer>(childrens);
	        Collections.sort(childList);
			
	        for (int i = 0; i < childList.size(); i++) {
	        	System.out.print(childList.get(i));
	        	
	        	if (i < childList.size() - 1)
	        		System.out.print(" ");
	        }
		}
	}
}
