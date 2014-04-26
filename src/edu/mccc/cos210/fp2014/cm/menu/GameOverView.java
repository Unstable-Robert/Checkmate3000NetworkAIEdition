package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameOverView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	public GameOverView(Checkmate c) {
		super(c);
		boolean isWhiteTurn = c.getGameModel().getBoard().isWhiteTurn();
 	System.out.println(isWhiteTurn);
		JLabel WinnerLabel = new JLabel("");
		if(!isWhiteTurn) {
			WinnerLabel = new JLabel("White Wins!!!");
		} else {
			WinnerLabel = new JLabel("Black Wins!!!");
		}
		WinnerLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		WinnerLabel.setSize(300, 50);
		WinnerLabel.setLocation(
			c.getWidth() / 2 - WinnerLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.20)
		);
		add(WinnerLabel);	

		JButton mmButton = new JButton("Main Menu");
		mmButton.setSize(150,50);
		mmButton.setLocation(
			c.getWidth() / 2 - mmButton.getWidth() / 2, 
			(int)(c.getHeight() * 0.75)
		);
		mmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myCheckmate.setView(Checkmate.MAIN_MENU);
			}
		});
		add(mmButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}

