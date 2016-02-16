package com.java.main.week1.assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private WeightedQuickUnionUF quickUnion;
  private int n;
  private int[][] grid; 
  public Percolation(int n) {

    if (n <= 0) {
      throw new IllegalArgumentException("n must be a positive number"
          + " greater than zero");
    }

    grid = new int[n][n];

    /**
     * For the implementation of this problem I used a matrix NXN and 
     * an array of size NXN + the two virtual nodes (Top and Bottom)
     * After an operation of open is called, let`s say i want to open the position
     * [2][2], I find the corresponding position in the array, so it will be the 
     * 7th element of the array
     * 
     * [virtualTop, 1, 2, 3, 4, 5, 6 ..... n, virtualBottom] 
     */
    quickUnion = new WeightedQuickUnionUF((n * n) + 2);
    this.n = n;
    /*
     * Initializing the matrix with all positions set to -1 would help
     * me to know which sites are open. 
     * After a site is opened, its position is set to the position number
     * E.g. Position [1][1] (It's a 1-index matrix) will be set to 0
     * after opening it.
     * 
     * */
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = -1;
      }
    }
    
  }

  public void open(int i, int j) {
    
    if (i < 1 || i > n || j < 1 || j > n) {
        throw new IndexOutOfBoundsException("One of the parameters "
           + "is out of bounds " + i + " - " + j);
  	}
    
    int matrixRow = i - 1;
    int matrixCol = j - 1;
    
    int positionInArray = matrixRow * n + matrixCol;
    grid[matrixRow][matrixCol] = positionInArray;
    
    /* Find the adjacent positions */
    int realRow = positionInArray / n;
    int leftAdjacent  = positionInArray  - 1;
    int rightAdjacent = positionInArray  + 1;
    int upperAdjacent = positionInArray  - n;
    int bottomAdjacent = positionInArray + n;
    
    if (upperAdjacent < 0) {
      quickUnion.union(0, positionInArray+1);
    } else if (isOpen(i-1, j)) {
      quickUnion.union((upperAdjacent+1), (positionInArray+1));
    }
    
    if (leftAdjacent / n == realRow && matrixCol > 0 && isOpen(i, j-1)) {
      quickUnion.union(leftAdjacent+1, positionInArray+1);
    }

    if (rightAdjacent / n == realRow && isOpen(i, j+1)) {
      quickUnion.union(rightAdjacent+1, positionInArray+1);
    }

    double bottomRow = (double) bottomAdjacent / n;
    
    if (bottomRow >= n && quickUnion.connected(0, positionInArray+1)) {
      quickUnion.union(n*n+1, positionInArray+1);
    } else if (matrixRow >= 0 && i+1 <= n && isOpen(i+1, j)) {
      quickUnion.union(bottomAdjacent+1, positionInArray+1);
    }
    
  }

  public boolean isOpen(int i, int j) {

    if (i < 1 || i > n || j < 1 || j > n) {
        throw new IndexOutOfBoundsException("One of the parameters "
           + "is out of bounds " + i + " - " + j);
  	}
    
    int matrixRow = i - 1;
    int matrixCol = j - 1;
    
    return grid[matrixRow][matrixCol] != -1;

  }

  public boolean isFull(int i, int j) {

	if (i < 1 || i > n || j < 1 || j > n) {
      throw new IndexOutOfBoundsException("One of the parameters "
         + "is out of bounds " + i + " - " + j);
	}
	  
	int matrixRow = i - 1;
	int matrixCol = j - 1;
    
    int arrayPosition = (matrixRow * n) + matrixCol + 1;
    return quickUnion.connected(0, arrayPosition);

  }

  public boolean percolates() {
    
	  if (n == 1 && isFull(1,1)) {
		  return true;
	  } else {
		  return quickUnion.connected(0, n * n + 1);
	  }
	  
  }
  
  public static void main(String[] args) {
	
	Percolation percolates = new Percolation(10);
	percolates.isFull(10, 6);
	  
  }
  
}