package com.main.java.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	
	public PercolationStats(int N, int T) {
		
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException();
		}
		
		Percolation percolation = new Percolation(N);

		double openSitesCount = 0d;
		double[] openSites = new double[N*N+1];
		int counter = 0;
		
		/**
		 * I perform T experiments and inside each experiment,
		 * I open randomly T sites
		 */
		for (int i = 0; i <= T; i++) {
			
			openSitesCount = 0d;
			
			for (int m = 0; m < T; m++) {
				
				/*
				 * I add 1 to the N variable in order to be included as a 
				 * possible random number, because the uniform method does 
				 * not consider the higher threshold
				 */
				int j = StdRandom.uniform(1, N+1);
				int k = StdRandom.uniform(1, N+1);
				
				if (!percolation.isOpen(j, k)) {

					percolation.open(j, k);
					openSitesCount++;
					
				}
				
				if (percolation.percolates()) {
					break;
				}
				
			}
			
			openSites[counter] = openSitesCount;
			counter++;
			
		}
		
		this.mean = StdStats.mean(openSites);
		this.stddev = StdStats.stddev(openSites);
		this.confidenceLo = mean - (1.96 * stddev / Math.sqrt(T));
		this.confidenceHi = mean + (1.96 * stddev / Math.sqrt(T));
		
	}

	public double mean() {
		return this.mean;
	}

	public double stddev() {
		return this.stddev;
	}

	public double confidenceLo() {
		return this.confidenceLo;
	}

	public double confidenceHi() {
		return this.confidenceHi;
	}

	public static void main(String[] args) {
		
		if (args != null && args.length != 2) {
			throw new IllegalArgumentException("There are missing arguments");
		}
		
		String n = args[0];
		String t = args[1];
		
		PercolationStats percolationStats = new PercolationStats(Integer.parseInt(n), Integer.parseInt(t));
		
	}
	   
}