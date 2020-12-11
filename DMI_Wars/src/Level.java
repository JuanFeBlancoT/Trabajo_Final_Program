import processing.core.PApplet;
import processing.core.PImage;

public class Level {
	//constants
	final int WIDTH=1200;
	final int HEIGHT=650;
	
	//attributes
	boolean gameOver=false;
	boolean paintBomb=false;
	boolean boomIncoming=false;
	boolean alive = true;
	boolean boom=false;
	boolean victory=false;
	boolean justOnce=true;
	
	int enemiesAlive=4;
	int bombTimer=0;
	int timeH=0;
	int boomC=0;
	int xB;
	int yB;
	
	
	int contarP=0;
	////////
	PImage nivel1;
	
	
	
	int numLevel;
	//relations
		Enemy[] enemies;
		MapZone levelMap;
		Player player1;
		
	public Level(int numLevel,PApplet app) {
		this.numLevel = numLevel;
		enemies=new Enemy[4];
		levelMap =new MapZone(0,0,app);
		player1= new Player(6,1,levelMap);
		//esti no va aqui
		enemies[0]=new Enemy(7,7, levelMap, app);
		enemies[1]=new Enemy(10,4, levelMap, app);
		enemies[2]=new Enemy(4,10, levelMap, app);
		enemies[3]=new Enemy(4,2, levelMap, app);
		nivel1 = app.loadImage("Nivel 1.png");
		//setObstacles
		
		
	}
	
	
	public void pintarNivel(PApplet app) {
		switch(numLevel) {
		case 1:
			//Set cosos
			levelMap.getTile(5,6).setType(1);
			levelMap.getTile(2,0).setType(1);
			levelMap.getTile(1,2).setType(1);
			levelMap.getTile(5,0).setType(1);
			levelMap.getTile(6,0).setType(1);
			levelMap.getTile(7,0).setType(1);
			levelMap.getTile(5,1).setType(1);
			levelMap.getTile(6,1).setType(1);
			levelMap.getTile(7,1).setType(1);
			
			levelMap.getTile(8,1).setType(1);
			levelMap.getTile(10,2).setType(1);
			levelMap.getTile(4,3).setType(1);
			levelMap.getTile(0,4).setType(1);
			levelMap.getTile(1,4).setType(1);
			levelMap.getTile(2,4).setType(1);
			levelMap.getTile(0,5).setType(1);
			levelMap.getTile(1,5).setType(1);
			levelMap.getTile(2,5).setType(1);
			
			levelMap.getTile(0,8).setType(1);
			levelMap.getTile(6,8).setType(1);
			levelMap.getTile(8,4).setType(1);
			
			levelMap.getTile(8,6).setType(1);
			levelMap.getTile(9,6).setType(1);
			levelMap.getTile(10,6).setType(1);
			levelMap.getTile(9,9).setType(1);
			levelMap.getTile(13,7).setType(1);
			levelMap.getTile(14,9).setType(1);
			levelMap.getTile(14,9).setType(1);
			levelMap.getTile(17,10).setType(1);
			levelMap.getTile(20,8).setType(1);
			levelMap.getTile(19,7).setType(1);
			levelMap.getTile(12,10).setType(1);
			levelMap.getTile(14,5).setType(1);
			levelMap.getTile(18,0).setType(1);
			levelMap.getTile(16,3).setType(1);
			
			
			
			System.out.println("ROW: "+player1.getRow()+"   COL:"+player1.getCol());
			
			
			contarP++;
			if(contarP>300) {
				contarP=0;
			}
			app.image(nivel1,600, 325);	
			
						
				for (int i = 0; i < enemies.length; i++) {
					enemies[i].disparos();
					//enemies[i].mover((int) random(1,4));
					if(contarP%20==0) {
					enemies[i].mover(( int )(Math.random()*4+1));
					}
			}
			//levelMap.pintar(app);
			
			player1.pintar(app);
			
			
			
			
			app.noStroke();
			app.fill(60);
			app.rect(600,625,1200,50);
			app.stroke(1);
			
			for (int i = 0; i < enemies.length; i++) {			
				enemies[i].pintar(app);
			}
			
			if(timeH%80==0 && gameOver==false) {
				player1.setVulnerable(true);
				timeH=0;
			}
			
			if(paintBomb && bombTimer<120) {
				player1.mainBomb.pintar(app,xB, yB);
				bombTimer++;
				boomIncoming=true;
				
			}else {
				bombTimer=0;
				paintBomb=false;
				boomIncoming=false;
			}
			if(bombTimer==120) {
				player1.mainBomb.explote(xB, yB);
				player1.confirmDamage(xB,yB);
				for (int i = 0; i < enemies.length; i++) {
					if(enemies[i].getVisible()==true) {
						alive=enemies[i].checkExploded(xB, yB);
						if(alive) {
							alive=false;
							enemiesAlive--;
						}
					}
					
				}
			}
			
			if(player1.getLifes()<1) {
				gameOver=true;
			}
			
			if(boomIncoming) {
				boomC++;
				if(boomC%15==0) {
					boom=!boom;
				}
				if(boom) {
					app.fill(130,130,0,100);
				}else {
					app.fill(230,0,0,100);
				}
				
				app.rect(75+yB*50, 75+xB*50, 150, 50);
				app.rect(75+yB*50, 75+xB*50, 50, 150);
				//boom=!boom;
			}
			
			if(enemiesAlive==0) {
				victory=true;
			}
			
			if(gameOver) {
				app.textSize(100);
				app.rectMode(app.CENTER);
				
				app.fill(230,30,30);
				app.rect(WIDTH/2,HEIGHT/2,600,200);
				app.fill(100,0,0);
				app.text("GAME OVER", 110,(HEIGHT/2)+40);
			}else {
				app.textSize(40);
				app.rectMode(app.CENTER);
				app.fill(255);
				app.text("Enemies left: "+enemiesAlive, 500,40);
			}
			
			if(victory){
				app.textSize(100);
				app.rectMode(app.CENTER);
				
				app.fill(30,230,30);
				app.rect(WIDTH/2,HEIGHT/2,600,200);
				app.fill(0,100,0);
				app.text("VICTORY!", 165,(HEIGHT/2)+40);
			}
			break; //end level 1
			
		}//end switch
		

		
	}//end pintarNivel
	
	private int random(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean getVictory() {
		return victory;
	}	
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public Player getPlayer() {
		return player1;
	}
	
	public boolean getPaintBomb() {
		return paintBomb;
	}
	
	public void setPaintBomb(boolean newP) {
		paintBomb= newP;
	}
	
	public int getXb() {
		return xB;
	}
	
	public int getYb() {
		return yB;
	}
	
	public void setXb(int xb) {
		xB = xb;
	}
	
	public void setYb(int yb) {
		yB =yb;
	}
	
	public Bomb getMainBomb() {
		return player1.getBomb();
	}

	public void setJustOnce(boolean newJO) {
		justOnce=newJO;
	}
	
	public void movePlayer(int direction) {
		//if(justOnce) {
			player1.mover(direction);
			//justOnce=false;
		//}
		
	}
}
