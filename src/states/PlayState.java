package states;

import com.plant.Creature;

import javafx.scene.Group;

public class PlayState extends State {
    //private Player player;
   // private Map map;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        //player = new Player();
        //map = new Map("map.txt");

        //camera.setToOrtho(true, Game.WIDTH, Game.HEIGHT);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(double dt, Creature c) {
       c.update(dt);
    }

    @Override
    protected void render(Group root, Creature c) {
    	c.draw(root);
    }

    @Override
    protected void dispose() {
        //player.dispose();
        //map.dispose();
    }

    @Override
    protected void resize(int width, int height) {
        //viewport.update(width, height);
    }

}
