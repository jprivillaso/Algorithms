package com.main.java;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private int N; // size of the stack
	private Node first; // top of stack

	public class Node {

		private Item item;
		private Node next;

	}

	@Override
	public Iterator<Item> iterator() {
		return null;
	}

	public Deque() {
		first = null;
		N = 0;
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		return 0;
	}

	public void addFirst(Item item) {

	}

	public void addLast(Item item) {

	}

	public Item removeFirst() {
		return null;
	}

	public Item removeLast() {
		return null;
	}

	public static void main(String[] args) {

		Deque<String> s = new Deque<String>();
		
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
		}
		
		StdOut.println("(" + s.size() + " left on stack)");

	}

}