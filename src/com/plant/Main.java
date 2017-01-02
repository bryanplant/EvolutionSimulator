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
import states.GameStateManager;
import states.PlayState;

public class Main extends Application{
	long lastTime = 0;

	private GameStateManager gsm;

	public static void main(String[] args){

		launch(args);
	}

	@Override
	public void init(){

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		gsm = new GameStateManager();
		gsm.push(new PlayState(gsm, primaryStage));

		AnimationTimer timer = new AnimationTimer(){

			@Override
			public void handle(long time) {
				if(lastTime == 0)
					lastTime = time;
				double dt = (time-lastTime)/1000000000.0;
				gsm.update(dt);
				gsm.render();

				lastTime = time;
			}

		};
		timer.start();
	}
}
