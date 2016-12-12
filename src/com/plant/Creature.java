package com.plant;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

public class Creature {
	private ArrayList<Node> nodes;
	
	public Creature(){
		nodes = new ArrayList<Node>();
		nodes.add(new Node(50, 20));
	}
	
	public void update(long time){	
		for(Node temp: nodes){
			temp.move(time);
		}
	}
	
	public void addNode(Node node){
		nodes.add(node);
	}
	
	public void draw(Group root){
		if(!root.getChildren().contains(nodes.get(0).getCircle())){
			for(Node temp: nodes){
				temp.draw(root);
			}
		}
	}
	
	public void hide(Group root){
		for(Node temp: nodes){
			temp.hide(root);
		}
	}
}
