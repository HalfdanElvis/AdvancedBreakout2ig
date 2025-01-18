package Model;

import Main.SceneManager;
import View.GameView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CollisionManager {
    public static void blockCollision(Ball ball, ArrayList<Block> blockList, GameView gameView) {
        int hitTop = 0;
        int hitBottom = 0;
        int hitRight = 0;
        int hitLeft = 0;
        int hitCount = 0;
        int deathCount = 0;
        
        // Checks all blocks
        for (int i = blockList.size()-1; i >= 0; i--){
            Block block = blockList.get(i);
            if (ball.getBoundsInParent().intersects(block.getBoundsInParent())){
                
                // Plays sfx
                SceneManager.getInstance().playHitSFX();
                hitCount++;
                
                // Checks which side of the block got hit
                if (ball.getY() < block.getY()) {
                    hitTop++;
                }
                if (ball.getY() > block.getY() + block.getHeight()) {
                    hitBottom++;
                }
                if (ball.getX() > block.getX() + block.getWidth()) {
                    hitRight++;
                }
                if (ball.getX() < block.getX()) {
                    hitLeft++;
                }

                // Checks if the ball is able to break the block
                if (block.getHp()-ball.getAttack() <= 0) {

                    // Play sfx
                    SceneManager.getInstance().playBlockBreakSFX();

                    // Increases Score and Combo
                    gameView.getPlayer().updateCurrentScore((long) (block.getScore()+ball.getCombo()*block.getScore()*0.5));
                    ball.addCombo();

                    // Remove Block
                    blockList.remove(i);
                    gameView.getChildren().remove(block); 

                    deathCount++;
                    
                    // Updates the balls piercing
                    ball.setCurrentPierce(ball.getCurrentPierce()-1);

                } else {
                    // Removes HP from the block if the ball isn't able to break it
                    block.looseHp(ball.getAttack());
                    block.updateOpacity();
                    ball.setCurrentPierce(0);
                }
            }
        }
        //check if the ball should bounce based on blocks deleted and pierce
        if (deathCount != hitCount || ball.getCurrentPierce()+1 < hitCount) {
            if (hitCount == 1) {  //if exactly 1 block was hit, checks which corner it hit and ball's current angle to determine new angle
                //top left corner
                if (hitLeft == 1 && hitTop == 1) {
                    if (ball.getAngle() > 0 && ball.getAngle() < 90) {
                        ball.sideHit();
                        ball.topBottomHit();
                    }
                    else if (ball.getAngle() < 0 && ball.getAngle() > -90) {
                        ball.sideHit();
                    }
                    else {
                        ball.topBottomHit();
                    }
                }
                //bottom left corner
                else if (hitLeft == 1 && hitBottom == 1) {
                    if (ball.getAngle() < 0 && ball.getAngle() > -90) {
                        ball.sideHit();
                        ball.topBottomHit();
                    }
                    else if (ball.getAngle() > 0 && ball.getAngle() < 90) {
                        ball.sideHit();
                    }
                    else {
                        ball.topBottomHit();
                    }
                }
                //top right corner
                else if (hitRight == 1 && hitTop == 1) {
                    if (ball.getAngle() > 90 && ball.getAngle() < 180) {
                        ball.sideHit();
                        ball.topBottomHit();
                    }
                    else if (ball.getAngle() < -90 && ball.getAngle() > -180) {
                        ball.sideHit();
                    }
                    else {
                        ball.topBottomHit();
                    }
                }
                //bottom right corner
                else if (hitRight == 1 && hitBottom == 1) {
                    if (ball.getAngle() < -90 && ball.getAngle() > -180) {
                        ball.sideHit();
                        ball.topBottomHit();
                    }
                    else if (ball.getAngle() > 90 && ball.getAngle() < 180) {
                        ball.sideHit();
                    }
                    else {
                        ball.topBottomHit();
                    }
                }
                else if (hitRight == 1 || hitLeft == 1) {
                    ball.sideHit();
                }
                else {
                    ball.topBottomHit();
                }
            }
            //if hit 2 or more blocks, bounce from the side that was hit the most
            else if (hitCount >= 2) {
                if ((Math.max(hitTop, hitBottom)) >= (Math.max(hitLeft, hitRight))) {
                    ball.topBottomHit();
                }
                if ((Math.max(hitTop, hitBottom)) <= (Math.max(hitLeft, hitRight))) {
                    ball.sideHit();
                }
            }
            //normalizes angle between 180 and -180
            while (ball.getAngle() > 180) {
                ball.setAngle(ball.getAngle()-360);
            }
            while (ball.getAngle() < -180) {
                ball.setAngle(ball.getAngle()+360);
            }
        }
    }

    public static void collisionWithPlatform(Ball ball, Platform platform) {
        if (ball.getBoundsInParent().intersects(platform.getBoundsInParent())){

            // Prevents ball being caught in platform
            if (!ball.isInPlatform()) {

                // Check if ball center is still above platform
                if (ball.getY()<platform.getY()+platform.getHeight()){

                    ball.setCurrentPierce(ball.getMaxPierce());
                    ball.resetCombo();
                    ball.setInPlatform(true); 

                    // Play sfx
                    SceneManager.getInstance().playHitSFX();

                    // Finds how far along the platform the collison is
                    double collisionPoint = ((ball.getX()-platform.getX())/platform.getWidth());
                    System.out.println(collisionPoint);

                    // Inlcudes the balls size in the calculation of angle on edges
                    /*
                    if(collisionPoint < 0.3){
                        collisionPoint += ball.getRadius()/platform.getWidth();
                    } else if (collisionPoint > 0.7){
                        collisionPoint -= ball.getRadius()/platform.getWidth();
                    }
                    */ 

                    // Sets the balls new angle
                    ball.setAngle(-(1 - collisionPoint) * 180);
                   
                    //adjusts for exteme angels
                    if (ball.getAngle() < -160) {ball.setAngle(-160);}
                    if (ball.getAngle() > -20) {ball.setAngle(-20);}
                }
            }
        }
        else {
            ball.setInPlatform(false);
        }
    }

    public static void checkBorderCollision (Ball ball, GameView gameView) {
        
        // Ball hit left wall
        if ((ball.getX() - ball.getRadius() <= 0)) {
            
            // Plays sfx
            SceneManager.getInstance().playHitSFX();
            
            ball.sideHit();
            ball.setX(ball.getRadius());
        // Ball hit right wall
        } else if ((ball.getX() + ball.getRadius() >= OptionsModel.getSceneWidth())) {
            
            // Plays sfx
            SceneManager.getInstance().playHitSFX();

            ball.sideHit();
            ball.setX(OptionsModel.getSceneWidth()-ball.getRadius());
        }

        // Ball hit roof
        if ((ball.getY() - ball.getRadius()) <= gameView.getHudHeight()) {

            // Plays sfx
            SceneManager.getInstance().playHitSFX();

            ball.topBottomHit();
            ball.setY(ball.getRadius()+gameView.getHudHeight());
        }
    }
}
