package states;

//Abstract class for different state types to built from
public abstract class State {
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
