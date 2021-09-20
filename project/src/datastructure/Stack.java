package datastructure;

public class Stack {

	private int top;
	private int[] stack;

	private final int MAX_N = 10;

	public Stack() {
		stack = new int[MAX_N];
		top = 0;
	}

	public boolean isEmpty() {
		return (top == 0);
	}

	public boolean isFull() {
		return (top == MAX_N);
	}

	public boolean push(int value) {
		if (isFull()) {
			System.out.println("stack overflow!");
			return false;
		}
		stack[top] = value;
		top++;

		return true;
	}

	public int pop(int value) {
		if (isEmpty()) {
			System.out.println("stack is empty!");
			return 0;
		}
		top--;
		value = stack[top];

		return value;
	}
}
