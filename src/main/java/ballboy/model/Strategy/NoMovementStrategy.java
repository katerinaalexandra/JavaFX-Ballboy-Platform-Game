package ballboy.model.Strategy;

import ballboy.model.Entity;
import ballboy.model.GameEngine;
import ballboy.model.LevelDao;

public class NoMovementStrategy implements MovementStrategy {
    @Override
    public void move(Entity entity, LevelDao levelDao) {

    }

    @Override
    public void collide(Entity thisEntity, Entity collisionEntity, LevelDao levelDao) {

    }
}
