package main;

public class Calculator { // ������ ����!

	ArrayStack stack = new ArrayStack(100); // ������ 100��ŭ ����

	public boolean isAnOperator(String s) { // ���������� �ƴ��� �Ǻ��ϴ� �޼ҵ�
		boolean x = false; // x�� false�� ����
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) // �����ڰ�
																				// ������
			x = true; // x�� true�� ����

		return x; // x�� ����
	}

	public double evaluate(double x, double y, String op) { // x�� y, op�� ���ҷΰ�����
															// evauate �޼ҵ� ����
		double z = 0; // z�� 0���� �ʱ�ȭ�Ͽ� ����
		if (op.equals("+")) // +�̸�
			z = x + y; // ����
		else if (op.equals("-")) // -�̸�
			z = x - y; // ��
		else if (op.equals("*")) // *�̸�
			z = x * y; // ����
		else // ��������
			z = x / y; // ����
		return z; // z�� ����
	}

	public Calculator(Object[] postfix) { // ������ �迭�� ���� ���� �޼ҵ� ����
		for (int i = 0; i < postfix.length; i++) { // ������ ���̸�ŭ i�� ����
			Object in = postfix[i]; // i��ŭ�� ���̸� ���� ��ü in ����
			if (isAnOperator((String) in)) { // ���ڿ��� �޾ƿ��� ������ �ϰ��
				double y = Double.parseDouble((String) stack.pop()); // ���ڿ���
																		// �޾ƿͼ�
																		// pop
				double x = Double.parseDouble((String) stack.pop()); // ���ڿ���
																		// �޾ƿͼ�
																		// pop
				double z = evaluate(x, y, (String) in); // ������ ������ ���� z�� ����
				stack.push("" + z); // Ǫ��!
			} else {
				stack.push(in); // �������� ��ü�� Ǫ��
			}
		}
		System.out.println("result : " + stack.pop()); // pop�� ���� ���

	}
}
