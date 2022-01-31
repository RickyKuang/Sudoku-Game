package sudoku;

/**
 * Sudoku Solver, solves a 9x9 int array that represents Sudoku. Includes other functionalities like printing.
 * 
 * @author Ricky Kuang
 * @version 1.0
 */
public class SudokuSolver {
	int[][] puzzle;
	private int invalidRow;
	private int invalidCol;
	
	/**
	 * Constructor for Sudoku Solver that takes in a 9x9 sudoku puzzle as parameter.
	 * 
	 * @param puzzle 9x9 sudoku puzzle that can be solved
	 */
	public SudokuSolver(int[][] puzzle) {
		this.puzzle = puzzle;
	}
	
//	/**
//	 * Getter method for the puzzle instance variable.
//	 * 
//	 * @return puzzle instance variable
//	 */
//	public int[][] getPuzzle() {
//		return puzzle;
//	}
	
	/**
	 * Setter method for a puzzle square. Takes in row and column numbers, and fills square at coordinate with number.
	 * 
	 * @param row Row of the current square being filled.
	 * @param column Column of the current square being filled.
	 * @param number Number that will be inserted into square.
	 */
	public void setPuzzleSquare(int[][] board, int row, int column, int number) {
		board[row][column] = number;
	}
	
	/**
	 * Solves the sudoku puzzle using backtracking algorithm.
	 * 
	 * @return true if puzzle can be solved, or if number is valid in position. false if can't be solved or number is invalid
	 */
	public boolean solvePuzzle(int[][] board) {
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if (board[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						board[i][j] = k;
						
						if (isValidNumber(board, i, j) && solvePuzzle(board))
							return true;
						
						board[i][j] = 0;
					}
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Checks for any conflict given the square of given row and column. Checks using row, column, and box checkers.
	 * 
	 * @param row Row of the current square being checked.
	 * @param column Column of the current square being checked.
	 * @return true if there is no conflict, false if there is conflict
	 */
	public boolean isValidNumber(int[][] board, int row, int column) {
		if (checkRow(board, row, column) == true
				&& checkColumn(board, row, column) == true
				&& checkBox(board, row, column) == true)
			return true;
		
		return false;
	}
	
	/**
	 * Checks for any conflict in the same row as the square in given row and column.
	 * 
	 * @param row Row of the current square
	 * @param column Column of the current square
	 * @return true if there is no conflict, false if there is conflict
	 */
	public boolean checkRow(int[][] board, int row, int column) {
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == board[row][column] && i != column) {
				this.setInvalidRow(row);
				this.setInvalidCol(i);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks for any conflict in the same column as the square in given row and column.
	 * 
	 * @param row Row of the current square
	 * @param column Column of the current square
	 * @return true if there is no conflict, false if there is conflict
	 */
	public boolean checkColumn(int[][] board, int row, int column) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][column] == board[row][column] && i != row) {
				this.setInvalidRow(i);
				this.setInvalidCol(column);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks for any conflict within the same 3x3 square that given square is in.
	 * 
	 * @param row Row of the current square
	 * @param column Column of the current square
	 * @return true if there is no conflict, false if there is conflict
	 */
	public boolean checkBox(int[][] board, int row, int column) {
		int startRow = (row/3) * 3;
		int startColumn = (column/3) * 3;
		
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startColumn; j < startColumn + 3; j++) {
				if (board[i][j] == board[row][column] && i != row && j != column) {
					this.setInvalidRow(i);
					this.setInvalidCol(j);
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Getter method for the invalid row number if there is a conflict.
	 * 
	 * @return invalidRow instance variable that contains row value of conflicted square
	 */
	public int getInvalidRow() {
		return invalidRow;
	}
	
	/**
	 * Getter method for the invalid column number if there is a conflict.
	 * 
	 * @return invalidCol instance variable that contains column value of conflicted square
	 */
	public int getInvalidCol() {
		return invalidCol;
	}
	
	/**
	 * Setter method for the invalid row instance variable.
	 * 
	 * @param invalidRow The new number of the invalid row.
	 */
	public void setInvalidRow(int invalidRow) {
		this.invalidRow = invalidRow;
	}
	
	/**
	 * Setter method for the invalid column instance variable.
	 * 
	 * @param invalidCol The new number of the invalid column.
	 */
	public void setInvalidCol(int invalidCol) {
		this.invalidCol = invalidCol;
	}
	
	public boolean puzzleEquals(int[][] puzzle, int[][] solution) {
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				if (puzzle[i][j] != solution[i][j])
					return false;
			}
		}
		return true;
	}
	
	public int[][] copyPuzzle() {
		int[][] copy = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				copy[i][j] = puzzle[i][j];
			}
		}
		
		return copy;
	}
	
	public void printPuzzle(int[][] p) {
		String puzzleStr = "";
		for (int i = 0; i < p.length; i++) {
			String rowStr = "{";
			for (int j = 0; j < p.length-1; j++) {
				rowStr += p[i][j] + ", ";
			}
			
			rowStr += p[i][8] + "}\n";
			puzzleStr += rowStr;
		}
		
		System.out.println(puzzleStr);
	}
	
	public static void main(String[] args) {
		int[][] filledSudoku = {
				{9, 8, 7, 6, 5, 4, 3, 2, 1}, 
				{2, 4, 6, 1, 7, 3, 9, 8, 5},
				{3, 5, 1, 9, 2, 8, 7, 4, 6},
				{1, 2, 8, 5, 3, 7, 6, 9, 4},
				{6, 3, 4, 8, 9, 2, 1, 5, 7},
				{7, 9, 5, 4, 6, 1, 8, 3, 2},
				{5, 1, 9, 2, 8, 6, 4, 7, 3},
				{4, 7, 2, 3, 1, 9, 5, 6, 8},
				{8, 6, 3, 7, 4, 5, 2, 1, 0}
			};
		
		int[][] mediumSudoku = {
				  { 0, 0, 0, 6, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 5, 0, 1 },
				  { 3, 6, 9, 0, 8, 0, 4, 0, 0 },
				  
				  { 0, 0, 0, 0, 0, 6, 8, 0, 0 },
				  { 0, 0, 0, 1, 3, 0, 0, 0, 9 },
				  { 4, 0, 5, 0, 0, 9, 0, 0, 0 },
				  
				  { 0, 0, 0, 0, 0, 0, 3, 0, 0 },
				  { 0, 0, 6, 0, 0, 7, 0, 0, 0 },
				  { 1, 0, 0, 3, 4, 0, 0, 0, 0 }
			};
		
		SudokuSolver ss = new SudokuSolver(filledSudoku);
		ss.solvePuzzle(mediumSudoku);
		ss.printPuzzle(mediumSudoku);
	}
}