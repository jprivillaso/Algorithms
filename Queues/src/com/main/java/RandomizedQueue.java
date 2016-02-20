package com.main.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int N; // size of the stack
	private Node first;
	private Node last;
	
	private class Node {

		private Item item;
		private Node next;

	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private Node current = first;

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

	public RandomizedQueue() {
		first = null;
		last = null;
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		
		if (item == null) {
			throw new NullPointerException();
		}

		Node previousLast = last;
		last = new Node();
		last.item = item;
		last.next = null;

		if (previousLast != null) {
			previousLast.next = last;
		}

		if (N == 0) {
			first = last;
		} else if (N == 1) {
			first = previousLast;
		}

		N++;
		
	}

	public Item dequeue() {
		
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		if (N == 1) {

			Item item = last.item;
			first = null;
			last = null;
			N--;
			return item;

		} else {
			
			ListIterator iter = new ListIterator();
			Node previousNode = first;
			Node currentNode = first;
			int itemToRemove = StdRandom.uniform(1, N+1);
			
			for (int i = 1; i < itemToRemove; i++) {
				
				previousNode = currentNode;
				currentNode = currentNode.next;
				iter.next();
				
			}
			
			if (currentNode.next != null) {
				previousNode.next = currentNode.next;
			} else {
				previousNode.next = null;
			}
			
			if (itemToRemove == N) {
				last = previousNode;
			} else if (itemToRemove == 1) {
				first = currentNode.next;
			}
			
			
			N--;
			return currentNode.item;

		}
		
	}

	public Item sample() {
		
		Node currentNode = first;
		
		int itemToRemove = StdRandom.uniform(1, N+1);
		
		for (int i = 1; i <= itemToRemove; i++) {
			currentNode = currentNode.next;
		}
		
		return currentNode.item;
		
	}

	public static void main(String[] args) {
		
	}

}