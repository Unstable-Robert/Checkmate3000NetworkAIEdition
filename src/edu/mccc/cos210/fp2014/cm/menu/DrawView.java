package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DrawView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	public DrawView(Checkmate c) {
		super(c);
		JLabel jl = new JLabel("The game ends in a draw!");
		jl.setForeground(Color.WHITE);
		jl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		jl.setSize(500, 50);
		jl.setLocation(
			c.getWidth() / 2 - jl.getWidth() / 2, 
			(int)(c.getHeight() * 0.20)
		);
		add(jl);	

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

