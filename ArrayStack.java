package main;

public class ArrayStack implements Stack {

	static String EMPTY_MESSAGE = "stack is empty";

	private Object[] a;
	private int size;

	public ArrayStack(int capacity) {
		a = new Object[capacity];
	}

	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new IllegalStateException(EMPTY_MESSAGE);
		return a[size - 1];
	}

	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new IllegalStateException(EMPTY_MESSAGE);
		Object object = a[--size];
		a[size] = null;
		return object;
	}

	@Override
	public void push(Object object) {
		// TODO Auto-generated method stub
		if (size == a.length)
			resize();
		a[size++] = object;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	private void resize() {
		Object[] aa = a;
		a = new Object[2 * aa.length];
		System.arraycopy(aa, 0, a, 0, size);
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public String toString() {
		System.out.println("ToString");
		for (int i = size - 1; i >= 0; i--) {
			System.out.println("value : " + a[i]);
		}
		return null;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof ArrayStack))
			return false;
		ArrayStack temp = (ArrayStack) obj;
		if (temp.size == this.size) {
			for (int i = 0; i < size; i++)
				if (!temp.a[i].equals(this.a[i]))
					return false;
		} else
			return false;
		return true;
	}

	public Object popSecond() {
		Object temp = a[size - 2];
		a[size - 2] = a[size - 1];
		a[size - 1] = null;
		size--;
		return temp;
	}

	public Object popBottom() {
		Object temp = a[0];
		for (int i = 0; i < size - 1; i++) {
			a[i] = a[i + 1];
		}
		a[size - 1] = null;
		size--;
		return temp;
	}

	public int Size() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
