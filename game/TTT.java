package game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {   //background that we see in this game is known a frame
	private int size = 3;                                    //to get that frame our class extends Jframe
	private boolean crossturn = true;
	private JButton[][] buttons = new JButton[size][size];

	private enum status {

		incomplete, xwon, owon, tie;
	}

	public TTT() {
		super.setTitle("tic tac toe");
		GridLayout layout = new GridLayout(size, size);
		this.setLayout(layout);
		super.setSize(800, 800);

		Font font = new Font("Comic Sans MS", 1, 150);  // first is font,second is for bold,and third for text size
		for (int i = 0; i < size; i++) {  
			for (int j = 0; j < size; j++) {
				JButton button = new JButton("");
				button.setFont(font);
				button.addActionListener(this);
				buttons[i][j] = button;
				super.add(button);
			}

			super.setVisible(true);
			super.setResizable(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		makemove(button);
		status gs = getstatus();

		if (gs == gs.incomplete) {
			return;
		}
		declarewinner(gs);
		int choice = JOptionPane.showConfirmDialog(this, "do u want to restart");
		if (choice == JOptionPane.YES_OPTION) {
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[0].length; j++) {
					buttons[i][j].setText("");
				}
			}
		} else {
			super.dispose();
		}
	}

	private void declarewinner(status gs) {
		if (gs == gs.xwon) {
			JOptionPane.showMessageDialog(this, "x won");
		}
		if (gs == gs.owon) {
			JOptionPane.showMessageDialog(this, "o won");
		}
		if (gs == gs.tie) {
			JOptionPane.showMessageDialog(this, "tie");
		}
	}

	private void makemove(JButton button) {
		if (button.getText().length() != 0) {
			JOptionPane.showMessageDialog(this, "invalid move");
		} else if (crossturn) {
			button.setText("X");
		} else {
			button.setText("O");
		}
		crossturn = !crossturn;
	}

	private status getstatus() {
		String text1 = "", text2 = "", text3 = "";
    
		for (int i = 0; i < buttons.length; i++) {
			text1 = buttons[i][0].getText();
			text2 = buttons[i][1].getText();
			text3 = buttons[i][2].getText();
			if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
				if (text1.equals("X"))
					return status.xwon;

				else
					return status.owon;
			}

		}

		for (int i = 0; i < buttons[0].length; i++) {
			text1 = buttons[0][i].getText();
			text2 = buttons[1][i].getText();
			text3 = buttons[2][i].getText();
			if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
				if (text1.equals("X"))
					return status.xwon;
				else
					return status.owon;
			}

		}

		text1 = buttons[0][0].getText();
		text2 = buttons[1][1].getText();
		text3 = buttons[2][2].getText();
		if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
			if (text1.equals("X"))
				return status.xwon;
			else
				return status.owon;
		}

		text1 = buttons[0][2].getText();
		text2 = buttons[1][1].getText();
		text3 = buttons[2][0].getText();
		if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
			if (text1.equals("X"))
				return status.xwon;
			else
				return status.owon;
		}

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				if (buttons[i][j].getText() =="") {
					return status.incomplete;
				}
			}
		}

		return status.tie;
	}

}
