
import processing.core.PApplet;
import processing.core.PImage;

public class Bala {
	
	//images
	
	private int x;
	private int y;
	private int dir; //0-abajo,1-derecha,2-izquierda,3-arriba
	

	public Bala(int x, int y, int dir) {
		
		this.x = x;
		this.y = y;
		this.dir = dir;
		
	}
	
	public void pintar(PApplet app, PImage v, PImage h) {

		switch(dir) {
		case 0:
			app.image(v,x,y,25,25);
			break;
		case 1:
			app.image(h,x,y,25,25);
			break;
		case 2:
			app.image(h,x,y,25,25);
			break;
		case 3:
			app.image(v,x,y,25,25);
			break;
		}
	 
		
	}
	
	public void mover() {
		switch (dir) {
		case 0:
			y+=5;
			break;
		case 1:
			x+=5;
			break;
		case 2:
			x-=5;
			break;
		case 3:
			y-=5;
			break;
			

		default:
			break;
		}
	}

}
