package com.plant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//A node has friction and a list of nodes that it is connected to
public class Node {
	private Circle c;
	private ArrayList<Integer> connections;
	private int numConnections;

	private double friction;

	Random rand = new Random();

	//creates a new node at (x, y)
	public Node(double x, double y){
		c = new Circle(x, y, 12.5);
		c.setFill(Color.ALICEBLUE);

		connections = new ArrayList<Integer>();

		friction = rand.nextDouble()/2;
	}
	public void show(Pane root){
		root.getChildren().add(c);
	}

	public void hide(Pane root){
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

	public void setFriction(double friction){
		this.friction = friction;
	}

	public void translateX(double x){
		c.setCenterX(c.getCenterX() + x);
	}

	public void translateY(double y){
		c.setCenterY(c.getCenterY() + y);
	}

	//Keeps track of what nodes this node is already connected to
	public void addConnection(int x){
		connections.add(x);
		Collections.sort(connections);
		numConnections++;
	}

	public boolean hasConnection(int index){
		return (Collections.binarySearch(connections, index) >= 0);
	}

	public int getNumConnections(){
		return numConnections;
	}

	public double getFriction(){
		return friction;
	}
}

