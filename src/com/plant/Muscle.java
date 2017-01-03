package com.plant;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Muscle {
	private Line m;
	private Node n1;
	private Node n2;

	private int speed;
	private double contractTime;
	private double expandTime;
	private double waitTime;
	private double time;
	private short state;
	private short nextState;

	private double maxLength;
	private double minLength;

	Random rand = new Random();

	public Muscle(Node n1, Node n2){
		this.n1 = n1;
		this.n2 = n2;

		m = new Line(n1.getX(), n1.getY(), n2.getX(), n2.getY());

		double x = n1.getX() - n2.getX();
		double y = n1.getY() - n2.getY();
		double length = Math.sqrt(x*x + y*y);
		maxLength = length*1.5;
		minLength = length/2.5;

		speed = rand.nextInt(150) + 50;
		contractTime = rand.nextDouble() + .75;
		expandTime = rand.nextDouble() + .75;
		waitTime = rand.nextDouble() + .5;
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

	private void moveMuscle(double dt){
		time += dt;
		if(state == 0){
			if(time > contractTime){
				state = 2;
				nextState = 1;
				time = 0;
			}
		}
		else if(state == 1){
			if(time > expandTime){
				state = 2;
				nextState = 0;
				time = 0;
			}
		}
		else if(state == 2){
			if(time > waitTime){
				state = nextState;
				time = 0;
			}
		}

		double distanceX = n1.getX() - n2.getX();
		double distanceY = n1.getY() - n2.getY();
		double magnitude = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
		double directionX = distanceX/magnitude;
		double directionY = distanceY/magnitude;

		if(state == 0 && magnitude > minLength){
			n1.translateX(-directionX*speed*dt*n1.getFriction());
			n1.translateY(-directionY*speed*dt*n1.getFriction());
			n2.translateX(directionX*speed*dt*n2.getFriction());
			n2.translateY(directionY*speed*dt*n2.getFriction());
		}
		else if(state == 1 && magnitude < maxLength){
			n2.translateX(-directionX*speed*dt*n1.getFriction());
			n2.translateY(-directionY*speed*dt*n1.getFriction());
			n1.translateX(directionX*speed*dt*n2.getFriction());
			n1.translateY(directionY*speed*dt*n2.getFriction());
		}
	}

	private void updateLine(){
		m.setStartX(n1.getX());
		m.setStartY(n1.getY());
		m.setEndX(n2.getX());
		m.setEndY(n2.getY());
	}
}
