package edu.mccc.cos210.fp2014.cm.menu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Test if the The main menu.
 * This menu allows a user to chose what type of game they want to play.
 * Types of game:
 *    Local game, Host game, Join game
 *
 */
public class TestMainMenuView extends SettingsView implements ActionListener  {
	private static final long serialVersionUID = 1L;
	boolean isOpen;
	
	public TestMainMenuView(Checkmate c) {
		super(c);
	}
	public static void main(String[] sa){
		int bw = 200;
		int bh = 50; 
		JFrame jf = new JFrame("Chessmate 3000 Network AI Edition: Main Menu");
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      		jf.setSize(800, 640);
        	jf.setResizable(false);
        	jf.setLocationRelativeTo(null);
        	jf.setBackground(new Color(255, 123, 50));
        	jf.setLayout(null);
	
		 //Local Game button
        	JButton lgb = new JButton("Local Game");
       		lgb.setSize(bw,bh);
       		lgb.setLocation((int)(jf.getWidth() * 0.5 - bw/2),(int)(jf.getHeight() * 0.2 - bh/2));
        	lgb.addActionListener(new ActionListener() {
            		@Override
            		public void actionPerformed(ActionEvent ae) {
           			System.out.println("lgb Clicked");
          		}
       		});
        	jf.add(lgb);

		//host game button
		JButton hgb = new JButton("Host Game");
		hgb.setSize(bw,bh);
		hgb.setLocation((int)(jf.getWidth() * 0.5 - bw/2),(int)(jf.getHeight() *0.4 - bh/2));
		hgb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("hgb clicked");
			}
		});
		jf.add(hgb);

		//join game button
		JButton jgb = new JButton("Join Game");
		jgb.setSize(bw,bh);
		jgb.setLocation((int)(jf.getWidth() * 0.5 - bw/2),(int)(jf.getHeight() * 0.6 - bh/2));
		jgb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("jgb clicked");
			}
		});
		jf.add(jgb);

		//Quit game button
		JButton qgb = new JButton("Quit Game");
		qgb.setSize(bw,bh);
		qgb.setLocation((int)(jf.getWidth() * 0.5 - bw/2),(int)(jf.getHeight() * 0.8 - bh/2));
		qgb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("quit clicked");
			}
		});
		jf.add(qgb);
	}	
	@Override
			public void actionPerformed(ActionEvent ae) {
				
			}	
}
