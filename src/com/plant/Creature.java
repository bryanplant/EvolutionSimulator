package com.plant;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

public class Creature {
	private Node n1;
	private Node n2;
	
	public Creature()
	{
		n1 = new Node(100, 100);
		n2 = new Node(150, 250);
	}
	
	public void draw(Group root){
		n1.draw(root);
		n2.draw(root);
	}
	
	public void hide(Group root){
		n1.hide(root);
		n2.hide(root);
	}
}
