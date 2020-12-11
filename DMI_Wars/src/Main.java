import java.awt.Rectangle;
import java.nio.file.WatchEvent;

import processing.core.PApplet;
import processing.core.PImage;
public class Main extends PApplet{
	public static void main (String[] args) {
		PApplet.main("Main");
	}
	
	
	//Constants
	final int WIDTH=1200;
	final int HEIGHT=650;
	final int ENEMIES=4;
	
	//atributes
	int levelNum=1;
	int coolDownMove=0;
	int levelType=1;
	
	boolean canMove=true;
	//relations
	Level[] levels;
	
	public void settings() {
		size(WIDTH, HEIGHT);
	}
	
	public void setup() {
		
		levels=new Level[10];
		
		levels[0]=new Level(1,this);
		levels[1]=new Level(2,this);
		levels[2]=new Level(3,this);
		levels[3]=new Level(4,this);
		levels[4]=new Level(5,this);
		levels[5]=new Level(6,this);
		levels[6]=new Level(7,this);
		levels[7]=new Level(8,this);
		levels[8]=new Level(9,this);
		levels[9]=new Level(10,this);

		
	
	}

	public void draw() {
		background(60);
		
		rectMode(CENTER);
		
		switch(levelNum) {
		
		case 1:
			levels[0].pintarNivel(this);
			break;
		case 2:
			levels[1].pintarNivel(this);
			break;
		case 3:
			levels[2].pintarNivel(this);
			break;
		case 4:
			levels[3].pintarNivel(this);
			break;
		case 5:
			levels[4].pintarNivel(this);
			break;
		case 6:
			levels[5].pintarNivel(this);
			break;
		case 7:
			levels[6].pintarNivel(this);
			break;
		case 8:
			levels[7].pintarNivel(this);
			break;
		case 9:
			levels[8].pintarNivel(this);
			break;
		case 10:
			levels[9].pintarNivel(this);
			break;
		}
		
			
		

	}
	
	public void keyPressed() {
	for(int i=0;i<levels.length;i++) {
		coolDownMove++;
		System.out.println(coolDownMove);
		System.out.println(canMove);
		if(coolDownMove==10) {
			coolDownMove=0;
			canMove=true;
		}
		if(canMove) {
			if(levels[i].getGameOver()==false && levels[i].getVictory()==false) {
				
					switch(key) {
					case 'd':
						levels[i].movePlayer(1);
		
						break;
						
					case 'a':
						levels[i].movePlayer(2);
		
						break;
						
					case 'w':
						levels[i].movePlayer(4);
		
						break;
						
					case 's':
						levels[i].movePlayer(3);
		
						break;
					case 'b':
						if(levels[i].getPaintBomb()==false) {
							levels[i].setXb(levels[i].getPlayer().getBomb().getRow());
							levels[i].setYb(levels[i].getPlayer().getBomb().getCol());
							levels[i].setPaintBomb(true);
						}
					
						break;
					}//end switch
					
				}//end if levels
			}//end if canMove
		}//end for
	}//end keyPressed
} 
