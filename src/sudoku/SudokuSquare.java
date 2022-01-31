package sudoku;

import javax.swing.*;

/**
 * Sudoku Square class that extends JButton. Has additional variables/methods for the purpose of Sudoku Square/Cell.
 * 
 * @author Ricky Kuang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SudokuSquare extends JButton {
	
	private int number;
	private int row;
	private int column;
	private boolean filled;
	
	/**
	 * Constructor for Sudoku Square that extends JButton class.
	 * 
	 * @param number Number between 0 and 9. 0 if sudoku square is empty
	 * @param row Row of square in sudoku board
	 * @param column Column of square in sudoku board
	 * @param filled Whether the square is prefilled in sudoku board
	 */
	public SudokuSquare(int number, int row, int column, boolean filled) {
		super();
		this.number = number;
		this.row = row;
		this.column = column;
		this.filled = filled;
	}
	
	/**
	 * Getter method for number of sudoku square
	 * 
	 * @return number instance variable
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Getter method for the row of the sudoku square
	 * 
	 * @return row instance variable
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Getter method for the column of the sudoku square
	 * 
	 * @return column instance variable
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Getter method for the filled status of the sudoku square
	 * 
	 * @return filled instance variable
	 */
	public boolean getFilled() {
		return filled;
	}
	
	/**
	 * Setter method for the number of sudoku square
	 * 
	 * @param number New number that the sudoku square will be set to
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * Setter method for the row of sudoku square
	 * 
	 * @param row Row that the sudoku square will be set to
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Setter method for the column of sudoku square
	 * 
	 * @param column Column that the sudoku square will be set to
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Setter method for the filled status of sudoku square
	 * 
	 * @param filled Filled status that the sudoku square will be set to
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
}
