package states;

import com.plant.Creature;
import com.plant.Environment;
import com.plant.UserInterface;

import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayState extends State {

	private Scene scene;
	private Creature creature;
	private Pane root;
	private BorderPane pane;
	private HBox uiBox;
	private SubScene subScene;

	private ParallelCamera camera;

	private Environment environment;
	private UserInterface ui;

	private int gameWidth = 1280;
	private int gameHeight = 720;
	private int simSpeed = 1;
	private double cameraSpeed = 20;

	private double totalTime = 0;

	private boolean firstUpdate = true;

    public PlayState(GameStateManager gsm, Stage primaryStage)
    {
        super(gsm);

        camera = new ParallelCamera();
        root = new Pane(camera);

        environment = new Environment(gameHeight);
        environment.show(root);
        creature = new Creature(6, 3, gameWidth/2, gameHeight/2);
        creature.show(root);

        subScene = new SubScene(root, gameWidth, gameHeight);
        subScene.setCamera(camera);

	    pane = new BorderPane();
	    pane.setStyle("-fx-background-color: #336699;");
	    pane.setCenter(subScene);

	    uiBox = new HBox();

	    ui = new UserInterface(this);
	    ui.show(uiBox);

	    pane.setTop(uiBox);

	    scene = new Scene(pane);

	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Evolution Simulator");
	    primaryStage.show();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(double dt) {
	    	dt*=simSpeed;
	    	totalTime+=dt;

    	if(totalTime < 1){
    		firstUpdate = true;
    	}
    	else{
    		if(firstUpdate){
    			dt = totalTime - 1;
    		}
	    	/*if(totalTime > 10){
	    		dt = 10 - lastTime;
	    		System.out.println(lastTime);
	    	}*/

	    	creature.update(dt);
	    	updateCamera(dt);

	    	/*if(totalTime >= 10){
	    		System.out.println(creature.getAverageX() + ", " + creature.getAverageY());
	    		creature.reset(gameWidth/2, gameHeight/2);
	    		totalTime = 0;
	    	}*/
	    	firstUpdate = false;
    	}
	}

    @Override
    protected void render() {

    }

    @Override
    protected void dispose() {
    }

    @Override
    protected void resize(int width, int height) {
        camera.resize(width, height);
    }

    private void updateCamera(double dt){
    	if(Math.abs(camera.getTranslateX() + gameWidth/2 - creature.getAverageX()) > 50){
	    	if(camera.getTranslateX() + gameWidth/2 < creature.getAverageX())
	    		camera.setTranslateX(camera.getTranslateX() + cameraSpeed*dt);
	    	else if(camera.getTranslateX() + gameWidth/2 > creature.getAverageX())
	    		camera.setTranslateX(camera.getTranslateX() - cameraSpeed*dt);
    	}
    	if(Math.abs(camera.getTranslateY() + gameHeight/2 - creature.getAverageY()) > 50){
	    	if(camera.getTranslateY() + gameHeight/2 < creature.getAverageY())
	    		camera.setTranslateY(camera.getTranslateY() + cameraSpeed*dt);
	    	else if(camera.getTranslateY() + gameHeight/2 > creature.getAverageY())
	    		camera.setTranslateY(camera.getTranslateY() - cameraSpeed*dt);
    	}
    }

    public void setSimSpeed(int speed){
    	simSpeed = speed;
    }
}
