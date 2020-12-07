import processing.core.PApplet;

public class Tile {

	private int xTilePos, yTilePos, tSize, color;
	private int type;
	private boolean visible;
	
	public Tile(int xTilePos, int yTilePos) {
		this.xTilePos=xTilePos;
		this.yTilePos=yTilePos;
		tSize=50;
		
	}
	
	public void pintar(PApplet app) {
		switch(type) {
		case 0:
			app.fill(255);
			break;
		case 1:
			app.fill(0);
			break;
		case 2:
			app.fill(120,0,0);
			break;
		case 3:
			app.fill(180);
			break;
			
		}

		app.square(xTilePos,yTilePos,tSize);
	}
	
	public int getX() {
		return xTilePos;
	}
	
	public int getY() {
		return yTilePos;
	}
	
	public int getSize() {
		return tSize;
	}
	
	public int getType() {
		return type;
	}
	
	public void setX(int x) {
		xTilePos=x;
	}
	
	public void setY(int y) {
		yTilePos=y;
	}
	
	public void setType(int type) {
		this.type=type;
	}
	

}
