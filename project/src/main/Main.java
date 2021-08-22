package main;

import programmers.skill.level01.Problum02;

public class Main {

	public static void main(String[] args) {

		Problum02 p2 = new Problum02();
		int[] answer = p2.solution(12345);
		
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);			
		}
	}
}
