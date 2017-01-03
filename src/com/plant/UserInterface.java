package com.plant;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class UserInterface {
	private Button button;
	private Button button2;

	public UserInterface(){
		button = new Button("Normal");
		button2 = new Button("Fast");
	}

	public void update(PerspectiveCamera camera, HBox uiBox){
		uiBox.setLayoutX(camera.getTranslateX());
		uiBox.setLayoutY(camera.getTranslateY());
	}

	public void show(HBox uiBox){
		uiBox.getChildren().add(button);
		uiBox.getChildren().add(button2);
	}

	public void hide(Group root){
		root.getChildren().remove(button);
	}
}
