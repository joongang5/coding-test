package baekjoon.example;

import java.util.Scanner;

public class Example {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int first = scanner.nextInt();
		int second = scanner.nextInt();
		
		int answer = first + second;
		
		System.out.println(answer);
		
		scanner.close();
	}
}
