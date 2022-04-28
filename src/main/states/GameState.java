package main.states;

public abstract class GameState {
    protected GameStateManager gsm;
    
    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void update(double time);
    public abstract void input();
    public abstract void render();
}
