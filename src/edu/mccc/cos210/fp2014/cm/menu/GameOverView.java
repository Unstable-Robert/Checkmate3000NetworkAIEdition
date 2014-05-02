package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.io.FileWriter;

import javax.swing.*;

public class GameOverView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	public GameOverView(boolean whiteWon, Checkmate c, final String moves) {
		super(c);
		JLabel winnerLabel = new JLabel("");
		if(!whiteWon) {
			winnerLabel = new JLabel("White Wins!!!");
		} else {
			winnerLabel = new JLabel("Black Wins!!!");
		}
		winnerLabel.setForeground(Color.WHITE);
		winnerLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		winnerLabel.setSize(300, 50);
		winnerLabel.setLocation(
			c.getWidth() / 2 - winnerLabel.getWidth() / 2, 
			(int)(c.getHeight() * 0.20)
		);
		add(winnerLabel);	

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
        JButton smButton = new JButton("Save Log");
        smButton.setSize(150,50);
        smButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveWindow = new JFileChooser();
                saveWindow.showSaveDialog(GameOverView.this);
                try(FileWriter fw = new FileWriter(saveWindow.getSelectedFile()+".c3")) {
                    fw.write(moves);
                    fw.close();
                } catch (java.io.IOException a){
                    System.out.println(a);
                }
            }
        });
        smButton.setLocation(
                c.getWidth() / 2 - smButton.getWidth()/2,
                (int)(c.getHeight() * 0.85)
        );
        add(smButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}

