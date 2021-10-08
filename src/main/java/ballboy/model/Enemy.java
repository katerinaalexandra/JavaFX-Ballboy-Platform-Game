package ballboy.model;

import ballboy.model.Strategy.MovementStrategy;
import org.json.simple.JSONObject;

public class Enemy extends MovingEntity {
    String size;

    public Enemy(JSONObject config, MovementStrategy movementStrategy) {
        this.movementStrategy=movementStrategy;

        extractConfig(config);

        this.layer=Layer.FOREGROUND;
        setDimensions(40,40);
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

    }

}
