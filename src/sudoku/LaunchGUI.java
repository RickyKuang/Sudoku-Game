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
