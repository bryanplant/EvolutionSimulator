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

		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.strokeRect(50, 50, 100, 100);
		gc.fillOval(200, 200, 50, 50);

		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.setFill(Color.PINK);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
