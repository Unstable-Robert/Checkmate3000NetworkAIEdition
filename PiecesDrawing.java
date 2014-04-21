import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.geom.*;
import java.awt.Color;

public class PiecesDrawing extends JPanel {
	public static void main (String [ ] args) {
       	
	JFrame jf = new JFrame("Chess pieces");
	jf.setVisible(true);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	jf.setSize(800, 640);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);	

	JLabel brl = new JLabel("\u265C");
	brl.setFont(new Font(brl.getFont().toString(), Font.PLAIN, 60));	
	brl.setLocation((int)(jf.getWidth() * 0.1), (int)(jf.getHeight() * 0.0));
	brl.setSize(60, 60);
	jf.add(brl);

	JLabel bkl = new JLabel("\u265D");
	bkl.setFont(new Font(bkl.getFont().toString(), Font.PLAIN, 60));	
	bkl.setLocation((int)(jf.getWidth() * 0.2), (int)(jf.getHeight() * 0.0));
	bkl.setSize(60, 60);
	jf.add(bkl);
	
	JLabel bbl = new JLabel("\u265E");
	bbl.setFont(new Font(bbl.getFont().toString(), Font.PLAIN, 60));	
	bbl.setLocation((int)(jf.getWidth() * 0.3), (int)(jf.getHeight() * 0.0));
	bbl.setSize(60, 60);
	jf.add(bbl);
	
	JLabel bking = new JLabel("\u265A");
	bking.setFont(new Font(bking.getFont().toString(), Font.PLAIN, 60));	
	bking.setLocation((int)(jf.getWidth() * 0.5), (int)(jf.getHeight() * 0.0));
	bking.setSize(60, 60);
	jf.add(bking);
	
	JLabel bq = new JLabel("\u265B");
	bq.setFont(new Font(bq.getFont().toString(), Font.PLAIN, 60));	
	bq.setLocation((int)(jf.getWidth() * 0.4), (int)(jf.getHeight() * 0.0));
	bq.setSize(60, 60);
	jf.add(bq);	
	
	JLabel brr = new JLabel("\u265C");
	brr.setFont(new Font(brr.getFont().toString(), Font.PLAIN, 60));	
	brr.setLocation((int)(jf.getWidth() * 0.8), (int)(jf.getHeight() * 0.0));
	brr.setSize(60, 60);
	jf.add(brr);

	JLabel bkr = new JLabel("\u265D");
	bkr.setFont(new Font(bkr.getFont().toString(), Font.PLAIN, 60));	
	bkr.setLocation((int)(jf.getWidth() * 0.7), (int)(jf.getHeight() * 0.0));
	bkr.setSize(60, 60);
	jf.add(bkr);
	
	JLabel bbr = new JLabel("\u265E");
	bbr.setFont(new Font(bbr.getFont().toString(), Font.PLAIN, 60));	
	bbr.setLocation((int)(jf.getWidth() * 0.6), (int)(jf.getHeight() * 0.0));
	bbr.setSize(60, 60);
	jf.add(bbr);

	JLabel bp0 = new JLabel("\u265F");
	bp0.setFont(new Font(bp0.getFont().toString(), Font.PLAIN, 60));	
	bp0.setLocation((int)(jf.getWidth() * 0.1), (int)(jf.getHeight() * 0.1));
	bp0.setSize(60, 60);
	jf.add(bp0);
	
	JLabel bp1 = new JLabel("\u265F");
	bp1.setFont(new Font(bp1.getFont().toString(), Font.PLAIN, 60));	
	bp1.setLocation((int)(jf.getWidth() * 0.2), (int)(jf.getHeight() * 0.1));
	bp1.setSize(60, 60);
	jf.add(bp1);

	JLabel bp2 = new JLabel("\u265F");
	bp2.setFont(new Font(bp2.getFont().toString(), Font.PLAIN, 60));	
	bp2.setLocation((int)(jf.getWidth() * 0.3), (int)(jf.getHeight() * 0.1));
	bp2.setSize(60, 60);
	jf.add(bp2);

	JLabel bp3 = new JLabel("\u265F");
	bp3.setFont(new Font(bp3.getFont().toString(), Font.PLAIN, 60));	
	bp3.setLocation((int)(jf.getWidth() * 0.4), (int)(jf.getHeight() * 0.1));
	bp3.setSize(60, 60);
	jf.add(bp3);

	JLabel bp4 = new JLabel("\u265F");
	bp4.setFont(new Font(bp4.getFont().toString(), Font.PLAIN, 60));	
	bp4.setLocation((int)(jf.getWidth() * 0.5), (int)(jf.getHeight() * 0.1));
	bp4.setSize(60, 60);
	jf.add(bp4);
	
	JLabel bp5 = new JLabel("\u265F");
	bp5.setFont(new Font(bp5.getFont().toString(), Font.PLAIN, 60));	
	bp5.setLocation((int)(jf.getWidth() * 0.6), (int)(jf.getHeight() * 0.1));
	bp5.setSize(60, 60);
	jf.add(bp5);

	JLabel bp6 = new JLabel("\u265F");
	bp6.setFont(new Font(bp6.getFont().toString(), Font.PLAIN, 60));	
	bp6.setLocation((int)(jf.getWidth() * 0.7), (int)(jf.getHeight() * 0.1));
	bp6.setSize(60, 60);
	jf.add(bp6);

	JLabel bp7 = new JLabel("\u265F");
	bp7.setFont(new Font(bp7.getFont().toString(), Font.PLAIN, 60));	
	bp7.setLocation((int)(jf.getWidth() * 0.8), (int)(jf.getHeight() * 0.1));
	bp7.setSize(60, 60);
	jf.add(bp7);
	



	JLabel wp0 = new JLabel("\u2659");
	wp0.setFont(new Font(wp0.getFont().toString(), Font.PLAIN, 60));	
	wp0.setLocation((int)(jf.getWidth() * 0.1), (int)(jf.getHeight() * 0.7));
	wp0.setSize(60, 60);
	jf.add(wp0);
	
	JLabel wp1 = new JLabel("\u2659");
	wp1.setFont(new Font(wp1.getFont().toString(), Font.PLAIN, 60));	
	wp1.setLocation((int)(jf.getWidth() * 0.2), (int)(jf.getHeight() * 0.7));
	wp1.setSize(60, 60);
	jf.add(wp1);

	JLabel wp2 = new JLabel("\u2659");
	wp2.setFont(new Font(wp2.getFont().toString(), Font.PLAIN, 60));	
	wp2.setLocation((int)(jf.getWidth() * 0.3), (int)(jf.getHeight() * 0.7));
	wp2.setSize(60, 60);
	jf.add(wp2);

	JLabel wp3 = new JLabel("\u2659");
	wp3.setFont(new Font(wp3.getFont().toString(), Font.PLAIN, 60));	
	wp3.setLocation((int)(jf.getWidth() * 0.4), (int)(jf.getHeight() * 0.7));
	wp3.setSize(60, 60);
	jf.add(wp3);

	JLabel wp4 = new JLabel("\u2659");
	wp4.setFont(new Font(wp4.getFont().toString(), Font.PLAIN, 60));	
	wp4.setLocation((int)(jf.getWidth() * 0.5), (int)(jf.getHeight() * 0.7));
	wp4.setSize(60, 60);
	jf.add(wp4);
	
	JLabel wp5 = new JLabel("\u2659");
	wp5.setFont(new Font(wp5.getFont().toString(), Font.PLAIN, 60));	
	wp5.setLocation((int)(jf.getWidth() * 0.6), (int)(jf.getHeight() * 0.7));
	wp5.setSize(60, 60);
	jf.add(wp5);

	JLabel wp6 = new JLabel("\u2659");
	wp6.setFont(new Font(wp2.getFont().toString(), Font.PLAIN, 60));	
	wp6.setLocation((int)(jf.getWidth() * 0.7), (int)(jf.getHeight() * 0.7));
	wp6.setSize(60, 60);
	jf.add(wp6);

	JLabel wp7 = new JLabel("\u2659");
	wp7.setFont(new Font(wp7.getFont().toString(), Font.PLAIN, 60));	
	wp7.setLocation((int)(jf.getWidth() * 0.8), (int)(jf.getHeight() * 0.7));
	wp7.setSize(60, 60);
	jf.add(wp7);

	JLabel wrl = new JLabel("\u2656");
	wrl.setFont(new Font(wrl.getFont().toString(), Font.PLAIN, 60));	
	wrl.setLocation((int)(jf.getWidth() * 0.1), (int)(jf.getHeight() * 0.8));
	wrl.setSize(60, 60);
	jf.add(wrl);

	JLabel wkl = new JLabel("\u2658");
	wkl.setFont(new Font(wkl.getFont().toString(), Font.PLAIN, 60));	
	wkl.setLocation((int)(jf.getWidth() * 0.2), (int)(jf.getHeight() * 0.8));
	wkl.setSize(60, 60);
	jf.add(wkl);
	
	JLabel wbl = new JLabel("\u2657");
	wbl.setFont(new Font(wbl.getFont().toString(), Font.PLAIN, 60));	
	wbl.setLocation((int)(jf.getWidth() * 0.3), (int)(jf.getHeight() * 0.8));
	wbl.setSize(60, 60);
	jf.add(wbl);

	JLabel wql = new JLabel("\u2655");
	wql.setFont(new Font(wql.getFont().toString(), Font.PLAIN, 60));	
	wql.setLocation((int)(jf.getWidth() * 0.4), (int)(jf.getHeight() * 0.8));
	wql.setSize(60, 60);
	jf.add(wql);

	JLabel wking = new JLabel("\u2654");
	wking.setFont(new Font(wking.getFont().toString(), Font.PLAIN, 60));	
	wking.setLocation((int)(jf.getWidth() * 0.5), (int)(jf.getHeight() * 0.8));
	wking.setSize(60, 60);
	jf.add(wking);
	
	JLabel wrr = new JLabel("\u2656");
	wrr.setFont(new Font(wrr.getFont().toString(), Font.PLAIN, 60));	
	wrr.setLocation((int)(jf.getWidth() * 0.8), (int)(jf.getHeight() * 0.8));
	wrr.setSize(60, 60);
	jf.add(wrr);

	JLabel wkr = new JLabel("\u2658");
	wkr.setFont(new Font(wkr.getFont().toString(), Font.PLAIN, 60));	
	wkr.setLocation((int)(jf.getWidth() * 0.7), (int)(jf.getHeight() * 0.8));
	wkr.setSize(60, 60);
	jf.add(wkr);
	
	JLabel wbr = new JLabel("\u2657");
	wbr.setFont(new Font(wbr.getFont().toString(), Font.PLAIN, 60));	
	wbr.setLocation((int)(jf.getWidth() * 0.6), (int)(jf.getHeight() * 0.8));
	wbr.setSize(60, 60);
	jf.add(wbr); 
	
	jf.repaint();

	}	
}