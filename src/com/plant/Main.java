package com.plant;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import states.GameStateManager;
import states.PlayState;

//updates the GameStateManager with change in time
public class Main extends Application{
	private long lastTime = 0;

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

		new AnimationTimer(){

			@Override
			public void handle(long time) {
				if(lastTime == 0)
					lastTime = time;
				else{
					double dt = (time-lastTime)/1000000000.0;
					gsm.update(dt);
					gsm.render();
				}
				lastTime = time;
			}

		}.start();
	}
}
