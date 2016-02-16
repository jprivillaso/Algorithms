package com.java.main.week1;

public class QuickUnionWeigted {

	private int[] id;
	private int[] sz;
	
	public QuickUnionWeigted(int size) {
		
		id = new int[size];
		sz = new int[size];
		
		for (int i = 0; i < size; i++) {
			id[i] = i;
			sz[i] = 0;
		}
		
	}

	public int root (int i) {
		
		while (i != id[i]) {
			
			id[i] = id[id[i]];
			i = id[i];
			
		}
		
		return i;
		
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {

		int i = root(p);
		int j = root(q);

		if (i == j) return; 
		
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}

	}

	public void init() {

		union(2,9);
		union(2,6);
		union(1,3);
		union(5,2);
		union(2,4);
		union(8,5);
		union(0,7);
		union(7,1);
		union(2,1);
		
		for (int i = 0; i < id.length; i++) {
			System.out.println(id[i]);
		}

	}

	public static void main(String[] args) {

		QuickUnionWeigted qu = new QuickUnionWeigted(10);
		qu.init();

	}

}
