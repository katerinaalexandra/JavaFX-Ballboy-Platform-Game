package ballboy.model.Strategy;

import ballboy.model.Entity;
import ballboy.model.LevelDao;
import ballboy.model.MovingEntity;

public class HorizontalMovementStrategy implements MovementStrategy {
    @Override
    public void move(Entity entity, LevelDao levelDao) {
        MovingEntity movableEntity = (MovingEntity) entity;

        double timestep=levelDao.getTimestep();
        double gravity=levelDao.getGravity();
        double width=levelDao.getLevelWidth();

        double dx=movableEntity.getXVel();

        if (dx == 0) {
            dx+=0.5;
        }

        double xPos=movableEntity.getXPos();
        double difference=0.0;
        if (xPos - (dx*gravity) < 0 ) {
            difference = (dx*gravity)-xPos;
        } else if (xPos - (dx*gravity) > width) {
            difference = (dx*gravity) - xPos;
        }

        movableEntity.setXPos(xPos-(dx*gravity)+difference);

        if (movableEntity.getXPos() == 0) {
            dx=(-dx);
        } else if (movableEntity.getXPos() == width) {
            dx=(-dx);
        }

        movableEntity.setXVel(dx);


    }

    @Override
    public void collide(Entity thisEntity , Entity collisionEntity, LevelDao levelDao) {

    }
}
