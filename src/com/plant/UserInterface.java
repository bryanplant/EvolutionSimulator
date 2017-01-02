package com.plant;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UserInterface {
	private Button button;
	private Button button2;
	private Text text;
	
	public UserInterface(){
		button = new Button();
		button.setMinSize(75, 30);
		button2 = new Button();

		text = new Text((int)button.getTranslateX(), (int)button.getTranslateY(), "Normal");
	}
	
	public void update(PerspectiveCamera camera){
		button.setLayoutX(camera.getTranslateX());
		button.setLayoutY(camera.getTranslateY());
		
		text.setTranslateX(camera.getTranslateX());
		text.setTranslateY(camera.getTranslateY());
	}
	
	public void show(Group root){
		root.getChildren().add(button);
		root.getChildren().add(button2);
		root.getChildren().add(text);
	}
	
	public void hide(Group root){
		root.getChildren().remove(button);
	}
}
