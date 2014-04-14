package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main menu.
 * This menu allows a user to chose what type of game they want to play.
 */
public class MainMenuView extends SettingsView implements ActionListener  {
	private static final long serialVersionUID = 1L;
	public MainMenuView(Checkmate c) {
		super(c);
	}
	/**
	 * Changes the view based on what button you've pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
