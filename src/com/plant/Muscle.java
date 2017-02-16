package com.plant;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;


//A muscle expands and contracts and moves the connected nodes
public class Muscle {
	private Line m;
	private Node n1;
	private Node n2;

	private int contractSpeed;
	private int expandSpeed;
	private double contractTime;
	private double expandTime;
	private double waitTime;
	private double time;
	private double lastTime;
	private double lastMoveTime;
	private short state;
	private short nextState;

	private double maxLength;
	private double minLength;
	private boolean maxed;
	private boolean mined;

	Random rand = new Random();

	public Muscle(Node n1, Node n2){
		this.n1 = n1;
		this.n2 = n2;

		m = new Line(n1.getX(), n1.getY(), n2.getX(), n2.getY());

		double x = n1.getX() - n2.getX();
		double y = n1.getY() - n2.getY();
		double length = Math.sqrt(x*x + y*y);
		maxLength = length*2.5;
		minLength = length/2;

		time = 0;
		lastTime = 0;
		lastMoveTime = 0;
		state = 0;
		contractSpeed = rand.nextInt(150) + 50;
		expandSpeed = rand.nextInt(150) + 50;
		contractTime = rand.nextDouble() + .75;
		expandTime = rand.nextDouble() + .75;
		waitTime = rand.nextDouble() + .5;
		maxed = false;  //if the muscle is at its max length
		mined = false;  //if the muscle is at its minimum length
	}

	public void update(double dt){
		moveMuscle(dt);
		updateLine();
	}

	public void show(Pane root){
		root.getChildren().add(m);
	}

	public void hide(Pane root){
		root.getChildren().remove(m);
	}

	public Line getLine(){
		return m;
	}

	//updates the position of the connected nodes based on dt, speed, and friction
	private void moveMuscle(double dt){
		time += dt;

		double distanceX = n1.getX() - n2.getX();
		double distanceY = n1.getY() - n2.getY();
		double magnitude = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
		double directionX = distanceX/magnitude;
		double directionY = distanceY/magnitude;

		if(state == 0){
			if(time > contractTime){
				state = 2;
				nextState = 1;

				lastMoveTime = contractTime-lastTime;
				time = 0;
			}
		}
		else if(state == 1){
			if(time > expandTime){
				state = 2;
				nextState = 0;

				lastMoveTime = expandTime-lastTime;
				time = 0;
			}
		}
		else if(state == 2){
			if(time > waitTime){
				state = nextState;
				time = time-waitTime;
			}
		}

		if(state == 0){
			n1.translateX(-directionX*contractSpeed*dt*n1.getFriction());
			n1.translateY(-directionY*contractSpeed*dt*n1.getFriction());
			n2.translateX(directionX*contractSpeed*dt*n2.getFriction());
			n2.translateY(directionY*contractSpeed*dt*n2.getFriction());
		}
		else if(state == 1){
			n2.translateX(-directionX*expandSpeed*dt*n1.getFriction());
			n2.translateY(-directionY*expandSpeed*dt*n1.getFriction());
			n1.translateX(directionX*expandSpeed*dt*n2.getFriction());
			n1.translateY(directionY*expandSpeed*dt*n2.getFriction());
		}
		else if(state == 2 && time == 0){
			if(nextState == 0){
				n2.translateX(-directionX*expandSpeed*lastMoveTime*n1.getFriction());
				n2.translateY(-directionY*expandSpeed*lastMoveTime*n1.getFriction());
				n1.translateX(directionX*expandSpeed*lastMoveTime*n2.getFriction());
				n1.translateY(directionY*expandSpeed*lastMoveTime*n2.getFriction());
			}
			else{
				n2.translateX(-directionX*expandSpeed*lastMoveTime*n1.getFriction());
				n2.translateY(-directionY*expandSpeed*lastMoveTime*n1.getFriction());
				n1.translateX(directionX*expandSpeed*lastMoveTime*n2.getFriction());
				n1.translateY(directionY*expandSpeed*lastMoveTime*n2.getFriction());
			}
		}
		maxed = (magnitude > maxLength);
		mined = (magnitude < minLength);

		if(maxed){

			n1.translateX(-directionX*(magnitude-maxLength)/2);
			n1.translateY(-directionY*(magnitude-maxLength)/2);
			n2.translateX(directionX*(magnitude-maxLength)/2);
			n2.translateY(directionY*(magnitude-maxLength)/2);
		}
		else if(mined){
			n1.translateX(directionX*(minLength-magnitude)/2);
			n1.translateY(directionY*(minLength-magnitude)/2);
			n2.translateX(-directionX*(minLength-magnitude)/2);
			n2.translateY(-directionY*(minLength-magnitude)/2);
		}

		lastTime = time;
	}

	//redraws the muscle line
	private void updateLine(){
		m.setStartX(n1.getX());
		m.setStartY(n1.getY());
		m.setEndX(n2.getX());
		m.setEndY(n2.getY());
	}

	//resets muscle to starting state
	public void reset(){
		time = 0;
		state = 0;
		mined = false;
		maxed = false;
	}
}
