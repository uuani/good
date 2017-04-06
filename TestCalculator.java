package main;

import java.util.Scanner; // 스태너 사용 선언

public class TestCalculator {
	public static void main(String[] args) { // 메인함수 시작
		Scanner sc = new Scanner(System.in); // 스캐너 사용
		System.out.println("input : "); // 문장 출력
		String[] infix = sc.nextLine().split(" "); // 띄어쓰기를 하여 문자를 읽어 들여옴
		System.out.print("Infix : "); // 문장 출력
		for (int a = 0; a < infix.length; a++) // 중위식 길이만큼 a를 증가시켜서
			System.out.print("" + infix[a]); // 중위식 출력
		System.out.println(); // 줄바꿈

		change intopost = new change(infix); // 후위식으로 바꾸는 객체 생성
		Object[] postfix = intopost.convertInfix(); // 후위식 배열 생성
		System.out.print("Postfix : "); // 문장 출력
		for (int b = 0; b < postfix.length; b++) { // 후위식 길이보다 작게 b를 증가 
			System.out.print("" + postfix[b]); // 후위식 출력
		}
		System.out.println(); // 줄바꿈
		new Calculator(postfix);
	}
}
