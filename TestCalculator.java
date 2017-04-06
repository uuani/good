package main;

import java.util.Scanner; // ���³� ��� ����

public class TestCalculator {
	public static void main(String[] args) { // �����Լ� ����
		Scanner sc = new Scanner(System.in); // ��ĳ�� ���
		System.out.println("input : "); // ���� ���
		String[] infix = sc.nextLine().split(" "); // ���⸦ �Ͽ� ���ڸ� �о� �鿩��
		System.out.print("Infix : "); // ���� ���
		for (int a = 0; a < infix.length; a++) // ������ ���̸�ŭ a�� �������Ѽ�
			System.out.print("" + infix[a]); // ������ ���
		System.out.println(); // �ٹٲ�

		change intopost = new change(infix); // ���������� �ٲٴ� ��ü ����
		Object[] postfix = intopost.convertInfix(); // ������ �迭 ����
		System.out.print("Postfix : "); // ���� ���
		for (int b = 0; b < postfix.length; b++) { // ������ ���̺��� �۰� b�� ���� 
			System.out.print("" + postfix[b]); // ������ ���
		}
		System.out.println(); // �ٹٲ�
		new Calculator(postfix);
	}
}
