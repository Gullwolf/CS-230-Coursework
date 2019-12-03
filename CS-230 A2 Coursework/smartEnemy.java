import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class SmartEnemy extends Body {
	
	private ArrayList<Object> objectList;
	private Player player;
	private int swap;
	private int upcounter = 0;//count of the topside of the enemy unwalkable object
	private int downcounter = 0;//count of the down of the enemy unwalkable object
	private int leftcounter = 0;//count of the leftside of the enemy unwalkable object
	private int rightcounter = 0;//count of the right of the enemy unwalkable object
	private int c ;//to state the unwalkable object is on the left or the right side of the enemy when in same y
	private int d ;//to state the unwalkable object is on the up or the down side of the enemy when in same x
	public SmartEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		
		player = TrainCanvas.getPlayer();
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/MissingTexture.png");	
		drawObject();
	}
public void move(){
	if(player.getX()!=this.x){// check if the enemy x equal to player x
		if(this.x<player.getX()){//go right if player in right side
			swap = 1;
		}else if(this.x>player.getX()){//go left if player in left side
			swap = 2;
		}
		
	}else if (swap == 0){//find out go up or down when know plaer y not equal to enemy y
		if(this.y<player.getY()){// go up if player is on the upper side
			swap = 3;
		}else if(this.y>player.getY()){//go down if player is lower then the enemy
			swap = 4;
		}
	}else if (swap == 1){//go right
		for (int i = 0; i < objectList.size(); i++) {
            // same way in basic enemy to know the object position			
			if ((objectList.get(i).getX() == this.x + 1) && objectList.get(i).getY() == this.y) {
               // find the next positon have what object on it
				if(objectList.get(i).isEnemyWalkable) {//if is enemy walkable 
					this.x++;//go right
                  
				}else{// if not walkable 
					if(player.getY()!=this.y){//change to up or down if no in same y 
						swap = 0;
					}else if(player.getY() == this.y){//if in same y 
					c=x+1;//unwalkable object on the right side of the enemy when in same y to the player
					topwall();//find the count of the wall on top of the unwalkable object 
					downwall();//find the count of the wall under the unwalkable object
					if(upcounter != downcounter){// if not same number of unwalkable object in top and down
						if(upcounter>downcounter){//go down if the number of unwalkable object is less then go up (faster way)
							swap = 4;//go down
							upcounter = 0;//reset counter
							downcounter = 0;
						}else if(upcounter<downcounter){//go up if the number of unwalkable object is less then go down (faster way)
							swap = 3;//go up
							upcounter = 0;
							downcounter = 0;
						}
					}
					}
					i = objectList.size();//stop loop 
					
				}
			}
		}	
	}else if (swap == 2){// go left
		for (int j = 0; j < objectList.size(); j++) {
			 // same way in basic enemy to know the object position		
			if  ((objectList.get(j).getX() == this.x - 1) && objectList.get(j).getY() == this.y) {
				// find the next positon have what object on it
				if (objectList.get(j).isEnemyWalkable) {// walkable
					this.x--;//go left

				}else{// if not 
					if(player.getY()!=this.y){//change up or down
						swap = 0;
					}else if(player.getY() == this.y){// if same y with player
						c=x-1;//unwalkable object on the left side of the enemy when in same y to the player
						topwall();
						downwall();
						if(upcounter != downcounter){
							if(upcounter>downcounter){//go down if the number of unwalkable object is less then go up (faster way)
								swap = 4;
								upcounter = 0;
								downcounter = 0;
							}else if(upcounter<downcounter){//go up if the number of unwalkable object is less then go down (faster way)
								swap = 3;
								upcounter = 0;
								downcounter = 0;
							}
						}
					}
					j = objectList.size();//stop loop
					
				}				
			}
		}
	}else if (swap == 3){//go up 
		for (int i = 0; i < objectList.size(); i++) {
			// same way in basic enemy to know the object position		
			if  ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y + 1) {
				// find the next positon have what object on it
				if (objectList.get(i).isEnemyWalkable) {// if walkable
					this.y++;//go up 
				}else if(player.getX()!=this.x){//if not in same x with the player
					if(this.x<player.getX()){//player on rightside
						swap = 1;//move right
					}else if(this.x<player.getX()){//on leftside
						swap = 2;//go left
					}
					}else if(player.getX()==this.x){//if same x with player
					d=y+1;//unwalkable object downside to the player
					leftwall();//find number of wall in left side of the object we need to pass
					rightwall();//find number of wall in right side of the object we need to pass
					if(rightcounter != leftcounter){
						if(rightcounter>leftcounter){// if left side less the right
							swap = 2;//then go left
							rightcounter = 0;
							leftcounter = 0;
						}else if(rightcounter<leftcounter){//same
							swap = 1;
							rightcounter = 0;
							leftcounter = 0;
						}
					}
					}
					i = objectList.size();
				}
			}
		
	}else if (swap == 4){//go down
		for (int j = 0; j < objectList.size(); j++) {
			// same way in basic enemy to know the object position		
			if ((objectList.get(j).getX() == this.x) && objectList.get(j).getY() == this.y - 1) {
				// find the next positon have what object on it
				if(objectList.get(j).isEnemyWalkable) {//if walkable 
					this.y--;//go down

				}else if(player.getX()!=this.x) {//if not walkable

					if(this.x<player.getX()){//player on the right side
						swap = 1;//go right
					}else if(this.x<player.getX()){//player on left side
						swap = 2;//go left
					}
				}else if(player.getX()==this.x){//if same x with player
					d=y-1;//unwalkable object on top sdie
					leftwall();//counter of the leftside unwalkable object on top of the object we need to pass
					rightwall();//counter of the right unwalkable object on top of the object we need to pass
					if(rightcounter != leftcounter){
						if(rightcounter>leftcounter){//if left is faster
							swap = 2;
							rightcounter = 0;
							leftcounter = 0;
						}else if(rightcounter<leftcounter){//if right is faster
							swap = 1;
							rightcounter = 0;
							leftcounter = 0;
						}
					}
					}
					j = objectList.size();}
			}
		}
	}



public void topwall(){//method of find number of unwalkable object form the top of the one we cant pass to 
	for (int k = 0; k < objectList.size(); k++){//same way to call the list
		if ((objectList.get(k).getX() == this.c) && objectList.get(k).getY() == this.y++){//this.c make us can change form x+1 or x-1
			if (objectList.get(k).isEnemyWalkable){// if the next one is walkable
				k = objectList.size(); //then no unwalkable wall on the topsidee so end
			}else{
				upcounter++;//number of the unwalkable wall on top 
			}
		}
	}
}
public void downwall(){//method of find number of unwalkable object form the under of the one we cant pass to 
	for (int t = 0; t < objectList.size(); t++){
		if ((objectList.get(t).getX() == this.c) && objectList.get(t).getY() == this.y--){//this.c make us can change form x+1 or x-1 just y change form ++ to -- cuz is under
			if (objectList.get(t).isEnemyWalkable){// if the next one is walkable
				t = objectList.size(); //then no unwalkable wall on the undersie so end
			}else{
				downcounter++;//number of the unwalkable wall under
			}
		}
	}
}

public void leftwall(){//method of find number of unwalkable object form the left of the one we cant pass to
	for (int r = 0; r < objectList.size(); r++) {
		if  ((objectList.get(r).getX() == this.x--) && objectList.get(r).getY() == this.d) {//this.d so we can change form y-1 or y+1
			if (objectList.get(r).isEnemyWalkable) {// if the next one is walkable
				r = objectList.size();//then no unwalkable wall on the leftside so end
			}else
				leftcounter++;//number of the unwalkable wall on leftside
	    }

     }
}
public void rightwall(){//method of find number of unwalkable object form the right of the one we cant pass to
	for (int p = 0; p < objectList.size(); p++) {
		if  ((objectList.get(p).getX() == this.x++) && objectList.get(p).getY() == this.d) {
			if (objectList.get(p).isEnemyWalkable) {// if the next one is walkable
				p = objectList.size();//then no unwalkable wall on the rightside so end
			}else
				rightcounter++;//number of the unwalkable wall on rightsdie
	    }

     }
}



}
