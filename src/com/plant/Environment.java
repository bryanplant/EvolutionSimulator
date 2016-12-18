package com.plant;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Environment {
	private ArrayList<Rectangle> rects;
	private double width;
	private double height;
	private int numRects;
	
	public Environment(double height){
		width = 200;
		this.height = height;
		numRects = 10;
		
		rects = new ArrayList<Rectangle>();
		for(int i = 0; i < 10; i++){
			rects.add(new Rectangle(i*width, -height, width, height*3));
			rects.get(i).setFill(new Color(0, i*.7/numRects + .3, 0, 1));
		}
	}
	
	public void show(Group root){
		for(Rectangle temp : rects){
			root.getChildren().add(temp);
		}
	}

}
