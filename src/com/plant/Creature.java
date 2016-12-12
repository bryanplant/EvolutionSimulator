package com.plant;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

public class Creature {
	private ArrayList<Node> nodes;
	private ArrayList<Muscle> muscles;
	
	public Creature(){
		nodes = new ArrayList<Node>();
		muscles = new ArrayList<Muscle>();
		nodes.add(new Node(50, 50));
		nodes.add(new Node(150, 20));
		
		muscles.add(new Muscle(nodes.get(0), nodes.get(1)));
	}
	
	public void update(double dt){	
		for(Node temp: nodes){
			temp.update(dt);
		}
		for(Muscle temp: muscles){
			temp.update();
		}
	}
	
	public void addNode(Node node){
		nodes.add(node);
	}
	
	public void draw(Group root){
		if(!root.getChildren().contains(nodes.get(0).getCircle())){
			for(Node temp: nodes){
				temp.render(root);
			}
		}
		
		if(!root.getChildren().contains(muscles.get(0).getLine())){
			for(Muscle temp: muscles){
				temp.render(root);
			}
		}
	}
	
	public void hide(Group root){
		for(Node temp: nodes){
			temp.hide(root);
		}
	}
}
