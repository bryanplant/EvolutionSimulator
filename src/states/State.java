package states;

import com.plant.Creature;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;

public abstract class State {
    protected ParallelCamera camera;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm)
    {
        this.gsm = gsm;
    }

    protected abstract void handleInput();
    protected abstract void update(double dt);
    protected abstract void render();
    protected abstract void dispose();
    protected abstract void resize(int width, int height);
}
