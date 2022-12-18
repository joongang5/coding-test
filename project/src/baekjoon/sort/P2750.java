//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package baekjoon.sort;

import java.util.Arrays;
import java.util.Scanner;

public class P2750 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = Integer.parseInt(scanner.nextLine());
        int[] nums = new int[N];
        for (int i = 0; i < nums.length; i++) {
        	nums[i] = Integer.parseInt(scanner.nextLine());
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);	
        }
        
        scanner.close();
    }
}
