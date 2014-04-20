package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Local game menu.
 */
public class LocalView extends SettingsView implements ActionListener {
	private static final long serialVersionUID = 1L;
	public LocalView(Checkmate c) {
		super(c);
		
		JButton backButton = new JButton("Back");
		backButton.setSize(150, 50);
		backButton.setLocation((int)(c.getWidth() * 0.45), (int)(c.getHeight() * 0.45));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myCheckmate.setView(Checkmate.MAIN_MENU);
			}
		});
		add(backButton);
	}
	/**
	 * Changes settings and allows user to start the game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
