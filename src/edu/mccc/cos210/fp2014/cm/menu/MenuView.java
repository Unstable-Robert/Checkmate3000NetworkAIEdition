package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main menu. This allows a user to chose what type of game they want to play.
 * It's also the main point of entry for the application.
 */
public class MenuView extends SettingsView implements ActionListener  {

	private static final long serialVersionUID = 8818430694660983542L;

	public MenuView() {
		
	}
	
	/**
	 * Hey someone wanted to play.
	 */
	public static void main(String[] sa) {
		new MenuView();
	}
	/**
	 * Which game has been decided, it now prompts the appropriate submenu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
