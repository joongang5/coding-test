package baekjoon.example;

import java.util.Scanner;

public class Example {

	public static void main(String[] args) {
		int answer = 0;
		Scanner scanner = new Scanner(System.in);
		
		String[] temp = scanner.nextLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		
		answer = N + K;
		
		System.out.println(answer);
		
		scanner.close();
	}
}
