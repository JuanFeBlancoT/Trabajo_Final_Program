import processing.core.PApplet;

public class MapZone {
	//Constants
	public final int TILE_W=22;
	public final int TILE_H=12;
	public final int ENEMIES=4;
	//Atributes
	private int size, xMap, yMap;
	
	//Relations
	private Tile[][] tileSet;

	
	public MapZone(int xMap, int yMap, PApplet app) {
		this.xMap=xMap;
		this.yMap=yMap;
		
		tileSet=new Tile[TILE_W][TILE_H];
		
		int x=75;
		int y=75;
		
		for (int i = 0; i < TILE_H; i++) {
			for (int j = 0; j < TILE_W; j++) {
				
				tileSet[j][i]= new Tile(x,y);

				x+=50;
				if(x>1125) {
					x=75;
				}
			}
			y+=50;
		}
		
		tileSet[5][6].setType(1);
		tileSet[5][9].setType(2);
		



		
	}//end mapZone
	
	public void pintar(PApplet app) {
		int x=75;
		int y=75;
		for (int i = 0; i < TILE_W; i++) {
			for (int j = 0; j < TILE_H; j++) {
				tileSet[i][j].pintar(app);
			
			}
			
		}
		
	}
	
	public int getXpos(int x, int y) {
		return tileSet[x][y].getX();
	}
	
	public int getYpos(int x, int y) {
		return tileSet[x][y].getY();
	}
	
	/*public Player getPLayer() {
		return player1;
	}*/
	
	/*public Tile getUpperTile(int x, int y) {
		Tile upperT=null;
		
		for (int i = 0; i < tileSet.length; i++) {
			for (int j = 0; j < tileSet.length; j++) {
				if(tileSet[i][j].getX()==x && tileSet[i][j].getY()==y) {
					
					if() {
						
					}
						upperT=tileSet[i][j-1];
					
					
				}
			}
		}
		
		return upperT;
	}//end getUppperTile*/
	
	/*public int getUpperType(Tile actual) {
		Tile upperT=null;
		
		for (int i = 0; i < tileSet.length; i++) {
			for (int j = 0; j < tileSet.length; j++) {
				if(tileSet[i][j]==actual) {
					if(i>0) {
						upperT=tileSet[i-1][j];
					}
						
					
					
				}
			}
		}
		
		return upperT.getType()	;
	}//end getUppperType*/
	
	public int getTileType(int row, int col) {
		int tType = -1;
		if(row>0 || col>0 || row<TILE_H || col<TILE_W) {
			tType=tileSet[col][row].getType();
		}
		return tType;
		
	}

	
	public void setType(int row, int column) {
		tileSet[row][column].setType(0);
	}
	

	
}