package org.example;

import org.example.api.UserInfo;

import java.awt.*;
import java.awt.event.*;

public class InputListener implements MouseListener, MouseMotionListener, KeyListener {

    public Rectangle mouse = new Rectangle(0,0, 5, 5);
    public boolean pressed = false;
    public boolean release = false;
    public boolean clicked = false;
    public boolean loginMarked = false;
    public boolean passwordMarked = false;

    private boolean added = false;
    public UserInfo userInfo = new UserInfo("","");

    private void setMouse(int x, int y){
        mouse.x = x;
        mouse.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clicked = true;
        setMouse(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setMouse(e.getX(), e.getY());
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setMouse(e.getX(), e.getY());
        pressed = false;
        release = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setMouse(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setMouse(e.getX(), e.getY());
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        if(passwordMarked){
//            userInfo.password
//        } else if (loginMarked){
//
//        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
