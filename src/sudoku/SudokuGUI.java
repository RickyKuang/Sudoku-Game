package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 * Sudoku Board GUI. Launched through the LaunchGUI after selecting puzzle difficulty. Data uploaded to SQL database after game is won.
 * Plan(s): allow user to view leaderboard
 * 
 * @author Ricky Kuang
 * @version 1.0
 */
public class SudokuGUI 
{
	// Frame and Board
	private JFrame sudokuFrame;
	private JPanel sudokuBoard;
	
	// Sudoku Square
	private SudokuSquare[][] sudokuSquareButtons;
	private JLabel labelDisplay;
	private SudokuSquare selectedSquare;
	private SudokuSquare prevSquare;
	private Color prevColor;
	
	// Digit Buttons
	private JPanel buttonPanel;
	private ArrayList<JButton> buttons;
	
	// Other
	private SudokuSolver solver;
	private int[][] puzzle;
	private int[][] solution;
	private int userID;
	private int puzzleID;
//	private JFrame viewLeaderboard;
//	private JLabel leaderboardLabel;
	
	/**
	 * Constructor for GUI of Sudoku board.
	 * 
	 * @param puzzle Creates board based on puzzle in the parameter.
	 */
	public SudokuGUI(int[][] board, int puzzleID, int userID) {
		// Solve puzzle with solver
		this.puzzle = board;
		solver = new SudokuSolver(board);
		solution = solver.copyPuzzle();
		
		SudokuSolver solSolver = new SudokuSolver(solution);
		solSolver.solvePuzzle(solution);
		
		this.userID = userID;
		this.puzzleID = puzzleID;
		//==================================== FRAME ====================================//
		sudokuFrame = new JFrame();
		sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sudokuFrame.setSize(600, 600);
		sudokuFrame.setLayout(new BorderLayout());
		sudokuFrame.setTitle("Sudoku");
		sudokuFrame.setResizable(false);
		sudokuFrame.setLocationRelativeTo(null);
		
		//==================================== BOARD PANEL ====================================//
		sudokuBoard = new JPanel(new GridLayout(9,9));
		sudokuBoard.setSize(new Dimension(400, 400));
		sudokuSquareButtons = new SudokuSquare[9][9];
		labelDisplay = new JLabel("Select a Square");
		labelDisplay.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		labelDisplay.setHorizontalAlignment(JLabel.CENTER);
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
				sudokuSquare.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
				
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
						labelDisplay.setText("Row: " + selectedSquare.getRow() + ", Column: " + selectedSquare.getColumn());
					}
					
				});
				sudokuSquareButtons[i][j] = sudokuSquare;
				sudokuBoard.add(sudokuSquareButtons[i][j]);
			}
		}
		
		//==================================== Buttons 1-9 =====================================//
		buttonPanel = new JPanel(new GridLayout(5, 2));
		buttonPanel.setBackground(new Color(255, 150, 150));
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < 9; i++) {
			JButton digButton = new JButton(Integer.toString(i+1));
			digButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			digButton.setPreferredSize(new Dimension(30,30));
			
			digButton.addActionListener(new ActionListener() {

				/**
				 * Method that inserts a number b/w 1 and 9 in the selected square if the value is valid.
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectedSquare.getFilled() == false) {
						solver.setPuzzleSquare(puzzle, selectedSquare.getRow()-1, selectedSquare.getColumn()-1, Integer.parseInt(digButton.getText()));
						
						// check validity
						if (!solver.isValidNumber(puzzle, selectedSquare.getRow()-1, selectedSquare.getColumn()-1)) {
							labelDisplay.setText("CONFLICT with: Row " + (solver.getInvalidRow()+1) + ", Column " + (solver.getInvalidCol()+1));
							
							solver.setPuzzleSquare(puzzle, selectedSquare.getRow()-1, selectedSquare.getColumn()-1, 0);
						}
						
						else {
							selectedSquare.setNumber(Integer.parseInt(digButton.getText()));
							selectedSquare.setText("" + digButton.getText());
							solution[selectedSquare.getRow()-1][selectedSquare.getColumn()-1] = selectedSquare.getNumber();
							
							if (gameWon()) {
								labelDisplay.setText("GAME WON. THANKS FOR PLAYING");
								
//								viewLeaderboard = new JFrame();
//								viewLeaderboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//								viewLeaderboard.setSize(600, 600);
//								viewLeaderboard.setTitle("Leaderboard");
//								viewLeaderboard.setResizable(false);
//								viewLeaderboard.setLocationRelativeTo(null);
//								
//								leaderboardLabel.setText(retrieveLeaderboard());
//								
//								viewLeaderboard.add(leaderboardLabel);
//								viewLeaderboard.setVisible(true);
							}
						}
					}
				}
				
			});
			
			buttons.add(digButton);
			buttonPanel.add(buttons.get(i));
		}
		
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Comic San MS", Font.PLAIN, 20));
		clearButton.setPreferredSize(new Dimension(30,30));
		clearButton.addActionListener(new ActionListener() {

			/**
			 * Method that clears the selected square on the board.
			 */
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
		
//		leaderboardLabel = new JLabel("Leaderboard");
//		leaderboardLabel.setBounds(150, 100, 200, 100);
		
		sudokuFrame.add(labelDisplay, BorderLayout.NORTH);
		sudokuFrame.add(sudokuBoard, BorderLayout.CENTER);
		sudokuFrame.add(buttonPanel, BorderLayout.SOUTH);
		
		sudokuFrame.setVisible(true);
		sudokuBoard.setVisible(true);
		buttonPanel.setVisible(true);
	}
	
	/**
	 * Compares the puzzle process with the solution puzzle.
	 * 
	 * @return true if puzzle matches solution, false otherwise
	 */
	public boolean compareToSolution() {
		if (solver.puzzleEquals(puzzle, solution))
			return true;
		
		return false;
	}
	
	/**
	 * Determines whether the game has been won or not. Won if puzzle matches solution.
	 * 
	 * @return true if game has been won, false otherwise
	 */
	public boolean gameWon() {
		if (compareToSolution() == true) {
			uploadToDatabase();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Uploads data such as puzzleID, puzzle difficulty, time spent, etc. to MySQL database
	 */
	public void uploadToDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SUDOKU", "root", "RK10mysqlroot!");
			Statement stmt = con.createStatement();
			
			String sql = "INSERT INTO RESULTS (userID, puzzleID) VALUES (" + userID + ", " + puzzleID + ")";
			stmt.executeUpdate(sql);
			con.close();
		} catch (Exception exception) {
			labelDisplay.setText(exception.getMessage());
		}
	}
	
//	public String retrieveLeaderboard() {
//		String leaderboardStr = "";
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
//	        Statement stmt = con.createStatement();
//			
//			String sql = "SELECT * FROM RESULTS";
//			
//			ResultSet rs = stmt.executeQuery(sql);
//	        leaderboardStr = "<html>Leaderboard<br>"
//	           		+ "(ID, Puzzle, Difficulty, Time)<br>"
//	           		+ "--------------------------<br>";
//	        while (rs.next()) {
//	        	   leaderboardStr += rs.getInt(1) + " | " + rs.getInt(2) + " | " + rs.getString(3) + " | " + rs.getTimestamp(4) + "<br>";
//	        }
//			
//			con.close();
//		} catch (Exception exception) {
//			exception.getMessage();
//		}
//		
//		return leaderboardStr + "</html>";
//	}
}

