package ballboy.model;

import ballboy.model.Strategy.MovementStrategy;
import javafx.scene.image.Image;

public abstract class MovingEntity implements Entity {
    // Static coordinates
    double xPos;
    double yPos;

    // Used for collision strategy
    double prevXPos;
    double prevYPos;

    double startingX;
    double startingY;

    // Dynamic coordinates
    double xVel;
    double yVel;

    // Entit aesthetics
    double height;
    double width;
    Layer layer;
    String imagePath;
    String size;

    MovementStrategy movementStrategy;

    @Override
    public Image getImage() {
        return new Image(imagePath);
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public Layer getLayer() {
        return layer;
    }

    @Override
    public void tick(LevelDao levelDao) {
        this.movementStrategy.move(this, levelDao);
    }

    public void setXPos(double xPos) { this.xPos=xPos;

    }

    public void setYPos(double yPos) { this.yPos=yPos;

    }

    public double getXVel() {
        return xVel;
    }

    public double getYVel() {
        return yVel;
    }

    public void setXVel(double xVel) {
        this.xVel=xVel;
    }

    public void setYVel(double yVel) {
        this.yVel= yVel;
    }

    public double getStartingX() {return startingX;}

    public double getStartingY() {return startingY;}

    public double getPrevXPos() {return prevXPos;}

    public double getPrevYPos() {return prevYPos;}

    public void setPrevXPos(double xPos) {this.prevXPos=xPos;}

    public void setPrevYPos(double yPos) {this.prevYPos=yPos;}

}
