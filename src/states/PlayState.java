package states;

import java.awt.Graphics2D;
import java.util.Random;

import com.plant.Creature;
import com.plant.Environment;
import com.plant.Muscle;
import com.plant.Node;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayState extends State {

	private Scene scene;
	private Creature creature;
	private Group root;
	private Group cameraGroup;
	private Canvas canvas;
	private GraphicsContext gc;
	private PerspectiveCamera camera;
	
	private Environment environment;
	
	private int gameWidth = 1280;
	private int gameHeight = 720;

    public PlayState(GameStateManager gsm, Stage primaryStage)
    {
        super(gsm);

        cameraGroup = new Group();
		root = new Group();
		canvas = new Canvas(gameWidth,gameHeight);
		gc = canvas.getGraphicsContext2D();
		camera = new PerspectiveCamera();
		
		cameraGroup.getChildren().add(camera);

		root.getChildren().add(canvas);
		root.getChildren().add(cameraGroup);
		
		scene = new Scene(root, gameWidth, gameHeight, Color.GRAY);
		scene.setCamera(camera);

		creature = new Creature();
		environment = new Environment(gameHeight);
		environment.show(root);

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
    	updateCamera();
    	creature.update(dt);
    }

    @Override
    protected void render() {
    	//creature.render(root);
    	//environment.render(root);
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
    	int numMuscles = 4;
    	int newMuscles;

    	for(int i = 0; i < numNodes; i ++){
    		creature.addNode(new Node(rand.nextInt(200) + 500, rand.nextInt(200) + 300));
    	}

		for(int i = 0; i < numNodes; i++){
			if(creature.getNode(i).getNumConnections() >= numMuscles)
				newMuscles = 0;
			else
				newMuscles = numMuscles;
				//newMuscles = numMuscles - c.getNode(i).getNumConnections();
			for(int j = 0; j < newMuscles; j++){
				int nextNode = rand.nextInt(numNodes);
				while(creature.getNode(i).hasConnection(nextNode) || nextNode == i){
					nextNode = rand.nextInt(numNodes);
				}

				creature.addMuscle(new Muscle(creature.getNode(i), creature.getNode(nextNode)));
				creature.getNode(i).addConnection(nextNode);
				creature.getNode(nextNode).addConnection(i);
				System.out.println(nextNode);
			}
			System.out.println();
		}
		creature.show(root);
    }

    private void setSimulationSpeed(int speed){
    	creature.setSimulationSpeed(speed);
    }
    
    private void updateCamera(){
    	int numNodes = 0;
    	double xTotal = 0;
    	double yTotal = 0;
    	
    	double translateSpeed = 1;
    	
    	for(Node temp : creature.getNodes()){
    		xTotal += temp.getX();
    		yTotal += temp.getY();
    		numNodes++;
    	}
    	double camTargetX = xTotal/numNodes - gameWidth/2;
    	double camTargetY = yTotal/numNodes - gameHeight/2;
    	
    	if(Math.abs(camera.getTranslateX() - camTargetX) > 10){
    		if(camera.getTranslateX() - camTargetX < 0)
    			camera.setTranslateX(camera.getTranslateX() + translateSpeed);
    		else
    			camera.setTranslateX(camera.getTranslateX() - translateSpeed);
    			
    	}
    	if(Math.abs(camera.getTranslateY() - camTargetY) > 10){
    		if(camera.getTranslateY() - camTargetY < 0)
    			camera.setTranslateY(camera.getTranslateY() + translateSpeed);
    		else
    			camera.setTranslateY(camera.getTranslateY() - translateSpeed);
    	}
    }

}
