package Game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class StarterFrame extends JFrame {
	JLabel title;
	JButton b1;
	JButton b2;
	JButton b3;
	JPanel pUpper;
	JPanel pLower;
	
	//Konstruktor
	public StarterFrame() {
		super("Kamisado");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(400, 500);		
		
		title = new JLabel("Kamisado");
		title.setSize(1000, 500);
		b1 = new JButton("Új játék");
		b2 = new JButton("Játék folytatása");
		b3 = new JButton("Kilépés");
		pUpper = new JPanel();
		pLower = new JPanel();
		pUpper.add(title, BorderLayout.CENTER);
		pLower.setLayout(new GridLayout(3,1));
		pLower.add(b1);
		pLower.add(b2);
		pLower.add(b3);
		this.add(pUpper, BorderLayout.NORTH);
		this.add(pLower, BorderLayout.CENTER);
		
		b1.addActionListener(new NewGameButtonActionListener());
		b2.addActionListener(new ContinueButtonActionListener());
		b3.addActionListener(new ExitButtonActionListener());
	}
	
	
	//Kilépés gomb
	public class ExitButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);			
		}
	}
	
	//Új játék gomb
	public class NewGameButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame gf = new GameFrame(true);
		}
	}
	
	//Játék folytatása gomb
	public class ContinueButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File f = new File("positions.txt");
			if(f.exists()) {
				if(f.length() == 0) {
					GameFrame gf = new GameFrame(true);
				} else {
					GameFrame gf = new GameFrame(false);
				}
			} else {
				GameFrame gf = new GameFrame(true);
			}
			
		}
		
	}
}
