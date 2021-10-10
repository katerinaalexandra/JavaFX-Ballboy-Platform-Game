package ballboy.model;

import ballboy.model.Strategy.MovementStrategy;
import org.json.simple.JSONObject;

public class Ballboy extends MovingEntity {
    String size;
    public double yAcceleration=1.0;

    public Ballboy(JSONObject config, MovementStrategy movementStrategy) {
        this.movementStrategy=movementStrategy;
        extractConfig(config);
        setDimensionsHero(this.size);
        this.layer=Layer.FOREGROUND;
    }


    public void setDimensionsHero(String size) {
        switch (size) {
            case "small":
                this.height = 10;
                this.width = 10;
                break;
            case "normal":
                this.height = 20;
                this.width = 20;
                break;
            case "large":
                this.height = 30;
                this.width = 30;
                break;
        }
    }

    public void initialiseHero() {
        this.xPos=startingX;
        this.yPos=startingY;
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

    public double getYAcceleration() {return yAcceleration;}

    public void setYAcceleration(double yAcceleration) {
        this.yAcceleration=yAcceleration;
    }


}
