package org.scorpion.state;

public class GameState {

    int state;

    public GameState(int state){
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
