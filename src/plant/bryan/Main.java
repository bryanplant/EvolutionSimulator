package plant.bryan;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Evolution Simulator");
		Group root = new Group();
		Canvas canvas = new Canvas(1280,720);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Creature c = new Creature();

		Circle circle = new Circle(200,200,25);
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);

		root.getChildren().add(canvas);
		c.draw(root);
		c.hide(root);
		
		Scene scene = new Scene(root, Color.GRAY);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
