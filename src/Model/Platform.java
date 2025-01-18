package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle {
    private static final double initialPlatformWidth = OptionsModel.getSceneWidth() / 5;
    private static final double initialPlatformHeight = OptionsModel.getSceneHeight() / 30;
    private static final double initialX = OptionsModel.getSceneWidth() / 2 - initialPlatformWidth / 2;
    private static final double initialY = OptionsModel.getSceneHeight() * 0.8;
    private static final double initialVelocity = OptionsModel.getSceneHeight()*0.003 + OptionsModel.getSceneHeight()*0.002;

    private double velocity = initialVelocity;
    private boolean isMovingLeft;
    private boolean isMovingRight;

    public Platform() {
        super(initialX, initialY, initialPlatformWidth, initialPlatformHeight);
        Image image = new Image("/resources/Platform.png");
        ImagePattern pattern = new ImagePattern(image);
        setFill(pattern); 
    }

    public void updatePosition() {
        if (isMovingLeft()) {
            setX(getX() - velocity);
        }
        if (isMovingRight()) {
            setX(getX() + velocity);
        }
        if (getX() < 0) {

        } else if (getX() + getWidth()/2 > OptionsModel.getSceneWidth()) {
            setX(OptionsModel.getSceneWidth() - getWidth()/2);
        }
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

    public static double getIntialPlatformWidth() {
        return initialPlatformWidth;
    }

    public static double getInitialPlatformVelocity() {
        return initialVelocity;
    }

    //Resets Platforms position and size when changing level
    public void reset() {
        setWidth(Math.min(getWidth(), OptionsModel.getSceneWidth()*0.4));
        setX(initialX);
        setY(initialY);
    }
}
