package states;

import java.util.Stack;

//Manages what state the simulation is in. e.g. Menu screen, simulation screen
public class GameStateManager {
	private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){states.push(state);}

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(double dt){
        states.peek().update(dt);
    }

    public void render(){
        states.peek().render();
    }

    public void resize(int width, int height) {
        states.peek().resize(width, height);
    }
}
