package ballboy.model;

import ballboy.model.Strategy.MovementStrategy;
import org.json.simple.JSONObject;

public class Cloud extends MovingEntity {

    public Cloud(JSONObject config, MovementStrategy movementStrategy) {

        this.movementStrategy=movementStrategy;

        this.layer=Layer.BACKGROUND;

        extractConfig(config);
        setDimensions(100,100);
        System.out.println("Cloud should be at x coordinate " + this.getXPos());
        System.out.println("Cloud should have this image " + this.getImage());
    }

    public void setDimensions(double height, double width) {
        this.height=height;
        this.width=width;
    }

    @Override
    public void extractConfig(JSONObject config) {
        this.size=(String) config.get("size");

        this.startingX = (double) config.get("startingX");
        this.startingY=(double) config.get("startingY");

        this.xPos = (double) config.get("startingX");
        this.yPos=(double) config.get("startingY");

        this.imagePath=(String) config.get("imagePath");

        this.xVel=(double) config.get("cloudVelocity");

    }

}
