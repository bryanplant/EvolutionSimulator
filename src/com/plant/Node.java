package com.plant;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Node {
	private Circle c;
	private double speedX;
	private double speedY;
	private double gravity = 5;
	private long lastTime;
	private long dt;

	public Node(double x, double y){
		c = new Circle(x, y, 12.5);
		c.setFill(Color.ALICEBLUE);
		speedX = 0;
		speedY = 0;
	}

	public void update(double dt){
		speedY+=gravity*dt;
		setY(getY()+speedY);
	}

	public void render(Group root){
		root.getChildren().add(c);
	}

	public void hide(Group root){
		root.getChildren().remove(c);
	}

	public Circle getCircle(){
		return c;
	}

	public double getX(){
		return c.getCenterX();
	}

	public double getY(){
		return c.getCenterY();
	}

	public void setX(double x){
		c.setCenterX(x);
	}

	public void setY(double y){
		c.setCenterY(y);
	}
}
