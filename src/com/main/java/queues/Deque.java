package com.main.java.queues;

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
		first.item = item;
		first.next = previousFirst;

		if (N == 0) {
			last = first;
		} else if (N == 1) {
			last = previousFirst;
		}

		N++;

	}

	public void addLast(Item item) {

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

	public Item removeFirst() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Node previousFirst = first;

		if (N == 1) {
			first = null;
			last = null;
		} else {
			first = first.next;
		}

		N--;
		return previousFirst.item;

	}

	public Item removeLast() {

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

			while (iter.hasNext()) {

				if (currentNode.next == null) {
					last = previousNode;
					last.next = null;
				} else {

					Node temp = currentNode;
					currentNode = currentNode.next;
					previousNode = temp;

				}

				iter.next();

			}

			N--;
			return currentNode.item;

		}

	}

	public static void main(String[] args) {
		
	}

}