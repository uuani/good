package main;

import java.util.Scanner; // ��ĳ�� ��� ����

public class change {

	Scanner input = new Scanner(System.in); // ��ĳ�� ���
	ArrayStack stack = new ArrayStack(100); // stack �迭 ����
	Object[] infix; // �迭 infix ����

	change(String[] str) { // �������� ���������� String ���·� �޾ƿ�
		this.infix = str; // �� infix�� str
	}

	public boolean isAnOperator(Object s) { // operator���� �ƴ��� �Ǻ��ϴ� �޼ҵ�
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) // ������
																				// �̸�
			return true; // true�� ����
		return false; // �������� false�� ����
	}

	public Object[] bracket(int index) { // ��ȣ�� �ν��ϴ� �޼ҵ�
		index++; // �ε����� ������Ŵ
		Object s = infix[index]; // ������index�� ������ ���ο� ��ü s ����
		Object[] post = new Object[100]; // �������� ���ο� �迭 100���� ����
		int postIndex = 0; // ������ index�� 0���� �ʱ�ȭ�Ͽ� ����
		int count = 0; // count�� 0���� �ʱ�ȭ �Ͽ� ����

		while (!s.equals(")")) { // �迭 s���� )�� ���ö����� �ݺ��ϴ� �ݺ���
			if (isAnOperator(s)) { // ������ �� s�� ������
				if (count == 0) // count�� 0���� �ϰ�
					stack.push(s); // s�� ���ÿ� Ǫ��
				else { // �������� (������ ���)
					Object temp = stack.pop(); // �� �ϴ� ���ο� �迭 temp ����
					if (parity(temp) < parity(s)) { // �켱���� �� �Ǻ��Ͽ� s�� temp����
													// �켱������ ������
						stack.push(s); // s�� ���� Ǫ��
						stack.push(temp); // �״��� temp�� Ǫ��
					} else { // ��������
						stack.push(temp);
						stack.push(s); // �ݴ�� Ǫ��
					}
				}
				count++; // count�� ��� ����
			} else if (s.equals("(")) { // ���߰�ȣ�� ���� ���� ���� -> ���ο� (�� ������ ���� ����
				Object[] temp = bracket(index); // ��ȣ�� index�� ������ �迭 temp ����
				index = index + temp.length + 1; // index�� ���� temp�� ���̿� 1�� ���Ͽ�
													// ��������� ����
				for (int i = 0; i < temp.length; i++) { // temp�� ���̱��� i�� ��� ����
														// ���Ѽ�
					post[postIndex] = temp[i]; // postindex�� i��° �迭�� ���� ��
					postIndex++; // postindex�� ���� ������Ŵ
				}
			} else { // ��������
				post[postIndex] = s; // �迭 s�� post�ε����� ����
				postIndex++; // post �ε����� ��� ����
			}

			index++; // index�� ��� ����
			s = infix[index]; // �������� index�� �迭 s�� ����
		}
		while (count != 0) { // �������� 0�� �ƴ� ������ �ݺ�
			post[postIndex] = stack.pop(); // postindex��ŭ�� ���̸� ���� post �迭�� pop ��
			count--; // count�� --
			postIndex++; // postindex�� ��� ����
		}
		Object[] a = new Object[postIndex]; // postindex��ŭ�� ���̸� ���� �迭 ����

		System.arraycopy(post, 0, a, 0, a.length); // a��ŭ�� ���̷� ������ arraycopy�Ͽ�
													// �ٲ���

		return a; // a�� ����
	}

	public int parity(Object str) { // �켱����
		int par = 0; // par�� 0���� �ʱ�ȭ �Ͽ� ����
		switch ((String) str) { // string������ str�� �о���� switch����
		case "+": // +�϶���
		case "-": // -�϶�
			par = 2; // 2�� ����
		case "*": // *��
		case "/": // /�϶���
			par = 1;
		}
		return par; // par�� ����
	}

	public Object[] convertInfix() { // ���������� ��ȯ�ϴ� �޼ҵ� ����
		int size = 0; // size�� 0���� �ʱ�ȭ �Ͽ� ����
		int c = 0; // c�� 0���� �ʱ�ȭ �Ͽ� ����

		for (int a = 0; a < infix.length; a++) { // �������� ���̸�ŭ a�� ������Ű�Ť��� �ݺ�
			Object s = infix[a]; // a��ŭ�� ���̸� ���� �迭 ��ü s ����
			if (!s.equals("(") && !s.equals(")")) // )�� (�� �ƴϸ�
				size++; // size�� ��� ���� ��Ŵ
		}

		Object[] postfix = new Object[size]; // size��ŭ�� ���̸� ���� ������ �迭 ����

		for (int b = 0; b < infix.length; b++) { // �������� ���̸�ŭ b�� ������Ŵ
			Object s = infix[b]; // b��ŭ�� ���̸� ���� s ��ü ����
			if (!isAnOperator(s) && !s.equals("(")) { // �����ڳ� (�� ��������
				if (!s.equals("(") && !s.equals(")")) { // )�� (�� ������ ��
					postfix[c] = s; // �����Ĺ迭 c�� s�� ������
					c++; // c�� ��� ���� ��Ŵ
				}
			} else if (s.equals("(")) { // ������ (�� ������ ��
				Object[] temp = bracket(b); // �迭 temp�� ��ȣ b����
				b = temp.length + b + 1; // b �� temp�� ���̿� 1�� ���� ������ ��
				for (int i = 0; i < temp.length; i++) { // temp�� ���̸�ŭ i�� ������
					postfix[c] = temp[i]; // c�Ǳ��̸� ���� ������ �� i��ŭ�� ���̸� ���� temp�� ������ ����
					c++; // c�� ��� ���� ��Ŵ
				}
			} else if (isAnOperator(s)) { // ������-> �������� ���
				if (stack.Size() == 0 && !s.equals(")")) // 0�� )�� �ƴ϶��
					stack.push(s); // s�� Ǫ��
				else { // ��������
					Object op = stack.pop(); // pop
					if (parity(op) < parity(s)) { // �켱������ �����ؼ� s�� �� ũ��
						stack.push(s); // s�� ���� Ǫ��
						stack.push(op); // op�� �������� Ǫ��
					} else {
						stack.push(op);
						stack.push(s); // �������� �ݴ�� Ǫ��
					}
				}
			}
		}
		while (!stack.isEmpty()) { // isEmpty�� �ƴ� ������ �ݺ�
			postfix[c] = stack.pop(); // �����Ĺ迭 c�� pop
			c++; // c�� ����
		}

		return postfix; // ���������� ����
	}
}
