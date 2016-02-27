package com.main.java.queues;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

	public static void main(String[] args) {

		if (args.length == 0) {
			throw new NoSuchElementException("K number is missing");
		}

		RandomizedQueue<String> randQueue = new RandomizedQueue<>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			randQueue.enqueue(item);
		}
		
		int k = Integer.parseInt(args[0]);
		
		for (int i = 0; i < k; i++) {
			StdOut.println(randQueue.dequeue());
		}

	}

}
