package main;

public class Calculator { // 후위식 계산기!

	ArrayStack stack = new ArrayStack(100); // 스택을 100만큼 생성

	public boolean isAnOperator(String s) { // 연산자인지 아닌지 판별하는 메소드
		boolean x = false; // x를 false로 선언
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) // 연산자가
																				// 있으면
			x = true; // x를 true로 리턴

		return x; // x로 리턴
	}

	public double evaluate(double x, double y, String op) { // x와 y, op를 원소로가지는
															// evauate 메소드 생성
		double z = 0; // z를 0으로 초기화하여 선언
		if (op.equals("+")) // +이면
			z = x + y; // 더함
		else if (op.equals("-")) // -이면
			z = x - y; // 뺌
		else if (op.equals("*")) // *이면
			z = x * y; // 곱함
		else // 나머지는
			z = x / y; // 나눔
		return z; // z로 리턴
	}

	public Calculator(Object[] postfix) { // 후위식 배열을 갖는 계산기 메소드 생성
		for (int i = 0; i < postfix.length; i++) { // 후위식 길이만큼 i를 증가
			Object in = postfix[i]; // i만큼의 길이를 갖는 객체 in 생성
			if (isAnOperator((String) in)) { // 문자열로 받아오는 연산자 일경우
				double y = Double.parseDouble((String) stack.pop()); // 문자열로
																		// 받아와서
																		// pop
				double x = Double.parseDouble((String) stack.pop()); // 문자열로
																		// 받아와서
																		// pop
				double z = evaluate(x, y, (String) in); // 연산을 수행한 것을 z에 저장
				stack.push("" + z); // 푸쉬!
			} else {
				stack.push(in); // 나머지는 객체를 푸쉬
			}
		}
		System.out.println("result : " + stack.pop()); // pop한 것을 출력

	}
}
