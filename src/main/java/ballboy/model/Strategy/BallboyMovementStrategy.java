package ballboy.model.Strategy;

import ballboy.model.*;

public class BallboyMovementStrategy implements MovementStrategy {
    @Override
    public void move(Entity entity, LevelDao levelDao) {

        MovingEntity movableEntity = (MovingEntity) entity;

        double timestep = levelDao.getTimestep();
        double floorHeight= levelDao.getFloorHeight();

        // Casting relies on correct 'movement' values in config
        double dy = movableEntity.getYVel();
        double yPos = movableEntity.getYPos();


        double difference=0.0;
        if (yPos + dy > floorHeight) {
            difference = yPos + dy - floorHeight;
        }


        movableEntity.setYPos(yPos + dy - difference);


        if (movableEntity.getYPos() == floorHeight) {
            double temp_dy = (-dy);
            dy = temp_dy;
        }
        movableEntity.setYVel(dy+=timestep);

    }
}
