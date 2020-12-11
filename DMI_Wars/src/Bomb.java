import processing.core.PApplet;

public class Bomb {
	private int x, y, row, col, tam, xP, yP;
	
	//Relations
	MapZone mapRef;
	
	public Bomb(int row, int col, int xP, int yP, MapZone mapRef) {
		this.row=row;
		this.col=col;
		y=75+row*50;
		x=75+col*50;
		this.xP=xP;
		this.yP=yP;
		tam=20;
		this.mapRef=mapRef;
	}
	
	
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setRow(int row) {
		this.row=row;
	}
	
	public void setCol(int col) {
		this.col=col;
	}
	
	public void pintar(PApplet app, int xP, int yP) {
		app.fill(200,20,20);
		app.circle(75+50*yP, 75+50*xP, tam);
	}
	
	public void explote(int col, int row) {
		
		
			if(col>0 && mapRef.getTileType(col-1, row)!=1) {
				mapRef.setType(row, col-1);
			}
			
			if(col<21 && mapRef.getTileType(col+1, row)!=1) {
				mapRef.setType(row, col+1);
			}
			
			if(row>0 && mapRef.getTileType(col, row-1)!=1) {
				mapRef.setType(row-1, col);
			}
			
			if(row<11 && mapRef.getTileType(col, row+1)!=1) {
				mapRef.setType(row+1, col);
			}
			
	}
}
