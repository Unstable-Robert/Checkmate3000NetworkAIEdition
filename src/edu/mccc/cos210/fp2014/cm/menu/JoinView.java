package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Join network game menu.
 * Menu allows user to join a local game.
 */
public class JoinView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	public JoinView(Checkmate c) {
		super(c);
	}
	/**
	 * Allows user to enter the host's IP and return to the main menu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
