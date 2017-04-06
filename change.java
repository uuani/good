package main;

import java.util.Scanner; // 스캐너 사용 선언

public class change {

	Scanner input = new Scanner(System.in); // 스캐너 사용
	ArrayStack stack = new ArrayStack(100); // stack 배열 선언
	Object[] infix; // 배열 infix 선언

	change(String[] str) { // 중위식을 후위식으로 String 형태로 받아옴
		this.infix = str; // 이 infix는 str
	}

	public boolean isAnOperator(Object s) { // operator인지 아닌지 판별하는 메소드
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) // 연산자
																				// 이면
			return true; // true로 리턴
		return false; // 나머지는 false로 리턴
	}

	public Object[] bracket(int index) { // 괄호를 인식하는 메소드
		index++; // 인덱스를 증가시킴
		Object s = infix[index]; // 중위식index를 가지는 새로운 객체 s 생성
		Object[] post = new Object[100]; // 전위식을 새로운 배열 100으로 생성
		int postIndex = 0; // 전위식 index를 0으로 초기화하여 선언
		int count = 0; // count를 0으로 초기화 하여 선언

		while (!s.equals(")")) { // 배열 s에서 )가 나올때까지 반복하는 반복문
			if (isAnOperator(s)) { // 연산자 가 s에 있으면
				if (count == 0) // count를 0으로 하고
					stack.push(s); // s를 스택에 푸쉬
				else { // 나머지는 (숫자일 경우)
					Object temp = stack.pop(); // 팝 하는 새로운 배열 temp 생성
					if (parity(temp) < parity(s)) { // 우선순위 를 판별하여 s가 temp보다
													// 우선순위가 늦으면
						stack.push(s); // s를 먼저 푸쉬
						stack.push(temp); // 그다음 temp를 푸쉬
					} else { // 나머지는
						stack.push(temp);
						stack.push(s); // 반대로 푸쉬
					}
				}
				count++; // count를 계속 증가
			} else if (s.equals("(")) { // 이중괄호가 있을 때를 생각 -> 새로운 (가 나왔을 때를 생각
				Object[] temp = bracket(index); // 괄호위 index를 가지는 배열 temp 생성
				index = index + temp.length + 1; // index의 값은 temp의 길이에 1을 더하여
													// 계속적으로 더함
				for (int i = 0; i < temp.length; i++) { // temp의 길이까지 i를 계속 증가
														// 시켜서
					post[postIndex] = temp[i]; // postindex와 i번째 배열을 같게 함
					postIndex++; // postindex를 꼐속 증가시킴
				}
			} else { // 나머지는
				post[postIndex] = s; // 배열 s를 post인덱스로 대입
				postIndex++; // post 인덱스를 계속 증가
			}

			index++; // index를 계속 증가
			s = infix[index]; // 중위식의 index를 배열 s에 대입
		}
		while (count != 0) { // 후위식이 0이 아닐 때까지 반복
			post[postIndex] = stack.pop(); // postindex만큼의 길이를 갖는 post 배열을 pop 함
			count--; // count를 --
			postIndex++; // postindex를 계속 증가
		}
		Object[] a = new Object[postIndex]; // postindex만큼의 길이를 갖는 배열 생성

		System.arraycopy(post, 0, a, 0, a.length); // a만큼의 길이로 같도록 arraycopy하여
													// 바꿔줌

		return a; // a로 리턴
	}

	public int parity(Object str) { // 우선순위
		int par = 0; // par을 0으로 초기화 하여 선언
		switch ((String) str) { // string형태의 str을 읽어오고 switch해줌
		case "+": // +일때와
		case "-": // -일때
			par = 2; // 2로 리턴
		case "*": // *와
		case "/": // /일때는
			par = 1;
		}
		return par; // par로 리턴
	}

	public Object[] convertInfix() { // 중위식으로 변환하는 메소드 생성
		int size = 0; // size를 0으로 초기화 하여 선언
		int c = 0; // c를 0으로 초기화 하여 선언

		for (int a = 0; a < infix.length; a++) { // 전위식의 길이만큼 a를 증가시키ㅕㄴ서 반복
			Object s = infix[a]; // a만큼의 길이를 갖는 배열 객체 s 생성
			if (!s.equals("(") && !s.equals(")")) // )와 (이 아니면
				size++; // size를 계속 증가 시킴
		}

		Object[] postfix = new Object[size]; // size만큼의 길이를 갖는 후위식 배열 생성

		for (int b = 0; b < infix.length; b++) { // 전위식의 길이만큼 b를 증가시킴
			Object s = infix[b]; // b만큼의 길이를 갖는 s 객체 생성
			if (!isAnOperator(s) && !s.equals("(")) { // 연산자나 (가 나왔을때
				if (!s.equals("(") && !s.equals(")")) { // )와 (가 나왔을 때
					postfix[c] = s; // 후위식배열 c는 s와 같아짐
					c++; // c를 계속 증가 시킴
				}
			} else if (s.equals("(")) { // 나머지 (가 나왔을 때
				Object[] temp = bracket(b); // 배열 temp는 괄호 b생성
				b = temp.length + b + 1; // b 는 temp의 길이와 1을 더한 값으로 함
				for (int i = 0; i < temp.length; i++) { // temp의 길이만큼 i를 증가함
					postfix[c] = temp[i]; // c의길이를 갖는 후위식 은 i만큼의 길이를 갖는 temp와 같도록 설정
					c++; // c를 계속 증가 시킴
				}
			} else if (isAnOperator(s)) { // 나머지-> 연산자일 경우
				if (stack.Size() == 0 && !s.equals(")")) // 0과 )가 아니라면
					stack.push(s); // s를 푸쉬
				else { // 나머지는
					Object op = stack.pop(); // pop
					if (parity(op) < parity(s)) { // 우선순위를 생각해서 s가 더 크면
						stack.push(s); // s를 먼저 푸쉬
						stack.push(op); // op를 다음으로 푸쉬
					} else {
						stack.push(op);
						stack.push(s); // 나머지는 반대로 푸쉬
					}
				}
			}
		}
		while (!stack.isEmpty()) { // isEmpty가 아닐 때까지 반복
			postfix[c] = stack.pop(); // 후위식배열 c를 pop
			c++; // c를 증가
		}

		return postfix; // 후위식으로 리턴
	}
}
