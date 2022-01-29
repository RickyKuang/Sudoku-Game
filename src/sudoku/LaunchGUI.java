package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LaunchGUI {

	private JFrame launchFrame;
	private JLabel selectPuzzle;
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
		
		launchFrame = new JFrame("Launch Frame");
		launchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		launchFrame.setSize(600, 600);
		launchFrame.setLayout(new FlowLayout());
		launchFrame.setResizable(false);
		
		selectPuzzle = new JLabel("Select Puzzle");
		launchFrame.add(selectPuzzle);
		
		easy = new JButton("Easy");
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
				new SudokuGUI(easySudoku);
				launchFrame.setVisible(false);
			}
			
		});
		launchFrame.add(easy);
		
		medium = new JButton("Medium");
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
				new SudokuGUI(mediumSudoku);
				launchFrame.setVisible(false);
			}
			
		});
		launchFrame.add(medium);
		
		hard = new JButton("Hard");
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
//				{1, 2, 8, 5, 3, 7, 6, 9, 4},
//				{6, 3, 4, 8, 9, 2, 1, 5, 7},
//				{7, 9, 5, 4, 6, 1, 8, 3, 2},
//				{5, 1, 9, 2, 8, 6, 4, 7, 3},
//				{4, 7, 2, 3, 1, 9, 5, 6, 8},
//				{8, 6, 3, 7, 4, 5, 2, 1, 0}
			};
		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SudokuGUI(hardSudoku);
				launchFrame.setVisible(false);
			}
			
		});
		launchFrame.add(hard);
		
		launchFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new LaunchGUI();
	}
}
