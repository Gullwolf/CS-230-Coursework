import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class smartEnemy extends Body {
	private ArrayList<Object> objectList;

	public smartenemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		
		this.objectList = TrainCanvas.getObjects();
		this.image = Color.RED;	
		drawObject();
	}


	
public void move() {
	if(player.getX()!=this.x) {
		if(this.x<player.getX()){
			for (int i = 0; i < objectList.size(); i++) {
			
			if ((objectList.get(i).getX() == this.x + 1) && objectList.get(i).getY() == this.y) {
				
				if(objectList.get(i).isEnemyWalkable) {
					this.x++;
					
					return;
				} else if(this.x>player.getX()) {
					for (int i = 0; i < objectList.size(); i++) {
						
						if  ((objectList.get(i).getX() == this.x) &&
								objectList.get(i).getY() == this.y + 1) {
							
							if (objectList.get(i).isEnemyWalkable) {
								this.y++;
							
								return;
							}
						}
					}
				}
			}
			}
		}
	}else if(player.getY()!=this.y) {
		if(this.y<player.getY()) {
			for (int i = 0; i < objectList.size(); i++) {
				
				if  ((objectList.get(i).getX() == this.x) &&
						objectList.get(i).getY() == this.y + 1) {
					
					if (objectList.get(i).isEnemyWalkable) {
						this.y++;
						
						return;
					} else if(this.y>player.getY()) {
						for (int i = 0; i < objectList.size(); i++) {
							
							if ((objectList.get(i).getX() == this.x) && objectList.get(i).getY() == this.y - 1) {
							
								if(objectList.get(i).isEnemyWalkable) {
									this.y--;
								
									return;
								} 
							}
						}
					}
				}
			}
		}
	}
}
}

