package com.plant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Node {
	private Circle c;
	private ArrayList<Integer> connections;
	private int numConnections;

	private double friction;

	Random rand = new Random();

	public Node(double x, double y){
		c = new Circle(x, y, 12.5);
		c.setFill(Color.ALICEBLUE);

		connections = new ArrayList<Integer>();

		friction = rand.nextDouble()/2;
	}

	public void update(double dt){

	}

	public void show(Group root){
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

	public void translateX(double x){
		c.setCenterX(c.getCenterX() + x);
	}

	public void translateY(double y){
		c.setCenterY(c.getCenterY() + y);
	}

	public void addConnection(int x){
		connections.add(x);
		Collections.sort(connections);
		numConnections++;
	}

	public boolean hasConnection(int index){
		//System.out.println(Collections.binarySearch(connections, index));
		return (Collections.binarySearch(connections, index) >= 0);
	}
	
	public int getNumConnections(){
		return numConnections;
	}

	public double getFriction(){
		return friction;
	}
}

