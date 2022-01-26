package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SudokuGUI 
{
	// Frame and Board
	private JFrame sudokuFrame;
	private JPanel sudokuBoard;
	
	// Sudoku Square
	private SudokuSquare[][] sudokuSquareButtons;
	private JLabel selectedSquareLabel;
	private SudokuSquare selectedSquare;
	private SudokuSquare prevSquare;
	private Color prevColor;
	
	// Digit Buttons
	private JPanel buttonPanel;
	private ArrayList<JButton> buttons;
	
	// Other
	private JLabel timeLabel;
	private boolean gameWon;
	private int[][] puzzle;
	private int[][] solution;
	
	public SudokuGUI(int[][] puzzle) {
		// Solve puzzle with solver
		this.puzzle = puzzle;
		this.solution = puzzle.clone();
		SudokuSolver solver = new SudokuSolver(this.solution);
		solution = solver.getPuzzle();
		
		//==================================== FRAME ====================================//
		sudokuFrame = new JFrame();
		sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sudokuFrame.setSize(600, 600);
		sudokuFrame.setLayout(new BorderLayout());
		sudokuFrame.setTitle("Sudoku");
		sudokuFrame.setResizable(false);
		
		//==================================== BOARD PANEL ====================================//
		sudokuBoard = new JPanel(new GridLayout(9,9));
		sudokuBoard.setSize(new Dimension(400, 400));
		sudokuSquareButtons = new SudokuSquare[9][9];
		selectedSquareLabel = new JLabel("No Selection");
		selectedSquare = new SudokuSquare(0, 0, 0, false);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int cellNumber = 0;
				boolean filled = false;
				if (puzzle[i][j] != 0) {
					cellNumber = puzzle[i][j];
					filled = true;
				}
				
				SudokuSquare sudokuSquare = new SudokuSquare(cellNumber, i+1, j+1, filled);
				
				if (filled == true) {
					sudokuSquare.setText(sudokuSquare.getNumber() + "");
					sudokuSquare.setForeground(new Color(100, 100, 100));
				}
				
				sudokuSquare.setOpaque(true);
				sudokuSquare.setPreferredSize(new Dimension(50,50));
				
				// Set background color of each Sudoku Square
				// For distinguishing between each 3x3 block
				if ((i<3 || i>5) && (j<3 || j>5) || (i>2 && i<6) && (j>2 && j<6))
					sudokuSquare.setBackground(Color.GRAY);
				else
					sudokuSquare.setBackground(Color.BLACK);
				
				sudokuSquare.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						prevSquare = selectedSquare;
						prevSquare.setBackground(prevColor);
						
						selectedSquare = sudokuSquare;
						prevColor = selectedSquare.getBackground();
						selectedSquare.setBackground(Color.RED);
						selectedSquareLabel.setText("Row: " + selectedSquare.getRow() + ", Column: " + selectedSquare.getColumn());
					}
					
				});
				sudokuSquareButtons[i][j] = sudokuSquare;
				sudokuBoard.add(sudokuSquareButtons[i][j]);
			}
		}
		
		//==================================== Buttons 1-9 =====================================//
		buttonPanel = new JPanel(new GridLayout(3, 4));
		buttonPanel.setBackground(new Color(255, 150, 150));
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < 9; i++) {
			JButton digButton = new JButton(Integer.toString(i+1));
			digButton.setPreferredSize(new Dimension(30,30));
			
			digButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectedSquare.getFilled() == false) {
						selectedSquare.setNumber(Integer.parseInt(digButton.getText()));
						
						// check validity
						// solver.isValidNumber(selectedSquare.getRow(), selectedSquare.getColumn());
						
						selectedSquare.setText("" + digButton.getText());
					}
				}
				
			});
			
			buttons.add(digButton);
			buttonPanel.add(buttons.get(i));
		}
		
		JButton clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(30,30));
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedSquare.getNumber() != 0 && selectedSquare.getFilled() == false) {
					selectedSquare.setNumber(0);
					selectedSquare.setText("");
				}
			}
			
		});
		buttons.add(clearButton);
		buttonPanel.add(clearButton);
		
		sudokuFrame.add(selectedSquareLabel, BorderLayout.NORTH);
		sudokuFrame.add(sudokuBoard, BorderLayout.CENTER);
		sudokuFrame.add(buttonPanel, BorderLayout.SOUTH);
		
		sudokuFrame.setVisible(true);
		sudokuBoard.setVisible(true);
		buttonPanel.setVisible(true);
	}
	
	public boolean compareToSolution() {
		if (puzzle.equals(solution))
			return true;
		
		return false;
	}
	
	public boolean gameWon() {
		if (compareToSolution() == true) {
			gameWon = true;
			return true;
		}
		
		return false;
	}
	
	public void uploadToDatabase() {
		
	}
}

