package ballboy.model;

import ballboy.model.Strategy.MovementStrategy;
import org.json.simple.JSONObject;

public class Wall extends StationaryEntity {
    public Wall(JSONObject config, MovementStrategy movementStrategy) {

        // Set movementStrategy
        this.movementStrategy=movementStrategy;

        extractConfig(config);
        setDimensions(40,40);

        this.layer=Layer.MIDGROUND;
    }

    public void setDimensions(double height, double width) {
        this.height=height;
        this.width=width;
    }

    @Override
    public void extractConfig(JSONObject config) {

        this.size=(String) config.get("size");
        this.xPos = (double) config.get("startingX");
        this.yPos=(double) config.get("startingY");
        this.imagePath=(String) config.get("imagePath");

    }
}
