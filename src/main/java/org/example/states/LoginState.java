package org.example.states;

import org.example.GamePanel;
import org.example.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginState implements UpdateAndDraw{

    private final GamePanel gp;
    private final Rectangle[] options;
    private final BufferedImage logo;
    private String password = "Password";
    private String username = "User Name";

    public LoginState(GamePanel gp){
        this.gp = gp;
        options = new Rectangle[5];
        options[3] = new Rectangle();
        options[4] = new Rectangle();
        this.logo = UtilityTool.loadUnscaledImage("/logo.png");
    }
    @Override
    public void update() {
        if (gp.inputListener.clicked) {
            gp.inputListener.clicked = false;
            for (int i = 0; i < options.length; i++) {
                switch (i) {
                    case 0 -> {
                        if (options[i].intersects(gp.inputListener.mouse)) {
                            gp.stateController.setCurrentState(new PlayState(gp));
                        }
                    }
                    case 1 -> {
                        if (options[i].intersects(gp.inputListener.mouse)) {
                            System.out.println("High Score");
                        }
                    }
                    case 2 -> {
                        if (options[i].intersects(gp.inputListener.mouse)) {
                            System.exit(0);
                        }
                    }
                    case 3 -> {
                        if (options[i].intersects(gp.inputListener.mouse)) {
                            gp.inputListener.passwordMarked = true;
                            gp.inputListener.loginMarked = false;
                            if(password.equals("Password")){
                                password = "";
                            }
                            if (username.isEmpty()){
                                username = "User Name";
                            }
                        }
                    }
                    default -> {
                        if (options[i].intersects(gp.inputListener.mouse)) {
                            gp.inputListener.loginMarked = true;
                            gp.inputListener.passwordMarked = false;
                            if (username.equals("User Name")) {
                                username = "";
                            }
                            if (password.isEmpty()) {
                                password = "Password";
                            }
                        }
                    }
                }
            }
        }
        if(gp.inputListener.passwordMarked){
            if(!gp.inputListener.userInfo.password.isEmpty()){
                password = gp.inputListener.userInfo.password;
            }
        }
        if(gp.inputListener.loginMarked){
            if(!gp.inputListener.userInfo.userName.isEmpty()){
                username = gp.inputListener.userInfo.userName;
            }
        }
    }


    @Override
    public void draw(Graphics2D g2) {
        int x;
        int y = gp.screenHeight / 2 + gp.tileSize;

        g2.drawImage(logo, 0, 0, null);

        g2.setColor(Color.LIGHT_GRAY);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));

//        if(!gp.inputListener.passwordMarked && gp.inputListener.userInfo.password.isEmpty()){
//            g2.drawString(password, gp.screenHeight / 2 + gp.tileSize, gp.screenHeight / 2 - gp.tileSize / 2 - 5);
//        }
        g2.drawString(password, gp.screenHeight / 2 + gp.tileSize, gp.screenHeight / 2 - gp.tileSize / 2 - 5);
        options[3].setBounds( gp.screenHeight / 2 + gp.tileSize, gp.screenHeight / 2 - gp.tileSize, gp.screenWidth / 2 - gp.tileSize * 2, gp.tileSize / 2);
        g2.drawRect(gp.screenHeight / 2 + gp.tileSize, gp.screenHeight / 2 - gp.tileSize, gp.screenWidth / 2 - gp.tileSize * 2, gp.tileSize / 2);

//        if(!gp.inputListener.loginMarked && gp.inputListener.userInfo.userName.isEmpty()){
//            g2.drawString(username, gp.tileSize, gp.screenHeight / 2 - gp.tileSize / 2 - 5);
//        }
        g2.drawString(username, gp.tileSize, gp.screenHeight / 2 - gp.tileSize / 2 - 5);
        options[4].setBounds(gp.tileSize, gp.screenHeight / 2 - gp.tileSize, gp.screenWidth / 2 - gp.tileSize * 2, gp.tileSize / 2);
        g2.drawRect(gp.tileSize, gp.screenHeight / 2 - gp.tileSize, gp.screenWidth / 2 - gp.tileSize * 2, gp.tileSize / 2);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        String text = "Login";
        x  = getCenterForX(g2, text);
        options[0] = getRectangleForText(g2, text, x, y);
        if(options[0].intersects(gp.inputListener.mouse)){
            g2.fillRect(options[0].x + 2, options[0].y + (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight(), options[0].width, 2);
        }
        g2.drawString(text, x, y);

        text = "Guest";
        x  = getCenterForX(g2, text);
        y += gp.tileSize * 2;
        options[1] = getRectangleForText(g2, text, x, y);
        if(options[1].intersects(gp.inputListener.mouse)){
            g2.fillRect(options[1].x + 2, options[1].y + (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight(), options[1].width, 2);
        }
        g2.drawString(text, x, y);

        text = "Back";
        x  = getCenterForX(g2, text);
        y += gp.tileSize * 2;
        options[2] = getRectangleForText(g2, text, x, y);
        if(options[2].intersects(gp.inputListener.mouse)){
            g2.fillRect(options[2].x + 2, options[2].y + (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight(), options[2].width, 2);
        }
        g2.drawString(text, x, y);
    }

    private int getCenterForX(Graphics2D g2, String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
    private Rectangle getRectangleForText(Graphics2D g2, String text, int x, int y){
        int height = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
        return new Rectangle(
                x, y - height + 10,
                (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(),
                height
        );
    }
}