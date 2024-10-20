package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;

import javax.swing.*;

public class GameFrame extends JFrame implements Serializable {

	boolean newGame;

	private boolean patt1;
	private boolean patt2;
	
	JPanel header;
	JPanel gameBoard;
	JLabel activePlayer;
	JLabel message;
	JButton saveButton;
	JButton ready;
	private JButton uj;
	private JButton[][] fields;
	private ArrayList<JButton> userFields;
	private ArrayList<JButton> robotFields; 
	private JButton bAktualis;
	private ArrayList<JButton> activeButtons;
	
	
	//Konstruktor
	public GameFrame(boolean newGame) {
		super("Kamisado");
		this.newGame = newGame;
		patt1 = false;
		patt2 = false;
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setResizable(false);
		
		header = new JPanel();
		gameBoard = new JPanel();
		
		activePlayer = new JLabel("Te következel!");
		message = new JLabel("");
		saveButton = new JButton("Játékállás mentése");
		saveButton.addActionListener(new SaveButtonActionListener());
		ready = new JButton("Léptem!");
	
		header.add(activePlayer, BorderLayout.WEST);
		header.add(message, BorderLayout.CENTER);
		header.add(saveButton, BorderLayout.EAST);
		header.add(ready, BorderLayout.EAST);
		
		this.add(header, BorderLayout.NORTH);
		
		gameBoard.setLayout(new GridLayout(8,8));
		
		activeButtons = new ArrayList<>();
		userFields = new ArrayList<>();
		robotFields = new ArrayList<>();
		
		fields = new JButton[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				JButton b = new JButton();
				fields[i][j] = b;
				
				if(i == j) {
					b.setBackground(Color.orange);
				}
				if(i + j == 7) {
					b.setBackground(Color.red);
				}
				if(i + j == 3 || i + j == 11) {
					b.setBackground(Color.yellow);
				}
				if(Math.abs(i - j) == 4) {
					b.setBackground(Color.pink);
				}
				if(i == 0 && j == 1 || i == 7 && j == 6) {
					b.setBackground(Color.green);
				}
				if(i == 1 && j == 0 || i == 6 && j == 7) {
					b.setBackground(Color.magenta);
				}
				if(i + j == 5) {
					if(i % 2 == 0) {
						b.setBackground(Color.magenta);
					} else {
						b.setBackground(Color.green);
					}
				}
				if(i + j == 9) {
					if(i % 2 == 1) {
						b.setBackground(Color.magenta);
					} else {
						b.setBackground(Color.green);
					}
				}
				if(i == 7 && j == 1 || i == 0 && j == 6) {
					b.setBackground(Color.blue);
				}
				if(i == 1 && j == 7 || i == 6 && j == 0) {
					b.setBackground(Color.lightGray);
				}
				if(j - i == 2) {
					if (i % 2 == 0) {
						b.setBackground(Color.lightGray);
					} else {
						b.setBackground(Color.blue);
					}
				}
				if(i - j == 2) {
					if(i % 2 == 1) {
						b.setBackground(Color.lightGray);
					} else {
						b.setBackground(Color.blue);
					}
				}
				gameBoard.add(b);
			}
		}
		
		this.add(gameBoard, BorderLayout.CENTER);
			
		
		//Ha új játékot kezdtünk
		if(newGame) {
			fields[0][7].setIcon(new OvalButtonIcon(Color.red));
			fields[0][6].setIcon(new OvalButtonIcon(Color.blue));
			fields[0][5].setIcon(new OvalButtonIcon(Color.magenta));
			fields[0][4].setIcon(new OvalButtonIcon(Color.pink));
			fields[0][3].setIcon(new OvalButtonIcon(Color.yellow));
			fields[0][2].setIcon(new OvalButtonIcon(Color.lightGray));
			fields[0][1].setIcon(new OvalButtonIcon(Color.green));
			fields[0][0].setIcon(new OvalButtonIcon(Color.orange));
			fields[7][0].setIcon(new RectButtonIcon(Color.red));
			fields[7][1].setIcon(new RectButtonIcon(Color.blue));
			fields[7][2].setIcon(new RectButtonIcon(Color.magenta));
			fields[7][3].setIcon(new RectButtonIcon(Color.pink));
			fields[7][4].setIcon(new RectButtonIcon(Color.yellow));
			fields[7][5].setIcon(new RectButtonIcon(Color.lightGray));
			fields[7][6].setIcon(new RectButtonIcon(Color.green));
			fields[7][7].setIcon(new RectButtonIcon(Color.orange));
			
			for(int i = 0; i < 8; i++) {
				fields[7][i].addActionListener(new StartingButtonActionListener());
				userFields.add(fields[7][i]);
				robotFields.add(fields[0][i]);
			}
			
			message.setText("Kattints a bábura, amivel kezdeni szeretnél.");
			
		} 
		
		//Ha eddigi játékot folytatunk
		else {
			try {
				FileInputStream f = new FileInputStream("positions.txt");
				ObjectInputStream in = new ObjectInputStream(f);
				GameFrame gf = (GameFrame)in.readObject();
				in.close();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						fields[i][j].setIcon(gf.fields[i][j].getIcon());
					}
				}
				int l = gf.bIndex_i(gf.getButton());
				int c = gf.bIndex_j(gf.getButton());
				bAktualis = fields[l][c];
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(gf.getActiveButtons().contains(gf.fields[i][j])) {
							activeButtons.add(fields[i][j]);
							fields[i][j].addActionListener(new FieldButtonActionListener());
						}
						if(gf.getUserFields().contains(gf.getFields()[i][j])) {
							userFields.add(fields[i][j]);
						}
						if(gf.getRobotFields().contains(gf.getFields()[i][j])) {
							robotFields.add(fields[i][j]);
						}
					}
				}
				
				MyColor col = new MyColor(bAktualis.getBackground());
				activePlayer.setText("Te következel! A lépő bábud színe: " + col.toString());
				
			} catch(IOException ex) {
				ex.printStackTrace();
			} catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void setNewGame(boolean b) {
		newGame = b;
	}
	
	public ArrayList<JButton> getActiveButtons() {
		return activeButtons;
	}
	
	public ArrayList<JButton> getUserFields() {
		ArrayList<JButton> result = new ArrayList<>();
		result = (ArrayList<JButton>) userFields.clone();
		return result;
	}
	
	public ArrayList<JButton> getRobotFields() {
		return robotFields;
	}
	
	public JButton getButton() {
		return bAktualis;
	}
	
	public JButton[][] getFields() {
		JButton[][] result = new JButton[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				result[i][j] = fields[i][j];
			}
		}
		return result;
	}
	
	public JButton getUj() {
		return uj;
	}
	
	public void setFields(JButton[][] fields) {
		this.fields = fields;
	}
	
	public void setBAktualis(JButton b) {
		bAktualis = b;
	}
	
	public int bIndex_i(JButton b) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(b == fields[i][j]) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public int bIndex_j(JButton b) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(b == fields[i][j]) {
					return j;
				}
			}
		}
		return -1;
	}
	
	//Játékállás mentése
	public class SaveButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				FileOutputStream f = new FileOutputStream("positions.txt");
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(GameFrame.this);
				out.close();
				message.setText("Játékállás elmentve");
				}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//Lehetséges kezdőbábuk gombjai
	public class StartingButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton pressed = (JButton)e.getSource();
			bAktualis = pressed;
			for(int i = 0; i < 8; i++) {
				removeButtonActionListener(fields[7][i]);
			}
			int i = bIndex_i(bAktualis);
			int j = bIndex_j(bAktualis);
			for(int x = 1; x < 7; x++) {
				for(int y = 0; y < 8; y++) {
					if(y == j || (x - y) == (i - j) || (x + y) == (i + j)) {
						activeButtons.add(fields[x][y]);
						fields[x][y].addActionListener(new FieldButtonActionListener());
					}
				}
			}
			MyColor c = new MyColor(bAktualis.getBackground());
			activePlayer.setText("Te következel! A lépő bábud színe: " + c.toString());
			message.setText(null);
		}
	}
	
	//Mező gombja, melyre a játékos lépni szeretne
	public class FieldButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			header.remove(saveButton);
			message.setText(null);
			
			JButton pressed = (JButton)e.getSource();
			uj = pressed;
			uj.setIcon(bAktualis.getIcon());
			bAktualis.setIcon(null);
			
			ready.addActionListener(new ReadyButtonActionListener());
			for(int i = 0; i < activeButtons.size(); i++) {
				removeButtonActionListener(activeButtons.get(i));
			}
		}
	}
	
	//"Léptem!" gomb
	public class ReadyButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			removeButtonActionListener(ready);
			message.setText(null);
			
			for(int i = 0; i < activeButtons.size(); i++) {
				removeButtonActionListener(activeButtons.get(i));
			}
			
			userFields.remove(bAktualis);
			userFields.add(uj);
			
			//Győzelem
			for(int i = 0; i < 8; i++) {
				if(userFields.contains(fields[0][i])) {
					activePlayer.setText(null);
					message.setText("Győztél!");
					return;
				}
			}
			
			Color colorUj = uj.getBackground();
			OvalButtonIcon lepoIconRobot = null;
			for(int i = 0; i < 8; i++) {
				lepoIconRobot = (OvalButtonIcon)robotFields.get(i).getIcon();
				if(lepoIconRobot.getColor().equals(colorUj)) {
					bAktualis = robotFields.get(i);
					break;
				}
			}
			
			activeButtons.clear();
			
			int i = bIndex_i(bAktualis);
			int j = bIndex_j(bAktualis);
			for(int x = i + 1; x < 8; x++) {
				if(fields[x][j].getIcon() == null) {
					activeButtons.add(fields[x][j]);
				} else {
					break;
				}
			}
			boolean endCycle = false;
			for(int x = i + 1; x < 8; x++) {
				for(int y = 0; y < 8; y++) {
					if((x-y) == (i-j)) {
						if(fields[x][y].getIcon() == null) {
							activeButtons.add(fields[x][y]);
						} else {
							endCycle = true;
							break;
						}
					}
				}
				if(endCycle) {
					break;
				}
			}
			
			endCycle = false;
			for(int x = i + 1; x < 8; x++) {
				for(int y = 0; y < 8; y++) {
					if((x+y) == (i+j)) {
						if(fields[x][y].getIcon() == null) {
							activeButtons.add(fields[x][y]);
						} else {
							endCycle = true;
							break;
						}
					}
				}
				if(endCycle) {
					break;
				}
			}
			
			if(activeButtons.isEmpty()) {
				patt1 = true;
				if(patt1 && patt2) {
					message.setText("Patthelyzet alakult ki, Te nyertél!");
					return;
				}
				message.setText("A robot nem tudott lépni, te jössz!");
				colorUj = bAktualis.getBackground();
				RectButtonIcon lepoIconUser = null;
				for(int b = 0; b < 8; b++) {
					lepoIconUser = (RectButtonIcon)userFields.get(b).getIcon();
					if(lepoIconUser.getColor().equals(colorUj)) {
						bAktualis = userFields.get(b);
						break;
					}
				}
			}
			else {
				int max = activeButtons.size() - 1;
				int random = (int) (Math.random() * (max + 1));
				uj = activeButtons.get(random);
				uj.setIcon(bAktualis.getIcon());
				bAktualis.setIcon(null);
				
				//A robot győzött
				for(int c = 0; c < 8; c++) {
					if(uj == fields[7][c]) {
						message.setText("Vesztettél!");
						activePlayer.setText(null);
						return;
					}
				}
				robotFields.remove(bAktualis);
				robotFields.add(uj);
				
				colorUj = uj.getBackground();
				RectButtonIcon lepoIconUser = null;
				for(int b = 0; b < 8; b++) {
					lepoIconUser = (RectButtonIcon)userFields.get(b).getIcon();
					if(lepoIconUser.getColor().equals(colorUj)) {
						bAktualis = userFields.get(b);
						break;
					}
				}
			}
			
			MyColor c = new MyColor(colorUj);
			activePlayer.setText("Te következel! A lépő bábud színe: " + c.toString());
			
			activeButtons.clear();
			
			i = bIndex_i(bAktualis);
			j = bIndex_j(bAktualis);
			for(int x = i - 1 ; x >= 0; x--) {
				if(fields[x][j].getIcon() == null) {
					activeButtons.add(fields[x][j]);
				} else {
					break;
				}
			}
			
			endCycle = false;
			for(int x = i - 1; x >= 0; x--) {
				for(int y = 0; y < 8; y++) {
					if((x-y) == (i-j)) {
						if(fields[x][y].getIcon() == null) {
							activeButtons.add(fields[x][y]);
						} else {
							endCycle = true;
							break;
						}
					}
				}
				if(endCycle) {
					break;
				}
			}
			
			endCycle = false;
			for(int x = i - 1; x >= 0; x--) {
				for(int y = 0; y < 8; y++) {
					if((x+y) == (i+j)) {
						if(fields[x][y].getIcon() == null) {
							activeButtons.add(fields[x][y]);
						} else {
							endCycle = true;
							break;
						}
					}
				}
				if(endCycle) {
					break;
				}
			}
			
			if(activeButtons.isEmpty()) {
				patt2 = true;
				message.setText("Nem tudsz lépni, kattints a 'Léptem!' gombra!");
				this.actionPerformed(e);
				if(patt1 && patt2) {
					message.setText("Patthelyzet alakult ki, Te vesztettél!");
					activePlayer.setText(null);
					return;
				}
			}
			for(int a = 0; a < activeButtons.size(); a++) {
				activeButtons.get(a).addActionListener(new FieldButtonActionListener());
			}
			header.add(saveButton);
		}
	}
	
	//ELveszi az adott gombról az összes hozzáadott ActionListenert
	public void removeButtonActionListener(JButton b) {
		for(ActionListener al : b.getActionListeners()) {
	        b.removeActionListener(al);
	    }
	}
}