package Game;

import java.awt.Color;
import java.io.Serializable;

public class MyColor implements Serializable {
	private Color c;
	
	public MyColor(Color c) {
		this.c = c;
	}
	
	public Color getColor() {
		return c;
	}
	
	//A színnevek kiírására
	public String toString() {
		if(c == Color.blue) {
			return "kék";
		} else if(c == Color.green) {
			return "zöld";
		} else if (c == Color.lightGray) {
			return "szürke";
		} else if (c == Color.magenta) {
			return "lila";
		} else if (c == Color.orange) {
			return "narancssárga";
		} else if (c == Color.pink) {
			return "rózsaszín";
		} else if (c == Color.red) {
			return "piros";
		} else if (c == Color.yellow) {
			return "citromsárga";
		}
		
		return null;
	}
}
