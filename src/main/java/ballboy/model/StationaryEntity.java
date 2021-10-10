package ballboy.model;

import ballboy.model.Strategy.MovementStrategy;
import javafx.scene.image.Image;
import org.json.simple.JSONObject;

public abstract class StationaryEntity implements Entity {
    // Static coordinates
    double xPos;
    double yPos;

    // Entity aesthetics
    double height;
    double width;
    Layer layer;
    String imagePath;
    public String size;

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
        //this.movementStrategy.move(this, model);
    }

    public void setXPos(double xPos) { this.xPos=xPos;

    }

    public void setYPos(double yPos) { this.yPos=yPos;

    }

}
