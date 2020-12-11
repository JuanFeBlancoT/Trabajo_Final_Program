import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy {

	private int x, y , row, col,tam;
	private boolean visible;
	private int dir; //0-abajo,1-derecha,2-izquierda,3-arriba
	
	private int contador;
	
	//Relations
	
	
	
	MapZone mapRef;
	
	Bala balas;
	
	public PImage sandman1;
	public PImage sandman2;
	public PImage sandman3;
	public PImage sandman4;
	PImage verti;
	PImage hori;
	
	public Enemy(int row, int col ,MapZone mapRef, PApplet app) {
		this.row=row;
		this.col=col;
		x=75+col*50;
		y=75+row*50;
		tam=40;
		visible=true;
		this.mapRef=mapRef;
		
		//balas = new Bala(x,y,dir);
		
		sandman1 = app.loadImage("Enemigo 1-3.png"); //derecha
		sandman2 = app.loadImage("Enemigo 1-4.png"); //izquierda
		sandman3 = app.loadImage("Enemigo 1-1.png"); //abajo
		sandman4 = app.loadImage("Enemigo 1-2.png"); //arriba
		
		verti=app.loadImage("balaVerti.png");
		hori =app.loadImage("balaHori.png");
		
		app.imageMode(app.CENTER);
	}
	
	public void pintar(PApplet app) {
		if(visible) {
			
			/*app.fill(160,0,0);
			app.circle(x,y,tam);*/
			
			switch (dir) {
			case 0:
				
				app.image(sandman3, x, y, 50, 50);
				break;
			case 1:
				
				app.image(sandman1, x, y, 50, 50);
				break;
			case 2:
				app.image(sandman2, x, y, 50, 50);
				break;
			case 3:
				app.image(sandman4, x, y, 50, 50);
				break;
				

			default:
				break;
			}
			
			
			if(balas != null) {
				balas.pintar(app, verti, hori);
				balas.mover();
				
			}
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
							dir = 1;
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
							dir = 2;
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
							dir = 0;
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
							dir = 3;
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
	
	public void disparos() {
		contador++;
		
		if(contador%180==0) {
		
		balas = new Bala(x,y,dir);
	}
	}
}
