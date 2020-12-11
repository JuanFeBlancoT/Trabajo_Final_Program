import processing.core.PApplet;

public class Player {
	//Atributes
	private int lifes;
	private int row, col, xP, yP;
	private boolean vulnerable=true;
	
	//Relations
	Tile actualTile;
	MapZone refMap;
	Bomb mainBomb;
	
	public Player(int row, int col, MapZone refMap) {
		this.row=row;
		this.col=col;
		xP=75+(50*row);
		yP=75+(50*col);
		//actualTile=tile;
		this.refMap=refMap;
		mainBomb=new Bomb(row,col, xP, yP, refMap);
		lifes=5;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getX() {
		return xP;
	}
	
	public int getY() {
		return yP;
	}
	
	public Tile getTile() {
		return actualTile;
	}
	
	public void setRow(int x) {
		row=x;
	}
	
	public void setCol(int y) {
		col=y;;
	}
	
	public void setTile(Tile tile) {
		actualTile=tile;
	}
	
	public void pintar(PApplet app) {
		app.fill(0,120,0);
		app.circle(xP, yP, 25);
		app.textSize(40);
		app.fill(255);
		app.text("Lifes: "+lifes, 15, 40);
	}
	
	public void mover(int direction) {
		int typeT=-1;
		switch(direction) {
		case 1:
			
			if(col!=21) {
				typeT=refMap.getTileType(row,col+1);
				
				if(typeT==0) {
					col=col+1;
					xP=75+(50*col);
					mainBomb.setCol(col);
				}
			}
			
			
			break;
		case 2:
			
			if(col!=0) {
				typeT=refMap.getTileType(row,col-1);
				
				if(typeT==0) {
					col=col-1;
					xP=75+(50*col);
					mainBomb.setCol(col);
				}
			}
			
			break;
		case 3:
			
			if(row!=10) {
				typeT=refMap.getTileType(row+1,col);
				
				if(typeT==0) {
					row=row+1;
					yP=75+(50*row);
					mainBomb.setRow(row);
					
				}
			}
			
				break;
			
			
		case 4:
			
			if(row!=0) {
				typeT=refMap.getTileType(row-1,col);
				
				if(typeT==0){
					row=row-1;
					yP=75+(50*row);
					mainBomb.setRow(row);
					
				}
				
			}
			
			break;
		}
	}

	public void confirmDamage(int row, int col) {
		
		if((this.row==row-1 && this.col==col)  || (this.row==row+1 && this.col==col) || (this.col==col-1 && this.row==row) || 
			 (this.col==col+1 && this.row==row || (this.row==row && this.col==col))) {
			lifes--;
			
		}
		
	}
	


	public int getLifes() {
		
		return lifes;
	}
	
	public void setLifes(int lifes) {
		this.lifes=lifes;
	}

	public boolean getVulnerable() {
		return vulnerable;
	}
	
	public void setVulnerable(boolean b) {
		vulnerable=b;
		
	}
	
	public void reduceLife(){
		
		if(vulnerable) {
			lifes-=1;
			vulnerable=false;
			
		}
		
	}

	public Bomb getBomb() {
		return mainBomb;
	}
}
