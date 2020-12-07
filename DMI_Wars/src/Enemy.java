import java.util.Map;

import processing.core.PApplet;

public class Enemy {

	private int x, y , row, col,tam;
	private boolean visible;
	//Relations
	MapZone mapRef;
	
	public Enemy(int row, int col ,MapZone mapRef) {
		this.row=row;
		this.col=col;
		x=75+col*50;
		y=75+row*50;
		tam=40;
		visible=true;
		this.mapRef=mapRef;
	}
	
	public void pintar(PApplet app) {
		if(visible) {
			app.fill(160,0,0);
			app.circle(x,y,tam);
		}
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public boolean mover(int option) {
		int typeT;
		boolean moved=false;
			switch(option) {
			
			case 1:
				if(col<21) {
					typeT=mapRef.getTileType(row,col+1);
					
					if(typeT==0) {
						
						x=75+(50*col);
						moved=true;
						if(moved==true) {
							col=col+1;
						}
					}
				}
			
				break;
			case 2:
				if(col>0) {
					typeT=mapRef.getTileType(row,col-1);
					
					if(typeT==0) {
						
						x=75+(50*col);
						moved=true;
						if(moved==true) {
							col=col-1;
						}
					}
				}
				break;
			case 3:
				if(row<11) {
					typeT=mapRef.getTileType(row+1,col);
					
					if(typeT==0) {
						
						y=75+(50*row);
						moved=true;
						if(moved==true) {
							row=row+1;
						}
					}
				}
				break;
			case 4:
				if(row>0) {
					typeT=mapRef.getTileType(row-1,col);
					
					if(typeT==0){
						
						y=75+(50*row);
						moved=true;
						if(moved==true) {
							row=row-1;
						}
					}
					
				}
				break;
			}
			return moved;
	}
	
	public boolean checkExploded(int row, int col) {
		boolean damage=false;
		if((this.row==row-1 && this.col==col)  || (this.row==row+1 && this.col==col) || (this.col==col-1 && this.row==row) || 
				 (this.col==col+1 && this.row==row || (this.row==row && this.col==col))) {
				visible=false;
				damage=true;
			}
		return damage;
	}
}
