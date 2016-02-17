package com.main.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int N; // size of the stack
	private Node first; // top of stack
	private Node last; // top of stack

	private class Node {

		private Item item;
		private Node next;

	}
	
	public Deque() {
		first = null;
		last = null;
		N = 0;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {

		private Node current = last;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			Item item = current.item;
			current = current.next;

			return item;

		}

	}

	public boolean isEmpty() {
		return first == null && last == null;
	}

	public int size() {
		return N;
	}

	public void addFirst(Item item) {

		if (item == null) {
			throw new NullPointerException();
		}

		Node previousFirst = first;
		first = new Node();
		first.next = null;
		first.item = item;
		
		if (previousFirst != null) {
			previousFirst.next = first;
		}
		
		if (N == 0) {
			last = first;
		} else if (N == 1) {
			previousFirst = last;
		}
		
		N++;

	}

	public Iterator<Item> getIterator () {
		return iterator();
	}
	
	
	public void addLast(Item item) {

		if (item == null) {
			throw new NullPointerException();
		}

		Node previousLast = last;
		last = new Node();
		last.item = item;
		
		if (previousLast != null) {
			last.next = previousLast;
		}
		
		if (N == 0) {
			first = last;
		} else if (N == 1) {
			previousLast = first;
		}
		
		N++;

	}

	public Item removeFirst() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		ListIterator iterator = new ListIterator();
		Item newFirstItem = null;
		Item currentItem  = null;
		
		while (iterator.hasNext()) {
			
			newFirstItem = currentItem;
			currentItem = iterator.next();
			
		}

		N--;
		
		if (N == 0) {
			last = first;
		}
		
		return newFirstItem;

	}

	public Item removeLast() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		last = last.next;
		N--;
		
		if (N == 0) {
			
			first = null;
			last = null;
			
		} else if (N == 1){
			first = last;
		}
		
		if (last != null) {
			return last.item;
		}
		
		return null;

	}

	public static void main(String[] args) {

		Deque<String> s = new Deque<String>();

		s.addFirst("A");
		s.addFirst("B");
		s.addFirst("C");
		s.addLast("D");
		
		System.out.println(s.first.item);
		System.out.println(s.last.item);
		
		s.removeLast();
		
		System.out.println("====");
		System.out.println(s.last.item);

		s.removeFirst();
		
		System.out.println("====");
		System.out.println(s.first.item);
		
		/*if (args != null && args.length > 0) {

			
			for (int i = 0; i < args.length; i++) {

				String item = args[i];

				if (!item.equals("-")) {
					s.addFirst(item);
				} else {
					s.removeFirst();
				}

				StdOut.println("(" + s.size() + " items on queue)");

			}

			System.out.println(s.first.item + " is the first item on the queue");
			
		}*/

	}

}