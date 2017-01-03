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
import javafx.scene.transform.Translate;
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
	private int simSpeed;

    public PlayState(GameStateManager gsm, Stage primaryStage)
    {
        super(gsm);

        camera = new ParallelCamera();
        root = new Pane(camera);

        environment = new Environment(gameHeight);
        environment.show(root);
        creature = new Creature(4, 2);
        creature.show(root);

        subScene = new SubScene(root, gameWidth, gameHeight);
        subScene.setCamera(camera);

	    pane = new BorderPane();
	    pane.setCenter(subScene);

	    uiBox = new HBox();
	    ui = new UserInterface();
	    ui.show(uiBox);

	    pane.setTop(uiBox);

	    scene = new Scene(pane);

	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Evolution Simulator");
	    primaryStage.show();

	    simSpeed = 20;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(double dt) {
    	dt*=simSpeed;
    	updateCamera(2);
    	creature.update(dt);
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

    private void updateCamera(int speed){
    	camera.getTransforms().add(new Translate(-3, 0));
    }

}
