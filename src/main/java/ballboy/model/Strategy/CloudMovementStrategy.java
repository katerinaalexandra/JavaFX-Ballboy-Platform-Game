package ballboy.model.Strategy;

import ballboy.model.Entity;
import ballboy.model.GameEngine;
import ballboy.model.LevelDao;
import ballboy.model.MovingEntity;

public class CloudMovementStrategy implements MovementStrategy {
    @Override
    public void move(Entity entity, LevelDao levelDao) {
        MovingEntity movableEntity = (MovingEntity) entity;

        // For clouds, only X coordinate should change
        double cloudVelocity = movableEntity.getXVel();

        // displacement = vel * time taken
        double timestep = levelDao.getTimestep();

        double displacement = cloudVelocity * timestep;

        movableEntity.setXPos(movableEntity.getXPos()-displacement);

    }

    @Override
    public void collide(Entity thisEntity, Entity collisionEntity, LevelDao levelDao) {

    }
}
