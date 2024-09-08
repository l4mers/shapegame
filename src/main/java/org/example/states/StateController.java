package org.example.states;

import org.example.GamePanel;

import java.awt.*;

public class StateController {

    private UpdateAndDraw currentState;
    public StateController(GamePanel gp) {
        currentState = new LoginState(gp);
    }

    public void setCurrentState(UpdateAndDraw currentState) {
        this.currentState = currentState;
    }

    public void update() {
        currentState.update();
    }

    public void draw(Graphics2D g2) {
        currentState.draw(g2);
    }
}
