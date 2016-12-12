package com.plant;

import javafx.animation.AnimationTimer;
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
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private Scene scene;
	private Creature c;
	private long start;

	public static void main(String[] args){

		launch(args);
	}

	@Override
	public void init(){
		root = new Group();
		canvas = new Canvas(1280,720);
		gc = canvas.getGraphicsContext2D();

		root.getChildren().add(canvas);

		scene = new Scene(root, Color.GRAY);

		c = new Creature();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Evolution Simulator");
		primaryStage.setScene(scene);
		primaryStage.show();
		start = System.nanoTime();

		AnimationTimer timer = new AnimationTimer(){

			@Override
			public void handle(long arg0) {
				c.update(arg0);
				c.draw(root);
			}

		};
		timer.start();
	}
}
