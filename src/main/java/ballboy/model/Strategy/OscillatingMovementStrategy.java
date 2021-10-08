package ballboy.model.Strategy;

import ballboy.model.Entity;
import ballboy.model.GameEngine;
import ballboy.model.LevelDao;
import ballboy.model.MovingEntity;

public class OscillatingMovementStrategy implements MovementStrategy {
    @Override
    public void move(Entity entity, LevelDao levelDao) {
        MovingEntity movableEntity = (MovingEntity) entity;

        // Simple Harmonic Motion
        double timestep=levelDao.getTimestep();


        double dx=0.5;
        double dy=movableEntity.getYVel();

        double xPos=movableEntity.getXPos();
        double yPos=movableEntity.getYPos();

        double upperBound=movableEntity.getStartingY()-100;
        double lowerBound=movableEntity.getStartingY()+100;

        double amplitude=10.0;
        double cosMovement=0.5;

        if (yPos + dy > lowerBound) {
            double temp_dy=(-dy);
            dy=temp_dy;
        } else if (yPos - dy < upperBound) {
            double temp_dy = (-dy);
            dy=temp_dy;
        }

        movableEntity.setYPos(yPos+dy);
        movableEntity.setYVel(dy+timestep);

        movableEntity.setXPos(xPos-dx);

    }
}
