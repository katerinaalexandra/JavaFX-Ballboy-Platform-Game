package ballboy.model.Strategy;

import ballboy.model.*;

public class BallboyMovementStrategy implements MovementStrategy {
    @Override
    public void move(Entity entity, LevelDao levelDao) {
        System.out.println("Ballboy should bounce");
        MovingEntity movableEntity = (MovingEntity) entity;

        double gravity = levelDao.getGravity();
        double timestep = levelDao.getTimestep();
        double floorHeight= levelDao.getFloorHeight();
        System.out.println("current timestep = " + timestep);

        // Casting relies on correct 'movement' values in config
        double dy = movableEntity.getYVel();
        System.out.println("current yVel = " + dy);
        double yPos = movableEntity.getYPos();

        System.out.println("current yPos = " + yPos);

        double difference=0.0;
        if (yPos + (dy*gravity) > floorHeight) {
            difference = yPos + (dy*gravity) - floorHeight;
        }


        movableEntity.setYPos(yPos + (dy*gravity) - difference);

        System.out.println("new yPos = " + (yPos + (dy*gravity) - difference));

        if (movableEntity.getYPos() == floorHeight) {
            double temp_dy = (-dy);
            dy = temp_dy;
        }

        System.out.println(dy+=timestep);
        movableEntity.setYVel(dy+=timestep);

    }
}
