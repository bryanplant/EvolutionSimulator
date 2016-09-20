package com.plant;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Node {
	private Circle c;
	
	public Node(double x, double y){
		c = new Circle(x, y, 25);
		c.setFill(Color.ALICEBLUE);
	}
	
	public Circle getCircle(){
		return c;
	}
	
	public void draw(Group root){
		root.getChildren().add(c);
	}
	
	public void hide(Group root){
		root.getChildren().remove(c);
	}
}
