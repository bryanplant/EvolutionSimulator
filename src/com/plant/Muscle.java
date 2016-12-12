package com.plant;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Muscle {
	private Line m;
	private Node[] n = new Node[2];
	
	public Muscle(Node n1, Node n2){
		n[0] = n1;
		n[1] = n2;
		m = new Line(n[0].getX(), n[0].getY(), n[1].getX(), n[1].getY());
	}
	
	public void update(){
		m.setStartX(n[0].getX());
		m.setStartY(n[0].getY());
		m.setEndX(n[1].getX());
		m.setEndY(n[1].getY());
	}
	
	public void render(Group root){
		root.getChildren().add(m);
	}
	
	public Line getLine(){
		return m;
	}
}
