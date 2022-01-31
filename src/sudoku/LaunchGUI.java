package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LaunchGUI {

	private JFrame launchFrame;
	private JLabel selectPuzzle;
	private JPanel enterNamePanel;
	private JLabel enterNameLabel;
	private JTextField enterNameField;
	private JButton submitNameButton;
	private String userName;
	private int userID;
	private JButton easy;
	private JButton medium;
	private JButton hard;
	
	/**
	 * Constructor for the GUI Launcher of Sudoku
	 * Allows the user to select the difficulty of the Sudoku puzzle (easy, medium, hard)
	 * Launches Sudoku Board GUI after selecting difficulty
	 */
	public LaunchGUI() {
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//		  
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//		  
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//		  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		
		launchFrame = new JFrame("Sudoku Launcher");
		launchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		launchFrame.setSize(550, 300);
		launchFrame.setLayout(new BorderLayout());
		launchFrame.setResizable(false);
		launchFrame.setLocationRelativeTo(null);
		
		selectPuzzle = new JLabel("Select Puzzle");
		selectPuzzle.setHorizontalAlignment(JLabel.CENTER);
		selectPuzzle.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		launchFrame.add(selectPuzzle, BorderLayout.NORTH);
		
		easy = new JButton("Easy");
		easy.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		int[][] easySudoku = {
				  { 0, 0, 4, 0, 5, 0, 0, 0, 0 },
				  { 9, 0, 0, 7, 3, 4, 6, 0, 0 },
				  { 0, 0, 3, 0, 2, 1, 0, 4, 9 },
				  
				  { 0, 3, 5, 0, 9, 0, 4, 8, 0 },
				  { 0, 9, 0, 0, 0, 0, 0, 3, 0 },
				  { 0, 7, 6, 0, 1, 0, 9, 2, 0 },
				  
				  { 3, 1, 0, 9, 7, 0, 2, 0, 0 },
				  { 0, 0, 9, 1, 8, 2, 0, 0, 3 },
				  { 0, 0, 0, 0, 6, 0, 1, 0, 0 }
		};
		easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SUDOKU", "root", "RK10mysqlroot!");
					Statement stmt = con.createStatement();
					
					String sql = "select userID from user where userName = '" + userName + "'";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next())
						userID = rs.getInt(1);
					
					selectPuzzle.setText(String.valueOf(userID));
					con.close();
				} catch (Exception exception) {
					exception.getMessage();
				}
				new SudokuGUI(easySudoku, 1, userID);
				launchFrame.setVisible(false);
			}
			
		});
		launchFrame.add(easy, BorderLayout.WEST);
		
		medium = new JButton("Medium");
		medium.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
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
		medium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SUDOKU", "root", "RK10mysqlroot!");
					Statement stmt = con.createStatement();
					
					String sql = "select userID from user where userName = '" + userName + "'";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next())
						userID = rs.getInt(1);
					
					selectPuzzle.setText(String.valueOf(userID));
					con.close();
				} catch (Exception exception) {
					exception.getMessage();
				}
				new SudokuGUI(mediumSudoku, 2, userID);
				launchFrame.setVisible(false);
			}
			
		});
		launchFrame.add(medium, BorderLayout.CENTER);
		
		hard = new JButton("Hard");
		hard.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		int[][] hardSudoku = {
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 3, 0, 8, 5 },
				  { 0, 0, 1, 0, 2, 0, 0, 0, 0 },
				  
				  { 0, 0, 0, 5, 0, 7, 0, 0, 0 },
				  { 0, 0, 4, 0, 0, 0, 1, 0, 0 },
				  { 0, 9, 0, 0, 0, 0, 0, 0, 0 },
				  
				  { 5, 0, 0, 0, 0, 0, 0, 7, 3 },
				  { 0, 0, 2, 0, 1, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 4, 0, 0, 0, 9 }
//				{9, 8, 7, 6, 5, 4, 3, 2, 1}, 
//				{2, 4, 6, 1, 7, 3, 9, 8, 5},
//				{3, 5, 1, 9, 2, 8, 7, 4, 6},
//				
//				{1, 2, 8, 5, 3, 7, 6, 9, 4},
//				{6, 3, 4, 8, 9, 2, 1, 5, 7},
//				{7, 9, 5, 4, 6, 1, 8, 3, 2},
//				
//				{5, 1, 9, 2, 8, 6, 4, 7, 3},
//				{4, 7, 2, 3, 1, 9, 5, 6, 8},
//				{8, 6, 3, 7, 4, 5, 2, 1, 0}
			};
		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SUDOKU", "root", "RK10mysqlroot!");
					Statement stmt = con.createStatement();
					
					String sql = "select userID from user where userName = '" + userName + "'";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next())
						userID = rs.getInt(1);
					
					selectPuzzle.setText(String.valueOf(userID));
					con.close();
				} catch (Exception exception) {
					exception.getMessage();
				}
				new SudokuGUI(hardSudoku, 3, userID);
				launchFrame.setVisible(false);
			}
			
		});
		launchFrame.add(hard, BorderLayout.EAST);
		
		enterNamePanel = new JPanel(new FlowLayout());
		enterNameLabel = new JLabel("Enter name");
		enterNamePanel.add(enterNameLabel);
		
		enterNameField = new JTextField(20);
		enterNameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		enterNamePanel.add(enterNameField);
		
		submitNameButton = new JButton("Submit");
		submitNameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userName = enterNameField.getText();
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SUDOKU", "root", "RK10mysqlroot!");
					Statement stmt = con.createStatement();
					
					String sql = "insert into user (userName, puzzlesSolved) values ('" + userName + "', 0);";
					stmt.executeUpdate(sql);
					con.close();
				} catch (Exception exception) {
					exception.getMessage();
				}
			}
			
		});
		enterNamePanel.add(submitNameButton);
		launchFrame.add(enterNamePanel, BorderLayout.SOUTH);
		
		launchFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new LaunchGUI();
	}
}
