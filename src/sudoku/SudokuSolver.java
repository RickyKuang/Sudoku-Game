package sudoku;

public class SudokuSolver {
	private int[][] puzzle;
	
	public SudokuSolver(int[][] puzzle) {
		this.puzzle = puzzle;
	}
	
	public int[][] getPuzzle() {
		return puzzle;
	}
	
	public boolean solvePuzzle() {
		for (int i = 0; i < puzzle.length; i++) 
		{
			for (int j = 0; j < puzzle[i].length; j++) 
			{
				if (puzzle[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						puzzle[i][j] = k;
						
						if (isValidNumber(i, j) && solvePuzzle())
							return true;
						
						puzzle[i][j] = 0;
					}
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void fillBox(int row, int column, int number) {
		puzzle[row][column] = number;
		if (isValidNumber(row, column) == false)
			puzzle[row][column] = 0;
	}
	
	public boolean isValidNumber(int row, int column) {
		if (checkRow(row, column) == true
				&& checkColumn(row, column) == true
				&& checkBox(row, column) == true)
			return true;
		
		return false;
	}
	
	public boolean checkRow(int row, int column) {
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[row][i] == puzzle[row][column] && i != column)
				return false;
		}
		return true;
	}
	
	public boolean checkColumn(int row, int column) {
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i][column] == puzzle[row][column] && i != row)
				return false;
		}
		return true;
	}
	
	public boolean checkBox(int row, int column) {
		int startRow = (row/3) * 3;
		int startColumn = (column/3) * 3;
		
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startColumn; j < startColumn + 3; j++) {
				if (puzzle[i][j] == puzzle[row][column] && i != row && j != column)
					return false;
			}
		}
		return true;
	}
}
