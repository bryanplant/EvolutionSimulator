package com.plant;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.Pane;

public class Creature {
	private ArrayList<Node> nodes;
	private ArrayList<Muscle> muscles;
	private int numNodes;
	private int numMuscles;

	Random rand = new Random();

	public Creature(int numNodes, int numMuscles){
		nodes = new ArrayList<Node>();
		muscles = new ArrayList<Muscle>();
		this.numNodes = numNodes;
		this.numMuscles = numMuscles;

    	Random rand = new Random();
    	int newMuscles;

    	for(int i = 0; i < numNodes; i ++){
    		addNode(new Node(rand.nextInt(200) + 500, rand.nextInt(200) + 300));
    	}

		for(int i = 0; i < numNodes; i++){
			if(nodes.get(i).getNumConnections() >= numMuscles)
				newMuscles = 0;
			else
				newMuscles = numMuscles;

			for(int j = 0; j < newMuscles; j++){
				int nextNode = rand.nextInt(numNodes);
				while(nodes.get(i).hasConnection(nextNode) || nextNode == i){
					nextNode = rand.nextInt(numNodes);
				}

				addMuscle(new Muscle(nodes.get(i), nodes.get(nextNode)));
				nodes.get(i).addConnection(nextNode);
				nodes.get(nextNode).addConnection(i);
			}
		}
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

	public int getNumNodes(){
		return numNodes;
	}

	public int getNumMuscles(){
		return numMuscles;
	}

	public ArrayList<Node> getNodes(){
		return nodes;
	}

	public void show(Pane root){
		for(Node temp: nodes){
			temp.show(root);
		}

		for(Muscle temp: muscles){
			temp.show(root);
		}
	}

	public void hide(Pane root){
		for(Node temp: nodes){
			temp.hide(root);
		}

		for(Muscle temp: muscles){
			temp.hide(root);
		}
	}
}
