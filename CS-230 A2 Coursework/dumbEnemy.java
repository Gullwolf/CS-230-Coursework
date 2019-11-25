import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/** This class is create a enemy which can find the shortest way to attack the player
*@author Hao Wu
*@version 1.0
*/
public class dumbEnemy extends Body {
	private ArrayList<Object> objectList;
	/**
	*create an enemy
	*/
	public dumbEnemy(int x, int y, GraphicsContext gc, int TILE_SIZE) {
		super(x, y, gc, TILE_SIZE);
		
		//Getting the list of objects in the map
		this.objectList = TrainCanvas.getObjects();
		this.image = Color.RED;
		drawObject();
	}
	/**
	* a mothed to find the shortest way
	*/
	public void move() {
		if (player.getX()!=this.x){
			if(this.x<player.getX()){
				this.x=x+1;
			}
			else{
				this.x=x-1;
			}
		}
		else if(player.getY()!=this.y){
			if(this.y<player.getY()){
				this.y=y+1;
			}
			else{
				this.y=y-1;
			}
		}
	}
}
