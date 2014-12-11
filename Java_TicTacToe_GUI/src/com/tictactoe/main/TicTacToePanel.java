package com.tictactoe.main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TicTacToePanel extends JPanel {
	
	/**
	 * This is an application on TicTacToe. There are two sides of players.
	 * One is the O and the other one is the X. The result is based on which
	 * player is able to cover any of the whole row or column or diagonal in
	 * the 3*3 grid.
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 400;
	private static final int TTTHEIGHT = 200;
	private static final int TEXTHEIGHT = 50;
	
	private JPanel primary;
	private JPanel ttt;
	private JPanel text;
	private JButton[] buttons;
	private ButtonCheck[] buttonCheck;
	private JLabel label;
	
	private boolean checkOorX;
	private boolean end;
	private int count;
	
	public TicTacToePanel() {
		this.setSize(WIDTH, TTTHEIGHT + TEXTHEIGHT);
		creatPrimary();
		this.add(primary);
	}
	
	private void creatPrimary() {
		primary = new JPanel();
		primary.setSize(WIDTH, TTTHEIGHT + TEXTHEIGHT);
		primary.setLayout (new BoxLayout (primary, BoxLayout.Y_AXIS));
		ttt = creatTTT();
		text = creatText();
		primary.add(ttt);
		primary.add(text);
		checkOorX = true;
		end = false;
		label.setText("Place an O on an empty square");
	}
	
	private JPanel creatTTT() {
		ttt = new JPanel();
		ttt.setSize(WIDTH, TTTHEIGHT);
		ttt.setLayout (new GridLayout (3, 3));
		buttons = new JButton[9];
		buttonCheck = new ButtonCheck[9];
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttonCheck[i] = new ButtonCheck();
			buttons[i].addActionListener(buttonCheck[i]);
			ttt.add(buttons[i]);
		}
		return ttt;
	}
	
	private JPanel creatText() {
		text = new JPanel();
		text.setSize(WIDTH, TEXTHEIGHT);
		text.setBackground(Color.yellow);
		label = new JLabel();
		label.setSize(WIDTH, TEXTHEIGHT);
		text.add(label);
		return text;
	}
	
	private String buttonGetText(int box) {
		return buttons[box].getText();
	}
	
	private boolean isWin() {
		return
		((buttonGetText(0) != "" && buttonGetText(4) != "" && buttonGetText(8) != "" && buttonGetText(0).equals(buttonGetText(4)) && buttonGetText(4).equals(buttonGetText(8)))
		|| (buttonGetText(2) != "" && buttonGetText(4) != "" && buttonGetText(6) != "" && buttonGetText(2).equals(buttonGetText(4)) && buttonGetText(4).equals(buttonGetText(6)))
		|| (buttonGetText(0) != "" && buttonGetText(3) != "" && buttonGetText(6) != "" && buttonGetText(0).equals(buttonGetText(3)) && buttonGetText(3).equals(buttonGetText(6)))
		|| (buttonGetText(1) != "" && buttonGetText(4) != "" && buttonGetText(7) != "" && buttonGetText(1).equals(buttonGetText(4)) && buttonGetText(4).equals(buttonGetText(7)))
		|| (buttonGetText(2) != "" && buttonGetText(5) != "" && buttonGetText(8) != "" && buttonGetText(2).equals(buttonGetText(5)) && buttonGetText(5).equals(buttonGetText(8)))
		|| (buttonGetText(0) != "" && buttonGetText(1) != "" && buttonGetText(2) != "" && buttonGetText(0).equals(buttonGetText(1)) && buttonGetText(1).equals(buttonGetText(2)))
		|| (buttonGetText(3) != "" && buttonGetText(4) != "" && buttonGetText(5) != "" && buttonGetText(3).equals(buttonGetText(4)) && buttonGetText(4).equals(buttonGetText(5)))
		|| (buttonGetText(6) != "" && buttonGetText(7) != "" && buttonGetText(8) != "" && buttonGetText(6).equals(buttonGetText(7)) && buttonGetText(7).equals(buttonGetText(8)))) 
		? true : false;
	}
	
	private class ButtonCheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JButton checked = (JButton)(e.getSource());
			String result = checked.getText();
			
			if (end) {
				return;
			}
			
			if (result.equals("X") || result.equals("O")) {
				label.setText("That square is already used");
				return;
			}
			
			if (checkOorX) {
				checked.setText("O");
				count++;
			}
			else {
				checked.setText("X");
				count++;
			}
			
			if (isWin()) {
				end = true;
				if (checkOorX) {
					label.setText("O is the winner!");
				}
				else {
					label.setText("X is the winner!");
				}
			}
			else {
				checkOorX = !checkOorX;
				if (checkOorX) {
					label.setText("Place an O on an empty square");
				}
				else {
					label.setText("Place an X on an empty square");
				}
			}
			
			if (!isWin() && count == 9) {
				end = true;
				label.setText("No winner; the game is a draw!");
				return;
			}
		}
	}
}
