package edu.mccc.cos210.fp2014.cm.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Runner class and main JFrame.
 */
public class Checkmate extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String MAIN_MENU = "main menu";
	public static final String LOCAL = "local";
	public static final String HOST = "host";
	public static final String JOIN = "join";
	private CardLayout cards;
	private JPanel cardPanel;
	private Checkmate() {
		super("Checkmate 3000 Network AI Edition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 640);
		setResizable(false);
		cards = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(cards);
		cardPanel.add(new MainMenuView(this), MAIN_MENU);
		cardPanel.add(new LocalView(this), LOCAL);
		cardPanel.add(new HostView(this), HOST);
		cardPanel.add(new JoinView(this), JOIN);
		add(cardPanel);
		cards.show(cardPanel, MAIN_MENU);
		setVisible(true);
	}
	public static void main(String[] sa) {
		new Checkmate();
	}
	/**
	 * Set what JPanel you want to see.
	 */
	public void setView(String v) {
		cards.show(cardPanel, v);
	}
}
