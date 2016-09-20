package states;

import javafx.scene.Camera;
import javafx.scene.ParallelCamera;

public abstract class State {
    protected ParallelCamera camera;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm)
    {
        this.gsm = gsm;
        camera = new ParallelCamera();
    }

    protected abstract void handleInput();
    protected abstract void update(float dt);
    protected abstract void render();
    protected abstract void dispose();
    protected abstract void resize(int width, int height);
}
