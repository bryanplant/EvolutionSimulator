package com.plant;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import states.PlayState;

//Displays buttons for the user to control the simulation
public class UserInterface {
	private ArrayList<Button> buttons;
	private Text label;

	public UserInterface(PlayState playState){
		label = new Text("Simulation Speed:");
		label.setFill(Color.WHITE);
		label.setUnderline(true);
		label.setFont(Font.font("Times New Roman", 16));

		buttons = new ArrayList<Button>();
		buttons.add(new Button("Normal"));
		buttons.add(new Button("Fast"));
		buttons.add(new Button("Real Fast"));

		for(Button temp : buttons){
			temp.setMinWidth(75);
		}

		buttons.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                playState.setSimSpeed(1);
            }
        });

		buttons.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                playState.setSimSpeed(10);
            }
        });

		buttons.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                playState.setSimSpeed(20);
            }
        });
	}

	public void update(PerspectiveCamera camera, HBox uiBox){
	}

	public void show(HBox uiBox){
	    uiBox.setStyle("-fx-background-color: #000AB2;");
	    uiBox.setSpacing(5.0);
	    uiBox.setAlignment(Pos.CENTER_LEFT);
	    uiBox.setMinHeight(45);

		uiBox.getChildren().add(label);
		for(Button temp : buttons){
			uiBox.getChildren().add(temp);
		}
	}


}
