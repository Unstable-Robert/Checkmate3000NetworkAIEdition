package edu.mccc.cos210.fp2014.cm.menu;

//import sun.tools.jstat.Alignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Test class to mimic settings view
 */
public class TestSettingsView {
    private static boolean isTimed;
    private static final int TIME_MIN = 0;
	private static final int TIME_MAX = 180;
    public static void main(String[] sa){
        isTimed = false;
        JFrame jf = new JFrame("Settings");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 640);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setBackground(new Color(255, 123, 50));
        jf.setLayout(null);

        //backButton returns to previous screen
        JButton backButton = new JButton("Back");
        backButton.setSize(100,50);
        backButton.setLocation((int)(jf.getWidth() * 0.03),(int)(jf.getHeight() * 0.05));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("BackButton Clicked");
            }
        });
        jf.add(backButton);

        //startButton starts the game with given settings
        JButton startButton = new JButton("Start Game");
        startButton.setSize(100,50);
        startButton.setLocation((int)(jf.getWidth() * 0.78), (int)(jf.getHeight() * 0.05));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("StartButton Clicked");
            }
        });
        jf.add(startButton);

        //Checkbox whether game is timed or not
        JCheckBox checkbox = new JCheckBox();
        checkbox.setSize(100,40);
        checkbox.setLocation((int) (jf.getWidth() * 0.28), (int) (jf.getHeight() * 0.25));
        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isTimed){
                    isTimed = false;
                } else isTimed = true;
                System.out.println(isTimed);
            }
        });
        jf.add(checkbox);
        JLabel timedGame = new JLabel("Timed Game?");
        timedGame.setSize(100,40);
        timedGame.setLocation((int)(jf.getWidth() * 0.1),(int)(jf.getHeight() * 0.25));
        jf.add(timedGame);

        //if game is timed on is display timer settings
        JLabel timeLabel = new JLabel("Time");
        timeLabel.setSize(40,20);
        timeLabel.setLocation((int)(jf.getWidth() * 0.1),(int)(jf.getHeight() * 0.42));
        jf.add(timeLabel);
        JSpinner timeSpinner = new JSpinner(new SpinnerNumberModel(TIME_MIN, TIME_MIN, TIME_MAX, 1));
        timeSpinner.setSize(40,20);
        timeSpinner.setLocation((int)(jf.getWidth() * 0.18),(int)(jf.getHeight() * 0.42));
        jf.add(timeSpinner);
        JLabel minLabel = new JLabel("Minutes");
        minLabel.setSize(55,20);
        minLabel.setLocation((int)(jf.getWidth() * 0.3),(int)(jf.getHeight() * 0.42));
        jf.add(minLabel);

        //items to pick color
        JLabel color = new JLabel("Your Color: ");
        color.setSize(100,20);
        color.setLocation((int) (jf.getWidth() * 0.1), (int) (jf.getHeight() * 0.55));
        jf.add(color);
        ButtonGroup colorPickerButtons = new ButtonGroup();
        JRadioButton whiteRadio = new JRadioButton("White");
        whiteRadio.setLocation((int) (jf.getWidth() * 0.25), (int) (jf.getHeight() * 0.55));
        whiteRadio.setSize(70,20);
		whiteRadio.setSelected(true);
        colorPickerButtons.add(whiteRadio);
        jf.add(whiteRadio);
        JRadioButton blackRadio = new JRadioButton("Black");
        blackRadio.setLocation((int) (jf.getWidth() * 0.4), (int) (jf.getHeight() * 0.55));
        blackRadio.setSize(70,20);
        colorPickerButtons.add(blackRadio);
        jf.add(blackRadio);

        //displays your ip
        JLabel ipLabel = new JLabel("Your IP: " + "192.168.1.10");
        ipLabel.setSize(140,40);
        ipLabel.setLocation((int)((jf.getWidth() * 0.5) - ipLabel.getWidth()/2), (int) (jf.getHeight() * 0.75));
        jf.add(ipLabel);
        jf.setVisible(true);
    }
}
