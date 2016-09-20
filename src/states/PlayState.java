package states;

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
    protected void update(float dt) {
        //player.update(dt, camera, map);
        //camera.update();
    }

    @Override
    protected void render() {

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
