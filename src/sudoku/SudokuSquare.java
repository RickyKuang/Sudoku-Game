package sudoku;

import javax.swing.*;

public class SudokuSquare extends JButton {
	
	private int number;
	private int row;
	private int column;
	private boolean filled;
	
	public SudokuSquare(int number, int row, int column, boolean filled) {
		super();
		this.number = number;
		this.row = row;
		this.column = column;
		this.filled = filled;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public boolean getFilled() {
		return filled;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
}
