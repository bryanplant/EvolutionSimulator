package states;

import java.util.Random;

import com.plant.Creature;
import com.plant.Muscle;
import com.plant.Node;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayState extends State {
	
	private Scene scene;
	private Creature c;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;

    public PlayState(GameStateManager gsm, Stage primaryStage)
    {
        super(gsm);
  
		root = new Group();
		canvas = new Canvas(1280,720);
		gc = canvas.getGraphicsContext2D();

		root.getChildren().add(canvas);
		scene = new Scene(root, Color.GRAY);

		c = new Creature();
		
		primaryStage.setTitle("Evolution Simulator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		createCreature();
		setSimulationSpeed(20);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(double dt) {
       c.update(dt);
    }

    @Override
    protected void render() {
    	c.draw(root);
    }

    @Override
    protected void dispose() {
        //player.dispose();
        //map.dispose();
    }

    @Override
    protected void resize(int width, int height) {
        //viewport.update(width, height);
    }
    
    private void createCreature(){
    	Random rand = new Random();
    	int numNodes = 10;
    	int numMuscles = 2;
    	int newMuscles;
    	
    	for(int i = 0; i < numNodes; i ++){
    		c.addNode(new Node(rand.nextInt(200) + 100, rand.nextInt(200) + 100));
    	}
    	
		for(int i = 0; i < numNodes; i++){		
			if(c.getNode(i).getNumConnections() >= numMuscles)
				newMuscles = 0;
			else
				newMuscles = numMuscles;
				//newMuscles = numMuscles - c.getNode(i).getNumConnections();
			for(int j = 0; j < newMuscles; j++){
				int nextNode = rand.nextInt(numNodes);
				while(c.getNode(i).hasConnection(nextNode) || nextNode == i){
					nextNode = rand.nextInt(numNodes);
				}

				c.addMuscle(new Muscle(c.getNode(i), c.getNode(nextNode)));
				c.getNode(i).addConnection(nextNode);
				c.getNode(nextNode).addConnection(i);
				System.out.println(nextNode);
			}
			System.out.println();
		}
    }
    
    private void setSimulationSpeed(int speed){
    	c.setSimulationSpeed(speed);
    }

}
