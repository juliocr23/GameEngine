package Animation;

import java.awt.*;

public class Player extends Sprite {

    public Player(double x, double y, int w, int h,String imgFile){
        super(x,y,w,h,imgFile);
    }

    @Override
    public void processInput() {

    }

    @Override
    public void update() {


        if(initialX_Velocity + x_Acceleration <= finalX_Velocity)
            initialX_Velocity += x_Acceleration;

        if(initialY_Velocity + y_Acceleration <= finalY_Velocity)
            initialY_Velocity += y_Acceleration;

        if(isMoveRight())
            moveRight();

        if(isMoveLeft())
            moveLeft();

        if(isMoveUp())
            moveUp();

        if(isMoveDown())
            moveDown();

    }

    @Override
    public void draw(Graphics g) {

    }

    public void moveLeft(){
        x -= getInitialX_Velocity();
    }

    public void moveRight(){
        x += getInitialX_Velocity();
    }

    public void moveDown(){
        y += getInitialY_Velocity();
    }

    public void moveUp(){
        y -= getInitialY_Velocity();
    }

    public void jump(){

    }
}
