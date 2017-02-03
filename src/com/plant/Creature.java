package com.plant;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.Pane;

public class Creature {
	private ArrayList<Node> nodes;
	private ArrayList<Muscle> muscles;
	private int numNodes;
	private int numMuscles;

	private ArrayList<Integer> startPosX;
	private ArrayList<Integer> startPosY;

	Random rand = new Random();

	public Creature(int numNodes, int numMuscles, double x, double y){
		nodes = new ArrayList<Node>();
		muscles = new ArrayList<Muscle>();
		this.numNodes = numNodes;
		this.numMuscles = numMuscles;

		startPosX = new ArrayList<Integer>();
		startPosY = new ArrayList<Integer>();

    	Random rand = new Random();
    	int newMuscles;

    	for(int i = 0; i < numNodes; i ++){
    		int startX = rand.nextInt(200);
    		int startY = rand.nextInt(200);
    		nodes.add(new Node(startX, startY));
    		startPosX.add(startX);
    		startPosY.add(startY);
    	}

		for(int i = 0; i < numNodes; i++){
			newMuscles = numMuscles - nodes.get(i).getNumConnections();

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
		center(x, y);

		for(Muscle temp: muscles){
			temp.update(0);
		}
	}

	public void update(double dt){
		for(Muscle temp: muscles){
			temp.update(dt);
		}
	}

	public void center(double x, double y){
		double diffX = x - getAverageX();
		double diffY = y - getAverageY();

		for(Node temp : nodes){
			temp.setX(temp.getX() + diffX);
			temp.setY(temp.getY() + diffY);
		}
	}

	public void reset(double x, double y){
		for(int i = 0; i < nodes.size(); i++){
			nodes.get(i).setX(startPosX.get(i));
			nodes.get(i).setY(startPosY.get(i));
		}
		center(x, y);

		for(Muscle temp : muscles){
			temp.reset();
		}
		update(0);
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

	public double getAverageX(){
		double totalX = 0;
		for(Node temp : nodes){
			totalX+=temp.getX();
		}
		return(totalX/numNodes);
	}

	public double getAverageY(){
		double totalY = 0;
		for(Node temp : nodes){
			totalY+=temp.getY();
		}
		return(totalY/numNodes);
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
