import java.awt.Rectangle;
import java.nio.file.WatchEvent;

import processing.core.PApplet;
public class Main extends PApplet{
	public static void main (String[] args) {
		PApplet.main("Main");
	}
	
	
	//Constants
	final int WIDTH=1200;
	final int HEIGHT=650;
	final int ENEMIES=4;
	//Atributes
	boolean moved=false;
	boolean victory=false;
	boolean alive=true;
	int enemiesAlive=4;
	boolean paintBomb=false;
	boolean boom=false;
	int boomC=0;
	int timer=0;
	int bombTimer=0;
	int xB, yB;
	int timeH=0;
	boolean gameOver=false;
	boolean boomIncoming=false;
	int[] direct=new int[ENEMIES];
	boolean hurt=false;
	//Relations
	MapZone mainMap;
	Player player1;
	Enemy[] enemies;
	
	public void settings() {
		size(WIDTH, HEIGHT);
	}
	
	public void setup() {
		
		mainMap= new MapZone(0,0, this);
		player1= new Player(0,0, mainMap);
		enemies=new Enemy[ENEMIES];
		
		direct[0]=(int) random(1,4);
		direct[1]=(int) random(1,4);
		direct[2]=(int) random(1,4);
		direct[3]=(int) random(1,4);
		
		enemies[0]=new Enemy(7,7, mainMap);
		enemies[1]=new Enemy(10,4, mainMap);
		enemies[2]=new Enemy(4,10, mainMap);
		enemies[3]=new Enemy(4,2, mainMap);
	}

	public void draw() {
		background(60);
		
		rectMode(CENTER);
		
		mainMap.pintar(this);
		player1.pintar(this);
		noStroke();
		fill(60);
		rect(600,625,1200,50);
		stroke(1);
		timer++;
	
		for (int i = 0; i < enemies.length; i++) {
			
	
			if(timer%80==0) {
				direct[i]=(int) random(1,5);
			}
			
			enemies[i].pintar(this);
			//************************************************************************
			//enemies[i].mover(direct[i]);
			/*			
			if( enemies[i].getVisible() && enemies[i].getX()==player1.getX() && enemies[i].getY()==player1.getY() ) {
				
					player1.reduceLife();
					timeH++;
				
			}*/
			
		}
		if(timeH%80==0 && gameOver==false) {
			player1.setVulnerable(true);
			timeH=0;
		}
		
		/*if(hurt) {
			player1.setLifes(player1.getLifes()-1);
			hurt=false;
		}*/
	
		
		if(paintBomb && bombTimer<120) {
			player1.mainBomb.pintar(this,xB, yB);
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
				fill(130,130,0,100);
			}else {
				fill(230,0,0,100);
			}
			
			rect(75+yB*50, 75+xB*50, 150, 50);
			rect(75+yB*50, 75+xB*50, 50, 150);
			//boom=!boom;
		}
		
		if(enemiesAlive==0) {
			victory=true;
		}
		
		if(gameOver) {
			textSize(100);
			rectMode(CENTER);
			
			fill(230,30,30);
			rect(WIDTH/2,HEIGHT/2,600,200);
			fill(100,0,0);
			text("GAME OVER", 110,(HEIGHT/2)+40);
		}else {
			textSize(40);
			rectMode(CENTER);
			fill(255);
			text("Enemies left: "+enemiesAlive, 500,40);
		}
		
		if(victory){
			textSize(100);
			rectMode(CENTER);
			
			fill(30,230,30);
			rect(WIDTH/2,HEIGHT/2,600,200);
			fill(0,100,0);
			text("VICTORY!", 165,(HEIGHT/2)+40);
		}
		//System.out.println("row: "+player1.mainBomb.getRow()+ " , COL: "+ player1.mainBomb.getCol());
	}
	
	public void keyPressed() {
		//System.out.println(mainMap.getUpperType(mainMap.getUpperTile(mainMap.getPLayer().getX(), mainMap.getPLayer().getY())));
		
	if(gameOver==false && victory==false) {
		
			switch(key) {
			case 'd':
				player1.mover(1);
				/*if(mainMap.getPLayer().getX()<WIDTH-75) {
					
					mainMap.getPLayer().mover(1);
				}*/
				break;
				
			case 'a':
				player1.mover(2);
				/*if(mainMap.getPLayer().getX()>75) {
					mainMap.getPLayer().mover(2);
				}*/
				
				break;
				
			case 'w':
				player1.mover(4);
				/*if(mainMap.getPLayer().getY()>75) {
					
					mainMap.getPLayer().mover(4);
				}*/
				break;
				
			case 's':
				player1.mover(3);
				/*if(mainMap.getPLayer().getY()<HEIGHT-75) {
					mainMap.getPLayer().mover(3);
				}*/
				break;
			case 'b':
				if(paintBomb==false) {
					xB=player1.mainBomb.getRow();
					yB=player1.mainBomb.getCol();
					System.out.println(xB);
					System.out.println(yB);
					paintBomb=true;
				}
			
				break;
			}//end switch
		}//end if
	}//end keyPressed
} 
