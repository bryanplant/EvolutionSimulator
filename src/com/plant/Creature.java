package com.plant;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

public class Creature {
	private ArrayList<Node> nodes;
	private ArrayList<Muscle> muscles;

	Random rand = new Random();

	public Creature(){
		nodes = new ArrayList<Node>();
		muscles = new ArrayList<Muscle>();


	}

	public void update(double dt){
		for(Node temp: nodes){
			temp.update(dt);
		}
		for(Muscle temp: muscles){
			temp.update(dt);
		}
	}

	public void addNode(Node node){
		nodes.add(node);
	}
	
	public void addMuscle(Muscle muscle){
		muscles.add(muscle);
	}
	
	public Node getNode(int index){
		return nodes.get(index);
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
	
	public void setSimulationSpeed(int speed){
		for(Muscle i : muscles){
			i.setSimulationSpeed(speed);
		}
	}
}
