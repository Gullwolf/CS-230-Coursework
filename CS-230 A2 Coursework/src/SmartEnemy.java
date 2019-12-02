import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class SmartEnemy extends Body {
	
	private ArrayList<Object> objectList;
	private Player player;
	
	public SmartEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		
		player = TrainCanvas.getPlayer();
		this.objectList = TrainCanvas.getObjects();
		this.image = new Image("file:Art/MissingTexture.png");	
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
							for (int j = 0; j < objectList.size(); j++) {

								if  ((objectList.get(j).getX() == this.x) &&
										objectList.get(j).getY() == this.y + 1) {

									if (objectList.get(j).isEnemyWalkable) {
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
							for (int j = 0; j < objectList.size(); j++) {

								if ((objectList.get(j).getX() == this.x) && objectList.get(i).getY() == this.y - 1) {

									if(objectList.get(j).isEnemyWalkable) {
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