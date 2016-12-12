package com.plant;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Node {
	private Circle c;
	private double speedX;
	private double speedY;
	private double gravity = 10;
	private long lastTime;
	public Node(double x, double y){
		c = new Circle(x, y, 12.5);
		c.setFill(Color.ALICEBLUE);
		speedX = 0;
		speedY = 0;
	}

	public void move(long time){
		if(lastTime == 0)
			lastTime = time;
		speedY+=gravity*((time - lastTime)/1000000000.0);
		setY(getY()+speedY);
		
		if(getY() >= 720 && getY() <= 730){
			System.out.println(speedY);
			System.out.println(getY());
			System.out.println((time - lastTime)/100000000000.0);
			setY(720);
		}
		lastTime = time;
	}

	public void draw(Group root){
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
