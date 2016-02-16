package com.java.main.week1;

public class QuickUnion {

	private int[] id;
	
	public QuickUnion(int size) {
		
		id = new int[size];
		
		for (int i = 0; i < size; i++) {
			id[i] = i;
		}
		
	}
	
	public QuickUnion() {
		
	}
	
	public boolean connected (int p, int q) {
		return id[p] == id[q];
	}
	
	public void union (int p, int q) {
		
		int pid = id[p];
		int qid = id[q];
		
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pid) id[i] = qid; 	
		}	
		
	}
	
	public void init () {
		  
		union(6,0);
		union(7,6);
		union(9,3);
		union(3,0);
		union(1,8);
		union(7,4);
		
		for (int i = 0; i < id.length; i++) {
			System.out.println(id[i]);
		}
		
	}
	
	public static void main(String[] args) {
		
		QuickUnion qu = new QuickUnion(10);
		qu.init();
		
	}
	
}