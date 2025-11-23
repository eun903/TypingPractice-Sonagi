package taja;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[] args) {

		JFrame myFrame = new JFrame("자바 타자연습");
		myFrame.add(new Gui());
		myFrame.setSize(1000, 735);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

}