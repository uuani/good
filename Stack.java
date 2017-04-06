package main;

public interface Stack {
	public Object peek();
	public Object pop();
	public void push(Object object);
	public int size();
	public Object popSecond();
	public Object popBottom();
	public boolean equals(Object obj);
}


