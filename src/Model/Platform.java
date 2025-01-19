//this file allows the platform to be constructed and keeps track of its state
//written by Halfdan & Marcus
package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle {
    //default properties, also used for upgrades
    private static final double INITIAL_PLATFORM_WIDTH = OptionsModel.getSceneWidth() / 5;
    private static final double INITIAL_PLATFORM_HEIGHT = OptionsModel.getSceneHeight() / 30;
    private static final double INITIAL_X = OptionsModel.getSceneWidth() / 2 - INITIAL_PLATFORM_WIDTH / 2;
    private static final double INITIAL_Y = OptionsModel.getSceneHeight() * 0.8;
    //making velocity scale with screensize (ball would otherwise be slower on larger screens), also used for upgrades
    private static final double INITIAL_VELOCITY = OptionsModel.getSceneHeight()*0.003 + OptionsModel.getSceneHeight()*0.002;

    private double velocity = INITIAL_VELOCITY;
    private boolean isMovingLeft;
    private boolean isMovingRight;

    public Platform() {
        super(INITIAL_X, INITIAL_Y, INITIAL_PLATFORM_WIDTH, INITIAL_PLATFORM_HEIGHT);
        Image image = new Image("/resources/Platform.png");
        setFill(new ImagePattern(image)); 
    }

    public void updatePosition() {
        if (isMovingLeft()) {
            setX(getX() - velocity);
        }
        if (isMovingRight()) {
            setX(getX() + velocity);
        }
        //stops more than half of the platform from going into the sides
        if (getX() < -getWidth()/2) {
            setX(-getWidth()/2);
        } else if (getX() + getWidth()/2 > OptionsModel.getSceneWidth()) {
            setX(OptionsModel.getSceneWidth() - getWidth()/2);
        }
    }

    // Getters Setter & Adders
    public void addPlatformWidth(double platformWidth) { setWidth(getWidth()+platformWidth); }
    public double getVelocity() { return this.velocity; }
    public void setVelocity(double velocity) { this.velocity = velocity; }
    public void addVelocity(double velocity) { this.velocity += velocity; }
    public boolean isMovingLeft() { return isMovingLeft; }
    public void setMovingLeft(boolean bool) { this.isMovingLeft = bool; }
    public boolean isMovingRight() { return isMovingRight; }
    public void setMovingRight(boolean bool) { this.isMovingRight = bool; }

    public static double getIntialPlatformWidth() { return INITIAL_PLATFORM_WIDTH;}
    public static double getInitialPlatformVelocity() { return INITIAL_VELOCITY; }

    //Resets Platforms position and size when changing level
    public void reset() {
        setWidth(Math.min(getWidth(), OptionsModel.getSceneWidth()*0.4));
        setX(INITIAL_X);
        setY(INITIAL_Y);
    }
}
