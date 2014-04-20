package edu.mccc.cos210.fp2014.cm.menu;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Runner class and main JFrame.
 */
public class Checkmate extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel currentView = null;
	private Checkmate() {
		super("Checkmate 3000 Network AI Edition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 640);
		setResizable(false);
		setView(new MainMenuView(this));
		setVisible(true);
	}
	public static void main(String[] sa) {
		new Checkmate();
	}
	/**
	 * Return the JPanel that is currently being viewed.
	 */
	public JPanel getView() {
		return currentView;
	}
	/**
	 * Set what JPanel you want to see.
	 */
	public void setView(JPanel jp) {
		if (currentView != null) {
			this.remove(currentView);
		}
		this.add(jp);
		currentView = jp;
	}
}
